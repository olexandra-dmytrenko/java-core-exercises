package bank;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MyBank {

    List<Account> accounts;

    public void transfer(Account fromAcc, Account toAcc, int amount) {
//        deadLock(fromAcc, toAcc, amount);
        // deadLockResolved(fromAcc, toAcc, amount);
        deadLockResolvedBeautiful(fromAcc, toAcc, amount);
    }

    /**
     * 1) determine, which account is less (set the priority between which account has to be processed first
     * 2) perform the action with the given account
     * I have to input the info of
     * - 1 account
     * - result of Predicate, if it's less
     * - receive an action that should be performed with it
     * <p>
     * Combinations:
     * <p>
     * First less & First from
     * fromFirst
     * toSecond
     * <p>
     * First bigger & First from
     * toSecond
     * fromFirst
     * <p>
     * First less & First to
     * toFirst
     * fromSecond
     * <p>
     * First bigger & First to
     * fromSecond
     * toFirst
     *
     * @param fromAcc
     * @param toAcc
     * @param amount
     */
    private void deadLockResolved(Account fromAcc, Account toAcc, int amount) {
        Account lessA = null, biggerA = null;
        boolean firstAccLess = fromAcc.getAmount() < toAcc.getAmount();
        if (firstAccLess) {
            lessA = fromAcc;
            biggerA = toAcc;
        } else {
            lessA = toAcc;
            biggerA = fromAcc;
        }
        synchronized (lessA) {
            if (firstAccLess) {
                fromAcc.withdraw(amount);
                outputDeadLockInfo(fromAcc, toAcc, "No deadlock: From account ", "To account ");
                synchronized (biggerA) {
                    toAcc.deposit(amount);
                }
            } else {
                toAcc.deposit(amount);
                outputDeadLockInfo(fromAcc, toAcc, "No deadlock: From account ", ". To account ");
                synchronized (biggerA) {
                    fromAcc.withdraw(amount);
                }
            }
        }
    }

    private void outputDeadLockInfo(Account fromAcc, Account toAcc, String s, String s2) {
        System.out.println(s + fromAcc.id + " = " + fromAcc.amount
                + s2 + toAcc.id + " = " + toAcc.amount);
    }

    private void deadLock(Account fromAcc, Account toAcc, int amount) {
        synchronized (fromAcc) {
            if (!fromAcc.equals(toAcc)) {
                fromAcc.withdraw(amount);
                outputDeadLockInfo(fromAcc, toAcc, "Deadlock: From account ", ". To account ");
                synchronized (toAcc) {
                    toAcc.deposit(amount);
                }
            }
        }
    }

    private void deadLockResolvedBeautiful(Account fromAcc, Account toAcc, int amount) {
        final BiConsumer<Account, Integer> withdraw = Account::withdraw;
        final BiConsumer<Account, Integer> deposit = Account::deposit;

        final class AccountInfo {
            private final Account account;
            private final Consumer<Integer> action;

            private AccountInfo(Account account, BiConsumer<Account, Integer> action) {
                this.account = account;
                this.action = i -> action.accept(account, i);
            }
        }

        AccountInfo accountInfo1;
        AccountInfo accountInfo2;
        if (fromAcc.id < toAcc.id) {
            accountInfo1 = new AccountInfo(fromAcc, withdraw);
            accountInfo2 = new AccountInfo(toAcc, deposit);
        } else {
            accountInfo1 = new AccountInfo(toAcc, deposit);
            accountInfo2 = new AccountInfo(fromAcc, withdraw);
        }
        synchronized (accountInfo1.account) {
            accountInfo1.action.accept(amount);
            outputDeadLockInfo(fromAcc, toAcc, "No deadlock: From account ", ". To account ");
            synchronized ((accountInfo2.account)) {
                accountInfo2.action.accept(amount);
            }
        }
    }


    /**
     * sum of all accounts
     *
     * @return
     */
    public int total() {
        return accounts.stream().mapToInt(Account::getAmount).sum();
    }

    public static void main(String[] args) throws InterruptedException {
        MyBank bank = new MyBank();
        Account account1 = new Account();
        account1.id = 1;
        account1.deposit(3000);
        Account account2 = new Account();
        account1.id = 2;
        account1.deposit(1000);
        bank.accounts = Arrays.asList(account1, account2);

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
            for (int i = 0; i < 4000; i++) {
                //System.out.println("1 -> 2 -> 1");
                bank.transfer(account2, account1, 1);
            }
        }
        ).start();

        Thread.sleep(10000);
        System.out.println(bank.total());

    }
}
