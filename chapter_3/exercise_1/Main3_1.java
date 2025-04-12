package chapter_3.exercise_1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main3_1 {

    private static AtomicInteger totalAmountBeautifulWord = new AtomicInteger();
    private static List<String> wordList = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        WordGenerator generator = new WordGenerator();

        wordList.addAll(generator.generatText(10000000, "avh"));
        System.out.println(wordList);

        EqualLetter equalLetter = new EqualLetter();
        Palindorm palindrom = new Palindorm();
        StartToEnd startToEnd = new StartToEnd();

        var startMultyTime = System.currentTimeMillis();

        equalLetter.run();
        palindrom.run();
        startToEnd.run();

        equalLetter.join();
        palindrom.join();
        startToEnd.join();
        System.out.println(totalAmountBeautifulWord);
        System.out.printf("TIME MYLTITHREADING: %d", System.currentTimeMillis() - startMultyTime);

        var pointsWord = 0;

        var startMonoTime = System.currentTimeMillis();
        for(String word : wordList){
            if (word.charAt(0) <= word.charAt(1) && word.charAt(1) <= word.charAt(2)) {
                pointsWord++;
            }
        }
        for(String word : wordList){
            if (word.charAt(0) == word.charAt(1) && word.charAt(1) == word.charAt(2)) {
                pointsWord++;
            }
        }
        for(String word : wordList){
            if (word.charAt(0) == word.charAt(2)) {
                pointsWord++;
            }
        }
        System.out.printf("\n\nTIME MONO: %d", System.currentTimeMillis() - startMonoTime);
    }

    static class EqualLetter extends Thread{
        @Override
        public void run() {
            for(String word : wordList){
                if (word.charAt(0) == word.charAt(1) && word.charAt(1) == word.charAt(2)) {
                    totalAmountBeautifulWord.incrementAndGet();
                }
                if (word.charAt(0) == word.charAt(2)) {
                    totalAmountBeautifulWord.incrementAndGet();
                }
                if (word.charAt(0) <= word.charAt(1) && word.charAt(1) <= word.charAt(2)) {
                    totalAmountBeautifulWord.incrementAndGet();
                }
            }
        }
    }

    static class Palindorm extends Thread{
        @Override
        public void run() {
            for(String word : wordList){
                if (word.charAt(0) == word.charAt(2)) {
                    totalAmountBeautifulWord.incrementAndGet();
                }
            }
        }
    }

    static class StartToEnd extends Thread{
        @Override
        public void run() {
            for(String word : wordList){
                if (word.charAt(0) <= word.charAt(1) && word.charAt(1) <= word.charAt(2)) {
                    totalAmountBeautifulWord.incrementAndGet();
                }
            }
        }
    }

}
