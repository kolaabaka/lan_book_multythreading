package chapter_2.exercise_1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main2_1{

    private static final List<Thread> threadPool = new ArrayList<>();

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++){
            CustomTrhead th  = new CustomTrhead();
            th.setName("#" + i);
            threadPool.add(th);
        }

        for (Thread th : threadPool){
            th.start();
        }
        try{
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e){

        }
        for (Thread th : threadPool){
            th.stop();
        }
    }

}