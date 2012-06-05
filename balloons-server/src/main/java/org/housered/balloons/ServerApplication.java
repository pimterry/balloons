package org.housered.balloons;

import static org.housered.balloons.Globals.DEFAULT_TCP_PORT;
import static org.housered.balloons.Globals.DEFAULT_UDP_PORT;
import static org.housered.balloons.Globals.GAME_NAME;
import static org.housered.balloons.Globals.GAME_VERSION;

import java.io.IOException;

import org.housered.balloons.multiplayer.ServerCommandManager;
import org.housered.balloons.multiplayer.ServerStateManager;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.scene.Spatial;
import com.jme3.system.JmeContext.Type;

public class ServerApplication extends SimpleApplication
{
    private Server server;
    private ServerCommandManager commandListener;
    private ServerStateManager stateManager;
    private WorldManager worldManager;

    public static void main(String[] args)
    {
        ServerApplication server = new ServerApplication();
        server.start(Type.Headless);
    }

    @Override
    public void simpleInitApp()
    {
        initNetwork();
        initManagers();
        tempCreateEntities();
    }

    private void tempCreateEntities()
    {
        Spatial entity = worldManager.createEntity();
        getRootNode().attachChild(entity);
    }
    
    private void initManagers()
    {
        commandListener = new ServerCommandManager(server);
        worldManager = new WorldManager(assetManager);
        stateManager = new ServerStateManager(worldManager, server);
        
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
            //FIXME: add logging
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void destroy()
    {
        server.close();
        super.destroy();
    }
}
