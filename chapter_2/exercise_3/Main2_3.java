package chapter_2.exercise_3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main2_3 {

    private static List<Integer> randArray = new ArrayList<>();
    private static BigInteger sum = BigInteger.ZERO;

    public static void main(String[] args) throws InterruptedException {
        //Initialisation
        Random randomizer = new Random();
        var streamRandomNumber = randomizer.ints(40000000, 0, 200);
        streamRandomNumber.forEach(x -> randArray.add(x));
        BigInteger summaMonoThread = BigInteger.ZERO;

        //One thread try
        var startMonoThreadTime = System.currentTimeMillis();
        for(int i = 0; i < randArray.size(); i++){
            summaMonoThread = summaMonoThread.add(BigInteger.valueOf(randArray.get(i)));
        }
        var avgMonoThread = summaMonoThread.divide(BigInteger.valueOf(100000));
        System.out.printf("Time: %d Avg: %d\n\n", System.currentTimeMillis() - startMonoThreadTime, avgMonoThread);

        //Multy thread try
        List<Thread> threadList = new ArrayList<>();
        for(int i = 0; i < randArray.size(); i += 10000){
            CalcSumThread th = new CalcSumThread(i, i + 9999);
            threadList.add(th);
        }
        var startMultyThreadTime = System.currentTimeMillis();
        for(Thread th: threadList){
            th.start();
        }
        for(Thread th: threadList){
            th.join();
        }
        var avgMultyThread = sum.divide(BigInteger.valueOf(100_000));
        System.out.printf("Time: %d Avg: %d", System.currentTimeMillis() - startMultyThreadTime, avgMultyThread);



    }

    public static synchronized void sumThread(BigInteger del){
        sum = sum.add(del);
    }

    static class CalcSumThread extends Thread{
        private int start;
        private int end;
        private BigInteger localSum = BigInteger.ZERO;

        public CalcSumThread(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for(int i = start; i <= end; i++){
                localSum = localSum.add(BigInteger.valueOf(randArray.get(i)));
            }
            Main2_3.sumThread(localSum);
        }
    }

}
