public class Main {

    public static void main(String[] args) {

        Integer[] numbers = {-2, 0, 3, -5, 2, -1};
        // SegmentTree<Integer> segmentTree = new SegmentTree<>(numbers, (e1, e2) -> e1 + e2);
        SegmentTree<Integer> segmentTree = new SegmentTree<>(numbers, Integer::sum);

        System.out.println(segmentTree);
        System.out.println(segmentTree.query(0, 2));
        System.out.println(segmentTree.query(0, 5));
        System.out.println(segmentTree.query(2, 5));

        segmentTree.set(1, 1);

        System.out.println(segmentTree);
        System.out.println(segmentTree.query(0, 2));
        System.out.println(segmentTree.query(0, 5));
        System.out.println(segmentTree.query(2, 5));
    }

}
