public class Main {

    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
        }
        System.out.println(linkedList);

        System.out.println("------");

        linkedList.add(2, 666);
        System.out.println(linkedList);

        System.out.println("------");

        System.out.println(linkedList.get(2));

        System.out.println("------");

        System.out.println(linkedList.contains(666));
        System.out.println(linkedList.contains(999));

        System.out.println("------");

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }

}
