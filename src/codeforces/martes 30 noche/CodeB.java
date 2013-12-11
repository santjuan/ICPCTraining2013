import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeB
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
	
	static int[][] image;
	static boolean[][] garden;
	
	static void generateImage()
	{
		image = new int[garden.length][garden[0].length];
		for(int i = 0; i < garden.length; i++)
		{
			int currentSum = 0;
			for(int j = 0; j < garden[0].length; j++)
			{
				currentSum += garden[i][j] ? 1 : 0;
				image[i][j] = currentSum + (i == 0 ? 0 : image[i - 1][j]);
			}
		}
		
	}
	

	static boolean valid(int x, int y) 
	{
		return x >= 0 && x < garden.length && y >= 0 && y < garden[0].length;
	}

	
	static int giveSum(int x1, int y1, int x2, int y2)
	{
		int ans = image[x2][y2];
		if(valid(x1 - 1, y2))
			ans -= image[x1 - 1][y2];
		if(valid(x2, y1 - 1))
			ans -= image[x2][y1 - 1];
		if(valid(x1 - 1, y1 - 1))
			ans += image[x1 - 1][y1 - 1];
		return ans;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		garden = new boolean[n][m];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
				garden[i][j] = sc.nextInt() == 1;
		int a = sc.nextInt();
		int b = sc.nextInt();
		generateImage();
		int best = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
			{
				int x2 = i + a - 1;
				int y2 = j + b - 1;
				if(valid(x2, y2))
					best = Math.min(best, giveSum(i, j, x2, y2));
					
			}
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
			{
				int x2 = i + b - 1;
				int y2 = j + a - 1;
				if(valid(x2, y2))
					best = Math.min(best, giveSum(i, j, x2, y2));
			}
		System.out.println(best);
	}
}
