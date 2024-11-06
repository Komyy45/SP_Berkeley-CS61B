package deque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayDequeTest {

    private ArrayDeque<Integer> deque;

    @BeforeEach
    void setUp() {
        deque = new ArrayDeque<>();
    }

    @Test
    void testInitialSizeAndCapacity() {
        assertEquals(0, deque.size(), "Initial size should be zero.");
    }

    @Test
    void testAddFirst() {
        deque.addFirst(1);
        assertEquals(1, deque.size());
        assertEquals(1, deque.get(0));

        deque.addFirst(2);
        assertEquals(2, deque.size());
        assertEquals(2, deque.get(0));
        assertEquals(1, deque.get(1));
    }

    @Test
    void testAddLast() {
        deque.addLast(1);
        assertEquals(1, deque.size());
        assertEquals(1, deque.get(0));

        deque.addLast(2);
        assertEquals(2, deque.size());
        assertEquals(1, deque.get(0));
        assertEquals(2, deque.get(1));
    }

    @Test
    void testRemoveFirst() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertEquals(1, deque.removeFirst());
        assertEquals(2, deque.size());
        assertEquals(2, deque.get(0));

        assertEquals(2, deque.removeFirst());
        assertEquals(1, deque.size());

        assertEquals(3, deque.removeFirst());
        assertEquals(0, deque.size());

        assertNull(deque.removeFirst(), "Removing from empty deque should return null.");
    }

    @Test
    void testRemoveLast() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        assertEquals(1, deque.removeLast());
        assertEquals(2, deque.size());
        assertEquals(3, deque.get(0));
        assertEquals(2, deque.get(1));

        assertEquals(2, deque.removeLast());
        assertEquals(1, deque.size());

        assertEquals(3, deque.removeLast());
        assertEquals(0, deque.size());

        assertNull(deque.removeLast(), "Removing from empty deque should return null.");
    }

    @Test
    void testAddFirstAndRemoveLast() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        assertEquals(1, deque.removeLast());
        assertEquals(2, deque.removeLast());
        assertEquals(3, deque.removeLast());
        assertNull(deque.removeLast(), "Removing from empty deque should return null.");
    }

    @Test
    void testAddLastAndRemoveFirst() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        assertEquals(1, deque.removeFirst());
        assertEquals(2, deque.removeFirst());
        assertEquals(3, deque.removeFirst());
        assertNull(deque.removeFirst(), "Removing from empty deque should return null.");
    }

    @Test
    void testResizeOnAddLast() {
        for (int i = 0; i < 9; i++) {
            deque.addLast(i);
        }
        assertEquals(9, deque.size(), "Size should be updated after resize.");
        assertEquals(0, deque.get(0));
        assertEquals(8, deque.get(8));
    }

    @Test
    void testResizeOnAddFirst() {
        for (int i = 0; i < 9; i++) {
            deque.addFirst(i);
        }
        assertEquals(9, deque.size(), "Size should be updated after resize.");
        assertEquals(8, deque.get(0));
        assertEquals(0, deque.get(8));
    }

    @Test
    void testGetWithinBounds() {
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);

        assertEquals(10, deque.get(0));
        assertEquals(20, deque.get(1));
        assertEquals(30, deque.get(2));
    }

    @Test
    void testGetOutOfBounds() {
        deque.addLast(10);
        assertThrows(IndexOutOfBoundsException.class, () -> deque.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> deque.get(1));
    }

    @Test
    void testAddRemoveMix() {
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);

        assertEquals(3, deque.removeFirst());
        assertEquals(1, deque.removeFirst());
        assertEquals(2, deque.removeFirst());
        assertEquals(4, deque.removeFirst());
        assertNull(deque.removeFirst(), "Deque should be empty.");
    }

    @Test
    void testCapacityDoesNotShrink() {
        for (int i = 0; i < 10; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < 10; i++) {
            deque.removeFirst();
        }
        assertEquals(0, deque.size(), "Size should be zero after all removals.");
        deque.addLast(100);
        assertEquals(100, deque.get(0));
    }

    @Test
    void testAddRemovePerformance() {
        int operations = 1000;
        for (int i = 0; i < operations; i++) {
            deque.addLast(i);
        }
        assertEquals(operations, deque.size());

        for (int i = 0; i < operations; i++) {
            assertEquals(i, deque.removeFirst());
        }
        assertEquals(0, deque.size(), "Deque should be empty after removing all elements.");
    }
}
