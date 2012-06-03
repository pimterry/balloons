package org.housered.balloons;

import org.housered.balloons.entity.SimpleStateReceivingControl;
import org.housered.balloons.state.State;
import org.housered.balloons.state.StateReceiver;

import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class WorldManager
{
    public StateReceiver createStateReceivingEntity(long id, State initialState)
    {
        // TODO: create different types of entity
        // Add the necessary controls
        Box box = new Box(new Vector3f(1, -1, 1), 1, 1, 1);
        Geometry entity = new Geometry("box", box);
        
        SimpleStateReceivingControl control = new SimpleStateReceivingControl();
        entity.addControl(control);
        
        return control;
    }
}
