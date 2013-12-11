import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;


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
	
	
	static class Entrada
	{
		int posicion;
		int valor;
	}
	
	static class SegmentTree
	{
		Entrada[] M;
		
		public SegmentTree(int size)
		{
			M = new Entrada[size * 4 + 4];
			for(int i = 0; i < M.length; i++)
			{
				M[i] = new Entrada();
				M[i].posicion = 0;
				M[i].valor = Integer.MIN_VALUE;
			}
		}

		//it's initially called update(1, 0, size - 1, pos, value)
		void update(int node, int b, int e, int pos, int value)
		{
			//if the current interval doesn't intersect 
			//the updated position return -1
			if (pos > e || pos < b)
				return;
			//if the current interval is the updated position
			//then update it
			if (b == e)
			{
				M[node].posicion = pos;
				M[node].valor = value;
				return;
			}
			update(2 * node, b, (b + e) / 2, pos, value);
			update(2 * node + 1, (b + e) / 2 + 1, e, pos, value);
			//update current value after updating childs
			Entrada hijo1 = M[2 * node];
			Entrada hijo2 = M[2 * node + 1];
			if(hijo1.valor > hijo2.valor)
			{
				M[node].posicion = hijo1.posicion;
				M[node].valor = hijo1.valor;
			}
			else
			{
				M[node].posicion = hijo2.posicion;
				M[node].valor = hijo2.valor;
			}
		}
		
		//it's initially called query(1, 0, size - 1, i, j)
		Entrada query(int node, int b, int e, int i, int j)
		{	
			//if the current interval doesn't intersect 
			//the query interval return -1
			if (i > e || j < b)
				return null;
			
			//if the current interval is completely included in 
			//the query interval return the value of this node
			if (b >= i && e <= j)
				return M[node];

			//compute the value from
			//left and right part of the interval
			Entrada p1 = query(2 * node, b, (b + e) / 2, i, j);
			Entrada p2 = query(2 * node + 1, (b + e) / 2 + 1, e, i, j);
			if(p1 == null)
				return p2;
			if(p2 == null)
				return p1;
			if(p1.valor > p2.valor)
				return p1;
			else
				return p2;
		}
	}
	
	static TreeMap <Integer, Integer> todos = new TreeMap <Integer, Integer> ();
	
	
	static int masDerecha(int a)
	{
		Integer bK = todos.floorKey(a);
		if(bK == null)
			return -1;
		else
			return todos.get(bK);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int[][] dominoes = new int[n][2];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < 2; j++)
				dominoes[i][j] = sc.nextInt();
		int[][] clon = dominoes.clone();
		Arrays.sort(dominoes, new Comparator <int[]> () 
		{
			@Override
			public int compare(int[] o1, int[] o2) 
			{
				return o2[0] - o1[0];
			}
		});
		for(int i = 0; i < n; i++)
			todos.put(dominoes[i][0], i);
		HashMap <Integer, Integer> respuesta = new HashMap <Integer, Integer> ();
		SegmentTree arbol = new SegmentTree(n);
		int[] respuestas = new int[n];
		for(int i = 0; i < n; i++)
		{
			int x = dominoes[i][0];
			int h = dominoes[i][1];
			int derecha = masDerecha(x + h - 1);
			if(derecha == i)
			{
				respuesta.put(x, 1);
				respuestas[i] = 1;
			}
			else
			{
				Entrada e = arbol.query(1, 0, n - 1, derecha, i);
				if(e.valor < x + h - 1)
				{
					respuesta.put(x, i - derecha + 1);
					respuestas[i] = i - derecha + 1;
				}
				else
				{
					int estaRespuesta = i - e.posicion + respuestas[e.posicion];
					respuesta.put(x, estaRespuesta);
					respuestas[i] = estaRespuesta;
				}
			}
			arbol.update(1, 0, n - 1, i, x + h - 1);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++)
			sb.append(respuesta.get(clon[i][0])).append(" ");
		System.out.println(sb.toString().trim());
	}
}