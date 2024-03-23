package MNISTReader;

import CNN.data.Image;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MnistDataReader {

    public MnistMatrix[] readData(String dataFilePath, String labelFilePath) {
        return readData(dataFilePath, labelFilePath, false);
    }

    public MnistMatrix[] readData(String dataFilePath, String labelFilePath, boolean Log) {

        try {
            DataInputStream dataInputStream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(dataFilePath)));
            int magicNumber = dataInputStream.readInt();
            int numberOfItems = dataInputStream.readInt();
            int nRows = dataInputStream.readInt();
            int nCols = dataInputStream.readInt();

            if (Log) {
                System.out.println("magic number is " + magicNumber);
                System.out.println("number of items is " + numberOfItems);
                System.out.println("number of rows is: " + nRows);
                System.out.println("number of cols is: " + nCols);
            }

            DataInputStream labelInputStream = new DataInputStream(
                    new BufferedInputStream(new FileInputStream(labelFilePath)));
            int labelMagicNumber = labelInputStream.readInt();
            int numberOfLabels = labelInputStream.readInt();

            if (Log) {
                System.out.println("labels magic number is: " + labelMagicNumber);
                System.out.println("number of labels is: " + numberOfLabels);
            }

            MnistMatrix[] data = new MnistMatrix[numberOfItems];

            assert numberOfItems == numberOfLabels;

            for (int i = 0; i < numberOfItems; i++) {
                MnistMatrix mnistMatrix = new MnistMatrix(nRows, nCols);
                mnistMatrix.setLabel(labelInputStream.readUnsignedByte());
                for (int r = 0; r < nRows; r++) {
                    for (int c = 0; c < nCols; c++) {
                        mnistMatrix.setValue(r, c, dataInputStream.readUnsignedByte());
                    }
                }
                data[i] = mnistMatrix;
            }
            dataInputStream.close();
            labelInputStream.close();
            return data;

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