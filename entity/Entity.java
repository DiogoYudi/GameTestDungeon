package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Entity {
    GamePanel gp;

    public int worldX, worldY;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2, guardUp, guardDown, guardLeft, guardRight;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;

    //COLLISION
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    
    //DIALOGUE
    public int actionLockCounter = 0;
    public String dialogues[][] = new String[20][20];
    public int dialogueIndex = 0;
    public int dialogueSet = 0;

    //STATUS
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public Projectile projectile;
    public int speed;
    public int defaultSpeed;
    public boolean alive = true;
    public boolean dying = false;
    public boolean invincible = false;
    public String name;
    public boolean collision = false;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;

    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    public boolean attacking = false;
    int dyingCounter = 0;
    public boolean hpBarOn = false;
    public int hpBarCounter = 0;
    public boolean knockBack = false;
    int knockBackCounter = 0;
    public Entity attacker;
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false;
    public int guardCounter = 0;
    int offBalanceCounter = 0;
    public boolean offBalance = false;
    public boolean inRage = false;
    public boolean sleep = false;
    public boolean temp = false;
    public boolean drawing = true;

    public boolean onPath = false;
    public Entity loot;
    public boolean opened = false;

    public BufferedImage image, image2, image3;

    public Entity linkedEntity;

    public boolean boss;

    //TYPE
    public int type;
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;
    public final int type_pickaxe = 10;

    //ITEM ATTRIBUTE
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int value;
    public int price;
    public int knockBackPower = 0;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;

    public Entity(GamePanel gp){
        this.gp = gp;
    }

    public int getScreenX(){
        return worldX - gp.player.worldX + gp.player.screenX;
    }

    public int getScreenY(){
        return worldY - gp.player.worldY + gp.player.screenY;
    }

    public int getLeftX(){
        return worldX + solidArea.x;
    }

    public int getRightX(){
        return worldX + solidArea.x + solidArea.width;
    }

    public int getTopY(){
        return worldY + solidArea.y;
    }

    public int getBottomY(){
        return worldY + solidArea.y + solidArea.height;
    }

    public int getCol(){
        return (worldX + solidArea.x)/gp.titleSize;
    }

    public int getRow(){
        return (worldY + solidArea.y)/gp.titleSize;
    }

    public int getCenterX(){
        return worldX + left1.getWidth()/2;
    }

    public int getCenterY(){
        return worldY + up1.getHeight()/2;
    }

    public int getXDistance(Entity target){
        return Math.abs(getCenterX() - target.getCenterX());
    }

    public int getYDistance(Entity target){
        return Math.abs(getCenterY() - target.getCenterY());
    }

    public int getTileDistance(Entity target){
        return (getXDistance(target) + getYDistance(target))/gp.titleSize;
    }

    public int getGoalCol(Entity target){
        return (target.worldX + target.solidArea.x)/gp.titleSize;
    }

    public int getGoalRow(Entity target){
        return (target.worldY + target.solidArea.y)/gp.titleSize;
    }

    public void resetCounter(){
        spriteCounter = 0;
        actionLockCounter = 0;
        invincibleCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }

    public void setLoot(Entity loot){}
    public void setAction(){}
    public void move(String direction){}
    public void damageReaction(){}
    public void speak(){}

    public void facePlayer(){
        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void startDialogue(Entity entity, int setNum){
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }

    public void interact(){}
    
    public boolean use(Entity entity){
        return false;
    }
    public void checkDrop(){}
    public void dropItem(Entity droppedItem){
        for(int i = 0; i < gp.obj[1].length; i++){
            if(droppedItem == null) break;
            if(gp.obj[gp.currentMap][i] == null){
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;      //Local que o monstro morreu
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }

    public Color getParticleColor(){
        Color color = null;
        return color;
    }

    public int getParticleSize(){
        int size = 0;
        return size;
    }

    public int getParticleSpeed(){
        int speed = 0;
        return speed;
    }

    public int getParticleMaxLife(){
        int maxLife = 0;
        return maxLife;
    }

    public void generateParticle(Entity generator, Entity target){
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4); 
    }

    public void checkCollision(){
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this, gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(this.type == type_monster && contactPlayer == true){
            damagePlayer(attack);
        }
    }
    public void update(){
        if(!sleep){
            if(knockBack == true){
                checkCollision();
                if(collisionOn == true){
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }else if(collisionOn == false){
                    switch (knockBackDirection) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                    }
                }
                knockBackCounter++;
                if(knockBackCounter == 10){
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
            }else if(attacking == true){
                attacking();
            }else{
                setAction();
                checkCollision();
    
                if(collisionOn == false){
                    switch (direction) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                    }
                }
                spriteCounter++;
                if(spriteCounter > 10){
                    if(spriteNum == 1){
                        spriteNum = 2;
                    }else if(spriteNum == 2){
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }
    
            if(invincible == true){
                invincibleCounter++;
                if(invincibleCounter > 40){
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
            if(shotAvailableCounter < 30){
                shotAvailableCounter++;
            }
            if(offBalance == true){
                offBalanceCounter++;
                if(offBalanceCounter > 60){
                    offBalance = false;
                    offBalanceCounter = 0;
                }
            }
        }
    }

    public void checkAttackOrNot(int rate, int straight, int horizontal){
        boolean targetInRange = false;
        int xDis = getXDistance(gp.player);
        int yDis = getYDistance(gp.player);

        switch (direction) {
            case "up":
                if(gp.player.getCenterY() < getCenterY() && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "down":
                if(gp.player.getCenterY() > getCenterY() && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "left":
                if(gp.player.getCenterX() < getCenterX() && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "right":
                if(gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
        }
        if(targetInRange == true){
            //CHECK IF IT INITIATES AN ATTACK
            int i = new Random().nextInt(rate);
            if(i == 0){
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }

    public void checkShootOrNot(int rate, int shotInterval){
        int i = new Random().nextInt(rate);
        if(i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval){
            projectile.set(worldX, worldY, direction, true, this);
            //CHECK VACANCY
            for(int j = 0; j < gp.projectile[1].length; j++){   //Adicionar projetil na lista de projeteis
                if(gp.projectile[gp.currentMap][j] == null){
                    gp.projectile[gp.currentMap][j] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }

    public void checkStartChasingOrNot(Entity target, int distance, int rate){
        if(getTileDistance(target) < distance){
            int i = new Random().nextInt(rate);
            if(i == 0){
                onPath = true;
            }
        }
    }

    public void checkStopChasingOrNot(Entity target, int distance, int rate){
        if(getTileDistance(target) > distance){
            int i = new Random().nextInt(rate);
            if(i == 0){
                onPath = false;
            }
        }
    }

    public void getRandomDirection(int interval){
        actionLockCounter++;

        if(actionLockCounter > interval){               //Para que o npc não fique trocando de direção a cada frame (120frame = 2s)
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25){                            //É tipo uma IA, que vai escolher aleatoriamente o movimento do npc
                direction = "up";
            }if(i > 25 && i <= 50){
                direction = "down";
            }if(i > 50 && i <= 75){
                direction = "left";
            }if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;   
        }
    }

    public void moveTowardPlayer(int interval){
        actionLockCounter++;

        if(actionLockCounter > interval){
            if(getXDistance(gp.player) > getYDistance(gp.player)){
                if(gp.player.getCenterX() < getCenterX()){
                    direction = "left";
                }else{
                    direction = "right";
                }
            }else if(getXDistance(gp.player) < getYDistance(gp.player)){
                if(gp.player.getCenterY() < getCenterY()){
                    direction = "up";
                }else{
                    direction = "down";
                }
            }
            actionLockCounter = 0;
        }
    }

    public String getOppositeDirection(String direction){
        String oppositeDirection = "";
        switch (direction) {
            case "up":
                oppositeDirection = "down";
                break;
            case "down":
                oppositeDirection = "up";
                break;
            case "left":
                oppositeDirection = "right";
                break;
            case "right":
                oppositeDirection = "left";
                break;

        }
        return oppositeDirection;
    }

    public void attacking(){
        spriteCounter++;
        if(spriteCounter <= motion1_duration){     //Mostra a sprite do boy_attacking_1 nesses 5 frames
            spriteNum = 1;
        }
        if(spriteCounter > motion1_duration && spriteCounter <= motion2_duration){   //Mostra a sprite do boy_attacking_2 nesses 20 frames (6-25)
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {    //Attack area
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }
            //Attack area se torna a solidArea(Area de colisão)
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if(type == type_monster){
                if(gp.cChecker.checkPlayer(this) == true){
                    damagePlayer(attack);
                }
            }else{  //PLAYER
                //Checar colisão do ataque com o monstro
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }
            //Depois de verificar a colisão, retornar os valores ao original
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > motion2_duration){     //Finalizar a animação de ataque
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void damagePlayer(int attack){
        if(gp.player.invincible == false){
            int damage = attack - gp.player.defense;
            //GET AN OPPOSITE DIRECTION OF THIS ATTACKER
            String canGuardDirection = getOppositeDirection(direction);
            if(gp.player.guarding && gp.player.direction.equals(canGuardDirection)){
                //PARRY
                if(gp.player.guardCounter < 10){
                    damage = 0;
                    gp.playSE(16);
                    setKnockBack(this, gp.player, knockBackPower);
                    offBalance = true;
                    spriteCounter =- 60;
                }else{
                    damage /= 3;
                    gp.playSE(15);
                }
            }else{
                //NOT GUARDING
                gp.playSE(6);
                if(damage < 1){
                    damage = 1;
                }
            }

            if(damage != 0){
                gp.player.transparent = true;
                setKnockBack(gp.player, this, knockBackPower);
            }

            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }

    public void setKnockBack(Entity target, Entity attacker, int knockBackPower){
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }

    public boolean inCamera(){
        boolean inCamera = false;

        if(worldX + gp.titleSize*5 > gp.player.worldX - gp.player.screenX && //Essa condição é para que o while não gere os blocos que estão fora da range do player
            worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&  //Cap fique gerando, pode causar lag, por renderização de blocos não necessário
            worldY + gp.titleSize*5 > gp.player.worldY - gp.player.screenY && 
            worldY - gp.titleSize < gp.player.worldY + gp.player.screenY){
                inCamera = true;
        }
        return inCamera;
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        if(inCamera()){
                int tempScreenX = getScreenX();
                int tempScreenY = getScreenY();

                switch(direction){
                    case "up":
                        if(attacking == false){
                            if(spriteNum == 1) image = up1;
                            if(spriteNum == 2) image = up2;
                        }
                        if(attacking == true){
                            tempScreenY = getScreenY() - up1.getHeight();
                            if(spriteNum == 1) image = attackUp1;
                            if(spriteNum == 2) image = attackUp2;
                        }
                        break;
                    case "down":
                        if(attacking == false){
                            if(spriteNum == 1) image = down1;
                            if(spriteNum == 2) image = down2;
                        }
                        if(attacking == true){
                            if(spriteNum == 1) image = attackDown1;
                            if(spriteNum == 2) image = attackDown2;
                        }
                        break;
                    case "left":
                        if(attacking == false){
                            if(spriteNum == 1) image = left1;
                            if(spriteNum == 2) image = left2;
                        }
                        if(attacking == true){
                            tempScreenX = getScreenX() - left1.getWidth();
                            if(spriteNum == 1) image = attackLeft1;
                            if(spriteNum == 2) image = attackLeft2;
                        }
                        break;
                    case "right":
                        if(attacking == false){
                            if(spriteNum == 1) image = right1;
                            if(spriteNum == 2) image = right2;
                        }
                        if(attacking == true){
                            if(spriteNum == 1) image = attackRight1;
                            if(spriteNum == 2) image = attackRight2;
                        }
                        break;
                }

                if(invincible == true){
                    hpBarOn = true;
                    hpBarCounter = 0;
                    changeAlpha(g2, 0.4f);  //Deixar o slime um pouco mais transparente quando estiver em invencibilidade
                }
                if(dying == true){
                    dyingAnimation(g2);
                }
                
                g2.drawImage(image, tempScreenX, tempScreenY, null);

                changeAlpha(g2, 1F);
        }
    }

    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        int i = 5;
        if(dyingCounter <= i) changeAlpha(g2, 0f);
        if(dyingCounter > i && dyingCounter <= i*2) changeAlpha(g2, 1f);
        if(dyingCounter > i*2 && dyingCounter <= i*3) changeAlpha(g2, 0f);
        if(dyingCounter > i*3 && dyingCounter <= i*4) changeAlpha(g2, 1f);
        if(dyingCounter > i*4 && dyingCounter <= i*5) changeAlpha(g2, 0f);
        if(dyingCounter > i*5 && dyingCounter <= i*6) changeAlpha(g2, 1f);
        if(dyingCounter > i*6 && dyingCounter <= i*7) changeAlpha(g2, 0f);
        if(dyingCounter > i*7 && dyingCounter <= i*8) changeAlpha(g2, 1f);
        if(dyingCounter > i*8){
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
            image = uTool.scaleImage(image, width, height);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }

    public void searchPath(int goalCol, int goalRow){
        int startCol = (worldX + solidArea.x)/gp.titleSize;
        int startRow = (worldY + solidArea.y)/gp.titleSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);

        if(gp.pFinder.search() == true){
            //NEXT WORLDX & WORLDY
            int nextX = gp.pFinder.pathList.get(0).col * gp.titleSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.titleSize;

            //ENTITY SOLID AREA POSITION
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.titleSize){
                direction = "up";
            }else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.titleSize){
                direction = "down";
            }else if(enTopY >= nextY && enBottomY < nextY + gp.titleSize){
                //LEFT OR RIGHT
                if(enLeftX > nextX){
                    direction = "left";
                }
                if(enLeftX < nextX){
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                //UP OR LEFT
                direction = "up";
                checkCollision();
                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                //UP OR RIGHT
                direction = "up";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                //DOWN OR LEFT
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                //DOWN OR RIGHT
                direction = "down";
                checkCollision();
                if(collisionOn == true){
                    direction = "right";
                }
            }
            
            //IF REACHES THE GOAL, STOP THE SEARCH
            /*
            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if(nextCol == goalCol && nextRow == goalRow){
                onPath = false;
            }
            */
        }
    }

    public int getDetected(Entity user, Entity target[][], String targetName){
        int index = 999;

        //CHECK THE SURROUDING OBJECT
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch(user.direction){
            case "up":
                nextWorldY = user.getTopY()-gp.player.speed; break;
            case "down":
                nextWorldY = user.getBottomY()+gp.player.speed; break;
            case "left":
                nextWorldX = user.getLeftX()-gp.player.speed; break;
            case "right":
                nextWorldX = user.getRightX()+gp.player.speed; break;
        }

        int col = nextWorldX/gp.titleSize;
        int row = nextWorldY/gp.titleSize;

        for(int i = 0; i < target[1].length; i++){
            if(target[gp.currentMap][i] != null){
                if(target[gp.currentMap][i].getCol() == col && target[gp.currentMap][i].getRow() == row && target[gp.currentMap][i].name == targetName){
                    index = i;
                    break;
                }
            }
        }

        return index;
    }
}