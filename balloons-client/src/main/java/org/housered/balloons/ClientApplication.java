package org.housered.balloons;

import static org.housered.balloons.Globals.DEFAULT_TCP_PORT;
import static org.housered.balloons.Globals.DEFAULT_UDP_PORT;
import static org.housered.balloons.Globals.GAME_NAME;
import static org.housered.balloons.Globals.GAME_VERSION;

import java.io.IOException;

import org.housered.balloons.multiplayer.ClientCommandTransmitter;
import org.housered.balloons.multiplayer.ClientStateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Client;
import com.jme3.network.Network;

public class ClientApplication extends SimpleApplication
{
    private static final Logger LOG = LoggerFactory.getLogger(ClientApplication.class);
    
    private Client client;
    private ClientCommandTransmitter commandTransmitter;
    private ClientStateManager stateManager;
    private WorldManager worldManager;

    public static void main(String[] args)
    {
        Globals.fixLogging();
        ClientApplication app = new ClientApplication();
        app.start();
    }

    @Override
    public void simpleInitApp()
    {
        initNetwork();
        initManagers();
    }
    
    private void initManagers()
    {
        worldManager = new WorldManager(getRootNode(), assetManager);
        commandTransmitter = new ClientCommandTransmitter(client);
        stateManager = new ClientStateManager(worldManager, client);
        
        getStateManager().attach(worldManager);
    }

    private void initNetwork()
    {
        SerializableRegistry.registerSerializables();
        try
        {
            client = Network.connectToServer(GAME_NAME, GAME_VERSION, "localhost", DEFAULT_TCP_PORT, DEFAULT_UDP_PORT);
            client.start();
        }
        catch (IOException e)
        {
            LOG.error("Could not create client", e);
            throw new RuntimeException(e);
        }
    }
    
    

    @Override
    public void destroy()
    {
        client.close();
        super.destroy();
    }
}
