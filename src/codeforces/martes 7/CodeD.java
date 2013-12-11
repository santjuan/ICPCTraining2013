import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
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
	
	
	static class Figura implements Comparable <Figura>
	{
		long cuantos;
		long reward;
		
		Figura(long c, long r)
		{
			cuantos = c;
			reward = r;
		}

		@Override
		public int compareTo(Figura o) 
		{
			return Long.valueOf(reward).compareTo(o.reward);
		}
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		LinkedList <Figura> figuras = new LinkedList <Figura> ();
		for(int i = 0; i < n; i++)
			figuras.add(new Figura(sc.nextLong(), sc.nextLong()));
		LinkedList <Long> faltantes = new LinkedList <Long> ();
		int t = sc.nextInt();
		long[] vals = sc.nextLongArray(t);
		for(int i = 0; i < t; i++)
			faltantes.add(vals[i] - (i == 0 ? 0 : vals[i - 1]));
		faltantes.add(Long.MAX_VALUE);
		Collections.sort(figuras);
		long total = 0;
		long factorActual = 1;
		while(!figuras.isEmpty())
		{
			Figura actual = figuras.peek();
			long faltantesActual = faltantes.peek();
			if(actual.cuantos <= faltantesActual)
			{
				figuras.poll();
				total += factorActual * actual.cuantos * actual.reward;
				faltantesActual -= actual.cuantos;
				faltantes.poll();	
				if(faltantesActual != 0)
					faltantes.addFirst(faltantesActual);
				else
					factorActual++;
			}
			else
			{
				total += factorActual * faltantesActual * actual.reward;
				actual.cuantos -= faltantesActual;
				if(actual.cuantos == 0)
					figuras.poll();
				faltantes.poll();
				factorActual++;
			}
		}
		System.out.println(total);
	}
}