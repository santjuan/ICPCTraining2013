import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeA 
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
	
	static int numeroDoces(int n)
	{
		int base = 2;
		int ans = 0;
		while((n / base) != 0)
		{
			ans += (n / base);
			base *= 2;
		}
		return ans;
	}
	
	
	static class Arreglo
	{
		Long mejorInterno;
		Long mejorTerminar;
		Long mejorEmpezar;
		Long sumaTotal;
		int[] vals;
		
		public Arreglo(int[] este) 
		{
			vals = este;
		}

		long mejorTerminar()
		{
			if(mejorTerminar == null)
			{
				long best = Integer.MIN_VALUE;
				long current = 0;
				for(int i = vals.length - 1; i >= 0; i--)
				{
					current += vals[i];
					best = Math.max(best, current);
				}
				mejorTerminar = best;
			}
			return mejorTerminar;
		}
		
		long mejorEmpezar()
		{
			if(mejorEmpezar == null)
			{
				long best = Integer.MIN_VALUE;
				long current = 0;
				for(int i = 0; i < vals.length; i++)
				{
					current += vals[i];
					best = Math.max(best, current);
				}
				mejorEmpezar = best;
			}
			return mejorEmpezar;
		}
		
		long mejorInterno()
		{
			if(mejorInterno == null)
			{
				long bestAnt = Integer.MIN_VALUE;
				long best = Integer.MIN_VALUE;
				long current = 0;
				for(int i = 0; i < vals.length; i++)
				{
					bestAnt = Math.max(bestAnt + vals[i], vals[i]);
					best = Math.max(best, bestAnt);
				}
				mejorInterno = best;
			}
			return mejorInterno;
		}
		
		long sumaTotal()
		{
			if(sumaTotal == null)
			{
				long acum = 0;
				for(int i : vals)
					acum += i;
				sumaTotal = acum;
			}
			return sumaTotal;
		}
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		Arreglo[] arreglos = new Arreglo[n];
		for(int i = 0; i < n; i++)
		{
			int[] este = new int[sc.nextInt()];
			for(int j = 0; j < este.length; j++)
				este[j] = sc.nextInt();
			arreglos[i] = new Arreglo(este);
		}
		long bestGlobal = Integer.MIN_VALUE;
		long bestAnt = Integer.MIN_VALUE;
		for(int i = 0; i < m; i++)
		{
			Arreglo actual = arreglos[sc.nextInt() - 1];
			bestGlobal = Math.max(bestGlobal, actual.mejorInterno());
			bestGlobal = Math.max(bestGlobal, bestAnt + actual.mejorEmpezar());
			long bestSig = actual.mejorTerminar();
			bestSig = Math.max(bestSig, bestAnt + actual.sumaTotal());
			bestAnt = bestSig;
			bestGlobal = Math.max(bestGlobal, bestSig);
		}
		System.out.println(bestGlobal);
	}

}
