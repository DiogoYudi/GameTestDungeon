package tile_interactive;

import entity.Entity;
import java.awt.Color;
import main.GamePanel;

public class IT_DestructibleWall extends InteractiveTile{
    GamePanel gp;
    public IT_DestructibleWall(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;
        this.worldX = gp.titleSize * col;
        this.worldY = gp.titleSize * row;

        down1 = setup("/sprite/tiles_interactive/destructiblewall", gp.titleSize, gp.titleSize);
        destuctible = true;
    }
    
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        if(entity.currentWeapon.type == type_pickaxe){
            isCorrectItem = true;
        }
        return isCorrectItem;
    }

    public void playSE(){
        gp.playSE(20);
    }
    
    public InteractiveTile getDestroyedForm(){
        InteractiveTile tile = null;
        return tile;
    }

    public Color getParticleColor(){
        Color color = new Color(65, 65, 65);
        return color;
    }

    public int getParticleSize(){
        int size = 6;   //6 pixel
        return size;
    }

    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }

    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
}
