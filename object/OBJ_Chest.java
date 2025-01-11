package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{
    GamePanel gp;
    public static final String objName = "Chest";

    public OBJ_Chest(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = objName;
        type = type_obstacle;
        image = setup("/sprite/object/chest", gp.titleSize, gp.titleSize);
        image2 = setup("/sprite/object/chest_opened", gp.titleSize, gp.titleSize);
        down1 = image;
        collision = true;

		solidArea.x = 4;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 40;
		solidArea.height = 32;
    }

    public void setDialogue(){
        dialogues[0][0] = "You open the chest and find a " + loot.name + "!\n...But you cannot carry any more!";
        dialogues[1][0] = "You open the chest and find a " + loot.name + "!\nYou obtain the " + loot.name + "!";
        dialogues[2][0] = "It's empty!";
    }

    public void setLoot(Entity loot){
        this.loot = loot;

        setDialogue();
    }

    public void interact(){
        if(opened == false){
            gp.playSE(3);
            if(gp.player.canObtainItem(loot) == false){
                startDialogue(this, 0);
            }else{
                startDialogue(this, 1);
                down1 = image2;
                opened = true;
            }
        }else{
            startDialogue(this, 2);
        }
    }
}
