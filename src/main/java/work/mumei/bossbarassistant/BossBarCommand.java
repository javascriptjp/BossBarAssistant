package work.mumei.bossbarassistant;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class BossBarCommand {
    public void sendHelp(CommandSender sender) {
        List<String> messages = BossBarAssistant.config.getStringList("HELP");
        for (String message : messages) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
