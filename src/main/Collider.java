package main;

import entity.Entity;

public class Collider {

    public static boolean checkCollision(Entity e1, Entity e2){
        return e1.x<e2.x+e2.imageSprite.getWidth()&&
                e1.x+e1.imageSprite.getWidth()>e2.x&&
                e1.y<e2.y+e2.imageSprite.getHeight()&&
                e1.y+e1.imageSprite.getHeight()>e2.y;
    }


}
