import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LiveB
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
	
	static ArrayList <Point2D.Double> generar(int cuantos, double xInicial, double yInicial)
	{
		ArrayList <Point2D.Double> respuesta = new ArrayList <Point2D.Double> ();
		for(int i = 0; i < cuantos; i++)
		{
			respuesta.add(new Point2D.Double(xInicial, yInicial));
			xInicial += 1;
		}
		return respuesta;
	}
	
	
	static double EPS = 1e-8;
	
	static boolean equals(double a, double b)
	{
		return Math.abs(a - b) <= EPS;
	}
	static int generar(int cuantos)
	{
		ArrayList <Point2D.Double> respuesta = new ArrayList <Point2D.Double> ();
		double xInicial = 0;
		double yInicial = 0;
		double deltaY = Math.sqrt(0.75);
		while(cuantos != 0)
		{
			respuesta.addAll(generar(cuantos, xInicial, yInicial));
			xInicial += 0.5;
			yInicial += deltaY;
			cuantos--;
		}
		Point2D.Double[] todos = respuesta.toArray(new Point2D.Double[0]);
		int cuenta = 0;
		for(int i = 0; i < todos.length; i++)
		{
			Point2D.Double a = todos[i];
			for(int j = i + 1; j < todos.length; j++)
			{
				Point2D.Double b = todos[j];
				for(int k = j + 1; k < todos.length; k++)
				{
					Point2D.Double c = todos[k];
					double dist = a.distanceSq(b);
					if(equals(a.distanceSq(c), dist) && equals(b.distanceSq(c), dist))
						cuenta++;
				}
			}
		}
		return cuenta;
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				return;
			else
			{
				n--;
	//			System.out.println(generar(n));
				long ans = n;
				ans *= (n + 1);
				ans *= (n + 2);
				ans *= (n + 3);
				ans /= 24;
				System.out.println(ans + " " + generar(n + 1));
			}
		}
	}
}
