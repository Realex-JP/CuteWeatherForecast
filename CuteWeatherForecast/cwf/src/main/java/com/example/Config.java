package com.example;

import java.awt.*;
import javax.imageio.*;
import javax.swing.*;

public final class Config {
    final static int updateTime = 60 * 60 * 1000;
    final static String apiKey = "";

    final static Color themeColor = new Color(0xC8C4FF);

    final static Dimension dim = new Dimension(600, 400);

    final static Font mainFont = new Font("Agency FB", Font.PLAIN, 70);
    final static Font subFont = new Font("Bauhaus 93", Font.PLAIN, 20);
    final static Font locationFont = new Font("Bauhaus 93", Font.PLAIN, 40);

    final static String location1 = "Tokyo";
    final static String location2 = "Paris";
    final static String location3 = "London";

    final static JButton setLocation1 = new JButton("Tokyo");
    final static JButton setLocation2 = new JButton("Paris");
    final static JButton setLocation3 = new JButton("London");
    final static JButton setOther = new JButton("Other");

    static {
        setLocation1.setFont(subFont);
        setLocation1.setBorderPainted(false);
        setLocation1.setContentAreaFilled(false);
        setLocation2.setFont(subFont);
        setLocation2.setBorderPainted(false);
        setLocation2.setContentAreaFilled(false);
        setLocation3.setFont(subFont);
        setLocation3.setBorderPainted(false);
        setLocation3.setContentAreaFilled(false);
        setOther.setFont(subFont);
        setLocation3.setBorderPainted(false);
        setLocation3.setContentAreaFilled(false);
    }


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
