package org.housered.balloons.state;

import com.jme3.math.Vector3f;
import com.jme3.network.serializing.Serializable;

@Serializable
public class SimpleState implements State
{
    private final long entityId;
    private final Vector3f position;

    public SimpleState(long entityId, Vector3f position)
    {
        this.entityId = entityId;
        this.position = position;
    }

    @Override
    public long getEntityId()
    {
        return entityId;
    }

    public Vector3f getPosition()
    {
        return position;
    }
}
