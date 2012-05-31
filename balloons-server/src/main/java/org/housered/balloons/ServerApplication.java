package org.housered.balloons;

import static org.housered.balloons.Globals.DEFAULT_TCP_PORT;
import static org.housered.balloons.Globals.DEFAULT_UDP_PORT;
import static org.housered.balloons.Globals.GAME_NAME;
import static org.housered.balloons.Globals.GAME_VERSION;

import java.io.IOException;

import org.housered.balloons.multiplayer.ServerCommandManager;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.system.JmeContext.Type;

public class ServerApplication extends SimpleApplication
{
    private Server server;
    private ServerCommandManager commandListener;

    public static void main(String[] args)
    {
        ServerApplication server = new ServerApplication();
        server.start(Type.Headless);
    }

    @Override
    public void simpleInitApp()
    {
        initNetwork();
    }

    private void initNetwork()
    {
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

        SerializableRegistry.registerSerializables();
        commandListener = new ServerCommandManager();

        server.addConnectionListener(commandListener);
        server.addMessageListener(commandListener);
    }
    
    @Override
    public void destroy()
    {
        server.close();
        super.destroy();
    }
}
