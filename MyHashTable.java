import java.util.HashSet;
import java.util.Set;
//A class that hashes the a list and stores 
//the LinkedArrays
public class MyHashTable<T> {
  protected static final int DEFAULTTABLESIZE = 101; 
  protected int size;
  protected int tableSize;
  protected Object[] table;
 
  // Workhorse constructor. The internal table size is tableSize if
  // tableSize is prime or the next prime number that is
  // greater than tableSize if tableSize is not prime.
  public MyHashTable( int tableSize )
  {
	  size = 0;
	  //if the tableSize is a prime number 
	  if (isPrime(tableSize)) 
	  {
		  //set tableSize
		  this.tableSize = tableSize;
	  }
	  else
	  {
		  //find the next prime number
		  this.tableSize = nextPrime(tableSize);
	  }
	  //initialize array 
	  this.table = new Object[this.tableSize];
	
  }
 
  // Convenience constructor. DEFAULTTABLESIZE is 101
  public MyHashTable()
  {
	  this(DEFAULTTABLESIZE);
  }
  
  // Make the hash table logically empty.
  // Target Complexity: O(N)
  @SuppressWarnings("unchecked")
  public void clear( )
  {
	  //loop through the table
	  for (int i =0; i < tableSize; i++)
	  {
		  if (table[i] != null)
		  {
			  //clear each list in the table 
			  ((LinkedArrays<T>) table[i]).clear();  
		  }
	  }
	  size = tableSize = 0;
	  
  }
 
  // Insert x into the hash table. If x is already present, then do 
  // nothing.
  // Throws IllegalArgumentException if x is null.
  @SuppressWarnings("unchecked")
  public void insert(T x)
  {
	  if (x == null)
	  {
		  throw new IllegalArgumentException("Error! x is null");
	  }
	  int hash = myhash(x);
	  //if there is no list 
	  if (table[hash] == null)
	  {
		  //create a new list
		  table[hash] = new LinkedArrays<T>();
		  //add the element to the list 
		  ((LinkedArrays<T>) table[hash]).add(x);
		  size++;  
		  return;
	  }
	  else if (!((LinkedArrays<T>) table[hash]).contains(x))
	  {
		  //add to list 
		  ((LinkedArrays<T>) table[hash]).add(x);
		  size++; 
		  return;
	  }
	  
  }
  
  // Remove x from the hash table.
  // Throws IllegalArgumentException if x is null.
  @SuppressWarnings("unchecked")
  public void remove( T x )
  {
	  if (x == null)
	  {
		  throw new IllegalArgumentException("Error! x is null");
	  }
	  int hash = myhash(x);
	  //remove element from the list 
	 if( ((LinkedArrays<T>) table[hash]).remove(x) != null)
	 {
		 size--;
	 }
  }
 
  // Return true if x is in the hash table
  // Throws IllegalArgumentException if x is null.
  @SuppressWarnings("unchecked")
  public boolean contains(T x )
  {
	  if (x == null)
	  {
		  throw new IllegalArgumentException("Error! x is null");
	  }
	  int hash = myhash(x);
	  return(((LinkedArrays<T>) table[hash]).contains(x));
  }
 
  // Return the first element in the hashed-to LinkedArrays that equals 
  // x, or null if there is no such element.
  // Throws IllegalArgumentException if x is null.
  @SuppressWarnings("unchecked")
  public T getMatch(T x)
  {
	  if (x == null)
	  {
		  throw new IllegalArgumentException("Error! x is null");
	  }
	  int hash = myhash(x);
	  if(((LinkedArrays<T>) table[hash]).getMatch(x) != null)
	  {
		  return x;
	  }
	  return null;
  }
 
  // Returns the number of elements
  // Target Complexity: O(1)
  public int size()
  {
	  return size;
  }
 
  // Returns true if there are no elements.
  // Target Complexity: O(1)
  public boolean isEmpty( )
  {
	  return (size == 0);
  }
 
  // Returns a Set containing all of the T elements in the table. (Set is
  // an interface implemented by classes HashSet and TreeSet.)
  @SuppressWarnings("unchecked")
  public Set<T> toSet()
  {
	  HashSet<T> hashset = new HashSet<T>();
	  //iterate through the table
	  for (int i=0; i < tableSize; i++)
	  {
		  if (table[i] != null)
		  {
			LinkedArrayNode<T> current = (((LinkedArrays<T>) table[i]).getHead()).next;
			Object [] array =current.getArray();
			//iterate through the linked list
			while (current != (((LinkedArrays<T>) table[i]).getTail()))
			{
				//iterate through the array of the linked list
				for (int j =0; j < current.getArraySize(); j++)
				{
					//add each element to the set 
					hashset.add((T) array[j]);
				}
				current = current.next;
			}
			  
		  }
	  }
	  return hashset;
  }
 
  // Returns a pretty representation of the hash table.
  // Uses toString() of LinkedArrays.
  // Example: For a table of size 3
  // Table:
  // 0: | two |
  // 1: | one, four |
  // 2: 
  @SuppressWarnings("unchecked")
  public String toString()
  {
	  String n = "";
	  String str = "Table: ";
	  for (int i=0; i < tableSize ; i++)
	  {
		  if (table[i] == null)
		  {
			  n ="";
		  }
		  else
		  {
			  n = ((LinkedArrays<T>) table[i]).toString();
		  }
		  str = str + i +": " + n +'\n';
	  }
	  return str;
  }
 
  // Increases the size of the table by finding a prime number 
  // (nextPrime) at least as large as twice the current table size. 
  // Rehashes the elements of the hash table when size is greater than 
  // tableSize/2.
  @SuppressWarnings("unchecked")
  protected void rehash()
  {
	  //get the set of elements
	  Set<T> set = toSet();
	  //multiply the the currentSize by 2
	  int newSize = tableSize *2;
	  //if the number is not prime
	  if (!isPrime(newSize))
	  {
		  //get the next prime number
		  newSize = nextPrime (newSize);
	  }
	  //reset table
	  tableSize = newSize;
	  size = 0;
	  table = new Object [tableSize];
	  //add elements to the table 
	  for (Object o: set)
	  {
		  insert((T)o);
	  }  
  }

  // Internal method for computing the hash value from the hashCode of x.
  protected int myhash(T x) {
    int hashVal = x.hashCode( );
    hashVal %= tableSize;
    if( hashVal < 0 )
      hashVal += tableSize;
    return hashVal;
  }
 
  // Internal method to find a prime number at least as large as n. 
  protected static int nextPrime(int n ){
    if( n % 2 == 0 )
      n++;
    for( ; !isPrime( n ); n += 2 )
      ;
    return n;
  }
 
  // Internal method to test if a number is prime. Not an efficient 
  // algorithm. 
  protected static boolean isPrime(int n ) {
    if( n == 2 || n == 3 )
      return true;
    if( n == 1 || n % 2 == 0 )
      return false;
    for( int i = 3; i * i <= n; i += 2 )
      if( n % i == 0 )
        return false;
    return true;
  }
}