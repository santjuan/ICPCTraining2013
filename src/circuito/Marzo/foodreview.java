import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class foodreview
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
	}
	
	static class Estado
	{
		int[][] conjuntos;
		int paridad;
		
		Estado unir(int i, int j, int nodoUno, int nodoDos)
		{
			Estado nuevo = new Estado();
			int[][] nuevoConjunto = new int[conjuntos.length - 1][];
			int current = 0;
			for(int a = 0; a < conjuntos.length; a++)
				if(a != i && a != j)
					nuevoConjunto[current++] = conjuntos[a];
			int[] nuevoC = new int[conjuntos[i].length + conjuntos[j].length];
			int tamPrimero = conjuntos[i].length;
			for(int a = 0; a < tamPrimero; a++)
				nuevoC[a] = conjuntos[i][a];
			for(int a = 0; a < conjuntos[j].length; a++)
				nuevoC[a + tamPrimero] = conjuntos[j][a];
			nuevoConjunto[current++] = nuevoC;
			nuevo.conjuntos = nuevoConjunto;
			nuevo.ordenar();
			int nuevaParidad = paridad;
			nuevaParidad ^= (1 << nodoUno);
			nuevaParidad ^= (1 << nodoDos);
			nuevo.paridad = nuevaParidad;
			return nuevo;
		}
		
		void ordenar()
		{
			for(int[] val : conjuntos)
				Arrays.sort(val);
			Arrays.sort(conjuntos, new Comparator <int[]> () 
			{
				@Override
				public int compare(int[] o1, int[] o2) 
				{
					return o1[0] - o2[0];
				}
			});
		}

		@Override
		public int hashCode() 
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.deepHashCode(conjuntos);
			result = prime * result + paridad;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Estado other = (Estado) obj;
			if (!Arrays.deepEquals(conjuntos, other.conjuntos))
				return false;
			if (paridad != other.paridad)
				return false;
			return true;
		}
		
		
	}
	
	static int[][] costo;
	static HashMap <Estado, Integer> dp = new HashMap <Estado, Integer> ();
	
	static int intentar(Estado e)
	{
		if(dp.containsKey(e))
			return dp.get(e);
		if(e.conjuntos.length == 1)
		{
			dp.put(e, mejorCosto(e.paridad));
			return dp.get(e);
		}
		int mejor = Integer.MAX_VALUE;
		for(int i = 0; i < 1; i++)
			for(int j = i + 1; j < e.conjuntos.length; j++)
			{
				int[] a = e.conjuntos[i];
				int[] b = e.conjuntos[j];
				for(int k = 0; k < a.length; k++)
					for(int l = 0; l < b.length; l++)
						mejor = Math.min(mejor, costo[a[k]][b[l]] + intentar(e.unir(i, j, a[k], b[l])));
			}
		dp.put(e, mejor);
		return mejor;
	}

	static int[] optimoMascaras;
	
	static int mejorCosto(int mascara)
	{
		return optimoMascaras[mascara];
	}
	
	static class Entrada implements Comparable <Entrada>
	{
		int mascara;
		int costo;
		
		public Entrada(int m, int c)
		{
			mascara = m;
			costo = c;
		}
		
		@Override
		public int compareTo(Entrada o) 
		{
			return costo - o.costo;
		}
	}
	
	static void intentarDijkstra()
	{
		int[] mejor = new int[1 << costo.length];
		for(int i = 0; i < mejor.length; i++)
			mejor[i] = 100000000;
		PriorityQueue <Entrada> pq = new PriorityQueue <Entrada> ();
		mejor[0] = 0;
		pq.add(new Entrada(0, 0));
		while(!pq.isEmpty())
		{
			Entrada actual = pq.poll();
			if(mejor[actual.mascara] != actual.costo)
				continue;
			for(int i = 0; i < costo.length; i++)
				for(int j = 0; j < costo.length; j++)
				{
					int costoNuevo = actual.costo + costo[i][j];
					int mascaraNueva = actual.mascara;
					mascaraNueva ^= 1 << i;
					mascaraNueva ^= 1 << j;
					if(mejor[mascaraNueva] > costoNuevo)
					{
						mejor[mascaraNueva] = costoNuevo;
						pq.add(new Entrada(mascaraNueva, costoNuevo));
					}
				}
		}
		optimoMascaras = mejor;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			int r = sc.nextInt();
			if(n == 0 && r == 0)
				return;
			dp.clear();
			Estado inicial = new Estado();
			inicial.conjuntos = new int[n][1];
			for(int i = 0; i < n; i++)
				inicial.conjuntos[i][0] = i;
			inicial.ordenar();
			inicial.paridad = 0;
			int costoObligatorios = 0;
			costo = new int[n][n];
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < n; j++)
					costo[i][j] = 100000000;
				costo[i][i] = 0;
			}
			for(int t = 0; t < r; t++)
			{
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				int c = sc.nextInt();
				costoObligatorios += c;
				int conjuntoA = 0;
				int conjuntoB = 0;
				for(int i = 0; i < inicial.conjuntos.length; i++)
					for(int j = 0; j < inicial.conjuntos[i].length; j++)
						if(inicial.conjuntos[i][j] == a)
							conjuntoA = i;
						else if(inicial.conjuntos[i][j] == b)
							conjuntoB = i;
				if(conjuntoA != conjuntoB)
					inicial = inicial.unir(conjuntoA, conjuntoB, a, b);
				else
				{
					inicial.paridad ^= 1 << a;
					inicial.paridad ^= 1 << b;
				}
				costo[a][b] = Math.min(costo[a][b], c);
				costo[b][a] = Math.min(costo[b][a], c);
			}
			int f = sc.nextInt();
			for(int t = 0; t < f; t++)
			{
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				int c = sc.nextInt();
				costo[a][b] = Math.min(costo[a][b], c);
				costo[b][a] = Math.min(costo[b][a], c);
			}
			for(int k = 0; k < n; k++)
				for(int i = 0; i < n; i++)
					for(int j = 0; j < n; j++)
						costo[i][j] = Math.min(costo[i][j], costo[i][k] + costo[k][j]);
			ArrayList <int[]> conjuntos = new ArrayList <int[]> ();
			for(int[] c : inicial.conjuntos)
				if(c.length != 1 || (c.length == 1 && c[0] == 0))
					conjuntos.add(c);
			int[][] conjuntosC = new int[conjuntos.size()][];
			for(int i = 0; i < conjuntosC.length; i++)
				conjuntosC[i] = conjuntos.get(i);
			Estado inicialN = new Estado();
			inicialN.conjuntos = conjuntosC;
			inicialN.paridad = inicial.paridad;
			inicialN.ordenar();
			intentarDijkstra();
			System.out.println(intentar(inicialN) + costoObligatorios);
		}
	}
}