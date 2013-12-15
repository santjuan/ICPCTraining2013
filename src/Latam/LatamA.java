import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class LatamA 
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
	
	static class Edge 
	{
		int from, to, index;
		long cap, flow;

		Edge(int fromi, int toi, long capi, long flowi, int indexi)
		{
			from = fromi;
			to = toi;
			cap = capi;
			flow = flowi;
			index = indexi;
		}
	}

	static class Dinic 
	{
		  int N;
		  ArrayList <Edge> [] G;
		  Edge[] dad;
		  int[] Q;
		  
		  @SuppressWarnings("unchecked")
		Dinic(int N1)
		  {
			  N = N1;
			  G = new ArrayList[N];
			  for(int i = 0; i < N; i++)
				  G[i] = new ArrayList <Edge> ();
			  dad = new Edge[N];
			  Q = new int[N];
		  }
		  
		  void AddEdge(int from, int to, int cap)
		  {
		    G[from].add(new Edge(from, to, cap, 0, G[to].size()));
		    if (from == to) G[from].get(G[from].size() - 1).index++;
		    G[to].add(new Edge(to, from, 0, 0, G[from].size() - 1));
		  }

		  Edge especial = new Edge(0, 0, 0, 0, 0);
		  
		  long BlockingFlow(int s, int t)
		  {
			  Arrays.fill(dad, null);
			  dad[s] = especial;
		    
		    int head = 0, tail = 0;
		    Q[tail++] = s;
		    while (head < tail)
		    {
		      int x = Q[head++];
		      for (Edge e : G[x]) 
		      {
				    if (dad[e.to] == null && e.cap - e.flow > 0) 
				    {
				      dad[e.to] = e;
				      Q[tail++] = e.to;
				    }
		      }
		    }
		    if (dad[t] == null) return 0;

		    long totflow = 0;
		    for (Edge e1 : G[t]) 
		    {
		      Edge start = G[e1.to].get(e1.index);
		      long amt = Long.MAX_VALUE / 4;
		      for (Edge e = start; amt != 0 && e != dad[s]; e = dad[e.from])
		      {
			    if (e == null) { amt = 0; break; }
			    	amt = Math.min(amt, e.cap - e.flow);
			  }
			  if (amt == 0) continue;
			  for (Edge e = start; amt != 0 && e != dad[s]; e = dad[e.from])
			  {
			    e.flow += amt;
			    G[e.to].get(e.index).flow -= amt;
			   }
			   totflow += amt;
		    }
		    return totflow;
		  }

		  long GetMaxFlow(int s, int t) 
		  {
		    long totflow = 0;
		    long flow = 0;
		    while ((flow = BlockingFlow(s, t)) != 0)
		      totflow += flow;
		    return totflow;
		  }
		}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			Integer nI = sc.nextInteger();
			if(nI == null)
				return;
			int n = nI;
			char[][] matrix = sc.nextGrid(n);
			int m = matrix[0].length;
			int[][] rowsIds = new int[n][m];
			int[][] colsIds = new int[n][m];
			int nodeId = 0;
			int source = nodeId++;
			int sink = nodeId++;
			int rowsStart = nodeId;
			int rowsEnd = 0;
			for(int i = 0; i < n; i++)
			{
				boolean lastPawn = true;
				for(int j = 0; j < m; j++)
				{
					if(matrix[i][j] == 'X')
					{
						if(!lastPawn)
							nodeId++;
						lastPawn = true;
					}
					else
					{
						rowsIds[i][j] = nodeId;
						lastPawn = false;
					}
				}
				if(!lastPawn)
					nodeId++;
				rowsEnd = nodeId;
			}
			int colsStart = nodeId;
			int colsEnd = 0;
			for(int i = 0; i < m; i++)
			{
				boolean lastPawn = true;
				for(int j = 0; j < n; j++)
				{
					if(matrix[j][i] == 'X')
					{
						if(!lastPawn)
							nodeId++;
						lastPawn = true;
					}
					else
					{
						colsIds[j][i] = nodeId;
						lastPawn = false;
					}
				}
				if(!lastPawn)
					nodeId++;
				colsEnd = nodeId;
			}
			Dinic dinic = new Dinic(nodeId);
			for(int i = rowsStart; i < rowsEnd; i++)
				dinic.AddEdge(source, i, 1);
			for(int i = colsStart; i < colsEnd; i++)
				dinic.AddEdge(i, sink, 1);
			for(int i = 0; i < n; i++)
				for(int j = 0; j < m; j++)
					if(matrix[i][j] != 'X')
						dinic.AddEdge(rowsIds[i][j], colsIds[i][j], 1);
			System.out.println(dinic.GetMaxFlow(source, sink));
		}
	}
}