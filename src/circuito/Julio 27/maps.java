import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class maps 
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
	}
	
	static class Point
	{
		double x,y;
		public Point(double xx, double yy){
			x = xx;
			y = yy;
		}
		Point add(Point o){
			return new Point(x + o.x, y + o.y);
		}
		Point sub(Point o){
			return new Point(x - o.x, y - o.y);
		}
		
		Point rotate(double the)
		{
			the = (Math.PI / 180.0) * the;
			return new Point(x * Math.cos(the) - y * Math.sin(the), x * Math.sin(the) + y * Math.cos(the));
		}
		Point scale(double s)
		{
			return new Point(x * s, y * s);
		}
		public Point scale(double sX, double sY) 
		{
			return new Point(x * sX, y * sY);
		}
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int w = sc.nextInt();
			int h = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();
			int s = sc.nextInt();
			int r = sc.nextInt();
			if (w == 0 && h == 0 && x == 0 && y == 0 && s == 0 && r ==0)
				return;
			Point current = new Point(x, y);
			Point changeCurrent = new Point(x, y);
			for(int i = 0; i < 2000; i++)
			{
				changeCurrent = changeCurrent.scale(s / 100.0).rotate(r);
				current = current.add(changeCurrent);
			}
			String res = String.format("%.2f %.2f", current.x, current.y);
			res = res.replace(",", ".");
			System.out.println(res);
		}
	}
}