package org.housered.balloons.entity;

import org.housered.balloons.state.SimpleState;
import org.housered.balloons.state.State;
import org.housered.balloons.state.StateGenerator;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

public class SimpleStateCreatingControl extends AbstractControl implements StateGenerator
{

    @Override
    public Control cloneForSpatial(Spatial arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public State generateCurrentState()
    {
        return new SimpleState((Long) spatial.getUserData("entityId"), spatial.getLocalTranslation());
    }

    @Override
    protected void controlRender(RenderManager arg0, ViewPort arg1)
    {
    }

    @Override
    protected void controlUpdate(float arg0)
    {
    }

}
