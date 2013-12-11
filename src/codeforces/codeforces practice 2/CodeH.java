import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CodeH 
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
	
	static ArrayList <Integer> solucion(int n, long x, long y, long cur)
	{
		x -= cur * cur;
		n--;
		y -= cur;
		if(n == 0)
		{
			if(x <= 0 && y >= 0)
			{
				ArrayList <Integer> sol = new ArrayList <Integer> ();
				sol.add((int) cur);
				return sol;
			}
			else
				return null;
		}
		long mult = n == 0 ? 0 : y / n;
		if(mult == 0)
			mult = 1;
		while(true)
		{
			if(mult * n <= y && x <= mult * mult * n)
			{
				ArrayList <Integer> sol = new ArrayList <Integer> ();
				sol.add((int) cur);
				for(int i = 0; i < n; i++)
					sol.add((int) mult);
				return sol;
			}
			if(mult * n > y)
				break;
			mult++;
		}
		return null;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		long x = sc.nextLong();
		int y = sc.nextInt();
		boolean pudo = false;
		for(int i = y; i >= 1; i--)
		{
			ArrayList <Integer> posible = solucion(n, x, y, i);
			if(posible != null)
			{
				pudo = true;
				System.out.println(posible.toString().replace("[", "").replace(",", "").replace("]", ""));
				return;
			}
		}
		if(!pudo)
			System.out.println(-1);
	}
}