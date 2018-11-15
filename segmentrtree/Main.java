package segmentrtree;

public class Main {
    public static void main(String[] args){
        Integer[] nums = {1, 2, 3, 4, 5, 6};
//      SegmentTree<Integer> segTree = new SegmentTree<>(nums,
//                new Merger<Integer>() {
//                    @Override
//                    public Integer merge(Integer a, Integer b) {
//                        return a + b;
//                    }
//                });
        //lambda 匿名内部类
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a,b) -> a + b);
        System.out.println("data  "+segmentTree.query(0,1));
    }
}
