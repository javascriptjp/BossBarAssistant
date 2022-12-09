package work.mumei.bossbarassistant;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class BossBarChanger extends BukkitRunnable {
    public static Integer timer = 0;
    public static BossBarMessage bossBarMessage = null;
    public static Integer showing = 0;

    @Override
    public void run() {
        if (timer < 0) {
            bossBarMessage = BossBarAssistant.bossBarMessages.get(showing);
            timer = bossBarMessage.time;
            BossBarAssistant.Bossbar.setTitle(ChatColor.translateAlternateColorCodes('&', bossBarMessage.message));
            BossBarAssistant.Bossbar.setProgress(1);
            BossBarAssistant.Bossbar.setColor(BossBarAssistant.Colors.get(bossBarMessage.color));
            if (BossBarAssistant.bossBarMessages.size() == showing + 1) {
                showing = 0;
            } else {
                showing = showing + 1;
            }
        } else {
            if (bossBarMessage == null) bossBarMessage = BossBarAssistant.bossBarMessages.get(showing);
            double time = (double) timer / bossBarMessage.time;
            if (time > 1) time = 1.0;
            if (time < 0) time = 0.0;
            BossBarAssistant.Bossbar.setProgress(time);
            timer = timer - 1;
        }
    }
}
