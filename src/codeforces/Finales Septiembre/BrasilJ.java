import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

public class BrasilJ 
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
	
	static class Query
	{
		int a;
		int b;
		int answer;
		
		Query(int aa, int bb)
		{
			if(aa > bb)
			{
				int tmp = aa;
				aa = bb;
				bb = tmp;
			}
			a = aa;
			b = bb;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + a;
			result = prime * result + b;
			return result;
		}

		@Override
		public boolean equals(Object obj) 
		{
			Query other = (Query) obj;
			if (a != other.a)
				return false;
			if (b != other.b)
				return false;
			return true;
		}
	}
	
	static class Bridge implements Comparable <Bridge>
	{
		int from, to, cost;
		Bridge next;
		
		public Bridge(Scanner sc)
		{
			from = sc.nextInt() - 1;
			to = sc.nextInt() - 1;
			cost = sc.nextInt();
		}

		@Override
		public int compareTo(Bridge o) 
		{
			return o.cost - cost;
		}
	}
	
	static class ListEntry
	{
		int val;
		ListEntry next;
		
		ListEntry(int v, ListEntry n)
		{
			val = v;
			next = n;
		}
	}
	
	static class ConnectedComponent
	{
		LinkedList <Island> islands = new LinkedList <Island> ();
		LinkedHashMap < Integer, ListEntry > pendingEntries = new LinkedHashMap < Integer, ListEntry > ();
		
		public void add(int i, int j)
		{
			if(!pendingEntries.containsKey(i))
				pendingEntries.put(i, new ListEntry(j, null));
			else
				pendingEntries.put(i, new ListEntry(j, pendingEntries.get(i)));
		}
	}
	
	static class Island
	{
		int id;
		ConnectedComponent component;
		
		Island(Scanner sc, int i)
		{
			id = i;
			component = new ConnectedComponent();
			component.islands.add(this);
		}
	}
	
	static HashMap <Query, Query> allQueries = new HashMap <Query, Query> ();
	
	static Query getQuery(int from, int to)
	{
		Query newQuery = new Query(from, to);
		if(!allQueries.containsKey(newQuery))
			allQueries.put(newQuery, newQuery);
		return allQueries.get(newQuery);
	}

	static int n;
	static Island[] allIslands;

	static LinkedHashMap < Integer, ListEntry > mergeEntries(LinkedHashMap < Integer, ListEntry > a, LinkedHashMap < Integer, ListEntry > b) 
	{
		if(a.size() > b.size())
			return mergeEntries(b, a);
		Iterator < Map.Entry < Integer, ListEntry > > it = a.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry < Integer, ListEntry > e = it.next();
			ListEntry set = e.getValue();
			if(!b.containsKey(e.getKey()))
				b.put(e.getKey(), set);
			else
			{
				ListEntry currentSet = b.get(e.getKey());
				while(set != null)
				{
					ListEntry setN = set.next;
					set.next = currentSet;
					currentSet = set;
					set = setN;
					totalCount++;
				}
				b.put(e.getKey(), currentSet);
			}
		}
		return b;
	}
	
	static int totalCount = 0;
	
	static void merge(ConnectedComponent a, ConnectedComponent b, int cost) 
	{
		if(a.islands.size() > b.islands.size())
			merge(b, a, cost);
		else
		{
			for(Island i : a.islands)
			{
				ListEntry set = b.pendingEntries.get(i.id);
				while(set != null)
				{
					getQuery(i.id, set.val).answer = cost;
					a.pendingEntries.remove(set.val);
					set = set.next;
				}
				b.pendingEntries.remove(i.id);
				i.component = b;
				b.islands.add(i);
			}
			b.pendingEntries = mergeEntries(a.pendingEntries, b.pendingEntries);
		}
	}
	
	static void sort(Bridge[] bridges)
	{
		Bridge[] inOrder = new Bridge[100001];
		for(Bridge b : bridges)
		{
			Bridge posibleHead = inOrder[b.cost];
			if(posibleHead == null)
				inOrder[b.cost] = b;
			else
			{
				b.next = posibleHead;
				inOrder[b.cost] = b;
			}
		}
		int bridgeId = 0;
		for(int i = 100000; i >= 0; i--)
		{
			Bridge b = inOrder[i];
			while(b != null)
			{
				bridges[bridgeId++] = b;
				b = b.next;
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		//System.setIn(new FileInputStream("prueba.txt"));
		Scanner sc = new Scanner();
		while(true)
		{
			long start = System.currentTimeMillis();
			Integer nTemp = sc.nextInteger();
			if(nTemp == null) return;
			n = nTemp;
			int m = sc.nextInt();
			int s = sc.nextInt();
			Bridge[] allBridges = sc.nextObjectArray(Bridge.class, m);
			allIslands = sc.nextObjectArray(Island.class, n);
			Query[] queries = new Query[s];
			allQueries.clear();
			for(int i = 0; i < s; i++)
				queries[i] = getQuery(sc.nextInt() - 1, sc.nextInt() - 1);
			for(Query q : allQueries.keySet())
			{
				allIslands[q.a].component.add(q.b, q.a);
				allIslands[q.b].component.add(q.a, q.b);
			}
			sort(allBridges);
			for(Bridge b : allBridges)
			{
				if(allIslands[b.from].component != allIslands[b.to].component)
					merge(allIslands[b.from].component, allIslands[b.to].component, b.cost);
			}
			StringBuilder sb = new StringBuilder();
			for(Query q : queries)
			{
				sb.append(q.answer);
				sb.append('\n');
			}
			System.out.print(sb.toString());
			System.out.flush();
		}
	}
}