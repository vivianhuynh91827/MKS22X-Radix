import java.util.*;
import java.io.*;

public class Radix {
  public static void radixsort(int[] data) {
    @SuppressWarnings("unchecked")
    MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
    for (int i = 0; i < buckets.length; i ++) {
      buckets[i] = new MyLinkedList<Integer>(); //initialize LinkedLists for each bucket
    }
    MyLinkedList<Integer> currentList = new MyLinkedList<Integer>();
    int largest = 0;
    for (int i = 0 ; i < currentList.size(); i ++) {
      currentList.add(data[i]);
      int abs = Math.abs(data[i]);
      if (abs > largest) largest = abs;
    }
    int loops = countDigits(largest); //number of times needed to loop through in order to sort
    int currentLoop = 1;
    while (currentLoop <=loops) {
      // System.out.println("hi");
      for (int i = 0 ; i < currentList.size(); i ++) { //for each element in data
        int current = currentList.get(i);
        int digit = Math.abs( current ) %10; //retrieve ones digit of the element
        if (current < 0) { //if element is negative
          buckets[9-digit].add(current); //add to respective bucket on the left of the buckets
        }
        else { //if element is positive
          buckets[digit+10].add(current); //add to respective bucket on the right of the buckets
        }
      }
      currentList = connectLists(buckets);
      currentLoop++;
    }
    System.out.println("hi");
    System.out.println(Arrays.toString(buckets));
    System.out.println("hi");
    System.out.println(currentList);
  }

  private static MyLinkedList<Integer> connectLists(MyLinkedList<Integer>[] lists) {
    MyLinkedList<Integer> finalList = lists[0]; //starting list
    for (int i = 1; i < lists.length; i ++) { //for each linkedlist
      finalList.extend(lists[i]); //add it to finalList
    }
    return finalList;
  }

  private static int countDigits(int n) {
    int digits = 1; //number of digits
    while (n > Math.pow(10,digits)) {
      //while the n is greater than 10 raised to the current number of digits counted
      digits ++;
    }
    return digits;
  }

  public static void main(String[] args) {
    int[] test = {10,5,-20,-34,75,345,23,0,30,7,2,1,88};
    radixsort(test);
    // System.out.println("15: " + countDigits(15));
    // System.out.println("1: " + countDigits(1));
    // System.out.println("155: " + countDigits(155));
  }
}
