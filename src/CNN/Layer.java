package CNN;

import java.util.List;

public abstract class Layer {
    
    protected Layer nextLayer;
    protected Layer previousLayer;

    public abstract double[] getOutput(List<double[][]> input);
    public abstract double[] getOutput(double[] input);

    public abstract void backPropagation(double[] dLdO);
    public abstract void backPropagation(List<double[][]> dLdO);
    
    public double[] matrixToVector(List<double[][]> input){
        int length = input.size();
        int rows = input.get(0).length;
        int cols = input.get(0)[0].length;
    }

}
