public class Main {
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
