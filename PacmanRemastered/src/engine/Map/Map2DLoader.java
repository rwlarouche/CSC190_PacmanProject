/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

import engine.API;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


/**
 *
 * 
 */
public abstract class Map2DLoader{
    
    /**
     * This should implement a switch statement that will take the characters that were input into the system and
     * return the correct type of tile to input. It provides the current row and column that it's on just in case.
     * @param symbol
     * @param row
     * @param column
     * @return The corresponding map tile, or 
     */
   public abstract Map2DTile translateToTile(char symbol, int row, int column);
    
   public final API api; 
    
   /**
    * Uses the Map2DBuilder object to determine the expected length of each row and column.
    * All whitespace characters should be ignored and everything else passed to the getTileType and inserted in
    * the corresponding part of the 2D array. This can be done without having to read the whole line into a String.
    * @param b
    * @param stream Input file stream.
    * @return 
    */
    public Map2DTile[][] loadMap(Map2DBuilder b, InputStream stream) throws FileNotFoundException{
        
          File file = new File("map.txt");  
       
          Scanner mapScan = new Scanner(file);
          String [] size = mapScan.nextLine().split("\\s");
        
        char[][] array = new char[Integer.parseInt(size[0])][Integer.parseInt(size[1])];
        
        for(int i = 0; i < 4; i++){
            array[i] = mapScan.nextLine().toCharArray();
        }
        
        for(int k = 0; k < array.length; k++){
            for(int s = 0; s < array[k].length; s++){
                System.out.print(array[k][s]+" ");
            }
            System.out.println();
        }
        mapScan.close();
//            theString = sc.nextLine();
//            
//            while (mapScan.hasNextLine()) {
//                String line = mapScan.nextLine();
//                System.out.println(line);
//            }
//            mapScan.close();
        
        
//                char[] charArray = theString.toCharArray();
//                
//                for(int x = 0; x < charArray.length; x++){
//                    
//                    if(charArray[x] == 'w'){
//                        
//                        b.mapGrid[2][2] = new PacTileWall();
//                        
//                    }
//                    else if(charArray[x] == 'p'){
//                        b.mapGrid[0][0].add(new Pacman(this));
//                    }
//                    else if(charArray[x] == '.'){
//                        b.mapGrid[0][2].add(new PacPill(this));
//                    }
//                }}
//        
        throw new UnsupportedOperationException("Delete this after you finish writing the method."); //To change body of generated methods, choose Tools | Templates.
   }
    
    /**
     * Prompts the user to choose the file to load from, and closes when done.
     * @param b
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public Map2DTile[][] loadMap(Map2DBuilder b) throws FileNotFoundException, IOException{
        Map2DTile[][] returnTiles;
       try (InputStream IS = api.chooseFile("Select a level file to load....")) {
           returnTiles = loadMap(b,IS);
       }
        return returnTiles;
    }
    
    public Map2DLoader(API api){
        this.api = api;
    }
}
