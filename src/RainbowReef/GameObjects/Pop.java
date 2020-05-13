package RainbowReef.GameObjects;

import RainbowReef.Blocks.Block;
import RainbowReef.GameManager;
import RainbowReef.GameUtilities.Hitbox;

import java.awt.*;

import static javax.imageio.ImageIO.read;

public class Pop extends GameObject {
    int xSpeed = 0;
    int ySpeed = -2;
    Image popImage;
    boolean stop;
    GameManager gameManager;

    public Pop(int x, int y, GameManager gameManager){
        try{
            popImage = read(Block.class.getClassLoader().getResource("pop1.png"));
        }catch (Exception e){
            System.out.println("ERR in Pop class: " + e);
        }
        this.x = x;
        this.y = y;
        this.sprite = popImage;
        this.hitbox = new Hitbox(this, this.sprite.getWidth(null)-25, this.sprite.getWidth(null)/3 + 5);
        this.hitbox.getHitbox().height /= 3;
        this.hitbox.getHitbox().x /=2;
        //this.hitbox.getHitbox().width -= 25;
        this.gameManager = gameManager;
        this.stop = true;
    }

    public  int getX(){
        return this.x;
    }

    public  int getY(){return this.y;}
    public void setY(int value){this.y = value; }
    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void update(){
        if(this.stop){
            this.x = gameManager.getKatchXPosition();
            this.y = this.y;
        }
        else{
            this.x += xSpeed;
            this.y += ySpeed;
            checkBounds();
            this.hitbox.update(this, this.sprite.getWidth(null)/3,this.sprite.getHeight(null)/3 + 5);
            checkPopBoundary();
        }

    }

    public void changeSpeeds(int xSpeed, int ySpeed){
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    void checkBounds(){
        if(this.x < 30 || this.x > GameManager.SCREEN_WIDTH -85){
            changeSpeeds(xSpeed*-1,ySpeed);
        }
        if(this.y < 40 ){
            changeSpeeds(xSpeed,ySpeed*-1);
        }

    }

    public void checkPopBoundary(){
        if(this.y > GameManager.SCREEN_HEIGHT - 20){
            this.gameManager.deductStar();
            this.respawn();
        }
    }
    public void launch(){
        this.stop = false;
    }
    public void respawn(){
        this.x = GameManager.SCREEN_WIDTH/2;
        this.y = GameManager.SCREEN_HEIGHT - 130;
        this.stop = true;
        this.xSpeed = 0;
        this.ySpeed = -2;
    }
    public void draw(Graphics2D g) {
        g.drawImage(sprite,x,y,null);
        g.setColor(Color.green);
        g.drawRect(x,y,this.getSprite().getWidth(null),this.getSprite().getHeight(null));
    }
}
