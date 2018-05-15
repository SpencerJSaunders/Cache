import java.util.NoSuchElementException;

/*
@Spencer Saunders
Cache class that handles adding, removing, getting, and other various methods to represent a Cache using a Double Linked List implementation. 
*/
//Cache deals with abstract object types of T and the interface references abstract object types of T
	public class Cache<T> implements ICache<T>{


		//initialize our nodes, capacity, incrementers
		private int size;
		private DLLNode<T> head, tail, dummyNode;
		private int modCount;
		private int capacity;
		private double hit, miss, numAccess;
		
		//constructor with a default capacity of 100
		public Cache()
		{
			capacity = 100;
			
			dummyNode = new DLLNode<T>(null);
			
			head = dummyNode;
			tail = dummyNode;
			modCount = 0;
			capacity = 100;
			size = 0;
		}
		
   //constructor that takes in a capacity paramter
  
		public Cache(int cacheSize)
		{
				
			dummyNode = new DLLNode<T>(null);
			
			head = dummyNode;
			tail = dummyNode;
			capacity = cacheSize;
			
			
			size = 0;
		}
		
		
   //method to add a T element into a Cache
		public void add(T element) {

			DLLNode<T> newNode = new DLLNode<T>(element);
			
      //if there's no elements in Cache, set head and tail equal to newNode
			if (isEmpty()) {
				head = newNode;
				tail = head;
			}
			

			else {
				newNode.setNext(head);
				head.setPrevious(newNode);
				head = newNode;
				
			}
			
			
			
			size++;
			
			if(size > capacity)
			{
				removeLast();
			}
			
		}
		
		
		public void removeLast() {
			
			if (isEmpty()) {
				throw new IllegalStateException("IUDoubleLinkedList - removeLast");
			}
	        
	      //create a new T element that grabs the last node, tail, in our DLL
			T last = tail.getElement();

			if (size == 1) {
				head = tail = null;
			} else {
	          //move the tail back one and set the element's node after equal to null
				tail = tail.getPrevious();
				tail.getNext().setPrevious(null);
				tail.setNext(null);
			}

			size--;
		}
		

		@Override
   //method to get a desired T element target
   //@return head.getElement()
		public T get(T target) 
		{

			DLLNode<T> currentNode = head;
			for(int i = 0; i < size; i++)
			{
      //increment hit if we locate element inside one of the cache levels
				if(currentNode.getElement().equals(target))
				{
					hit++;
					return head.getElement();
				}
				else
				{
					currentNode = currentNode.getNext();
				}
				}
			//if we don't find the element in either of the caches (or one) then increment the miss variable
			miss++;
			
			return null;

			
		}
	

		@Override
   //method to clean out a Cache by removing every element
		public void clear() {
			
			while(size > 0)
			{
				removeLast();
			}
			
		}
        public int size(){
        	
        	return size;
        }


//method that checks to see if a T element is located within a Cache
//@return found
    	public boolean contains(T target) {
    		boolean found = false;
    		DLLNode<T> current = head;

    		for (int i = 0; i < size; i++) {

    			if (current.getElement().equals(target)) {
    				found = true;
    			}
    			current = current.getNext();

    		}
    		
    		//returns the boolean flag
    		return found;
    	}

		@Override
   //method that removes a specific target
		public void remove(T target) {
			
      
      //throw an exception if there's no elements in the Cache
			if(isEmpty())
			{
				throw new NoSuchElementException("No elements");
			}
			
            if(!contains(target))
            {
            	throw new NoSuchElementException("");
            }
            
			if (size == 1) {
				head = tail = null;
				size--;
			}
			
			else if(head.getElement() == target)
			{
				head = head.getNext();
				head.setPrevious(null);
				size--;
			}
			
			else if(tail.getElement() == target)
			{
				tail = tail.getPrevious();
				tail.getNext().setPrevious(null);
				tail.setNext(null);
				size--;
			}
             
			else
			{
				DLLNode<T> current = head.getNext();
				//we already checked head and tail so start at index 1 and end 1 early
				for(int i = 1; i < size; i++)
				{
				  if(current.getElement() == target)
				  {
             //update pointers to stop pointing at removed target T element
					  DLLNode<T> previous = current.getPrevious();
					  DLLNode<T> next = current.getNext();
					  previous.setNext(next);
					  next.setPrevious(previous);
					  current.setNext(null);
					  current.setPrevious(null);
					  size--;
					  break;

				  }
              
              //update where current will be pointing at
				current = current.getNext();
			
			}
	        //return the element we removed
		}
		}

		@Override
   //if we find our target, then remove and add target T element
		public void write(T target) {

			
			if(!contains(target))
			{
			   throw new NoSuchElementException("");

			}
			
			remove(target);
			add(target);
						

		}			

	
		
		@Override
   //checks if the cache contains any elements
   //@return size == 0
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return (size == 0);
		}



		//returns our total number of hits
		public double totalHit()
		{
			return hit;
		}
		
  //returns our total number of accesses  
  //@return hit + miss
		public double totalAccesses()
		{
			return hit + miss;
		}
		
	
		@Override
   //returns our hit rate
   //@return (hit/miss)*100/100
		public double getHitRate() {
			// TODO Auto-generated method stub
			return (hit/miss)*100/100;
		}

		@Override
		public double getMissRate() {
			// TODO Auto-generated method stub
			return 1-hit;
		}


	}
	
	
			