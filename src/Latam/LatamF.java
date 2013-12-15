import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

public class LatamF 
{
	static class Scanner
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		public String nextLine()
		{
			try
			{
				return br.readLine();
			}
			catch(Exception e)
			{
				throw(new RuntimeException());
			}
		}
		
		public String next()
		{
			while(!st.hasMoreTokens())
			{
				String l = nextLine();
				if(l == null)
					return null;
				st = new StringTokenizer(l);
			}
			return st.nextToken();
		}
		
		public int nextInt()
		{
			return Integer.parseInt(next());
		}
		
		public long nextLong()
		{
			return Long.parseLong(next());
		}
		
		public double nextDouble()
		{
			return Double.parseDouble(next());
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
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
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
	
	static class SuperScanner
    {
            char[] buffer;
            InputStreamReader isr;
            int p;
            int start;
            
            public SuperScanner()
            {
                    buffer = new char[6000001];
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
    }

	public static void main(String[] args) throws FileNotFoundException
	{
	//	System.setIn(new FileInputStream("f.in"));
		SuperScanner sc = new SuperScanner();
		while(true)
		{
			Integer nI = null;
			try
			{
				nI = sc.nextInt();
			}
			catch(Exception e)
			{
				return;
			}
			if(nI == null)
				return;
			int n = nI;
			int g = sc.nextInt();
			int[][] results = new int[n][2];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < 2; j++)
					results[i][j] = sc.nextInt();
//			PriorityQueue <int[]> pq = new PriorityQueue <int[]> (n, new Comparator<int[]> () 
//			{
//				@Override
//				public int compare(int[] a, int[] b)
//				{
//					return a[0] - b[0];
//				}
//			});
//			int wins = 0;
//			int[][] counts = new int[101][2];
//			for(int i = 0; i <= 100; i++)
//				counts[i][0] = i;
//			for(int[] r : results)
//			{
//				if(r[0] > r[1])
//					wins++;
//				else
//				{
//					int diff = r[1] - r[0];
//					counts[diff][1]++;
//				}
//			}
//			for(int i = 0; i <= 100; i++)
//				if(counts[i][1] != 0)
//					pq.add(counts[i].clone());
//			while(g > 0 && !pq.isEmpty())
//			{
//				int[] first = pq.poll();
//				if(first[1] != counts[first[0]][1])
//					continue;
//				if(first[0] == 0)
//					wins++;
//				counts[first[0]][1]--;
//				if(first[0] != 0)
//					counts[first[0] - 1][1]++;
//				if(counts[first[0]][1] != 0)
//					pq.add(counts[first[0]].clone());
//				if(first[0] != 0)
//					pq.add(counts[first[0] - 1].clone());
//				g--;
//			}
			
//			int draws = 0;
//			if(!pq.isEmpty() && pq.peek()[0] == 0)
//				draws = pq.poll()[1];
			int wins = 0;
			int draws = 0;
			int[] counts = new int[101];
			for(int[] r : results)
			{
				if(r[0] > r[1])
					wins++;
				else
					counts[r[1] - r[0]]++;
			}
			for(int i = 0; i <= 100 && g > 0; i++)
			{
				int toWin = i + 1;
				int possibleWins = g / toWin;
				if(possibleWins >= counts[i])
				{
					wins += counts[i];
					g -= counts[i] * toWin;
					counts[i] = 0;
				}
				else
				{
					wins += possibleWins;
					g -= possibleWins * toWin;
					counts[i] -= possibleWins;
					break;
				}
			}
			draws += counts[0];
			for(int i = 1; i <= 100 && g > 0; i++)
			{
				int toDraw = i;
				int possibleDraws = g / toDraw;
				if(possibleDraws >= counts[i])
				{
					draws += counts[i];
					g -= counts[i] * toDraw;
					counts[i] = 0;
				}
				else
				{
					draws += possibleDraws;
					g -= possibleDraws * toDraw;
					counts[i] -= possibleDraws;
				}
			}
			int ans = wins * 3 + draws;
			System.out.println(ans);
		}
		
	}
}