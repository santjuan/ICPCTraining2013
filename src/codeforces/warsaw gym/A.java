import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class A
{
	static class Scanner
	{
		static final boolean debug = false;
		static final String name = "athletic";
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
	
	static class Nodo implements Comparable <Nodo>
	{
		boolean finalizacion = false;
		Nodo padre = null;
		ArrayList <Nodo> hijos = new ArrayList <Nodo> ();
		int altura;
		int hijosLibres = 0;
		
		@Override
		public int compareTo(Nodo o) 
		{
			return o.altura - altura;
		}
	}
	
	public static void arreglar(Nodo[] nodos)
	{
		LinkedList <Nodo> cola = new LinkedList <Nodo> ();
		nodos[0].altura = 0;
		cola.add(nodos[0]);
		while(!cola.isEmpty())
		{
			Nodo actual = cola.poll();
			for(Nodo hijo : actual.hijos)
			{
				if(hijo != actual.padre)
				{
					hijo.padre = actual;
					hijo.altura = actual.altura + 1;
					cola.add(hijo);
				}
			}
		}
		Arrays.sort(nodos);
	}
	
	public static int simular(Nodo[] nodos)
	{
		int total = 0;
		for(Nodo actual : nodos)
		{
			int cuenta = actual.hijosLibres + (actual.finalizacion ? 1 : 0);
			if(cuenta > 1)
				total++;
			else if(cuenta == 1 && actual.padre != null)
				actual.padre.hijosLibres++;
		}
		return total;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		Nodo[] nodos = new Nodo[n];
		for(int i = 0; i < n; i++)
			nodos[i] = new Nodo();
		for(int i = 0; i < n - 1; i++)
		{
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			nodos[a].hijos.add(nodos[b]);
			nodos[b].hijos.add(nodos[a]);
		}
		int nFin = sc.nextInt();
		for(int i = 0; i < nFin; i++)
			nodos[sc.nextInt() - 1].finalizacion = true;
		arreglar(nodos);
		sc.println(simular(nodos) + "");
		sc.out.flush();
	}
}