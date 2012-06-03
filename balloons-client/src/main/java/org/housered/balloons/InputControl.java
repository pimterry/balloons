package org.housered.balloons;

import java.util.ArrayList;

import org.housered.balloons.command.Command;
import org.housered.balloons.command.CommandSubscriber;
import org.housered.balloons.command.FireWeaponCommand;

import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

public class InputControl extends AbstractControl implements ActionListener, AnalogListener
{

    public static enum ACTIONS
    {
        FIRE
    }

    private ArrayList<CommandSubscriber> subscribers = new ArrayList<CommandSubscriber>();

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
        Command cmd = null;

        if (ACTIONS.FIRE.name().equals(name))
        {
            cmd = new FireWeaponCommand(keyPressed);
        }
        else
        {
            throw new IllegalArgumentException("Bad action");
        }

        raiseCommand(cmd);
    }

    public void registerSubscriber(CommandSubscriber subscriber)
    {
        subscribers.add(subscriber);
    }

    private void raiseCommand(Command command)
    {
        for (CommandSubscriber subscriber : subscribers)
        {
            subscriber.commandRaised(command);
        }
    }
}
