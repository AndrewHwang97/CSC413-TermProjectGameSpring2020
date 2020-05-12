package RainbowReef.GameObjects;

import RainbowReef.Blocks.Block;
import RainbowReef.GameManager;
import RainbowReef.GameUtilities.Hitbox;

import java.awt.*;

import static javax.imageio.ImageIO.read;

public class Bigleg extends GameObject {
    Image biglegImage;
    int hp;
    boolean isDestroyed;
    GameManager gameManager;
    int points;
    public Bigleg(int x, int y, GameManager gameManager){

        try{
            biglegImage =  read(Bigleg.class.getClassLoader().getResource("BiglegSmall.png"));
        }catch (Exception e){
            System.out.println("ERR in Bigleg Class: " + e);
        }
        this.x = x;
        this.y = y;
        this.sprite = biglegImage;
        this.hitbox = new Hitbox(this);
        this.hp = 1;
        this.isDestroyed = false;
        this.gameManager=gameManager;
        this.points = 250;
    }

    public void update(){
        if(!isDestroyed){
            if(this.hp < 1){
                destroy();
                gameManager.sendPoints(this.points);
            }
        }

    }

    public void deductHp(){this.hp--;}
    public void destroy(){
        this.hitbox.disableHitbox();
        this.isDestroyed = true;
        gameManager.deductBigleg();
    }

    public void draw(Graphics2D g) {
        if(!this.isDestroyed){
            g.drawImage(sprite,x,y,null);
        }
    }
}
