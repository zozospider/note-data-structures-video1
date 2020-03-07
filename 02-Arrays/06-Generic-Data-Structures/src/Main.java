public class Main {

    public static void main(String[] args) {

        Array<Integer> array = new Array<>(20);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);

        array.add(1, 100);
        System.out.println(array);

        array.addFirst(-1);
        System.out.println(array);

        array.remove(2);
        System.out.println(array);

        array.removeElement(4);
        System.out.println(array);

        array.removeFirst();
        System.out.println(array);

        System.out.println("------");

        Array<Student> studentArray = new Array<>(10);

        studentArray.addLast(new Student("Amy", 100));
        studentArray.addLast(new Student("Ford", 60));
        studentArray.addLast(new Student("Bob", 80));

        System.out.println(studentArray);
    }

}
