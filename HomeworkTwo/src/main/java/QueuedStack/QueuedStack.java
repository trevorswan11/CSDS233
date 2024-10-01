package QueuedStack;

import java.util.Queue;
import java.util.EmptyStackException;

/**
 * A class to mimic the behavior of a stack using a single queue
 * 
 * @author Trevor Swan
 * @version CSDS233 - Fall 2024
 */
public class QueuedStack<T> {
    Queue<T> queue;

    public void throwing() {
        throw new EmptyStackException();
    }
}
