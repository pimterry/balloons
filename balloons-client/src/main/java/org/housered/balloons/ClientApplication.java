package org.housered.balloons;

import static org.housered.balloons.Globals.DEFAULT_TCP_PORT;
import static org.housered.balloons.Globals.DEFAULT_UDP_PORT;
import static org.housered.balloons.Globals.GAME_NAME;
import static org.housered.balloons.Globals.GAME_VERSION;

import java.io.IOException;

import org.housered.balloons.multiplayer.ClientCommandTransmitter;
import org.housered.balloons.multiplayer.ClientStateManager;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Client;
import com.jme3.network.Network;

public class ClientApplication extends SimpleApplication
{
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
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

        worldManager = new WorldManager(assetManager);
        commandTransmitter = new ClientCommandTransmitter(client);
        stateManager = new ClientStateManager(worldManager, client);
    }

    @Override
    public void destroy()
    {
        client.close();
        super.destroy();
    }
}
