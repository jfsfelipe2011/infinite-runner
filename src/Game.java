import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Game extends Canvas implements Runnable, KeyListener {
    public static int WIDTH = 640;
    public static int HEIGHT = 480;

    public static World world;
    public static Player player;

    public static int pontos = 0;

    public static BufferedImage playerSprite;
    public static BufferedImage floorSprite;

    public Game() throws IOException {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.addKeyListener(this);

        world = new World();
        player = new Player(0, 448 - 32);

        playerSprite = ImageIO.read(this.getClass().getResource("/player.png"));
        floorSprite = ImageIO.read(this.getClass().getResource("/sprite.png"));
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        JFrame frame = new JFrame();
        frame.setTitle("Infine Runner");
        frame.add(game);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        new Thread(game).start();
    }

    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bufferStrategy.getDrawGraphics();
        g.setColor(new Color(0, 148, 255));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        world.render(g);
        player.render(g);

        g.setFont(new Font("arial", Font.BOLD, 18));
        g.setColor(Color.WHITE);
        g.drawString("Pontos: " + (pontos - 2) , 10, 20);

        g.dispose();
        bufferStrategy.show();
    }

    public void tick() {
        world.tick();
        player.tick();
    }

    @Override
    public void run() {
        this.requestFocus();
        double fps = 60.0;
        while (true) {
            tick();
            render();
            try {
                Thread.sleep((int) (1000/fps));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Player.jump = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Player.jump = false;
        }
    }
}
