package ru.job4j.taskJMM;

/**
 * Created by Alexandr on 26.09.2017.
 */
public class TestVisibilityProblem {
    VisibilityProblem prb = new VisibilityProblem();
    public static void main(String[] args) {
        TestVisibilityProblem testVisibPrb = new TestVisibilityProblem();
        testVisibPrb.test();
    }

    Runnable one = () -> {
        String b = prb.getA().substring(1,2);
        prb.setA(prb.getA() + b);
        String bN = prb.getA().substring(prb.getA().length() - 1, prb.getA().length());
        prb.setA(prb.getA() + bN);
        prb.setNumb(prb.getNumb() + 1);
        prb.getItem().setLength(prb.getItem().getLength() + 1);
        prb.getItem().setSize(prb.getItem().getSize() + 1);
        System.out.printf("a = %s, Thread = %s%n", prb.getA(), Thread.currentThread().getName());
    };

    Runnable two = () -> {
        String c = prb.getA().substring(0,1);
        prb.setA(prb.getA() + c);
        String cN = prb.getA().substring(prb.getA().length() - 1, prb.getA().length());
        prb.setA(prb.getA() + cN);
        prb.setNumb(prb.getNumb() + 1);
        prb.getItem().setLength(prb.getItem().getLength() + 1);
        prb.getItem().setSize(prb.getItem().getSize() + 1);
        System.out.printf("a = %s, Thread = %s%n", prb.getA(), Thread.currentThread().getName());
    };

    public void test() {
        Thread t = new Thread(one);
        t.setName("one");
        Thread tr = new Thread(two);
        tr.setName("two");
        t.start();
        tr.start();
        try {
            t.join();
            tr.join();
        } catch (InterruptedException e) {
            t.interrupt();
        }
        System.out.println(prb.getA());
        System.out.println(prb.getNumb());
        System.out.println(prb.getItem().getLength());
        System.out.println(prb.getItem().getSize());
    }
}
