package RainbowReef;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
    private ArrayList<Block> blocks;
    private CollisonDetector collisonDetector;
    private int numStarsLeft;
    Image backgroundImage;
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.init();
        try {

            while (true) {
                gameManager.katch.update();
                gameManager.pop.update();
                gameManager.testerBlock.update();
                gameManager.collisonDetector.checkCollisions(gameManager.pop,gameManager.testerBlock);
                gameManager.collisonDetector.checkCollisions(gameManager.katch,gameManager.pop);
                for(int i = 0; i < gameManager.blocks.size(); i++){

                    gameManager.collisonDetector.checkCollisions(gameManager.pop,gameManager.blocks.get(i));
                    gameManager.blocks.get(i).update();
                }
                //gameManager.checkPopBoundary();
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
        backgroundImage = null;
        numStarsLeft = 3;
        blocks = new ArrayList<Block>();
        try {
            katchImage = read(GameManager.class.getClassLoader().getResource("Katch.gif"));
            backgroundImage = read(GameManager.class.getClassLoader().getResource("Background1.bmp"));

            InputStreamReader isr = new InputStreamReader(GameManager.class.getClassLoader().getResourceAsStream("maps/map_01"));
            BufferedReader mapReader = new BufferedReader(isr);

            String row = mapReader.readLine();
            if(row == null){
                throw new IOException("no data for map file");
            }
            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for(int currRow = 0; currRow < numRows; currRow++){
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for(int currCol = 0; currCol < numCols; currCol++){
                    switch (mapInfo[currCol]){
                        case "1":
                            Block_Breakable block = new Block_Breakable(currCol*30,currRow*30,"violet");
                            this.blocks.add(block);
                            break;
                        case "2":
                            Block_Breakable blockyellow = new Block_Breakable(currCol*30,currRow*30,"red");
                            this.blocks.add(blockyellow);
                            break;
                        case "8":
                            Block_Unbreakable blockUnbreak = new Block_Unbreakable(currCol*30,currRow*30);
                            this.blocks.add(blockUnbreak);
                            break;
                        case "9":
                            Block_Wall wall = new Block_Wall(currCol*30,currRow*30);
                            this.blocks.add(wall);
                            break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        katch = new Katch(katchImage, GameManager.SCREEN_WIDTH/2,GameManager.SCREEN_HEIGHT - 100);
        pop = new Pop(GameManager.SCREEN_WIDTH/2,150,this);
        testerBlock = new Block_Breakable(GameManager.SCREEN_WIDTH/2 - 100,GameManager.SCREEN_HEIGHT-200,"green");

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


    public void deductStar(){
        this.numStarsLeft--;
        System.out.println("num stars: " + numStarsLeft);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);
        buffer = world.createGraphics();
        buffer.setColor(Color.black);
        buffer.fillRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        buffer.drawImage(backgroundImage,0,0,null);
        this.katch.draw(buffer);
        this.blocks.forEach(block-> block.getHitbox().draw(buffer));
        this.blocks.forEach(block-> block.draw(buffer));
        this.pop.draw(buffer);
        this.pop.getHitbox().draw(buffer);
        this.katch.getHitbox("left").draw(buffer);
        this.katch.getHitbox("mid").draw(buffer);
        this.katch.getHitbox("right").draw(buffer);

        this.testerBlock.draw(buffer);
        this.testerBlock.getHitbox().draw(buffer);

        g2.drawImage(world,0,0,null);

    }
}
