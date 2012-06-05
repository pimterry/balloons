package org.housered.balloons.command;

import com.jme3.network.AbstractMessage;

public abstract class Command extends AbstractMessage
{
    public abstract void executeWith(CommandExecutor executor);
}
