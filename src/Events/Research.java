package Events;

import java.util.concurrent.TimeUnit;

public class Research implements Runnable{
    String name;
    static int benefits;
    static int moneySpends;

    @Override
    public void run() {

    }
    public static int getBenefits(int money){
        while (money>0) {
            try {
                TimeUnit.SECONDS.wait(10);
                System.out.printf("In progress...");
            } catch (InterruptedException e) {
                System.out.printf("Не хватает денег");
            }
            moneySpends--;
            benefits++;
        }
        return benefits;
    }
}
