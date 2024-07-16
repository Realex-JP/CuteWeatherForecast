package CuteWeatherForecast;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;

public final class Config {
    final static int updateTime = 3600000; //更新する頻度（１時間）

    final static Color themeColor = new Color(0xC8C4FF);

    final static Dimension dim = new Dimension(600, 600);

    final static Font setButtonFont = new Font("Bauhaus 93", Font.PLAIN, 20);

    final static ImageIcon sunny = new ImageIcon();
    final static ImageIcon cloudy = new ImageIcon();
    final static ImageIcon rainy = new ImageIcon();
    final static ImageIcon snowy = new ImageIcon();
}
