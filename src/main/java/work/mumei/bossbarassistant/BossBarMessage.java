package work.mumei.bossbarassistant;

public class BossBarMessage {
    public String message = "";
    public Integer time = 1;
    public String color = "BLUE";

    public BossBarMessage(String message, Integer time, String color) {
        this.message = message;
        this.time = time;
        this.color = color;
    }
}
