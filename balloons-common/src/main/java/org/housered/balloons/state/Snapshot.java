package org.housered.balloons.state;

import java.util.LinkedList;
import java.util.List;

import com.jme3.network.AbstractMessage;
import com.jme3.network.serializing.Serializable;

/**
 * Contains 0-n states, sent from the server to the client to update entities.
 * 
 * @author Ed
 */
@Serializable
public class Snapshot extends AbstractMessage
{
    private List<State> states = new LinkedList<State>();

    public void addState(State state)
    {
        //TODO: check only one state per entity
        states.add(state);
    }

    public List<State> getStates()
    {
        return states;
    }
}
