package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueHeart extends Entity{
    GamePanel gp;
    public static final String objName = "Blue Heart";

    public OBJ_BlueHeart(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = objName;
        type = type_pickupOnly;
        down1 = setup("/sprite/object/blueheart", gp.titleSize, gp.titleSize);

        setDialogues();
    }

    public void setDialogues(){
        dialogues[0][0] = "You pick up a beautiful blue gem.";
        dialogues[0][1] = "You find the Blue Heart,\nthe legendary treasure!";
    }

    public boolean use(Entity entity){
        gp.gameState = gp.cutsceneState;
        gp.csManager.sceneNum = gp.csManager.ending;
        return true;
    }
}
