import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
	
	static byte[][] dp = new byte[5001][5001];
	static char[] palabra;
	
	static boolean esPalindrome(int a, int b)
	{
		if(a >= b)
			return true;
		if(dp[a][b] != 0)
			return dp[a][b] == 1;
		return (dp[a][b] = (byte) ((palabra[a] == palabra[b] && esPalindrome(a + 1, b - 1)) ? 1 : 2)) == 1;
	}
	
	static int[][] dp2 = new int[5001][5001];
	
	static int contarPalindromes(int a, int b)
	{
		if(a > b)
			return 0;
		if(dp2[a][b] != -1)
			return dp2[a][b];
		int ans = 0;
		if(esPalindrome(a, b))
			ans++;
		ans += contarPalindromes(a + 1, b);
		ans += contarPalindromes(a, b - 1);
		ans -= contarPalindromes(a + 1, b - 1);
		return dp2[a][b] = ans;
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		palabra = sc.next().toCharArray();
		int q = sc.nextInt();
		for(int i = 0; i < 5001; i++)
			for(int j = 0; j < 5001; j++)
				dp2[i][j] = -1;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out), 10000);
		for(int i = 0; i < q; i++)
		{
			bw.write(contarPalindromes(sc.nextInt() - 1, sc.nextInt() - 1) + "");
			bw.write('\n');
			
		}
		bw.flush();
		bw.close();
	}

}
