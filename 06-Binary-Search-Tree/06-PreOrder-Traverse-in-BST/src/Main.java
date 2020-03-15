public class Main {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
        int[] numbers = {5, 3, 6, 8, 4, 2};
        for (int number : numbers) {
            bst.add(number);
        }
        bst.preOrder();

        System.out.println("------");

        System.out.println(bst);

        /////////////////
        //      5      //
        //    /   \    //
        //   3     6   //
        //  / \     \  //
        // 2   4     8 //
        /////////////////
    }

}
