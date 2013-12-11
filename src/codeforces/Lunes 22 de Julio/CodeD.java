import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CodeD 
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
	
	static class Arista implements Comparable <Arista>
	{
		int id;
		long costo;
		
		
		Arista(int i, long c)
		{
			id = i;
			costo = c;
		}
		
		@Override
		public int compareTo(Arista o) 
		{
			return costo < o.costo ? -1 : costo > o.costo ? 1 : 0;
		}
	}
	
	
	static class ShortestPaths
	{
		int tam;
		static long INFINITO = (Long.MAX_VALUE >>> 1) - 1;
		
		ShortestPaths(int t)
		{
			tam = t;
		}
		
		long[] dijkstra(int desde)
		{
			long[] resultado = new long[tam];
			Arrays.fill(resultado, INFINITO);
			if(mapa[desde / c][desde % c] == 'T') return resultado;
			PriorityQueue <Arista> pq = new PriorityQueue <Arista> ();
			resultado[desde] = 0;
			pq.add(new Arista(desde, 0));
			while(!pq.isEmpty())
			{
				Arista actual = pq.poll();
				if(actual.costo != resultado[actual.id])
					continue;
				int i = actual.id / c;
				int j = actual.id % c;
				for(int[] d : diffs)
				{
					int iN = i + d[0];
					int jN = j + d[1];
					if(iN >= 0 && iN < r && jN >= 0 && jN < c)
					{
						if(mapa[iN][jN] != 'T')
						{
							long nuevoCosto = actual.costo + 1;
							if(nuevoCosto < resultado[iN * c + jN])
							{
								resultado[iN * c + jN] = nuevoCosto;
								pq.add(new Arista(iN * c + jN, nuevoCosto));
							}
						}
					}
					
				}
			}
			return resultado;
		}
	}
	
	static final int[][] diffs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int r, c;
	static char[][] mapa;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		r = sc.nextInt();
		c = sc.nextInt();
		mapa = new char[r][];
		for(int i = 0; i < r; i++) mapa[i] = sc.next().toCharArray();
		ShortestPaths s = new ShortestPaths(r * c);
		int iS = 0, jS = 0, iE = 0, jE = 0;
		for(int i = 0; i < r; i++)
			for(int j = 0; j < c; j++)
				if(mapa[i][j] == 'S')
				{
					iS = i;
					jS = j;
				}
				else if(mapa[i][j] == 'E')
				{
					iE = i;
					jE = j;
				}
		long[] dists = s.dijkstra(iE * c + jE);
		long d = dists[iS * c + jS];
		int cuenta = 0;
		for(int i = 0; i < r; i++)
			for(int j = 0; j < c; j++)
			{
				if(mapa[i][j] >= '0' && mapa[i][j] <= '9')
				{
					long dT = dists[i * c + j];
					if(dT <= d) cuenta += mapa[i][j] - '0';
				}
			}
		System.out.println(cuenta);
	}

}
