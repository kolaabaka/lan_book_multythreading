package chapter_2.exercise_1;

public class CustomTrhead extends Thread{
    @Override
    public void run() {
        while(!isInterrupted()) {
            try {
                System.out.println("HELLO FROM " + Thread.currentThread().getName());
                sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " HAS BEEN KILLED");
                this.interrupt();
            }
        }
    }
}
