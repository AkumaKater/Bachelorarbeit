package CNN.layers;

import static CNN.data.MatrixUtility.multiply;
import static CNN.data.MatrixUtility.add;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConvolutionLayer extends Layer {

    private List<double[][]> filters;
    private int filterSize;
    private int stepSize;

    private int inLength;
    private int inRows;
    private int inCols;
    private double learnRate;

    private List<double[][]> lastInput;

    public ConvolutionLayer(int filterSize, int stepSize, int inLength, int inRows, int inCols, int numFilters, double learnRate) {
        this.filterSize = filterSize;
        this.stepSize = stepSize;
        this.inLength = inLength;
        this.inRows = inRows;
        this.inCols = inCols;
        this.learnRate = learnRate;

        generateRandomFilters(numFilters);
    }

    private void generateRandomFilters(int numFilters) {
        List<double[][]> filters = new ArrayList<>();
        Random random = new Random();

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
        lastInput = list;
        List<double[][]> featureMaps = new ArrayList<>();

        for (int m = 0; m < list.size(); m++) {
            for (double[][] filter : filters) {
                featureMaps.add(convolve(list.get(m), filter, stepSize));
            }
        }
        return featureMaps;
    }

    private double[][] convolve(double[][] input, double[][] filter, int stepSize) {
        int outRows = (input.length - filter.length) / stepSize + 1;
        int outCols = (input[0].length - filter[0].length) / stepSize + 1;

        double[][] featureMap = new double[outRows][outCols];

        int inRows = input.length;
        int inCols = input[0].length;

        int fRows = filter.length;
        int fCols = filter[0].length;

        int featureRow = 0;
        int featureCol;

        for (int i = 0; i <= inRows - fRows; i += stepSize) {
            featureCol = 0;
            for (int j = 0; j <= inCols - fCols; j += stepSize) {

                double sum = 0.0;

                // Hier werden Die Filter Eingesetzt
                for (int x = 0; x < fRows; x++) {
                    for (int y = 0; y < fCols; y++) {
                        int inputRowIndex = i + x;
                        int inputColIndex = j + y;

                        double value = filter[x][y] * input[inputRowIndex][inputColIndex];
                        sum += value;
                    }
                }

                featureMap[featureRow][featureCol] = sum;
                featureCol++;
            }
            featureRow++;
        }
        return featureMap;
    }

    public double[] getOutput(List<double[][]> input) {
        List<double[][]> output = convolutionForwardPass(input);

        return this.nextLayer.getOutput(output);
    }

    public double[] getOutput(double[] input) {
        List<double[][]> matrixinput = vectorToMatrix(input, inLength, inRows, inCols);

        return getOutput(matrixinput);
    }

    @Override
    public void backPropagation(double[] dLdO) {
        List<double[][]> matrixInput = vectorToMatrix(dLdO, inLength, inRows, inCols);
        backPropagation(matrixInput);
    }

    @Override
    public void backPropagation(List<double[][]> dLdO) {
        List<double[][]> filtersDelta = new ArrayList<>();
        List<double[][]> dLd0PreviousLayer = new ArrayList<>();
        for (int f = 0; f < filters.size(); f++) {
            filtersDelta.add(new double[filterSize][filterSize]);
        }

        for (int i = 0; i < lastInput.size(); i++) {
            double[][] errorForInput = new double[inRows][inCols];

            for (int f = 0; f < filters.size(); f++) {
                double[][] currentFilter = filters.get(f);
                double[][] error = dLdO.get(i * filters.size() + f);

                double[][] spacedError = spaceArray(error);
                double[][] dLdF = convolve(lastInput.get(i), spacedError, 1);

                double[][] delta = multiply(dLdF, learnRate * -1);
                double[][] newTotalDelta = add(filtersDelta.get(f), delta);
                filtersDelta.set(f, newTotalDelta);

                double[][] flippedError = flipArray(spacedError);
                errorForInput = add(errorForInput, fullConvolve(currentFilter, flippedError));

            }

            dLd0PreviousLayer.add(errorForInput);
        }
        for (int f = 0; f < filters.size(); f++) {
            double[][] modified = add(filtersDelta.get(f), filters.get(f));
            filters.set(f, modified);
        }

        if(previousLayer != null){
            previousLayer.backPropagation(dLd0PreviousLayer);
        }

    }

    public void backPropagationHalfe(List<double[][]> dLdO) {
        List<double[][]> filtersDelta = new ArrayList<>();
        for (int f = 0; f < filters.size(); f++) {
            filtersDelta.add(new double[filterSize][filterSize]);
        }

        for (int i = 0; i < lastInput.size(); i++) {
            for (int f = 0; f < filters.size(); f++) {
                double[][] error = dLdO.get(i * filters.size() + f);

                double[][] spacedError = spaceArray(error);
                double[][] dLdF = convolve(lastInput.get(i), spacedError, 1);

                double[][] delta = multiply(dLdF, learnRate * -1);
                double[][] newTotalDelta = add(filtersDelta.get(f), delta);
                filtersDelta.set(f, newTotalDelta);

            }
        }
        for (int f = 0; f < filters.size(); f++) {
            double[][] modified = add(filtersDelta.get(f), filters.get(f));
            filters.set(f, modified);
        }
    }

    @Override
    public int getOutputLength() {
        return filters.size() * inLength;
    }

    @Override
    public int getOutputRows() {
        return (inRows - filterSize) / stepSize + 1;
    }

    @Override
    public int getOutputCols() {
        return (inCols - filterSize) / stepSize + 1;
    }

    @Override
    public int getOutputElements() {
        return getOutputCols() * getOutputRows() * getOutputLength();
    }

    public double[][] spaceArray(double[][] input) {
        if (stepSize == 1) {
            return input;
        }

        int outRows = (input.length - 1) * stepSize + 1;
        int outCols = (input[0].length - 1) * stepSize + 1;

        double[][] output = new double[outRows][outCols];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length; j++) {
                output[i * stepSize][j * stepSize] = input[i][j];
            }
        }
        return output;
    }

    public double[][] flipArray(double[][] array) {
        return flipArrayHorizontal(flipArrayVertical(array));
    }

    public double[][] flipArrayHorizontal(double[][] array) {
        int rows = array.length;
        int cols = array[0].length;

        double[][] output = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                output[rows - i - 1][j] = array[i][j];
            }
        }
        return output;
    }

    public double[][] flipArrayVertical(double[][] array) {
        int rows = array.length;
        int cols = array[0].length;

        double[][] output = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                output[i][cols - j - 1] = array[i][j];
            }
        }
        return output;
    }

    private double[][] fullConvolve(double[][] currentFilter, double[][] flippedError) {
        int outRows = (currentFilter.length + flippedError.length);
        int outCols = (currentFilter[0].length + flippedError[0].length);

        int inRows = currentFilter.length;
        int inCols = currentFilter[0].length;

        int fRows = flippedError.length;
        int fCols = flippedError[0].length;

        double[][] output = new double[outRows][outCols];

        int outRow = 0;
        int outCol;

        for (int i = -fRows +1; i < inRows; i++) {
            outCol = 0;
            for (int j = -fCols +1; j < inCols; j++) {

                double sum = 0.0;

                // Hier werden Die Filter Eingesetzt
                for (int x = 0; x < fRows; x++) {
                    for (int y = 0; y < fCols; y++) {
                        int inputRowIndex = i + x;
                        int inputColIndex = j + y;

                        if (inputRowIndex >= 0 && inputColIndex >= 0 && inputRowIndex < inRows
                                && inputColIndex < inCols) {
                            double value = flippedError[x][y] * currentFilter[inputRowIndex][inputColIndex];
                            sum += value;
                        }

                    }
                }

                output[outRow][outCol] = sum;
                outCol++;
            }
            outRow++;
        }
        return output;
    }

    @Override
    public String toString() {
        //DecimalFormat df = new DecimalFormat("0.00");
        String out = "";
        for(double[][] filter : filters){
            for(double[] row : filter){
                for(double value : row){
                    if(value< -0.5){
                        out += "..";
                    }
                    if(value>= -0.5 && value <= 0.5){
                        out += "||";
                    }
                    if(value> 0.5){
                        out += "@@";
                    }
                }
                out += "\n";
            }
            out += "\n";
        }

        return out;
    }
}
