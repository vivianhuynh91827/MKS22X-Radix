import java.util.*;
import java.io.*;

public class Radix {
  public static void radixsort(int[] data) {
    @SuppressWarnings("unchecked")
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    for (int i = 0 ; i < data.length; i ++) {
      int digit = Math.abs( data[i] % 10 );
      if (data[i] < 0) {
        buckets[9-digit].add(data[i]);
      }
      else {
        buckets[digit+10].add(data[i]);
      }
    }
    System.out.println(Arrays.toString(buckets));
  }
  public static void main(String[] args) {
    int[] test = {9,8,7,6,5,4,3,2,1,0,-1,-2,-3,-4,-5,-6,-7,-8,-9};
    radixsort(test);
  }
}
