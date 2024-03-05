package CNN.layers;

import java.util.List;
import java.util.Random;

public class FullyConnectedByRae extends Layer {

    private Long SEED;

    double[][] weights;
    int inLength;
    int outLength;
    double learnRate;

    private double[] lastZ;
    private double[] lastInput;

    public FullyConnectedByRae(int inLength, int outLength, Long SEED, double learnRate) {
        this.inLength = inLength;
        this.outLength = outLength;
        this.SEED = SEED;
        this.learnRate = learnRate;

        weights = new double[inLength][outLength];
        setRandomWeights();
    }

    public double[] FullyConnectedForwardPass(double[] input){
        lastInput = input;
        double[] Z = new double[outLength];
        double[] a = new double[outLength];
        for(int i=0; i<inLength; i++){
            for(int j=0; j<outLength;j++){
                Z[j] += input[i]*weights[i][j];
            }
        }

        lastZ = Z;

        for(int i=0; i<inLength; i++){
            for(int j=0; j<outLength;j++){
                a[j] = Sigmoid(Z[j]);
            }
        }
        return a;
    }

    @Override
    public double[] getOutput(List<double[][]> input) {
        double[] vector = matrixToVector(input);
        return getOutput(vector);
    }

    @Override
    public double[] getOutput(double[] input) {
        double[] ForwardPass = FullyConnectedForwardPass(input);
        if(nextLayer != null){
            return nextLayer.getOutput(ForwardPass);
        }else{
            return ForwardPass;
        }
    }

    @Override
    public void backPropagation(double[] dLdO) {
        double[] dLdX = new double[inLength];
        double dOdZ;
        double dzdw;
        double dLdw;
        double dzdx;

        for(int k=0; k<inLength; k++){

            double dLdX_sum = 0;

            for(int j=0; j<outLength; j++){
                dOdZ = SigmoidAbleitung(lastZ[j]);
                dzdw = lastInput[k];
                dzdx = weights[k][j]; 

                dLdw = dLdO[j] * dOdZ * dzdw;

                weights[k][j] -= dLdw*learnRate;

                dLdX_sum += dLdO[j] * dOdZ * dzdx;
            }
            dLdX[k] = dLdX_sum;
        }
        if(previousLayer!= null){
            previousLayer.backPropagation(dLdX);
        }
    }

    @Override
    public void backPropagation(List<double[][]> dLdO) {
        double[] vector = matrixToVector(dLdO);
        backPropagation(vector);
    }

    @Override
    public int getOutputLength() {
        return 0;
    }

    @Override
    public int getOutputRows() {
        return 0;
    }

    @Override
    public int getOutputCols() {
        return 0;
    }

    @Override
    public int getOutputElements() {
        return outLength;
    }

    public void setRandomWeights(){
        Random random = new Random(SEED);

        for(int in=0; in<inLength; in++){
            for(int out=0; out<outLength; out++){
                weights[in][out] = random.nextGaussian();
            }
        }
    }

    //Die Sigmoid Funktion
    public double Sigmoid(double weightedInput) {
        return 1.0 / (1 + Math.exp(-weightedInput));
    }
    //Die Ableitung der Sigmoid Funktion
    public double SigmoidAbleitung(double weightedInput) {
        double activation = Sigmoid(weightedInput);
        return activation * (1.0 - activation);
    }
    
    public double ReLu(double weightedInput) {
        if (weightedInput <= 0)
        return 0.0;
    else
        return weightedInput;
    }
        
    public double ReLuAbleitung(double weightedInput) {
        if (weightedInput <= 0)
            return 0.01; //Leak Value, um Tote bereiche zu vermeiden. Vermutlich bei Sigmoid kein Problem
        else
            return 1.0;
    }
    
    
}
