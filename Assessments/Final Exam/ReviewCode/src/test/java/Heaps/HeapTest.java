package Heaps;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class HeapTest {
    public void ae(int expected, int actual) throws AssertionError {
        assertEquals(new Integer(expected), new Integer(actual));
    }

    Heap<Integer> heap;
    @Test
    public void maxTest() {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(40);
        l.add(2);
        l.add(90);
        l.add(1);
        l.add(-1);
        heap = new MaxAtTop<>(l);
        
        ae(90, heap.poll());
        ae(40, heap.poll());
        ae(-1, heap.remove(2));
        ae(2, heap.poll());
        ae(1, heap.poll());
    }

    @Test
    public void minTest() {
        heap = new MinAtTop<>();
        heap.insert(2);
        heap.insert(23);
        heap.insert(25);
        heap.insert(52);
        heap.insert(20);
        heap.insert(-2);

        ae(-2, heap.poll());
        heap.set(0, 68);
        ae(20, heap.poll());
        System.out.println(heap.toString());
    }
}
