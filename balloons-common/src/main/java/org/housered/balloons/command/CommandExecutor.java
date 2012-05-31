package org.housered.balloons.command;

import java.util.LinkedList;
import java.util.Queue;

public abstract class CommandExecutor implements CommandSubscriber
{
    private Queue<Command> commands = new LinkedList<Command>();
    private Queue<Command> commandsBuffer = new LinkedList<Command>();
    
    public void commandRaised(Command command) {
        synchronized(commands) {
            commands.add(command);
        }
    }
    
    public void unbufferCommands() {
        synchronized(commands) {
            Queue<Command> tempCommandsQueue = commands;
            commands = commandsBuffer;
            commandsBuffer = tempCommandsQueue;
            commands.clear();
        }
        
        for (Command cmd : commandsBuffer) {
            cmd.executeWith(this);
        }
    }
    
    public void executeCommand(Command command) {
        // Do nothing!
    }
}
