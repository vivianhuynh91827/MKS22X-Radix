public class MyLinkedList<E>{
  private int size;
  private Node start, end;

  /**Creates an empty list
  */
  public MyLinkedList() {
    size=0;
  }

  /** Returns the length of the list
  @return the current size of the list
  */
  public int size() {
    return size;
  }

  /** Adds the given value to the end of the ArrayList
  @param value is value that will be added
  @return a boolean for whether or not addition was successful
  */
  public boolean add(E value){
    Node newVal;
    if (size==0) {
      newVal = new Node(value, null, null); //Lone Node, no next or prec
      size++; //Increase size
      start = newVal; //Set Start to the first node
      end = newVal; //Set end to the node
      return true;
    }
    else {
      newVal = new Node(value, null, end);
      //Last node, so no next, only prev which is the current end not yet updated
      size++; //Increase size
      end.setNext(newVal); //change the next of the previous end to the new Node
      end = newVal; //set the end to the new node
      return true;
    }
  }

  /** Adds the given value to the given index
  @param index is the location of the new value
  @param value is the value that will be words
  */
  public void add(int index, E value) {
    if ((size==0 && index != 0)||((index > size || index < 0)&&size>0) ) {
      throw new IndexOutOfBoundsException("Index "+index+" is out of bounds");
    }
    Node before,after;
    if (size==0||(size>0&&index==size)) {//if linked list is empty or adding to the end of the list
      add(value);
    }
    else {
      if (index == 0) { //if new Node will be inserted at the beginning
        before = null;
        after = getNode(0);
      }
      else { // if new node will be in the middle of the linked list
        before = getNode(index-1);
        after = getNode(index);
      }
      Node newNode = new Node(value, after, before);
      if (before == null) {
         start = newNode; //if index given is 0, make the new Node the start
         size++;
         after.setPrev(newNode);
      }
      else {
        before.setNext(newNode); //if value will be added to the middle of the list
        after.setPrev(newNode);
        size++;
      }
    }
  }

  /** Each value separated by a comma inside a bracket
  @return a String of the list
  */
  public String toString() {
    String linkedList = "[";
    if (size==0) {
      return "[]";
    }
    Node current = start; //keep tracks of current node
    while (current.getNext()!= null) { // if node has a next
      linkedList+=current.getData() + ", ";
      current = current.getNext(); //Moves to next node
    }
    linkedList+=current.getData(); //Adds end node
    linkedList+="]";
    return linkedList;
  }

  private String debugToString() {
    String linkedList = "[";
    if (size==0) {
      return "[]";
    }
    Node current = start; //keep tracks of current node
    while (current.getNext()!= null) { // if node has a next
      linkedList+="("+current.getPrev()+")";
      linkedList+=current.getData();
      linkedList+="("+current.getNext()+")" + ", ";
      current = current.getNext(); //Moves to next node
    }
    linkedList+="("+current.getPrev()+")";
    linkedList+=current.getData(); //Adds end node
    linkedList+="("+current.getNext()+")";
    linkedList+="]";
    return linkedList;
  }

  /** Retrieves the value at the given index
  @param index is the location of the wanted value
  @return an Integer of the value at the specified index
  */
  public E get(int index) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException("Index "+index+" is out of bounds");
    }
    return getNode(index).getData();//uses helper function to find value at given index
  }

  /** Changes the value at the given index
  @param index is the location of the new value
  @return an Integer of the original value before the change
  */
  public E set(int index, E value) {
    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException("Index "+index+" is out of bounds");
    }
    Node changing = getNode(index); //gets node that will be replaced
    E original = changing.getData(); //stores original data that will be replaced
    changing.setData(value); //changes the data
    return original;
  }

  private Node getNode(int index) {
    Node current = start;
    for (int i = 0; i < index; i ++) { //loops through index amount of times to get to the wanted Node
      current = current.getNext();
    }
    return current;
  }

  /** Finds of given value is inside the list
  @param value is the wanted value
  @return a boolean depending on if the value is in the list
  */
  public boolean contains(E value) {
    boolean contains = false; //has the value been found yet
    Node current = start;
    for (int i = 0; i < size; i ++) { //loops through linked list
      if (current.getData()==value) {
        contains=true; //if the value of the current node is equal to the wanted value, make boolean contains true;
      }
      current = current.getNext(); //updates current node
    }
    return contains;
  }

  /** Retrieves the index of the given value
  @param value is the wanted value
  @return an int of the index of the wanted value
  */
  public int indexOf(E value) {
    Node current = start;
    for (int curInd = 0; curInd < size; curInd ++) { //loops through linked list
      if (current.getData()==value) {
        return curInd; //if the value of current node is equal to wanted value, return the index at which it wqas found
      }
      current = current.getNext();
    }
    return -1; //if the value given was not found, return -1
  }

  /** Removes a value at the given index from the list
  @param index is the location of the wanted value
  @return an Integer of the value removed
  */
  public E remove(int index) {
    if ((size==0 && index != 0)||((index >= size || index < 0)&&size>0) ) {
      throw new IndexOutOfBoundsException("Index "+index+" is out of bounds");
    }
    Node removing = getNode(index);
    Node after,before;
    if (index == 0) { //if value is at the beginning of the list
      after = getNode(1); //get second element
      after.setPrev(null); //remove first element
      start = after; //set the second element as the start
      size--;
    }
    else if (index == size-1) { //if value is at the end of the list
      before = getNode(size-2); // get second to last element
      before.setNext(null); // remove last element
      end = before; //set second to last element as the end
      size--;
    }
    else {
      after = getNode(index+1); //get node after wanted node
      before = getNode(index-1); //get node before wanted node
      after.setPrev(before); //remove wanted node from memory
      before.setNext(after);
      size--;
    }
    return removing.getData();
  }

  /** Removed the first instance of the given value
  @param value is the value wanted to be removed
  @return a boolean depending on whether or not the value was removed
  */
  public boolean remove(E value) {
    if (contains(value)) { //if the list contains the wanted value
      int index = indexOf(value); // get the index of the wanted value
      remove(index);
      return true;
    }
    return false;
  }

  /**  Links two lists togehter
  @param other is a MyLinkedList that will be linked to the original
  */
  public void extend(MyLinkedList other) {
    if (other.size()!=0) {
      Node middleEnd = this.end;
      Node middleStart = other.start;
      middleEnd.setNext(middleStart);
      middleStart.setPrev(middleEnd);
      this.size += other.size();
      end = other.end;
      other.start = null;
      other.end = null;
      other.size = 0;
    }
  }

  public void clear() {
    size = 0;
    start = null;
    end = null;
  }

  public E removeFront() {
    Node temp = getNode(0);
    Node newStart = getNode(1);
    start = newStart;
    return temp.getData();
  }

  private class Node {
    private E data;
    private Node next, prev;

    public Node(E value, Node newNext, Node newPrev) {
      data = value;
      next = newNext;
      prev = newPrev;
    }

    public E getData() {
      return data;
    }

    public Node getNext() {
      return next;
    }

    public Node getPrev() {
      return prev;
    }

    public void setData(E newData) {
      data = newData;
    }

    public void setNext(Node newNext) {
      next = newNext;
    }

    public void setPrev(Node newPrev) {
      prev = newPrev;
    }

    public String toString() {
      return ""+data;
    }
  }

  public static void main(String[] args) {
    MyLinkedList<Integer> test = new MyLinkedList<>();
    test.add(0);
    test.add(1);
    test.add(2);
    System.out.println(test);
    MyLinkedList<Integer> test2 = new MyLinkedList<>();
    test2.add(3);
    test2.add(4);
    test2.add(5);
    test.extend(test2);
    test.removeFront();
    System.out.println(test);


  }
}
