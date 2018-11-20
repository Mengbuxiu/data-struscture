package trie;

import set.FileOperation;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
//        ArrayList<String> words = new ArrayList<>();
//        if(FileOperation.readFile("D:\\zhenglin\\work_soft\\idea_wp\\HandwritingCode\\src\\pride-and-prejudice.txt", words)) {
//            long startTime = System.nanoTime();

            Trie trie = new Trie();
            trie.add("I");
            trie.add("Love");
            trie.add("you");
            trie.add("yours");
            System.out.println(trie.contains("you"));
//            for (String word : words)
//                trie.add(word);
//
//            for (String word : words)
//                trie.contains(word);
//
//            long endTime = System.nanoTime();
//
//            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("Total words: " + trie.getSize());
        //    System.out.println("Trie: " + time + " s");
        }
   // }
}
