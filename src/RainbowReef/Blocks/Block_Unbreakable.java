package RainbowReef.Blocks;

import java.awt.*;

import static javax.imageio.ImageIO.read;

public class Block_Unbreakable extends Block{
    Image blockImage;
    public Block_Unbreakable(int x, int y){
        try{
            blockImage = read(Block.class.getClassLoader().getResource("Block_solid.gif"));
        }catch (Exception e){
            System.out.println("ERR in Breakable_Block Class: " + e);
        }
        this.x = x;
        this.y = y;
        this.hp = 1;
        this.sprite = blockImage;
    }

    public  void takeDamage(int damage){}
    public  void update(){}

    public void draw(Graphics2D g) {
        g.drawImage(sprite,x,y,null);
    }
}
