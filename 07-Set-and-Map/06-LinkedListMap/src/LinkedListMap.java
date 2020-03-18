public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        K key;
        V value;
        Node next;
        Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
        @Override
        public String toString() {
            return key.toString() + ": " + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node(null, null, null);
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(K key) {
        Node current = dummyHead.next;
        while (current != null) {
            if (current.key.equals(key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
        } else {
            node.value = value;
        }
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + " does not exist!");
        }
        node.value = value;
    }

    @Override
    public V remove(K key) {

        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delete = prev.next;
            prev.next = delete.next;
            delete.next = null;
            size--;
            return delete.value;
        }

        return null;
    }

}
