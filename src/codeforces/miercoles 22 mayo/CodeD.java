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

		public String[] nextStringArray(int n) 
		{	
			String[] vals = new String[n];
			for(int i = 0; i < n; i++)
				vals[i] = next();
			return vals;
		}
	}

	static String valores = "23456789TJQKA";
	static String pintas = "SDHC";
	
	static class Carta
	{
		int pinta;
		int valor;
		
		Carta(String s)
		{
			valor = valores.indexOf(s.charAt(0));
			pinta = pintas.indexOf(s.charAt(1));
		}
	}
	
	static Carta[] cartas;
	static Boolean[][][][] dp = new Boolean[52][52][52][52];
	
	static boolean dp(int n, int primera, int segunda, int tercera)
	{
		if(dp[n][primera][segunda][tercera] != null)
			return dp[n][primera][segunda][tercera];
		if(n == 2)
			return dp[n][primera][segunda][tercera] = esPosible(primera, segunda, tercera);
		if(compatibles(primera, segunda) && dp(n - 1, primera, tercera, n - 3))
			return dp[n][primera][segunda][tercera] = true;
		if(compatibles(primera, n - 3) && dp(n - 1, segunda, tercera, primera))
			return dp[n][primera][segunda][tercera] = true;
		return dp[n][primera][segunda][tercera] = false;
	}
	
	private static boolean compatibles(int primera, int segunda) 
	{
		return (cartas[primera].pinta == cartas[segunda].pinta) || (cartas[primera].valor == cartas[segunda].valor);
	}

	private static boolean esPosible(int primera, int segunda, int tercera) 
	{
		return compatibles(primera, segunda) && compatibles(primera, tercera);
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		cartas = new Carta[n];
		for(int i = 0; i < n; i++)
			cartas[i] = new Carta(sc.next());
		boolean resultado = n <= 3 ? chequearEspecial(n) : dp(n - 1, n - 1, n - 2, n - 3); 
		System.out.println(resultado ? "YES" : "NO");
	}

	private static boolean chequearEspecial(int n) 
	{
		if(n == 3)
			return esPosible(n - 1, n - 2, n - 3);
		else if(n == 2)
			return compatibles(n - 1, n - 2);
		else
			return true;
	}

}
