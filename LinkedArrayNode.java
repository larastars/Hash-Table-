//A node class that holds data for the node 
//this class is used in LinkedArrays
public class LinkedArrayNode<T> {
  protected LinkedArrayNode<T> prev;    //previous node
  protected LinkedArrayNode<T> next;	//next node 
  protected Object[] array;          // array holds T objects
  protected static final int DEFAULTLENGTHOFARRAY = 16;
  protected int arraySize;    // number of elements in the array.
 
 
  // Workhorse constructor. Initialize prev and next and the size of the
  // array, and create an array of Objects of the specified length.
  // Parameter lengthOfArray is used to create array. The value of 
  // lengthOfArray need not be saved since it is essentially array.length.
  // Throws IllegalArgumentException if lengthOfArray < 0.
  public LinkedArrayNode(LinkedArrayNode<T> prev, LinkedArrayNode<T> next, int lengthOfArray)
  {	
	  if (lengthOfArray < 0)
	  {
		  throw new IllegalArgumentException("Error! array size is < 0");
	  }
	  this.prev = prev; 
	  this.next = next; 
	  this.arraySize = 0;
	  this.array = new Object[lengthOfArray] ;
  }
 
  // Convenience constructor. Calls the workhorse constructor with 
  // DEFAULTLENGTHOFARRAY as the argument.
  public LinkedArrayNode(LinkedArrayNode<T> prev, LinkedArrayNode<T> next)
  {
	  this(prev, next, DEFAULTLENGTHOFARRAY);

  }
  public Object [] getArray()
  {
	  return array;
  }
  public int getArraySize()
  {
	  return arraySize;
  }
 
  // Add element x to the end of the array.
  // Throws IllegalArgumentException if x is null.
  // Target Complexity: O(1)
  public void add(T x)
  {
	 if (x == null)
	 {
		 throw new IllegalArgumentException("Error! x is null");
	 }
	 //add element at arraySize
	 array[arraySize]= x;
	 arraySize++;
  }

 
  // Locate and remove the first element that equals x. This may require 
  // elements to be shifted (left). Returns a reference to the removed 
  // element, or null if the element was not removed.
  // Throws IllegalArgumentException if x is null.
  // Target Complexity: O(n)
  public T remove(T x)
  {
	  if (x == null)
		 {
			 throw new IllegalArgumentException("Error! x is null");
		 }
	  //loop through the array
	  for (int i =0; i < arraySize; i++)
	  {
		  if (array[i].equals(x))
		  {
			  //shift the array
			  shift(i);
		  	  arraySize--;
		  	  return x;
		  }
	  }
	  return null;
  }
  //a helper method that shifts the digits in the array 
  private void shift(int i)
  {
	  //using try catch block to get ride of indexOutOfBoundsExceptions 
	  try
	  {
		  while(array [i+1]!= null)
		  {
			  array[i] = array[i+1];
			  i++;
		  }
	  }
	  catch (IndexOutOfBoundsException e)
	  {
		  return;
	  }
	  
  }
  
  // Returns a reference to the first element in the array that equals x, or 
  // null if there is no matching element. 
  // Throws IllegalArgumentException if x is null.
  // Target Complexity: O(N)
  public T getMatch(T x)
  {
	  if (x == null)
		 {
			 throw new IllegalArgumentException("Error! x is null");
		 }
	  //loop through array
	  for (int i =0; i < arraySize; i++)
	  {
		  if (array[i].equals(x))
			  return x;
	  }
	  return null;  
  }
  //helper method that returns true if there are more spaces 
  //in the array 
  public boolean spaceInArray()
  {
	  return (arraySize < array.length);
  }
  // toString() - create a pretty representation of the node by
  // showing all of the elements in the array.
  // Target Complexity: O(n)
  // Example: an array with size four and length five: 1, 2, 4, 6
  public String toString()
  {
	  String str = "";
	  //loop through the array
	  for(int i =0; i <arraySize; i++)
	  {
		  //add each element to str
		  str = str + array[i] +",";
	  }
	  //trim the comma at the end of the string
	  if (!str.equals(""))
	  {
		  str = str.substring(0, str.length()-1);
	  }
	  return str;
  }

}

