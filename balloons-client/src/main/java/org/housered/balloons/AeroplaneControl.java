package org.housered.balloons;

import org.housered.balloons.command.CommandRaiser;

import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

public class AeroplaneControl extends AbstractControl implements ActionListener, AnalogListener
{
    private CommandRaiser raiser;

    public AeroplaneControl(CommandRaiser raiser)
    {
        this.raiser = raiser;
    }

    @Override
    public Control cloneForSpatial(Spatial arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected void controlRender(RenderManager arg0, ViewPort arg1)
    {
        // TODO Auto-generated method stub
    }

    @Override
    protected void controlUpdate(float arg0)
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void onAnalog(String name, float value, float tpf)
    {
    }

    @Override
    public void onAction(String name, boolean keyPressed, float tpf)
    {
    }
}
