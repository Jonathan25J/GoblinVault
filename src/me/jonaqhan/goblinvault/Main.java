package me.jonaqhan.goblinvault;

import org.bukkit.plugin.java.JavaPlugin;

import me.jonaqhan.goblinvault.events.CloseVault;
import me.jonaqhan.goblinvault.events.OpenVault;
import me.jonaqhan.goblinvault.vault.Open;

public class Main extends JavaPlugin {

	public void onEnable() {
		new OpenVault(this);
		new Open(this);
		new CloseVault(this);

	}

	public void onDisable() {

	}

}
