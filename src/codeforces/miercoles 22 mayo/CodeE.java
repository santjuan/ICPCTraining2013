import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeE 
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
	}
	
	static char[] valor;
	
	static int[][][][] dp = new int[11][2][10][];
	
	static void arrayCopy(int[] a, int[] b)
	{
		for(int i = 0; i < a.length; i++)
			a[i] += b[i];
	}
	
	static int[] dp(int indice, boolean tope, int nLuckys)
	{
		if(dp[indice][tope ? 1 : 0][nLuckys] != null)
			return dp[indice][tope ? 1 : 0][nLuckys];
		if(indice == valor.length)
		{
			int[] ans = new int[10];
			ans[nLuckys]++;
			return dp[indice][tope ? 1 : 0][nLuckys] = ans;
		}
		int limite = tope ? valor[indice] - '0' : 9;
		int[] ans = new int[10];
		for(int i = 0; i <= limite; i++)
			arrayCopy(ans, dp(indice + 1, tope && i == limite, nLuckys + ((i == 4 || i == 7) ? 1 : 0)));
		if(indice == 0)
			ans[0]--;
		return dp[indice][tope ? 1 : 0][nLuckys] = ans;
	}
	
	
	static long combinatoriaRapida(long n, long k, boolean f)
	{
		if(k > n)
			return 0;
		BigInteger arriba = BigInteger.ONE;
		for(long i = n; i > (n - k); i--)
			arriba = arriba.multiply(BigInteger.valueOf(i));
		BigInteger abajo = BigInteger.ONE;
		if(!f)
			for(long i = 1; i <= k; i++)
				abajo = abajo.multiply(BigInteger.valueOf(i));
		return arriba.divide(abajo).mod(BigInteger.valueOf(1000000007)).longValue();
	}
	
	static int[] posibilidades;
	static Long[][][] dp2 = new Long[10][10][10];
	
	static long contar(int desde, int cuantos, int restantes)
	{
		if(restantes <= 0)
			return 0;
		if((desde == -1 && cuantos == 0) || cuantos == 0)
			return 1;
		else if(desde == -1 && cuantos != 0)
			return 0;
		if(dp2[desde][cuantos][restantes] != null)
			return dp2[desde][cuantos][restantes];
		long res = 0;
		for(int i = 0; i <= cuantos; i++)
		{
			long r = combinatoriaRapida(posibilidades[desde], i, true) * combinatoriaRapida(cuantos, i, false);
			r %= 1000000007;
			r *= contar(desde - 1,  cuantos - i, restantes - i * desde);
			r %= 1000000007;
			res += r;
			res %= 1000000007;
		}
		return dp2[desde][cuantos][restantes] = res;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		valor = sc.next().toCharArray();
		posibilidades = dp(0, true, 0);
		long res = 0;
		for(int i = 1; i < 10; i++)
		{
			res += (combinatoriaRapida(posibilidades[i], 1, true) * contar(i - 1, 6, i)) % 1000000007;
			res %= 1000000007;
		}
		System.out.println(res);
	}

}
