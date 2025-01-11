package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{
    GamePanel gp;
    public static final String objName = "Key";
    public OBJ_Key(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = objName;
        type = type_consumable;
        down1 = setup("/sprite/object/key", gp.titleSize, gp.titleSize);
        description = "[" + name + "]\nIt opens a door.";
        price = 2;
        stackable = true;
        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] = "You use the " + name + " and open the door!";
        dialogues[1][0] = "What are youy doing?";
    }

    public boolean use(Entity entity){

        int objIndex = getDetected(entity, gp.obj, "Door");

        if(objIndex != 999){
            startDialogue(this, 0);
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        }else{
            startDialogue(this, 1);
            return false;
        }
    }
}
