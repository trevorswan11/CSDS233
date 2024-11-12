package NumLinkedList;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Iterator;

/**
 * A class for unit testing the NumLinkedList class.
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class NumLinkedListTest {
    // Test the add/size and additional (not required) methods 
    @Test
    public void basicOpsTest() {
        NumLinkedList testerList = new NumLinkedList(null);
        // Default attributes without a defined head
        assertEquals(null, testerList.getHead());
        assertEquals(0, testerList.size());
        assertTrue(testerList.isEmpty());

        // Create a head for the list
        IntegerNode head = new IntegerNode(0);
        testerList.setHead(head);
        assertEquals(head, testerList.getHead());
        assertEquals(1, testerList.size());
        assertFalse(testerList.isEmpty());

        // Add some elements to the list
        for (int i = 1; i < 100; i++) {
            testerList.add(i);
        }
        assertEquals(100, testerList.size());

        // Use an iterator to check each node's element
        Iterator<IntegerNode> itr = testerList.iterator();
        int val = 0;
        StringBuilder expected = new StringBuilder();
        while (itr.hasNext()) {
            assertEquals(val, itr.next().getElement());
            expected.append(val).append(val == 99 ? " -> null" : " -> ");
            val++;
        }

        // The iterator should be exhausted and an expected string representation made
        assertFalse(itr.hasNext());
        assertEquals(expected.toString(), testerList.toString());

        // Create two lists with different addresses
        NumLinkedList tester1 = new NumLinkedList(new IntegerNode(0));
        NumLinkedList tester2 = new NumLinkedList(new IntegerNode(0));

        // Add a ton of elements, lists should only be equal at the very end
        for (int i = 1; i < 1000; i++) {
            tester1.add(i);
            assertFalse(tester1.equals(tester2));
            tester2.add(i);
        }
        assertTrue(tester1.equals(tester2));
    }
    
    // Test the isSorted method
    @Test
    public void isSortedTest() {
        // test null and one element lists
        assertTrue(new NumLinkedList(null).isSorted());
        assertTrue(new NumLinkedList(new IntegerNode(0)).isSorted());

        // Create two tester lists to test with multiple elements
        NumLinkedList tester1 = new NumLinkedList(new IntegerNode(0));
        NumLinkedList tester2 = new NumLinkedList(new IntegerNode(100));
        NumLinkedList tester3 = new NumLinkedList(new IntegerNode(-50));

        // tester1 should increase, tester2 should decrease, tester3 is alternating
        for (int i = 1; i < 100; i++) {
            tester1.add(i);
            tester2.add(100 - i);
            tester3.add((int) (100 * 0.2 * i - 20) % 7);
        }

        // tester1 should be correctly sorted, but others aren't
        assertTrue(tester1.isSorted());
        assertFalse(tester2.isSorted());
        assertFalse(tester3.isSorted());
    }

    // Test the reverse method
    @Test
    public void reverseTest() {
        // Test an empty list 
        NumLinkedList voidList = new NumLinkedList(null);
        voidList.reverse();
        assertEquals(null, voidList.getHead());

        // Test a list with one element only
        NumLinkedList oneList = new NumLinkedList(new IntegerNode(2));
        oneList.reverse();
        assertTrue(oneList.equals(new NumLinkedList(new IntegerNode(2))));

        // Add one more element then reverse it
        oneList.add(3);
        assertTrue(oneList.isSorted());
        assertFalse(oneList.equals(new NumLinkedList(new IntegerNode(2))));
        assertTrue(oneList.equals(new NumLinkedList(new IntegerNode(2, new IntegerNode(3)))));
        oneList.reverse();
        assertFalse(oneList.isSorted());
        assertFalse(oneList.equals(new NumLinkedList(new IntegerNode(2, new IntegerNode(3)))));
        assertTrue(oneList.equals(new NumLinkedList(new IntegerNode(3, new IntegerNode(2)))));
    }

    // Test the duplicate list method
    @Test
    public void duplicateTest() {
        // Duplicate an empty list
        NumLinkedList emptyList = new NumLinkedList(null);
        assertTrue(emptyList.equals(NumLinkedList.duplicate(emptyList))); 

        // Create two linked lists
        NumLinkedList list1 = new NumLinkedList(new IntegerNode(1));
        list1.add(3);
        list1.add(5);

        NumLinkedList list2 = new NumLinkedList(new IntegerNode(2));
        list2.add(4);
        list2.add(6);

        // duplicate the individual lists
        NumLinkedList duplicate1 = NumLinkedList.duplicate(list1);
        NumLinkedList duplicate2 = NumLinkedList.duplicate(list2);

        // Check if the list contents are equal
        assertTrue(list1.equals(duplicate1));
        assertTrue(list2.equals(duplicate2));

        // Check if the list addresses are unequal
        assertFalse(list1 == duplicate1);
        assertFalse(list2 == duplicate2);

        // Check the addresses through the whole list
        Iterator<IntegerNode> itrBase1 = list1.iterator();
        Iterator<IntegerNode> itrDupe1 = duplicate1.iterator();

        while (itrBase1.hasNext()) {
            assertFalse(itrBase1.next() == itrDupe1.next());
        }
    }

    // Test the merge method with null lists
    @Test
    public void mergeTestNull() {
        NumLinkedList mergedList;

        // Create two null lists and merge them
        NumLinkedList nullList1 = new NumLinkedList(null);
        NumLinkedList nullList2 = new NumLinkedList(null);
        assertNull(NumLinkedList.merge(nullList1, nullList2).getHead());

        // Create a list with elements
        NumLinkedList list1 = new NumLinkedList(new IntegerNode(0));
        list1.add(1);
        list1.add(5);
        list1.add(3);

        // Merge a filled list with a null one
        mergedList = NumLinkedList.merge(nullList1, list1);
        assertTrue(mergedList.equals(list1));
    }

    // Test the merge method with sorted lists
    @Test
    public void mergeTestSorted() {
        NumLinkedList mergedList;
        NumLinkedList duplicate1;
        NumLinkedList duplicate2;

        // Create two sorted linked lists
        NumLinkedList list1 = new NumLinkedList(new IntegerNode(1));
        list1.add(3);
        list1.add(5);

        NumLinkedList list2 = new NumLinkedList(new IntegerNode(2));
        list2.add(4);
        list2.add(6);

        // duplicate the individual lists and merge
        duplicate1 = NumLinkedList.duplicate(list1);
        duplicate2 = NumLinkedList.duplicate(list2);
        mergedList = NumLinkedList.merge(list1, list2);

        // individual lists should remain unchanged, and merged should be sorted
        assertTrue(duplicate1.equals(list1));
        assertTrue(duplicate2.equals(list2));
        assertTrue(mergedList.isSorted());

        // Create a list to test against the merged one\
        NumLinkedList verify = new NumLinkedList(new IntegerNode(1));
        for (int i = 2; i < 7; i ++) {
            verify.add(i);
        }
        assertTrue(mergedList.equals(verify));
    }

    // Test the merge method with unsorted lists
    @Test
    public void mergeTestUnsorted() {
        NumLinkedList mergedList;
        NumLinkedList duplicate1;
        NumLinkedList duplicate2;

        // Create two lists with unsorted elements
        NumLinkedList list1 = new NumLinkedList(new IntegerNode(0));
        list1.add(1);
        list1.add(5);
        list1.add(3);

        NumLinkedList list2 = new NumLinkedList(new IntegerNode(0));
        list2.add(6);
        list2.add(4);
        list2.add(-4);
        
        // Duplicate and merge the lists
        duplicate1 = NumLinkedList.duplicate(list1);
        duplicate2 = NumLinkedList.duplicate(list2);
        mergedList = NumLinkedList.merge(list1, list2);

        // Create a list with the expected output
        NumLinkedList expected = new NumLinkedList(new IntegerNode(0));
        expected.add(0);
        expected.add(1);
        expected.add(5);
        expected.add(3);
        expected.add(6);
        expected.add(4);
        expected.add(-4);

        // Test the merged against expected and make sure its components are unchanged
        assertTrue(duplicate1.equals(list1));
        assertTrue(duplicate2.equals(list2));
        assertFalse(mergedList.isSorted());
        assertTrue(mergedList.equals(expected));
    }
}
