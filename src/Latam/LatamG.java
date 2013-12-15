import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class LatamG 
{
	static class Scanner
    {
            char[] buffer;
            InputStreamReader isr;
            int p;
            int start;
            
            public Scanner()
            {
                    buffer = new char[40000001];
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
    		
    		public void printLine(BufferedWriter bw, int... vals) throws IOException
    		{
    			if(vals.length == 0)
    				bw.write('\n');
    			else
    			{
    				bw.write(String.valueOf(vals[0]));
    				for(int i = 1; i < vals.length; i++)
    				{
    					bw.write(" ");
    					bw.write(String.valueOf(vals[i]));
    				}
    				bw.write('\n');
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
	
	static class RMQ
	{
		final static int[] M = new int[400004];
		final int[] heights;
		
		public RMQ(int size, int[] h)
		{
			Arrays.fill(M, Integer.MAX_VALUE);
			heights = h;
			init(1, 0, heights.length - 1);
		}

		void init(int node, int b, int e)
		{
			if (b == e)
			{
				M[node] = heights[b];
				return;
			}
			init((node << 1), b, ((b + e) >> 1));
			init((node << 1) + 1, ((b + e) >> 1) + 1, e);
			M[node] = Math.min(M[(node << 1)], M[(node << 1) + 1]);
		}
		
		//it's initially called query(1, 0, size - 1, i, j)
		long query(int node, int b, int e, int i, int j)
		{
			long p1, p2;
			
			//if the current interval doesn't intersect 
			//the query interval return -1
			if (i > e || j < b)
				return -1;
			
			//if the current interval is completely included in 
			//the query interval return the value of this node
			if (b >= i && e <= j)
				return M[node];

			//compute the value from
			//left and right part of the interval
			p1 = query((node << 1), b, ((b + e) >> 1), i, j);
			p2 = query((node << 1) + 1, ((b + e) >> 1) + 1, e, i, j);
			//join them to generate result
			if(p1 == -1)
				return p2;
			if(p2 == -1)
				return p1;
			long tmp = Math.min(p1, p2);
			return tmp;
		}
	}
	
	static class SegmentTree
	{
		final static int[] M = new int[400004];
		final int[] heights;
		
		public SegmentTree(int size, int[] h)
		{
			Arrays.fill(M, -1);
			heights = h;
			init(1, 0, heights.length - 1);
		}

		void init(int node, int b, int e)
		{
			if (b == e)
			{
				M[node] = b;
				return;
			}
			init((node << 1), b, ((b + e) >> 1));
			init((node << 1) + 1, ((b + e) >> 1) + 1, e);
			if(heights[M[(node << 1)]] > heights[M[(node << 1) + 1]])
				M[node] = M[(node << 1)];
			else
				M[node] = M[(node << 1) + 1];
		}
		
		//it's initially called query(1, 0, size - 1, i, j)
		int query(int node, int b, int e, int i, int j, int max, boolean leftMost)
		{
			if (i > e || j < b)
				return -1;
			if(heights[M[node]] <= max)
				return -1;
			if(b == e)
				return heights[M[node]] > max ? M[node] : -1;
			else if(leftMost)
			{
				if((heights[M[(node << 1)]] <= max) || (i > ((b + e) >> 1)) || (j < b))
					return query((node << 1) + 1, ((b + e) >> 1) + 1, e, i, j, max, leftMost);
				else
					return query((node << 1), b, ((b + e) >> 1), i, j, max, leftMost);
			}
			else
			{
				if(heights[M[(node << 1) + 1]] <= max || (i > e) || (j < ((b + e) >> 1) + 1))
					return query((node << 1), b, ((b + e) >> 1), i, j, max, leftMost);
				else
					return query((node << 1) + 1, ((b + e) >> 1) + 1, e, i, j, max, leftMost);
			}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
//		System.setIn(new FileInputStream("f.in"));
//		System.setOut(new PrintStream("f.out"));
		Scanner sc = new Scanner();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		while(true)
		{
			Integer n = sc.nextInteger();
			if(n == null)
			{
				bw.flush();
				bw.close();
				return;
			}
			int[] heights = sc.nextIntArray(n);
			RMQ rmq = new RMQ(heights.length, heights);
			SegmentTree segmentTree = new SegmentTree(heights.length, heights);
			ArrayList <Integer> all = new ArrayList <Integer> ();
			for(int i = 0; i < heights.length; i++)
			{
				int leftMost = segmentTree.query(1, 0, heights.length - 1, 0, i, heights[i], true);
				int rightMost = segmentTree.query(1, 0, heights.length - 1, i, heights.length - 1, heights[i], false);
				if(leftMost == -1 && rightMost == -1)
				{
					if(heights[i] >= 150000)
						all.add(i + 1);
				}
				else if(leftMost == -1)
				{
					int min = (int) rmq.query(1, 0, heights.length - 1, i, rightMost);
					if(heights[i] - min >= 150000)
						all.add(i + 1);
				}
				else if(rightMost == -1)
				{
					int min = (int) rmq.query(1, 0, heights.length - 1, leftMost, i);
					if(heights[i] - min >= 150000)
						all.add(i + 1);
				}
				else
				{
					int minA = (int) rmq.query(1, 0, heights.length - 1, i, rightMost);
					int minB = (int) rmq.query(1, 0, heights.length - 1, leftMost, i);
					if(heights[i] - Math.max(minA, minB) >= 150000)
						all.add(i + 1);
				}
			}
			sc.printLine(bw, sc.unwrap(all));
		}
	}
}