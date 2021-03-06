package RainbowReef.Blocks;

import RainbowReef.GameManager;
import RainbowReef.GameUtilities.Hitbox;

import java.awt.*;

import static javax.imageio.ImageIO.read;

public class Block_Unbreakable extends Block{
    Image blockImage;
    GameManager gameManager;
    public Block_Unbreakable(int x, int y, GameManager gameManager){
        try{
            blockImage = read(Block.class.getClassLoader().getResource("Blocks/Block_solid.gif"));
        }catch (Exception e){
            System.out.println("ERR in Breakable_Block Class: " + e);
        }
        this.x = x;
        this.y = y;
        this.hp = 1;
        this.sprite = blockImage;
        this.hitbox = new Hitbox(this);
        this.hitbox.getHitbox().width -= 8;
        this.breakable = false;
        this.points = 0;
        this.gameManager = gameManager;
    }

    public  void takeDamage(int damage){ gameManager.getSoundManager().playSound("sounds/Sound_block.wav");}
    public  void update(){}
    public boolean getLifePlus(){return  this.lifePlus;}

    public void draw(Graphics2D g) {
        g.drawImage(sprite,x,y,null);
    }
}
