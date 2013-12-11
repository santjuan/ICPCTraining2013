import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;


public class JamB 
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
	
	
	static int[] simular(int n, int[] orden)
	{
		int[] resultados = new int[orden.length];
		int bitActual = 1 << (n - 1);
		for(int i = 0; i < n; i++)
		{
			boolean[] jugo = new boolean[orden.length];
			for(int j = 0; j < orden.length; j++)
			{
				if(!jugo[j])
				{
					for(int k = j + 1; k < orden.length; k++)
					{
						if(!jugo[k])
						{
							if(resultados[j] == resultados[k])
							{
								jugo[j] = true;
								jugo[k] = true;
								if(orden[j] < orden[k])
									resultados[k] |= bitActual;
								else
									resultados[j] |= bitActual;
								break;
							}
						}
					}
				}
			}
			bitActual >>= 1;
		}
		return resultados;
	}
	
	static int mayor(int[] orden, int[] resultados, int p)
	{
		int mayor = 0;
		for(int i = 0; i < orden.length; i++)
		{
			if(resultados[i] < p)
				mayor = Math.max(mayor, orden[i]);
		}
		return mayor;
	}
	
	static int menor(int[] orden, int[] resultados, int p)
	{
		int menor = Integer.MAX_VALUE;
		for(int i = 0; i < orden.length; i++)
		{
			if(resultados[i] >= p)
				menor = Math.min(menor, orden[i]);
		}
		if(menor == Integer.MAX_VALUE)
			menor = orden.length;
		return menor - 1;
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		System.setIn(new FileInputStream("B.in"));
		System.setOut(new PrintStream("B.out"));
		Scanner sc = new Scanner();
		int tc = sc.nextInt();
		for(int caso = 1; caso <= tc; caso++)	
		{
			int n = sc.nextInt();
			int p = sc.nextInt();
			int[] orden = new int[1 << n];
			for(int i = 0; i < orden.length; i++)
				orden[i] = i;
			int peor = menor(orden, simular(n, orden), p);
			if(peor == Integer.MAX_VALUE)
				peor = 0;
			for(int i = 0; i < orden.length; i++)
				orden[i] = orden.length - 1 - i;
			int mejor = mayor(orden, simular(n, orden), p);
			System.out.println("Case #" + caso + ": " + peor + " " + mejor);
		}
	}

}
