import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    // 二分搜索树中的节点
    private class Node {

        // 元素值
        E e;

        // 左孩子
        Node left;

        // 右孩子
        Node right;

        Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    // 根节点
    private Node root;

    // 当前二分搜索树中的元素个数
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    // 获取树中的元素个数
    public int getSize() {
        return size;
    }

    // 返回树是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 向二分搜索树中添加元素 e
    public void add(E e) {

        // 在以 root 为根节点的二分搜索树中添加一个元素
        root = add(root, e);
    }

    // 我们想在以 node 为根节点的二分搜索树中添加一个元素
    // 可以在以 node -> left 为根节点的二分搜索树中添加元素 (规模更小的同等问题)
    // 或者在以 node -> right 为根节点的二分搜索树中添加元素 (规模更小的同等问题)

    // 此方法的宏观语义 (重点关注):
    // 向以 node 为根的二分搜索树中插入元素 e, 并返回该二分搜索树的根
    // 如果 node 为 null, 则插入元素 e 到根位置, 然后返回根
    // 如果 node 不为 null, 则插入元素 e 到 node 的左孩子或右孩子

    // 情况 1:
    // node=null  -> return new Node(e);
    //   null     ->  node (returned)
    // null null    null null

    // 情况 2:
    // node!=null -> return add(node.right, e);
    //   node     ->  node
    // left null   left null (return add(null, e) = 情况 1) -> (return add(right, e) = 情况 2 / 3)

    // 情况 3:
    // node!=null -> return add(node.right, e);
    //   node     ->  node
    // left right   left right (return add(right, e) = 情况 2 / 3)
    private Node add(Node node, E e) {

        // 递归终止
        // 当前传入的根节点为 null, 则返回当前新创建的根节点
        if (node == null) {
            // 元素个数加 1
            size++;
            return new Node(e);
        }

        // 递归调用 (更小的同等问题)
        // 添加元素 e 到当前节点的左孩子 / 右孩子 (子树) 中

        if (e.compareTo(node.e) < 0) {

            // 如果要添加的元素 e 小于当前节点的元素 e, 则添加到当前节点的左孩子 (子树) 中
            // 以 node.left 为根节点, 插入元素 e, 将返回的根节点作为当前 node 的新的左孩子
            node.left = add(node.left, e);

        } else if (e.compareTo(node.e) > 0) {

            // 如果要添加的元素 e 大于当前节点的元素 e, 则添加到当前节点的右孩子 (子树) 中
            // 以 node.left 为根节点, 插入元素 e, 将返回的根节点作为当前 node 的新的右孩子
            node.right = add(node.right, e);
        }

        // 返回当前根节点
        return node;
    }

    // 二分搜索树中是否包含元素 e
    public boolean contains(E e) {

        // 返回以 root 为根节点的二分搜索树中是否包含元素 e
        return contains(root, e);
    }

    // 此方法的宏观语义 (重点关注):
    // 判断以 node 为根的二分搜索树中是否包含元素 e
    private boolean contains(Node node, E e) {

        // 递归终止
        // node 为 null, 则不包含
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {

            // 递归终止
            // 如果元素 e 等于 node 的元素值, 则包含
            return true;

        } else if (e.compareTo(node.e) < 0) {

            // 递归调用 (规模更小的同等问题)
            // 如果元素 e 在 node 的左孩子中, 则返回以 node 的左孩子为根的二分搜索树中是否包含元素 e
            return contains(node.left, e);

        } else { // e.compareTo(node.e) > 0

            // 递归调用 (规模更小的同等问题)
            // 如果元素 e 在 node 的右孩子中, 则返回以 node 的右孩子为根的二分搜索树中是否包含元素 e
            return contains(node.right, e);
        }
    }

    // 二分搜索树的前序遍历
    public void preOrder() {
        // 前序遍历以 root 为根的二分搜索树
        preOrder(root);
    }

    // 前序遍历以 node 为根的二分搜索树
    private void preOrder(Node node) {

        // 递归终止
        if (node == null) {
            return;
        }

        // 访问当前节点 (前序)
        System.out.println(node.e);

        // 递归调用左孩子 (规模更小的同等问题)
        preOrder(node.left);

        // 递归调用右孩子 (规模更小的同等问题)
        preOrder(node.right);
    }

    // 二分搜索树的前序遍历 (非递归)
    // 使用栈实现 (建议画图帮助理解各个步骤的执行顺序)
    public void preOrderNotRecursion() {

        // 空树不做处理
        if (root == null) {
            return;
        }

        // 使用栈存储待访问的节点
        Stack<Node> stack = new Stack<>();

        // 首先将 root 入栈
        stack.push(root);

        // 循环处理栈中的节点 (从 root 开始, 依次出栈当前节点, 再入栈当前节点的右孩子, 左孩子)
        while (!stack.isEmpty()) {

            // 循环内逻辑为对当前节点的处理

            // 将最近要访问的节点出栈
            Node current = stack.pop();

            // 访问当前节点 (前序)
            System.out.println(current.e);

            // 之所以先将右孩子入栈, 是因为栈是先进后出, 如果要先访问左孩子, 后访问右孩子, 则需要先入栈右孩子, 后入栈左孩子

            // 如果右孩子不为 null, 则将右孩子入栈
            if (current.right != null) {
                stack.push(current.right);
            }

            // 如果左孩子不为 null, 则将左孩子入栈
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    // 二分搜索树的中序遍历
    public void inOrder() {
        // 中序遍历以 root 为根的二分搜索树
        inOrder(root);
    }

    // 中序遍历以 node 为根的二分搜索树
    private void inOrder(Node node) {

        // 递归终止
        if (node == null) {
            return;
        }

        // 递归调用左孩子 (规模更小的同等问题)
        inOrder(node.left);

        // 访问当前节点 (中序)
        System.out.println(node.e);

        // 递归调用右孩子 (规模更小的同等问题)
        inOrder(node.right);
    }

    // 二分搜索树的后序遍历
    public void postOrder() {
        // 后序遍历以 root 为根的二分搜索树
        postOrder(root);
    }

    // 后序遍历以 node 为根的二分搜索树
    private void postOrder(Node node) {

        // 递归终止
        if (node == null) {
            return;
        }

        // 递归调用左孩子 (规模更小的同等问题)
        postOrder(node.left);

        // 递归调用右孩子 (规模更小的同等问题)
        postOrder(node.right);

        // 访问当前节点 (后序)
        System.out.println(node.e);
    }

    // 二分搜索树的层序遍历 (广度优先遍历)
    // 使用队列实现 (建议画图帮助理解各个步骤的执行顺序)
    public void levelOrder() {

        // 空树不做处理
        if (root == null) {
            return;
        }

        // 使用栈存储待访问的节点
        Queue<Node> queue = new LinkedList<>();

        // 首先将 root 入队
        queue.add(root);

        while (!queue.isEmpty()) {

            // 循环内逻辑为对当前节点的处理

            // 将最近要访问的节点出队
            Node current = queue.remove();

            // 访问当前节点
            System.out.println(current.e);

            // 如果左孩子不为 null, 则将左孩子入队
            if (current.left != null) {
                queue.add(current.left);
            }

            // 如果右孩子不为 null, 则将右孩子入队
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    // 查找二分搜索树的最小元素
    public E minimum() {

        // 空树抛出异常
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }

        // 返回以 root 为根的二分搜索树的最小值所在的节点
        Node mini = minimum(root);
        return mini.e;
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

    // 查找二分搜索树的最大元素
    public E maximum() {

        // 空树抛出异常
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }

        // 返回以 root 为根的二分搜索树的最大值所在的节点
        Node maxi = maximum(root);
        return maxi.e;
    }

    // 返回以 node 为根的二分搜索树的最大值所在的节点
    private Node maximum(Node node) {

        // 递归终止
        if (node.right == null) {
            return node;
        }

        // 递归调用
        return maximum(node.right);
    }

    // 从二分搜索树中删除最小值所在节点, 返回最小值
    public E removeMin() {

        // 查找二分搜索树的最小元素
        E min = minimum();

        // 删除掉以 root 为根的二分搜索树中的最小节点, 返回删除节点后新的二分搜索树的根
        // 将新的根节点赋值到 root 引用
        root = removeMin(root);

        // 返回最小元素
        return min;
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

    // 从二分搜索树中删除最大值所在节点, 返回最大值
    public E removeMax() {

        // 查找二分搜索树的最大元素
        E max = maximum();

        // 删除掉以 root 为根的二分搜索树中的最大节点, 返回删除节点后新的二分搜索树的根
        // 将新的根节点赋值到 root 引用
        root = removeMax(root);

        // 返回最大元素
        return max;
    }

    // 删除掉以 node 为根的二分搜索树中的最大节点, 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {

        // 递归终止
        if (node.right == null) {

            // 删除当前节点, 返回删除后的新的二分搜索树的根 (新的根为左孩子, 即表示将原左孩子转移到了当前删除节点的位置, 即当前节点从树中被删除)

            Node leftNode = node.left;
            // 释放原 node 的引用
            node.left = null;
            // 元素个数减 1
            size--;
            // 删除后新的根为左节点
            return leftNode;
        }

        // 递归调用
        // 以 node.right 为根节点, 删除最大值节点, 将返回的根节点作为当前 node 的新的右孩子
        node.right = removeMax(node.right);

        // 返回当前节点
        return node;
    }

    // 从二分搜索树中删除元素为 e 的节点 (建议画图帮助理解各个步骤的执行顺序)
    public void remove(E e) {

        // 删除掉以 root 为根的二分搜索树中值为 e 的节点, 返回删除节点后新的二分搜索树的根
        // 将新的根节点赋值到 root 引用
        root = remove(root, e);
    }

    // 删除掉以 node 为根的二分搜索树中值为 e 的节点, 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {

        // 递归终止
        if (node == null) {
            return null;
        }

        // 递归终止
        if (e.compareTo(node.e) == 0) {

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
            // return successor;

            /* 方式一 (更直观的实现方式, 操作不同节点的引用来改变树结构)
            // b. 前驱节点策略的实现方式
            Node predecessor = maximum(node.left);
            predecessor.left = removeMax(node.left);
            predecessor.right = node.right;
            return predecessor;
            */

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
        }

        // 递归调用
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
        }
        return node;
    }

    // 删除掉以 node 为根的二分搜索树中值为 e 的节点, 返回删除节点后新的二分搜索树的根
    private Node remove_backup(Node node, E e) {
        // 递归终止
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            // 递归调用
            node.left = remove_backup(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            // 递归调用
            node.right = remove_backup(node.right, e);
            return node;
        } else { // e.compareTo(node.e) == 0
            // 递归终止
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            return successor;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BST{\n");
        BSTToString(root, 0, builder);
        builder.append("}");
        return builder.toString();
    }

    private void BSTToString(Node node, int depth, StringBuilder builder) {
        // 递归终止
        if (node == null) {
            builder.append(toDepthString(depth)).append("null\n");
            return;
        }
        builder.append(toDepthString(depth)).append(node.e).append("\n");
        // 递归调用 (规模更小的同等问题)
        BSTToString(node.left, depth + 1, builder);
        BSTToString(node.right, depth + 1, builder);
    }

    private String toDepthString(int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("- ");
        }
        return builder.toString();
    }

}
