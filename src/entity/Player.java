package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle(8, 16, 15, 15);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        //Player status
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

        up1 = setup("/res/player/girlUp1");
        up2 = setup("/res/player/girlUp2");
        down1 = setup("/res/player/girlDown1");
        down2 = setup("/res/player/girlDown2");
        left1 = setup("/res/player/girlLeft1");
        left2 = setup("/res/player/girlLeft2");
        right1 = setup("/res/player/girlRight1");
        right2 = setup("/res/player/girlRight2");
    }
    public void update() {

        if(keyH.upPressed == true ||keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){

        if(keyH.upPressed == true){
            direction = "up";
        }
        else if(keyH.downPressed == true){
            direction = "down";
        }
        else if(keyH.leftPressed == true){
            direction = "left";
        }
        else if(keyH.rightPressed == true){
            direction = "right";
        }

        //check tile collsion
        collisionOn = false;
        gp.cChecker.checkTile(this);

        //check object collision
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        //check npc collison
        int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
        interactNPC(npcIndex);

        //check event
        gp.eHandler.checkEvent();

        gp.keyH.enterPressed = false;


        //if collision is false player can move
        if(collisionOn == false){
            switch(direction){
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
        if(spriteCounter > 11){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        }
    }

    public void pickUpObject(int i){
        if(i != 999){
        }

    }
    public void interactNPC(int i){
        if(i != 999){
            if(gp.keyH.enterPressed == true){
            gp.gameState = gp.dialogueState;
            gp.npc[i].speak();
            }
        }  
    }
    
    public void draw(Graphics2D g2) {
   
        BufferedImage  image = null;

        switch(direction){
            case "up":
            if(spriteNum == 1){
                image = up1;
            }
            if(spriteNum == 2){
                image = up2;
            }
            break;
            case "down":
            if(spriteNum == 1){
                image = down1;
            }
            if(spriteNum ==2){
                image = down2;
            }
            break;
            case "left":
            if(spriteNum == 1){
                image = left1;  
            }
            if(spriteNum == 2){
                image = left2;
            }
            break;
            case "right":
            if(spriteNum == 1){
                image = right1;
            }
            if(spriteNum == 2){
                image = right2;
            }
            break;
        }

        g2.drawImage(image, screenX, screenY, null);
    }
}
