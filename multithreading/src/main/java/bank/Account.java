package bank;

import java.util.Objects;

public class Account {
    int id;
    volatile int amount;


    public void deposit(int amount) {
        this.amount += amount;
    }

    public void withdraw(int amount) {
        this.amount -= amount;
    }

    int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, amount);
    }
}
