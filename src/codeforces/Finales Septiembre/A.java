import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;


public class A 
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
	}
	
	static class Point
	{
		int x;
		int y;
		int id;
		
		public Point(Scanner sc)
		{
			x = sc.nextInt();
			y = sc.nextInt();
		}
		
		int distance(Point o)
		{
			return Math.abs(x - o.x) + Math.abs(y - o.y);
		}
	}
	
	static double MinCostMatching(int[][] cost, int[] Lmate, int[] Rmate) 
	{
		  int n = cost.length;

		  // construct dual feasible solution
		  double[] u = new double[n];
		  double[] v = new double[n];
		  for (int i = 0; i < n; i++) {
		    u[i] = cost[i][0];
		    for (int j = 1; j < n; j++) u[i] = Math.min(u[i], cost[i][j]);
		  }
		  for (int j = 0; j < n; j++) {
		    v[j] = cost[0][j] - u[0];
		    for (int i = 1; i < n; i++) v[j] = Math.min(v[j], cost[i][j] - u[i]);
		  }
		  
		  // construct primal solution satisfying complementary slackness
		 Arrays.fill(Lmate, -1);
		 Arrays.fill(Rmate, -1);
		  int mated = 0;
		  for (int i = 0; i < n; i++) {
		    for (int j = 0; j < n; j++) {
		      if (Rmate[j] != -1) continue;
		      if (Math.abs(cost[i][j] - u[i] - v[j]) < 1e-10) 
		      {
				Lmate[i] = j;
				Rmate[j] = i;
				mated++;
				break;
		      }
		    }
		  }
		  
		  double[] dist = new double[n];
		  int[] dad = new int[n];
		  boolean[] seen = new boolean[n];
		  
		  // repeat until primal solution is feasible
		  while (mated < n) {
		    
		    // find an unmatched left node
		    int s = 0;
		    while (Lmate[s] != -1) s++;
		    
		    // initialize Dijkstra
		    Arrays.fill(dad, -1);
		    Arrays.fill(seen, false);
		    for (int k = 0; k < n; k++) 
		      dist[k] = cost[s][k] - u[s] - v[k];
		    
		    int j = 0;
		    while (true) {
		      
		      // find closest
		      j = -1;
		      for (int k = 0; k < n; k++) {
			if (seen[k]) continue;
			if (j == -1 || dist[k] < dist[j]) j = k;
		      }
		      seen[j] = true;
		      
		      // termination condition
		      if (Rmate[j] == -1) break;
		      
		      // relax neighbors
		      final int i = Rmate[j];
		      for (int k = 0; k < n; k++) {
			if (seen[k]) continue;
			final double new_dist = dist[j] + cost[i][k] - u[i] - v[k];
			if (dist[k] > new_dist) {
			  dist[k] = new_dist;
			  dad[k] = j;
			}
		      }
		    }
		    
		    // update dual variables
		    for (int k = 0; k < n; k++) {
		      if (k == j || !seen[k]) continue;
		      final int i = Rmate[k];
		      v[k] += dist[k] - dist[j];
		      u[i] -= dist[k] - dist[j];
		    }
		    u[s] += dist[j];
		    
		    // augment along path
		    while (dad[j] >= 0) {
		      final int d = dad[j];
		      Rmate[j] = Rmate[d];
		      Lmate[Rmate[j]] = j;
		      j = d;
		    }
		    Rmate[j] = s;
		    Lmate[s] = j;
		    
		    mated++;
		  }
		  
		  double value = 0;
		  for (int i = 0; i < n; i++)
		    value += cost[i][Lmate[i]];
		  
		  return value;
		}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			Integer rT = sc.nextInteger();
			if(rT == null) return;
			int r = rT;
			int n = sc.nextInt();
			Point[] restaurants = sc.nextObjectArray(Point.class, r);
			Point[] orders = sc.nextObjectArray(Point.class, n);
			int[][] costs = new int[r][r];
			for(int i = 0; i < r; i++)
				for(int j = 0; j < n; j++)
					costs[i][j] = restaurants[i].distance(orders[j]);
			System.out.println(Math.round(MinCostMatching(costs, new int[r], new int[r])));
		}
	}
}