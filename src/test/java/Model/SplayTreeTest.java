package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeTest {
    @Test
    void initTest(){
        SplayTree<Integer> tree = new SplayTree<Integer>();
        assertEquals(0, tree.size());
        assertFalse(tree.isEmpty());
    }
    @Test
    void find() {
        SplayTree<Integer> tree = new SplayTree<Integer>();
        tree.add(8);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        assertEquals(true, tree.find(3));
        assertEquals(false, tree.find(1));
        assertThrows(NullPointerException.class, () -> tree.find(null));
    }

    @Test
    void getTree() {
    }

    @Test
    void size() {
        SplayTree<Integer> tree = new SplayTree<Integer>();
        assertEquals(0, tree.size());
        tree.add(8);
        assertEquals(1, tree.size());
        tree.add(8);
        assertEquals(1, tree.size());
        tree.add(4);
        tree.add(5);
        tree.add(7);
        tree.add(10);
        tree.add(11);
        tree.add(12);
        tree.add(15);
        tree.add(13);
        assertEquals(9, tree.size());
        tree.remove(13);
        assertEquals(8, tree.size());
    }

    @Test
    void isEmpty() {
        SplayTree<Integer> tree = new SplayTree<Integer>();
        assertTrue(tree.isEmpty());
        tree.add(8);
        assertFalse(tree.isEmpty());
        tree.add(3);
        tree.add(4);
        tree.remove(8);
        tree.remove(3);
        tree.remove(4);
        assertTrue(tree.isEmpty());
    }

    @Test
    void contains() {
        SplayTree<Integer> tree = new SplayTree<Integer>();
        assertFalse( tree.contains(2));
        tree.add(2);
        assertTrue(tree.contains(2));
        tree.add(3);
        tree.add(4);
        assertTrue(tree.contains(3));
        assertThrows(ClassCastException.class, () -> tree.contains("g"));
        assertThrows(NullPointerException.class, () -> tree.contains(null));
    }

    @Test
    void iterator() {
    }

    @Test
    void toArray() {
        SplayTree<Integer> tree = new SplayTree<Integer>();
        int[] arr = {8,3,4,5,7,10};
        tree.add(8);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(7);
        tree.add(10);
        assertEquals(arr, tree.toArray());
        Object[] res = new Object[15];
        assertEquals(arr, tree.toArray(res));
        assertEquals(arr, tree.add(1));
    }

    @Test
    void add() {
        SplayTree<Integer> tree = new SplayTree<Integer>();
        assertEquals(true, tree.add(1));
        assertEquals(true, tree.find(1));
        assertEquals(true, tree.add(1));
        assertThrows(ClassCastException.class, () -> tree.add("g"));
        assertThrows(NullPointerException.class, () -> tree.add(null));
    }

    @Test
    void remove() {
    }

    @Test
    void addAll() {
    }

    @Test
    void clear() {
    }

    @Test
    void removeAll() {
    }

    @Test
    void retainAll() {
    }

    @Test
    void containsAll() {
    }

    @Test
    void testToArray() {
    }
}