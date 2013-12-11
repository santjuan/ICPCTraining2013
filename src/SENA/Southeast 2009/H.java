import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class H 
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
	
	static Point2D[] puntos;
	static double[] penalizacion;
	static Double[] dp;
	
	static double dp(int current)
	{
		if(dp[current] != null)
			return dp[current];
		Point2D actual = puntos[current];
		double mejor = Double.MAX_VALUE;
		double penalizacionAcumulada = 0;
		for(int next = current + 1; next < puntos.length - 1; next++)
		{
			mejor = Math.min(mejor, penalizacionAcumulada + actual.distance(puntos[next]) + 1.0 + dp(next));
			penalizacionAcumulada += penalizacion[next];
		}
		mejor = Math.min(mejor, penalizacionAcumulada + actual.distance(puntos[puntos.length - 1]) + 1.0);
		return dp[current] = mejor;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				return;
			puntos = new Point2D[n + 2];
			dp = new Double[n + 2];
			penalizacion = new double[n + 2];
			puntos[0] = new Point2D.Double(0.0, 0.0);
			puntos[puntos.length - 1] = new Point2D.Double(100.0, 100.0);
			for(int i = 1; i < puntos.length - 1; i++)
			{
				puntos[i] = new Point2D.Double(sc.nextDouble(), sc.nextDouble());
				penalizacion[i] = sc.nextDouble();
			}
			System.out.printf("%.3f\n", dp(0));
		}
	}

}
