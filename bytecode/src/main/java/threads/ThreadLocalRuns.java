package threads;

import java.util.Random;

/**
 * Может выполнятся перестановка команд в компеляторе, если он не видит связи меж ними. Но он не смотрит на другие потоки.
 * <p>
 * Happens-before
 * --------------
 * Потоки должны обменятся информацией. Если между потоками установлена зависимость Happens-before,
 * то поток увидит результат выполнения прошлого потока.
 * Для ряда операций это отношение нельзя ввести.
 * Можно переставлять волатайл переменную вниз, но перед ней нельзя менять порядок команд
 */
public class ThreadLocalRuns {
    public static void main(String[] args) {
        NamedRunnable one = new NamedRunnable();
        one.run();
        NamedRunnable two = new NamedRunnable();
        two.run();
        NamedRunnable three = new NamedRunnable();
        three.run();

        one.s.print();
        two.s.print();
        three.s.print();
    }


    static class Service {
        ThreadLocal<Integer> local = new ThreadLocal<>();

        public void put(int val) {
            local.set(val);
        }

        public void print() {
            System.out.println(local.get());
        }
    }


    static class Repo {
        ThreadLocal<Integer> local = new ThreadLocal<>();

        public void put(int val) {
            local.set(val);
        }

        public void print() {
            System.out.println(local.get());
        }
    }

    static class NamedRunnable implements Runnable {

        Service s = new Service();

        @Override
        public void run() {
            Random r = new Random();
            int i = r.nextInt(100);
            System.out.println(i);
            s.put(i);
        }
    }
}
