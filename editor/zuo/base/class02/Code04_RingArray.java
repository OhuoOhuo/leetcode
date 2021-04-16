package base.class02;

/**
 * @author ：cwf
 * @date ：Created in 2021/3/11 14:06
 * @description：数组实现队列
 * @modified By：
 * @version: $
 */
public class Code04_RingArray {

    public class MyQueue {

        public int[] arr;
        public int size;
        public int limit;
        public int pushi;
        public int popi;

        public MyQueue(int limit) {
            arr = new int[limit];
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列已满");
            }
            size++;
            arr[pushi] = value;
            pushi = getNext(pushi);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列为空");
            }
            int res = arr[popi];
            size--;
            popi = getNext(popi);
            return res;
        }


        //返回下个位置，如果是最后一个则到0点
        public int getNext(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }


    }
}
