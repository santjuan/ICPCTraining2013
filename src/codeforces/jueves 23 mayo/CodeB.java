import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CodeB 
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
	
	static class FordFulkerson
	{
		int[][] capacity;
		boolean[] visited;
		int source;
		int sink;
		int primeraPersona;
		int ultimaPersona;
		int primerProyecto;
		int ultimoProyecto;
		
		
		int augment(int x, int min)
		{
			if(x == sink)
				return min;
			visited[x] = true;
			if(x == source)
			{
				for(int i = primeraPersona; i <= ultimaPersona; i++)
				{
					if(!visited[i] && capacity[x][i] != 0)
					{
						int ret = augment(i, 1);
						if(ret != 0)
						{
							capacity[x][i] -= ret;
							capacity[i][x] += ret;
							return ret;
						}
					}
				}
				return 0;
			}
			else if(x >= primeraPersona && x <= ultimaPersona)
			{
				for(int i = primerProyecto; i <= ultimoProyecto; i++)
				{
					if(!visited[i] && capacity[x][i] != 0)
					{
						int ret = augment(i, 1);
						if(ret != 0)
						{
							capacity[x][i] -= ret;
							capacity[i][x] += ret;
							return ret;
						}
					}
				}
				return 0;
			}
			else
			{
				for(int i = sink; i <= sink; i++)
				{
					if(!visited[i] && capacity[x][i] != 0)
					{
						int ret = augment(i, 1);
						if(ret != 0)
						{
							capacity[x][i] -= ret;
							capacity[i][x] += ret;
							return ret;
						}
					}
				}
				for(int i = primeraPersona; i <= ultimaPersona; i++)
				{
					if(!visited[i] && capacity[x][i] != 0)
					{
						int ret = augment(i, 1);
						if(ret != 0)
						{
							capacity[x][i] -= ret;
							capacity[i][x] += ret;
							return ret;
						}
					}
				}
				return 0;
			}
		}
		
		int maxFlow()
		{
			int ret = 0;
			while(true)
			{
				Arrays.fill(visited, false);
				int flow = augment(source, 1);
				if(flow == 0)
					return ret;
				ret += flow;
			}
		}

		public void clear() 
		{
			for(int[] i : capacity)
				Arrays.fill(i, 0);
		}
	}
	
	
	static int[][] preferencias;
	static FordFulkerson ff;
	
	
	static int maximo(int[] escogidos, int[] proyectos, int cual, int limite, boolean validar)
	{
		int source = 0;
		int sink = 1;
		int primeraPersona = 2;
		int primerProyecto = 2 + escogidos.length;
		ff.clear();
		for(int i = 0; i < escogidos.length; i++)
			if(escogidos[i] == -1)
				ff.capacity[source][primeraPersona + i] = 1;
		for(int i = 0; i < proyectos.length; i++)
			if(proyectos[i] > 0)
				ff.capacity[primerProyecto + i][sink] = proyectos[i];
		for(int i = 0; i < escogidos.length; i++)
			if(escogidos[i] == -1)
			{
				int indice = 0;
				for(int j : preferencias[i])
				{
					if(i == cual && indice > limite)
						break;
					if(proyectos[j] > 0)
						ff.capacity[primeraPersona + i][primerProyecto + j] = 1;
					indice++;
				}
			}
		int ans = ff.maxFlow();
		if(validar && ff.capacity[primeraPersona + cual][source] == 0)
			return -1;
		else
			return ans;
	}
	
	static int busquedaBinaria(int a, int b, int[] escogidos, int[] proyectos, int cual, int buscado)
	{
		if(a == b)
			return maximo(escogidos, proyectos, cual, a, true) == buscado ? a : -1;
		int mid = (a + b) >>> 1;
		if(maximo(escogidos, proyectos, cual, mid, true) == buscado)
			return busquedaBinaria(a, mid, escogidos, proyectos, cual, buscado);
		else
			return busquedaBinaria(mid + 1, b, escogidos, proyectos, cual, buscado);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int casos = sc.nextInt();
		for(int caso = 1; caso <= casos; caso++)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			ff = new FordFulkerson();
			ff.capacity = new int[n + m + 2][n + m + 2];
			ff.visited = new boolean[n + m + 2];
			ff.source = 0;
			ff.sink = 1;
			ff.primeraPersona = 2;
			ff.primerProyecto = 2 + n;
			ff.ultimaPersona = ff.primerProyecto - 1;
			ff.ultimoProyecto = n + m + 1;
			int[] proyectos = sc.nextIntArray(m);
			preferencias = new int[n][];
			for(int i = 0; i < n; i++)
			{
				int k = sc.nextInt();
				preferencias[i] = new int[k];
				for(int j = 0; j < k; j++)
					preferencias[i][j] = sc.nextInt() - 1;
			}
			int[] escogidos = new int[n];
			for(int i = 0; i < escogidos.length; i++)
				escogidos[i] = -1;
			int maxFlow = maximo(escogidos, proyectos, 0, preferencias[0].length, false);
			int buscado = maxFlow;
			for(int i = 0; i < n; i++, ff.primeraPersona++)
			{
				if(buscado == 0)
					break;
				if(preferencias[i].length == 0)
					continue;
				int cual = busquedaBinaria(0, preferencias[i].length - 1, escogidos, proyectos, i, buscado);
				if(cual != -1)
				{
					escogidos[i] = preferencias[i][cual];
					proyectos[preferencias[i][cual]]--;
					buscado--;
				}
			}
			System.out.println("Case #" + caso + ":");
			System.out.println(maxFlow + " applicant(s) can be hired.");
			for(int i = 0; i < escogidos.length; i++)
				if(escogidos[i] != -1)
					System.out.println((i + 1) + " " + (escogidos[i] + 1));
		}
	}
}