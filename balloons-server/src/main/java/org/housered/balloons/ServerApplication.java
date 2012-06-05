package org.housered.balloons;

import static org.housered.balloons.Globals.DEFAULT_TCP_PORT;
import static org.housered.balloons.Globals.DEFAULT_UDP_PORT;
import static org.housered.balloons.Globals.GAME_NAME;
import static org.housered.balloons.Globals.GAME_VERSION;

import java.io.IOException;

import org.housered.balloons.entity.SimpleStateCreatingControl;
import org.housered.balloons.multiplayer.ServerCommandManager;
import org.housered.balloons.multiplayer.ServerStateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.scene.Spatial;
import com.jme3.system.JmeContext.Type;

public class ServerApplication extends SimpleApplication
{
    private static final Logger LOG = LoggerFactory.getLogger(ServerApplication.class);

    private Server server;
    private ServerCommandManager commandListener;
    private ServerStateManager stateManager;
    private WorldManager worldManager;

    private Spatial tempEntity;

    public static void main(String[] args)
    {
        Globals.fixLogging();
        ServerApplication server = new ServerApplication();
        server.start(Type.Headless);
    }

    @Override
    public void simpleInitApp()
    {
        LOG.debug("Initialising server");
        initNetwork();
        initManagers();
        tempCreateEntities();
    }
    
    @Override
    public void update()
    {
        //FIXME: we have to call this super method or appstates' updates aren't called
        super.update();
        //tempEntity.move(0.010f, 0, 0);
    }

    private void tempCreateEntities()
    {
        tempEntity = worldManager.createEntity();
        tempEntity.addControl(new SimpleStateCreatingControl());
    }

    private void initManagers()
    {
        commandListener = new ServerCommandManager(server);
        worldManager = new WorldManager(getRootNode(), assetManager);
        stateManager = new ServerStateManager(worldManager, server);

        getStateManager().attach(worldManager);
        getStateManager().attach(stateManager);
    }

    private void initNetwork()
    {
        SerializableRegistry.registerSerializables();
        try
        {
            server = Network.createServer(GAME_NAME, GAME_VERSION, DEFAULT_TCP_PORT, DEFAULT_UDP_PORT);
            server.start();
        }
        catch (IOException e)
        {
            LOG.error("Could not create server", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy()
    {
        server.close();
        super.destroy();
    }
}
