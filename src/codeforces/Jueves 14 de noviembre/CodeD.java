import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CodeD 
{
	static class Scanner
    {
            char[] buffer;
            InputStreamReader isr;
            int p;
            int start;
            
            public Scanner()
            {
                    buffer = new char[20000001];
                    isr = new InputStreamReader(System.in);         
                    read(0);
            }
            
            void read(int init)
            {
                    try
                    {
                            int tam;
                            if((tam = isr.read(buffer, init, buffer.length - init)) < buffer.length - init)
                            {
                                    if(tam < 0)
                                            tam = 0;
                                    buffer[tam] = '\0';
                            }
                            else
                            	throw(new RuntimeException());
                    }
                    catch(Exception e)
                    {
                            throw(new RuntimeException());
                    }
            }
            
            public void readNext()
            {
                    int tam = buffer.length;
                    int pos = p;
                    while(pos < tam && buffer[pos] <= ' ')
                            pos++;
                    if(pos == tam)
                            throw new RuntimeException();
                    start = pos;
                    while(pos < tam && buffer[pos] > ' ')
                            pos++;
                    if(pos == tam)
                        throw new RuntimeException();
                    p = pos;
            }
            
            public String next()
            {
                    readNext();
                    return new String(buffer, start, p - start);
            }
            
            
            public long nextLong()
            {
            	readNext();
            	int d = start;
            	int pos = p;
            	long r = 0;
            	boolean n = buffer[d] == '-';
            	if(n)
            		d++;
            	r -= buffer[d++] - '0';
            	while (d < pos && (r = (r << 1) + (r << 3)) <= 0)
            		r -= buffer[d++] - '0';
            	return n ? r : -r;
            }
            
            public int nextInt()
            {
                    return (int) nextLong();
            }
            
            public double nextDouble()
            {
                    return Double.parseDouble(next());
            }
            
            public String nextLine()
            {
                    int tam = buffer.length;
                    int pos = p;
                    while(pos < tam && buffer[pos] != '\n')
                            pos++;
                    if(pos == tam)
                    {
                            read(0);
                            return nextLine();
                    }
                    start = ++pos;
                    while(pos < tam && buffer[pos] != '\n')
                            pos++;
                    if(pos >= tam)
                    {
                            System.arraycopy(buffer, start, buffer, 0, buffer.length - start);
                            read(buffer.length - start);
                            return nextLine();
                    }
                    p = pos;
                    return new String(buffer, start, p - start);
            }
            
            public boolean EOF()
            {
                    int tam = buffer.length;
                    int pos = p;
                    while(pos < tam && buffer[pos] <= ' ')
                            if(buffer[pos++] == '\0')
                                    return true;
                    pos++;
                    if(pos >= tam)
                    {
                            if(p == 0)
                                    return false;
                            System.arraycopy(buffer, p, buffer, 0, buffer.length - p);
                            read(buffer.length - p);
                            p = 0;
                            return EOF();
                    }
                    return false;
            }
            
            public int[] nextIntArray(int n)
    		{
    			int[] res = new int[n];
    			for(int i = 0; i < res.length; i++)
    				res[i] = nextInt();
    			return res;
    		}
    		
    		public long[] nextLongArray(int n)
    		{
    			long[] res = new long[n];
    			for(int i = 0; i < res.length; i++)
    				res[i] = nextLong();
    			return res;
    		}
    		
    		public double[] nextDoubleArray(int n)
    		{
    			double[] res = new double[n];
    			for(int i = 0; i < res.length; i++)
    				res[i] = nextDouble();
    			return res;
    		}
    		public void sortIntArray(int[] array)
    		{
    			Integer[] vals = new Integer[array.length];
    			for(int i = 0; i < array.length; i++)
    				vals[i] = array[i];
    			Arrays.sort(vals);
    			for(int i = 0; i < array.length; i++)
    				array[i] = vals[i];
    		}
    		
    		public void sortLongArray(long[] array)
    		{
    			Long[] vals = new Long[array.length];
    			for(int i = 0; i < array.length; i++)
    				vals[i] = array[i];
    			Arrays.sort(vals);
    			for(int i = 0; i < array.length; i++)
    				array[i] = vals[i];
    		}
    		
    		public void sortDoubleArray(double[] array)
    		{
    			Double[] vals = new Double[array.length];
    			for(int i = 0; i < array.length; i++)
    				vals[i] = array[i];
    			Arrays.sort(vals);
    			for(int i = 0; i < array.length; i++)
    				array[i] = vals[i];
    		}

    		public String[] nextStringArray(int n) 
    		{	
    			String[] vals = new String[n];
    			for(int i = 0; i < n; i++)
    				vals[i] = next();
    			return vals;
    		}
    		
    		public Integer nextInteger()
    		{
    			try
    			{
    				int n = nextInt();
    				return n;
    			}
    			catch(Exception e)
    			{
    				return null;
    			}
    		}
    		
    		public Long nextLLong()
    		{
    			try
    			{
    				long n = nextLong();
    				return n;
    			}
    			catch(Exception e)
    			{
    				return null;
    			}
    		}
    		
    		public int[][] nextIntMatrix(int n, int m)
    		{
    			int[][] ans = new int[n][];
    			for(int i = 0; i < n; i++)
    				ans[i] = nextIntArray(m);
    			return ans;
    		}
    		
    		public char[][] nextGrid(int r) 
    		{
    			char[][] grid = new char[r][];
    			for(int i = 0; i < r; i++)
    				grid[i] = next().toCharArray();
    			return grid;
    		}
    		
    		public static <T> T fill(T arreglo, int val)
    		{
    			if(arreglo instanceof Object[])
    			{
    				Object[] a = (Object[]) arreglo;
    				for(Object x : a)
    					fill(x, val);
    			}
    			else if(arreglo instanceof int[])
    				Arrays.fill((int[]) arreglo, val);
    			else if(arreglo instanceof double[])
    				Arrays.fill((double[]) arreglo, val);
    			else if(arreglo instanceof long[])
    				Arrays.fill((long[]) arreglo, val);
    			return arreglo;
    		}
    		
    		<T> T[] nextObjectArray(Class <T> clazz, int size)
    		{
    			@SuppressWarnings("unchecked")
    			T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
    			for(int c = 0; c < 3; c++)
    			{
    				Constructor <T> constructor;
    				try 
    				{
    					if(c == 0)
    						constructor = clazz.getDeclaredConstructor(Scanner.class, Integer.TYPE);
    					else if(c == 1)
    						constructor = clazz.getDeclaredConstructor(Scanner.class);
    					else
    						constructor = clazz.getDeclaredConstructor();
    				} 
    				catch(Exception e)
    				{
    					continue;
    				}
    				try
    				{
    					for(int i = 0; i < result.length; i++)
    					{
    						if(c == 0)
    							result[i] = constructor.newInstance(this, i);
    						else if(c == 1)
    							result[i] = constructor.newInstance(this);
    						else
    							result[i] = constructor.newInstance();	
    					}
    				}
    				catch(Exception e)
    				{
    					throw new RuntimeException(e);
    				}
    				return result;
    			}
    			throw new RuntimeException("Constructor not found");
    		}
    		
    		public void printLine(int... vals)
    		{
    			if(vals.length == 0)
    				System.out.println();
    			else
    			{
    				System.out.print(vals[0]);
    				for(int i = 1; i < vals.length; i++)
    					System.out.print(" ".concat(String.valueOf(vals[i])));
    				System.out.println();
    			}
    		}
    		
    		public void printLine(long... vals)
    		{
    			if(vals.length == 0)
    				System.out.println();
    			else
    			{
    				System.out.print(vals[0]);
    				for(int i = 1; i < vals.length; i++)
    					System.out.print(" ".concat(String.valueOf(vals[i])));
    				System.out.println();
    			}
    		}
    		
    		public void printLine(double... vals)
    		{
    			if(vals.length == 0)
    				System.out.println();
    			else
    			{
    				System.out.print(vals[0]);
    				for(int i = 1; i < vals.length; i++)
    					System.out.print(" ".concat(String.valueOf(vals[i])));
    				System.out.println();
    			}
    		}
    		
    		public void printLine(int prec, double... vals)
    		{
    			if(vals.length == 0)
    				System.out.println();
    			else
    			{
    				System.out.printf("%." + prec + "f", vals[0]);
    				for(int i = 1; i < vals.length; i++)
    					System.out.printf(" %." + prec + "f", vals[i]);
    				System.out.println();
    			}
    		}
    		
    		public Collection <Integer> wrap(int[] as)
    		{
    			ArrayList <Integer> ans = new ArrayList <Integer> ();
    			for(int i : as)
    				ans.add(i);
    			return ans;
    		}
    		
    		public int[] unwrap(Collection <Integer> collection)
    		{
    			int[] vals = new int[collection.size()];
    			int index = 0;
    			for(int i : collection) vals[index++] = i;
    			return vals;
    		}
    }
	
	static int n, m, a;
	
	static int[] personalMoney, bikes;
	
	
	static long[] trySolve(int x)
	{
		if(x > bikes.length || x > personalMoney.length)
			return null;
		long currentShared = a;
		boolean could = true;
		long allBikes = 0;
		int currentPersonIndex = personalMoney.length - x;
		int currentBikeIndex = 0;
		while(currentPersonIndex < personalMoney.length)
		{
			int currentPerson = personalMoney[currentPersonIndex++];
			int currentBike = bikes[currentBikeIndex++];
			allBikes += currentBike;
			if(currentPerson < currentBike)
			{
				long needed = currentBike - currentPerson;
				if(needed > currentShared)
					return null;
				currentShared -= needed;
			}
		}
		return new long[]{x, Math.max(0, allBikes - a)};
	}
	
	static long[] binarySearch(int from, int to)
	{
		if(from == to)
			return trySolve(from);
		else if(from + 1 == to)
		{
			long[] right = trySolve(to);
			if(right != null)
				return right;
			else
				return trySolve(from);
		}
		int mid = (from + to) >>> 1;
		long[] possible = trySolve(mid);
		if(possible == null)
			return binarySearch(from, mid - 1);
		else
			return binarySearch(mid, to);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		m = sc.nextInt();
		a = sc.nextInt();
		personalMoney = sc.nextIntArray(n);
		Arrays.sort(personalMoney);
		bikes = sc.nextIntArray(m);
		sc.sortIntArray(bikes);
		long[] ans = binarySearch(0, n);
		sc.printLine(ans);
	}
}