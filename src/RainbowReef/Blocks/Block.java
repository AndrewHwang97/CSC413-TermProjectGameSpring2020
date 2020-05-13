package RainbowReef.Blocks;
import RainbowReef.GameObjects.GameObject;
import RainbowReef.GameUtilities.Hitbox;

import java.awt.*;

public  abstract class Block extends GameObject{
    int hp;
    int x;
    int y;
    Image sprite;
    Hitbox hitbox;
    boolean breakable;
    boolean lifePlus = false;
    int points;

    public Image getSprite(){return this.sprite;}
    public int getX() { return x; }
    public int getY() { return y; }
    public abstract void takeDamage(int damage);
    public abstract void update();
    public abstract boolean getLifePlus();
    public boolean isBreakable(){return breakable;}
    public int getPoints(){return points;}
    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    public void draw(Graphics2D g) {
    }
}
