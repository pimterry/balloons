package org.housered.balloons;

import java.util.Queue;

import org.housered.balloons.command.Command;

public interface CommandProvider
{
    /**
     * Returns the ordered commands for the given hosted connection id, or an empty queue if no
     * commands are available.
     */
    Queue<Command> getCommands(int connectionId);
}
