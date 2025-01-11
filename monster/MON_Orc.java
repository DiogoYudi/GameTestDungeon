package monster;

import entity.Entity;
import java.util.Random;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class MON_Orc extends Entity{
    GamePanel gp;
    public MON_Orc(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Orc";
        type = type_monster;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 8;
        defense = 2;
        exp = 10;
        knockBackPower = 5;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;

        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();
    }

    public void getImage(){
        up1 = setup("/sprite/monster/orc_up_1", gp.titleSize, gp.titleSize);
        up2 = setup("/sprite/monster/orc_up_2", gp.titleSize, gp.titleSize);

        down1 = setup("/sprite/monster/orc_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/sprite/monster/orc_down_2", gp.titleSize, gp.titleSize);

        left1 = setup("/sprite/monster/orc_left_1", gp.titleSize, gp.titleSize);
        left2 = setup("/sprite/monster/orc_left_2", gp.titleSize, gp.titleSize);

        right1 = setup("/sprite/monster/orc_right_1", gp.titleSize, gp.titleSize);
        right2 = setup("/sprite/monster/orc_right_2", gp.titleSize, gp.titleSize);
    }

    public void getAttackImage(){
        attackUp1 = setup("/sprite/monster/orc_attack_up_1", gp.titleSize, gp.titleSize * 2);    //Vezes 2 porque as imagens é 16x32
        attackUp2 = setup("/sprite/monster/orc_attack_up_2", gp.titleSize, gp.titleSize * 2);
        attackDown1 = setup("/sprite/monster/orc_attack_down_1", gp.titleSize, gp.titleSize * 2);
        attackDown2 = setup("/sprite/monster/orc_attack_down_2", gp.titleSize, gp.titleSize * 2);
        attackLeft1 = setup("/sprite/monster/orc_attack_left_1", gp.titleSize *2, gp.titleSize);
        attackLeft2 = setup("/sprite/monster/orc_attack_left_2", gp.titleSize * 2, gp.titleSize);
        attackRight1 = setup("/sprite/monster/orc_attack_right_1", gp.titleSize * 2, gp.titleSize);
        attackRight2 = setup("/sprite/monster/orc_attack_right_2", gp.titleSize * 2, gp.titleSize);
    }

    public void setAction(){
        if(onPath == true){
            //CHECK IF IT STOP CHASING
            checkStopChasingOrNot(gp.player, 15, 100);

            //SEARCH THE DIRECTION TO GO
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }else{
            //CHECK IF IT STARTS CHASING
            checkStartChasingOrNot(gp.player, 5, 100);

            //GET A RANDOM DIRECTION
            getRandomDirection(120);
        }
        //CHECK IF IT ATTACKS
        if(attacking == false){
            checkAttackOrNot(30, gp.titleSize*4, gp.titleSize);
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
