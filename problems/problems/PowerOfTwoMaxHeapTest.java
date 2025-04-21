package problems;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PowerOfTwoMaxHeapTest {

    // PowerOfTwoMaxHeap implementation
    public static class PowerOfTwoMaxHeap<T extends Comparable<T>> {
        private final ArrayList<T> heap;
        private final int childrenPower;
        private final int numChildren;

        public PowerOfTwoMaxHeap(int childrenPower) {
            if (childrenPower < 0) {
                throw new IllegalArgumentException("Power must be non-negative.");
            }
            this.childrenPower = childrenPower;
            this.numChildren = 1 << childrenPower; // 2^childrenPower
            this.heap = new ArrayList<>();
        }

        public void insert(T value) {
            heap.add(value);
            heapifyUp(heap.size() - 1);
        }

        public T popMax() {
            if (heap.isEmpty()) {
                throw new NoSuchElementException("Heap is empty.");
            }

            T max = heap.get(0);
            T last = heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                heap.set(0, last);
                heapifyDown(0);
            }
            return max;
        }

        public T peekMax() {
            if (heap.isEmpty()) {
                throw new NoSuchElementException("Heap is empty.");
            }
            return heap.get(0);
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

        public int size() {
            return heap.size();
        }

        private void heapifyUp(int index) {
            while (index > 0) {
                int parentIndex = (index - 1) / numChildren;
                if (heap.get(index).compareTo(heap.get(parentIndex)) > 0) {
                    swap(index, parentIndex);
                    index = parentIndex;
                } else {
                    break;
                }
            }
        }

        private void heapifyDown(int index) {
            while (true) {
                int maxIndex = index;
                for (int i = 1; i <= numChildren; i++) {
                    int childIndex = numChildren * index + i;
                    if (childIndex < heap.size() && heap.get(childIndex).compareTo(heap.get(maxIndex)) > 0) {
                        maxIndex = childIndex;
                    }
                }

                if (maxIndex == index) break;

                swap(index, maxIndex);
                index = maxIndex;
            }
        }

        private void swap(int i, int j) {
            T temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        PowerOfTwoMaxHeap<Integer> heap = new PowerOfTwoMaxHeap<>(2); // 4-ary heap (2^2 = 4 children per node)

        heap.insert(10);
        heap.insert(30);
        heap.insert(20);
        heap.insert(40);
        heap.insert(25);

        System.out.println("Max: " + heap.popMax()); // Should be 40
        System.out.println("Max: " + heap.popMax()); // Should be 30
        System.out.println("Max: " + heap.popMax()); // Should be 25
    }
}
