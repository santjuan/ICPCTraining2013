import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class CodeB
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
	
	static int[][] adjacencia;

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		final int n = sc.nextInt();
		final int[][] original = new int[n][n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				original[i][j] = sc.nextInt();
		final int[][] actual = new int[n][n];
		final int inf = 100000 * 1000;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				actual[i][j] = inf;
		LinkedList <Integer> cuales = new LinkedList <Integer> ();
		for(int i = 0; i < n; i++)
			cuales.add(sc.nextInt() - 1);
		ArrayList <Long> respuestas = new ArrayList <Long> ();
		int[] puestos = new int[n];
		int limite = 0;
		while(!cuales.isEmpty())
		{
			int siguiente = cuales.pollLast();
			puestos[limite++] = siguiente;
			for(int i = 0; i < limite; i++)
			{
				int cual = puestos[i];
				actual[siguiente][cual] = original[siguiente][cual];
				actual[cual][siguiente] = original[cual][siguiente];
			}
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
				{
					int val = actual[j][i] + actual[i][siguiente];
					if(val < actual[j][siguiente])
						actual[j][siguiente] = val;
					int val1 = actual[siguiente][i] + actual[i][j];
					if(val1 < actual[siguiente][j])
						actual[siguiente][j] = val1;
				}
			long cuenta = 0;
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
				{
					int val = actual[i][siguiente] + actual[siguiente][j];
					if(val < actual[i][j])
						actual[i][j] = val;
					if(actual[i][j] < inf)
						cuenta += actual[i][j];
				}
			respuestas.add(cuenta);
		}
		Collections.reverse(respuestas);
		for(long i : respuestas)
			System.out.print(i + " ");
		System.out.println();
	}

}
