import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LiveD 
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
	
	static int maximo;
	static float izquierda;
	static float derecha;
	static float sinCambio;
	static int n;
	static float[][][] dp;
	
	static float dp(int actual, int pasos, boolean llego)
	{
		if(dp[actual + n][pasos][llego ? 1 : 0] != -1)
			return dp[actual + n][pasos][llego ? 1 : 0];
		if(pasos == 0)
			return dp[actual + n][pasos][llego ? 1 : 0] = llego || (actual == maximo) ? 1.0f : 0.0f;
		if(actual == maximo)
			return dp[actual + n][pasos][llego ? 1 : 0] = izquierda * dp(actual - 1, pasos - 1, true) + sinCambio * dp(actual, pasos - 1, true);
		else
			return dp[actual + n][pasos][llego ? 1 : 0] = izquierda * dp(actual - 1, pasos - 1, llego) + sinCambio * dp(actual, pasos - 1, llego) + derecha * dp(actual + 1, pasos - 1, llego);
	}
	
	static void limpiar()
	{
		for(int i = 0; i < dp.length; i++)
			for(int j = 0; j < dp[0].length; j++)
				for(int k = 0; k < 2; k++)
					dp[i][j][k] = -1;
	}
	
	static float resolver()
	{
		dp = new float[(n << 1) + 10][n + 10][2];
		float ans = 0; 
		for(int i = 0; i <= n; i++)
		{
			long t1 = System.currentTimeMillis();
			limpiar();
			long t2 = System.currentTimeMillis();
			maximo = i;
			ans += dp(0, n, false) * i;
			long t3 = System.currentTimeMillis();
			System.out.println("voy en " + i + " " + (t2 - t1) + " " + (t3 - t2));
		}
		return ans;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int i = 0; i < t; i++)
		{
			int id = sc.nextInt();
			n = sc.nextInt();
			izquierda = (float) sc.nextDouble();
			derecha = (float) sc.nextDouble();
			sinCambio = (float) (1.0 - izquierda - derecha);
			System.out.println(id + " " + resolver());
		}
	}
}