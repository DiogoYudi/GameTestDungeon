package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity{
    GamePanel gp;
    public static final String objName = "Red Potion";
    public OBJ_Potion_Red(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = objName;
        value = 5;
        type = type_consumable;
        down1 = setup("/sprite/object/potion_red", gp.titleSize, gp.titleSize);
        description = "[" + name + "]\nHeals your life " + value + ".";
        price = 2;
        stackable = true;

        setDialogue();
    }

    public void setDialogue(){
        dialogues[0][0] = "You drink the " + name + "!\n" + "Your life has been recovered by " + value + ".";
    }

    public boolean use(Entity entity){
        startDialogue(this, 0);
        entity.life += value;
        gp.playSE(2);
        return true;
    }
}
