package Queue;

public class CircularQueue<T> {
    private T[] arr;
    private int tail, head, size;
    
    @SuppressWarnings("unchecked")
    public CircularQueue(int size) {
        this.arr = (T[]) (new Object[size]);
        this.tail = -1;
        this.head = 0;
        this.size = 0;
    }

    public boolean isFull() {
        return this.size == arr.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    public void push(T item) {
        enqueue(item);
    }

    public void enqueue(T item) {
        if (isFull())
            throw new IllegalStateException("Queue is full.");
        tail = (tail + 1) % this.arr.length;                        // Move the tail pointer and add the item
        this.arr[tail] = item;
        size++;
    }

    public void pop() {
        dequeue();
    }

    public T dequeue() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty.");
        T value = this.arr[head];                                   // Get the item from the head, move the head pointer
        head = (head + 1) % this.arr.length;
        size--;
        return value;
    }

    public T peek() {
        if (isEmpty())
            throw new IllegalStateException("Queue is empty.");
        return this.arr[head];                                      // Simply return the head without changing pointer
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            int index = (head + i) % arr.length;                    // Compute the correct index
            sb.append(arr[index]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
