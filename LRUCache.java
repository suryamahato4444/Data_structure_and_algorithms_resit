//QUESTION-6A
package lang;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private Map<Integer, Node> map;
    private int capacity;
    private int count;
    private Node head;
    private Node tail;

    class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.count = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    private Node popTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node();
            node.key = key;
            node.value = value;
            map.put(key, node);
            addNode(node);
            count++;
            if (count > capacity) {
                Node tail = popTail();
                map.remove(tail.key);
                count--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
}
