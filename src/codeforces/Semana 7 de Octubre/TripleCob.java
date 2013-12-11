import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.StringTokenizer;


public class TripleCob 
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
				res[i] = nextDouble();
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
		
		static <T> T fill(T arreglo, int val)
		{
			if(arreglo instanceof Object[])
			{
				Object[] a = (Object[]) arreglo;
				for(Object x : a)
					fill(x, val);
			}
			else if(arreglo instanceof int[])
				Arrays.fill((int[]) arreglo, val);
			else if(arreglo instanceof double[])
				Arrays.fill((double[]) arreglo, val);
			else if(arreglo instanceof long[])
				Arrays.fill((long[]) arreglo, val);
			return arreglo;
		}
		
		<T> T[] nextObjectArray(Class <T> clazz, int size)
		{
			@SuppressWarnings("unchecked")
			T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
			for(int c = 0; c < 3; c++)
			{
				Constructor <T> constructor;
				try 
				{
					if(c == 0)
						constructor = clazz.getDeclaredConstructor(Scanner.class, Integer.TYPE);
					else if(c == 1)
						constructor = clazz.getDeclaredConstructor(Scanner.class);
					else
						constructor = clazz.getDeclaredConstructor();
				} 
				catch(Exception e)
				{
					continue;
				}
				try
				{
					for(int i = 0; i < result.length; i++)
					{
						if(c == 0)
							result[i] = constructor.newInstance(this, i);
						else if(c == 1)
							result[i] = constructor.newInstance(this);
						else
							result[i] = constructor.newInstance();	
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
				return result;
			}
			throw new RuntimeException("Constructor not found");
		}
	}
	
	static int[] value;
	static int[] valueImage;
	
	static int suma(int a, int b)
	{
		if(b < a) return 0;
		return valueImage[b] - (a == 0 ? 0 : valueImage[a - 1]); 
	}
	
	static int dpValue(int a, int b)
	{
		if(a == b) return 0;
		int n1 = (b - a) + 1;
		int mediana = (b + a) / 2;
		int total = suma(mediana + 1, b) - suma(a, mediana - 1);
		if((n1 & 1) == 0) return total - value[mediana];
		else return total;
	}
	
	static int n;
	static int[][] dp;
	
	static int dp(int a, int k, int from, int to)
	{
		if(a >= n) return 0;
		if(k == 0)
		{
			dp[a][k] = dpValue(a, n - 1);
			return a;
		}
		int best = Integer.MAX_VALUE;
		int bestIndex = -1;
		if(from < a)
			from = a;
		for(int i = from; i <= to; i++)
		{
			int val = dpValue(a, i) + ((i + 1 == dp.length) ? 0 : (i + 1 == n ? 0 : dp[i + 1][k - 1]));
			if(val < best)
			{
				best = val;
				bestIndex = i;
			}
		}
		dp[a][k] = best;
		return bestIndex;
	}
	
	static void calculateDp(int k, int aFrom, int aTo, int optFrom, int optTo)
	{
		int mid = (aFrom + aTo) >>> 1;
		int bestMid = dp(mid, k, optFrom, optTo);
		if(mid == aFrom)
		{
			dp(aTo, k, optFrom, optTo);
			return;
		}
		calculateDp(k, aFrom, mid - 1, optFrom, bestMid);
		calculateDp(k, mid + 1, aTo, bestMid, optTo);
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			Integer tmp = sc.nextInteger();
			if(tmp == null) return;
			n = tmp;
			int m = sc.nextInt() - 1;
			if(n == 0 && m == 0) return;
			value = sc.nextIntArray(n);
			sc.sortIntArray(value);
			valueImage = new int[n];
			valueImage[0] = value[0];
			for(int i = 1; i < n; i++)
				valueImage[i] = valueImage[i - 1] + value[i];
			dp = Scanner.fill(new int[n + 1][m + 1], -1);
			for(int i = 0; i <= n; i++)
				dp(i, 0, 0, n - 1);
			for(int i = 1; i <= m; i++)
				calculateDp(i, 0, n, 0, n - 1);
			System.out.println(dp[0][m]);
		}
	}
}