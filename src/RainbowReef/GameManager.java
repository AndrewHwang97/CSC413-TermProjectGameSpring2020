package RainbowReef;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static javax.imageio.ImageIO.read;

import RainbowReef.Blocks.*;
import RainbowReef.GameObjects.*;
import RainbowReef.GameUtilities.*;


public class GameManager extends JPanel{
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 960;
    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jFrame;
    private Katch katch;
    private Pop pop;
    private Block testerBlock;
    private CollisonDetector collisonDetector;
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.init();
        try {

            while (true) {
                gameManager.katch.update();
                gameManager.pop.update();
                gameManager.collisonDetector.checkCollisions(gameManager.pop,gameManager.testerBlock);
                gameManager.repaint();
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }

    }


    private void init() {
        this.jFrame = new JFrame("RainbowReef");
        this.world = new BufferedImage(GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage katchImage = null;
        try {
            katchImage = read(GameManager.class.getClassLoader().getResource("Katch.gif"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        katch = new Katch(katchImage, GameManager.SCREEN_WIDTH/2,GameManager.SCREEN_HEIGHT - 100);
        pop = new Pop(GameManager.SCREEN_WIDTH/2,GameManager.SCREEN_HEIGHT-90);
        testerBlock = new Block_Breakable(GameManager.SCREEN_WIDTH/2,GameManager.SCREEN_HEIGHT-200,"green");

        collisonDetector = new CollisonDetector();
        KatchControls katchControl = new KatchControls(katch,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT);

        this.jFrame.setLayout(new BorderLayout());
        this.jFrame.add(this);
        this.jFrame.addKeyListener(katchControl);
        this.jFrame.setSize(GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT + 30);
        this.jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        buffer = world.createGraphics();
        buffer.setColor(Color.black);
        buffer.fillRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        this.katch.draw(buffer);
        this.pop.draw(buffer);
        this.katch.getHitbox().draw(buffer);
        this.testerBlock.draw(buffer);

        g2.drawImage(world,0,0,null);

    }
}
