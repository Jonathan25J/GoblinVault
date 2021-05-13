package me.jonaqhan.goblinvault.events;

import java.io.File;
import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.jonaqhan.goblinvault.Main;
import me.jonaqhan.goblinvault.utils.Chat;
import me.jonaqhan.goblinvault.vault.Open;

public class OpenVault implements CommandExecutor {
	public Main plugin;

	public OpenVault(Main plugin) {
		this.plugin = plugin;

		plugin.getCommand("vault").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {

		if (!(sender instanceof Player))
			return false;

		Open manager = new Open(plugin);
		Player p = (Player) sender;
		
		if (!p.hasPermission("goblinvault.use")) {
			p.sendMessage(Chat.color("#FFB300[#13DB25Goblin Vault#FFB300] &cyou need the goblinvault.use permission"));
			return false;
		}
		
		if (args.length >= 2) {
			p.sendMessage(Chat.color("#FFB300[#13DB25Goblin Vault#FFB300] &c/vault <number>"));
			return false;
		}

		File file = createFile(p);

		int number = 1;
		try {
			number = Integer.valueOf(args[0]);
		} catch (Exception e) {
			p.sendMessage(Chat.color("#FFB300[#13DB25Goblin Vault#FFB300] &cyou need to give a value"));
			return false;
		}
		
		if (number <= 0) {
			p.sendMessage(Chat.color("#FFB300[#13DB25Goblin Vault#FFB300] &cnumber must be above 0"));
			return false;
		}

		if (p.hasPermission("goblinvault.use.vault." + args[0])) {

			manager.createVault(file, number, p);
			
			p.sendMessage(Chat.color("#FFB300[#13DB25Goblin Vault#FFB300] &aopened &bvault &e" + number));
		} else {
			p.sendMessage(Chat.color("#FFB300[#13DB25Goblin Vault#FFB300] &cyou need the goblinvault.use.vault." + args[0] + " permission!"));
		}

		return false;
	}

	public File createFile(Player p) {

		File dir = new File(plugin.getDataFolder() + File.separator + "Vaults");

		if (!dir.exists())
			dir.mkdirs();
		File file = new File(dir + File.separator + p.getUniqueId().toString() + ".yml");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return file;
	}

}
