package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity{
    public static final String objName = "Blue Shield";
    public OBJ_Shield_Blue(GamePanel gp){
        super(gp);

        name = objName;
        type = type_shield;
        down1 = setup("/sprite/object/shield_blue", gp.titleSize, gp.titleSize);
        defenseValue = 2;
        description = "[" + name + "]\nA shiny blue shield.";
        price = 10;
    }
}
