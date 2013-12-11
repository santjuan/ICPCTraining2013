import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
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
	
	static long[] tiemposActuales;
	
	static class Persona implements Comparable <Persona>
	{
		int id;
		
		public Persona(int i)
		{
			id = i;
		}

		@Override
		public int compareTo(Persona o) 
		{
			return Long.valueOf(tiemposActuales[id]).compareTo(tiemposActuales[o.id]);
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		PriorityQueue <Long> pqC = new PriorityQueue <Long> ();
		@SuppressWarnings("unchecked")
		PriorityQueue <Persona> [] pqsP = new PriorityQueue[3];
		for(int i = 0; i < 3; i++) pqsP[i] = new PriorityQueue <Persona> ();
		int[] ks = sc.nextIntArray(3);
		int[] waits = sc.nextIntArray(3);
		int n = sc.nextInt();
		int[] personas = sc.nextIntArray(n);
		sc.sortIntArray(personas);
		tiemposActuales = new long[personas.length];
		for(int i = 0; i < personas.length; i++) 
		{
			tiemposActuales[i] = personas[i];
			pqsP[0].add(new Persona(i));	
		}
		long maximoTiempo = 0;
		for(int i = 0; i < 3; i++)
		{
			pqC.clear();
			for(int j = 0; j < Math.min(personas.length, ks[i]); j++) pqC.add(0L);
			while(!pqsP[i].isEmpty())
			{
				Persona actual = pqsP[i].poll();
				long tiempo = pqC.poll();
				if(tiempo < tiemposActuales[actual.id])
					tiempo = tiemposActuales[actual.id];
				tiempo += waits[i];
				pqC.add(tiempo);
				tiemposActuales[actual.id] = tiempo;
				if(i == 2)
					maximoTiempo = Math.max(maximoTiempo, tiemposActuales[actual.id] - personas[actual.id]);
				else
					pqsP[i + 1].add(actual);
					
			}
			pqsP[i] = null;
			System.gc();
		}
		System.out.println(maximoTiempo);
	}

}
