package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[75];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/map02.txt");
    }

    public void getTileImage(){

        setUp(0, "mainGrass", false);
        setUp(1, "mainGrass", false);
        setUp(2, "mainGrass", false);
        setUp(3, "mainGrass", false);
        setUp(4, "mainGrass", false);
        setUp(5, "mainGrass", false);
        setUp(6, "mainGrass", false);
        setUp(7, "mainGrass", false);
        setUp(8, "mainGrass", false);
        setUp(9, "mainGrass", false);
        setUp(10, "mainGrass", false);
        setUp(11, "mainWater", true);
        setUp(12, "waterBE", true);
        setUp(13, "waterBLC", true);
        setUp(14, "waterBRC", true);
        setUp(15, "waterLE", true);
        setUp(16, "waterRE", true);
        setUp(17, "waterTE", true);
        setUp(18, "waterTLC", true);
        setUp(19, "waterTRC", true);
        setUp(20, "waterOCTL", true);
        setUp(22, "waterOCTR", true);
        setUp(23, "waterOCBL", true);
        setUp(24, "waterOCBR", true);
        setUp(25, "mainPath", false);
        setUp(26, "pathBE", false);
        setUp(27, "pathTE", false);
        setUp(28, "pathLE", false);
        setUp(29, "pathRE", false);
        setUp(30, "pathBLC", false);
        setUp(31, "pathBRC", false);
        setUp(32, "pathTLC", false);
        setUp(33, "pathTRC", false);
        setUp(34, "pathOCBL", false);
        setUp(35, "pathOCBR", false);
        setUp(36, "pathOCTR", false);
        setUp(37, "pathOCTL", false);
        setUp(38, "mainBrick", true);
        setUp(39, "floor1", false);
        setUp(40, "tree1", true);
        setUp(41, "accentGrass1", false);
        setUp(42, "accentGrass2", false);
        setUp(43, "pathREAG1", false);
        setUp(44, "pathTRCAG2", false);
        setUp(45, "pathTEAG1", false);
        setUp(46, "tree1AG2", true);
        setUp(47, "pathBEAG2", false);
        setUp(48, "pathLEAG2", false);
        setUp(49, "pathTLCAG2", false);
        setUp(50, "pathOCBLAG2", false);
        setUp(51, "waterAccent1", true);
    }

    public void setUp(int index, String imageName, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){
        try {
            
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));


            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception e) {
        }
    }

    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
