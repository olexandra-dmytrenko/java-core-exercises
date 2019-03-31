package bank;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MyBankTest {

    @Test
    public void transfer() {
        MyBank bank = new MyBank();

        Account account1 = new Account();
        account1.id = 1;
        account1.deposit(300000);
        Account account2 = new Account();
        account1.id = 2;
        account1.deposit(500000);
        bank.accounts = Arrays.asList(account1, account2);

        int totalBefore = bank.total();

        //IntStream.range(1, 300000).parallel().forEach((a) -> bank.transfer(account1, account2, 1));

        assertEquals(totalBefore, bank.total());
    }

    @Test
    public void deadLock() throws InterruptedException {
        MyBank bank = new MyBank();
        Account account1 = new Account();
        account1.id = 1;
        account1.deposit(3000);
        Account account2 = new Account();
        account1.id = 2;
        account1.deposit(1000);
        bank.accounts = Arrays.asList(account1, account2);

        int totalBefore = bank.total();

        new Thread(() ->
        {
            for (int i = 0; i < 1000; i++) {
                //System.out.println("1 -> 2");
                bank.transfer(account1, account2, 1);
            }
        }
        ).start();

        new Thread(() ->
        {
            for (int i = 0; i < 10000; i++) {
                //System.out.println("1 -> 2 -> 1");
                bank.transfer(account2, account1, 1);
            }
        }
        ).start();

        Thread.sleep(10000);
        System.out.println("Bank total should be " + bank.total());
        assertEquals(totalBefore, bank.total());
    }
}