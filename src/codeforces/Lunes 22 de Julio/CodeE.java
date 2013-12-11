import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;


public class CodeE 
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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		if(n <= 7)
		{
			boolean[][] grafo = new boolean[n][n];
			for(int i = 0; i < m; i++) 
			{
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				if(a < b) grafo[a][b] = true;
				else grafo[b][a] = true;
			}
			int cuenta = (n * (n - 1)) / 2;
			int limite = 1 << cuenta;
			int[] adjacentes = new int[n];
			outer:
			for(int a = 0; a < limite; a++)
			{
				if(Integer.bitCount(a) != m) continue;
				Arrays.fill(adjacentes, 0);
				int actual = 1;
				for(int i = 0; i < n; i++)
					for(int j = i + 1; j < n; j++)
					{
						if((a & actual) != 0)
						{
							if(grafo[i][j]) continue outer;
							adjacentes[i]++; 
							if(adjacentes[i] > 2)
								continue outer;
							adjacentes[j]++;
							if(adjacentes[j] > 2)
								continue outer;
						}
						actual <<= 1;
					}
				actual = 1;
				for(int i = 0; i < n; i++)
					for(int j = i + 1; j < n; j++)
					{
						if((a & actual) != 0)
							System.out.println((i + 1) + " " + (j + 1));
						actual <<= 1;
					}
				return;
			}
			System.out.println(-1);
		}
		else
		{
			HashSet <String> entrada = new HashSet <String> ();
			for(int i = 0; i < m; i++) 
			{
				int a = sc.nextInt();
				int b = sc.nextInt();
				if(a < b) entrada.add(a + " " + b);
				else entrada.add(b + " " + a);
			}
			ArrayList <Integer> orden = new ArrayList <Integer> ();
			for(int i = 0; i < n; i++) orden.add(i + 1);
			int[] adjacentes = new int[n];
			outer:
			while(true)
			{
				Arrays.fill(adjacentes, 0);
				Collections.shuffle(orden);
				for(int i = 0; i < m; i++)
				{
					int a = orden.get(i);
					int b = orden.get((i + 1) % n);
					adjacentes[a - 1]++; 
					if(adjacentes[a - 1] > 2)
						continue outer;
					adjacentes[b - 1]++;
					if(adjacentes[b - 1] > 2)
						continue outer;
					if(a > b)
					{
						if(entrada.contains(b + " " + a))
							continue outer;
					}
					else
					{
						if(entrada.contains(a + " " + b))
							continue outer;
					}
				}
				for(int i = 0; i < m; i++)
				{
					int a = orden.get(i);
					int b = orden.get((i + 1) % n);
					System.out.println(a + " " + b);
				}
				return;
			}
		}
	}

}
