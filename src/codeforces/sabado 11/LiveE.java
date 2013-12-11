import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
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
	}
	
	static int[][] grafoTransformado;
	static int[][] grafoInicial;
	static int[][] dp;
	
	
	static int[] calcularDP(int desde)
	{
		if(dp[desde] != null)
			return dp[desde];
		int[] res = new int[grafoTransformado.length];
		for(int i = 0; i < grafoTransformado.length; i++)
		{
			if(grafoInicial[desde][i] <= 100)
				res[i] = grafoInicial[desde][i];
			else
				res[i] = 1000000;
		}
		res[desde] = 0;
		for(int j = 0; j < grafoTransformado.length; j++)
			for(int i = 0; i < grafoTransformado.length; i++)
				res[i] = Math.min(res[i], grafoTransformado[j][desde] + grafoTransformado[j][i]);
		return dp[desde] = res;
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		int tc = sc.nextInt();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int caso = 1; caso <= tc; caso++)
		{
			bw.write("CASE " + caso + "\n");
			int n = sc.nextInt();
			int r = sc.nextInt();
			int q = sc.nextInt();
			grafoInicial = new int[n][n];
			grafoTransformado = new int[n][n];
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < n; j++)
					grafoInicial[i][j] = grafoTransformado[i][j] = 1000000;
				grafoInicial[i][i] = grafoTransformado[i][i] = 0;
			}
			boolean[] estaciones = new boolean[n];
			for(int i = 0; i < n; i++)
				estaciones[i] = sc.next().charAt(0) == 'G';
			for(int i = 0; i < r; i++)
			{
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				int c = sc.nextInt();
				grafoInicial[a][b] = Math.min(grafoInicial[a][b], c);
				grafoInicial[b][a] = Math.min(grafoInicial[b][a], c);
			}
			for(int k = 0; k < n; k++)
				for(int i = 0; i < n; i++)
					for(int j = 0; j < n; j++)
						grafoInicial[i][j] = Math.min(grafoInicial[i][j], grafoInicial[i][k] + grafoInicial[k][j]);
			for(int i = 0; i < n; i++)
			{
				if(!estaciones[i])
					continue;
				for(int j = 0; j < n; j++)
					if(grafoInicial[i][j] <= 100)
						grafoTransformado[i][j] = grafoInicial[i][j];
			}
			for(int k = 0; k < n; k++)
				for(int i = 0; i < n; i++)
					for(int j = 0; j < n; j++)
						grafoTransformado[i][j] = Math.min(grafoTransformado[i][j], grafoTransformado[i][k] + grafoTransformado[k][j]);
			dp = new int[n][];
			for(int i = 0; i < q; i++)
			{
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				int val = calcularDP(a)[b];
				if(val >= 1000000)
					bw.write("NO GAS PATH\n");
				else
				{
					bw.write(Integer.toString(val));
					bw.write('\n');
				}
			}
		}
		bw.flush();
		bw.close();
	}
}
