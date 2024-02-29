package CNN;

import java.util.List;
import java.util.Random;

public class FullyConnectedByRae extends Layer {

    private Long SEED;

    double[][] weights;
    int inLength;
    int outLength;

    public FullyConnectedByRae(int inLength, int outLength, Long SEED) {
        this.inLength = inLength;
        this.outLength = outLength;
        this.SEED = SEED;

        weights = new double[inLength][outLength];
        setRandomWeights();
    }

    public double[] FullyConnectedForwardPass(double[] input){
        
    }

    @Override
    public double[] getOutput(List<double[][]> input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOutput'");
    }

    @Override
    public double[] getOutput(double[] input) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOutput'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOutputLength'");
    }

    @Override
    public int getOutputRows() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOutputRows'");
    }

    @Override
    public int getOutputCols() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOutputCols'");
    }

    @Override
    public int getOutputElements() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOutputElements'");
    }

    public void setRandomWeights(){
        Random random = new Random(SEED);

        for(int in=0; in<inLength; in++){
            for(int out=0; out<outLength; out++){
                weights[in][out] = random.nextGaussian();
            }
        }
    }
    
}
