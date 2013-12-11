import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;


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
	
	static class Circulo implements Comparable <Circulo>
	{
		long x;
		long y;
		long r;
		
		public Circulo(Scanner sc) 
		{
			r = sc.nextLong();
			x = sc.nextLong();
			y = sc.nextLong();
		}

		boolean contains(long xx, long yy)
		{
			return (x - xx) * (x - xx) + (y - yy) * (y - yy) <= r * r;
		}
		
		@Override
		public int compareTo(Circulo o) 
		{
			return Long.valueOf(o.r).compareTo(r);
		}
	}
	
	static boolean isInside(Circulo a, Circulo b)
	{
		return b.contains(a.x, a.y) && a.r * a.r < b.r * b.r;
	}
	
	static int idActual = 0;
	
	static class Nodo
	{
		int id = idActual++;
		int distancia;
		Nodo padre;
		Circulo circulo;
		boolean existe = true;
		ArrayList <Nodo> hijos = new ArrayList <Nodo> ();
	}
	
	static ArrayList <Nodo> todosNodos = new ArrayList <Nodo> ();
	
	static ArrayList <Nodo> darNodos(Nodo padre, LinkedList <Circulo> circulos)
	{
		ArrayList <Nodo> respuesta = new ArrayList <Nodo> ();
		while(!circulos.isEmpty())
		{
			Circulo primero = circulos.pollFirst();
			LinkedList <Circulo> faltantes = new LinkedList <Circulo> ();
			LinkedList <Circulo> internos = new LinkedList <Circulo> ();
			while(!circulos.isEmpty())
			{
				Circulo siguiente = circulos.pollFirst();
				if(isInside(siguiente, primero))
					internos.add(siguiente);
				else
					faltantes.add(siguiente);
			}
			Nodo primeroNodo = new Nodo();
			todosNodos.add(primeroNodo);
			primeroNodo.padre = padre;
			primeroNodo.circulo = primero;
			primeroNodo.hijos = darNodos(primeroNodo, internos);
			respuesta.add(primeroNodo);
			circulos = faltantes;
		}
		return respuesta;
	}

	
	static int darId(Nodo nodo, long x, long y)
	{
		if(nodo.existe && !nodo.circulo.contains(x, y))
			return -1;
		for(Nodo n : nodo.hijos)
		{
			int id = darId(n, x, y);
			if(id != -1)
				return id;
		}
		return nodo.id;
	}
	
	static int[] darDistancias(int id)
	{
		Nodo inicial = todosNodos.get(id);
		ArrayDeque <Nodo> cola = new ArrayDeque <Nodo> ();
		int[] respuesta = new int[todosNodos.size()];
		for(Nodo n : todosNodos)
			n.distancia = Integer.MAX_VALUE;
		inicial.distancia = 0;
		cola.add(inicial);
		while(!cola.isEmpty())
		{
			Nodo n = cola.pollFirst();
			respuesta[n.id] = n.distancia;
			for(Nodo o : n.hijos)
			{
				if(o.distancia == Integer.MAX_VALUE)
				{
					o.distancia = n.distancia + 1;
					cola.add(o);
				}
			}
			if(n.padre != null && n.padre.distancia == Integer.MAX_VALUE)
			{
				n.padre.distancia = n.distancia + 1;
				cola.add(n.padre);
			}
		}
		return respuesta;
	}

	static int[][] dp;
	
	static int[] dar(int id)
	{
		if(dp[id] != null)
			return dp[id];
		return dp[id] = darDistancias(id);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		int[] controles = new int[n];
		int[][] posControles = sc.nextIntMatrix(n, 2);
		Nodo padre = new Nodo();
		padre.existe = false;
		todosNodos.add(padre);
		LinkedList <Circulo> circulos = new LinkedList <Circulo> ();
		for(int i = 0; i < m; i++)
			circulos.add(new Circulo(sc));
		Collections.sort(circulos);
		padre.hijos = darNodos(padre, circulos);
		dp = new int[todosNodos.size()][];
		for(int i = 0; i < n; i++)
			controles[i] = darId(padre, posControles[i][0], posControles[i][1]);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < k; i++)
		{
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			sb.append(dar(controles[a])[controles[b]]);
			sb.append('\n');
		}
		System.out.print(sb.toString());
		System.out.flush();
	}
}
