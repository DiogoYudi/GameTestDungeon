package entity;

import java.awt.Rectangle;
import java.util.ArrayList;
import main.GamePanel;
import object.OBJ_DoorIron;
import tile_interactive.IT_MetalPlate;
import tile_interactive.InteractiveTile;

public class NPC_BigRock extends Entity{
    public static final String npcName = "Big Rock";
    public NPC_BigRock(GamePanel gp){
        super(gp);

        name = npcName;
        direction = "down";
        speed = 4;
        
        solidArea = new Rectangle();
		solidArea.x = 2;
		solidArea.y = 6;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 44;
		solidArea.height = 40;

        dialogueSet = -1;

        getNPCImage();
        setDialogue();
    }

    public void getNPCImage(){
        up1 = setup("/sprite/npc/bigrock", gp.titleSize, gp.titleSize);
        up2 = setup("/sprite/npc/bigrock", gp.titleSize, gp.titleSize);
        down1 = setup("/sprite/npc/bigrock", gp.titleSize, gp.titleSize);
        down2 = setup("/sprite/npc/bigrock", gp.titleSize, gp.titleSize);
        left1 = setup("/sprite/npc/bigrock", gp.titleSize, gp.titleSize);
        left2 = setup("/sprite/npc/bigrock", gp.titleSize, gp.titleSize);
        right1 = setup("/sprite/npc/bigrock", gp.titleSize, gp.titleSize);
        right2 = setup("/sprite/npc/bigrock", gp.titleSize, gp.titleSize);
    }

    public void setDialogue(){
        dialogues[0][0] = "It's a giant rock!";
    }

    public void setAction(){}

    public void update(){}

    public void speak(){
        facePlayer();
        startDialogue(this, dialogueSet);

        dialogueSet++;

        if(dialogues[dialogueSet][0] == null){
            dialogueSet--;
        }
    }

    public void move(String d){
        this.direction = d;
        checkCollision();

        if(collisionOn == false){
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }
        
        detectPlate();
    }

    public void detectPlate(){
        ArrayList<InteractiveTile> plateList = new ArrayList<>();
        ArrayList<Entity> rockList = new ArrayList<>();

        //CREATE A PLATE LIST
        for(int i = 0; i < gp.iTile[1].length; i++){
            if(gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].name != null && gp.iTile[gp.currentMap][i].name.equals(IT_MetalPlate.itName)){
                plateList.add(gp.iTile[gp.currentMap][i]);
            }
        }

        //CREATE A ROCK LIST
        for(int i = 0; i < gp.npc[1].length; i++){
            if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(NPC_BigRock.npcName)){
                rockList.add(gp.npc[gp.currentMap][i]);
            }
        }

        int count = 0;

        //SCAN THE PLATE LIST
        for(int i = 0; i < plateList.size(); i++){
            int xDistance = Math.abs(worldX - plateList.get(i).worldX);
            int yDistance = Math.abs(worldY - plateList.get(i).worldY);
            int distance = Math.max(xDistance, yDistance);

            if(distance < 8){
                if(linkedEntity == null){   //Para que reproduza essa ação apenas uma vez
                    linkedEntity = plateList.get(i);
                    gp.playSE(3);
                }
                
            }else{
                if(linkedEntity == plateList.get(i)){
                    linkedEntity = null;
                }
                
            }
        }

        //SCAN THE ROCK LIST
        for(int i = 0; i < rockList.size(); i++){
            //COUNT THE ROCK ON THE PLATE
            if(rockList.get(i).linkedEntity != null){
                count++;
            }
        }

        //IF ALL THE ROCKS ARE ON THE PLATES, THE IRON DOOR OPENS
        if(count == rockList.size()){
            for(int i = 0; i < gp.obj[1].length; i++){
                if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_DoorIron.objName)){
                    gp.obj[gp.currentMap][i] = null;
                    gp.playSE(21);
                }
            }
        }
    }
}
