import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class unreal 
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
	
	static class Rectangle
	{
		double x1, y1, x2, y2;
		int start;
		int end;
		
		Rectangle(double xx1, double yy1, double xx2, double yy2)
		{
			x1 = xx1;
			y1 = yy1;
			x2 = xx2;
			y2 = yy2;
		}
		
		
		void defineStartEnd(IntegralImage image)
		{
			start = -1;
			end = -1;
			double[] vals = image.vals;
			for(int i = 0; i < vals.length - 1; i++)
			{
				if(y1 <= vals[i] && y2 >= vals[i + 1])
				{
					if(start == -1)
						start = i;
					end = i;
				}
			}
		}
	}
	
	static class IntegralImage
	{
		double[] vals;
		int[] current;
		
		public IntegralImage(TreeSet<Double> yVals) 
		{
			vals = new double[yVals.size()];
			int indice = 0;
			for(double d : yVals)
				vals[indice++] = d;
			current = new int[vals.length];
		}

		double getOpen()
		{
			double total = 0;
			for(int i = 0; i < vals.length - 1; i++)
			{
				if(current[i] > 0)
					total += vals[i + 1] - vals[i];
			}
			return total;
		}
		
		void open(Rectangle r)
		{
			for(int i = r.start; i <= r.end; i++)
				current[i]++;
		}
		
		void close(Rectangle r)
		{
			for(int i = r.start; i <= r.end; i++)
				current[i]--;
		}
	}

	static class Event implements Comparable <Event>
	{
		double val;
		boolean open;
		Rectangle r;
		
		Event(double v, boolean o, Rectangle rr)
		{
			val = v;
			open = o;
			r = rr;
		}
		
		@Override
		public int compareTo(Event o) 
		{
			return Double.valueOf(val).compareTo(o.val);
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) return;
			TreeSet <Double> yVals = new TreeSet <Double> ();
			Rectangle[] rectangles = new Rectangle[n];
			PriorityQueue <Event> events = new PriorityQueue <Event> ();
			for(int i = 0; i < n; i++)
			{
				rectangles[i] = new Rectangle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble());
				yVals.add(rectangles[i].y1);
				yVals.add(rectangles[i].y2);
				events.add(new Event(rectangles[i].x1, true, rectangles[i]));
				events.add(new Event(rectangles[i].x2, false, rectangles[i]));
			}
			yVals.add(Double.MAX_VALUE);
			IntegralImage image = new IntegralImage(yVals);
			for(Rectangle e : rectangles)
				e.defineStartEnd(image);
			double lastEvent = events.peek().val;
			double total = 0;
			while(!events.isEmpty())
			{
				Event e = events.poll();
				total += (e.val - lastEvent) * image.getOpen();
				lastEvent = e.val;
				if(e.open)
					image.open(e.r);
				else
					image.close(e.r);
			}
			System.out.println(String.format("%.2f", total).replace(",", "."));
		}
	}
}