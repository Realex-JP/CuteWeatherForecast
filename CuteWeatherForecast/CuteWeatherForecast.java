package CuteWeatherForecast;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CuteWeatherForecast extends JPanel implements Runnable{

    Thread fThread;

    public CuteWeatherForecast() {

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
                Thread.sleep(3600000);
                updateWeatherInfo();
                repaint();
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void getWeatherInfo() {

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