import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LiveD 
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
	
	static boolean[][] possibleRight;
	static boolean[][] possibleLeft;
	static int[] trainTimes;
	static int T;
	static int N;
	static Integer[][] dp;
	
	static int dp(int estacion, int tiempo)
	{
		if(tiempo == T) return estacion == N - 1 ? 0 : 1000000;
		if(tiempo > T) return 1000000;
		if(dp[estacion][tiempo] != null) return dp[estacion][tiempo];
		int mejor = 1 + dp(estacion, tiempo + 1);
		if(estacion != N - 1 && possibleRight[estacion][tiempo])
			mejor = Math.min(mejor, dp(estacion + 1, tiempo + trainTimes[estacion]));
		if(estacion != 0 && possibleLeft[estacion][tiempo])
			mejor = Math.min(mejor, dp(estacion - 1, tiempo + trainTimes[estacion - 1]));
		return dp[estacion][tiempo] = mejor;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int caso = 1;
		while(true)
		{
			N = sc.nextInt();
			if(N == 0) return;
			T = sc.nextInt();
			trainTimes = new int[N - 1];
			for(int i = 0; i < N - 1; i++)
				trainTimes[i] = sc.nextInt();
			int m1 = sc.nextInt();
			possibleRight = new boolean[N][T + 1];
			for(int i = 0; i < m1; i++)
			{
				int currentStation = 0;
				int currentTime = sc.nextInt();
				while(currentStation < N && currentTime <= T)
				{
					possibleRight[currentStation][currentTime] = true;
					currentTime += currentStation == N - 1 ? 0 : trainTimes[currentStation];
					currentStation++;
				}
			}
			int m2 = sc.nextInt();
			possibleLeft = new boolean[N][T + 1];
			for(int i = 0; i < m2; i++)
			{
				int currentStation = N - 1;
				int currentTime = sc.nextInt();
				while(currentStation >= 0 && currentTime <= T)
				{
					possibleLeft[currentStation][currentTime] = true;
					currentTime += currentStation == 0 ? 0 : trainTimes[currentStation - 1];
					currentStation--;
				}
			}
			dp = new Integer[N + 1][T + 1];
			int val = dp(0, 0);
			System.out.println("Case Number " + caso++ + ": " + (val > 1000 ? "impossible" : val + "")); 
		}
	}
}