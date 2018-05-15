import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;
/*
@author Spencer Saunders


Tester class to test efficiency and accuracy of DLL implementation of Cache.

*/
public class Test {

	public static<T> void main(String[] args) throws FileNotFoundException, IOException
	{	
 
    
		String fileName = "";
		Cache cache1 = null;
		Cache cache2 = null;
		T currentToken = null;
        
		//exception to ensure that our args[] paramters are either 3 or 4.
		if(args.length != 3 && args.length != 4)
		{
			throw new IOException("Incorrect command line arguments!");
		}
		
		
		if(args[0].equals("1"))
		{
			if(args[1].equals("100"))
			{
				cache1 = new Cache();
				fileName = args[2];
				System.out.println("L1 cache with " + args[1] + " entries created\n");

			}
			else
			{
				cache1 = new Cache(Integer.parseInt(args[1]));
				fileName = args[2];
				System.out.println("L1 cache with " + args[1] + " entries created\n");

			}
		}
			
			else if(args[0].equals("2"))
			{
				if(args[1].equals("100"))
				{
					cache1 = new Cache();
					cache2 = new Cache(Integer.parseInt(args[2]));
					fileName = args[3];
					
					System.out.println("L1 cache with " + args[1] + " entries created");
					System.out.println("L2 cache with " + args[2] + " entries created\n");

				}
				else
				{
					cache1 = new Cache(Integer.parseInt(args[1]));
					cache2 = new Cache(Integer.parseInt(args[2]));

					fileName = args[3];
					System.out.println("L1 cache with " + args[1] + " entries created");
					System.out.println("L2 cache with " + args[2] + " entries created\n");

				}
			}
		
		
		File file = new File(fileName);
		Scanner scan = new Scanner(file).useDelimiter("\\s+");
	
		
		while(scan.hasNext())
		{
		   currentToken = (T)scan.next();
		   if(cache2 == null)
		   {
			   if(cache1.get(currentToken) != null)
			   {
				   cache1.remove(currentToken);
				   cache1.add(currentToken);
			   }
		   
			   else
			   {
				   cache1.add(currentToken);
			   }
		   }
		   
		   else
		   {
			   
		   if(cache1.get(currentToken) != null)
			   {
				   cache1.remove(currentToken);
				   cache1.add(currentToken);
				   cache2.remove(currentToken);
				   cache2.add(currentToken);
			   
			   }
			    else if(cache2.get(currentToken) != null)
			    {
			    	cache2.remove(currentToken);
			    	cache2.add(currentToken);
			    	cache1.add(currentToken);
			    	
			    }
			    else
			    {
			    	
			    	cache1.add(currentToken);
			    	cache2.add(currentToken);
			    	
			    }
			   }
		
		}
		DecimalFormat dc = new DecimalFormat("0.00");

		   if(cache2 == null)
		   {
			double overallHitRate = (cache1.totalHit())/(cache1.totalAccesses());
			System.out.println("Number of L1 cache hits: " + cache1.totalHit());
			System.out.println("L1 cache hit rate: " + dc.format(cache1.getHitRate()*100) + "%\n");
			System.out.println("Total number of accesses: " + cache1.totalAccesses());
			System.out.println("Total number of cache hits: " + cache1.totalHit());
			System.out.println("Overall hit rate: " + dc.format(overallHitRate*100) + "%");
		   }
			
		   else
		   {	
			
			double totalHits = cache1.totalHit() + cache2.totalHit();
			double cache1HitRate = (cache1.totalHit()/cache1.totalAccesses()*100);
			double cacheHitRate2 = (cache2.totalHit()/cache2.totalAccesses()*100);
			double totalHitRate = (totalHits/cache1.totalAccesses())*100;

			
			System.out.println("Number of L1 cache hits: " + cache1.totalHit());
			System.out.println("L1 cache hit rate: " + dc.format(cache1HitRate) +"%\n");

			System.out.println("Number of L2 cache hits: " + cache2.totalHit());
			System.out.println("L2 cache hit rate: " + dc.format(cacheHitRate2) + "%\n");
			System.out.println("Total number of accesses: " + cache1.totalAccesses());
			System.out.println("Total number of cache hits: " + totalHits);
			System.out.println("Overall hit rate: " + dc.format(totalHitRate) + "%");
		   }

	}		
		}