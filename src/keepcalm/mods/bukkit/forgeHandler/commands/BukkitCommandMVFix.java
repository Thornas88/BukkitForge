package keepcalm.mods.bukkit.forgeHandler.commands;

import keepcalm.mods.bukkit.asm.BukkitContainer;
import net.minecraft.command.ICommandSender;
/**
 * A command to fix compatibility issues with plugins like MultiVerse - which i __suspect__ use 
 * aliases.
 */
public class BukkitCommandMVFix extends BukkitCommandBase {
	
	private boolean hasBeenAdded = false;
	
	public BukkitCommandMVFix() {
		
		
	}
	
	@Override
	public String getCommandName() {
		
		return "bexec";
	}
	
	public String getCommandUsage(ICommandSender var1) {
		return "/bexec <bukkit command> <arguments>";
	}
	
	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		BukkitContainer.bServer.getRealCmdMap().dispatch(new BukkitCommandSender(var1), this.joinListOfStrings(var2));
	}

}
