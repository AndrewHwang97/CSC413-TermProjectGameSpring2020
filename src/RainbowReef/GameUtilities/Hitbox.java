package RainbowReef.GameUtilities;

import RainbowReef.GameObjects.*;

import java.awt.*;

public class Hitbox {
    int x;
    int y;
    Rectangle hitbox;

    public Hitbox(GameObject obj){
        this.x = obj.getX();
        this.y = obj.getY();
        System.out.println("THIS X: " + x);
        hitbox = new Rectangle(x,y,obj.getSprite().getWidth(null), obj.getSprite().getHeight(null));
    }

    public Hitbox(GameObject obj,int width,int xOffset){
        this.x = obj.getX();
        this.y = obj.getY();
        System.out.println("THIS X: " + x);
        hitbox = new Rectangle(x + xOffset,y,width, obj.getSprite().getHeight(null));
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public Rectangle getHitbox() { return hitbox; }

    public void update(GameObject obj){
        this.x = obj.getX();
        this.y = obj.getY();
        this.hitbox.x = obj.getX();
        this.hitbox.y = obj.getY();

    }
    public void update(GameObject obj, int xOffset){
        this.x = obj.getX() + xOffset;
        this.y = obj.getY();
        this.hitbox.x = obj.getX() + xOffset;
        this.hitbox.y = obj.getY();

    }

    public void disableHitbox(){
        this.hitbox.width = 0;
        this.hitbox.height = 0;
        this.hitbox.x = -10;
        this.hitbox.y = -10;
    }

    public void draw(Graphics2D g){
        g.setColor(Color.blue);
        g.drawRect(this.hitbox.x,this.hitbox.y,hitbox.width,hitbox.height);
    }
}
