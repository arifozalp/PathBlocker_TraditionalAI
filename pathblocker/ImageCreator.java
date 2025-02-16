package aiProject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;
import javax.imageio.ImageIO;

public class ImageCreator {

    final int N = 16; 

    // Method to save the map as an png file 
    public void saveMapAsImage(String filename, int[][] baseMap, Stack<Position> visitedMap,Position endPos) {
        try {
            int cellSize = 10; 
            BufferedImage image = new BufferedImage(N * cellSize, N * cellSize, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();

            //The default map is being painted
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    Color color;
                    switch (baseMap[i][j]) {
                        case 1:
                            color = Color.BLACK; // Wall
                            break;
                        case 2:
                            color = Color.BLUE; // Starting Point
                            break;
                        case 3:
                            color = Color.RED; // Exit
                            break;
                        default:
                            color = Color.LIGHT_GRAY; // Empty Cell
                            break;
                    }
                    g2d.setColor(color);
                    g2d.fillRect(j * cellSize, i * cellSize, cellSize, cellSize); 
                }
            }

            //Here, the solution is painted using stack on the map and the image is saved.
            int counter = 0;
            g2d.setColor(Color.MAGENTA);
            for (Position pos : visitedMap) {
                if (pos.x == -1 && pos.y == -1) {
                    counter++;
                    makeImage(filename, counter, image);
                } else {
                    g2d.fillRect(pos.y * cellSize, pos.x * cellSize, cellSize, cellSize);
                }
            }
            g2d.setColor(Color.GREEN);
            g2d.fillRect(endPos.y * cellSize, endPos.x * cellSize, cellSize, cellSize);
            makeImage(filename, counter, image);
            g2d.dispose();
        } catch (Exception e) {
            System.out.println("Error saving the image: " + e.getMessage());
        }
    }

    // Method to create and save the PNG image 
    private void makeImage(String levelName, int counter, BufferedImage image) {
        try {
            File directory = new File(levelName);
            if (!directory.exists()) {
                directory.mkdirs(); 
            }
            ImageIO.write(image, "png", new File(levelName + "\\" + String.format("%04d", counter) + ".png"));
        } catch (IOException e) {
            System.out.println("Error creating image: " + e.getMessage());
        }
    }
}
