package base.class04;

/**
 * @author ：cwf
 * @description：堆结构 用数组结构实现的完全二叉树
 * 大根堆：每个节点都是以该节点位顶点的子树的最大值（言下之意：不要求有序，同样的几个节点有多种组成大根堆的方法）
 * 小根堆：每个节点都是以该节点为顶点子树的最小值
 */
public class code02_heap01 {

    public static class MyMaxHeap {
        private int[] heap;
        private int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("满了");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        //上浮，直到无法上浮为止
        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }


        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("空了");
            }
            int res = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return res;
        }

        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;

            while (left < heapSize) {
                int bigIndex = 0;
                if (left + 1 < heapSize && arr[left + 1] > arr[left]) {
                    bigIndex = left + 1;
                } else {
                    bigIndex = left;
                }
                if (arr[index] >= arr[bigIndex]) {
                    break;
                }
                swap(arr, index, bigIndex);
                index = bigIndex;
                left = index * 2 + 1;
            }

        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    }

    public static class RightMaxHeap {
        private int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            arr[maxIndex] = arr[--size];
            return ans;
        }

    }

    public static void main(String[] args) {
        int value = 1000;
        int limit = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int curLimit = (int) (Math.random() * limit) + 1;
            MyMaxHeap my = new MyMaxHeap(curLimit);
            RightMaxHeap test = new RightMaxHeap(curLimit);
            int curOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (my.isEmpty() != test.isEmpty()) {
                    System.out.println("Oops!");
                }
                if (my.isFull() != test.isFull()) {
                    System.out.println("Oops!");
                }
                if (my.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    my.push(curValue);
                    test.push(curValue);
                } else if (my.isFull()) {
                    if (my.pop() != test.pop()) {
                        System.out.println("Oops!");
                    }
                } else {
                    if (Math.random() < 0.5) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else {
                        if (my.pop() != test.pop()) {
                            System.out.println("Oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");

    }


}
