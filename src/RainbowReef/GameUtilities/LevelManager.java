package RainbowReef.GameUtilities;

import RainbowReef.Blocks.Block;
import RainbowReef.Blocks.Block_Breakable;
import RainbowReef.Blocks.Block_Unbreakable;
import RainbowReef.Blocks.Block_Wall;
import RainbowReef.GameManager;
import RainbowReef.GameObjects.Bigleg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

public class LevelManager {
    int numBiglegs;
    int numLevels = 3;
    GameManager gameManager;
    public LevelManager(GameManager gameManager){
        this.gameManager = gameManager;
    }

    public ArrayList loadLevel(String mapName, String assetType){
        int number_biglegs = 0;

        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<Bigleg> biglegs = new ArrayList<>();
        ArrayList returnVal = null;
        try {
            InputStreamReader isr = new InputStreamReader(GameManager.class.getClassLoader().getResourceAsStream("maps/"+mapName));
            BufferedReader mapReader = new BufferedReader(isr);

            String row = mapReader.readLine();
            if(row == null){
                throw new IOException("no data for map file");
            }
            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for(int currRow = 0; currRow < numRows; currRow++){
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for(int currCol = 0; currCol < numCols; currCol++){
                    switch (mapInfo[currCol]){
                        case "1":
                            Block_Breakable block = new Block_Breakable(currCol*30,currRow*30,"violet",gameManager);
                            blocks.add(block);
                            break;
                        case "2":
                            Block_Breakable blockyellow = new Block_Breakable(currCol*30,currRow*30,"red",gameManager);
                            blocks.add(blockyellow);
                            break;
                        case "8":
                            Block_Unbreakable blockUnbreak = new Block_Unbreakable(currCol*30,currRow*30);
                            blocks.add(blockUnbreak);
                            break;
                        case "9":
                            Block_Wall wall = new Block_Wall(currCol*30,currRow*30);
                            blocks.add(wall);
                            break;
                        case "10":
                            Bigleg bigleg = new Bigleg(currCol*30,currRow*30,gameManager);
                            biglegs.add(bigleg);
                            number_biglegs++;
                            break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        switch (assetType){
            case "blocks":
                returnVal = blocks;
                break;
            case "biglegs":
                returnVal = biglegs;
                break;
        }
        this.numBiglegs = number_biglegs;
        return returnVal;
    }

    public int getNumBiglegs(){return this.numBiglegs;}
    public int getNumLevels(){return this.numLevels;}

}
