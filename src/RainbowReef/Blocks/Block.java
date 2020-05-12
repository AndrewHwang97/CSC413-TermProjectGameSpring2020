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

    public Image getSprite(){return this.sprite;}
    public int getX() { return x; }
    public int getY() { return y; }
    public abstract void takeDamage(int damage);
    public abstract void update();
    public boolean isBreakable(){return breakable;}
    @Override
    public Hitbox getHitbox() {
        return hitbox;
    }

    public void draw(Graphics2D g) {
    }
}
