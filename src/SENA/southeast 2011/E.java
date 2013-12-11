import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;


public class E 
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
		int i;
		int j;
		int dir;
		int pasos;
		int veces;
		
		public Estado(int iInicial, int jInicial, int dirInicial, int pasosInicial, int vecesInicial) 
		{
			i = iInicial;
			j = jInicial;
			dir = dirInicial;
			pasos = pasosInicial;
			veces = vecesInicial;
		}
		
		boolean existe()
		{
			return i >= 0 && i < visitados.length && j >= 0 && j < visitados[0].length && mapa[i][j] != '*';
		}
		
		Estado visitado()
		{
			return visitados[i][j][dir];
		}
		
		Estado derecha()
		{
			return new Estado(i, j, (dir + 1) % 4, pasos + 1, veces);
		}
		
		Estado izquierda()
		{
			return new Estado(i, j, (dir - 1) < 0 ? 3 : dir - 1, pasos + 1, veces);
		}
		
		Estado adelante(int x)
		{
			Estado val = new Estado(dir == 0 ? i - x : dir == 2 ? i + x : i, dir == 1 ? j + x : dir == 3 ? j - x : j, dir, pasos + 1, veces);
			if(val.existe())
				return val;
			else
				return null;
		}
		
		Estado actual()
		{
			return visitados[i][j][dir];
		}
	}
	
	static Estado[][][] visitados;
	static String direcciones = "NESW";
	

	static char[][] mapa;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			if(n == 0 && m == 0)
				return;
			mapa = new char[n][];
			for(int i = 0; i < n; i++)
				mapa[i] = sc.next().toCharArray();
			int iInicial = 0;
			int jInicial = 0;
			int dirInicial = 0;
			int iFinal = 0;
			int jFinal = 0;
			for(int i = 0; i < n; i++)
				for(int j = 0; j < m; j++)
				{
					char este = mapa[i][j];
					if(direcciones.contains(este + ""))
					{
						iInicial = i;
						jInicial = j;
						dirInicial = direcciones.indexOf(este);
					}
					if(este == 'X')
					{
						iFinal = i;
						jFinal = j;
					}
				}
			visitados = new Estado[n][m][4];
			LinkedList <Estado> cola = new LinkedList <Estado> ();
			Estado inicial = new Estado(iInicial, jInicial, dirInicial, 0, 1);
			visitados[inicial.i][inicial.j][inicial.dir] = inicial;
			cola.add(inicial);
			int total = 0;
			int nPasosTotal = Integer.MAX_VALUE;
			while(!cola.isEmpty())
			{
				Estado actual = cola.poll();
				if(actual.i == iFinal && actual.j == jFinal && actual.pasos <= nPasosTotal)
				{
					total += actual.veces;
					total %= 1000000;
					nPasosTotal = actual.pasos;
				}
				ArrayList <Estado> siguientes = new ArrayList <Estado> ();
				siguientes.add(actual.izquierda());
				siguientes.add(actual.derecha());
				for(Estado e : siguientes)
				{
					if(e == null)
						break;
					if(e.actual() != null)
					{
						if(e.pasos == e.actual().pasos)
						{
							e.actual().veces += e.veces;
							e.actual().veces %= 1000000;
						}
					}
					else
					{
						visitados[e.i][e.j][e.dir] = e;
						cola.add(e);
					}
				}
				for(int i = 1; i <= 100; i++)
				{
					Estado e = actual.adelante(i);
					if(e == null)
						break;
					if(e.actual() != null)
					{
						if(e.pasos == e.actual().pasos)
						{
							e.actual().veces += e.veces;
							e.actual().veces %= 1000000;
						}
					}
					else
					{
						visitados[e.i][e.j][e.dir] = e;
						cola.add(e);
					}
				}
			}
			System.out.println((nPasosTotal == Integer.MAX_VALUE ? 0 : nPasosTotal) + " " + total);
		}
	}
}