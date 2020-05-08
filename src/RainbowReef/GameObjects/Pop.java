package RainbowReef.GameObjects;

import RainbowReef.Blocks.Block;
import RainbowReef.GameUtilities.Hitbox;

import java.awt.*;

import static javax.imageio.ImageIO.read;

public class Pop extends GameObject {
    int xSpeed = 0;
    int ySpeed = -2;
    Image popImage;
    public Pop(int x, int y){
        try{
            popImage = read(Block.class.getClassLoader().getResource("Pop.gif"));
        }catch (Exception e){
            System.out.println("ERR in Pop class: " + e);
        }
        this.x = x;
        this.y = y;
        this.sprite = popImage;
        this.hitbox = new Hitbox(this);
    }

    public void update(){
        this.x += xSpeed;
        this.y += ySpeed;
        this.hitbox.update(this);
    }

    public void draw(Graphics2D g) {
        g.drawImage(sprite,x,y,null);
        g.setColor(Color.green);
        g.drawRect(x,y,this.getSprite().getWidth(null),this.getSprite().getHeight(null));
    }
}
