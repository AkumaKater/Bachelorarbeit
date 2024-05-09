package CNN.data;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Image {
    

    private double [][] data;

    private int nRows;
    private int nCols;

    private int label;
    public Image(double[][] data, int label) {
        this.data = data;
        this.label = label;
        this.nRows = data.length;
        this.nCols = data[0].length;

        data = new double[nRows][nCols];
    }
    public int getLabel() {
        return label;
    }
    public void setLabel(int label) {
        this.label = label;
    }
    public int getNumberOfRows() {
        return nRows;
    }

    public int getNumberOfColumns() {
      return nCols;
    }

    public double[][] getData() {
        return data;
    }
    public void setData(double[][] data) {
        this.data = data;
    }

    @Override
    public String toString(){

        String s = "Label: " + label + ", \n";

        for(int i =0; i < data.length; i++){
            for(int j =0; j < data[0].length; j++){
                //s+= data[i][j] + ", ";
                if(data[i][j]>100){
                    s+= "@";
                }else{
                    s+= ".";
                }
            }
            s+= "\n";
        }

        return s;
    }

        public void saveAsImage(String filename) throws IOException {
        int width = nCols;
        int height = nRows;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixelValue = (int) data[i][j]; // Annahme: Die Werte sind zwischen 0 und 255
                Color color = new Color(pixelValue, pixelValue, pixelValue); // Graustufenfarbe
                image.setRGB(j, i, color.getRGB());
            }
        }

        g.dispose();
        File outputFile = new File(filename);
        ImageIO.write(image, "png", outputFile);
    }

    public String printMnistMatrix() {
        String[] Helligkeit = { " ", ".", ",", ":", ";", "_", "~", "^", "\"", ";", "-", "=", "*", "+", "#", "%",
        "@" };
        double part = 256.0 / (Helligkeit.length);
        String result = "";
        result +=("label: " + getLabel() + " ");
        for (int r = 0; r < getNumberOfRows() * 2 - 7; r++) {
            result +=("-");
        }
        result +="\n";
        for (int r = 0; r < getNumberOfRows(); r++) {
            result +=("|");
            for (int c = 0; c < getNumberOfColumns(); c++) {
                int hell = (int) (data[r][c] / part);
                // if(hell>16){hell=16;}
                result +=(Helligkeit[hell]);
                result +=(Helligkeit[hell]);
            }
            result +=("|");
            result +="\n";
        }
        for (int r = -1; r < getNumberOfRows(); r++) {
            result +=("--");
        }
        return result;
    }

}
