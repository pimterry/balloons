package org.housered.balloons.multiplayer;

import org.housered.balloons.WorldManager;
import org.housered.balloons.state.Snapshot;
import org.housered.balloons.state.StateGenerator;

import com.jme3.app.state.AbstractAppState;
import com.jme3.network.Server;
import com.jme3.scene.Spatial;

public class ServerStateManager extends AbstractAppState
{
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
        
        server.broadcast(snapshot);
    }
}
