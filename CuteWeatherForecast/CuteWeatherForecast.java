package CuteWeatherForecast;

import java.awt.*;
import javax.swing.*;

import static CuteWeatherForecast.Config.*;

public class CuteWeatherForecast extends JPanel implements Runnable {

    Thread fThread;
    private JLabel temperatureLabel;
    private JLabel humidityLabel;
    private JLabel weatherLabel;

    private JButton setTokyo;
    private JButton setParis;
    private JButton setNewYork;

    public CuteWeatherForecast() {
        setLayout(new BorderLayout());

        JPanel weatherInfoPanel = new JPanel(new GridLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        temperatureLabel = new JLabel("Temperature: ");
        humidityLabel = new JLabel("Humidity: ");
        weatherLabel = new JLabel("Weather: ");

        weatherInfoPanel.add(temperatureLabel, gbc);
        gbc.gridy++;
        weatherInfoPanel.add(humidityLabel, gbc);
        gbc.gridy++;
        weatherInfoPanel.add(weatherLabel, gbc);
        gbc.gridy++;

        add(weatherInfoPanel, BorderLayout.CENTER);

        setTokyo = new JButton("Tokyo");
        setTokyo.setFont(Config.setButtonFont);
        setParis = new JButton("Paris");
        setParis.setFont(Config.setButtonFont);
        setNewYork = new JButton("NewYork");
        setNewYork.setFont(Config.setButtonFont);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(themeColor);

        buttonPanel.add(setTokyo);
        buttonPanel.add(setParis);
        buttonPanel.add(setNewYork);

        add(buttonPanel, BorderLayout.NORTH);

        setPreferredSize(dim);
        setBackground(themeColor);

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
                Thread.sleep(updateTime);
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
