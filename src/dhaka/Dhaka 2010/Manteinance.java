import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Manteinance 
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
	
	static interface Ejecucion <E>
	{
		boolean ejecutar(E vals);
	}
	
	static class Arista
	{
		Nodo a;
		Nodo b;
		Nodo superNodoA;
		Nodo superNodoB;
		long costoInicial;
		long costo;
		boolean esBridge;

		public Arista(Nodo aa, Nodo bb, long c)
		{
			a = aa;
			b = bb;
			costoInicial = c;
		}
	}
	
	static class Nodo
	{
		 int id;
		 boolean visitado;
		 boolean visitado1;
		 ArrayList <Arista> aristas = new ArrayList <Arista> ();
		 ArrayList <Arista> aristas2 = new ArrayList <Arista> ();
		 Nodo padre;
		 Nodo superNodo;
		 long costoInicial;
		 long costo;
		 long costoTotal;
		 int h;
		 int l;
		 int nd;
	}
	
	static class Grafo
	{
		Nodo[] nodos;
		
		public Grafo(int n) 
		{
			nodos = new Nodo[n];
			for(int i = 0; i < n; i++)
				nodos[i] = new Nodo();
		}

		void doAll(Ejecucion <Nodo> e)
		{
			for(Nodo n : nodos)
				e.ejecutar(n);
		}
		
		static Nodo[] actualesS = new Nodo[10001];
		static Nodo[] enOrdenS = new Nodo[10001];
		
		void dfs(Ejecucion <Nodo> e, Ejecucion <Nodo> inicial, Ejecucion <Arista> porArista, boolean haciaAdelante, boolean superGrafo)
		{
			for(Nodo n : nodos)
				n.visitado = false;
			for(Nodo n : nodos)
			{
				if(!n.visitado)
				{
					if(inicial != null)
						inicial.ejecutar(n);
					final Nodo[] actuales = actualesS;
					final Nodo[] enOrden = enOrdenS;
					int tamActuales = 0;
					int tamEnOrden = 0;
					actuales[tamActuales++] = n;
					while(tamActuales != 0)
					{
						Nodo este = actuales[--tamActuales];
						enOrden[tamEnOrden++] = este;
						este.visitado = true;
						if(haciaAdelante)
							e.ejecutar(este);
						for(Arista a : este.aristas)
						{
							Nodo otro = !superGrafo ? (a.a == este ? a.b : a.a) : (a.superNodoA == este ? a.superNodoB : a.superNodoA);
							if(!otro.visitado)
							{
								if(porArista == null || porArista.ejecutar(a))
								{
									actuales[tamActuales++] = otro;
									otro.visitado = true;
									otro.padre = este;
								}
							}
						}
					}
					if(!haciaAdelante)
					{
						for(int i = tamEnOrden - 1; i >= 0; i--)
							e.ejecutar(enOrden[i]);
					}
				}
			}
		}
	}
	
	static boolean paila;
	
	static boolean intentar(final long valor, Grafo superGrafo, Grafo original)
	{
		for(Nodo n : original.nodos)
			n.costo = n.costoInicial;
		paila = false;
		superGrafo.dfs(new Ejecucion <Nodo> ()
		{
			@Override
			public boolean ejecutar(Nodo n) 
			{
				for(Arista a : n.aristas)
				{
					Nodo otroSN = a.superNodoA == n ? a.superNodoB : a.superNodoA;
					Nodo otro = a.a.superNodo == otroSN ? a.a : a.b;
					Nodo este = a.a == otro ? a.b : a.a;
					if(otroSN.padre == n)
					{
						if(a.costo + otro.costo <= valor)
							otro.costo += a.costo;
						else
							este.costo += a.costo;
						if(este.costo > valor)
							paila = true;
					}
				}
				return false;
			}
		}, null, null, false, true);				
		return !paila;
	}
	
	static long busquedaBinaria(long a, long b, Grafo original, Grafo superGrafo)
	{
		if(a == b)
			return a;
		if(a + 1 == b)
			return intentar(a, superGrafo, original) ? a : b;
		long mid = (a + b) >>> 1;
		boolean posible = intentar(mid, superGrafo, original);
		if(posible)
			return busquedaBinaria(a, mid, original, superGrafo);
		else
			return busquedaBinaria(mid + 1, b, original, superGrafo);
	}
	
	static int idActual;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int i = 1; i <= t; i++)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			final Grafo original = new Grafo(n);
			for(int j = 0; j < n; j++)
				original.nodos[j].costoInicial = sc.nextLong();
			for(int j = 0; j < m; j++)
			{
				Arista nueva = new Arista(original.nodos[sc.nextInt() - 1], original.nodos[sc.nextInt() - 1], sc.nextLong());
				nueva.a.aristas.add(nueva);
				nueva.b.aristas.add(nueva);
			}
			original.dfs(new Ejecucion <Nodo> () 
			{	
				@Override
				public boolean ejecutar(Nodo n) 
				{
					n.id = idActual++;
					return true;
				}
			},
			new Ejecucion <Nodo> () 
			{	
				@Override
				public boolean ejecutar(Nodo n) 
				{
					idActual = 1;
					return true;
				}
			},
			null, true, false);
			original.dfs(new Ejecucion <Nodo> () 
			{	
				@Override
				public boolean ejecutar(Nodo n) 
				{
					n.nd = 1;
					n.h = n.id;
					n.l = n.id;
					for(Arista a : n.aristas)
					{
						Nodo hijo = a.a == n ? a.b : a.a;
						if(hijo != n.padre)
						{
							if(hijo.padre == n)
							{
								n.nd += hijo.nd;
								n.l = Math.min(n.l, hijo.l);
								n.h = Math.max(n.h, hijo.h);
							}
							else
							{
								n.l = Math.min(n.l, hijo.id);
								n.h = Math.max(n.h, hijo.id);
							}
						}
					}
					return true;
				}
			},
			null, null, false, false);
			for(Nodo nodo : original.nodos)
				for(Arista a : nodo.aristas)
				{
					if(a.a.padre == a.b || a.b.padre == a.a)
					{
						Nodo w = a.a.padre == a.b ? a.a : a.b;
						if(w.l == w.id && w.h < w.id + w.nd)
							a.esBridge = true;
					}
				}
			final ArrayList <Nodo> grafo = new ArrayList <Nodo> ();
			final ArrayList <Nodo> nodosActual = new ArrayList <Nodo> ();
			Ejecucion <Nodo> temp = new Ejecucion <Nodo> () 
			{	
				@Override
				public boolean ejecutar(Nodo vals) 
				{
					if(!nodosActual.isEmpty())
					{
						Nodo superNodo = new Nodo();
						superNodo.costoInicial = nodosActual.size();
						for(Nodo no : nodosActual)
							no.superNodo = superNodo;
						for(Nodo interno : nodosActual)
							for(Arista a : interno.aristas)
								if(a.esBridge)
								{
									superNodo.aristas.add(a);
									if(a.superNodoA == null)
										a.superNodoA = superNodo;
									else
										a.superNodoB = superNodo;
								}
						grafo.add(superNodo);
					}
					nodosActual.clear();
					return true;
				}
			}; 
			original.dfs(new Ejecucion <Nodo> () 
			{	
				@Override
				public boolean ejecutar(Nodo n) 
				{	
					nodosActual.add(n);
					return true;
				}
			},
			temp,
			new Ejecucion <Arista> () 
			{	
				@Override
				public boolean ejecutar(Arista a) 
				{
					return !a.esBridge;
				}
			}, true, false);
			if(nodosActual.size() != 0)
				temp.ejecutar(null);
			Grafo superGrafo = new Grafo(0);
			superGrafo.nodos = grafo.toArray(new Nodo[0]);
			superGrafo.dfs(new Ejecucion <Nodo> ()
			{
				@Override
				public boolean ejecutar(Nodo n) 
				{
					if(n.padre != null)
						n.padre.costoInicial += n.costoInicial;
					return false;
				}
			}, null, null, false, true);
			superGrafo.dfs(new Ejecucion <Nodo> ()
			{
				@Override
				public boolean ejecutar(Nodo n) 
				{
					if(n.padre != null)
						n.costoTotal = n.padre.costoTotal;
					else
						n.costoTotal = n.costoInicial;
					return true;
				}
			}, null, null, true, true);
			idActual = 1;
			superGrafo.dfs(new Ejecucion <Nodo> ()
			{
				@Override
				public boolean ejecutar(Nodo n) 
				{
					n.id = idActual++;
					for(Arista a : n.aristas)
					{
						Nodo otro = a.superNodoA == n ? a.superNodoB : a.superNodoA;
						if(otro.padre == n)
							a.costo = a.costoInicial * (otro.costoInicial) * (otro.costoTotal - otro.costoInicial);
					}
					return false;
				}
			}, null, null, true, true);
			long maximo = 0;
			for(Nodo no : original.nodos)
				maximo = Math.max(maximo, no.costoInicial);
			System.out.println("Case " + i + ": " + busquedaBinaria(maximo, 100000000000000000L, original, superGrafo));
		}
	}
}