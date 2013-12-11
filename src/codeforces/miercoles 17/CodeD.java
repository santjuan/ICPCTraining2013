import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
	
	static StringTokenizer st;
	
	
	static int cuenta = 0;
	
	static class Nodo
	{
		String nombre;
		ArrayList <Nodo> subordinados = new ArrayList <Nodo> ();
		
		Nodo()
		{
			nombre = st.nextToken();
			if(st.nextToken().equals("."))
				return;
			while(true)
			{
				subordinados.add(new Nodo());
				String delim = st.nextToken();
				if(delim.equals("."))
					break;
			}
		}
		
		void visitar(HashMap <String, Integer> vals)
		{
			if(vals.containsKey(nombre))
				cuenta += vals.get(nombre);
			else
				vals.put(nombre, 0);
			vals.put(nombre, vals.get(nombre) + 1);
			for(Nodo n : subordinados)
				n.visitar(vals);
			vals.put(nombre, vals.get(nombre) - 1);
		}
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		String linea = sc.nextLine(); 
		st = new StringTokenizer(linea, ":.,", true);
		Nodo raiz = new Nodo();
		raiz.visitar(new HashMap <String, Integer> ());
		System.out.println(cuenta);
	}
}