package RainbowReef.GameObjects;

import RainbowReef.Blocks.Block;
import RainbowReef.GameManager;
import RainbowReef.GameUtilities.Hitbox;

import java.awt.*;

import static javax.imageio.ImageIO.read;

public class Pop extends GameObject {
    int xSpeed = 0;
    int ySpeed = 1;
    Image popImage;
    GameManager gameManager;

    public Pop(int x, int y, GameManager gameManager){
        try{
            popImage = read(Block.class.getClassLoader().getResource("Pop.gif"));
        }catch (Exception e){
            System.out.println("ERR in Pop class: " + e);
        }
        this.x = x;
        this.y = y;
        this.sprite = popImage;
        this.hitbox = new Hitbox(this);
        this.hitbox.getHitbox().width -= 25;
        this.gameManager = gameManager;
    }

    public  int getX(){
        return this.x;
    }

    public  int getY(){return this.y;}
    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void update(){
        this.x += xSpeed;
        this.y += ySpeed;
        checkBounds();
        this.hitbox.update(this);
        checkPopBoundary();
    }

    public void changeSpeeds(int xSpeed, int ySpeed){
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    void checkBounds(){
        if(this.x < 30 || this.x > GameManager.SCREEN_WIDTH -80){
            changeSpeeds(xSpeed*-1,ySpeed);
        }
        if(this.y < 30 ){
            changeSpeeds(xSpeed,ySpeed*-1);
        }

    }

    public void checkPopBoundary(){
        if(this.y > GameManager.SCREEN_HEIGHT - 20){
            this.gameManager.deductStar();
            this.respawn();
        }
    }
    public void respawn(){
        this.x = GameManager.SCREEN_WIDTH/2;
        this.y = 90;
        this.xSpeed = 0;
        this.ySpeed = 2;
    }
    public void draw(Graphics2D g) {
        g.drawImage(sprite,x,y,null);
        g.setColor(Color.green);
        g.drawRect(x,y,this.getSprite().getWidth(null),this.getSprite().getHeight(null));
    }
}
