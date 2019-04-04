import java.util.*;
import java.io.*;

public class Radix {
  // public static void radixsort(int[] data) {
  //   @SuppressWarnings("unchecked")
  //   MyLinkedList<Integer>[] buckets = new MyLinkedList[20];
  //   for (int i = 0; i < buckets.length; i ++) {
  //     buckets[i] = new MyLinkedList<Integer>(); //initialize LinkedLists for each bucket
  //   }
  //   MyLinkedList<Integer> currentList = new MyLinkedList<Integer>();
  //   int largest = 0;
  //   for (int i = 0 ; i < data.length; i ++) {
  //     currentList.add(data[i]);
  //     int abs = Math.abs(data[i]);
  //     if (abs > largest) largest = abs;
  //   }
  //   System.out.println("largest: " + largest);
  //   int loops = countDigits(largest); //number of times needed to loop through in order to sort
  //   System.out.println("loops: " + loops);
  //   int currentLoop = 1;
  //   while (currentLoop <=loops) {
  //     // System.out.println("hi");
  //     for (int i = 0 ; i < currentList.size(); i ++) { //for each element in data
  //       int current = currentList.get(i);
  //       int digit = getDigit(current, currentLoop); //retrieve ones digit of the element
  //       if (current < 0) { //if element is negative
  //         buckets[9-digit].add(current); //add to respective bucket on the left of the buckets
  //       }
  //       else { //if element is positive
  //         buckets[digit+10].add(current); //add to respective bucket on the right of the buckets
  //       }
  //     }
  //     // System.out.println(Arrays.toString(buckets));
  //     currentList = connectLists(buckets);
  //     currentLoop++;
  //     for (int i = 0; i < buckets.length; i ++) {
  //       buckets[i] = new MyLinkedList<Integer>(); //initialize LinkedLists for each bucket
  //     }
  //     // System.out.println();
  //     // System.out.println(Arrays.toString(buckets));
  //     System.out.println(currentList);
  //   }
  //   // System.out.println(currentList);
  // }
  public static void radixsort(int[] data) {
    @SuppressWarnings("unchecked")
    MyLinkedList<Integer>[] buckets = new MyLinkedList[10];
    initializeBuckets(buckets);
    MyLinkedList<Integer> currentList = new MyLinkedList<Integer>();
    int largest = 0;
    for (int i = 0 ; i < data.length; i ++) {
      currentList.add(data[i]); //tranfer data elements over to currentList
      int abs = Math.abs(data[i]); //find abs value of the current element
      if (abs > largest) largest = abs; //if its greater than the current largest, replace it
    }
    int loops = countDigits(largest) + 1;
    int currentLoop = 0;
    while (currentLoop <= loops) {
      for (int i = 0; i < currentList.size(); i ++) { //for each element in currentList
        int current = currentList.get(i);
        int digit = getDigit(current, currentLoop); //retrieve digit according to currentLoop
        //if current is negative, add it to the front of the respective bucket
        if (current < 0) buckets[digit].add(0, current);
        //if current is positive, add it to the end of the respective bucket
        else buckets[digit].add(current);
      }
      currentList = connectLists(buckets);
      initializeBuckets(buckets);
      currentLoop++;
    }
    System.out.println(currentList);
  }

  private static MyLinkedList<Integer> connectLists(MyLinkedList<Integer>[] lists) {
    MyLinkedList<Integer> finalList = new MyLinkedList<>(); //starting list
    int listInd = 0;
    while (lists[listInd].size() == 0) {
      listInd++;
    }
    finalList = lists[listInd];
    for (int i = listInd+1; i < lists.length; i ++) { //for each linkedlist
      // System.out.println(i);
      // System.out.println(finalList);
      if (lists[i].size() != 0) { //if list is not empty
        finalList.extend(lists[i]); //add it to finalList
      }
    }
    return finalList;
  }

  private static void initializeBuckets(MyLinkedList<Integer>[] buckets) {
    for (int i = 0; i < buckets.length; i ++) {
      buckets[i] = new MyLinkedList<Integer>();
    }
  }

  private static int getDigit(int n, int place) {
    n = Math.abs(n);
    if (place == 1) return n % 10;
    else {
      // System.out.println();
      // System.out.println((int)(Math.pow(10,place)));
      int holder = n % (int)(Math.pow(10,place));
      // System.out.println(holder);
      holder -= n % (Math.pow(10,place-1));
      // System.out.println(holder);
      holder /= Math.pow(10,place-1);
      return holder;
    }
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
    int[] test = {10,5,-20,-347,75,345,23,0,30,7,2,1,88};
    radixsort(test);
    // System.out.println(getDigit(321,1));
    // System.out.println(getDigit(321,2));
    // System.out.println(getDigit(321,3));
    // System.out.println("15: " + countDigits(15));
    // System.out.println("1: " + countDigits(1));
    // System.out.println("155: " + countDigits(155));
  }
}
