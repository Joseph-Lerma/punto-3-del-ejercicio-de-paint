import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Linea {
    Point inicio;
    Point fin;
    Color color;

    public Linea(Point inicio, Point fin, Color color) {
        this.inicio = inicio;
        this.fin = fin;
        this.color = color;
    }
}

public class App extends JPanel {

    private List<Linea> lineas;

    public App() {
        lineas = new ArrayList<>();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Color color = generarColorAleatorio();
                Point inicio = e.getPoint();
                lineas.add(new Linea(inicio, inicio, color));
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                lineas.get(lineas.size() - 1).fin = e.getPoint();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Linea linea : lineas) {
            g.setColor(linea.color);
            g.drawLine(linea.inicio.x, linea.inicio.y, linea.fin.x, linea.fin.y);
        }
    }

    private Color generarColorAleatorio() {
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return new Color(r, g, b);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dibujar Lineas con colores aleatorios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().add(new App());
        frame.setVisible(true);
    }
}