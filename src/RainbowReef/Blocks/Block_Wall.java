package RainbowReef.Blocks;

import RainbowReef.GameUtilities.Hitbox;

import java.awt.*;

import static javax.imageio.ImageIO.read;

public class Block_Wall extends Block {

    Image blockImage;
    public Block_Wall(int x, int y){
        try{
            blockImage = read(Block.class.getClassLoader().getResource("Wall.gif"));
        }catch (Exception e){
            System.out.println("ERR in Breakable_Block Class: " + e);
        }
        this.x = x;
        this.y = y;
        this.hp = 1;
        this.sprite = blockImage;
        this.hitbox = new Hitbox(this);
        this.hitbox.getHitbox().height -= 3;
        this.hitbox.getHitbox().width -= 3;
        this.points = 0;
    }

    public void takeDamage(int damage) {
    }


    public void update() {
    }

    public void draw(Graphics2D g) {
        g.drawImage(sprite,x,y,null);
    }
}
