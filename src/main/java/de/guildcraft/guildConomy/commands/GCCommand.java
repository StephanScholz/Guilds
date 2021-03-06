package de.guildcraft.guildConomy.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.guildcraft.guildConomy.GCPlugin;

public class GCCommand implements CommandExecutor{
	
	private final Map<String, GCSubcommand> subcommands = new HashMap<String, GCSubcommand> ();
	
	GCBalanceCommand balanceCommand;
	
	public GCCommand(GCPlugin plugin) {
		subcommands.put("help", new GCHelpCommand(plugin));
		subcommands.put("set", new GCSetCommand(plugin));
		subcommands.put("clear", new GCClearCommand(plugin));
		subcommands.put("pay", new GCPayCommand(plugin));
		subcommands.put("give", new GCGiveCommand(plugin));
		subcommands.put("take", new GCTakeCommand(plugin));
		subcommands.put("top", new GCTopCommand(plugin));
		subcommands.put("record", new GCRecordCommand(plugin));
		subcommands.put("vp", new GCVotepointsCommand(plugin));
		subcommands.put("check", new GCCheckCommand(plugin));
		balanceCommand = new GCBalanceCommand(plugin);
	}
	
	// ---------------------------------------------------------------------------------------------

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length > 0) {
			for (Entry<String, GCSubcommand> entry : subcommands.entrySet()) {
				if (entry.getKey().equals(args[0])) {
					return entry.getValue().permissionExecute(sender, getSubArgs(args));
				}
			}
		} else {
			return balanceCommand.permissionExecute(sender, new String[0]);
		}
		sender.sendMessage(ChatColor.RED + "Der Befehl wurde leider nicht gefunden");
		return false;
	}

	// ---------------------------------------------------------------------------------------------

	private String[] getSubArgs(String[] args) {
		String[] subArgs = new String[args.length - 1];
		for (int i = 1; i < args.length; ++i)
			subArgs[i - 1] = args[i];
		return subArgs;
	}

	// ---------------------------------------------------------------------------------------------
	
}
