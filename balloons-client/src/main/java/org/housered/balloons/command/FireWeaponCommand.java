package org.housered.balloons.command;

import com.jme3.network.serializing.Serializable;

@Serializable
public class FireWeaponCommand extends Command
{
	private boolean keyPressed;
	
	public FireWeaponCommand()
	{
		keyPressed = false;
	}
	
	public FireWeaponCommand(boolean keyPressed)
	{
		this.keyPressed = keyPressed;
	}
	
	public boolean isKeyPressed()
	{
		return keyPressed;
	}
}
