package RainbowReef.Blocks;

import RainbowReef.GameUtilities.Hitbox;

import java.awt.*;

import static javax.imageio.ImageIO.read;

public class Block_Breakable extends Block {
    Image violet;
    Image yellow;
    Image red;
    Image green;
    Image lightBlue;
    Image darkBlue;
    Image white;
    boolean destroy;
    public Block_Breakable(int x, int y, String color){
        try{
            violet = read(Block.class.getClassLoader().getResource("Block1.gif"));
            yellow = read(Block.class.getClassLoader().getResource("Block2.gif"));
            red = read(Block.class.getClassLoader().getResource("Block3.gif"));
            green = read(Block.class.getClassLoader().getResource("Block4.gif"));
            lightBlue = read(Block.class.getClassLoader().getResource("Block5.gif"));
            darkBlue = read(Block.class.getClassLoader().getResource("Block6.gif"));
            white = read(Block.class.getClassLoader().getResource("Block7.gif"));
        }catch (Exception e){
            System.out.println("ERR in Breakable_Block Class: " + e);
        }
        this.x = x;
        this.y = y;
        this.hp = 1;
        this.destroy = false;

        initSpriteColor(color);

        this.hitbox = new Hitbox(this);
    }

    public void initSpriteColor(String color){
        switch (color){
            case "violet":
                this.sprite = violet;
                break;
            case "yellow":
                this.sprite = yellow;
                break;
            case "red":
                this.sprite = red;
                break;
            case "green":
                this.sprite = green;
                break;
            case "lightBlue":
                this.sprite = lightBlue;
                break;
            case "darkBlue":
                this.sprite = darkBlue;
                break;
            case "white":
                this.sprite = white;
                break;
            default:
                this.sprite = white;
                break;
        }
    }
    public void update(){
        if(this.hp < 1){
            this.destroy = true;
        }
        if(destroy){
            this.hitbox.disableHitbox();
        }
    }

    public void takeDamage(int damage){
        this.hp -= damage;
    }

    public void draw(Graphics2D g) {
        if(!destroy){
            g.drawImage(sprite,x,y,null);
        }

    }
}
