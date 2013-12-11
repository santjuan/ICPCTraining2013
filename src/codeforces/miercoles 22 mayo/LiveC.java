import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LiveC 
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
	
	static int[][] mapa;
	static boolean[] presentes;
	static boolean[][] visitados;
	static int menorA;
	static int mayorA;
	static int kA;
	
	static int floodFill(int i, int j)
	{
		if(i < 0 || i >= mapa.length || j < 0 || j >= mapa[0].length || visitados[i][j] || mapa[i][j] < menorA || mapa[i][j] > mayorA)
			return 0;
		visitados[i][j] = true;
		return 1 + floodFill(i + 1, j) + floodFill(i - 1, j) + floodFill(i, j + 1) + floodFill(i, j - 1);
	}
	
	static boolean esPosible(int menor, int mayor)
	{
		visitados = new boolean[mapa.length][mapa[0].length];
		menorA = menor;
		mayorA = mayor;
		int best = 0;
		for(int i = 0; i < mapa.length; i++)
			for(int j = 0; j < mapa[0].length; j++)
				best = Math.max(best, floodFill(i, j));
		return best >= kA;
	}
	
	static int busquedaBinaria(int a, int b, int menor)
	{
		if(a == b)
			return esPosible(menor, a) ? a : -1;
		if(a + 1 == b)
			return esPosible(menor, a) ? a : busquedaBinaria(b, b, menor);
		int mid = (a + b) >>> 1;
		if(esPosible(menor, mid))
			return busquedaBinaria(a, mid, menor);
		else
			return busquedaBinaria(mid + 1, b, menor);
	}
	
	static int intentarMejor(int menor)
	{
		int ans = busquedaBinaria(menor, 99, menor);
		if(ans == -1)
			return Integer.MAX_VALUE;
		else
			return ans - menor;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			Integer tmp = sc.nextInteger();
			if(tmp == null)
				return;
			int r = tmp;
			int c = sc.nextInt();
			mapa = sc.nextIntMatrix(r, c);
			boolean[] presentes = new boolean[100];
			for(int[] t : mapa)
				for(int p : t)
					presentes[p] = true;
			int n = sc.nextInt();
			for(int i = 0; i < n; i++)
			{
				kA = sc.nextInt();
				int best = Integer.MAX_VALUE;
				for(int j = 0; j < 100; j++)
					if(presentes[j])
						best = Math.min(best, intentarMejor(j));
				System.out.println(best);
			}
		}
	}

}
