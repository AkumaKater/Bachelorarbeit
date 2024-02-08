package CNN;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConvolutionLayer {

    private long SEED;

    private List<double[][]> filters;
    private int filterSize;
    private int stepSize;

    private int inLength;
    private int inRows;
    private int inCols;

    public ConvolutionLayer(int filterSize, int stepSize, int inLength, int inRows, int inCols, long SEED,
            int numFilters) {
        this.filterSize = filterSize;
        this.stepSize = stepSize;
        this.inLength = inLength;
        this.inRows = inRows;
        this.inCols = inCols;
        this.SEED = SEED;

        generateRandomFilters(numFilters);
    }

    private void generateRandomFilters(int numFilters) {
        List<double[][]> filters = new ArrayList<>();
        Random random = new Random(SEED);

        for (int n = 0; n < numFilters; n++) {
            double[][] newFilter = new double[filterSize][filterSize];

            for (int i = 0; i < filterSize; i++) {
                for (int j = 0; j < filterSize; j++) {
                    double value = random.nextGaussian();
                    newFilter[i][j] = value;
                }
            }
            filters.add(newFilter);
        }
        this.filters = filters;
    }

    public List<double[][]> convolutionForwardPass(List<double[][]> list) {

        List<double[][]> output = new ArrayList<>();

        for (int m = 0; m < list.size(); m++) {
            for(double[][] filter : filters){
                output.add(convolve(list.get(m), filter, stepSize));
            }
        }
        return output;
    }

    private double[][] convolve(double[][] input, double[][] filter, int stepSize) {
        int outRows = (input.length - filter.length)/stepSize + 1;
        int outCols = (input[0].length - filter[0].length)/stepSize + 1;

        int inRows = input.length;
        int inCols = input[0].length;

        int fRows = filter.length;
        int fCols = filter[0].length;

        double[][] output = new double[outRows][outCols];

        int outRow = 0;
        int outCol;

        for(int i=0; i <=inRows - fRows; i+=stepSize){
            outCol = 0;
            for(int j=0; j<=inCols-fCols; j+=stepSize){

                double sum = 0.0;

                //Hier werden Die Filter Eingesetzt
                for(int x=0; x< fRows; x++){
                    for(int y=0; y<fCols; y++){
                        int inputRowIndex = i+x;
                        int inputColIndex = j+y;

                        double value = filter[x][y] * input[inputRowIndex][inputColIndex];
                        sum += value;
                    }
                }

                output[outRow][outCol] = sum;
                outCol++;
            }
            outRow++;
        }
        return output;
    }

    double[] getOutput(List<double[][]> input){
        List<double[][]> output = convolutionForwardPass(input);

        // Next layer wurde noch nicht implementiert
        return this.nextLayer.getOutput(output);
    }
    double[] getOutput(List<double[][]> input){
        List<double[][]> output = convolutionForwardPass(input);

        // Next layer wurde noch nicht implementiert
        return this.nextLayer.getOutput(output);
    }

    
}
