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

    public void addConvolutionLayer(int numFilters, int filterSize, int stepSize, double learnRate){
        if(layers.isEmpty()){
            layers.add(new ConvolutionLayer(filterSize, stepSize, 1, inputRows, inputCols, numFilters, learnRate));
        }else{
            Layer previousLayer = layers.get(layers.size()-1);
            layers.add(new ConvolutionLayer(filterSize, stepSize, previousLayer.getOutputLength(), previousLayer.getOutputRows(), previousLayer.getOutputCols(), numFilters, learnRate));
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

    public void addFullyConnectedLayer(int outLength, double learnRate ){
        if(layers.isEmpty()){
            layers.add(new FullyConnectedLayer(inputCols*inputRows, outLength, learnRate));
        }else{
            Layer previousLayer = layers.get(layers.size()-1);
            layers.add(new FullyConnectedLayer(previousLayer.getOutputElements(), outLength, learnRate));
        }
    }

    public void addFullyConnectedLayerOld(int outLength){
        //FullyConnectedLayer
        if(layers.isEmpty()){
            layers.add(new FullyConnectedLayer_Deprecated(inputCols*inputRows, outLength));
        }else{
            Layer previousLayer = layers.get(layers.size()-1);
            layers.add(new FullyConnectedLayer_Deprecated(previousLayer.getOutputElements(), outLength));
        }
    }

    public NeuralNetwork build(){
        nn = new NeuralNetwork(layers, scaleFactor);
        return nn;
    }
    
}
