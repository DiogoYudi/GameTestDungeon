package entity;

import main.GamePanel;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Key;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;
import object.OBJ_Tent;

public class NPC_Merchant extends Entity{
    public NPC_Merchant(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getNPCImage();
        setDialogue();
        setItems();
    }

    public void getNPCImage(){
        up1 = setup("/sprite/npc/merchant_down_1", gp.titleSize, gp.titleSize);
        up2 = setup("/sprite/npc/merchant_down_2", gp.titleSize, gp.titleSize);
        down1 = setup("/sprite/npc/merchant_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/sprite/npc/merchant_down_2", gp.titleSize, gp.titleSize);
        left1 = setup("/sprite/npc/merchant_down_1", gp.titleSize, gp.titleSize);
        left2 = setup("/sprite/npc/merchant_down_2", gp.titleSize, gp.titleSize);
        right1 = setup("/sprite/npc/merchant_down_1", gp.titleSize, gp.titleSize);
        right2 = setup("/sprite/npc/merchant_down_2", gp.titleSize, gp.titleSize);
    }

    public void setDialogue(){
        dialogues[0][0] = "He he, so you found me.\nI have some good stuff.\nDo you want to trade?";
        dialogues[1][0] = "Come again, hehe!";
        dialogues[2][0] = "You need more coin to buy that!";
        dialogues[3][0] = "You cannot carry any more!";
        dialogues[4][0] = "You cannot sell an equipped item!";
    }

    public void setItems(){
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Shield_Blue(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Boots(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Tent(gp));
    }

    public void speak(){
        facePlayer();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}