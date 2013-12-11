import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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

	
	static int[][] dpFollow;
	static Boolean[][] dp;
	static int[] numeros;
	static int n;
	
	static boolean dp(int anterior, int indice)
	{
		if(indice == n)
			return true;
		if(dp[anterior - 1000][indice] != null)
			return dp[anterior - 1000][indice];
		char[] numero = (numeros[indice] + "").toCharArray();
		for(int i = 0; i < numero.length; i++)
		{
			char real = numero[i];
			for(char j = i == 0 ? '1' : '0'; j <= '9'; j++)
			{
				numero[i] = j;
				int siguiente = Integer.parseInt(new String(numero));
				if(siguiente >= 1000 && siguiente <= 2011 && siguiente >= anterior)
					if(dp(siguiente, indice + 1))
					{
						dpFollow[anterior - 1000][indice] = siguiente;
						return dp[anterior - 1000][indice] = true;
					}
				numero[i] = real;
			}
		}
		return dp[anterior - 1000][indice] = false;
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		numeros = new int[n];
		for(int i = 0; i < n; i++)
			numeros[i] = sc.nextInt();
		dp = new Boolean[1200][n + 1];
		dpFollow = new int[1200][n + 1];
		if(dp(1000, 0))
		{
			int posicion = 0;
			int anterior = 1000;
			while(posicion < n)
			{
				System.out.println(dpFollow[anterior - 1000][posicion]);
				anterior = dpFollow[anterior - 1000][posicion];
				posicion++;
			}
		}
		else
			System.out.println("No solution");
	}

}
