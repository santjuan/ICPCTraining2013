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
	}
	static final Scanner sc;
	static final int n;
	static final int[][] distancias;
	static final int[] distancia;
	static final int[] dp;
	static final int[] dpNext;
	static final int X;
	static final int Y;
	
	static
	{
		sc = new Scanner();
		X = sc.nextInt();
		Y = sc.nextInt();
		n = sc.nextInt();
		distancias = new int[n][n];
		distancia = new int[n];
		dp = new int[1 << n];
		Arrays.fill(dp, -1);
		dpNext = new int[1 << n];
	}
	
	
	static int dp(int mascara)
	{
		if(dp[mascara] != -1)
			return dp[mascara];
		int highest = -1;
		for(int i = 0, tmp = mascara; tmp != 0; i++, tmp >>= 1)
		{
			if((tmp & 1) == 1)
				highest = i;
		}
		if(highest == -1)
			return 0;
		int nextMsc = mascara ^ (1 << highest);
		int costHighest = distancia[highest];
		int best = (costHighest << 1) + dp(nextMsc);
		int bestNext = nextMsc;
		for(int i = 0, tmp = nextMsc, iC = 1; tmp != 0; i++, tmp >>= 1, iC <<= 1)
		{
			if((tmp & 1) == 1)
			{
				int msc = nextMsc ^ iC;
				int possibleA = costHighest + distancias[highest][i] + distancia[i] + dp(msc);
				if(possibleA < best)
				{
					best = possibleA;
					bestNext = msc;
				}
			}
		}
		dpNext[mascara] = bestNext;
		return dp[mascara] = best;
			
	}
	public static void main(String[] args)
	{
		int[][] objetos = new int[n][2];
		for(int i = 0; i < n; i++)
		{
			objetos[i][0] = sc.nextInt();
			objetos[i][1] = sc.nextInt();
			distancia[i] = (X - objetos[i][0]) * (X - objetos[i][0]) + (Y - objetos[i][1]) * (Y - objetos[i][1]);
		}
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				distancias[i][j] = (objetos[i][0] - objetos[j][0]) * (objetos[i][0] - objetos[j][0]) + (objetos[i][1] - objetos[j][1]) * (objetos[i][1] - objetos[j][1]);
		int ans = dp((1 << n) - 1);
		System.out.println(ans);
		int current = (1 << n) - 1;
		while(current != 0)
		{
			int next = dpNext[current];
			int differents = next ^ current;
			System.out.print("0 ");
			for(int i = 0; i < n; i++)
				if((differents & (1 << i)) != 0)
					System.out.print((i + 1) + " ");
			current = next;
		}
		System.out.println("0");
	}

}
