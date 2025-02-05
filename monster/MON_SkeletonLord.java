package monster;

import data.Progress;
import entity.Entity;
import java.util.Random;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_DoorIron;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class MON_SkeletonLord extends Entity{
    GamePanel gp;
    public static final String monName = "Skeleton Lord";
    public MON_SkeletonLord(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = monName;
        type = type_monster;
        boss = true;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 50;
        life = maxLife;
        attack = 10;
        defense = 2;
        exp = 50;
        knockBackPower = 5;
        sleep = true;

        int size = gp.titleSize*5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48*2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 170;
        attackArea.height = 170;

        motion1_duration = 25;
        motion2_duration = 50;

        getImage();
        getAttackImage();
        setDialogue();
    }

    public void getImage(){
        int i = 5;

        if(inRage == false){
            up1 = setup("/sprite/monster/skeletonlord_up_1", gp.titleSize*i, gp.titleSize*i);
            up2 = setup("/sprite/monster/skeletonlord_up_2", gp.titleSize*i, gp.titleSize*i);

            down1 = setup("/sprite/monster/skeletonlord_down_1", gp.titleSize*i, gp.titleSize*i);
            down2 = setup("/sprite/monster/skeletonlord_down_2", gp.titleSize*i, gp.titleSize*i);

            left1 = setup("/sprite/monster/skeletonlord_left_1", gp.titleSize*i, gp.titleSize*i);
            left2 = setup("/sprite/monster/skeletonlord_left_2", gp.titleSize*i, gp.titleSize*i);

            right1 = setup("/sprite/monster/skeletonlord_right_1", gp.titleSize*i, gp.titleSize*i);
            right2 = setup("/sprite/monster/skeletonlord_right_2", gp.titleSize*i, gp.titleSize*i);
        }
        if(inRage == true){
            up1 = setup("/sprite/monster/skeletonlord_phase2_up_1", gp.titleSize*i, gp.titleSize*i);
            up2 = setup("/sprite/monster/skeletonlord_phase2_up_2", gp.titleSize*i, gp.titleSize*i);

            down1 = setup("/sprite/monster/skeletonlord_phase2_down_1", gp.titleSize*i, gp.titleSize*i);
            down2 = setup("/sprite/monster/skeletonlord_phase2_down_2", gp.titleSize*i, gp.titleSize*i);

            left1 = setup("/sprite/monster/skeletonlord_phase2_left_1", gp.titleSize*i, gp.titleSize*i);
            left2 = setup("/sprite/monster/skeletonlord_phase2_left_2", gp.titleSize*i, gp.titleSize*i);

            right1 = setup("/sprite/monster/skeletonlord_phase2_right_1", gp.titleSize*i, gp.titleSize*i);
            right2 = setup("/sprite/monster/skeletonlord_phase2_right_2", gp.titleSize*i, gp.titleSize*i);
        }

        
    }

    public void getAttackImage(){
        int i = 5;

        if(inRage == false){
            attackUp1 = setup("/sprite/monster/skeletonlord_attack_up_1", gp.titleSize*i, gp.titleSize*i * 2);    //Vezes 2 porque as imagens é 16x32
            attackUp2 = setup("/sprite/monster/skeletonlord_attack_up_2", gp.titleSize*i, gp.titleSize*i * 2);
            attackDown1 = setup("/sprite/monster/skeletonlord_attack_down_1", gp.titleSize*i, gp.titleSize*i * 2);
            attackDown2 = setup("/sprite/monster/skeletonlord_attack_down_2", gp.titleSize*i, gp.titleSize*i * 2);
            attackLeft1 = setup("/sprite/monster/skeletonlord_attack_left_1", gp.titleSize*i *2, gp.titleSize*i);
            attackLeft2 = setup("/sprite/monster/skeletonlord_attack_left_2", gp.titleSize*i * 2, gp.titleSize*i);
            attackRight1 = setup("/sprite/monster/skeletonlord_attack_right_1", gp.titleSize*i * 2, gp.titleSize*i);
            attackRight2 = setup("/sprite/monster/skeletonlord_attack_right_2", gp.titleSize*i * 2, gp.titleSize*i);
        }
        if(inRage == true){
            attackUp1 = setup("/sprite/monster/skeletonlord_phase2_attack_up_1", gp.titleSize*i, gp.titleSize*i * 2);    //Vezes 2 porque as imagens é 16x32
            attackUp2 = setup("/sprite/monster/skeletonlord_phase2_attack_up_2", gp.titleSize*i, gp.titleSize*i * 2);
            attackDown1 = setup("/sprite/monster/skeletonlord_phase2_attack_down_1", gp.titleSize*i, gp.titleSize*i * 2);
            attackDown2 = setup("/sprite/monster/skeletonlord_phase2_attack_down_2", gp.titleSize*i, gp.titleSize*i * 2);
            attackLeft1 = setup("/sprite/monster/skeletonlord_phase2_attack_left_1", gp.titleSize*i *2, gp.titleSize*i);
            attackLeft2 = setup("/sprite/monster/skeletonlord_phase2_attack_left_2", gp.titleSize*i * 2, gp.titleSize*i);
            attackRight1 = setup("/sprite/monster/skeletonlord_phase2_attack_right_1", gp.titleSize*i * 2, gp.titleSize*i);
            attackRight2 = setup("/sprite/monster/skeletonlord_phase2_attack_right_2", gp.titleSize*i * 2, gp.titleSize*i);
        }
    }   

    public void setDialogue(){
        dialogues[0][0] = "No one can steal my treasure!";
        dialogues[0][1] = "You will die here!";
        dialogues[0][2] = "WELCOME TO YOUR DOOM!";
    }

    public void setAction(){
        if(inRage == false && life < maxLife/2){
            inRage = true;
            getImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack *= 2;
        }

        if(getTileDistance(gp.player) < 10){
            moveTowardPlayer(60);
        }else{
            getRandomDirection(120);
        }

        if(attacking == false){
            checkAttackOrNot(60, gp.titleSize*7, gp.titleSize*5);
        }
    }

    public void damageReaction(){
        actionLockCounter = 0;
        //direction = gp.player.direction;
        onPath = true;
    }

    public void checkDrop(){
        gp.bossBattleOn = false;
        Progress.skeletonLordDefeated = true;
        //RESTORE THE PREVIOUS MUSIC
        gp.stopMusic();
        gp.playMusic(19);

        //REMOVE THE IRONDOOR
        for(int i = 0; i < gp.obj[1].length; i++){
            if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_DoorIron.objName)){
                gp.playSE(21);
                gp.obj[gp.currentMap][i] = null;
            }
        }

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
