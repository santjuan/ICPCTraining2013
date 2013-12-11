import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class teleport 
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

		public char[][] nextGrid(int r, int c) 
		{
			char[][] grid = new char[r][];
			for(int i = 0; i < r; i++)
				grid[i] = next().toCharArray();
			return grid;
		}
	}
	
	static class GridDistance
	{
		static class Position
		{
			int i;
			int j;
			
			Position(int ii, int jj)
			{
				i = ii;
				j = jj;
			}
		}
		
		int n, m;
		
		GridDistance(int n1, int m1)
		{
			n = n1;
			m = m1;
		}
		
		boolean isValid(int i, int j)
		{
			return i >= 0 && i < n && j >= 0 && j < m && tablero[i][j] != 'X';
		}
		
		static final int[][] diffs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		
		int[][] getDistances(Position... startPositions)
		{
			int[][] distance = new int[n][m];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < m; j++)
					distance[i][j] = Integer.MAX_VALUE;
			ArrayDeque <Position> queue = new ArrayDeque <Position> ();
			for(Position p : startPositions)
				distance[p.i][p.j] = 0;
			queue.addAll(Arrays.asList(startPositions));
			while(!queue.isEmpty())
			{
				Position p = queue.poll();
				for(int[] d : diffs)
				{
					int xN = p.i + d[0];
					int yN = p.j + d[1];
					if(isValid(xN, yN) && distance[xN][yN] == Integer.MAX_VALUE)
					{
						distance[xN][yN] = distance[p.i][p.j] + 1;
						queue.add(new Position(xN, yN));
					}
				}
			}
			return distance;
		}
	}
	
	static double solve(int[] vals, int current)
	{
		double best = current;
		if(current == Integer.MAX_VALUE)
			current = vals.length - 1;
		int total = 0;
		for(int i : vals) total += i;
		double acum = 0.0;
		double acumP = ((double) vals[0]) / total;
		for(int i = 1; i <= current; i++)
		{
			double este = (1 + acum) / acumP;
			best = Math.min(best, este);
			double pEste = ((double) vals[i]) / total;
			acum += pEste * i;
			acumP += pEste;
		}
		return best;
	}
	
	static char[][] tablero;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int r = sc.nextInt();
			int c = sc.nextInt();
			if(r == 0 && c == 0) return;
			tablero = sc.nextGrid(r, c);
			int startI = 0;
			int startJ = 0;
			ArrayList <GridDistance.Position> allStarting = new ArrayList <GridDistance.Position> ();
			for(int i = 0; i < r; i++)
				for(int j = 0; j < c; j++)
					if(tablero[i][j] == 'Y')
					{
						startI = i;
						startJ = j;
					}
					else if(tablero[i][j] == 'E')
						allStarting.add(new GridDistance.Position(i, j));
			int[][] distances = new GridDistance(r, c).getDistances(allStarting.toArray(new GridDistance.Position[0]));
			int max = 0;
			for(int[] d : distances)
				for(int j : d)
					if(j != Integer.MAX_VALUE)
						max = Math.max(max, j);
			int[] counts = new int[max + 2];
			for(int i = 0; i < r; i++)
				for(int j = 0; j < c; j++)
					if(tablero[i][j] != 'X')
						if(distances[i][j] != Integer.MAX_VALUE)
							counts[distances[i][j]]++;
						else
							counts[counts.length - 1]++;
			sc.printLine(3, solve(counts, distances[startI][startJ]));
		}
	}
}