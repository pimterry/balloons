package org.housered.balloons.multiplayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.housered.balloons.CommandProvider;
import org.housered.balloons.command.Command;

import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import com.jme3.network.Server;

public class ServerCommandManager implements MessageListener<HostedConnection>, ConnectionListener, CommandProvider
{
    private Map<Integer, Queue<Command>> bufferedCommands = new HashMap<Integer, Queue<Command>>();

    public ServerCommandManager(Server server)
    {
        server.addConnectionListener(this);
        server.addMessageListener(this, Command.class);
    }

    @Override
    public void messageReceived(HostedConnection source, Message message)
    {
        //we could receive many different messages in the future, design/refactoring required.
        Command cmd = (Command) message;
        bufferedCommands.get(source.getId()).add(cmd);
    }

    @Override
    public Queue<Command> getCommands(int connectionId)
    {
        return bufferedCommands.get(connectionId);
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
