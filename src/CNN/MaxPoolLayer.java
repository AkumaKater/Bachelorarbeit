package CNN;

import java.util.ArrayList;
import java.util.List;

public class MaxPoolLayer extends Layer {

    private int stepSize;
    private int windowSize;

    private int inLength;
    private int inRows;
    private int inCols;

    List<int[][]> lastMaxRow;
    List<int[][]> lastMaxCol;

    public MaxPoolLayer(int stepSize, int windowSize, int inLength, int inRows, int inCols) {
        this.stepSize = stepSize;
        this.windowSize = windowSize;
        this.inLength = inLength;
        this.inRows = inRows;
        this.inCols = inCols;
    }

    public List<double[][]> maxPoolForwardPass(List<double[][]> input) {
        List<double[][]> output = new ArrayList<>();
        for (int l = 0; l < input.size(); l++) {
            output.add(pool(input.get(l)));
        }
        return output;
    }

    public double[][] pool(double[][] input) {
        double[][] output = new double[getOutputRows()][getOutputCols()];

        int[][] maxRows = new int[getOutputRows()][getOutputCols()];
        int[][] maxCols = new int[getOutputRows()][getOutputCols()];
        for (int r = 0; r < getOutputRows(); r += stepSize) {
            for (int c = 0; c < getOutputCols(); c += stepSize) {
                double max = 0.0;

                maxRows[r][c] = -1;
                maxCols[r][c] = -1;

                for (int x = 0; x < windowSize; x++) {
                    for (int y = 0; y < windowSize; y++) {
                        if (max < input[r + x][c + y]) {
                            max = input[r + x][c + y];
                            maxRows[r][c] = r + x;
                            maxCols[r][c] = c + y;
                        }
                    }
                }
                output[r][c] = max;
            }
        }
        lastMaxRow.add(maxRows);
        lastMaxCol.add(maxCols);
        return output;
    }

    @Override
    public double[] getOutput(List<double[][]> input) {
        List<double[][]> output = maxPoolForwardPass(input);
        return getNextLayer().getOutput(output);
    }

    @Override
    public double[] getOutput(double[] input) {
        return getOutput(vectorToMatrix(input, inLength, inRows, inCols));
    }

    @Override
    public void backPropagation(double[] dLdO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'backPropagation'");
    }

    @Override
    public void backPropagation(List<double[][]> dLdO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'backPropagation'");
    }

    @Override
    public int getOutputLength() {
        return inLength;
    }

    @Override
    public int getOutputRows() {
        return (inRows - windowSize) / stepSize + 1;
    }

    @Override
    public int getOutputCols() {
        return (inCols - windowSize) / stepSize + 1;
    }

    @Override
    public int getOutputElements() {
        return inLength * getOutputRows() * getOutputCols();
    }

}
