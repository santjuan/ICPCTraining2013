import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

public class LiveA 
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
				res[i] = nextLong();
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
		
		Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
		}
		
		int[][] nextIntMatrix(int n, int m)
		{
			int[][] ans = new int[n][];
			for(int i = 0; i < n; i++)
				ans[i] = nextIntArray(m);
			return ans;
		}
	}
	
	static class Arista
	{
		Nodo otro;
		int peso;
		
		Arista(Nodo o, int p)
		{
			otro = o;
			peso = p;
		}
	}
	
	static class Nodo
	{
		int connectedComponent = -1;
		ArrayList <Arista> adjacentes = new ArrayList <Arista> ();
	}
	
	static void joinHorizontal(Nodo[][] grid, int row, int delta)
	{
		Nodo actual = null;
		int tam = 0;
		for(int i = 0; i < grid[row].length; i++)
		{
			Nodo current = grid[row][i] != null ? grid[row][i] : ((row + delta) < grid.length) && (row + delta >= 0) && grid[row + delta][i] != null ? grid[row + delta][i] : null;
			if(actual == null)
			{
				actual = current;
				tam = 0;
			}
			else
			{
				if(current != null)
				{
					if(tam != 0)
					{
						actual.adjacentes.add(new Arista(current, tam));
						current.adjacentes.add(new Arista(actual, tam));
					}
					actual = current;
					tam = 0;
				}
				else
					tam++;
			}
		}
	}
	
	static void joinVertical(Nodo[][] grid, int col, int delta)
	{
		Nodo actual = null;
		int tam = 0;
		for(int i = 0; i < grid.length; i++)
		{
			Nodo current = grid[i][col] != null ? grid[i][col] : ((col + delta) < grid[i].length) && (col + delta >= 0) && grid[i][col + delta] != null ? grid[i][col + delta] : null;
			if(actual == null)
			{
				actual = current;
				tam = 0;
			}
			else
			{
				if(current != null)
				{
					if(tam != 0)
					{
						actual.adjacentes.add(new Arista(current, tam));
						current.adjacentes.add(new Arista(actual, tam));
					}
					actual = current;
					tam = 0;
				}
				else
					tam++;
			}
		}
	}
	
	static void floodFill(Nodo[][] grid, int i, int j, int connectedComponent)
	{
		if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == null  || grid[i][j].connectedComponent != -1)
			return;
		grid[i][j].connectedComponent = connectedComponent;
		floodFill(grid, i + 1, j, connectedComponent);
		floodFill(grid, i - 1, j, connectedComponent);
		floodFill(grid, i, j + 1, connectedComponent);
		floodFill(grid, i, j - 1, connectedComponent);
		floodFill(grid, i + 1, j + 1, connectedComponent);
		floodFill(grid, i + 1, j - 1, connectedComponent);
		floodFill(grid, i - 1, j + 1, connectedComponent);
		floodFill(grid, i - 1, j - 1, connectedComponent);
	}
	
	static int fillFloodFill(Nodo[][] grid)
	{
		int currentCC = 0;
		for(int i = 0; i < grid.length; i++)
			for(int j = 0; j < grid[0].length; j++)
			{
				if(grid[i][j] != null && grid[i][j].connectedComponent == -1)
					floodFill(grid, i, j, currentCC++);
			}
		return currentCC;
	}
	
	static class DisjointSet {
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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		boolean primero = true;
		int caso = 1;
		while(true)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			if(n == 0 && m == 0) return;
			if(!primero) System.out.println();
			primero = false;
			System.out.println("City " + caso++);
			char[][] tablero = new char[n][];
			for(int i = 0; i < n; i++)
				tablero[i] = sc.next().toCharArray();
			Nodo[][] grid = new Nodo[n][m];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < m; j++)
					if(tablero[i][j] == '#')
						grid[i][j] = new Nodo();
			int nSets = fillFloodFill(grid);
			for(int i = 0; i < m; i++)
			{
				joinVertical(grid, i, -1);
				joinVertical(grid, i, 1);
			}
			for(int i = 0; i < n; i++)
			{
				joinHorizontal(grid, i, -1);
				joinHorizontal(grid, i, 1);
			}
			DisjointSet ds = new DisjointSet(nSets);
			ArrayList <int[]> aristas = new ArrayList <int[]> ();
			for(int i = 0; i < n; i++)
				for(int j = 0; j < m; j++)
					if(grid[i][j] != null)
						for(Arista a : grid[i][j].adjacentes)
							aristas.add(new int[]{grid[i][j].connectedComponent, a.otro.connectedComponent, a.peso});
			Collections.sort(aristas, new Comparator<int[]>() 
			{
				@Override
				public int compare(int[] o1, int[] o2) 
				{
					return o1[2] - o2[2];
				}
			});
			int total = 0;
			int nBridges = 0;
			for(int[] arista : aristas)
			{
				if(ds.find_set(arista[0]) != ds.find_set(arista[1]))
				{
					total += arista[2];
					ds.merge(arista[0], arista[1]);
					nBridges++;
				}
			}
			if(nSets < 2)
				System.out.println("No bridges are needed.");
			else
			{
				if(nBridges == 0)
					System.out.println("No bridges are possible.");
				else if(nBridges == 1)
					System.out.println(nBridges + " bridge of total length " + total);
				else
					System.out.println(nBridges + " bridges of total length " + total);
				int nGroups = ds.countSets(nSets);
				if(nGroups != 1)
					System.out.println(nGroups + " disconnected groups");
			}
		}
	}
}