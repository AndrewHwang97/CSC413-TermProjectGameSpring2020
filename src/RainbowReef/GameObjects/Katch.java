package RainbowReef.GameObjects;

import RainbowReef.GameUtilities.*;

import java.awt.*;

public class Katch extends GameObject{

    private boolean rightPressed;
    private boolean leftPressed;
    private int moveSpeed = 5;

    public Katch(Image sprite, int x, int y){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        hitbox = new Hitbox(this);
    }

    void toggleLeftPressed(){this.leftPressed = true;}
    void toggleRightPressed(){this.rightPressed = true;}
    void unToggleLeftPressed(){this.leftPressed = false;}
    void unToggleRightPressed(){this.rightPressed = false;}
    public int getX(){return this.x;}
    public int getY(){return this.y;}

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void update(){
        if(this.leftPressed){
            moveLeft();
        }
        if(this.rightPressed){
            moveRight();
        }
        hitbox.update(this);

    }

    void moveLeft(){
        x -= moveSpeed;
    }

    void moveRight() {
        x += moveSpeed;
    }

    public void draw(Graphics2D g) {
        g.drawImage(sprite,x,y,null);
        g.setColor(Color.green);
        g.drawRect(x,y,this.getSprite().getWidth(null),this.getSprite().getHeight(null));
    }
}
