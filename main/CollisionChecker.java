package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;    //Vai pegar a posição do jogador (Posição da área do player colisivo)
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.titleSize;
        int entityRightCol = entityRightWorldX/gp.titleSize;
        int entityTopRow = entityTopWorldY/gp.titleSize;
        int entityBottomRow = entityBottomWorldY/gp.titleSize;

        int titleNum1, titleNum2;

        //USE A TEMPORAL DIRECTION WHEN IT'S BEING KNOCKBACKED
        String direction = entity.direction;
        if(entity.knockBack == true){
            direction = entity.knockBackDirection;
        }


        switch(direction){ //Vai "prever" para onde o player irá caso aperte a tal tecla
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.titleSize;
                titleNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                titleNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[titleNum1].collision == true || gp.tileM.tile[titleNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.titleSize;
                titleNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                titleNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[titleNum1].collision == true || gp.tileM.tile[titleNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.titleSize;
                titleNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                titleNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[titleNum1].collision == true || gp.tileM.tile[titleNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.titleSize;
                titleNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                titleNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[titleNum1].collision == true || gp.tileM.tile[titleNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
    }
    
    public int checkObject(Entity entity, boolean player){ //Verifica se o player está "relando" no objeto
        int index = 999;

        //USE A TEMPORAL DIRECTION WHEN IT'S BEING KNOCKBACKED
        String direction = entity.direction;
        if(entity.knockBack == true){               //Serve para que o player não fique preso em um objeto caso leve kb 
            direction = entity.knockBackDirection;
        }

        for(int i = 0; i < gp.obj[1].length; i++){
            if(gp.obj[gp.currentMap][i] != null){
                //Posição do player
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Posição do objeto
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;
                switch(direction){ //Vai verificar para onde o player irá 
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){ //Verifica se o player e o objeto estão se colidindo
                    if(gp.obj[gp.currentMap][i].collision == true){
                        entity.collisionOn = true;
                    }
                    if(player){  //Esse if serve para que um outro personagem, como NPC ou MOB não consiga pegar os objetos
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    // COLISÃO ENTRE ENTIDADES
    public int checkEntity(Entity entity, Entity[][] target){
        int index = 999;

        //USE A TEMPORAL DIRECTION WHEN IT'S BEING KNOCKBACKED
        String direction = entity.direction;
        if(entity.knockBack == true){
            direction = entity.knockBackDirection;
        }

        for(int i = 0; i < target[1].length; i++){
            if(target[gp.currentMap][i] != null){
                //Posição do player
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //Posição do objeto
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                switch(direction){ //Vai verificar para onde o player irá 
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)){ //Verifica se o player e o objeto estão se colidindo
                    if(target[gp.currentMap][i] != entity){
                        entity.collisionOn = true;
                        index = i;  
                    }
                    
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity){     //Verificar colisão do npc para o player
        boolean contactPlayer = false;
        //Posição do player
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        //Posição do objeto
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch(entity.direction){ //Vai verificar para onde o player irá 
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }
        if(entity.solidArea.intersects(gp.player.solidArea)){ //Verifica se o player e o objeto estão se colidindo
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }
}
