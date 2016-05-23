//A class that has a linked list of LinkedArrayNode
//it is used in MyHashTable class 
public class LinkedArrays<T> {
  protected int size;                 // number of elements
  protected int nodeCount;            // number of LinkedArrayNodes
  protected final int lengthOfArrays; // value initialized in constructor
  protected static final int DEFAULTLENGTHOFARRAYS = 16;
  protected LinkedArrayNode<T> head;        // dummy head node 
  protected LinkedArrayNode<T> tail;		//dummy tail node 
 
  // Workhorse constructor that initializes variables.
  // Throws IllegalArgumentException if lengthOfArray < 0.
  LinkedArrays (int lengthOfArrays)
  {
	  if (lengthOfArrays < 0)
	  {
		  throw new IllegalArgumentException("Error! lengthOfArrays is less than zero");
	  }
	  this.lengthOfArrays = lengthOfArrays;
	  nodeCount = size = 0;
	  //set head.next to tail
	  head = new LinkedArrayNode<T>(null, tail, lengthOfArrays);
	  //set tail.prev to head 
	  tail = new LinkedArrayNode<T>(head, null, lengthOfArrays);
  } 
  // Convenience constructor. Calls the workhorse constructor with 
  // DEFAULTLENGTHOFARRAYS as the argument.
  LinkedArrays()
  {
	  this(DEFAULTLENGTHOFARRAYS);
  }
  //helper method that return the head of the list
  public LinkedArrayNode<T> getHead ()
  {
	  return head;
  }
  //helper method that returns the tail of the list 
  public LinkedArrayNode<T> getTail ()
  {
	  return tail;
  }
  // Make this LinkedArrays logically empty.
  // Target Complexity: O(1)
  // Implementation note: It is not necessary to remove() all of the 
  // elements; instead, some data members can be reinitialized.
  // Target Complexity: O(1)
  public void clear()
  {
	  size = nodeCount = 0; 
	  head = new LinkedArrayNode<T>(null, tail);
	  tail = new LinkedArrayNode<T>(head, null);

  }

  // Returns the number of elements
  // Target Complexity: O(1)
  public int size()
  {
	  return size;
  }
 
  // Returns the number of LinkedArrayNodes.
  // Target Complexity: O(1)
  public int nodeCount()
  {
	  return nodeCount;
  }
 
  // Returns true if there are no elements in this LinkedArrays
  // Target Complexity: O(1)
  public boolean isEmpty( )
  {
	  return (nodeCount == 0);
  }
 
  // Returns the first element that equals x, or null 
  // if there is no such element.
  // Throws IllegalArgumentException if x is null.
  // Target Complexity: O(n) for n elements
  public T getMatch(T x)
  {
	  if (x == null)
	  {
		  throw new IllegalArgumentException("Error! x is null");
	  }
	  LinkedArrayNode<T> current = head.next;
	  //iterate through the linked list
	  while (current != tail)
	  {
		  //use getMatch from the Node class
		  if (current.getMatch(x) == x)
		  {
			  return x;
		  }
		  current = current.next;
	  }
	  return null; 
  }
 
  // Returns true if this LinkedArrays contains the specified element.
  // Throws IllegalArgumentException if x is null. May call getMatch.
  // Target Complexity: O(n)
  public boolean contains (T x)
  {
	  return (getMatch(x) != null);
	 
  }
  //helper method that adds a new node 
  private void addNewNode()
  {
	  LinkedArrayNode<T> newNode = new LinkedArrayNode<T>(tail.prev,tail,lengthOfArrays);
	  LinkedArrayNode <T> temp = tail.prev;
	  tail.prev = newNode;
	  temp.next = newNode;
	  nodeCount++;
  }
  // Insert x into the first LinkedArrayNode with an available space in 
  // its array. 
  // Returns true if x was added.
  // Throws IllegalArgumentException if x is null.
  // Target Complexity: O(number of nodes)
  public boolean add(T x)
  {
	  if (x == null)
	  {
		  throw new IllegalArgumentException("Error! x is null");
	  }
	  //if the list is empty or there is no room in the array of last node 
	  if (nodeCount == 0)
	  {
		  addNewNode();
	  } 
	  LinkedArrayNode<T> current = head.next;
	  while (current != tail)
	  {
		  //if there is space in the array
		 if (current.spaceInArray())
		  {
			 //add
			  current.add(x);
			  size++;
			  return true;
		  }
		  current = current.next;
	  }
	  //if no space in previous nodes then add a new node 
	  addNewNode();
	  current = tail.prev;
	  current.add(x);
	  size++;
	  return true;
  }
 
  // Remove the first occurrence of x if x is present. 
  // Returns a reference to the removed element if it is removed. 
  // When the last data element is removed from a LinkedArrayNode the 
  // node is removed from the LinkedArrays.
  // Throws IllegalArgumentException if x is null.
  // Target Complexity: O(n)
  @SuppressWarnings("unchecked")
  protected T remove(T x)
  {
	  if (x == null)
	  {
		  throw new IllegalArgumentException("Error! x is null");
	  }
	  LinkedArrayNode<T> current = head.next;
	  //iterate through the list 
	  while (current != head)
	  {
		  //pointer to the node to be removed 
		  Object returnType = new Object();
		  returnType = current.remove(x);
		  if (returnType == null)
		  {
			  current = current.next;
		  }
		  else
		  {
	     	  size--;
	     	  if (current.arraySize == 0)
			  {
				  //remove the node because it is empty 
			  	  LinkedArrayNode<T> prevNode = current.prev; 
				  LinkedArrayNode<T> nextNode = current.next; 
				  nodeCount--;
				  prevNode.next = nextNode;
				  nextNode.prev = prevNode;
			  }	 
	     	  return (T) returnType;
		  }
	  }
	  return null;
  }
    
  // Returns a pretty representation of the LinkedArrays.
  // Example: A LinkedArrays with two LinkedArrayNodes that have arrays 
  // of length two. The size of the first array is two and the size of
  // the second array is one: | 4, 2 | 3 |
  public String toString()
  {
	  String str ="|";
	  LinkedArrayNode<T> current = head.next;
	  while (current != tail)
	  {
		  if (!current.toString().equals(""))
		  {
			  str = str  + current.toString() + "|";
		  }
		  current = current.next;
	  }
	  if (str.equals("|"))
	  {
		  str = "";
	  }
	  return str;
  }
}
