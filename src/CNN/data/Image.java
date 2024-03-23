package CNN.data;

import MNISTReader.MnistMatrix;

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
                if(data[i][j]>0){
                    s+= "@";
                }else{
                    s+= ".";
                }
            }
            s+= "\n";
        }

        return s;
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
