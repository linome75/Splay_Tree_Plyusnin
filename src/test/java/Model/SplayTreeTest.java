package Model;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeTest {
    @Test
    void initTest() {
        SplayTree<Integer> tree = new SplayTree<>();
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
    }


    @Test
    void size() {
        SplayTree<Integer> tree = new SplayTree<>();
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
        SplayTree<Integer> tree = new SplayTree<>();
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
        SplayTree<Integer> tree = new SplayTree<>();
        assertFalse(tree.contains(2));
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
        SplayTree<Integer> tree = new SplayTree<>();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        tree.addAll(list);
        Iterator<Integer> it = tree.iterator();
        int i = 0;
        Integer[] res = new Integer[tree.size()];
        while (it.hasNext()) {
            res[i] = it.next();
            i++;
        }
        assertArrayEquals(res, list.toArray());
        assertTrue(i == tree.size());
    }

    @Test
    void toArray() {
        SplayTree<Integer> tree = new SplayTree<>();
        Object[] arr = {10, 8, 7, 5, 4, 3};
        Object[] arr1 = {10, 8, 7, 5, 4, 3, null};
        tree.add(8);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        tree.add(7);
        tree.add(10);
        Object[] res = tree.toArray(new Object[7]);
        Object[] res1 = tree.toArray(new Object[1]);
        Object[] nullArray = null;
        assertArrayEquals(arr, res1);
        assertArrayEquals(arr1, res);
        assertArrayEquals(arr, tree.toArray());
        assertThrows(NullPointerException.class, () -> tree.toArray(nullArray));
    }

    @Test
    void add() {
        SplayTree<Integer> tree = new SplayTree<>();
        tree.add(1);
        tree.add(23);
        tree.add(5);
        assertTrue(tree.add(1));
        assertTrue(tree.contains(1));
        assertTrue(tree.add(1));
        assertThrows(ClassCastException.class, () -> tree.add("g"));
        assertThrows(NullPointerException.class, () -> tree.add(null));
    }

    @Test
    void remove() {
        SplayTree<Integer> tree = new SplayTree<>();
        tree.add(8);
        tree.add(3);
        tree.add(4);
        tree.add(12);
        tree.remove(4);
        assertFalse(tree.contains(4));
        tree.add(4);
        assertTrue(tree.contains(4));
        tree.remove(12);
        tree.remove(4);
        tree.remove(3);
        tree.remove(8);
        assertTrue(tree.isEmpty());
        assertThrows(NullPointerException.class, () -> tree.remove(null));
    }

    @Test
    void addAll() {
        SplayTree<Integer> tree = new SplayTree<>();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        tree.addAll(list);
        Object[] arr = {5, 4, 3, 2, 1};
        assertArrayEquals(arr, tree.toArray());
    }

    @Test
    void clear() {
        SplayTree<Integer> tree = new SplayTree<>();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        tree.addAll(list);
        assertFalse(tree.isEmpty());
        tree.clear();
        assertTrue(tree.isEmpty());
    }

    @Test
    void removeAll() {
        SplayTree<Integer> tree = new SplayTree<>();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> listToRemove = Arrays.asList(1, 2, 3);
        Object[] arr = {4, 5};
        tree.addAll(list);
        tree.removeAll(listToRemove);
        assertTrue(tree.size() == list.size() - listToRemove.size());
        assertArrayEquals(arr, tree.toArray());
    }

    @Test
    void retainAll() {
        SplayTree<Integer> tree = new SplayTree<>();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> listToRetain = Arrays.asList(1, 2, 3);
        Object[] arr = {3, 2, 1};
        tree.addAll(list);
        tree.retainAll(listToRetain);
        assertArrayEquals(arr, tree.toArray());
        assertFalse(tree.contains(4));
    }

    @Test
    void containsAll() {
        SplayTree<Integer> tree = new SplayTree<>();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> listToCheck = Arrays.asList(1, 2, 3);
        List<Integer> listToCheck1 = Arrays.asList(1, 2, 3, 8);
        tree.addAll(list);
        assertTrue(tree.containsAll(list));
        assertTrue(tree.containsAll(listToCheck));
        assertFalse(tree.containsAll(listToCheck1));
    }


    @Test
    void testRemove() {
        SplayTree<Integer> tree = new SplayTree<>();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Object[] arr = {5, 4, 3, 2};
        tree.addAll(list);
        Iterator<Integer> it = tree.iterator();
        int i = tree.size();
        it.remove();
        assertTrue(i - 1 == tree.size());
        assertArrayEquals(arr, tree.toArray());
        while (it.hasNext()) {
            System.out.println(Arrays.asList(tree.toArray()));
            it.remove();
        }

    }
}