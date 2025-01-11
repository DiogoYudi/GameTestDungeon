package monster;

import entity.Entity;
import java.util.Random;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

public class MON_GreenSlime extends Entity{
    GamePanel gp;
    public MON_GreenSlime(GamePanel gp){
        super(gp);
        this.gp = gp;
        name = "Green Slime";
        type = type_monster;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage(){
        up1 = setup("/sprite/monster/greenslime_down_1", gp.titleSize, gp.titleSize);
        up2 = setup("/sprite/monster/greenslime_down_2", gp.titleSize, gp.titleSize);

        down1 = setup("/sprite/monster/greenslime_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/sprite/monster/greenslime_down_2", gp.titleSize, gp.titleSize);

        left1 = setup("/sprite/monster/greenslime_down_1", gp.titleSize, gp.titleSize);
        left2 = setup("/sprite/monster/greenslime_down_2", gp.titleSize, gp.titleSize);

        right1 = setup("/sprite/monster/greenslime_down_1", gp.titleSize, gp.titleSize);
        right2 = setup("/sprite/monster/greenslime_down_2", gp.titleSize, gp.titleSize);
    }

    public void setAction(){
        if(onPath == true){
            //CHECK IF IT STOP CHASING
            checkStopChasingOrNot(gp.player, 15, 100);

            //SEARCH THE DIRECTION TO GO
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            //CHECK IF IT SHOOTS PROJECTILE
            checkShootOrNot(200, 30);
        }else{
            //CHECK IF IT STARTS CHASING
            checkStartChasingOrNot(gp.player, 5, 100);

            //GET A RANDOM DIRECTION
            getRandomDirection(120);
        }
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
