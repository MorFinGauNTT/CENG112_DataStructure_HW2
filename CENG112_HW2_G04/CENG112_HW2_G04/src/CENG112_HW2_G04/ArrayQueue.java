package CENG112_HW2_G04;

public final class ArrayQueue<T> implements QueueInterface<T> {
    // Kod burada devam eder
 
 private T[] queue; // Circular array of queue entries and one unused
 // location
 private int frontIndex;
 private int backIndex;
 private boolean initialized = false;
 private static final int DEFAULT_CAPACITY = 50;

 private static final int MAX_CAPACITY = 10000;

 public ArrayQueue()
 {
 this(DEFAULT_CAPACITY);
 } // end default constructor

 public ArrayQueue(int initialCapacity)
 {
 checkCapacity(initialCapacity);

 // The cast is safe because the new array contains null entries
 @SuppressWarnings("unchecked")
 T[] tempQueue = (T[]) new Object[initialCapacity + 1];
 queue = tempQueue;
 frontIndex = 0;
 backIndex = initialCapacity;
 initialized = true;
 }
 public void checkInitialization()
 {
     if (!initialized)
         throw new SecurityException("ArrayBag object is not initialized " +
                 "properly.");}
public void enqueue(T newEntry)
{
 checkInitialization();
 ensureCapacity();
 backIndex = (backIndex + 1) % queue.length;
 queue[backIndex] = newEntry;
} // end enqueue

public T getFront()
{
 checkInitialization();
 if (isEmpty())
 throw new EmptyQueueException();
 else
 return queue[frontIndex];
} // end getFront

 
 private void checkCapacity(int capacity) {
	    if (capacity > MAX_CAPACITY) {
	        throw new IllegalStateException("Attempt to create a stack " +
	                "whose capacity exceeds maximum allowed capacity.");
	    }
	}
 private void ensureCapacity()
 {
  if (frontIndex == ((backIndex + 2) % queue.length)) // If array is full,
  { // double size of array
  T[] oldQueue = queue;
  int oldSize = oldQueue.length;
  int newSize = 2 * oldSize;
  checkCapacity(newSize - 1);
  // The cast is safe because the new array contains null entries
  @SuppressWarnings("unchecked")
  T[] tempQueue = (T[]) new Object[newSize];
  queue = tempQueue;
  for (int index = 0; index < oldSize - 1; index++)
  {
  queue[index] = oldQueue[frontIndex];
  frontIndex = (frontIndex + 1) % oldSize;
  } // end for
  frontIndex = 0;
  backIndex = oldSize - 2;
  } // end if
}
 public boolean isEmpty()
 {
  return frontIndex == ((backIndex + 1) % queue.length);
 }

public void clear() {
    checkInitialization(); 

    for (int i = frontIndex; i != backIndex; i = (i + 1) % queue.length) {
        queue[i] = null; 
    }

    queue[backIndex] = null;

    frontIndex = 0;
    backIndex = 0;
}

public T dequeue()
{
 checkInitialization();
 if (isEmpty())
 throw new EmptyQueueException();
 else
 {
 T front = queue[frontIndex];
 queue[frontIndex] = null;
 frontIndex = (frontIndex + 1) % queue.length;
 return front;
 } // end if
}
public int size() {
    return (backIndex - frontIndex + queue.length) % queue.length;
}
 }