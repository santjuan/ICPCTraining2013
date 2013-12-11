import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class traffic 
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

	static class PushRelabel 
	{
		int N;
		ArrayList <Edge> [] G;
		long[] excess;
		int[] dist, count;
		boolean[] active;
		ArrayDeque <Integer> Q = new ArrayDeque <Integer> ();

		@SuppressWarnings("unchecked")
		PushRelabel(int N1)
		{
			N = N1;
			G = new ArrayList[N];
			for(int i = 0; i < N; i++)
				G[i] = new ArrayList <Edge> ();
			excess = new long[N];
			dist = new int[N];
			active = new boolean[N];
			count = new int[2 * N];
		}
		
		void clear(int n)
		{
			N = n;
			for(int i = 0; i < N; i++)
				G[i].clear();
			for(int i = 0; i < N; i++)
			{
				excess[i] = 0;
				dist[i] = 0;
				active[i] = false;
			}
			int dosN = 2 * N;
			for(int i = 0; i < dosN; i++)
				count[i] = 0;
		}

		void AddEdge(int from, int to, int cap) 
		{
			int cambio = from == to ? 1 : 0;
			G[from].add(new Edge(from, to, cap, 0, G[to].size() + cambio));
			G[to].add(new Edge(to, from, 0, 0, G[from].size() - 1));
		}

		void Enqueue(int v) 
		{ 
			if (!active[v] && excess[v] > 0)
			{ 
				active[v] = true;
				Q.add(v); 
			} 
		}

		void Push(Edge e) 
		{
			long amt = Math.min(excess[e.from], e.cap - e.flow);
			if(dist[e.from] <= dist[e.to] || amt == 0) 
				return;
			e.flow += amt;
			G[e.to].get(e.index).flow -= amt;
			excess[e.to] += amt; 
			excess[e.from] -= amt;
			Enqueue(e.to);
		}

		void Gap(int k) 
		{
			for(int v = 0; v < N; v++) 
			{
				if(dist[v] < k) 
					continue;
				count[dist[v]]--;
				dist[v] = Math.max(dist[v], N + 1);
				count[dist[v]]++;
				Enqueue(v);
			}
		}

		void Relabel(int v) 
		{
			count[dist[v]]--;
			dist[v] = 2 * N;
			for (Edge e : G[v]) 
				if (e.cap - e.flow > 0)
					dist[v] = Math.min(dist[v], dist[e.to] + 1);
			count[dist[v]]++;
			Enqueue(v);
		}

		void Discharge(int v) 
		{
			for(Edge e : G[v])
			{
				if(excess[v] <= 0)
					break;
				Push(e);
			}
			if(excess[v] > 0) 
			{
				if(count[dist[v]] == 1) 
					Gap(dist[v]); 
				else
					Relabel(v);
			}
		}

		long GetMaxFlow(int s, int t)
		{
			count[0] = N - 1;
			count[N] = 1;
			dist[s] = N;
			active[s] = active[t] = true;
			for (Edge e : G[s]) 
			{
				excess[s] += e.cap;
				Push(e);
			}
			while (!Q.isEmpty()) 
			{
				int v = Q.poll();
				active[v] = false;
				Discharge(v);
			}
			long totflow = 0;
			for (Edge e : G[s]) 
				totflow += e.flow;
			return totflow;
		}
	}


	
	static class EdgeR
	{
		int from;
		int to;
		long capacity;
		long originalCapacity;
		String name;
		
		public EdgeR(Scanner sc)
		{
			String line1 = sc.nextLine();
			String[] line = line1.split(",");
			from = Integer.parseInt(line[0]);
			to = Integer.parseInt(line[1]);
			capacity = Integer.parseInt(line[2]);
			originalCapacity = capacity;
			name = line[3];
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		for(int i = 0; i < n; i++)
		{
			int m = sc.nextInt();
			int e = sc.nextInt();
			EdgeR[] allEdges = sc.nextObjectArray(EdgeR.class, e);
			PushRelabel push = new PushRelabel(m);
			for(EdgeR edge : allEdges)
				push.AddEdge(edge.from, edge.to, (int) edge.capacity);
			long normal = push.GetMaxFlow(0, m - 1);
			long max = Integer.MIN_VALUE;
			String maxS = null;
			for(EdgeR edge : allEdges)
			{
				push = new PushRelabel(m);
				String nombre = edge.name;
				for(EdgeR edge1 : allEdges)
					if(edge1.name.equals(nombre))
						edge1.capacity = Integer.MAX_VALUE / 4;
					else
						edge1.capacity = edge1.originalCapacity;
				for(EdgeR edge1 : allEdges)
					push.AddEdge(edge1.from, edge1.to, (int) edge1.capacity);
				long flow = push.GetMaxFlow(0, m - 1);
				if(flow >= max)
				{
					max = flow;
					maxS = edge.name;
				}
			}
			System.out.println(maxS + " " + (max - normal));
		}
	}
}