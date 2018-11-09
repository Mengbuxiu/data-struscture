package Array;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        Array a = new Array();
        for (int i = 0; i < 4; i++) {
            a.add(i, (int) (Math.random() * 10));
        }
        System.out.println(a.toString());
        a.addFirst(1);
        System.out.println(a.toString());
        a.addLast(111);
        System.out.println(a.toString());
        System.out.println(a.remove(6));
        System.out.println(a.toString());
    }
}
