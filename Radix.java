import java.util.*;
import java.io.*;

public class Radix {
  public static void radixsort(int[] data) {
    @SuppressWarnings("unchecked")
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    for (int i = 0; i < buckets.length; i ++) {
      buckets[i] = new MyLinkedList<Integer>(); //initialize LinkedLists for each bucket
    }
    int largest = 0;
    for (int i = 0 ; i < data.length; i ++) {
      int abs = Math.abs(data[i]);
      if (abs > largest) largest = abs;
    }
    int loops = countDigits(largest); //number of times needed to loop through in order to sort
    boolean sorted = true; //whether or not
    for (int i = 0 ; i < data.length; i ++) { //for each element in data
      int digit = Math.abs( data[i]) %10; //retrieve ones digit of the element
      if (data[i] < 0) { //if element is negative
        buckets[9-digit].add(data[i]); //add to respective bucket on the left of the buckets
      }
      else { //if element is positive
        buckets[digit+10].add(data[i]); //add to respective bucket on the right of the buckets
      }
    }
    int currentPlace = 2; //from the left; 1 - ones place, 2 - tens place, 3 - hundreds place, etc.
    // System.out.println(Arrays.toString(buckets));
  }

  private static MyLinkedList<Integer> connectLists(MyLinkedList<Integer>[] lists) {
    MyLinkedList<Integer> finalList = lists[0]; //starting list
    for (int i = 1; i < lists.length; i ++) { //for each linkedlist
      finalList.extend(lists[i]); //add it to finalList
    }
    return finalList;
  }

  private static int countDigits(int n) {
    return 0;
  }
  public static void main(String[] args) {
    int[] test = {10,5,-20,-34,75,345,23,0,30,7,2,1,88};
    radixsort(test);
  }
}
