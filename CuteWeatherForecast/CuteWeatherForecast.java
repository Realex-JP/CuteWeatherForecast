package CuteWeatherForecast;

import java.awt.*;
import javax.swing.*;

public class CuteWeatherForecast extends JPanel implements Runnable {

    Thread fThread;
    private JLabel temperatureLabel;
    private JLabel humidityLabel;
    private JLabel weatherLabel;

    private JButton setTokyo;
    private JButton setParis;
    private JButton setNewYork;

    public CuteWeatherForecast() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        temperatureLabel = new JLabel("Temperature: ");
        humidityLabel = new JLabel("Humidity: ");
        weatherLabel = new JLabel("Weather: ");

        setTokyo = new JButton("Tokyo");
        setTokyo.setFont(Config.setButtonFont);
        setParis = new JButton("Paris");
        setParis.setFont(Config.setButtonFont);
        setNewYork = new JButton("NewYork");
        setNewYork.setFont(Config.setButtonFont);

        add(temperatureLabel, gbc);
        gbc.gridy++;
        add(humidityLabel, gbc);
        gbc.gridy++;
        add(weatherLabel, gbc);
        gbc.gridy++;

        gbc.gridy++;
        gbc.gridwidth = 3;
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Config.themeColor);
        buttonPanel.add(setTokyo);
        buttonPanel.add(setParis);
        buttonPanel.add(setNewYork);

        add(buttonPanel, gbc);

        setPreferredSize(new Dimension(600, 600));
        setBackground(Config.themeColor);

        startThread();
    }

    private void startThread() {
        if (fThread == null) {
            fThread = new Thread(this);
            fThread.start();
        }
    }

    @Override
    public void run() {
        while (Thread.currentThread() == fThread) {
            getWeatherInfo();
            updateWeatherInfo();

            try {
                Thread.sleep(Config.updateTime);
                updateWeatherInfo();
                repaint();
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void getWeatherInfo() {
        String temperature = "25°C";
        String humidity = "60%";
        String weather = "Sunny";

        temperatureLabel.setText("Temperature: " + temperature);
        humidityLabel.setText("Humidity: " + humidity);
        weatherLabel.setText("Weather: " + weather);
    }

    private void updateWeatherInfo() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("CuteWeatherForecast");
            f.setResizable(false);
            f.add(new CuteWeatherForecast(), BorderLayout.CENTER);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }
}
