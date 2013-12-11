import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeH 
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
		
		static Object fill(Object arreglo, int val)
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
	}
	
	static int[] primos = new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
	static int[][] numerosFact = new int[101][primos.length];
	static long mod = 1000000007;
	
	static void factorizar(int[] a, int n)
	{
		for(int i = 0; i < a.length && n > 1; i++)
		{
			while((n % primos[i]) == 0)
			{
				n /= primos[i];
				a[i]++;
			}
		}
	}
	
	static void multiplicar(int[] a, int[] b)
	{
		for(int i = 0; i < a.length; i++) a[i] += b[i];
	}
	
	static void dividir(int[] a, int[] b)
	{
		for(int i = 0; i < a.length; i++) a[i] -= b[i];
	}
	
	static long darValor(int[] numero)
	{
		long val = 1;
		for(int i = 0; i < numero.length; i++)
		{
			for(int j = 0; j < numero[i]; j++)
			{
				val *= primos[i];
				val %= mod;
			}
		}
		return val;
	}
	
	static int limite;
	static int[] limites;
	static long[][] dp;
	
	static long dp(int digito, int puestos)
	{
		if(dp[digito][puestos] != -1) return dp[digito][puestos];
		if(digito == limites.length) return dp[digito][puestos] = puestos <= limite ? 1 : 0;
		long total = 0;
		for(int i = limites[digito]; puestos + i <= limite; i++)
		{
			int[] numero = new int[primos.length];
			for(int j = puestos + 1; j <= puestos + i; j++)
				multiplicar(numero, numerosFact[j]);
			for(int j = 1; j <= i; j++)
				dividir(numero, numerosFact[j]);
			long actual = darValor(numero);
			total += (actual * dp(digito + 1, puestos + i)) % mod;
			total %= mod;
		}
		return dp[digito][puestos] = total;
	}
	
	static long contarTodos(int lim, int[] lims)
	{
		long ans = 0;
		lim--;
		limite = lim;
		limites = lims;
		for(int i = 1; i < lims.length; i++)
		{
			dp = (long[][]) Scanner.fill(new long[lims.length + 1][lim + 2], -1);
			int numAnt = lims[i];
			int numN = lims[i] - 1;
			if(numAnt == 0)
				numN = numAnt;
			lims[i] = numN;
			ans += dp(0, 0);
			lims[i] = numAnt;
			ans %= mod;
		}
		return ans;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int lim = sc.nextInt();
		int[] lims = sc.nextIntArray(10);
		for(int i = 0; i <= 100; i++) factorizar(numerosFact[i], i);
		System.out.println(contarTodos(lim, lims));
	}
}