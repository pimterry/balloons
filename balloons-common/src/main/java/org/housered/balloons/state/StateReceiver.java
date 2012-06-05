package org.housered.balloons.state;

import com.jme3.scene.control.Control;

public interface StateReceiver extends Control
{
    void updateWithState(State state);
}
