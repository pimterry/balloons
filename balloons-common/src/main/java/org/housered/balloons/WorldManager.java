package org.housered.balloons;

import org.housered.balloons.entity.SimpleStateReceivingControl;
import org.housered.balloons.state.State;
import org.housered.balloons.state.StateReceiver;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class WorldManager
{
    private final AssetManager assetManager;

    public WorldManager(AssetManager assetManger)
    {
        this.assetManager = assetManger;
    }

    public StateReceiver createStateReceivingEntity(long id, State initialState)
    {
        // TODO: create different types of entity
        Box box = new Box(new Vector3f(1, -1, 1), 1, 1, 1);
        Geometry entity = new Geometry("box", box);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        entity.setMaterial(mat);

        //add the necessary controls
        SimpleStateReceivingControl control = new SimpleStateReceivingControl();
        entity.addControl(control);

        return control;
    }
}
