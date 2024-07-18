package com.example;

import java.awt.*;
import javax.imageio.*;
import javax.swing.*;

public final class Config {
    final static int updateTime = 60 * 60 * 1000; // 更新する頻度（1時間）

    final static Color themeColor = new Color(0xC8C4FF);

    final static Dimension dim = new Dimension(600, 400);

    final static Font mainFont = new Font("Agency FB", Font.PLAIN, 70);
    final static Font subFont = new Font("Bauhaus 93", Font.PLAIN, 20);
    final static Font locationFont = new Font("Bauhaus 93", Font.PLAIN, 40);

    final static ImageIcon sunny = new ImageIcon(Config.class.getResource("/sunny_2570483.png"));
    final static ImageIcon cloudy = new ImageIcon(Config.class.getResource("/cloud_2570486.png"));
    final static ImageIcon rainy = new ImageIcon(Config.class.getResource("/rain_2570494.png"));
    final static ImageIcon snowy = new ImageIcon(Config.class.getResource("/snow_2570507.png"));

    public static Image sunnyBg;
    public static Image cloudyBg;
    public static Image rainyBg;
    public static Image snowyBg;

    static {
        try {
            sunnyBg = ImageIO.read(Config.class.getResource("/sunnyBg.jpg"));
            cloudyBg = ImageIO.read(Config.class.getResource("/cloudyBg.jpg"));
            rainyBg = ImageIO.read(Config.class.getResource("/rainyBg.jpg"));
            snowyBg = ImageIO.read(Config.class.getResource("/snowyBg.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
