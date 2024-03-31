package CNN.network;

import static CNN.data.MatrixUtility.add;
import static CNN.data.MatrixUtility.multiply;

import java.util.ArrayList;
import java.util.List;

import CNN.data.Image;
import CNN.layers.Layer;

public class NeuralNetwork {

    List<Layer> layers;
    double scaleFactor;

    public NeuralNetwork(List<Layer> layers, double scaleFactor) {
        this.layers = layers;
        this.scaleFactor = scaleFactor;
        linkLayers();
    }

    public static int getHighestIndex(double[] numbers) {
        int maxNumberIndex = 0;

        // Schleife durch das Array laufen und die größte Zahl finden
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > numbers[maxNumberIndex]) {
                maxNumberIndex = i;
            }
        }
        if(maxNumberIndex>9){
            System.out.print(maxNumberIndex);
        }
        return maxNumberIndex;
    }

    private double[] getErrors(double[] networkOutput, int correctAnswer){
        int numClasses = networkOutput.length;
        double[] expected = new double[numClasses];
        expected[correctAnswer] = 1;
        return add(networkOutput, multiply(expected, -1));
    }

    public int guess(Image image){
        List<double[][]> inList = new ArrayList<>();
        inList.add(multiply(image.getData(), (1.0/scaleFactor)));
        double[] out = layers.get(0).getOutput(inList);
        int guess = getHighestIndex(out);
        
        

        return guess;
    }

    public void linkLayers(){
        if(layers.size()<=1){
            return;
        }
        for(int i=1;i<(layers.size()-1);i++){
            layers.get(i).setNextLayer(layers.get(i+1));
            layers.get(i).setPreviousLayer(layers.get(i-1));
        }
        layers.get(0).setNextLayer(layers.get(1));
        layers.get(layers.size()-1).setPreviousLayer(layers.get(layers.size()-2));
    }

    public float test (List<Image> images){
        int correct = 0;
        for(Image img : images){
            int guess = guess(img);
            if(guess == img.getLabel()){
                correct++;
            }
        }
        return ((float)correct/images.size());
    }

    public void train (List<Image> images){
        for(Image img : images){
            List<double[][]> inList = new ArrayList<>();
            inList.add(multiply(img.getData(), (1.0/scaleFactor)));
            double[] out = layers.get(0).getOutput(inList);
            double dCdO[] = getErrors(out, img.getLabel());

            layers.get((layers.size()-1)).backPropagation(dCdO);
        }
    }

    public List<Layer> getLayers() {
        return layers;
    }

}

class GuessIsNullException extends Exception{

}