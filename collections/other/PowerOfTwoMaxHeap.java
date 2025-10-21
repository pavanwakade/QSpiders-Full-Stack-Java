import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PowerOfTwoMaxHeap<T extends Comparable<T>> {
    private final int childrenCount; // 2^degreeExponent
    private final ArrayList<T> heap;

    public PowerOfTwoMaxHeap(int degreeExponent) {
        if (degreeExponent < 0 || degreeExponent > 10) {
            throw new IllegalArgumentException("degreeExponent must be between 0 and 10 for performance.");
        }
        this.childrenCount = 1 << degreeExponent; // 2^degreeExponent
        this.heap = new ArrayList<>();
    }

    public void insert(T value) {
        heap.add(value);
        siftUp(heap.size() - 1);
    }

    public T popMax() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        T maxValue = heap.get(0);
        T lastValue = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, lastValue);
            siftDown(0);
        }
        return maxValue;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }

    private void siftUp(int index) {
        int parentIndex = (index - 1) / childrenCount;
        while (index > 0 && heap.get(index).compareTo(heap.get(parentIndex)) > 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / childrenCount;
        }
    }

    private void siftDown(int index) {
        int size = heap.size();

        while (true) {
            int maxIndex = index;

            for (int i = 1; i <= childrenCount; i++) {
                int childIndex = childrenCount * index + i;
                if (childIndex < size &&
                    heap.get(childIndex).compareTo(heap.get(maxIndex)) > 0) {
                    maxIndex = childIndex;
                }
            }

            if (maxIndex != index) {
                swap(index, maxIndex);
                index = maxIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    // Debug Helper
    public void printHeap() {
        System.out.println(heap);
    }

     public static void main(String[] args) {
        PowerOfTwoMaxHeap<Integer> heap = new PowerOfTwoMaxHeap<>(2); // 2^2 = 4-ary heap

        int[] values = {15, 10, 30, 20, 40, 50, 5, 70};
        for (int val : values) {
            heap.insert(val);
        }

        heap.printHeap(); // Debug

        while (!heap.isEmpty()) {
            System.out.print(heap.popMax() + " ");
        }
    }
}
