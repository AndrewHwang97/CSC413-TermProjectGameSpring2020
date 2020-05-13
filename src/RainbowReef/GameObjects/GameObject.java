package RainbowReef.GameObjects;

import RainbowReef.GameUtilities.*;

import java.awt.*;

public abstract class GameObject {

    int x;
    int y;
    int width;
    int height;
    Image sprite;
    Hitbox hitbox;
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public Hitbox getHitbox() {return this.hitbox;}
    public Image getSprite(){return this.sprite;}
    public abstract void draw(Graphics2D g);
}
