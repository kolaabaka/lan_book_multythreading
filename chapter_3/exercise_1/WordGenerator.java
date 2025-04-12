package chapter_3.exercise_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordGenerator {

    private Random randomizer;

    public WordGenerator(){};

    public List<String> generatText(int size, String exampleString) {
        List<String> wordList = new ArrayList<>();
        randomizer = new Random();
        for (int i = 0; i < size; i++) {
            wordList.add(createWord(randomizer, exampleString));
        }

        return wordList;
    }

    private String createWord(Random randomizer, String example) {
        StringBuilder stringBufer = new StringBuilder();
        for (int i = 0; i < example.length(); i++) {
            stringBufer.append(example.charAt(randomizer.nextInt(example.length())));
        }
        return stringBufer.toString();
    }

}
