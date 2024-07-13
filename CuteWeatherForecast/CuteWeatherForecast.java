package CuteWeatherForecast;

import javax.swing.*;
import java.awt.*;

public class CuteWeatherForecast extends JPanel implements Runnable {

    Thread fThread;
    private JLabel temperatureLabel;
    private JLabel humidityLabel;
    private JLabel weatherLabel;

    public CuteWeatherForecast() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        temperatureLabel = new JLabel("Temperature: ");
        humidityLabel = new JLabel("Humidity: ");
        weatherLabel = new JLabel("Weather: ");

        add(temperatureLabel, gbc);
        gbc.gridy++;
        add(humidityLabel, gbc);
        gbc.gridy++;
        add(weatherLabel, gbc);

        setPreferredSize(new Dimension(300, 150));
        setBackground(new Color(135, 206, 250)); // Light blue background

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
                Thread.sleep(3600000); // 1 hour
                updateWeatherInfo();
                repaint();
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void getWeatherInfo() {
        // Fetch weather data from a weather API or another source
        // For demonstration, we will use dummy data
        String temperature = "25°C";
        String humidity = "60%";
        String weather = "Sunny";

        temperatureLabel.setText("Temperature: " + temperature);
        humidityLabel.setText("Humidity: " + humidity);
        weatherLabel.setText("Weather: " + weather);
    }

    private void updateWeatherInfo() {
        // Update the weather information on the labels
        // Here, it is redundant because getWeatherInfo already updates the labels
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // You can add custom painting code here if needed
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
