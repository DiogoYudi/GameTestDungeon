package monster;

import entity.Entity;
import java.util.Random;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class MON_Bat extends Entity{
    GamePanel gp;
    public MON_Bat(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Bat";
        type = type_monster;
        defaultSpeed = 4;
        speed = defaultSpeed;
        maxLife = 7;
        life = maxLife;
        attack = 7;
        defense = 0;
        exp = 7;

        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = setup("/sprite/monster/bat_down_1", gp.titleSize, gp.titleSize);
        up2 = setup("/sprite/monster/bat_down_2", gp.titleSize, gp.titleSize);

        down1 = setup("/sprite/monster/bat_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/sprite/monster/bat_down_2", gp.titleSize, gp.titleSize);

        left1 = setup("/sprite/monster/bat_down_1", gp.titleSize, gp.titleSize);
        left2 = setup("/sprite/monster/bat_down_2", gp.titleSize, gp.titleSize);

        right1 = setup("/sprite/monster/bat_down_1", gp.titleSize, gp.titleSize);
        right2 = setup("/sprite/monster/bat_down_2", gp.titleSize, gp.titleSize);
    }

    public void setAction(){
        getRandomDirection(10);
    }

    public void damageReaction(){
        actionLockCounter = 0;
        //direction = gp.player.direction;
        onPath = true;
    }

    public void checkDrop(){
        int i = new Random().nextInt(100)+1;

        //SET MONSTER DROP
        if(i < 25){
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if(i >= 25 && i < 50){
            dropItem(null);
        }
        if(i >= 50 && i < 75){
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 75 && i < 100){
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }
}
