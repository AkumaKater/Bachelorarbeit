package CNN.network;

import java.util.ArrayList;
import java.util.List;

import CNN.layers.*;

public class NetworkBuilder {
    
    private NeuralNetwork nn;
    private int inputRows;
    private int inputCols;
    private double scaleFactor;
    List<Layer> layers;

    public NetworkBuilder(int inputRows, int inputCols, double scaleFactor){
        this.inputRows = inputRows;
        this.inputCols = inputCols;
        this.scaleFactor = scaleFactor;
        layers = new ArrayList<>();
    }

    public void addConvolutionLayer(int numFilters, int filterSize, int stepSize, double learnRate, long SEED){
        if(layers.isEmpty()){
            layers.add(new ConvolutionLayer(filterSize, stepSize, 1, inputRows, inputCols, SEED, numFilters, learnRate));
        }else{
            Layer previousLayer = layers.get(layers.size()-1);
            layers.add(new ConvolutionLayer(filterSize, stepSize, previousLayer.getOutputLength(), previousLayer.getOutputRows(), previousLayer.getOutputCols(), SEED, numFilters, learnRate));
        }
    }

    public void addMaxPoolLayer(int stepSize, int windowSize){
        if(layers.isEmpty()){
            layers.add(new MaxPoolLayer(stepSize , windowSize, 1, inputRows, inputCols));
        }else{
            Layer previousLayer = layers.get(layers.size()-1);
            layers.add(new MaxPoolLayer(stepSize , windowSize, previousLayer.getOutputLength(), previousLayer.getOutputRows(), previousLayer.getOutputCols()));
        }
    }

    public void addFullyConnectedLayer(int outLength, double learnRate, long SEED){
        if(layers.isEmpty()){
            layers.add(new FullyConnectedByRae(inputCols*inputRows, outLength, SEED, learnRate));
        }else{
            Layer previousLayer = layers.get(layers.size()-1);
            layers.add(new FullyConnectedByRae(previousLayer.getOutputElements(), outLength, SEED, learnRate));
        }
    }

    public NeuralNetwork build(){
        nn = new NeuralNetwork(layers, scaleFactor);
        return nn;
    }
    
}
