import java.util.*;
import java.io.*;

public class Radix {
  public static void radixsort(int[] data) {
    @SuppressWarnings("rawtypes")
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    for (int i = 0; i < 20; i ++) {
      buckets[i] = new MyLinkedList<Integer>(); //initialize LinkedLists for each bucket
    }
    for (int i = 0 ; i < data.length; i ++) { //for each element in data
      int digit = Math.abs( data[i]) %10; //retrieve ones digit of the element
      if (data[i] < 0) { //if element is negative
        buckets[9-digit].add(data[i]); //add to respective bucket on the left of the buckets
      }
      else { //if element is positive
        buckets[digit+10].add(data[i]); //add to respective bucket on the right of the buckets
      }
    }
    // System.out.println(Arrays.toString(buckets));
  }
  public static void main(String[] args) {
    int[] test = {9,9,8,7,6,5,4,3,2,1,0,-1,-2,-3,-4,-5,-6,-7,-8,-9};
    radixsort(test);
  }
}
