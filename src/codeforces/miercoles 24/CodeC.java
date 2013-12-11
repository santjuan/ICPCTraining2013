import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeC 
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
	
	static int n;
	static int[] as;
	static int[][][] mejores;
	static Integer[][] dp;
	
	static int dp(int primero, int cola)
	{
		if(dp[primero][cola] != null)
			return dp[primero][cola];
		if(primero >= n)
		{
			mejores[primero][cola][0] = -1;
			mejores[primero][cola][1] = -1;
			mejores[primero][cola][2] = -1;
			mejores[primero][cola][3] = -1;
			return dp[primero][cola] = 0;
		}
		if(cola >= n)
		{
			mejores[primero][cola][0] = -1;
			mejores[primero][cola][1] = -1;
			mejores[primero][cola][2] = primero;
			mejores[primero][cola][3] = -1;
			return dp[primero][cola] = as[primero];
		}
		if(cola + 1 == n)
		{
			mejores[primero][cola][0] = -1;
			mejores[primero][cola][1] = -1;
			mejores[primero][cola][2] = primero;
			mejores[primero][cola][3] = cola;
			return dp[primero][cola] = Math.max(as[primero], as[cola]);
		}
		int min = Integer.MAX_VALUE;
		int val = dp(cola + 1, cola + 2) + Math.max(as[primero], as[cola]);
		if(val < min)
		{
			min = val;
			mejores[primero][cola][0] = cola + 1;
			mejores[primero][cola][1] = cola + 2;
			mejores[primero][cola][2] = primero;
			mejores[primero][cola][3] = cola;
		}
		val = dp(cola, cola + 2) + Math.max(as[primero], as[cola + 1]);
		if(val < min)
		{
			min = val;
			mejores[primero][cola][0] = cola;
			mejores[primero][cola][1] = cola + 2;
			mejores[primero][cola][2] = primero;
			mejores[primero][cola][3] = cola + 1;
		}
		val = dp(primero, cola + 2) + Math.max(as[cola], as[cola + 1]);
		if(val < min)
		{
			min = val;
			mejores[primero][cola][0] = primero;
			mejores[primero][cola][1] = cola + 2;
			mejores[primero][cola][2] = cola;
			mejores[primero][cola][3] = cola + 1;
		}
		return dp[primero][cola] = min;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		as = sc.nextIntArray(n);
		dp = new Integer[n + 10][n + 10];
		mejores = new int[n + 10][n + 10][4];
		int val = dp(0, 1);
		System.out.println(val);
		int primero = 0;
		int cola = 1;
		while(true)
		{
			int[] v = mejores[primero][cola];
			if(v[2] == -1)
				break;
			if(v[3] != -1)
				System.out.println((v[2] + 1) + " " + (v[3] + 1));
			else
			{
				System.out.println((v[2] + 1));
				break;
			}
			if(v[0] == -1)
				break;
			primero = v[0];
			cola = v[1];
		}
	}
}