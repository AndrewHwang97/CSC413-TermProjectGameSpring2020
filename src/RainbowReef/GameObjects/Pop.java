package RainbowReef.GameObjects;

import RainbowReef.Blocks.Block;
import RainbowReef.GameManager;
import RainbowReef.GameUtilities.Hitbox;

import java.awt.*;

import static javax.imageio.ImageIO.read;

public class Pop extends GameObject {
    int xSpeed = 0;
    int ySpeed = 2;
    Image popImage;
    public Pop(int x, int y){
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
    }

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
    }

    public void changeSpeeds(int xSpeed, int ySpeed){
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    void checkBounds(){
        if(this.x < 30 || this.x > GameManager.SCREEN_WIDTH -80){
            changeSpeeds(xSpeed*-1,ySpeed);
        }
        if(this.y <30 || this.y > GameManager.SCREEN_HEIGHT - 80){
            changeSpeeds(xSpeed,ySpeed*-1);
        }
    }
    public void draw(Graphics2D g) {
        g.drawImage(sprite,x,y,null);
        g.setColor(Color.green);
        g.drawRect(x,y,this.getSprite().getWidth(null),this.getSprite().getHeight(null));
    }
}
