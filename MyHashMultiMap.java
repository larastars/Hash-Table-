import java.util.*;
//A map class that is an instance of hash table that has
//key and value 
public class MyHashMultiMap<KeyType, ValueType>{
 
  // Hash table to hold key/value pairs 
  //instance of a hashtable 
  protected MyHashTable <Entry <KeyType,ValueType> >items;
  // Constructor
  public MyHashMultiMap()
  {
	  items = new MyHashTable <Entry <KeyType, ValueType>>();
  }
 
  // Associates the specified key with the specified value in this map.
  // If key is already associated with a List of values, then value
  // is added to this list; otherwise, a new List is created, value is 
  // added to this List, and key is associated with this new List.
  @SuppressWarnings("unchecked")
  public void put(KeyType key, ValueType value)
  {
	  if (key == null)
	  {
		  throw new IllegalArgumentException("Error! Key is null");
	  }
	  else if (value == null)
	  {
		  throw new IllegalArgumentException("Error! value is null");
	  }
	  else
	  { 
		  //insert an entry in the table
		 items.insert(makeEntry(key, newList()));
	  }
  }
 //helper method that makes a new list 
  private LinkedList<ValueType> newList()
  {
	 return new LinkedList<ValueType>();
  }
  // Returns the List of values that key is associated with, or null if 
  // there is no mapping for key.
  @SuppressWarnings("unchecked")
  public List<ValueType> get(KeyType key)
  {
	  if (key == null)
	  {
		  throw new IllegalArgumentException("Error! key is null");
	  }
	  
	  
	  
	  if (items.table[key.hashCode()] != null)
	  {
		  Entry n = makeEntry(key);
		  //returns the list in the table 
		 if (n.valueList != null)
		 {
			 return n.valueList;
		 }
		  
				  //((Entry) (items.table[key.hashCode()])).valueList;
	  } 
	return null; 
	
  }
 
  // Returns the number of elements
  // Target Complexity: O(1)
  public int size()
  {
	  return items.size;
  }
 
  // Returns true if there are no elements.
  // Target Complexity: O(1)
  public boolean isEmpty()
  {
	  return (items.size == 0);
  }
 
  // Make the map logically empty.
  // Target Complexity: O(1)
  public void clear()
  {
	  //reset items
	  items = new MyHashTable <Entry <KeyType, ValueType>>(); 
	  items.size = 0;
  }
 
  // Returns a Set of the Entries contained in this map.
  @SuppressWarnings("unchecked")
  public Set<Entry<KeyType, ValueType>> entrySet()
  {
	  return items.toSet();  
  }
  
  // Returns the MyHashTable<T> of items
  protected MyHashTable<Entry<KeyType,ValueType>> getItems()
  {
	return items;
	  
  }
  
 
  // A helper method that returns an Entry created from the 
  // specified key and List. Called by put and get.
  protected MyHashMultiMap.Entry<KeyType,ValueType> 
  makeEntry( KeyType key, LinkedList<ValueType> valueList ) {
     return new MyHashMultiMap.Entry<KeyType, ValueType>(key,valueList);
  }
 
  // A helper method for creating an Entry from a key value.
  // Called by put and get.
  protected MyHashMultiMap.Entry<KeyType,ValueType> makeEntry( KeyType key ) {
     return makeEntry(key, null );
  }
 
  public static class Entry<KeyType, ValueType> {
	  protected KeyType key;
	  protected LinkedList<ValueType> valueList;
	 
	  // Constructor
	  // Target Complexity: O(1)               
	  public Entry( KeyType k, LinkedList<ValueType> list )
	  {
		  key = k;
		  valueList = list;		  
	  }
	 
	  // Returns the hash code value for this map entry. 
	  // The hashCode of the Entry is the hashCode of the Entry's key, i.e,
	  // the Entry’s value is ignored during hashing.
	  public int hashCode()
	  {
		  //hash the key
		  return key.hashCode();
	  }
	 
	  // Compares the specified object with this entry for equality.
	  // The equality of two Entries is based on the equality of their keys.
	  @SuppressWarnings("unchecked")
	  public boolean equals( Object rhs )
	  {
		  if (this == rhs)
			  return true;
		  if(!(rhs instanceof MyHashMultiMap<?, ?>))
			  return false;
		  Entry<KeyType, ValueType> other =  (Entry<KeyType, ValueType>) rhs;
		  return (this.key == other.key);
	  }
	 
	  // Returns the key corresponding to this entry.          
	  public KeyType getKey()
	  {
		  return key;
	  }
	 
	  // Returns the value corresponding to this entry.
	  public LinkedList<ValueType> getValue()
	  {
		  return valueList;
	  }
	 
	  // Returns a pretty representation of the Entry.
	  // Format: key, valueList. Example: When key = "Carver" and valueList = 
	  // [CS310-003, SWE437-001]: “Carver, [CS310-003, SWE437-001]”
	  // Note: “[a, b]” is returned by LinkedList.toString for a LinkedList 
	  // containing Strings “a” and “b”.
	  public String toString()
	  {
		  return key +", " + valueList.toString();
	  }
	}
}