package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity{
    public static final String objName = "Normal Sword";
    public OBJ_Sword_Normal(GamePanel gp){
        super(gp);
        
        name = objName;
        type = type_sword;
        down1 = setup("/sprite/object/sword_normal", gp.titleSize, gp.titleSize);
        attackValue = 1;
        knockBackPower = 2;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old sword.";
        price = 5;
        motion1_duration = 5;
        motion2_duration = 25;
    }
}
