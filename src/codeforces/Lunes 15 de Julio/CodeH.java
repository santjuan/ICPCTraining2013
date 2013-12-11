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
	
	static int k;
	static Integer[] dp;
	static char[][] lines;
	static String vowels = "aeiou";
	
	static int dp(int i)
	{
		if(dp[i] != null) return dp[i];
		int count = 0;
		int size = lines[i].length;
		for(int j = 0; j < lines[i].length; j++)
		{
			if(vowels.indexOf(lines[i][j]) != -1)
			{
				count++;
				if(count == k) return dp[i] = j;
			}
		}
		return dp[i] = -1;
	}
	
	static boolean rhyme(int i, int j)
	{
		int a = dp(i);
		int b = dp(j);
		if(a == -1 || b == -1 || a != b) return false;
		for(int k = 0; k <= a; k++) if(lines[i][k] != lines[j][k]) return false;
		return true;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		k = sc.nextInt();
		dp = new Integer[4 * n];
		lines = new char[4 * n][];
		for(int i = 0; i < lines.length; i++)
			lines[i] = new StringBuilder(sc.next()).reverse().toString().toCharArray();
		Integer scheme = null;
		boolean possibleAll = true;
		for(int i = 0; i < lines.length; i += 4)
		{
			if(rhyme(i, i + 1) && rhyme(i + 2, i + 3))
			{
				if(!rhyme(i + 1, i + 2)) possibleAll = false;
				else continue;
				if(scheme != null && scheme != 0)
				{
					scheme = null;
					break;
				}
				else
					scheme = 0;
			}
			else if(rhyme(i, i + 2) && rhyme(i + 1, i + 3))
			{
				possibleAll = false;
				if(scheme != null && scheme != 1)
				{
					scheme = null;
					break;
				}
				else
					scheme = 1;
			}
			else if(rhyme(i, i + 3) && rhyme(i + 1, i + 2))
			{
				possibleAll = false;
				if(scheme != null && scheme != 2)
				{
					scheme = null;
					break;
				}
				else
					scheme = 2;
			}
			else
			{
				possibleAll = false;
				scheme = null;
				break;
			}
		}
		if(possibleAll)
			System.out.println("aaaa");
		else if(scheme == null)
			System.out.println("NO");
		else if(scheme.intValue() == 0)
			System.out.println("aabb");
		else if(scheme.intValue() == 1)
			System.out.println("abab");
		else
			System.out.println("abba");
	}
}