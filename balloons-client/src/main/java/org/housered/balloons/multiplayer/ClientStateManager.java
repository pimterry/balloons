package org.housered.balloons.multiplayer;

import java.util.HashMap;
import java.util.Map;

import org.housered.balloons.WorldManager;
import org.housered.balloons.entity.SimpleStateReceivingControl;
import org.housered.balloons.state.Snapshot;
import org.housered.balloons.state.State;
import org.housered.balloons.state.StateReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.scene.Spatial;

/**
 * Snapshots are received from the server and define a list of states, one for each entity. This
 * will receive snapshots in an event-driven manner, split them up into states and then pass these
 * (in the same thread) to the relevant entities. <br />
 * This manager is also responsible for checking all incoming states for new entities, which it must
 * then create using the WorldManager.
 * 
 * @author Ed
 */
public class ClientStateManager implements MessageListener<Client>
{
    private static final Logger LOG = LoggerFactory.getLogger(ClientStateManager.class);
    
    private final WorldManager worldManager;
    private final Map<Long, StateReceiver> stateReceivers;

    public ClientStateManager(WorldManager worldManager, Client client)
    {
        this.worldManager = worldManager;
        this.stateReceivers = new HashMap<Long, StateReceiver>();
        client.addMessageListener(this, Snapshot.class);
    }

    @Override
    public void messageReceived(Client client, Message message)
    {
        LOG.trace("received message from server");
        handleSnapshot((Snapshot) message);
    }

    private void handleSnapshot(Snapshot snapshot)
    {
        for (State state : snapshot.getStates())
        {
            handleState(state);
        }
    }

    private void handleState(State state)
    {
        long entityId = state.getEntityId();
        StateReceiver stateReceiver;

        if (stateReceivers.containsKey(entityId))
        {
            stateReceiver = stateReceivers.get(state.getEntityId());
        }
        else
        {
            LOG.debug("Creating new entity with id {}", state.getEntityId());
            Spatial newEntity = createNewStateReceivingEntity(entityId);
            stateReceiver = newEntity.getControl(StateReceiver.class);
            stateReceivers.put(entityId, stateReceiver);
        }

        stateReceiver.updateWithState(state);
    }

    //FIXME: add actual entity type etc
    private Spatial createNewStateReceivingEntity(long entityId)
    {
        Spatial entity = worldManager.createEntity(entityId);

        //FIXME: add the correct state receiving control, create some factories
        SimpleStateReceivingControl control = new SimpleStateReceivingControl();
        entity.addControl(control);

        return entity;
    }
}
