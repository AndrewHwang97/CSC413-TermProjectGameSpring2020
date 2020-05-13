package RainbowReef;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PrimitiveIterator;

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

    private ArrayList<Block> blocks;
    private ArrayList<Bigleg> biglegs;
    private CollisonDetector collisonDetector;
    private LevelManager levelManager;
    private ScoreManager scoreManager;
    private SoundManager soundManager;
    private int score;
    private int numStarsLeft;
    private int numBiglegsLeft;
    private int numLevels;
    private boolean levelEnd;
    private boolean respawned = false;


    private int currLevel;
    private String lvlName;
    Image backgroundImage;
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.init();
        try {

            while (true) {
                gameManager.katch.update();
                gameManager.pop.update();
                gameManager.score = gameManager.scoreManager.getPlayerScore();


                gameManager.collisonDetector.checkCollisions(gameManager.katch,gameManager.pop);
                for(int i = 0; i < gameManager.biglegs.size(); i++){
                    gameManager.biglegs.get(i).update();
                    gameManager.collisonDetector.checkCollisions(gameManager.pop,gameManager.biglegs.get(i));
                }
                for(int i = 0; i < gameManager.blocks.size(); i++){
                    gameManager.collisonDetector.checkCollisions(gameManager.pop,gameManager.blocks.get(i));
                    gameManager.blocks.get(i).update();
                }

                if(gameManager.numStarsLeft < 0){
                    System.exit(1);
                }

                if(gameManager.numBiglegsLeft < 1){
                    if(gameManager.currLevel == 3){
                        System.exit(1);
                    }
                    else {
                        gameManager.levelEnd = true;
                        gameManager.repaint();
                        gameManager.currLevel++;
                        gameManager.lvlName = "map_0"+gameManager.currLevel;
                        gameManager.loadLevelResources(gameManager.lvlName);
                    }

                }

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
        respawned = true;


        scoreManager = new ScoreManager();
        soundManager = new SoundManager();
        soundManager.playSound("sounds/Music.wav");
        levelManager = new LevelManager(this);

        numLevels = levelManager.getNumLevels();
        currLevel = 1;
        lvlName = "map_0"+currLevel;
        loadLevelResources(lvlName);

        try {
            katchImage = read(GameManager.class.getClassLoader().getResource("Katch.gif"));
            backgroundImage = read(GameManager.class.getClassLoader().getResource("Background1.bmp"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        katch = new Katch(katchImage, GameManager.SCREEN_WIDTH/2,GameManager.SCREEN_HEIGHT - 100,this);
        pop = new Pop(GameManager.SCREEN_WIDTH/2,GameManager.SCREEN_HEIGHT - 130,this);
        collisonDetector = new CollisonDetector(this);
        KatchControls katchControl = new KatchControls(katch,
                KeyEvent.VK_LEFT,
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_SPACE);

        this.jFrame.setLayout(new BorderLayout());
        this.jFrame.add(this);
        this.jFrame.addKeyListener(katchControl);
        this.jFrame.setSize(GameManager.SCREEN_WIDTH, GameManager.SCREEN_HEIGHT + 30);
        this.jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setVisible(true);
    }

    public void setRespawned(Boolean status){respawned = status;}
    public int getKatchXPosition(){return katch.getX();}

    public void deductStar(){
        this.numStarsLeft--;
        System.out.println("num stars: " + numStarsLeft);
    }

    public void addLife(){
        numStarsLeft++;
    }
    public void deductBigleg(){
        this.numBiglegsLeft--;
        System.out.println("num legs: " + numBiglegsLeft);}

    void loadLevelResources(String mapName){
        blocks = levelManager.loadLevel(mapName,"blocks");
        biglegs = levelManager.loadLevel(mapName,"biglegs");
        numBiglegsLeft=levelManager.getNumBiglegs();
        this.pop.respawn();
        levelEnd = false;
    }
    public Pop getPop(){return pop;}

    public void sendPoints(int points){
        scoreManager.addtoScore(points);
        System.out.println(scoreManager.getPlayerScore());
    }

    public SoundManager getSoundManager(){return soundManager;}
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
        this.biglegs.forEach(bigleg -> bigleg.draw(buffer));
        this.pop.draw(buffer);
        this.pop.getHitbox().draw(buffer);
        this.katch.getHitbox("left").draw(buffer);
        this.katch.getHitbox("mid").draw(buffer);
        this.katch.getHitbox("right").draw(buffer);


        g2.drawImage(world,0,0,null);

        Font font = new Font("Verdana",Font.BOLD,20);
        g2.setFont(font);
        if(levelEnd){
            g2.setColor(Color.BLACK);
            g2.drawString("Level Completed! Loading next level...", SCREEN_WIDTH/2-180,SCREEN_HEIGHT/2);
        }
        else{
            g2.setColor(Color.WHITE);
        }
        g2.drawString("SCORE: " + this.score ,20 ,20);
        g2.drawString("LIVES: " + this.numStarsLeft ,SCREEN_WIDTH-300 ,20);

    }
}
