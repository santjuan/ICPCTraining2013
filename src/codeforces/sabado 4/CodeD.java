import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class CodeD
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
		
		public Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
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
	
	static class Punto
	{
		int i;
		int j;
		
		Punto(int ii, int jj)
		{
			i = ii;
			j = jj;
		}
	}
	static int[][] diffs = new int[][]{{2, 1}, {-2, 1}, {2, -1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			Integer tmp = sc.nextInteger();
			if(tmp == null)
				return;
			int r = tmp;
			int c = sc.nextInt();
			int gr = sc.nextInt() - 1;
			int gc = sc.nextInt() - 1;
			int lr = sc.nextInt() - 1;
			int lc = sc.nextInt() - 1;
			int[][] cuanto = new int[r][c];
			for(int i = 0; i < r; i++)
				for(int j = 0; j < c; j++)
					cuanto[i][j] = Integer.MAX_VALUE;
			LinkedList <Punto> cola = new LinkedList <Punto> ();
			cola.add(new Punto(gr, gc));
			cuanto[gr][gc] = 0;
			int mejor = Integer.MAX_VALUE;
			while(!cola.isEmpty())
			{
				Punto actual = cola.poll();
				if(actual.i == lr && actual.j == lc)
				{
					mejor = cuanto[actual.i][actual.j];
					break;
				}
				for(int[] d : diffs)
				{
					int iN = actual.i + d[0];
					int jN = actual.j + d[1];
					if(iN < 0 || iN >= r || jN < 0 || jN >= c || cuanto[iN][jN] != Integer.MAX_VALUE)
						continue;
					cuanto[iN][jN] = cuanto[actual.i][actual.j] + 1;
					cola.add(new Punto(iN, jN));
						
				}
			}
			if(mejor == Integer.MAX_VALUE)
				System.out.println("impossible");
			else
				System.out.println(mejor);
		}
	}

}
