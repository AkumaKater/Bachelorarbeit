package MNISTReader;

import CNN.data.Image;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MnistImageReader {

    public List<Image> readData(String dataFilePath, String labelFilePath) {
        String dataHome = "src/MNIST/archive/";// train-labels.idx1-ubyte
        try {
            DataInputStream dataInputStream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(dataHome+dataFilePath)));
            int magicNumber = dataInputStream.readInt();
            int numberOfItems = dataInputStream.readInt();
            int nRows = dataInputStream.readInt();
            int nCols = dataInputStream.readInt();

            DataInputStream labelInputStream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(dataHome+labelFilePath)));
            int labelMagicNumber = labelInputStream.readInt();
            int numberOfLabels = labelInputStream.readInt();

            List<Image> images = new ArrayList<>();

            assert numberOfItems == numberOfLabels;

            for (int i = 0; i < numberOfItems; i++) {
                Image image;
                double[][] data = new double[nRows][nCols];
                for (int r = 0; r < nRows; r++) {
                    for (int c = 0; c < nCols; c++) {
                        data[r][c] = dataInputStream.readUnsignedByte();
                    }
                }
                image = new Image( data, labelInputStream.readUnsignedByte());
                images.add(image);
            }
            dataInputStream.close();
            labelInputStream.close();
            return images;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Image> getImages(String path){
        String dataHome = "src/MNIST/archive/";// train-labels.idx1-ubyte
        MnistMatrix[] mnistMatrix = new MnistDataReader().readData(dataHome + path, dataHome + "train-labels.idx1-ubyte");

        ArrayList<Image> output = new ArrayList<>();
        for(MnistMatrix mn : mnistMatrix){
            Image image = new Image(mn.getData(), mn.getLabel());
            output.add(image);
        }

        return output;
    }
}