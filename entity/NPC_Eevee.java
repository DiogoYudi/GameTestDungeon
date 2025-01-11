package entity;

import java.awt.Rectangle;
import java.util.Random;
import main.GamePanel;

public class NPC_Eevee extends Entity{
    public NPC_Eevee(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 2;

        solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage(){
        up1 = setup("/sprite/npc/image", gp.titleSize, gp.titleSize);
        up2 = setup("/sprite/npc/image", gp.titleSize, gp.titleSize);
        down1 = setup("/sprite/npc/image", gp.titleSize, gp.titleSize);
        down2 = setup("/sprite/npc/image", gp.titleSize, gp.titleSize);
        left1 = setup("/sprite/npc/image", gp.titleSize, gp.titleSize);
        left2 = setup("/sprite/npc/image", gp.titleSize, gp.titleSize);
        right1 = setup("/sprite/npc/image", gp.titleSize, gp.titleSize);
        right2 = setup("/sprite/npc/image", gp.titleSize, gp.titleSize);
    }

    public void setDialogue(){
        dialogues[0][0] = "Eevee!";
    }

    public void setAction(){ //Vai definir um movimento para os npc
        if(onPath == true){
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.titleSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.titleSize;
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
        super.speak();
        onPath = true;
    }
}
