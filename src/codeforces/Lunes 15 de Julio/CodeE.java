import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	
//	static int[][][] dpN4 = new int[300][300][300];
//	
//	static boolean dpN4(int i, int j, int k)
//	{
//		if(dpN4[i][j][k] != 0) return dpN4[i][j][k] == 1;
//		boolean ans = false;
//		for(int l = 1; l <= i; l++)
//			ans |= !dpN4(i - l, j, k);
//		for(int l = 1; l <= j; l++)
//			ans |= !dpN4(i, j - l, k);
//		for(int l = 1; l <= k; l++)
//			ans |= !dpN4(i, j, k - l);
//		int min = Math.min(i, Math.min(j, k));
//		for(int l = 1; l <= min; l++)
//			ans |= !dpN4(i - l, j - l, k - l);
//		dpN4[i][j][k] = ans ? 1 : 2;
//		return ans;
//	}
//	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int[] vals = sc.nextIntArray(n);
		if(n == 1)
			System.out.println(vals[0] != 0 ? "BitLGM" : "BitAryo");
		else if(n == 2)
		{
			boolean[][] dp = new boolean[vals[0] + 1][vals[1] + 1];
			for(int i = 0; i <= vals[0]; i++)
				for(int j = 0; j <= vals[1]; j++)
				{
					int min = Math.min(i, j);
					boolean ans = false;
					for(int k = 1; k <= min; k++)
						ans |= !dp[i - k][j - k];
					for(int k = 1; k <= i; k++)
						ans |= !dp[i - k][j];
					for(int k = 1; k <= j; k++)
						ans |= !dp[i][j - k];
					dp[i][j] = ans;
				}
			System.out.println(dp[vals[0]][vals[1]] ? "BitLGM" : "BitAryo");
		}
		else
		{
			boolean[][] dp12 = new boolean[vals[0] + 1][vals[1] + 1];
			boolean[][] dp13 = new boolean[vals[0] + 1][vals[2] + 1];
			boolean[][] dp23 = new boolean[vals[1] + 1][vals[2] + 1];
			for(boolean[] v : dp12) Arrays.fill(v, true);
			for(boolean[] v : dp13) Arrays.fill(v, true);
			for(boolean[] v : dp23) Arrays.fill(v, true);
			boolean[][][] dp = new boolean[vals[0] + 1][vals[1] + 1][vals[2] + 1];
			boolean[][][] dp2 = new boolean[vals[0] + 1][vals[1] + 1][vals[2] + 1];
			for(int i = 0; i <= vals[0]; i++)
			{
				for(int j = 0; j <= vals[1]; j++)
					for(int k = 0; k <= vals[2]; k++)
					{
						boolean algunCero = i == 0 || j == 0 || k == 0;
						boolean ans = false;
						if(!algunCero) ans |= dp2[i - 1][j - 1][k - 1];
						ans |= !dp12[i][j];
						ans |= !dp13[i][k];
						ans |= !dp23[j][k];
						dp[i][j][k] = ans;
//						if(dpN4(i, j, k) != dp[i][j][k]) throw new RuntimeException();
						if(!ans) dp12[i][j] = dp13[i][k] = dp23[j][k] = false;
						dp2[i][j][k] = !dp[i][j][k];
						if(!algunCero) dp2[i][j][k] |= dp2[i - 1][j - 1][k - 1];
					}
			}
			System.out.println(dp[vals[0]][vals[1]][vals[2]] ? "BitLGM" : "BitAryo");
		}
	}

}
