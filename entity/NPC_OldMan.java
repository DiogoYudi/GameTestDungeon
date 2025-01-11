package entity;

import java.awt.Rectangle;
import java.util.Random;
import main.GamePanel;

public class NPC_OldMan extends Entity{
    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        
        solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;

        dialogueSet = -1;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage(){
        up1 = setup("/sprite/npc/oldman_up_1", gp.titleSize, gp.titleSize);
        up2 = setup("/sprite/npc/oldman_up_2", gp.titleSize, gp.titleSize);
        down1 = setup("/sprite/npc/oldman_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/sprite/npc/oldman_down_2", gp.titleSize, gp.titleSize);
        left1 = setup("/sprite/npc/oldman_left_1", gp.titleSize, gp.titleSize);
        left2 = setup("/sprite/npc/oldman_left_2", gp.titleSize, gp.titleSize);
        right1 = setup("/sprite/npc/oldman_right_1", gp.titleSize, gp.titleSize);
        right2 = setup("/sprite/npc/oldman_right_2", gp.titleSize, gp.titleSize);
    }

    public void setDialogue(){
        dialogues[0][0] = "Hello!";
        dialogues[0][1] = "So you've come to this island to \nfind the treasure?";
        dialogues[0][2] = "I used to be a great wizard but now... \nI'm bit too old for taking an adventure.";
        dialogues[0][3] = "Well, good luck on you.";

        dialogues[1][0] = "If you become tired, rest at the water.";
        dialogues[1][1] = "However, the monster reappear if you rest.\nI don't know why but that's how it works.";
        dialogues[1][2] = "In any case, don't push yourself too hard.";

        dialogues[2][0] = "I wonder how to open that door...";
    }

    public void setAction(){ //Vai definir um movimento para os npc
        if(onPath == true){
            int goalCol = 12;
            int goalRow = 9;

            searchPath(goalCol, goalRow);
        }else{
            actionLockCounter++;

            if(actionLockCounter == 120){               //Para que o npc não fique trocando de direção a cada frame (120frame = 2s)
                Random random = new Random();
                int i = random.nextInt(100)+1;

                if(i <= 25){                            //É tipo uma IA, que vai escolher aleatoriamente o movimento do npc
                    direction = "up";
                }if(i > 25 && i <= 50){
                    direction = "down";
                }if(i > 50 && i <= 75){
                    direction = "left";
                }if(i > 75 && i <= 100){
                    direction = "right";
                }
                actionLockCounter = 0;   
            }
        }

        
    }

    public void speak(){
        facePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++;

        if(dialogues[dialogueSet][0] == null){
            dialogueSet--;
        }

        //onPath = true;
    }
}
