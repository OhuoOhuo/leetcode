package base.class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author ：cwf
 * @description：重写heap结构，增加更新功能 系统提供的堆结构不带有修改功能，有时需要有该功能，需要自己实现
 */
public class code03_heap02 {

    public static class MyHeap<T> {
        public ArrayList<T> heap;
        public int heapSize;
        public HashMap<T, Integer> indexMap;
        public Comparator<? super T> comparator;

        public MyHeap(Comparator<? super T> comparator) {
            heap = new ArrayList<>();
            heapSize = 0;
            indexMap = new HashMap<>();
            this.comparator = comparator;
        }


        public void resign(T value) {
            int valueIndex = indexMap.get(value);
            //上浮，下沉只会中一个
            heapInsert(valueIndex);
            heapify(heap,valueIndex, heapSize);
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public int size() {
            return heapSize;
        }

        public boolean contains(T key) {
            return indexMap.containsKey(key);
        }

        public void push(T value) {
            heap.add(value);
            indexMap.put(value, heapSize);
            heapInsert(heapSize++);
        }

        public T pop(){
            if(heapSize <=0){
                throw new RuntimeException("空了");
            }
            T ans = heap.get(0);
            int end = heapSize -1;
            swap(heap,0,end);
            heap.remove(end);
            indexMap.remove(ans);
            heapify(heap,0,--heapSize);
            return ans;
        }

        private void heapify(ArrayList<T> heap, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = left + 1 < heapSize && (comparator.compare(heap.get(left + 1), heap.get(left)) < 0)
                        ? left + 1
                        : left;
                largest = comparator.compare(heap.get(largest), heap.get(index)) < 0 ? largest : index;
                if (largest == index) {
                    break;
                }
                swap(heap,largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }

        private void heapInsert(int index) {
            while (comparator.compare(heap.get(index), heap.get((index + 1) / 2)) < 0) {
                swap(heap, index, (index + 1) / 2);
            }
        }


        private void swap(ArrayList<T> heap, int i, int j) {
            T t1 = heap.get(i);
            T t2 = heap.get(j);

            heap.set(i, t2);
            heap.set(j, t1);
            indexMap.put(t1, j);
            indexMap.put(t2, i);
        }


    }


    public static class Student {
        public int classNo;
        public int age;
        public int id;

        public Student(int c, int a, int i) {
            classNo = c;
            age = a;
            id = i;
        }
    }


}
