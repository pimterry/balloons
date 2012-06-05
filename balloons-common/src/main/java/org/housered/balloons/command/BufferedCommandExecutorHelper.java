package org.housered.balloons.command;

import java.util.LinkedList;
import java.util.Queue;

public class BufferedCommandExecutorHelper
{
    private final CommandExecutor executor;
    private Queue<Command> commandsBuffer = new LinkedList<Command>();
    private Queue<Command> commands = new LinkedList<Command>();

    public BufferedCommandExecutorHelper(CommandExecutor executor)
    {
        this.executor = executor;
    }

    public void bufferCommand(Command command)
    {
        synchronized (commandsBuffer)
        {
            commandsBuffer.add(command);
        }
    }

    public void unbufferCommands()
    {
        synchronized (commandsBuffer)
        {
            Queue<Command> tempCommandsQueue = commandsBuffer;
            commandsBuffer = commands;
            commands = tempCommandsQueue;
            commandsBuffer.clear();
        }

        for (Command cmd : commands)
        {
            cmd.executeWith(executor);
        }
    }
}
