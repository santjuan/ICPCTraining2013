import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class LiveE 
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
	
	static int c;
	static int[] countryCities;
	static int[][] gridS;
	static String[] names;
	static final int[][] ds = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	static int[] solve(final int iMin, final int jMin, final int iMax, final int jMax, final int c)
	{
		final int[][] grid = gridS;
		int solvedCountries = 0;
		final int[] countriesClose = new int[c];
		Arrays.fill(countriesClose, Integer.MAX_VALUE);
		final int[] countriesCurrent = new int[c];
		final int[][][] diffs = new int[10][10][c];
		final int[][][] current = new int[10][10][c];
		for(int i = iMin; i <= iMax; i++)
			for(int j = jMin; j <= jMax; j++)
			{
				if(grid[i][j] != -1)
					current[i][j][grid[i][j]] = 1000000;
			}
		int time = 0;
		while(solvedCountries < c)
		{
			Arrays.fill(countriesCurrent, 0);
			for(int i = iMin; i <= iMax; i++)
				for(int j = jMin; j <= jMax; j++)
				{
					if(grid[i][j] != -1)
					{
						boolean ok = true;
						for(int k = 0; ok && k < c; k++)
							ok &= current[i][j][k] > 0;
						if(ok)
							countriesCurrent[grid[i][j]]++;
					}
				}
			for(int i = 0; i < c; i++)
			{
				if(countriesCurrent[i] == countryCities[i])
				{
					if(countriesClose[i] == Integer.MAX_VALUE)
					{
						solvedCountries++;
						countriesClose[i] = time;
					}
				}
			}
			for(int i = iMin; i <= iMax; i++)
				for(int j = jMin; j <= jMax; j++)
					for(int k = 0; k < c; k++)
						diffs[i][j][k] = 0;
			for(int i = iMin; i <= iMax; i++)
				for(int j = jMin; j <= jMax; j++)
					if(grid[i][j] != -1)
						for(int k = 0; k < c; k++)
						{
							int val = current[i][j][k];
							int passVal = val / 1000;
							if(passVal != 0)
							{
								for(int[] d : ds)
								{
									int iN = i + d[0];
									int jN = j + d[1];
									if(iN >= iMin && iN <= iMax && jN >= jMin && jN <= jMax && grid[iN][jN] != -1)
									{
										diffs[iN][jN][k] += passVal;
										diffs[i][j][k] -= passVal;
									}
								}
							}
						}
			for(int i = iMin; i <= iMax; i++)
				for(int j = jMin; j <= jMax; j++)
					if(grid[i][j] != -1)
						for(int k = 0; k < c; k++)
							current[i][j][k] += diffs[i][j][k];
			time++;
		}
		return countriesClose;
	}
	
	
	static class Pais implements Comparable <Pais>
	{
		String nombre;
		int tiempo;
		
		Pais(String n, int t)
		{
			nombre = n;
			tiempo = t;
		}

		@Override
		public int compareTo(Pais o)
		{
			if(tiempo == o.tiempo)
				return nombre.compareTo(o.nombre);
			return tiempo - o.tiempo;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int caso = 1;
		while(true)
		{
			c = sc.nextInt();
			if(c == 0) return;
			countryCities = new int[c];
			int xMax = 0;
			int yMax = 0;
			int xMin = 9;
			int yMin = 9;
			gridS = new int[10][10];
			for(int i = 0; i < 10; i++)
				for(int j = 0; j < 10; j++)
					gridS[i][j] = -1;
			names = new String[c];
			for(int a = 0; a < c; a++)
			{
				names[a] = sc.next();
				int xi = sc.nextInt() - 1;
				int yi = sc.nextInt() - 1;
				int xf = sc.nextInt() - 1;
				int yf = sc.nextInt() - 1;
				xMax = Math.max(xMax, xf);
				xMin = Math.min(xMin, xi);
				yMax = Math.max(yMax, yf);
				yMin = Math.min(yMin, yi);
				for(int i = xi; i <= xf; i++)
					for(int j = yi; j <= yf; j++)
					{
						gridS[i][j] = a;
						countryCities[a]++;
					}
			}
			int[] total = solve(xMin, yMin, xMax, yMax, c);
			ArrayList <Pais> todos = new ArrayList <Pais> ();
			for(int i = 0; i < c; i++)
				todos.add(new Pais(names[i], total[i]));
			Collections.sort(todos);
			System.out.println("Case Number " + caso++);
			for(Pais p : todos)
				System.out.println("   " + p.nombre + "   " + p.tiempo);
		}
	}
}