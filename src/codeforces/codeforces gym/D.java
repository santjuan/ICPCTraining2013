import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class D 
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
	}
	

	private static void generarDivisores(ArrayList<Integer> factores, ArrayList<Integer> numeroVeces, int i, ArrayList<Integer> respuesta, int acum)
	{
		if(i == factores.size())
		{
			respuesta.add(acum);
			return;
		}
		for(int j = 0; j <= numeroVeces.get(i); j++)
		{
			generarDivisores(factores, numeroVeces, i + 1, respuesta, acum);
			acum *= factores.get(i);
		}
	}
	
	public static ArrayList <Integer> generarDivisores(int n)
	{
		ArrayList <Integer> factores = new ArrayList <Integer> ();
		ArrayList <Integer> numeroVeces = new ArrayList <Integer> ();
		for(int i = 2; i <= n; i++)
		{
			if(n % i == 0)
			{
				int cuenta = 0;
				while(n % i == 0)
				{
					n /= i;
					cuenta++;
				}
				factores.add(i);
				numeroVeces.add(cuenta);
			}
		}
		ArrayList <Integer> respuesta = new ArrayList <Integer> ();
		generarDivisores(factores, numeroVeces, 0, respuesta, 1);
		return respuesta;
	}

	static int[] divisores;
	static Integer[] dp;
	
	static int dp(int n)
	{
		if(n == 1)
			return 1;
		if(dp[n] != null)
			return dp[n];
		int cuenta = 0;
		for(int i : divisores)
		{
			if(i == 1 || n % i != 0)
				continue;
			if(i > n)
				break;
			cuenta += dp(n / i);
		}
		return dp[n] = cuenta;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		ArrayList <Integer> divs = generarDivisores(n);
		Collections.sort(divs);
		divisores = new int[divs.size()];
		int i = 0;
		for(int j : divs)
			divisores[i++] = j;
		dp = new Integer[n + 1];
		System.out.println(dp(n));
	}
}