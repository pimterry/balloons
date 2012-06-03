package org.housered.balloons.entity;

import org.housered.balloons.state.SimpleState;
import org.housered.balloons.state.State;
import org.housered.balloons.state.StateReceiver;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

public class SimpleStateReceivingControl extends AbstractControl implements StateReceiver
{
    private State state;
    private State stateBuffer;

    @Override
    public Control cloneForSpatial(Spatial spatial)
    {
        SimpleStateReceivingControl control = new SimpleStateReceivingControl();
        control.setSpatial(spatial);
        return control;
    }

    @Override
    public void updateWithState(State state)
    {
        synchronized (this.state)
        {
            this.state = state;
        }
    }

    @Override
    protected void controlUpdate(float tpf)
    {
        synchronized (state)
        {
            State temp = state;
            state = stateBuffer;
            stateBuffer = temp;
        }

        getSpatial().setLocalTranslation(((SimpleState) state).getPosition());
    }

    @Override
    protected void controlRender(RenderManager arg0, ViewPort arg1)
    {
    }
}
