import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		
		public String[] nextStringArray(int n)
		{
			String[] res = new String[n];
			for(int i = 0; i < res.length; i++)
				res[i] = next();
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

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		boolean[][] problemas = new boolean[n][n];
		ArrayList <String> nombres = new ArrayList <String> ();
		for(int i = 0; i < n; i++)
			nombres.add(sc.next());
		for(int i = 0; i < m; i++)
		{
			int a = nombres.indexOf(sc.next());
			int b = nombres.indexOf(sc.next());
			problemas[a][b] = problemas[b][a] = true;
		}
		int limite = 1 << n;
		int mejor = -1;
		ArrayList <String> mejores = new ArrayList <String> ();
		outer:
		for(int mascara = 0; mascara < limite; mascara++)
		{
			ArrayList <Integer> indices = new ArrayList <Integer> ();
			for(int i = 0; i < n; i++)
			{
				if((mascara & (1 << i)) != 0)
					indices.add(i);
			}
			for(int i : indices)
				for(int j : indices)
					if(problemas[i][j])
						continue outer;
			int cuantos = Integer.bitCount(mascara);
			if(cuantos > mejor)
			{
				mejor = cuantos;
				mejores.clear();
				for(int i : indices)
					mejores.add(nombres.get(i));
			}
		}
		Collections.sort(mejores);
		System.out.println(mejor);
		for(String s : mejores)
			System.out.println(s);
	}

}
