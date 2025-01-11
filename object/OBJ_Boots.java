package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity{
    public static final String objName = "Boots";
    public OBJ_Boots(GamePanel gp){
        super(gp);
        name = objName;
        description = "[" + name + "]\nIt increase the player speed.";
        down1 = setup("/sprite/object/boots", gp.titleSize, gp.titleSize);
        price = 10;
    }
}