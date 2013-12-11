import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	
	static class Tarea
	{
		int incomingCount;
		int incomingOriginal;
		boolean visitado = false;
		int computador;
		ArrayList <Tarea> hijos = new ArrayList <Tarea> ();
		
		Tarea(int c)
		{
			computador = c;
		}
	}
	
	
	static int intentar(Tarea[] tareas, int inicial)
	{
		for(Tarea t : tareas)
		{
			t.visitado = false;
			t.incomingCount = t.incomingOriginal;
		}
		int computadorActual = inicial;
		int costo = 0;
		while(true)
		{
			while(true)
			{
				boolean cambio = false;
				boolean visitoTodos = true;
				for(Tarea t : tareas)
				{
					visitoTodos &= t.visitado;
					if(!t.visitado && t.incomingCount == 0 && t.computador == computadorActual)
					{
						cambio = true;
						t.visitado = true;
						for(Tarea v : t.hijos)
							v.incomingCount--;
					}
				}
				if(visitoTodos)
					return costo + tareas.length;
				if(!cambio)
					break;
			}
			computadorActual++;
			computadorActual %= 3;
			costo++;
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		Tarea[] tareas = new Tarea[n];
		for(int i = 0; i < n; i++)
			tareas[i] = new Tarea(sc.nextInt() - 1);
		for(int i = 0; i < n; i++)
		{
			int k = sc.nextInt();
			tareas[i].incomingOriginal = k;
			for(int j = 0; j < k; j++)
			{
				int sig = sc.nextInt() - 1;
				tareas[sig].hijos.add(tareas[i]);
			}
		}
		System.out.println(Math.min(intentar(tareas, 0), Math.min(intentar(tareas, 1), intentar(tareas, 2))));
	}
}
