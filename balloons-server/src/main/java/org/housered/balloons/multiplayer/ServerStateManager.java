package org.housered.balloons.multiplayer;

import org.housered.balloons.WorldManager;
import org.housered.balloons.state.Snapshot;
import org.housered.balloons.state.StateGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.app.state.AbstractAppState;
import com.jme3.network.Server;
import com.jme3.scene.Spatial;

public class ServerStateManager extends AbstractAppState
{
    private static final Logger LOG = LoggerFactory.getLogger(ServerStateManager.class);

    private final Server server;
    private final WorldManager worldManager;

    public ServerStateManager(WorldManager worldManager, Server server)
    {
        this.server = server;
        this.worldManager = worldManager;
    }

    @Override
    public void update(float tpf)
    {
        LOG.trace("creating world snapshot, tpf is {} s", tpf);

        Snapshot snapshot = new Snapshot();

        //FIXME: keep own list?
        for (Spatial entity : worldManager.getEntities())
        {
            StateGenerator generator = entity.getControl(StateGenerator.class);

            if (generator != null)
            {
                snapshot.addState(generator.generateCurrentState());
            }
        }

        LOG.trace("broadcasting world snapshot, {} states", snapshot.getStates().size());
        server.broadcast(snapshot);
    }
}
