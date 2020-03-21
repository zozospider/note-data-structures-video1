import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {

    // 可比较的元素对象, 存储元素值和其出现的频次 (按照出现的频次从小到大排序)
    private class Element implements Comparable<Element> {

        // 元素值
        int element;
        // 出现的频次
        int frequency;

        public Element(int element, int frequency) {
            this.element = element;
            this.frequency = frequency;
        }

        // 按照出现的频次 frequency 倒序排列 (频次最小的元素优先级最高)
        @Override
        public int compareTo(Element element) {
            if (this.frequency > element.frequency) {
                return -1;
            } else if (this.frequency < element.frequency) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        // 遍历 nums, 将 num (key) 和其频次 (value) 存储到 map 映射中
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        // 遍历 map, 将 map 中的元素和其出现的频次加入到优先队列中 (最大堆: 优先级最高的在最上面)
        Queue<Element> priorityQueue = new MaxHeapPriorityQueue<>();

        // 遍历 map 所有数据, 加入到优先队列中 (队列中存储 k 个元素)
        for (int key : map.keySet()) {

            // 获取当前频次
            int frequency = map.get(key);

            if (priorityQueue.getSize() < k) {

                // 如果优先队列中还不足 k 个元素, 则填满优先队列
                priorityQueue.enqueue(new Element(key, frequency));

            } else {

                // 否则, 说明优先队列已经有 k 个元素了, 此时需要将当前元素的频次与优先队列中的最小频次进行比较

                // 如果当前元素的频次大于优先队列中的最小频次, 则需要将优先队列中的最小频次元素替换成当前元素

                if (frequency > priorityQueue.getFront().frequency) {

                    // 将优先队列中的最小频次元素出队
                    priorityQueue.dequeue();
                    // 将当前元素入队
                    priorityQueue.enqueue(new Element(key, frequency));
                }
            }
        }

        // 此时优先队列中的所有 key 即为 nums 数组中出现频率最高的 k 个数
        // 转换成 list 返回
        List<Integer> keyList = new LinkedList<>();
        while (!priorityQueue.isEmpty()) {
            keyList.add(priorityQueue.dequeue().element);
        }
        return keyList;
    }

}
