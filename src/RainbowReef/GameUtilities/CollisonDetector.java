package RainbowReef.GameUtilities;

import RainbowReef.GameObjects.*;

import java.awt.*;

public class CollisonDetector {
    Rectangle hbOj1;
    Rectangle hbObj2;

    public CollisonDetector(){
        // nothing here?
    }

    public void checkCollisions(GameObject obj1, GameObject obj2){
        hbOj1 = obj1.getHitbox().hitbox;
        hbObj2 = obj2.getHitbox().hitbox;

        if(hbOj1.intersects(hbObj2)){
            System.out.println("COLLISION");
        }
    }


}
