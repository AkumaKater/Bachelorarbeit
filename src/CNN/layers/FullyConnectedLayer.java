package CNN.layers;

import FFNetwork.NNMath;

import java.util.List;

import FFNetwork.Activation;

public class FullyConnectedLayer extends Layer{

    int numInputNodes, numOutputNodes;
    double[][] weights;
    double[] bias;
    // --> Steigung der Cost Funktion im Bezug auf das Gewicht W
    double[][] CostSteigungW;
    // --> Steigung der Cost Funktion im Bezug auf die Biases b
    double[] CostSteigungB;

    double[] inputs;
    double[] weightedInputs;
    double[] activations;

    public FullyConnectedLayer(int numInputNodes, int numOutputNodes) {
        this.numInputNodes = numInputNodes;
        this.numOutputNodes = numOutputNodes;
        weights = NNMath.RandomDoubleArrayMatrix(numOutputNodes, numInputNodes);

        inputs = new double[numInputNodes];
        weightedInputs = new double[numOutputNodes];
        activations = new double[numOutputNodes];
        CostSteigungW = new double[numInputNodes][numOutputNodes];

        bias = NNMath.RandomDoubleArray(numOutputNodes);
        CostSteigungB = new double[numOutputNodes];
    }

    private double CostAbleitung(double activation, double expectedOutput) {
        return 2 * (activation - expectedOutput);
    }

    public double[] CalculateOutputLayerNodeValues(double[] expectedOutputs) {
        double[] nodeValues = new double[expectedOutputs.length];
        for (int i = 0; i < nodeValues.length; i++) {
            double costDerivative = CostAbleitung(activations[i], expectedOutputs[i]);
            double activationAbleitung = Activation.geActivation().ActivationAbleitung(weightedInputs[i]);
            nodeValues[i] = activationAbleitung * costDerivative;
        }
        return nodeValues;
    }

    public double[] CalculateHiddenLayerNodeValues(double[] nodeValues) {
        FullyConnectedLayer oldLayer = (FullyConnectedLayer)nextLayer;
        double[] newNodeValues = new double[numOutputNodes];
        Activation ac = Activation.geActivation();
        for (int i = 0; i < numOutputNodes; i++) {
            for (int j = 0; j < nodeValues.length; j++) {
                newNodeValues[i] += nodeValues[j] * oldLayer.weights[j][i];
            }
            newNodeValues[i] *= ac.ActivationAbleitung(weightedInputs[i]);
        }
        return newNodeValues;
    }

    public void UpdateGradients(double[] nodeValues) {
        for (int nodeOut = 0; nodeOut < numOutputNodes; nodeOut++) {
            for (int nodeIn = 0; nodeIn < numInputNodes; nodeIn++) {
                double derivativeCostWrtWeight = inputs[nodeIn] * nodeValues[nodeOut];
                CostSteigungW[nodeIn][nodeOut] += derivativeCostWrtWeight;
            }
            //Hier werden die Änderungsraten für die Biases gespeichert
            CostSteigungB[nodeOut] = 1 * nodeValues[nodeOut];
        }
    }

    public void ApplyGradient(double learnrate) {
        for (int i = 0; i < numOutputNodes; i++) {
            for (int j = 0; j < numInputNodes; j++) {
                weights[i][j] -= CostSteigungW[j][i] * learnrate;
            }
            //Die Änderungsraten der Biases müssen von den Biases abgezogen werden
            bias[i] -= CostSteigungB[i]*learnrate;
        }
    }

    public void ClearGradient() {
        this.CostSteigungW = new double[numInputNodes][numOutputNodes];
        //Die Änderungsraten für die Biases müssen auch zurückgesetzt werden
        this.CostSteigungB = new double[numOutputNodes];
    }

    @Override
    public double[] getOutput(List<double[][]> input) {
        return getOutput(matrixToVector(input));
    }

    @Override
    public double[] getOutput(double[] input) {
        this.inputs = input;
        Activation activ = Activation.geActivation();
        for (int nodeOut = 0; nodeOut < numOutputNodes; nodeOut++) {
            // double weightedInput = 0;
            // Wird nun mit dem Bias initialisiert
            double weightedInput = bias[nodeOut];
            for (int nodeIn = 0; nodeIn < numInputNodes; nodeIn++) {
                weightedInput += input[nodeIn] * weights[nodeOut][nodeIn];
            }
            weightedInputs[nodeOut] = weightedInput;
            activations[nodeOut] = activ.ActivationFunction(weightedInput);
        }
        return nextLayer.getOutput(activations);
    }

    public void backPropagation(double[] expectedOutputs) {
        double[] nodeValues = expectedOutputs;
        if (nextLayer == null) {
            nodeValues = CalculateOutputLayerNodeValues(expectedOutputs);
        } else {
            nodeValues = CalculateHiddenLayerNodeValues(nodeValues);

            UpdateGradients(nodeValues);
    
            if (previousLayer != null) {
                previousLayer.backPropagation(nodeValues);
            }
    
        }
    }

    @Override
    public void backPropagation(List<double[][]> dLdO) {
        backPropagation(matrixToVector(dLdO));
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
        return numInputNodes * numOutputNodes;
    }
}