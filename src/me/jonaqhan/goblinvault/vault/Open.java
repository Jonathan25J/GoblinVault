package me.jonaqhan.goblinvault.vault;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.jonaqhan.goblinvault.Main;
import me.jonaqhan.goblinvault.utils.Chat;

public class Open {
	public Main plugin;

	public Open(Main plugin) {
		this.plugin = plugin;
	}

	public void createVault(File file, int number, Player p) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);

		Inventory inv = Bukkit.createInventory(null, 54, Chat.color("&aGoblin Vault &b" + number));
		if (config.get("Vaults." + String.valueOf(number)) != null) {

			@SuppressWarnings("unchecked")
			ItemStack[] items = ((List<ItemStack>) config.get("Vaults." + String.valueOf(number)))
					.toArray(new ItemStack[0]);
			inv.setContents(items);
			p.openInventory(inv);
		}
		if (config.get("Vaults." + String.valueOf(number)) == null) {
			p.openInventory(inv);

		}

	}
}
