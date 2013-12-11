import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CodeH 
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
	

	static void borrar(TreeSet <Integer> [] aristas, int actual) 
	{
		for(int i : aristas[actual])
			aristas[i].remove(actual);
	}

	
	@SuppressWarnings("unchecked")
	static ArrayList <Integer> intentar(TreeSet <Integer> [] aristas, int inicial, int k)
	{
		TreeSet <Integer> [] aristasOriginales = aristas;
		aristas = aristas.clone();
		for(int i = 0; i < aristas.length; i++) aristas[i] = (TreeSet <Integer>) aristas[i].clone();
		ArrayList <Integer> nodos = new ArrayList <Integer> ();
		int actual = inicial;
		nodos.add(actual);
		while(true)
		{
			if(nodos.size() >= k + 1 && aristas[actual].contains(inicial)) return nodos;
			if(aristas[actual].isEmpty()) 				
				return generar(aristasOriginales, nodos, k);
			Integer siguiente = aristas[actual].first();
			if(siguiente == inicial)
			{
				siguiente = aristas[actual].higher(inicial);
				if(siguiente == null)
				{
					siguiente = aristas[actual].lower(inicial);
					if(siguiente == null) return generar(aristasOriginales, nodos, k);
				}
			}
			nodos.add(siguiente);
			actual = siguiente;
			borrar(aristas, actual);
		}
	}
	

	private static ArrayList<Integer> generar(TreeSet<Integer> [] aristasOriginales, ArrayList<Integer> nodos, int k) 
	{
		int ultimo = nodos.get(nodos.size() - 1);
		for(int i = 0; i < ultimo; i++)
		{
			if(aristasOriginales[ultimo].contains(nodos.get(i)))
			{
				ArrayList <Integer> salida = new ArrayList <Integer> ();
				for(int j = i; j < nodos.size(); j++) salida.add(nodos.get(j));
				if(salida.size() < k + 1) throw new RuntimeException();
				return salida;
			}
		}
		throw new RuntimeException();
	}



	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		@SuppressWarnings("unchecked")
		TreeSet <Integer> [] aristas = new TreeSet[n];
		for(int i = 0; i < n; i++) aristas[i] = new TreeSet <Integer> ();
		for(int i = 0; i < m; i++)
		{
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			aristas[a].add(b);
			aristas[b].add(a);
		}
		ArrayList <Integer> nodos = null;
		nodos = intentar(aristas, 0, k);
		System.out.println(nodos.size());
		for(int i = 0; i < nodos.size(); i++)
			System.out.print((i == 0 ? "" : " ") + (nodos.get(i) + 1));
		System.out.println();
	}
}