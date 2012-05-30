package org.housered.balloons;

import org.housered.balloons.command.FireWeaponCommand;

import com.jme3.network.serializing.Serializer;

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
        Serializer.registerClass(FireWeaponCommand.class);
    }
}
