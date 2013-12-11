import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeI 
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
	
	static class Circle implements Comparable <Circle>
	{
		int radius;
		int center;
		int originalId;
		
		public Circle(int c, int r, int o) 
		{
			center = c;
			radius = r;
			originalId = o;
		}

		boolean isInside(int x, int y)
		{
			if(!isInsideRange(x)) return false;
			long dist = (x - center);
			dist *= dist;
			dist += ((long) y) * y;
			return dist <= radius * radius;
		}
		
		boolean isInsideRange(int x)
		{
			return x >= center - radius && x <= center + radius;
		}
		
		@Override
		public int compareTo(Circle o) 
		{
			return center - o.center;
		}
	}
	
	static Circle[] circles;
	static Integer[] firstShot;
	
	static void tryMark(int circleId, int x, int y, int shotId)
	{
		if(circleId >= circles.length || circleId < 0) return;
		if(firstShot[circleId] != null && firstShot[circleId].intValue() < shotId) return;
		if(circles[circleId].isInside(x, y)) firstShot[circleId] = shotId;
	}
	
	static void binaryMark(int i, int j, int x, int y, int shotId)
	{
		if(i > j)
			return;
		if(i == j)
		{
			tryMark(i, x, y, shotId);
			tryMark(i - 1, x, y, shotId);
			tryMark(i + 1, x, y, shotId);
			return;
		}
		int mid = (i + j) >>> 1;
		if(circles[mid].isInsideRange(x))
		{
			binaryMark(mid, mid, x, y, shotId);
			return;
		}
		if(circles[mid].center < x)
			binaryMark(mid + 1, j, x, y, shotId);
		else
			binaryMark(i, mid - 1, x, y, shotId);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		circles = new Circle[n];
		firstShot = new Integer[n];
		for(int i = 0; i < n; i++) circles[i] = new Circle(sc.nextInt(), sc.nextInt(), i);
		Arrays.sort(circles);
		int m = sc.nextInt();
		for(int shot = 1; shot <= m; shot++)
			binaryMark(0, circles.length - 1, sc.nextInt(), sc.nextInt(), shot);
		int count = 0;
		for(Integer i : firstShot)
			if(i != null)
				count++;
		int[] answers = new int[n];
		System.out.println(count);
		for(int i = 0; i < n; i++)
			answers[circles[i].originalId] = firstShot[i] == null ? -1 : firstShot[i].intValue();
		for(int i = 0; i < n; i++)
		{
			if(i != 0)
				System.out.print(" ");
			System.out.print(answers[i]);
		}
		System.out.println();
	}

}
