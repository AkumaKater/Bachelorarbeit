package CNN;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        long l = 8;
        for (int i = 0; i < 10; i++) {
            System.out.println(randomGenerator(l));
            System.out.println(randomGeneratorr(l));
        }
    }

    static double randomGenerator(long seed) {
        Random generator = new Random(seed);
        double num = generator.nextDouble() * (0.5);

        return num;
    }

    static double randomGeneratorr(long seed) {
        Random generator = new Random();
        double num = generator.nextDouble() * (0.5);

        return num;
    }
}