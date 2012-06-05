package org.housered.balloons.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.SceneGraphVisitor;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;

public class ColourChangingCommandExecutorControl extends AbstractControl implements CommandExecutor, CommandSubscriber
{
    private static final Logger LOG = LoggerFactory.getLogger(ColourChangingCommandExecutorControl.class);
    private final BufferedCommandExecutorHelper executorHelper;

    public ColourChangingCommandExecutorControl()
    {
        executorHelper = new BufferedCommandExecutorHelper(this);
    }

    @Override
    public Control cloneForSpatial(Spatial spatial)
    {
        Control copy = new ColourChangingCommandExecutorControl();
        copy.setSpatial(spatial);
        return copy;
    }

    @Override
    protected void controlRender(RenderManager arg0, ViewPort arg1)
    {
    }

    @Override
    protected void controlUpdate(float arg0)
    {
        executorHelper.unbufferCommands();
    }

    @Override
    public void executeCommand(Command cmd)
    {
        LOG.debug("Nothing goin' on here - {}", cmd);
        executeCommand((FireWeaponCommand) cmd);
    }

    public void executeCommand(FireWeaponCommand cmd)
    {
        LOG.debug("Executing FireWeaponCommand - {}", cmd);
        
        executeCommandOnSpatial(spatial);
        getSpatial().breadthFirstTraversal(new SceneGraphVisitor() {

            @Override
            public void visit(Spatial child)
            {
                executeCommandOnSpatial(child);
            }
        });
    }

    private void executeCommandOnSpatial(Spatial spatial)
    {
        if (spatial instanceof Geometry)
        {
            //TODO: duh, colour isn't synced
            Geometry geo = (Geometry) spatial;
            geo.getMaterial().setColor("Color", ColorRGBA.randomColor());
            
            
        }
        
        spatial.move(0.1f, 0, 0);
    }

    @Override
    public void commandRaised(Command command)
    {
        executorHelper.bufferCommand(command);
    }

}
