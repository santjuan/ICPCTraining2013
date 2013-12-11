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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		String a = sc.next();
		String b = sc.next();
		int n = sc.nextInt();
		int[][] costo = new int[26][26];
		for(int i = 0; i < 26; i++)
		{
			for(int j = 0; j < 26; j++)
				costo[i][j] = 100000000;
			costo[i][i] = 0;
		}
		for(int i = 0; i < n; i++)
		{
			int x = sc.next().charAt(0) - 'a';
			int y = sc.next().charAt(0) - 'a';
			costo[x][y] = Math.min(costo[x][y], sc.nextInt());
		}
		for(int k = 0; k < 26; k++)
			for(int i = 0; i < 26; i++)
				for(int j = 0; j < 26; j++)
					costo[i][j] = Math.min(costo[i][j], costo[i][k] + costo[k][j]);
		if(a.length() != b.length())
			System.out.println("-1");
		else
		{
			boolean paila = false;
			int totalCosto = 0;
			char[] ans = new char[a.length()];
			for(int i = 0; i < a.length(); i++)
			{
				int x = a.charAt(i) - 'a';
				int y = b.charAt(i) - 'a';
				int best = 100000000;
				for(int j = 0; j < 26; j++)
				{
					if(costo[x][j] + costo[y][j] < best)
					{
						ans[i] = (char) ('a' + j);
						best = costo[x][j] + costo[y][j];
					}
				}
				if(best >= 100000000)
				{
					paila = true;
					break;
				}
				totalCosto += best;
			}
			if(paila)
				System.out.println("-1");
			else
			{
				System.out.println(totalCosto);
				System.out.println(new String(ans));
			}
		}
	}
}
