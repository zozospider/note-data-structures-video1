import java.util.*;

public class Solution2 {

    // 元素对象, 存储元素值和其出现的频次
    private class Element {

        // 元素值
        int element;
        // 出现的频次
        int frequency;

        public Element(int element, int frequency) {
            this.element = element;
            this.frequency = frequency;
        }

    }

    // 用于 Element 对象的比较器 (按照出现的频次从大到小排序)
    private class ElementComparator implements Comparator<Element> {

        // 按照出现的频次 frequency 从大到小排列 (频次最大的元素优先级最高)
        @Override
        public int compare(Element e1, Element e2) {
            if (e1.frequency > e2.frequency) {
                return 1;
            } else if (e1.frequency == e2.frequency) {
                return 0;
            } else {
                return -1;
            }
            // return e1.frequency - e2.frequency;
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

        // 遍历 map, 将 map 中的元素和其出现的频次加入到优先队列中 (最小堆: 优先级最低的 (即频次最小的) 在最上面)
        Queue<Element> priorityQueue = new PriorityQueue<>(new ElementComparator());

        // 遍历 map 所有数据, 加入到优先队列中 (队列中存储 k 个频次最小的元素)
        for (int key : map.keySet()) {

            // 获取当前频次
            int frequency = map.get(key);

            if (priorityQueue.size() < k) {

                // 如果优先队列中还不足 k 个元素, 则填满优先队列
                priorityQueue.add(new Element(key, frequency));

            } else {

                // 否则, 说明优先队列已经有 k 个元素了, 此时需要将当前元素的频次与优先队列中的最小频次进行比较

                // 如果当前元素的频次大于优先队列中的最小频次, 则需要将优先队列中的最小频次元素替换成当前元素

                if (priorityQueue.peek() != null
                        && frequency > priorityQueue.peek().frequency) {

                    // 将优先队列中的最小频次元素出队
                    priorityQueue.remove();
                    // 将当前元素入队
                    priorityQueue.add(new Element(key, frequency));
                }
            }
        }

        // 此时优先队列中的所有 key 即为 nums 数组中出现频率最高的 k 个数
        // 转换成 list 返回
        List<Integer> keyList = new LinkedList<>();
        while (!priorityQueue.isEmpty()) {
            keyList.add(priorityQueue.remove().element);
        }
        return keyList;
    }

}
