package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity{
    public static final String objName = "Woodcutter's Axe";
    public OBJ_Axe(GamePanel gp){
        super(gp);

        name = objName;
        type = type_axe;
        down1 = setup("/sprite/object/axe", gp.titleSize, gp.titleSize);
        attackValue = 2;
        knockBackPower = 10;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nA bit rusty but still \ncan cut some trees.";
        price = 7;
        motion1_duration = 20;
        motion2_duration = 40;
    }
}
