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
	}
	
	static long[][] dp2;
	
	static long[] dp2(int posicion)
	{
		if(dp2[posicion] != null) return dp2[posicion];
		if(posicion == dp2.length - 1) return dp2[posicion] = new long[26];
		long[] vals = new long[26];
		for(int i = 0; i < 26; i++) vals[i] = dp(posicion + 1, i, true);
		for(int i = 1; i < 26; i++)
		{
			long res = vals[i];
			res += vals[i - 1];
//			if(res >= mod) res -= mod;
			vals[i] = res;
		}
		return dp2[posicion] = vals;
	}
	
	static long[][][] dp;
	static int[] palabra;
//	static final int mod = 1000000007;
	
	static long dp(int posicion, int letra, boolean cambio)
	{
		if(dp[posicion][letra][cambio ? 1 : 0] != -1) return dp[posicion][letra][cambio ? 1 : 0];
		if(posicion == dp.length - 1) return dp[posicion][letra][cambio ? 1 : 0] = cambio ? 1 : 0;
		if(posicion == 6) 
		{
			System.out.println();
		}
		int letraS = palabra[posicion + 1];
		int adelanteReal = letra < 25 - letraS ? letra : 25 - letraS;
		int atrasReal = 25 - letra < letraS ? 25 - letra : letraS;
		int atrasTest = testAtras(letra, letraS);
		if(atrasReal != atrasTest)
		{
			System.out.println();
			atrasReal = atrasTest;
		}
		int adelanteTest = testAdelante(letra, letraS);
		if(adelanteReal != adelanteTest)
		{
			System.out.println();
			adelanteReal = adelanteTest;
			 testAdelante(letra, letraS);
		}
		long respuesta = 0;
		respuesta += dp(posicion + 1, letraS, cambio);
		long[] siguiente = dp2(posicion);
		if(letraS - 1 >= 0 && atrasReal != 0)
		{
			respuesta += siguiente[letraS - 1];
//			if(respuesta >= mod) respuesta -= mod;
			if(letraS - 1 - atrasReal >= 0)
			{
				respuesta -= siguiente[letraS - 1 - atrasReal];
//				if(respuesta < 0) respuesta += mod;
			}
		}
		if(letraS + adelanteReal <= 25 && adelanteReal != 0)
		{
			respuesta += siguiente[letraS + adelanteReal];
//			if(respuesta >= mod) respuesta -= mod;
			respuesta -= siguiente[letraS];
//			if(respuesta < 0) respuesta += mod;
		}
		return dp[posicion][letra][cambio ? 1 : 0] = respuesta;
	}
	
	private static int testAdelante(int letra, int letraS) 
	{
		int cuenta = 0;
		while(letra >= 0 && letraS < 26)
		{
			cuenta++;
			letra--;
			letraS++;
		}
		return cuenta - 1;
	}

	private static int testAtras(int letra, int letraS)
	{
		int cuenta = 0;
		while(letra < 26 && letraS >= 0)
		{
			cuenta++;
			letra++;
			letraS--;
		}
		return cuenta - 1;
	}

	
	static long dpNormal(int posicion, int letra, boolean cambio)
	{
		if(posicion == dp.length - 1) return cambio ? 1 : 0;
		long respuesta = 0;
		int letraS = palabra[posicion + 1];
		respuesta += dpNormal(posicion + 1, letraS, cambio);
		int letraA = letra;
		int letraB = letraS;
		letraA--;
		letraB++;
		while(letraA >= 0 && letraB < 26) 
		{
			respuesta += dpNormal(posicion + 1, letraB, true);
			letraA--;
			letraB++;
		}
		letraA = letra;
		letraB = letraS;
		letraA++;
		letraB--;
		while(letraA < 26 && letraB >= 0) 
		{
			respuesta += dpNormal(posicion + 1, letraB, true);
			letraA++;
			letraB--;
		}
		return respuesta;
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int caso = 0; caso < t; caso++)
		{
			char[] entrada = sc.next().toCharArray();
			palabra = new int[entrada.length];
			for(int j = 0; j < entrada.length; j++) palabra[j] = entrada[j] - 'a';
			dp = new long[palabra.length][26][2];
			for(int i = 0; i < palabra.length; i++)
				for(int j = 0; j < 26; j++)
					for(int k = 0; k < 2; k++)
						dp[i][j][k] = -1;
			dp2 = new long[palabra.length][];
			System.out.println(dp(0, palabra[0], false) + " " + dpNormal(0, palabra[0], false));
		}
	}
}
