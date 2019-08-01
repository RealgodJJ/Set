import java.util.ArrayList;

public class Main {

    private static double testSet(Set<String> set, String fileName) {
        double startTime = System.nanoTime();

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(fileName, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words)
                set.add(word);
            System.out.println("Total different words: " + set.getSize());
        }

        double endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words1 = new ArrayList<>();
        if (FileOperation.readFile("src/article/pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            BSTSet<String> set1 = new BSTSet<>();
            for (String word : words1)
                set1.add(word);
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();

        System.out.println("A Tale of Two Cities");
        ArrayList<String> words2 = new ArrayList<>();
        if (FileOperation.readFile("src/article/a-tale-of-two-cities.txt", words2)) {
            System.out.println("Total words: " + words2.size());

            LinkedListSet<String> set2 = new LinkedListSet<>();
            for (String word : words2)
                set2.add(word);
            System.out.println("Total different words: " + set2.getSize());
        }

        System.out.println("=======================");

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        BSTSet<String> bstSet = new BSTSet<>();
        double time1 = testSet(linkedListSet, "src/article/pride-and-prejudice.txt");
        System.out.println("linkListSet: " + time1 + "s");
        double time2 = testSet(bstSet, "src/article/pride-and-prejudice.txt");
        System.out.println("bstSet: " + time2 + "s");
    }
}
