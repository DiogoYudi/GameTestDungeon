package entity;

import main.GamePanel;

public class PlayerDummy extends Entity{
    public static final String npcName = "Dummy";
    public PlayerDummy(GamePanel gp) {
        super(gp);
        name = npcName;
        getImage();
    }

    public void getImage(){
        up1 = setup("/sprite/player/boy_up_1", gp.titleSize, gp.titleSize);
        up2 = setup("/sprite/player/boy_up_2", gp.titleSize, gp.titleSize);
        down1 = setup("/sprite/player/boy_down_1", gp.titleSize, gp.titleSize);
        down2 = setup("/sprite/player/boy_down_2", gp.titleSize, gp.titleSize);
        left1 = setup("/sprite/player/boy_left_1", gp.titleSize, gp.titleSize);
        left2 = setup("/sprite/player/boy_left_2", gp.titleSize, gp.titleSize);
        right1 = setup("/sprite/player/boy_right_1", gp.titleSize, gp.titleSize);
        right2 = setup("/sprite/player/boy_right_2", gp.titleSize, gp.titleSize);
    }
}
