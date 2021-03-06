public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    // 映射中的节点
    private class Node {

        // key
        K key;

        // value
        V value;

        // 左孩子
        Node left;

        // 右孩子
        Node right;

        Node (K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    // 根节点
    private Node root;

    // 当前映射中的元素个数 (当前二分搜索树中的元素个数)
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    // 映射元素的大小 (获取树中的元素个数)
    // 时间复杂度: O(1)
    @Override
    public int getSize() {
        return size;
    }

    // 映射是否为空 (返回树是否为空)
    // 时间复杂度: O(1)
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 映射中是否包含 key (二分搜索树中是否包含元素 (key - value))
    // 平均复杂度: O(h) = O(log n)
    // 最差复杂度: O(n)
    @Override
    public boolean contains(K key) {

        // 获取以 root 为根的二分搜索树中 key 对应的 Node, 如果 Node 不为 null 则存在, 否则不存在
        return getNode(root, key) != null;
    }

    // 获取映射中 key 对应的 value
    // 平均复杂度: O(h) = O(log n)
    // 最差复杂度: O(n)
    @Override
    public V get(K key) {

        // 获取以 root 为根的二分搜索树中 key 对应的 Node, 如果 Node 为 null 则返回 null, 否则返回 Node 的 value
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    // 修改映射中 key 对应的 value
    // 平均复杂度: O(h) = O(log n)
    // 最差复杂度: O(n)
    @Override
    public void set(K key, V value) {

        // 获取以 root 为根的二分搜索树中 key 对应的 Node
        Node node = getNode(root, key);

        // 如果 Node 为 null, 则无法修改, 抛出异常
        if (node == null) {
            throw new IllegalArgumentException(key + " does not exist!");
        }

        // 修改 Node 的 value
        node.value = value;
    }

    // 将元素 (key - value) 添加到映射中 (向二分搜索树中添加元素 (key - value)) (如果 key 已存在, 则修改 key 对应的 value)
    // 平均复杂度: O(h) = O(log n)
    // 最差复杂度: O(n)
    @Override
    public void add(K key, V value) {

        // 在以 root 为根节点的二分搜索树中添加一个元素 (key - value)
        root = add(root, key, value);
    }

    // 此方法的宏观语义 (重点关注):
    // 向以 node 为根的二分搜索树中插入元素 (key - value), 并返回该二分搜索树的根
    // 如果 node 为 null, 则插入元素 (key - value) 到根位置, 然后返回根
    // 如果 node 不为 null, 则插入元素 (key - value) 到 node 的左孩子或右孩子
    private Node add(Node node, K key, V value) {

        // 递归终止
        // 当前传入的根节点为 null, 则返回当前新创建的根节点
        if (node == null) {
            // 元素个数加 1
            size++;
            return new Node(key, value, null, null);
        }

        if (key.compareTo(node.key) == 0) {

            // 递归终止
            // 如果 key 等于 node 的 key, 则找到 Node, 修改 Node 的 value
            node.value = value;

        } else if (key.compareTo(node.key) < 0) {

            // 如果要添加的元素 key 小于当前节点的元素, 则添加到当前节点的左孩子 (子树) 中
            // 以 node.left 为根节点, 插入元素 (key - value), 将返回的根节点作为当前 node 的新的左孩子
            node.left = add(node.left, key, value);

        } else { // key.compareTo(node.key) > 0

            // 如果要添加的元素 key 小于当前节点的元素, 则添加到当前节点的右孩子 (子树) 中
            // 以 node.left 为根节点, 插入元素 (key - value), 将返回的根节点作为当前 node 的新的右孩子
            node.right = add(node.right, key, value);
        }

        // 返回当前根节点
        return node;
    }

    // 将 key 从映射中删除 (从二分搜索树中删除元素为 (key - value) 的节点)
    // 平均复杂度: O(h) = O(log n)
    // 最差复杂度: O(n)
    @Override
    public V remove(K key) {

        // 获取以 root 为根的二分搜索树中 key 对应的 Node, 如果 Node 为 null 则返回 null, 否则删除 Node 并返回 Node 的 value
        Node node = getNode(root, key);

        if (node == null) {
            return null;
        }

        // 删除以 root 为根的二分搜索树中 key 的节点, 返回删除节点后新的二分搜索树的根
        // 将新的根节点赋值到 root 引用
        root = remove(root, key);
        return node.value;
    }

    // 删除以 node 为根的二分搜索树中 key 的节点, 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, K key) {

        // 递归终止
        if (node == null) {
            return null;
        }

        // 递归终止
        if (key.compareTo(node.key) == 0) {

            // 1. 待删除节点的左孩子为 null (如果此时左右右孩子都为 null, 逻辑也兼容)
            // 删除当前节点, 返回删除后的新的二分搜索树的根 (新的根为右孩子, 即表示将原右孩子转移到了当前删除节点的位置, 即当前节点从树中被删除)
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 2. 待删除节点的右孩子为 null
            // 删除当前节点, 返回删除后的新的二分搜索树的根 (新的根为左孩子, 即表示将原左孩子转移到了当前删除节点的位置, 即当前节点从树中被删除)
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 3. 待删除节点的左右孩子都不为 null

            // 方式一 (更直观的实现方式, 操作不同节点的引用来改变树结构)
            // a. 后继节点策略的实现方式
            // 找到比待删除节点大的最小节点 (即待删除节点右子树的最小节点) (后继节点), 用这个节点顶替待删除节点的位置

            // 找到比待删除节点大的最小节点 (即待删除节点右子树的最小节点) (后继节点)
            Node successor = minimum(node.right);

            // 用这个节点顶替待删除节点的位置 (顶替右孩子)
            // 删除掉以 node.right 为根的二分搜索树中的最小节点, 返回删除节点后新的二分搜索树的根
            // 将此新的二分搜索树的根作为后继节点的右孩子 (即用这个后继节点顶替了待删除节点的位置)
            successor.right = removeMin(node.right);

            // removeMin(node.right) 逻辑删除了子树中的最小节点, 并修改了成员变量 size 的值 (size--)
            // 但 remove(node.right) 执行后, 并没有对当前树的数量产生影响 (被返回的删除节点继续挂接在原树上), 因此理论上需要还原 size 的值 (size++)
            // size++;

            // 用这个节点顶替待删除节点的位置 (顶替左孩子)
            successor.left = node.left;

            // 到此为止, 因为 node 节点已被删除 (被 successor 节点顶替), 因此理论上需要修改成员变量 size 的值 (size--)
            // size--;

            // 返回删除节点后新的二分搜索树的根
            return successor;

            /* 方式一 (更直观的实现方式, 操作不同节点的引用来改变树结构)
            // b. 前驱节点策略的实现方式
            Node predecessor = maximum(node.left);
            predecessor.left = removeMax(node.left);
            predecessor.right = node.right;
            return predecessor;
            */

            // ------

            /* 方式二: (更高效的实现方式, 直接修改被删除节点)
            // a. 后继节点策略的实现方式
            Node successor = removeMin(node.right);
            node.e = successor.e;
            node.right = successor;
            return node;
            // b. 前驱节点策略的实现方式
            Node predecessor = removeMax(node.left);
            node.e = predecessor.e;
            node.left = predecessor;
            return node;
             */

            // ------

            /* 方式三: (更简洁的实现方式, 直接修改被删除节点)
            // a. 后继节点策略的实现方式
            node.e = minimum(node.right).e;
            node.right = removeMin(node.right);
            return node;
            // b. 前驱节点策略的实现方式
            node.e = maximum(node.left).e;
            node.left = removeMax(node.left);
            return node;
             */

        } else if (key.compareTo(node.key) < 0) {

            // 如果要删除的元素 key 小于当前节点的元素, 则从当前节点的左孩子 (子树) 中删除
            // 递归调用
            // 以 node.left 为根节点, 删除元素 key, 将返回的根节点作为当前 node 的新的左孩子
            node.left = remove(node.left, key);
            // 返回当前根节点
            return node;

        } else { // key.compareTo(node.key) > 0

            // 如果要删除的元素 key 大于当前节点的元素, 则从当前节点的右孩子 (子树) 中删除
            // 递归调用
            // 以 node.right 为根节点, 删除元素 key, 将返回的根节点作为当前 node 的新的右孩子
            node.right = remove(node.right, key);
            // 返回当前根节点
            return node;
        }
    }

    // 获取 key 对应的 Node
    // 返回以 node 为根节点的二分搜索树中, key 所在的节点
    private Node getNode(Node node, K key) {

        // 递归终止
        // node 为 null, 则未找到 Node
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0) {

            // 递归终止
            // 如果 key 等于 node 的 key, 则找到 Node
            return node;

        } else if (key.compareTo(node.key) < 0) {

            // 递归调用
            // 如果 key 在 node 的左孩子中, 则返回以 node 的左孩子为根的二分搜索树中 key 所在的节点
            return getNode(node.left, key);

        } else { // key.compareTo(node.key) > 0

            // 递归调用
            // 如果 key 在 node 的右孩子中, 则返回以 node 的右孩子为根的二分搜索树中 key 所在的节点
            return getNode(node.right, key);
        }
    }

    // 返回以 node 为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {

        // 递归终止
        if (node.left == null) {
            return node;
        }

        // 递归调用
        return minimum(node.left);
    }

    // 删除掉以 node 为根的二分搜索树中的最小节点, 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {

        // 递归终止
        if (node.left == null) {

            // 删除当前节点, 返回删除后的新的二分搜索树的根 (新的根为右孩子, 即表示将原右孩子转移到了当前删除节点的位置, 即当前节点从树中被删除)

            Node rightNode = node.right;
            // 释放原 node 的引用
            node.right = null;
            // 元素个数减 1
            size--;
            // 删除后新的根为右节点
            return rightNode;
        }

        // 递归调用
        // 以 node.left 为根节点, 删除最小值节点, 将返回的根节点作为当前 node 的新的左孩子
        node.left = removeMin(node.left);

        // 返回当前节点
        return node;
    }

}
