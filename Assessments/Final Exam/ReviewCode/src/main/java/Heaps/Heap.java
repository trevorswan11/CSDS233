package Heaps;

import java.util.ArrayList;

abstract class Heap<T extends Comparable<T>> {
    protected ArrayList<T> heap;

    public Heap() {
        heap = new ArrayList<>();
    }

    public Heap(int initialSize) {
        heap = new ArrayList<>(initialSize);
    }
    
    public Heap(ArrayList<T> list) {
        heap = list;
        heapify();
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
    public void insert(T element) {
        heap.add(element);
        siftUp(size() - 1);
    }

    public T poll() {
        if (isEmpty()) return null;
        T toRemove = heap.get(0);
        T lastElement = heap.remove(size() - 1);
        if (!isEmpty()) {
            heap.set(0, lastElement);
            siftDown(0);
        }
        return toRemove;
    }

    public T peek() {
        return heap.get(0);
    }

    public T get(int i) {
        return heap.get(i);
    }

    protected void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp); 
    }

    private void heapify() {
        for (int position = (size() - 2) / 2; position >= 0; position--)
            siftDown(position);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (T element : heap)
            sb.append(element.toString()).append(", ");
        sb.delete(sb.length() - 2, sb.length()).append("]");
        return sb.toString();
    }

    public abstract T remove(int i);
    public abstract T set(int i, T element);
    protected abstract void siftUp(int i);
    protected abstract void siftDown(int i);
}
