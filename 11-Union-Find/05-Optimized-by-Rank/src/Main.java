public class Main {

    public static void main(String[] args) {

        UF uf = new UnionFind4(4);

        uf.unionElements(1, 3);
        uf.unionElements(2, 3);

        System.out.println(uf.isConnected(0, 3));
        System.out.println(uf.isConnected(1, 3));
    }

}
