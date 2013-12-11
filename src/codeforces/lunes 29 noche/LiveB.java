import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LiveB 
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
	
	static char[] valor;
	static Long[][][][] dp;
	
	static long dp(int indice, boolean tope, int sumaPivot, int posBalance)
	{
		if(sumaPivot < 0)
			return 0;
		if(indice == valor.length)
			return sumaPivot == 0 ? 1 : 0;
		if(dp[indice][tope ? 1 : 0][sumaPivot][posBalance] != null)
			return dp[indice][tope ? 1 : 0][sumaPivot][posBalance];
		int limite = tope ? valor[indice] - '0' : 9;
		long cuenta = 0;
		for(int i = 0; i <= limite; i++)
		{
			int siguienteSuma = sumaPivot;
			if(indice >= posBalance)
				siguienteSuma -= i * (indice - posBalance);
			else
				siguienteSuma += i * (posBalance - indice);
			cuenta += dp(indice + 1, tope && (i == valor[indice] - '0'), siguienteSuma, posBalance);
		}
		return dp[indice][tope ? 1 : 0][sumaPivot][posBalance] = cuenta;
	}
	
	static long contar(long hasta)
	{
		valor = (hasta + "").toCharArray();
		dp = new Long[valor.length + 1][2][valor.length * valor.length * 9][valor.length + 1];
		long res = 0;
		for(int i = 0; i < valor.length; i++)
			res += dp(0, true, 0, i);
		return res - (valor.length - 1);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int T = sc.nextInt();
		for(int i = 0; i < T; i++)
		{
			long a = sc.nextLong();
			long b = sc.nextLong();
			long res = contar(b);
			if(a != 0)
				res -= contar(a - 1);
			System.out.println(res);
		}
	}

}
