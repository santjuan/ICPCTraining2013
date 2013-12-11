import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class LiveC 
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
	
	static class Nodo
	{
		ArrayList <Character> adjacentes = new ArrayList <Character> ();
		int currentBest = Integer.MAX_VALUE;
	}
	
	static HashMap <Character, Nodo> nodos = new HashMap <Character, Nodo> ();
	
	static Nodo darNodo(char c)
	{
		if(!nodos.containsKey(c))
			nodos.put(c, new Nodo());
		return nodos.get(c);
	}
	
	static class Entrada implements Comparable <Entrada>
	{
		char cual;
		int valor;
		
		public Entrada(char c, int v)
		{
			cual = c;
			valor = v;
		}

		@Override
		public int compareTo(Entrada o) 
		{
			return valor - o.valor;
		}
	}

	static int resolver(int nInicial, char inicio, char fin) 
	{
		PriorityQueue <Entrada> pq = new PriorityQueue<Entrada> ();
		darNodo(fin).currentBest = nInicial;
		pq.add(new Entrada(fin, nInicial));
		while(!pq.isEmpty())
		{
			Entrada actual = pq.poll();
			if(actual.valor != darNodo(actual.cual).currentBest) continue;
			int valorReal = actual.valor;
			if(actual.cual == inicio)
				return valorReal;
			if(actual.cual >= 'a' && actual.cual <= 'z')
				valorReal++;
			else
			{
				int tmp = (int) Math.ceil(valorReal / 20.0);
				while(true)
				{
					int tmp2 = (int) Math.ceil((valorReal + tmp) / 20.0);
					if(tmp2 == tmp)
						break;
					tmp = tmp2;
				}
				valorReal += tmp;
			}
			for(char c : darNodo(actual.cual).adjacentes)
			{
				Nodo otro = darNodo(c);
				if(otro.currentBest > valorReal)
				{
					otro.currentBest = valorReal;
					pq.add(new Entrada(c, otro.currentBest));
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int caso = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == -1) return;
			nodos.clear();
			for(int i = 0; i < n; i++)
			{
				char a = sc.next().charAt(0);
				char b = sc.next().charAt(0);
				darNodo(a).adjacentes.add(b);
				darNodo(b).adjacentes.add(a);
			}
			int nInicial = sc.nextInt();
			char inicio = sc.next().charAt(0);
			char fin = sc.next().charAt(0);
			System.out.println("Case " + caso++ + ": " + resolver(nInicial, inicio, fin));
		}
	}
}