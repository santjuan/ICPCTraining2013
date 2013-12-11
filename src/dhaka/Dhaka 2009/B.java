import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class B 
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
	}
	
	static char[] limite;
	
	
	static long intentar(long numero)
	{
		limite = Long.toBinaryString(numero).toCharArray();
		for(int i = 0; i <= limite.length; i++)
			for(int j = 0; j <= limite.length; j++)
				for(int k = 0; k <= 1; k++)
					dp[i][j][k] = -1;
		return dp(0, 0, true);
	}
	
	static final long[][][] dp = new long[32][32][2];
	
	static long dp(int indice, int cuenta, boolean tope)
	{
		if(dp[indice][cuenta][tope ? 1 : 0] != -1)
			return dp[indice][cuenta][tope ? 1 : 0];
		if(indice == limite.length)
			return dp[indice][cuenta][tope ? 1 : 0] = (long) cuenta;
		long acum = 0;
		int limiteActual = tope ? (limite[indice] - '0') : (1);
		acum += dp(indice + 1, cuenta, tope && (limiteActual == 0));
		if(limiteActual == 0)
			return dp[indice][cuenta][tope ? 1 : 0] = acum;
		acum += dp(indice + 1, cuenta + 1, tope && (limiteActual == 1));
		return dp[indice][cuenta][tope ? 1 : 0] = acum;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int caso = 1;
		while(true)
		{
			long a = sc.nextLong();
			long b = sc.nextLong();
			if(a == 0 && b == 0)
				return;
			long actual = intentar(b);
			long actualB = a == 0 ? 0 : intentar(a - 1);
			System.out.println("Case " + caso++ + ": " + (actual - actualB));
		}
	}
}
