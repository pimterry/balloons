package org.housered.balloons.state;

import com.jme3.scene.control.Control;

public interface StateGenerator extends Control
{
    State generateCurrentState();
}
