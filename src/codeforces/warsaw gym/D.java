import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class D 
{
	static class Scanner
	{
		static final boolean debug = true;
		static final String name = "numbereater";
		BufferedReader br;
		StringTokenizer st = new StringTokenizer("");
		PrintStream out;		
		
		Scanner()
		{
			if(debug)
			{
				br = new BufferedReader(new InputStreamReader(System.in));
				out = System.out;
			}
			else
			{
				try 
				{
					br = new BufferedReader(new FileReader(name + ".in"));
					out = new PrintStream(new BufferedOutputStream(new FileOutputStream(name + ".out")), false);
				}
				catch (FileNotFoundException e) 
				{
					throw(new RuntimeException());
				}
			}
		}
		
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
		
		public void println(String s)
		{
			out.println(s);
		}
		
		public void print(String s)
		{
			out.print(s);
		}
	}
	
	static class Arista
	{
		Nodo otro;
		int peso;

		public Arista(Nodo a, int paridad)
		{
			otro = a;
			peso = paridad;
		}
	}
	
	public static class Nodo
	{
		int numero;
		Boolean color = null;
		ArrayList <Arista> aristas = new ArrayList <Arista> ();	
		
		Nodo(int n)
		{
			numero = n;
		}
	}
	
	static int[][] queries;
	
	static Nodo darNodo(TreeMap <Integer, Nodo> mapa, int indice)
	{
		if(!mapa.containsKey(indice))
			mapa.put(indice, new Nodo(indice));
		return mapa.get(indice);
		
	}
	
	static TreeMap <Integer, Nodo> simular(int indice)
	{
		TreeMap <Integer, Nodo> respuesta = new TreeMap <Integer, Nodo> ();
		for(int i = 0; i <= indice; i++)
		{
			Nodo a = darNodo(respuesta, queries[i][0] - 1);
			Nodo b = darNodo(respuesta, queries[i][1]);
			int paridad = queries[i][2];
			a.aristas.add(new Arista(b, paridad));
			b.aristas.add(new Arista(a, paridad));
		}
		return respuesta;
	}
	
	static boolean colorear(Nodo actual, boolean color)
	{
		if(actual.color != null)
			return actual.color.booleanValue() == color;
		actual.color = color;
		for(Arista a : actual.aristas)
		{
			if(a.peso == 1)
			{
				if(!colorear(a.otro, !color))
					return false;
			}
			else
			{
				if(!colorear(a.otro, color))
					return false;
			}
		}
		return true;
	}
	
	static boolean validar(int indice)
	{
		TreeMap <Integer, Nodo> nodos = simular(indice);
		for(Nodo n : nodos.values())
			if(n.color == null && !colorear(n, false))
				return false;
		return true;
	}
	
	static int busquedaBinaria(int a, int b)
	{
		if(a == b)
			return validar(a) ? a : Integer.MAX_VALUE;
		if(a + 1 == b)
			return validar(b) ? b : validar(a) ? a : Integer.MAX_VALUE;
		int mid = (a + b) >>> 1;
		if(validar(mid))
			return busquedaBinaria(mid, b);
		else
			return busquedaBinaria(a, mid - 1);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		queries = new int[n][3];
		for(int i = 0; i < n; i++)
		{
			queries[i][0] = sc.nextInt();
			queries[i][1] = sc.nextInt();
			queries[i][2] = sc.nextInt();
		}
		if(n == 0)
			sc.println("0");
		else
		{
			int res = busquedaBinaria(0, n);
			if(res == Integer.MAX_VALUE)
				System.out.println("0");
			else
				System.out.println(res + 1);
		}
	}
}