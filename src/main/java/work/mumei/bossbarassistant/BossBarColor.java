package work.mumei.bossbarassistant;

import org.bukkit.boss.BarColor;

import java.util.Objects;

public class BossBarColor {
    public BarColor get(String color) {
        if (Objects.equals(color, "BLUE")) {
            return BarColor.BLUE;
        } else if (Objects.equals(color, "GREEN")) {
            return BarColor.GREEN;
        } else if (Objects.equals(color, "PINK")) {
            return BarColor.PINK;
        } else if (Objects.equals(color, "PURPLE")) {
            return BarColor.PURPLE;
        } else if (Objects.equals(color, "RED")) {
            return BarColor.RED;
        } else if (Objects.equals(color, "WHITE")) {
            return BarColor.WHITE;
        } else if (Objects.equals(color, "YELLOW")) {
            return BarColor.YELLOW;
        } else {
            return BarColor.BLUE;
        }
    }
}
