package main;

import data.Progress;
import entity.Entity;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];
    Entity eventMaster;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp){
        this.gp = gp;
        eventMaster = new Entity(gp);

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;

                if(row == gp.maxWorldRow){
                    row = 0;
                    map++;
                }
            }
        }
        setDialogue();
    }

    public void setDialogue(){
        eventMaster.dialogues[0][0] = "You fall into a pit!";
        eventMaster.dialogues[1][0] = "You drink the water. \nYour life and mana has been recovered.\n(The progress has been saved)";
    }

    public void checkEvent(){

        //Checar se o player está a pixel de distancia do ultimo evento
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.titleSize){
            canTouchEvent = true;
        }

        if(canTouchEvent){
            if(hit(0,26,26,"right") == true) damagePit(gp.dialogueState);
            else if(hit(0, 33, 58, "down") == true) healingPool(gp.dialogueState);
            else if(hit(0, 30, 50, "any") == true) teleport(1, 25, 26, gp.indoor);
            else if(hit(1, 25, 26, "any") == true) teleport(0, 30, 50, gp.outside);
            else if(hit(1, 25, 24, "up") == true) speak(gp.npc[1][0]);
            else if(hit(0, 67, 54, "any") == true) teleport(2, 9, 41, gp.dungeon);
            else if(hit(2, 9, 41, "any") == true) teleport(0, 67, 54, gp.outside);
            else if(hit(2, 8, 7, "any") == true) teleport(3, 26, 41, gp.dungeon);
            else if(hit(3, 26, 41, "any") == true) teleport(2, 8, 7, gp.dungeon);
            else if(hit(3, 25, 27, "any") == true) skeletonLord(); //CUTSCENE
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection){        //Verificar se o player está colidindo com o pixel que irá gerar um evento
        boolean hit = false;
        if(map == gp.currentMap){
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col*gp.titleSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row*gp.titleSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false){
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void damagePit(int gameState){
        gp.gameState = gameState;
        gp.playSE(6);
        eventMaster.startDialogue(eventMaster, 0);
        gp.player.life -= 1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }

    public void healingPool(int gameState){
        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(2);
            eventMaster.startDialogue(eventMaster, 1);
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setMonster();
            gp.saveLoad.save();
        }
    }

    public void teleport(int map, int col, int row, int area){

        gp.gameState = gp.transitionState;
        gp.nextArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(13);
    }

    public void speak(Entity entity){
        if(gp.keyH.enterPressed == true){
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }

    public void skeletonLord(){
        if(gp.bossBattleOn == false && Progress.skeletonLordDefeated == false){
            gp.gameState = gp.cutsceneState;
            gp.csManager.sceneNum = gp.csManager.skeletonLord;
        }
    }
}
