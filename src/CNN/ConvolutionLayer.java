package CNN;

import java.util.ArrayList;
import java.util.List;

public class ConvolutionLayer {
    
    private long SEED;

    private List<double[][]> filters;
    private int filterSize;
    private int stepSize;

    private int inLength;
    private int inRows;
    private int inCols;

    

    public ConvolutionLayer(int filterSize, int stepSize, int inLength, int inRows, int inCols, long SEED) {
        this.filterSize = filterSize;
        this.stepSize = stepSize;
        this.inLength = inLength;
        this.inRows = inRows;
        this.inCols = inCols;
        this.SEED = SEED;
    }



    public List<double[][]> convolutionForwardPass(List<double[][]> list){
        
        List<double[][]>output = new ArrayList<>();

        for (int m = 0; m < list.size(); m++){

        }
    }

}
