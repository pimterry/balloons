package org.housered.balloons;

import java.util.HashMap;
import java.util.Map;

import org.housered.balloons.state.State;
import org.housered.balloons.state.StateReceiver;

import com.jme3.scene.Spatial;

public class WorldManager
{
    private Map<Long, Spatial> entities = new HashMap<Long, Spatial>();

    public boolean doesEntityExist(long id)
    {
        return entities.containsKey(id);
    }

    public Spatial getEntity(long id)
    {
        return entities.get(id);
    }

    public StateReceiver createStateReceivingEntity(long id, State initialState)
    {
        //find out what kind of entity
        return null;
    }
}
