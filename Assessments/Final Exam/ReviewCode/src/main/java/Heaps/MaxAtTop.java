package Heaps;

import java.util.ArrayList;

public class MaxAtTop<T extends Comparable<T>> extends Heap<T> {
    public MaxAtTop() {
        super();
    }

    public MaxAtTop(ArrayList<T> list) {
        super(list);
    }

    @Override
    public T remove(int i) {
        if (isEmpty() || i < 0 || i >= size()) return null;
        T toRemove = heap.get(i);
        T lastElement = heap.remove(size() - 1);
        if (i < size()) {
            heap.set(i, lastElement);
            if (lastElement.compareTo(toRemove) > 0)
                siftUp(i);
            else
                siftDown(i);
        }
        return toRemove;
    }

    @Override
    public T set(int i, T element) {
        if (i >= size() || i < 0) return null;
        T toRemove = heap.set(i, element);
        if (element.compareTo(toRemove) > 0)
            siftUp(i);
        else
            siftDown(i);
        return toRemove;
    }

    @Override
    protected void siftUp(int i) {
        int parentIndex = (i - 1) / 2;
        while (i > 0 && get(i).compareTo(get(parentIndex)) > 0) {
            swap(i, parentIndex);
            i = parentIndex;
            parentIndex = (i - 1) / 2;
        }
    }

    @Override
    protected void siftDown(int i) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < size() && get(left).compareTo(get(largest)) > 0)
                largest = left;
            if (right < size() && get(right).compareTo(get(largest)) > 0)
                largest = right;
            if (largest == i)
                break;

            swap(i, largest);
            i = largest;
        }
    }
}
