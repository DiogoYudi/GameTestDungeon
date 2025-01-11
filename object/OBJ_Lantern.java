package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity{
    public static final String objName = "Lantern";
    public OBJ_Lantern(GamePanel gp) {
        super(gp);

        name = objName;
        type = type_light;
        down1 = setup("/sprite/object/lantern", gp.titleSize, gp.titleSize);
        description = "[" + name + "]\nIlluminates your \nsurroundings";
        price = 200;
        lightRadius = 250;
    }
    
}
