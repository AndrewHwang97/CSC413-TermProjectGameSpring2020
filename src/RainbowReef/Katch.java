package RainbowReef;

import java.awt.*;

public class Katch extends GameObject{
    Image sprite;
    private boolean rightPressed;
    private boolean leftPressed;
    private int moveSpeed = 5;

    public Katch(Image sprite, int x, int y){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }

    void toggleLeftPressed(){this.leftPressed = true;}
    void toggleRightPressed(){this.rightPressed = true;}
    void unToggleLeftPressed(){this.leftPressed = false;}
    void unToggleRightPressed(){this.rightPressed = false;}

    void update(){
        if(this.leftPressed){
            moveLeft();
        }
        if(this.rightPressed){
            moveRight();
        }
    }

    void moveLeft(){
        x -= moveSpeed;
    }

    void moveRight() {
        x += moveSpeed;
    }

    public void draw(Graphics2D g) {
        g.drawImage(sprite,x,y,null);
    }
}
