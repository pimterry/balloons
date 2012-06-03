package org.housered.balloons;

import static com.jme3.network.serializing.Serializer.registerClass;
import static com.jme3.network.serializing.Serializer.registerClasses;

import org.housered.balloons.command.FireWeaponCommand;
import org.housered.balloons.state.Snapshot;
import org.housered.balloons.state.State;

public class SerializableRegistry
{
    private SerializableRegistry()
    {

    }

    /**
     * Must contain all Serializable classes that will be passed between server and clients.
     */
    public static void registerSerializables()
    {
        //states
        registerClasses(Snapshot.class, State.class);

        //commands
        registerClass(FireWeaponCommand.class);
    }
}
