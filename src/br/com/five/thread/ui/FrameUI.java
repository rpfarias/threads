package br.com.five.thread.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import br.com.five.thread.model.Car;
import br.com.five.thread.model.Fabrica;
import br.com.five.thread.service.AbastecerCarros;
import br.com.five.thread.service.CaminhaoTamque;
import br.com.five.thread.service.CarroEmMovimento;

public class FrameUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private Panel2 jPanel2;

    Fabrica fabrica = new Fabrica();

    public FrameUI() {
        initComponents();
    }

    private void initComponents() {
        jPanel2 = new Panel2();

        jPanel2.setBackground(new java.awt.Color(110, 120, 30));
        jPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.setContentPane(jPanel2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(1100, 600);
    }

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrameUI frame = new FrameUI();
                frame.setVisible(true);
                frame.iniciarFabrica();
                frame.iniciarMovimentoCarro();
                frame.iniciarAbastecimentoDeCarro();
                frame.iniciarMovimentoCarro();
            }
        });
    }

    public void iniciarFabrica() {
        Thread threadFabrica = new Thread(new CaminhaoTamque(fabrica));
        threadFabrica.start();
    }

    public void iniciarAbastecimentoDeCarro() {
        List<Car> carros = jPanel2.getCarros();
        for (Car car : carros) {
            Thread threadFabricaCarros = new Thread(new AbastecerCarros(fabrica, car));
            threadFabricaCarros.start();
        }
    }

    public void iniciarMovimentoCarro() {
        List<Car> carros = jPanel2.getCarros();
        for (Car car : carros) {
            Thread threadCarroEmMovimento = new Thread(new CarroEmMovimento(car));
            threadCarroEmMovimento.start();
        }
    }

    public class Panel2 extends JPanel {

        private List<Car> carros;

        Panel2() {
            setPreferredSize(new Dimension(700, 600));
            iniciarCarros();
        }

        private Color defineColor() {
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            return new Color(r, g, b);
        }

        private void iniciarCarros() {

            Random rand = new Random();

            // Cria 5 novos carros
            carros = new ArrayList<Car>();
            for (int i = 0; i < 3; i++) {
                Car c = new Car(rand.nextInt(10), rand.nextInt(800), rand.nextInt(600), rand.nextInt(50),
                defineColor());
                c.mover();
                carros.add(c);
            }

            // TODO : Me ajude ! Meus carros não estão desenhando!!!
            // int i = 0;
            // while (i <= carros.size()) {
            // carros.forEach(c -> c.move());
            // Panel2.this.repaint();
            // i++;
            // }
        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            for (Car c : carros) {
                c.draw(g);
            }
        }

        public List<Car> getCarros() {
            return carros;
        }
    }
}