package FFNetwork;

import MNISTReader.MnistMatrix;

public class NeuralNetwork {
    Layer[] layers;
    double learnRate;

    // Initialisierung
    public NeuralNetwork(double learnRate, int[] layerSizes) {
        layers = new Layer[layerSizes.length - 1];
        for (int i = 0; i < layers.length; i++) {
            layers[i] = new Layer(layerSizes[i], layerSizes[i + 1]);
        }
        this.learnRate = learnRate;
        Linklayers();
    }

    // Abfragen
    public double[] Querry(double[] inputs) {
        return layers[0].CalculateOutputs(inputs);
        /*for (Layer layer : layers) {
            inputs = layer.CalculateOutputs(inputs);
        }
        return inputs;
        */
    }

    // Den Fehler berechnen mit der Cost Funktion
    double Cost(MnistMatrix dataPoint) {
        double[] QuerryOutputs = Querry(dataPoint.getInputs());
        double[] Targets = dataPoint.getTargets();
        double cost = 0;
        for (int i = 0; i < Targets.length; i++) {
            double error = Targets[i] - QuerryOutputs[i];
            cost += error * error;
        }
        return cost;
    }

    // Training
    public void learn(MnistMatrix data) {
        UpdateAllGradients(data);
        ApplyAllGradients(this.learnRate);
        ClearAllGradients();
    }

    // Training über Batch
    public void learn(MnistMatrix[] batch) {
        for (MnistMatrix data : batch) {
            UpdateAllGradients(data);
        }
        // Ganz WIctig! Der Durchschnitt wir errechnet durch
        // LearnRate / BatchSize
        ApplyAllGradients(this.learnRate / batch.length);
        ClearAllGradients();
    }

    void UpdateAllGradients(MnistMatrix dataPoint) {
        Querry(dataPoint.getInputs());
        Layer outputLayer = layers[layers.length - 1];
        outputLayer.BackPropagation(dataPoint.getTargets());

        /* double[] nodeValues = outputLayer.CalculateOutputLayerNodeValues(dataPoint.getTargets());
        outputLayer.UpdateGradients(nodeValues);
        for (int index = layers.length - 2; index >= 0; index--) {
            Layer hiddenLayer = layers[index];
            nodeValues = hiddenLayer.CalculateHiddenLayerNodeValues(layers[index + 1], nodeValues);
            hiddenLayer.UpdateGradients(nodeValues);
        } */
    }

    private void ApplyAllGradients(double learnrate) {
        for (Layer layer : layers) {
            layer.ApplyGradient(learnrate);
        }
    }

    private void ClearAllGradients() {
        for (Layer layer : layers) {
            layer.ClearGradient();
        }
    }

    public void Linklayers(){
        if(layers.length<=1){
            return;
        }
        for(int i=1;i<(layers.length-1);i++){
            layers[i].nextLayer = layers[i+1];
            layers[i].previousLayer = layers[i-1];
        }
        layers[0].nextLayer = layers[1];
        layers[layers.length-1].previousLayer = layers[layers.length-2];
    }
}