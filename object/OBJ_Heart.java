package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity{
    GamePanel gp;
    public static final String objName = "Heart";
    public OBJ_Heart(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = objName;
        type = type_pickupOnly;
        value = 2;
        down1 = setup("/sprite/object/heart_full", gp.titleSize, gp.titleSize);
        image = setup("/sprite/object/heart_full", gp.titleSize, gp.titleSize);
        image2 = setup("/sprite/object/heart_half", gp.titleSize, gp.titleSize);
        image3 = setup("/sprite/object/heart_blank", gp.titleSize, gp.titleSize);
    }

    public boolean use(Entity entity){
        gp.playSE(2);
        gp.ui.addMessage("Life +" + value);
        entity.life += value;
        return true;
    }
}
