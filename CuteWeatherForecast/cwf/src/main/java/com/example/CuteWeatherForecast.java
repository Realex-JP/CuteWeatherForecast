package com.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.*;
import org.json.JSONObject;

import static com.example.Config.*;

public class CuteWeatherForecast extends JPanel implements Runnable {

    Thread fThread;
    private JLabel temperatureLabel;
    private JLabel humidityLabel;
    private JLabel weatherLabel;

    private JButton setTokyo;
    private JButton setParis;
    private JButton setLondon;

    private Image bgImg;

    public CuteWeatherForecast() {
        setLayout(new BorderLayout());

        JPanel weatherInfoPanel = new JPanel(new GridLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImg != null) {
                    g.drawImage(bgImg, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        temperatureLabel = new JLabel();
        temperatureLabel.setHorizontalAlignment(JLabel.CENTER);
        temperatureLabel.setFont(mainFont);
        weatherLabel = new JLabel();
        weatherLabel.setHorizontalAlignment(JLabel.CENTER);
        humidityLabel = new JLabel();
        humidityLabel.setHorizontalAlignment(JLabel.CENTER);
        humidityLabel.setFont(mainFont);

        weatherInfoPanel.add(temperatureLabel);
        weatherInfoPanel.add(weatherLabel);
        weatherInfoPanel.add(humidityLabel);

        weatherInfoPanel.setBackground(themeColor);
        add(weatherInfoPanel, BorderLayout.CENTER);

        setTokyo = new JButton("Tokyo");
        setTokyo.setFont(subFont);
        setTokyo.setBorderPainted(false);
        setTokyo.setContentAreaFilled(false);

        setParis = new JButton("Paris");
        setParis.setFont(subFont);
        setParis.setBorderPainted(false);
        setParis.setContentAreaFilled(false);

        setLondon = new JButton("London");
        setLondon.setFont(subFont);
        setLondon.setBorderPainted(false);
        setLondon.setContentAreaFilled(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 5));
        buttonPanel.setBackground(themeColor);

        buttonPanel.add(setTokyo);
        buttonPanel.add(setParis);
        buttonPanel.add(setLondon);

        add(buttonPanel, BorderLayout.NORTH);

        setPreferredSize(dim);

        setTokyo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWeatherInfo("Tokyo");
            }
        });

        setParis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWeatherInfo("Paris");
            }
        });

        setLondon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateWeatherInfo("London");
            }
        });
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
            updateWeatherInfo("Tokyo");
            try {
                Thread.sleep(updateTime);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void updateWeatherInfo(String city) {
        try {
            String apiKey = "";
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q="+ city + "&units=metric&appid=" + apiKey;

            URL url = new URL(apiUrl);
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            connect.setRequestMethod("GET");

            int responseCode = connect.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                JSONObject jsonResponse = new JSONObject(content.toString());
                String temperature = jsonResponse.getJSONObject("main").getInt("temp") + "°C";
                String humidity = jsonResponse.getJSONObject("main").getInt("humidity") + "%";
                String weather = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("main");

                temperatureLabel.setText(temperature);
                humidityLabel.setText(humidity);

                ImageIcon icon = null;
                switch (weather) {
                    case "Clear":
                        icon = sunny;
                        bgImg = sunnyBg;
                        break;
                    case "Clouds":
                        icon = cloudy;
                        bgImg = cloudyBg;
                        break;
                    case "Rain":
                        icon = rainy;
                        bgImg = rainyBg;
                        break;
                    case "Snow":
                        icon = snowy;
                        bgImg = snowyBg;
                        break;
                }
                if (icon != null) {
                    weatherLabel.setIcon(icon);
                } else {
                    System.out.println("No icon found for weather: " + weather);
                }

                repaint();
            
            } else {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImg != null) {
            g.drawImage(bgImg, 0, 0, getWidth(), getHeight(), this);
        }
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
