import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeC 
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
	}
	
	
	static int n;
	static int m;
	static int[][][] dp;
	static int[][][] aristas;
	
	static int[] dijkstra(int ciudad, int carro)
	{
		if(dp[ciudad][carro] != null)
			return dp[ciudad][carro];
		int[][] matrizCostos = aristas[carro];
		int[] respuesta = new int[n];
		boolean[] visitados = new boolean[n];
		for(int i = 0; i < n; i++)
			respuesta[i] = Integer.MAX_VALUE;
		respuesta[ciudad] = 0;
		for(int i = 0; i < n; i++)
		{
			int mejor = -1;
			int mejorCosto = Integer.MAX_VALUE;
			for(int j = 0; j < n; j++)
			{
				if(!visitados[j] && respuesta[j] < mejorCosto)
				{
					mejorCosto = respuesta[j];
					mejor = j;
				}
			}
			visitados[mejor] = true;
			for(int j = 0; j < n; j++)
				respuesta[j] = Math.min(respuesta[j], mejorCosto + matrizCostos[mejor][j]);
		}
		return dp[ciudad][carro] = respuesta;
	}
	
	static int[][][] dpMejor;
	
	static int[] dp(int ciudad, int numeroCambios)
	{
		if(dpMejor[ciudad][numeroCambios] != null)
			return dpMejor[ciudad][numeroCambios];
		int[] mejor = new int[n];
		Arrays.fill(mejor, Integer.MAX_VALUE);
		for(int i = 0; i < m; i++)
		{
			int[] costos = dijkstra(ciudad, i);
			for(int j = 0; j < n; j++)
				mejor[j] = Math.min(mejor[j], costos[j]);
		}
		if(numeroCambios != 0)
		{
			int[] mejorSiguiente = mejor.clone();
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
					mejorSiguiente[j] = Math.min(mejorSiguiente[j], mejor[i] + dp(i, numeroCambios - 1)[j]);
			mejor = mejorSiguiente;
		}
		return dpMejor[ciudad][numeroCambios] = mejor;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		m = sc.nextInt();
		int r = sc.nextInt();
		aristas = new int[m][n][n];
		for(int i = 0; i < m; i++)
			for(int j = 0; j < n; j++)
				for(int k = 0; k < n; k++)
					aristas[i][j][k] = sc.nextInt();
		dp = new int[n + 1][m + 1][];
		dpMejor = new int[n + 1][n + 1][];
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
		for(int i = 0; i < r; i++)
		{
			int s = sc.nextInt() - 1;
			int t = sc.nextInt() - 1;
			int cambios = Math.min(sc.nextInt(), n);
			pw.println(dp(s, cambios)[t]);
		}
		pw.flush();
		pw.close();
	}
}
