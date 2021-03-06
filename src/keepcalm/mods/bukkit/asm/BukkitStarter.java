package keepcalm.mods.bukkit.asm;

import java.util.logging.Level;

import keepcalm.mods.bukkit.bukkitAPI.BukkitServer;
import keepcalm.mods.bukkit.forgeHandler.ForgeEventHandler;
import keepcalm.mods.bukkit.forgeHandler.commands.BukkitCommandHelp;
import keepcalm.mods.bukkit.forgeHandler.commands.BukkitCommandMVFix;
import keepcalm.mods.bukkit.forgeHandler.commands.CommandRequirementRegistry;
import keepcalm.mods.bukkit.forgeHandler.commands.CommandSetLevel;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;

public class BukkitStarter implements Runnable {
	private MinecraftServer server;
	public BukkitStarter(MinecraftServer serv) {
		server = serv;
	}
	
	@Override
	public void run() {
		try {
			//URL[] urls = ((URLClassLoader)getClass().getClassLoader()).getURLs();
			//BukkitClassLoader newCL = new BukkitClassLoader(urls, getClass().getClassLoader());
			//newCL.loadClass("keepcalm.mods.bukkit.bukkitAPI.BukkitServer").getMethod("bukkitReEntry").invoke(null);
			ServerCommandManager scm = (ServerCommandManager) server.getCommandManager();
			scm.registerCommand(new BukkitCommandHelp());
			scm.registerCommand(new BukkitCommandMVFix());
			CommandRequirementRegistry.load();
			scm.registerCommand(new CommandSetLevel());
			BukkitContainer.bukkitLogger.info("Starting the API, implementing Bukkit API version " + BukkitServer.version);
			
			//System.out.println("Starting the API...");
			BukkitContainer.bServer = new BukkitServer(MinecraftServer.getServer());
			//BukkitContainer.bServer.setServer(MinecraftServer.getServer());
			//BukkitContainer.bServer.continueLoad();
			// hopefully this works...
		}
		catch (Exception e) {
			// disable nicely
			ForgeEventHandler.ready = false;
			FMLCommonHandler.instance().getFMLLogger().log(Level.SEVERE, "Something real bad happened! The BukkitAPI will not be running for this Minecraft session.", e);
		}

	}
	
	

}
