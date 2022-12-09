// BossBarAssistant v1.0
// Copyright (c) 2022 Soso#2822
// Released under the MIT license
package work.mumei.bossbarassistant;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public final class BossBarAssistant extends JavaPlugin implements Listener {
    public static Plugin getPlugin() {
        return plugin;
    }

    public static List<BossBarMessage> bossBarMessages = new ArrayList<>();
    public static BossBarCommand Command = new BossBarCommand();
    public static BossBarColor Colors = new BossBarColor();
    public static BossBar Bossbar;
    public static FileConfiguration config;
    public static Plugin plugin;
    public static Logger logger;
    public static BukkitTask BossBarEvent;
    public static File ConfigFile;

    @Override
    public void onEnable() {
        plugin = this;
        logger = getLogger();
        ConfigFile = new File(plugin.getDataFolder(), "config.yml");
        if (!ConfigFile.exists()) plugin.saveResource("config.yml", false);
        config = getConfig();
        getServer().getPluginManager().registerEvents(this, this);
        for (String key : config.getConfigurationSection("Messages").getKeys(false)) {
            String message = config.getString("Messages." + key + ".message");
            Integer time = config.getInt("Messages." + key + ".time", 200);
            String color = config.getString("Messages." + key + ".color", "BLUE");
            BossBarMessage bossBarMessage = new BossBarMessage(message, time, color);
            bossBarMessages.add(bossBarMessage);
        }
        BossBarAssistant.Bossbar = Bukkit.createBossBar("loading...", BarColor.BLUE, BarStyle.SOLID);
        BossBarAssistant.Bossbar.setVisible(true);
        BossBarEvent = new BossBarChanger().runTaskTimer(BossBarAssistant.getPlugin(), 20, 0);
        logger.info(config.getString("ENABLE", "Load BossBarAssistant!"));
    }

    @Override
    public void onDisable() {
        bossBarMessages.clear();
        logger.info(config.getString("DISABLE", "Disable BossBarAssistant"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("BossBarAssistant")) {
            Commands(sender, args);
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        BossBarAssistant.Bossbar.addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        BossBarAssistant.Bossbar.removePlayer(event.getPlayer());
    }

    public void Commands(CommandSender sender, String[] args) {
        if (args.length == 0) {
            Command.sendHelp(sender);
        } else {
            if (Objects.equals(args[0], "reload")) {
                ReloadPlugin(sender);
            } else {
                Command.sendHelp(sender);
            }
        }
    }

    public void ReloadPlugin(CommandSender sender) {
        reloadConfig();
        config = getConfig();
        int i = 0;
        for (String key : config.getConfigurationSection("Messages").getKeys(false)) {
            String message = config.getString("Messages." + key + ".message");
            Integer time = config.getInt("Messages." + key + ".time", 40);
            String color = config.getString("Messages." + key + ".color", "BLUE");
            BossBarMessage bossBarMessage = new BossBarMessage(message, time, color);
            bossBarMessages.set(i, bossBarMessage);
            i++;
        }
        String message = ChatColor.translateAlternateColorCodes('&', config.getString("RELOAD", "Reloaded BossBarAssistant!"));
        sender.sendMessage(message);
    }
}
