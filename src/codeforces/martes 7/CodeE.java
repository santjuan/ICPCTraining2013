import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
	
	static class Entrada implements Comparable <Entrada>
	{
		int costo;
		int cuantos;
		
		Entrada(int co, int cu)
		{
			costo = co;
			cuantos = cu;
		}

		@Override
		public int compareTo(Entrada o) 
		{
			return o.costo - costo;
		}
	}
	
	static class Planeta
	{
		Entrada[] cuantosCompra;
		int[] costoVenta;
		
		Planeta(int m)
		{
			cuantosCompra = new Entrada[m];
			costoVenta = new int[m];
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		Planeta[] planetas = new Planeta[n];
		for(int i = 0; i < n; i++)
		{
			planetas[i] = new Planeta(m);
			sc.next();
			for(int j = 0; j < m; j++)
			{
				int co = sc.nextInt();
				int ve = sc.nextInt();
				int cu = sc.nextInt();
				planetas[i].cuantosCompra[j] = new Entrada(co, cu);
				planetas[i].costoVenta[j] = ve;
			}
		}
		int mejorGanancia = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
			{
				if(i == j)
					continue;
				Entrada[] entradasAct = new Entrada[m];
				for(int l = 0; l < m; l++)
					entradasAct[l] = new Entrada(planetas[j].costoVenta[l] - planetas[i].cuantosCompra[l].costo, planetas[i].cuantosCompra[l].cuantos);
				Arrays.sort(entradasAct);
				int kActual = k;
				int ganancia = 0;
				for(Entrada e : entradasAct)
				{
					if(e.costo < 0 || kActual == 0)
						break;
					ganancia += Math.min(kActual, e.cuantos) * e.costo;
					kActual -= Math.min(kActual, e.cuantos);
				}
				mejorGanancia = Math.max(mejorGanancia, ganancia);
			}
		System.out.println(mejorGanancia);
	}

}
