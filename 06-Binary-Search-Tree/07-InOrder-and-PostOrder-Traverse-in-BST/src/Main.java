public class Main {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
        int[] numbers = {5, 3, 6, 8, 4, 2};
        for (int number : numbers) {
            bst.add(number);
        }

        /////////////////
        //      5      //
        //    /   \    //
        //   3     6   //
        //  / \     \  //
        // 2   4     8 //
        /////////////////

        // 前序遍历
        bst.preOrder();

        System.out.println("------");

        // 中序遍历 (结果为顺序排列)
        bst.inOrder();

        System.out.println("------");

        // 后序遍历
        bst.postOrder();
    }

}
