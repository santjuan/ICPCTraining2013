import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class D 
{
	static class Scanner
	{
		static final boolean debug = false;
		static final String name = "domestic";
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
	
	static class DisjointSet
	{
		int[] p, rank;
		int size;

		public DisjointSet(int sizeI)
		{
			rank = new int[sizeI];
			p = new int[sizeI];
			size = sizeI;
			reset();
		}

		public void reset()
		{
			for(int i = 0; i < size; i++)
				make_set(i);
		}

		private void make_set(int x)
		{
			p[x] = x;
			rank[x] = 0;
		}

		public void merge(int x, int y)
		{
			link(find_set(x), find_set(y));
		}

		public void link(int x, int y)
		{
			if(rank[x] > rank[y])
				p[y] = x;
			else
			{
				p[x] = y;
				if(rank[x] == rank[y])
					rank[y] += 1;
			}
		}

		public int find_set(int x)
		{
			if(x != p[x])
				p[x] = find_set(p[x]);
			return p[x];
		}
	}

	static class Arista implements Comparable <Arista>
	{
		int a;
		int b;
		int cost;
		int nArista;

		public Arista(int aa, int bb, int cc, int i) 
		{
			a = aa;
			b = bb;
			cost = cc;
			nArista = i;
		}

		@Override
		public int compareTo(Arista o) 
		{
			return cost - o.cost;
		}
	}
	
	static class Respuesta
	{
		int cost;
		boolean first;
		Respuesta next;
		
		Respuesta(int c, boolean f, Respuesta n)
		{
			cost = c;
			first = f;
			next = n;
		}
	}

	static Arista[] todasAristas;
	static int[] integralImage;
	static Respuesta[][] dp;
	
	static Respuesta dp(int mochilaUno, int arista)
	{
		if(dp[mochilaUno][arista] != null)
			return dp[mochilaUno][arista];
		if(arista == todasAristas.length)
			return dp[mochilaUno][arista] = new Respuesta(0, false, null);
		int mochilaDos = integralImage[arista] - mochilaUno;
		Respuesta mejor = new Respuesta(Integer.MAX_VALUE, true, null);
		if(mochilaUno + todasAristas[arista].cost <= q1)
		{
			Respuesta valor = dp(mochilaUno + todasAristas[arista].cost, arista + 1);
			if(valor.cost != Integer.MAX_VALUE && valor.cost + p1 * todasAristas[arista].cost < mejor.cost)
				mejor = new Respuesta(valor.cost + p1 * todasAristas[arista].cost, true, valor);
		}
		if(mochilaDos + todasAristas[arista].cost <= q2)
		{
			Respuesta valor = dp(mochilaUno, arista + 1);
			if(valor.cost != Integer.MAX_VALUE && valor.cost + p2 * todasAristas[arista].cost < mejor.cost)
				mejor = new Respuesta(valor.cost + p2 * todasAristas[arista].cost, false, valor);
		}
		return dp[mochilaUno][arista] = mejor;
	}
	
	static int p1, p2, q1, q2;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		DisjointSet ds = new DisjointSet(n + 1);
		ArrayList <Arista> aristas = new ArrayList <Arista> ();
		for(int i = 0; i < m; i++)
			aristas.add(new Arista(sc.nextInt(), sc.nextInt(), sc.nextInt(), i + 1));
		Collections.sort(aristas);
		ArrayList <Arista> aristasI = new ArrayList <Arista> ();
		for(Arista a : aristas)
		{
			if(ds.find_set(a.a) != ds.find_set(a.b))
			{
				aristasI.add(a);
				ds.merge(a.a, a.b);
			}
		}
		todasAristas = aristasI.toArray(new Arista[0]);
		integralImage = new int[todasAristas.length];
		for(int i = 1; i < todasAristas.length; i++)
			integralImage[i] = integralImage[i - 1] + todasAristas[i - 1].cost;
		p1 = sc.nextInt();
		q1 = sc.nextInt();
		p2 = sc.nextInt();
		q2 = sc.nextInt();
		dp = new Respuesta[q1 + 10][todasAristas.length + 1];
		Respuesta cual = dp(0, 0);
		if(cual.cost == Integer.MAX_VALUE)
			sc.println("Impossible");
		else
		{
			sc.out.println(cual.cost);
			int i = 0;
			while(cual.next != null)
			{
				sc.println(todasAristas[i++].nArista + " " + (cual.first ? "5" : "6"));
				cual = cual.next;
			}
		}
		sc.out.flush();
	}
}