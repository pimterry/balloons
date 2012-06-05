package org.housered.balloons;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.slf4j.bridge.SLF4JBridgeHandler;

public class Globals
{
    public static final String GAME_NAME = "balloons";
    public static final int GAME_VERSION = 1;
    public static final int DEFAULT_UDP_PORT = 8525;
    public static final int DEFAULT_TCP_PORT = 8526;

    public static void fixLogging()
    {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
        Logger.getLogger("global").setLevel(Level.FINEST);
    }
}
