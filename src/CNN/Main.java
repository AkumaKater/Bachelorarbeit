package CNN;

import java.util.List;

import CNN.data.Image;
import CNN.network.NetworkBuilder;
import CNN.network.NeuralNetwork;
import MNISTReader.MnistImageReader;

import static java.util.Collections.shuffle;

public class Main {

    public static void main(String[] args) {

        long SEED = 123;
        System.out.println("Starting Data Loading...");

        List<Image> TrainingsImages = new MnistImageReader().readData("train-images.idx3-ubyte",
                "train-labels.idx1-ubyte");
        List<Image> TestingImages = new MnistImageReader().readData("t10k-images.idx3-ubyte",
                "t10k-labels.idx1-ubyte");

        //List<Image> TrainingsImages = new MnistDataReader().getImages("train-images.idx3-ubyte");
        System.out.println("Trainings Data: "+ TrainingsImages.size());
        //List<Image> TestingImages = new MnistDataReader().getImages("t10k-images.idx3-ubyte");
        System.out.println("Test Data: "+ TestingImages.size());
        

        NetworkBuilder builder = new NetworkBuilder(28, 28, 256*100);
        builder.addConvolutionLayer(8, 5, 1, 0.1, SEED);
        builder.addMaxPoolLayer(2, 3);
        builder.addFullyConnectedLayer(100, 0.1, SEED);
        builder.addFullyConnectedLayer(10, 0.1, SEED);

        NeuralNetwork nn = builder.build();

        float rate = nn.test(TestingImages);
        System.out.println("Pre Training succes Rate: " + rate);

        int epochs = 3;

        for(int i = 0; i < epochs; i++){
            shuffle(TrainingsImages);
            nn.train(TrainingsImages);
            PredictSome(3, TrainingsImages, nn);
            rate = nn.test(TestingImages);
            System.out.println("Success rate after round " + (i+1) + ": " + rate);
        }

        System.out.println(nn.getLayers().get(0).toString());
    }

    public static void PredictSome(int iterations, List<Image> list, NeuralNetwork nn){
        for(int i=0; i< iterations; i++){
            //System.out.println(list.get(i).printMnistMatrix());
            System.out.println(list.get(i).toString());
            System.out.println("Prediction: "+nn.guess(list.get(i)));
        }
    }
}
