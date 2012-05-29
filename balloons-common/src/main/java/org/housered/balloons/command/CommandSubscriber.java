package org.housered.balloons.command;

public interface CommandSubscriber
{
    void commandRaised(Command command);
}
