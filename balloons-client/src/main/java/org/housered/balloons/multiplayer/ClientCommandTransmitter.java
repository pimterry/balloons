package org.housered.balloons.multiplayer;

import org.housered.balloons.command.Command;
import org.housered.balloons.command.CommandSubscriber;

import com.jme3.network.Client;

/**
 * Responsible for sending commands that have been raised by the client to the server.
 */
public class ClientCommandTransmitter implements CommandSubscriber
{
    private Client client;

    public ClientCommandTransmitter(Client client)
    {
        this.client = client;
    }

    @Override
    public void commandRaised(Command command)
    {
        client.send(command);
    }
}
