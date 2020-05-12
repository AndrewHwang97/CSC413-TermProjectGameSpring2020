package RainbowReef.GameUtilities;

import RainbowReef.Blocks.*;
import RainbowReef.GameManager;
import RainbowReef.GameObjects.*;

import java.awt.*;

public class CollisonDetector {
    Rectangle hbObj1;
    Rectangle hbObj2;
    Rectangle hbObj3;
    Rectangle hbObj4;

    public CollisonDetector(){
        // nothing here?
    }
/*
    public void checkCollisions(GameObject obj1, GameObject obj2){
        hbOj1 = obj1.getHitbox().hitbox;
        hbObj2 = obj2.getHitbox().hitbox;

        if(hbOj1.intersects(hbObj2)){
            System.out.println("COLLISION");
        }
    }
*/
/*
    public void checkCollisions(GameObject obj1, Block obj2){
        hbObj1 = obj1.getHitbox().hitbox;
        hbObj2 = obj2.getHitbox().hitbox;

        if(hbObj1.intersects(hbObj2)){
            System.out.println("hit");
            obj2.takeDamage(1);
        }
    }
*/

    public void checkCollisions(Pop obj1, Block obj2){
        hbObj1 = obj1.getHitbox().hitbox;
        hbObj2 = obj2.getHitbox().hitbox;

        if(hbObj1.intersects(hbObj2)){
            if(!obj2.isBreakable())
                obj1.changeSpeeds(obj1.getxSpeed()*-1,obj1.getySpeed()*-1);
            else{
                obj1.changeSpeeds(obj1.getxSpeed()*1,obj1.getySpeed()*-1);
                System.out.println("hit");
                obj2.takeDamage(1);
            }
        }
    }
    public void checkCollisions(Katch obj1, Pop obj2){
        hbObj1 = obj1.getHitbox("left").hitbox;
        hbObj2 = obj1.getHitbox("mid").hitbox;
        hbObj3 = obj1.getHitbox("right").hitbox;
        hbObj4 = obj2.getHitbox().hitbox;

        int randNumX;
        int randNumY;
        randNumX = (int)(Math.random() * (5 - 1)+ 1);
        randNumY = (int)(Math.random() * (4 - 1)+ 1);

        if(hbObj1.intersects(hbObj4)){
            System.out.println("HIT LEFT");
            obj2.changeSpeeds(randNumX*-1,randNumY*-1);
        }
        if(hbObj2.intersects(hbObj4)){
            obj2.setY(obj2.getY() -5);
            obj2.changeSpeeds(obj2.getxSpeed(),obj2.getySpeed()*-1);
        }
        if(hbObj3.intersects(hbObj4)){
            System.out.println("HIT Right");
            obj2.changeSpeeds(randNumX,randNumY*-1);
        }
    }



}
