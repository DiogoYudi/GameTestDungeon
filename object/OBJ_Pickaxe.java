package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity{
    public static final String objName = "Pickaxe";
    public OBJ_Pickaxe(GamePanel gp){
        super(gp);

        name = objName;
        type = type_pickaxe;
        down1 = setup("/sprite/object/pickaxe", gp.titleSize, gp.titleSize);
        attackValue = 2;
        knockBackPower = 10;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[" + name + "]\nYou will dig it!";
        price = 7;
        motion1_duration = 10;
        motion2_duration = 30;
    }
}
