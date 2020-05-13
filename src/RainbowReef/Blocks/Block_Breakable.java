package RainbowReef.Blocks;

import RainbowReef.GameManager;
import RainbowReef.GameUtilities.*;

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
    Image life;
    GameManager gameManager;
    boolean destroy;
    boolean lifePlus = false;
    public Block_Breakable(int x, int y, String color, GameManager gameManager){
        try{
            violet = read(Block.class.getClassLoader().getResource("Blocks/Block1.gif"));
            yellow = read(Block.class.getClassLoader().getResource("Blocks/Block2.gif"));
            red = read(Block.class.getClassLoader().getResource("Blocks/Block3.gif"));
            green = read(Block.class.getClassLoader().getResource("Blocks/Block4.gif"));
            lightBlue = read(Block.class.getClassLoader().getResource("Blocks/Block5.gif"));
            darkBlue = read(Block.class.getClassLoader().getResource("Blocks/Block6.gif"));
            white = read(Block.class.getClassLoader().getResource("Blocks/Block7.gif"));
            life = read(Block.class.getClassLoader().getResource("Blocks/Block_life.gif"));
        }catch (Exception e){
            System.out.println("ERR in Breakable_Block Class: " + e);
        }
        this.x = x;
        this.y = y;
        this.hp = 1;
        this.destroy = false;

        initSpriteColor(color);

        this.hitbox = new Hitbox(this);
        this.breakable = true;
        this.points = 50;
        this.gameManager = gameManager;
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
            case "life":
                this.sprite = life;
                this.lifePlus = true;
                break;
            default:
                this.sprite = white;
                break;
        }
    }
    public void update(){
        if(!destroy){
            if(this.hp < 1){
                this.destroy = true;
                this.hitbox.disableHitbox();
                gameManager.sendPoints(this.points);
            }
        }
    }

    public boolean getLifePlus(){return this.lifePlus;}

    public void takeDamage(int damage){
        this.hp -= damage;
        System.out.println("hp: " + hp);
        gameManager.getSoundManager().playSound("sounds/Sound_block.wav");
    }

    public void draw(Graphics2D g) {
        if(!destroy){
            g.drawImage(sprite,x,y,null);
        }

    }
}
