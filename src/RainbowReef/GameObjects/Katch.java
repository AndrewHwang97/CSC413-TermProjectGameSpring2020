package RainbowReef.GameObjects;

import RainbowReef.GameManager;
import RainbowReef.GameUtilities.*;

import java.awt.*;

public class Katch extends GameObject{

    private boolean rightPressed;
    private boolean leftPressed;
    private boolean launchPressed;
    private int moveSpeed = 4;
    Hitbox hbLeft;
    Hitbox hbMid;
    Hitbox hbRight;
    GameManager gameManager;
    public Katch(Image sprite, int x, int y, GameManager gameManager){
        this.gameManager = gameManager;
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        hbLeft = new Hitbox(this, this.sprite.getWidth(null)/3,0);
        hbMid = new Hitbox(this, this.sprite.getWidth(null)/3,this.sprite.getWidth(null)/3);
        hbRight = new Hitbox(this, this.sprite.getWidth(null)/3,this.sprite.getWidth(null)/3 * 2);

    }

    void toggleLeftPressed(){this.leftPressed = true;}
    void toggleRightPressed(){this.rightPressed = true;}
    void toggleLaunchPressed(){this.launchPressed = true;}
    void unToggleLaunchPressed(){this.launchPressed = false;}
    void unToggleLeftPressed(){this.leftPressed = false;}
    void unToggleRightPressed(){this.rightPressed = false;}
    public int getX(){return this.x;}
    public int getY(){return this.y;}

    public Hitbox getHitbox() {
        return hbLeft;
    }
    public Hitbox getHitbox(String hb) {
        Hitbox retVal = null;
        switch (hb){
            case "left":
                retVal = hbLeft;
                break;
            case "mid" :
                retVal = hbMid;
                break;
            case "right" :
                retVal =  hbRight;
                break;
        }
        return retVal;
    }

    public void update(){
        if(this.leftPressed){
            moveLeft();
        }
        if(this.rightPressed){
            moveRight();
        }
        if(this.launchPressed){
            gameManager.getPop().launch();
        }
        hbLeft.update(this, 0);
        hbMid.update(this, this.sprite.getWidth(null)/3);
        hbRight.update(this, this.sprite.getWidth(null)/3 * 2);
        checkBounds();

    }

    void moveLeft(){
        x -= moveSpeed;
    }

    void moveRight() {
        x += moveSpeed;
    }

    void checkBounds(){
        if(this.x < 20)
            this.x = 20;
        if(this.x > GameManager.SCREEN_WIDTH-80)
            this.x = GameManager.SCREEN_WIDTH-80;
    }
    public void draw(Graphics2D g) {
        g.drawImage(sprite,x,y,null);

    }
}
