package main;

import entity.PlayerDummy;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import monster.MON_SkeletonLord;
import object.OBJ_BlueHeart;
import object.OBJ_DoorIron;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;

    //SCENE NUMBER
    public final int NA = 0;
    public final int skeletonLord = 1;
    public final int ending = 2;

    public CutsceneManager(GamePanel gp){
        this.gp = gp;

        endCredit = "aaaaaaaaaaaaaa\nbbbbbbbbbbbbbbbb\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nccccccccccccccccccc\nddddddddddddd\neeeeeeeeeeeeeee";
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        switch(sceneNum){
            case skeletonLord: scene_skeletonLord(); break;
            case ending: scene_ending(); break;
        }
    }

    public void scene_skeletonLord(){
        if(scenePhase == 0){
            gp.bossBattleOn = true;
            gp.stopMusic();

            //SHUT THE IRON DOOR
            for(int i = 0; i < gp.obj[1].length; i++){
                if(gp.obj[gp.currentMap][i] != null){
                    gp.obj[gp.currentMap][i] = new OBJ_DoorIron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.titleSize * 25;
                    gp.obj[gp.currentMap][i].worldY = gp.titleSize * 28;
                    gp.obj[gp.currentMap][i].temp = true;   //É uma variavel para poder identificar se a porta deve estar no mapa ou não (A porta só irá ficar no mapa quando o player estiver em batalha contra o boss)
                    gp.playSE(21);
                    break;
                }
            }

            //SEARCH A VACANT SLOT FOR THE DUMMY
            for(int i = 0; i < gp.npc[1].length; i++){
                if(gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }

            gp.player.drawing = false;

            scenePhase++;
        }
        if(scenePhase == 1){
            gp.player.worldY -= 2;
            if(gp.player.worldY < gp.titleSize * 16){   //Parar a camera na cordenada y = 16;
                scenePhase++;
            }
        }

        if(scenePhase == 2){
            //SEARCH THE BOSS
            for(int i = 0; i < gp.monster[1].length; i++){
                if(gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].name.equals(MON_SkeletonLord.monName)){
                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }
        if(scenePhase == 3){
            //THE BOSS SPEAKS
            gp.ui.drawDialogueScreen();
        }
        if(scenePhase == 4){
            //RETURN TO THE PLAYER

            //SEARCH THE DUMMY
            for(int i = 0; i < gp.npc[1].length; i++){
                if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)){
                    //RESTORE THE PLAYER POSITION
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                    gp.player.direction = gp.npc[gp.currentMap][i].direction;
                    //DELETE THE DUMMY
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }
            //START DRAWING THE PLAYER
            gp.player.drawing = true;
            
            //RESET
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;
            gp.playMusic(22);
        }
    }

    public void scene_ending(){
        if(scenePhase == 0){
            gp.stopMusic();
            gp.ui.npc = new OBJ_BlueHeart(gp);
            scenePhase++;
        }
        if(scenePhase == 1){
            //DISPLAY DIALOGUES
            gp.ui.drawDialogueScreen();
        }
        if(scenePhase == 2){
            //PLAY THE FANFARE
            gp.playSE(4);
            scenePhase++;
        }
        if(scenePhase == 3){
            //WAIT UNTIL THE SOUNDS EFFECT ENDS
            if(counterReached(200)){
                scenePhase++;
            }
        }
        if(scenePhase == 4){
            //THE SCREEN GETS DARKER
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            drawBlackBackground(alpha);
            if(alpha == 1f){
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 5){
            drawBlackBackground(1f);
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }

            String text = "After the fierce battle with the Skeleton Lord,\nthe adventurous finally found the legendary treasure.\nBut this is not the end of his journey.\nThe adventure has just begun.";
            drawString(alpha, 38f, 200, text, 70);
            if(counterReached(150)){
                gp.playMusic(0);
                scenePhase++;
            }
        }
        if(scenePhase == 6){
            drawBlackBackground(1f);
            drawString(1f, 120f, gp.screenHeight/2, "TESTE", 40);
            if(counterReached(100)){
                scenePhase++;
            }
        }

        if(scenePhase == 7){
            drawBlackBackground(1f);
            y = gp.screenHeight/2;
            drawString(1f, 38f, y, endCredit, 40);
            if(counterReached(100)){
                scenePhase++;
            }
        }

        if(scenePhase == 8){
            drawBlackBackground(1f);
            //SCROLLING THE CREDIT;
            y--;
            drawString(1f, 38f, y, endCredit, 40);
            if(counterReached(1000)){
                scenePhase++;
            }
        }

        if(scenePhase == 8){
            //RESET
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.titleState;
            gp.stopMusic();

        }
    }

    public boolean counterReached(int target){
        boolean counterReached = false;
        counter++;
        if(counter > target){
            counterReached = true;
            counter = 0;
        }

        return counterReached;
    }

    public void drawBlackBackground(float alpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));
        for(String line : text.split("\n")){
            int x = gp.ui.getXForCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
