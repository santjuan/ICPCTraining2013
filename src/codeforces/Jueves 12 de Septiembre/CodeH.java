import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CodeH 
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
	
	static class DisjointSet
	{
		int[] p, rank;

		public DisjointSet(int size) {
			rank = new int[size];
			p = new int[size];
			for (int i = 0; i < size; i++)
				make_set(i);
		}

		public void make_set(int x) {
			p[x] = x;
			rank[x] = 0;
		}

		public void merge(int x, int y) {
			link(find_set(x), find_set(y));
		}

		public void link(int x, int y) {
			if (rank[x] > rank[y])
				p[y] = x;
			else {
				p[x] = y;
				if (rank[x] == rank[y])
					rank[y] += 1;
			}
		}

		public int find_set(int x) {
			if (x != p[x])
				p[x] = find_set(p[x]);
			return p[x];
		}

		public int countSets(int n) 
		{
			HashSet <Integer> sets = new HashSet <Integer> ();
			for(int i = 0; i < n; i++)
				sets.add(find_set(i));
			return sets.size();
		}
	}
	
	static class Important
	{
		int i;
		int j;
		int id;
		Set set;
		
		Important(int id1, int i1, int j1)
		{
			id = id1;
			i = i1;
			j = j1;
		}
	}
	
	static int n;
	static int m;
	static char[][] grid;
	
	static final int[][] diffs = new int[][]{{0, -1, -1, 0}, {0, 1, 1, 0}, {-1, 0, 1, 1}, {1, 0, -1, 1}};
	
	static class Edge
	{
		Set from;
		Set to;
		int cost;
		int type;

		public Edge(Set f, Set t, int i, int tp) 
		{
			from = f;
			to = t;
			cost = i;
			type = tp;
		}
	}
	
	static class Set
	{
		int id;
		int distance = 0;
		ArrayList <Edge> aristas = new ArrayList <Edge> ();
		
		Set(int i)
		{
			id = i;
		}
	}
	
	static int setCount = 0;
	static HashMap <Integer, Set> sets = new HashMap <Integer, Set> ();
	static ArrayList <Set> allSets = new ArrayList <Set> ();
	
	static Set getSet(int id)
	{
		if(!sets.containsKey(id))
		{
			Set s = new Set(setCount++);
			sets.put(id, s);
			allSets.add(s);
		}
		return sets.get(id);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		m = sc.nextInt();
		grid = sc.nextGrid(n);
		int[][] importantId = Scanner.fill(new int[n][m], -1);
		int id = 0;
		ArrayList <Important> importants = new ArrayList <Important> (); 
		for(int i = 0; i < m; i++)
		{
			importantId[0][i] = id++;
			importants.add(new Important(importantId[0][i], 0, i));
			importantId[n - 1][i] = id++;
			importants.add(new Important(importantId[n - 1][i], n - 1, i));
		}
		for(int i = 1; i < n - 1; i++)
		{
			importantId[i][0] = id++;
			importants.add(new Important(importantId[i][0], i, 0));
			importantId[i][m - 1] = id++;
			importants.add(new Important(importantId[i][m - 1], i, m - 1));
		}
		DisjointSet ds1 = new DisjointSet(n * m);
		int startI = 0;
		int startJ = 0;
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < m; j++)
			{
				if(grid[i][j] == 'S')
				{
					startI = i;
					startJ = j;
				}
				if(grid[i][j] == '#')
					continue;
				for(int[] d : diffs)
				{
					int iN = i + d[0];
					int jN = j + d[1];
					if(iN >= 0 && iN < n && jN >= 0 && jN < m && grid[iN][jN] != '#')
						ds1.merge(i * m + j, iN * m + jN);
				}
//				for(int i1 = 0; i1 < n; i1++)
//				{
//					for(int j1 = 0; j1 < m; j1++)
//						System.out.print(grid[i1][j1] == '#' ? "#" : ((ds1.find_set(i1 * m + j1) + "").charAt(0) + ""));
//					System.out.println();
//				}
//				System.out.println();
			}
		}
		int startId = ds1.find_set(startI * m + startJ);
		int startSet = -1;
		for(Important i : importants)
		{
			if(grid[i.i][i.j] == '#') continue;
			i.set = getSet(ds1.find_set(i.i * m + i.j));
			if(ds1.find_set(i.i * m + i.j) == startId)
				startSet = i.set.id;
		}
		if(startSet == -1)
		{
			System.out.println("No");
			return;
		}
		ArrayList <Edge> allEdges = new ArrayList <Edge> ();
		for(Important important : importants)
		{
			if(grid[important.i][important.j] == '#') continue;
			for(int[] d : diffs)
			{
				int iN = important.i + d[0];
				int jN = important.j + d[1];
				if(iN >= 0 && iN < n && jN >= 0 && jN < m)
				{
				}
				else
				{
					iN += n;
					iN %= n;
					jN += m;
					jN %= m;
					if(grid[iN][jN] != '#')
					{
						Edge a = new Edge(importants.get(importantId[iN][jN]).set, important.set, d[2], d[3]);
						allEdges.add(a);
						important.set.aristas.add(a);
						a = new Edge(important.set, importants.get(importantId[iN][jN]).set, -d[2], d[3]);
						allEdges.add(a);
						importants.get(importantId[iN][jN]).set.aristas.add(a);
					}
				}
			}
		}
		for(Set s : allSets)
			if(s.id == startSet)
				s.distance = 0;
			else
				s.distance = Integer.MAX_VALUE - 100;
		for(int i = 0; i < setCount; i++)
			for(Edge e : allEdges)
				if(e.from.distance + e.cost < e.to.distance)
					e.to.distance = e.from.distance + e.cost;
		boolean paila = false;
		for(Edge e : allEdges)
			if(e.from.distance < 1000 && e.to.distance < 1000 && e.from.distance + e.cost < e.to.distance)
				paila = true;
		for(Edge e : allEdges)
			if(e.type == 1)
				e.cost = -e.cost;
		for(Set s : allSets)
			if(s.id == startSet)
				s.distance = 0;
			else
				s.distance = Integer.MAX_VALUE - 100;
		for(int i = 0; i < setCount; i++)
			for(Edge e : allEdges)
				if(e.from.distance + e.cost < e.to.distance)
					e.to.distance = e.from.distance + e.cost;
		for(Edge e : allEdges)
			if(e.from.distance < 1000 && e.to.distance < 1000 && e.from.distance + e.cost < e.to.distance)
				paila = true;
		System.out.println(paila ? "Yes" : "No");
	}
}