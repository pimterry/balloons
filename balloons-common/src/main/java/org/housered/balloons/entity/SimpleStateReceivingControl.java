package org.housered.balloons.entity;

import org.housered.balloons.state.SimpleState;
import org.housered.balloons.state.State;
import org.housered.balloons.state.StateReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

public class SimpleStateReceivingControl extends AbstractControl implements StateReceiver
{
    private static final Logger LOG = LoggerFactory.getLogger(SimpleStateReceivingControl.class);

    private Object syncObject = new Object();
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
        synchronized (syncObject)
        {
            this.state = state;
        }
    }

    @Override
    protected void controlUpdate(float tpf)
    {
        synchronized (syncObject)
        {
            if (state == null)
                return;

            State temp = state;
            state = stateBuffer;
            stateBuffer = temp;
        }

        LOG.debug("Updating entity to position {}", ((SimpleState) stateBuffer).getPosition());
        getSpatial().setLocalTranslation(((SimpleState) stateBuffer).getPosition());
    }

    @Override
    protected void controlRender(RenderManager arg0, ViewPort arg1)
    {
    }
}
