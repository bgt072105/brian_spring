package com.nighthawk.spring_portfolio.mvc.lightboard;

import lombok.Data;

@Data  // Annotations to simplify writing code (ie constructors, setters)
public class LightBoard {
    private Light[][] lights;

    /* Initialize LightBoard and Lights */
    public LightBoard(int numRows, int numCols) {
        this.lights = new Light[numRows][numCols];
        // 2D array nested loops, used for initialization
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                lights[row][col] = new Light();  // each cell needs to be constructed
            }
        }
    }

    public LightBoard(double[][][] rgbVals) {
        this.lights = new Light[rgbVals.length][rgbVals[0].length];
        // 2D array nested loops, used for initialization
        for (int row = 0; row < rgbVals.length; row++) {
            for (int col = 0; col < rgbVals[0].length; col++) {
                lights[row][col] = new Light(rgbVals[row][col][0],rgbVals[row][col][1],rgbVals[row][col][2],rgbVals[row][col][3]);  // each cell needs to be constructed
            }
        }
    }

    public void lightSwitch(int row, int col) {
        this.lights[row][col].lightSwitch();
    }

    /* Output is intended for API key/values */
    public String toString() { 
        String outString = "[";
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                outString += 
                // data
                "{" + 
                "\"row\": " + row + "," +
                "\"column\": " + col + "," +
                "\"light\": " + lights[row][col] +   // extract toString data
                "}," ;
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString = outString.substring(0,outString.length() - 1) + "]";
		return outString;
    }

    /* Output is intended for Terminal, effects added to output */
    public String toTerminal() { 
        String outString = "[";
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                outString += 
                // reset
                "\033[m" +
                
                // color
                "\033[38;2;" + 
                lights[row][col].getRed() + ";" +  // set color using getters
                lights[row][col].getGreen() + ";" +
                lights[row][col].getBlue() + ";" +
                lights[row][col].getEffect() + "m" +
                // data, extract custom getters
                "{" +
                "\"" + "RGB\": " + "\"" + lights[row][col].getRGB() + "\"" +
                "," +
                "\"" + "Effect\": " + "\"" + lights[row][col].getEffectTitle() + "\"" +
                "}," +
                // newline
                "\n" ;
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString = outString.substring(0,outString.length() - 2) + "\033[m" + "]";
		return outString;
    }

    /* Output is intended for Terminal, draws color palette */
    public String toColorPalette() {
        // block sizes
        final int ROWS = 5;
        final int COLS = 10;

        // Build large string for entire color palette
        String outString = "";
        // find each row
        for (int row = 0; row < lights.length; row++) {
            // repeat each row for block size
            for (int i = 0; i < ROWS; i++) {
                // find each column
                for (int col = 0; col < lights[row].length; col++) {
                    // repeat each column for block size
                    for (int j = 0; j < COLS; j++) {
                        // print single character, except at midpoint print color code
                        String c = (i == (int) (ROWS / 2) && j == (int) (COLS / 2) ) 
                            ? lights[row][col].getRGB()
                            : (j == (int) (COLS / 2))  // nested ternary
                            ? " ".repeat(lights[row][col].getRGB().length())
                            : " ";

                        outString += 
                        // reset
                        "\033[m" +
                        
                        // color
                        "\033[38;2;" + 
                        lights[row][col].getRed() + ";" +
                        lights[row][col].getGreen() + ";" +
                        lights[row][col].getBlue() + ";" +
                        "7m" +

                        // color code or blank character
                        c +

                        // reset
                        "\033[m";
                    }
                }
                outString += "\n";
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString += "\033[m";
		return outString;
    }
    
    public String toColorPalette(int ROWS, int COLS) {
        // Build large string for entire color palette
        String outString = "";
        // find each row
        for (int row = 0; row < lights.length; row++) {
            // repeat each row for block size
            for (int i = 0; i < ROWS; i++) {
                // find each column
                for (int col = 0; col < lights[row].length; col++) {
                    // repeat each column for block size
                    for (int j = 0; j < COLS; j++) {
                        // print single character, except at midpoint print color code
                        String c = (i == (int) (ROWS / 2) && j == (int) (COLS / 2) ) 
                            ? lights[row][col].getRGB()
                            : (j == (int) (COLS / 2))  // nested ternary
                            ? " ".repeat(lights[row][col].getRGB().length())
                            : " ";

                        outString += 
                        // reset
                        "\033[m" +
                        
                        // color
                        "\033[38;2;" + 
                        lights[row][col].getRed() + ";" +
                        lights[row][col].getGreen() + ";" +
                        lights[row][col].getBlue() + ";" +
                        "7m" +

                        // color code or blank character
                        c +

                        // reset
                        "\033[m";
                    }
                }
                outString += "\n";
            }
        }
        // remove last comma, newline, add square bracket, reset color
        outString += "\033[m";
		return outString;
    }

	/* Game of Life */
	public Light[][] simulateLife() {
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                // count neighbors
                int neighbors = 0;
                // check each neighbor
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        // skip self
                        if (i == 0 && j == 0) continue;
                        // check if neighbor is alive
                        if (lights[(row + i + lights.length) % lights.length][(col + j + lights[row].length) % lights[row].length].getOn()) {
                            neighbors++;
                        }
                    }
                }
                // apply rules
                /*
                * Any live cell with two or three live neighbours survives.
                Any dead cell with three live neighbours becomes a live cell.
                All other live cells die in the next generation. Similarly, all other dead cells stay dead.
                 * 
                 */
                if (lights[row][col].getOn()) {
                    if (neighbors < 2 || neighbors > 3) {
                        lights[row][col].setOn(false); // lambok method
                    }
                } else {
                    if (neighbors == 3) {
                        lights[row][col].setOn(true);
                    }
                }
            }
        }
        return lights;
    }

    // Display game of life with getOn as on/off
    public void displayLife() {
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                System.out.print(lights[row][col].getOn() ? "O" : "X");
            }
            System.out.println();
        }
    }

    // Display game of life with getOn as on/off
    public void displayLifeColor() {
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                if (lights[row][col].getOn()) {
                    System.out.print("\033[38;2;" + 
                    lights[row][col].getRed() + ";" +
                    lights[row][col].getGreen() + ";" +
                    lights[row][col].getBlue() + ";" +
                    "7m" +
                    "O" +
                    "\033[m");
                } else { // if not alive then don't print color
                    System.out.print("X");
                }
                // System.out.print(
                //     // reset
                //     "\033[m" +
                //     // color
                //     "\033[38;2;" + 
                //     lights[row][col].getRed() + ";" +
                //     lights[row][col].getGreen() + ";" +
                //     lights[row][col].getBlue() + ";" +
                //     "7m" +
                //     // data
                //     (lights[row][col].getOn() ? "O" : "X") +
                //     // reset
                //     "\033[m"
                // );
            }
            System.out.println();
        }
    }

    // next generation returns next generation of lights 
    public Light[][] nextGeneration() {
        // 2D array nested loops, used for reference
        for (int row = 0; row < lights.length; row++) {
            for (int col = 0; col < lights[row].length; col++) {
                // count neighbors
                int neighbors = 0;
                // check each neighbor
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        // skip self
                        if (i == 0 && j == 0) continue;
                        // check if neighbor is alive
                        if (lights[(row + i + lights.length) % lights.length][(col + j + lights[row].length) % lights[row].length].getOn()) {
                            neighbors++;
                        }
                    }
                }
                // apply rules
                /*
                * Any live cell with two or three live neighbours survives.
                Any dead cell with three live neighbours becomes a live cell.
                All other live cells die in the next generation. Similarly, all other dead cells stay dead.
                 * 
                 */
                if (lights[row][col].getOn()) {
                    if (neighbors < 2 || neighbors > 3) {
                        lights[row][col].setOn(false); // lambok method
                    }
                } else {
                    if (neighbors == 3) {
                        lights[row][col].setOn(true);
                    }
                }
            }
        }
        return lights;
    }

    static public void main(String[] args) {
        // create and display LightBoard
        LightBoard lightBoard = new LightBoard(5, 5);
        System.out.println(lightBoard);  // use toString() method
        System.out.println(lightBoard.toTerminal());
        System.out.println(lightBoard.toColorPalette());
        // create and display LightBoard
        LightBoard lightBoard2 = new LightBoard(6, 6);
        for (int i = 0; i < 6; i++) {
            lightBoard2.lightSwitch(2, i);
        }
        System.out.println(lightBoard2);  // use toString() method
        System.out.println(lightBoard2.toTerminal());
        System.out.println(lightBoard2.toColorPalette());
        // create and display Lightboard
        double[] light1 = {0, 0, 0, 0};
        double[] light2 = {0.1, 0.1, 0.1, 0.1};
        double[] light3 = {0.2, 0.2, 0.2, 0.2};
        double[] light4 = {0.3, 0.3, 0.3, 0.3};
        double[] light5 = {0.4, 0.4, 0.4, 0.4};
        double[] light6 = {0.5, 0.5, 0.5, 0.5};
        double[] light7 = {0.6, 0.6, 0.6, 0.6};
        double[] light8 = {0.7, 0.7, 0.7, 0.7};
        double[] light9 = {0.8, 0.8, 0.8, 0.8};
        double[][][] lights = {{light1, light2, light3}, {light4, light5, light6}, {light7, light8, light9}};
        LightBoard lightBoard3 = new LightBoard(lights);
        System.out.println(lightBoard3);  // use toString() method
        System.out.println(lightBoard3.toTerminal());
        System.out.println(lightBoard3.toColorPalette(10, 10));
    }
}
