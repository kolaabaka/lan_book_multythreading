package chapter_4.exercise_1;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Main4_1 {

    private static ArrayBlockingQueue<String> queA = new ArrayBlockingQueue<>(1000);
    private static ArrayBlockingQueue<String> queB = new ArrayBlockingQueue<>(1000);
    private static ArrayBlockingQueue<String> queC = new ArrayBlockingQueue<>(1000);

    private static Integer CountA = Integer.valueOf(0);
    private static Integer CountB = Integer.valueOf(0);
    private static Integer CountC = Integer.valueOf(0);

    public static void main(String[] args) throws InterruptedException {

        ProducerString producer = new ProducerString();
        CounterA counterA = new CounterA();
        CounterB counterB = new CounterB();
        CounterC counterC = new CounterC();
        producer.run();
        counterA.run();
        counterB.run();
        counterC.run();
        counterA.join();
        counterB.join();
        counterC.join();
        producer.join();
        System.out.printf("\nA:%4s  B:%4s  C:%4s", CountA, CountB, CountC);
    }

    static class ProducerString extends Thread{
        @Override
        public void run() {
            char [] vocabulary = {'a','b','c','d','e','f','g','h','j','k','l','m','n','o','p','r','s','t'};
            Random randomizer = new Random();
            int amountWord;
            StringBuilder strBuilder = new StringBuilder();
            for(int i = 0; i < 1000; i++){
                amountWord = randomizer.nextInt(10);
                for(int j = 0; j < amountWord; j++){
                    strBuilder.append(vocabulary[randomizer.nextInt(vocabulary.length)]);
                }
                while (queA.size() == 1000){
                    continue;
                }
                queA.add(strBuilder.toString());
                queB.add(strBuilder.toString());
                queC.add(strBuilder.toString());
                System.out.println(strBuilder);
                strBuilder.setLength(0);
            }
        }
    }

    static class CounterA extends Thread{
        @Override
        public void run() {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String strA;
            do {
                strA = queA.poll();
                if(strA != null) {
                    for (int i = 0; i < strA.length(); i++) {
                        if (strA.charAt(i) == 'a') {
                            CountA++;
                        }
                    }
                }
            } while (strA != null);
        }
    }

    static class CounterB extends Thread{
        @Override
        public void run() {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String strB;
            do {
                strB = queB.poll();
                if(strB != null) {
                    for (int i = 0; i < strB.length(); i++) {
                        if (strB.charAt(i) == 'b') {
                            CountB++;
                        }
                    }
                }
            } while (strB != null);
        }
    }

    static class CounterC extends Thread{
        @Override
        public void run() {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String strC;
            do {
                strC = queC.poll();
                if(strC != null) {
                    for (int i = 0; i < strC.length(); i++) {
                        if (strC.charAt(i) == 'c') {
                            CountC++;
                        }
                    }
                }
            } while (strC != null);
        }
    }

}
