import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class I {
	
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
	
	static class point implements Comparable<point>{
		int x, y;
		public point(int xx,int yy){
			x = xx;
			y = yy;
		}
		@Override
		public int compareTo(point o) {
			return o.y * x - o.x * y;
		}
	}
	
	static point[] array;
	static Integer[][][] dp;
	static int inf=10000000;
	static int N,K;
	
	static int f(int n, int k, int h){
		if (k == K)
			return 0;
		if (n == N)
			return -inf;
		if (dp[n][k][h]!=null)
			return dp[n][k][h];
		int plus = 2*h*array[n].x + array[n].x*array[n].y;
		dp[n][k][h] = Math.max(f(n + 1, k, h), plus + f(n + 1, k + 1, h + array[n].y));
		return dp[n][k][h];
	}
	
	public static void main(String args[]){
		Scanner sc=new Scanner();
		int T=sc.nextInt();
		for(int c = 0; c < T; c++){
			N=sc.nextInt();
			K=sc.nextInt();
			array = new point[N];
			int h = 0;
			for(int i = 0; i < N; i++){
				array[i] = new point(sc.nextInt(),sc.nextInt());
				h += array[i].y;
			}
			Arrays.sort(array);
			dp = new Integer[N][K][h + 1];
			System.out.println("Case "+(c+1)+": "+f(0,0,0));
		}
	}

}
