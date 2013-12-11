import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LiveC 
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

	static final int MAXB = 35000;

	static int bconv(int[] a, int n, int b)
	{
		int ndig=0;
		while (n > 0) 
		{
			a[ndig++] = n % b;
			n /= b;
		}
		return ndig;
	}

	static int n4(int n, int b) 
	{
		int[] tmp = new int[30];
		int ndig = bconv(tmp,n,b);
		int ans=0;
		for(int i = 0; i < ndig; i++)
			if(tmp[i]==4) ans++;
		return ans;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int tc = sc.nextInt();
		for(int i = 0; i < tc; i++)
		{
			int orgn = sc.nextInt();
			int n = Math.abs(orgn);
			int ans = -1;
			int best = 0;
			int limite = Math.min(n + 1, MAXB);
			for(int b = 5; b <= limite; b++)
			{
				int curr = n4(n,b);
				if (curr > best) {
					ans = b;
					best = curr;
				}
			}
			if(best < 2)
			{
				int modulo = (n - 4) % 4;
				int baseCand = (n - 4) / 4;
				if(modulo == 0 && baseCand > 4)
				{
					best = 2;
					ans = baseCand;
				}
			}
			if(n > MAXB && best == 0)
				throw(new RuntimeException());
			if (ans == -1) {
				System.out.printf("%d is infuriable.\n",orgn);
			} else {
				System.out.printf("%d %d %d\n",orgn,best,ans);
			}
		}
		System.out.flush();
	}
}
