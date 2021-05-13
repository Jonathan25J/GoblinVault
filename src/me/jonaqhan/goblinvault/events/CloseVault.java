package me.jonaqhan.goblinvault.events;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import me.jonaqhan.goblinvault.Main;
import me.jonaqhan.goblinvault.utils.Chat;

public class CloseVault implements Listener {
	public Main plugin;

	public CloseVault(Main plugin) {
		this.plugin = plugin;

		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void closeInv(InventoryCloseEvent e) throws IOException {

		if (!e.getView().getTitle().contains(Chat.color("&aGoblin Vault")))
			return;

		String title = e.getView().getTitle().replace(Chat.color("&b"), "");

		int number = Integer.valueOf(title.substring(title.lastIndexOf(" ") + 1));

		Player p = (Player) e.getPlayer();

		File[] files = new File(plugin.getDataFolder() + File.separator + "Vaults").listFiles();

		for (File file : files) {
			if (file.getName().replace(".yml", "").equals(p.getUniqueId().toString())) {
				FileConfiguration config = YamlConfiguration.loadConfiguration(file);

				config.createSection("Vaults." + String.valueOf(number));
				config.set("Vaults." + String.valueOf(number), e.getInventory().getContents());
				config.save(file);

			}

		}

	}

}
