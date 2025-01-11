package main;

import data.Progress;
import entity.NPC_BigRock;
import entity.NPC_Eevee;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.MON_Bat;
import monster.MON_Orc;
import monster.MON_SkeletonLord;
import object.OBJ_BlueHeart;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_DoorIron;
import object.OBJ_Pickaxe;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_DryTree;
import tile_interactive.IT_MetalPlate;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){        //Local dos objetos
        int mapNum = 0;
        int i = 0;

        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.titleSize*30;
        gp.obj[mapNum][i].worldY = gp.titleSize*54;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.titleSize*50;
        gp.obj[mapNum][i].worldY = gp.titleSize*54;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
        gp.obj[mapNum][i].worldX = gp.titleSize*67;
        gp.obj[mapNum][i].worldY = gp.titleSize*56;
        i++;

        gp.obj[mapNum][i] = new OBJ_Boots(gp);
        gp.obj[mapNum][i].worldX = gp.titleSize*31;
        gp.obj[mapNum][i].worldY = gp.titleSize*52;
        i++;

        mapNum = 2;
        i = 0;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.titleSize*34;
        gp.obj[mapNum][i].worldY = gp.titleSize*40;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Potion_Red(gp));
        gp.obj[mapNum][i].worldX = gp.titleSize*27;
        gp.obj[mapNum][i].worldY = gp.titleSize*18;
        i++;

        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].setLoot(new OBJ_Shield_Blue(gp));
        gp.obj[mapNum][i].worldX = gp.titleSize*18;
        gp.obj[mapNum][i].worldY = gp.titleSize*10;
        i++;

        gp.obj[mapNum][i] = new OBJ_DoorIron(gp);
        gp.obj[mapNum][i].worldX = gp.titleSize*18;
        gp.obj[mapNum][i].worldY = gp.titleSize*23;
        i++;

        mapNum = 3;
        i = 0;

        gp.obj[mapNum][i] = new OBJ_DoorIron(gp);
        gp.obj[mapNum][i].worldX = gp.titleSize*25;
        gp.obj[mapNum][i].worldY = gp.titleSize*15;
        i++;

        gp.obj[mapNum][i] = new OBJ_BlueHeart(gp);
        gp.obj[mapNum][i].worldX = gp.titleSize*25;
        gp.obj[mapNum][i].worldY = gp.titleSize*8;
        i++;

    }

    public void setNPC(){
        int mapNum = 0;
        int i = 0;

        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.titleSize*60;
        gp.npc[mapNum][i].worldY = gp.titleSize*51;
        i++;

        gp.npc[mapNum][i] = new NPC_Eevee(gp);
        gp.npc[mapNum][i].worldX = gp.titleSize*37;
        gp.npc[mapNum][i].worldY = gp.titleSize*47;
        i++;

        mapNum = 1;
        i = 0;

        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.titleSize*25;
        gp.npc[mapNum][i].worldY = gp.titleSize*22;
        i++;

        mapNum = 2;
        i = 0;

        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.titleSize*20;
        gp.npc[mapNum][i].worldY = gp.titleSize*25;
        i++;

        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.titleSize*11;
        gp.npc[mapNum][i].worldY = gp.titleSize*18;
        i++;

        gp.npc[mapNum][i] = new NPC_BigRock(gp);
        gp.npc[mapNum][i].worldX = gp.titleSize*23;
        gp.npc[mapNum][i].worldY = gp.titleSize*14;
        i++;
    }

    public void setMonster(){
        int mapNum = 0;
        int i = 0;

        gp.monster[mapNum][i] = new MON_Orc(gp);
        gp.monster[mapNum][i].worldX = gp.titleSize*14;
        gp.monster[mapNum][i].worldY = gp.titleSize*49;
        i++;

        mapNum = 2;
        i = 0;

        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.titleSize*34;
        gp.monster[mapNum][i].worldY = gp.titleSize*39;
        i++;

        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.titleSize*36;
        gp.monster[mapNum][i].worldY = gp.titleSize*25;
        i++;

        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.titleSize*39;
        gp.monster[mapNum][i].worldY = gp.titleSize*26;
        i++;

        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.titleSize*28;
        gp.monster[mapNum][i].worldY = gp.titleSize*11;
        i++;

        gp.monster[mapNum][i] = new MON_Bat(gp);
        gp.monster[mapNum][i].worldX = gp.titleSize*10;
        gp.monster[mapNum][i].worldY = gp.titleSize*19;
        i++;

        mapNum = 3;
        i = 0;

        if(Progress.skeletonLordDefeated == false){
            gp.monster[mapNum][i] = new MON_SkeletonLord(gp);
            gp.monster[mapNum][i].worldX = gp.titleSize*23;
            gp.monster[mapNum][i].worldY = gp.titleSize*16;
            i++;
        }
    }

    public void setInteractiveTile(){
        int mapNum = 0;
        int i = 0;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 21);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 33, 7);
        i++;

        mapNum = 2;
        i = 0;

        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 30);
        i++;

        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 31);
        i++;

        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 17, 32);
        i++;

        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 34);
        i++;

        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 18, 33);
        i++;

        gp.iTile[mapNum][i] = new IT_DestructibleWall(gp, 10, 22);
        i++;

        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 20, 22);
        i++;

        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 8, 17);
        i++;

        gp.iTile[mapNum][i] = new IT_MetalPlate(gp, 39, 31);
        i++;
    }
}
