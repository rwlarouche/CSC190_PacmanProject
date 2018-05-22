/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine.Map;

import engine.API;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


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
   
   
   private String lastMapLoaded;
   
   public String getLastLoadedString(){
        return lastMapLoaded;
   }
    
   public Map2DTile[][] reload(){
       return loadMap(new ByteArrayInputStream(lastMapLoaded.getBytes(StandardCharsets.UTF_8)));
   }
    
   public final API api; 
    
   /**
    * Uses the Map2DBuilder object to determine the expected length of each row and column.
    * All whitespace characters should be ignored and everything else passed to the getTileType and inserted in
    * the corresponding part of the 2D array. This can be done without having to read the whole line into a String.
    * @param stream Input file stream.
    * @return 
    */
    public Map2DTile[][] loadMap(InputStream stream){
//       try {
//           //My implementation begins here:
//           stream.mark(0);
//           int b = stream.read();
//           System.out.write(b);
//           stream.reset();
//           int avail = stream.available();
//           System.out.write(avail);
//       } catch (IOException ex) {
//           Logger.getLogger(Map2DLoader.class.getName()).log(Level.SEVERE, null, ex);
//       }
       
        try (Scanner fileScan = new Scanner(stream)){
            int mapWidth = fileScan.nextInt();
            int mapHeight = fileScan.nextInt();
            fileScan.skip("\\s");
            lastMapLoaded = mapWidth + "\n" + mapHeight;
            Map2DTile[][] mapGrid = new Map2DTile[mapHeight][mapWidth];
            for (int row = 0; row < mapHeight; row++){
                String oneLine = "";
                        do{
                            oneLine = fileScan.nextLine().chars().sequential().filter(c ->!Character.isWhitespace(c))
                        .limit(mapWidth).mapToObj(i -> (char)i).map(c -> c.toString()).collect(Collectors.joining(""));
                        } while (fileScan.hasNext() && oneLine.equals(""));
                for (int col = 0; col < mapWidth; col++){
                    mapGrid[row][col] = translateToTile(oneLine.charAt(col),row,col);
                }
                lastMapLoaded += "\n" + oneLine;
            }
            return mapGrid;
        }
    }      
        
//        String parsed = unparsed.chars().filter(c -> !Character.isWhitespace(c));
//        
//        Map2DTile[][] loadedGrid = new Map2DTile[mapWidth][mapHeight];
//        
//        try (Scanner mapScan = new Scanner(stream)) {
//
//
//
//
//
//            for (int row = 0; row < mapWidth; row++){
//                for (int col = 0; col < mapHeight; col++){
//                    
//                }
//            }
//        }   
           
//        try (Scanner mapScan = new Scanner(stream)) {  
           //Scott's begins here.
           
//           String [] size = mapScan.nextLine().split("\\s");
//           char[][] array = new char[Integer.parseInt(size[0])][Integer.parseInt(size[1])];
//           for(int i = 0; i < 30; i++){
//               array[i] = mapScan.nextLine().toCharArray();
//           }  for (int k = 0; k < array.length; k++) {
//               for(int s = 0; s < array[k].length; s++){
//                   System.out.print(array[k][s]+" ");
//               }
//               System.out.println();
//           }
//            theString = sc.nextLine();
//            
//            while (mapScan.hasNextLine()) {
//                String line = mapScan.nextLine();
//                System.out.println(line);
//            }
//            mapScan.close();
//       }
        
        
        //throw new UnsupportedOperationException("Delete this after you finish writing the method."); //To change body of generated methods, choose Tools | Templates.
       return null;
   }
    
    /**
     * Prompts the user to choose the file to load from, and closes when done.
     * @param b
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public Map2DTile[][] loadMap() throws FileNotFoundException, IOException{
        Map2DTile[][] returnTiles;
       try (InputStream IS = api.chooseFile("Select a level file to load....")) {
           returnTiles = loadMap(IS);
       }
        return returnTiles;
    }
    
    public Map2DLoader(API api){
        this.api = api;
        lastMapLoaded = "";
    }
}
