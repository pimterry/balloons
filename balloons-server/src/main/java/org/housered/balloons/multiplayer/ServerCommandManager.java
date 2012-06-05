package org.housered.balloons.multiplayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.housered.balloons.WorldManager;
import org.housered.balloons.command.Command;
import org.housered.balloons.command.CommandSubscriber;
import org.housered.balloons.command.FireWeaponCommand;

import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Server;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;

public class ServerCommandManager implements MessageListener<HostedConnection>, ConnectionListener
{
    private final WorldManager worldManager;
    private Map<Integer, Queue<Command>> bufferedCommands = new HashMap<Integer, Queue<Command>>();

    public ServerCommandManager(Server server, WorldManager worldManager)
    {
        this.worldManager = worldManager;
        server.addConnectionListener(this);
        //TODO: so yea, have to define concretes here I think
        server.addMessageListener(this, FireWeaponCommand.class);
    }

    @Override
    public void messageReceived(HostedConnection source, Message message)
    {
        //TODO: add a subscriber model
        for (Spatial spatial : worldManager.getEntities())
        {
            for (int i = 0; i < spatial.getNumControls(); i++)
            {
                //TODO: multiple controls on one entity may want commands, so have to iterate
                //possible to do nicer?
                Control control = spatial.getControl(i);
                if (control instanceof CommandSubscriber)
                {
                    CommandSubscriber subscriber = (CommandSubscriber) control;
                    subscriber.commandRaised((Command) message);
                }
            }
        }
    }

    @Override
    public void connectionAdded(Server server, HostedConnection client)
    {
        bufferedCommands.put(client.getId(), new ConcurrentLinkedQueue<Command>());
    }

    @Override
    public void connectionRemoved(Server server, HostedConnection client)
    {
        bufferedCommands.remove(client.getId());
    }
}
