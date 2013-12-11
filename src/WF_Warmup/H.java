import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class H
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
	
	static final int tamNodo = 650;
	
	static class Bucket
	{
		int idOriginal;
		int numero;
		int idSegment;
		
		public Bucket(int id, int nu) 
		{
			idOriginal = id;
			numero = nu;
		}
	}
	
	static class Nodo
	{
		Bucket[] arreglo = new Bucket[tamNodo];
		int tam = 0;
		int total = 0;
		Nodo siguiente = null;
		
		void insertar(Bucket b, int rank)
		{
			total += b.numero;
			for(int i = 0; i < tam; i++)
			{
				if(rank == 0)
				{
					correr(i, 1);
					arreglo[i] = b;
					return;
				}
				if(arreglo[i].numero > rank)
				{
					Bucket x = arreglo[i];
					int total = x.numero;
					x.numero = rank;
					total -= rank;
					correr(i + 1, 2);
					arreglo[i + 1] = b;
					arreglo[i + 2] = new Bucket(x.idOriginal, total);
					return;
				}
				rank -= arreglo[i].numero;
			}
			if(rank == 0)
			{
				arreglo[tam++] = b;
				return;
			}
		}

		private void correr(int a, int cuanto)
		{
			for(int i = tam - 1; i >= a; i--)
				arreglo[i + cuanto] = arreglo[i];
			tam += cuanto;
		}
	}
	
	
	static class Lista
	{
		Nodo cabeza;
		
		Lista()
		{
			cabeza = new Nodo();
		}
		
		void insertar(Bucket b, int rank)
		{
			Nodo actual = cabeza;
			while(actual != null)
			{
				if(actual.total >= rank)
				{
					actual.insertar(b, rank);
					if(actual.tam >= tamNodo - 2)
						split(actual);
					return;
				}
				rank -= actual.total;
				actual = actual.siguiente;
			}
		}

		void split(Nodo actual) 
		{
			Nodo derecho = new Nodo();
			int limite = actual.tam;
			for(int i = limite / 2; i < limite; i++)
			{
				Bucket aPoner = actual.arreglo[i];
				derecho.arreglo[derecho.tam++] = aPoner;
				actual.tam--;
				actual.total -= aPoner.numero;
				derecho.total += aPoner.numero;
			}
			derecho.siguiente = actual.siguiente;
			actual.siguiente = derecho;
		}
	}
	
	
	static class NodoSegment
	{
		int a, b, ninosSobrantes, ninasSobrantes;
		int parejasInternas;
		NodoSegment izquierdo, derecho;

		NodoSegment(int aa, int bb)
		{
			a = aa;
			b = bb;
			if(aa != bb)
			{
				int mid = (a + b) / 2;
				izquierdo = new NodoSegment(a, mid);
				derecho = new NodoSegment(mid + 1, b);
			}
		}
		
		void update(int indice, int ninos, int ninas)
		{
			if(a > indice || b < indice)
				return;
			if(a == b)
			{
				ninosSobrantes = ninos;
				ninasSobrantes = ninas;
				parejasInternas = 0;
			}
			else
			{
				izquierdo.update(indice, ninos, ninas);
				derecho.update(indice, ninos, ninas);
				actualizarInvariante();
			}
		}

		void actualizarInvariante() 
		{
			parejasInternas = izquierdo.parejasInternas + derecho.parejasInternas;
			ninosSobrantes = izquierdo.ninosSobrantes + derecho.ninosSobrantes;
			ninasSobrantes = izquierdo.ninasSobrantes + derecho.ninasSobrantes;
			int emparejar = Math.min(izquierdo.ninosSobrantes, derecho.ninasSobrantes);
			parejasInternas += emparejar;
			ninosSobrantes -= emparejar;
			ninasSobrantes -= emparejar;
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int caso = 1; caso <= t; caso++)
		{
			bw.write("Case " + caso + ":\n");
			int n = sc.nextInt();
			int[][] queries = sc.nextIntMatrix(n, 3);
			Lista lista = new Lista();
			lista.insertar(new Bucket(n, 1), 0);
			for(int i = 0; i < n; i++)
				lista.insertar(new Bucket(i, queries[i][2]), queries[i][1]);
			@SuppressWarnings("unchecked")
			LinkedList <Bucket> [] listas = new LinkedList[n + 1];
			for(int i = 0; i <= n; i++) 
				listas[i] = new LinkedList <Bucket> ();
			Nodo actual = lista.cabeza;
			int tamTotal = 0;
			while(actual != null)
			{
				Bucket[] buckets = actual.arreglo;
				int tam = actual.tam;
				for(int i = 0; i < tam; i++)
				{
					buckets[i].idSegment = tamTotal++;
					listas[buckets[i].idOriginal].add(buckets[i]);
				}
				actual = actual.siguiente;
			}
			NodoSegment raiz = new NodoSegment(0, tamTotal);
			raiz.update(listas[n].peekFirst().idSegment, 1, 0);
			for(int i = 0; i < n; i++)
			{
				boolean ninos = queries[i][0] == 0;
				for(Bucket b : listas[i])
					raiz.update(b.idSegment, ninos ? b.numero : 0, ninos ? 0 : b.numero);
				bw.write(String.valueOf(raiz.parejasInternas));
				bw.write('\n');
			}
		}
		bw.flush();
		bw.close();
	}
}