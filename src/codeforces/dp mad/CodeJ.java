import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeJ 
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
	
	static int[][][] dp = new int[100000][3][7];
	static int[][][] siguiente = new int[100000][3][7];
	static int primero;
	
	static boolean dp(int n, int tres, int siete)
	{
		if(dp[n][tres][siete] != -1)
			return dp[n][tres][siete] == 1;
		if(n == 0)
		{
			if(tres == 0 && siete == 0)
			{
				siguiente[n][tres][siete] = 0;
				return (dp[n][tres][siete] = 1) == 1;
			}
			else
				return (dp[n][tres][siete] = 0) == 1;
		}
		for(int digito = primero == n ? 1 : 0; digito < 10; digito++)
		{
			int sigT = ((tres * 10) + digito) % 3;
			int sigS = ((siete * 10) + digito) % 7;
			if(dp(n - 1, sigT, sigS))
			{
				siguiente[n][tres][siete] = digito;
				return (dp[n][tres][siete] = 1) == 1;
			}
		}
		return (dp[n][tres][siete] = 0) == 1;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		if(n == 1)
			System.out.println("-1");
		else
		{
			for(int i = 0; i < n; i++)
				for(int j = 0; j < 3; j++)
					for(int k = 0; k < 7; k++)
						dp[i][j][k] = -1;
			primero = n - 1;
			if(dp(n - 1, 0, 0))
			{
				n--;
				int tres = 0;
				int siete = 0;
				StringBuilder sb = new StringBuilder();
				while(n != -1)
				{
					int digito = siguiente[n][tres][siete];
					sb.append(digito);
					tres = ((tres * 10) + digito) % 3;
					siete = ((siete * 10) + digito) % 7;
					n--;
				}
				System.out.println(sb.toString());
			}
			else
				System.out.println("-1");
				
		}
	}
}