import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeD 
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
	
	static class Estado
	{
		int i;
		int j;
		int direccion;
		boolean cambio; 
		
		public Estado(int i, int j, int direccion, boolean cambio)
		{
			this.i = i;
			this.j = j;
			this.cambio = cambio;
			this.direccion = direccion;
		}		
	}
	
	static int[][] direcciones = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int n, m;
	static boolean[][] tablero;
	static final boolean[][][][] visitados = new boolean[50][50][4][2];
	static final boolean[][] visitadosN = new boolean[50][50];
	
	static boolean visitar(int i, int j)
	{
		for(int k = 0; k < n; k++)
			for(int l = 0; l < m; l++)
			{
				visitadosN[k][l] = false;
				for(int m = 0; m < 4; m++)
					for(int n = 0; n < 2; n++)
						visitados[k][l][m][n] = false;
			}
		ArrayDeque <Estado> cola = new ArrayDeque <Estado> ();
		for(int k = 0; k < direcciones.length; k++)
		{
			cola.add(new Estado(i, j, k, false));
			visitados[i][j][k][0] = true;
		}
		while(!cola.isEmpty())
		{
			Estado actual = cola.poll();
			visitadosN[actual.i][actual.j] = true;
			int iSig = actual.i + direcciones[actual.direccion][0];
			int jSig = actual.j + direcciones[actual.direccion][1];
			if(valido(iSig, jSig))
			{
				if(visitados[iSig][jSig][actual.direccion][actual.cambio ? 1 : 0])
					continue;
				cola.add(new Estado(iSig, jSig, actual.direccion, actual.cambio));
			}
			if(!actual.cambio)
			{
				for(int k = 0; k < direcciones.length; k++)
				{
					if(visitados[actual.i][actual.j][k][1])
						continue;
					cola.add(new Estado(actual.i, actual.j, k, true));
				}
			}
		}
		for(int k = 0; k < n; k++)
			for(int l = 0; l < m; l++)
				if(tablero[k][l] && !visitadosN[k][l])
					return false;
		return true;
	}
	
	private static boolean valido(int i, int j) 
	{
		return i >= 0 && i < n && j >= 0 && j < m && tablero[i][j];
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		m = sc.nextInt();
		tablero = new boolean[n][m];
		for(int i = 0; i < n; i++)
		{
			int j = 0;
			for(char c : sc.next().toCharArray())
				tablero[i][j++] = c == 'B';
		}
		boolean bien = true;
		outer:
		for(int i = 0; i < Math.max(n, m); i++)
		{
			if(i < n)
			{
				for(int j = i; j < m; j++)
					if(tablero[i][j] && !visitar(i, j))
					{
						bien = false;
						break outer;
					}
				for(int j = 0; j < m; j++)
					tablero[i][j] = false;
			}
			if(i < m)
			{
				for(int j = i; j < n; j++)
					if(tablero[j][i] && !visitar(j, i))
					{
						bien = false;
						break outer;
					}
				for(int j = 0; j < n; j++)
					tablero[j][i] = false;
			}
		}
		System.out.println(bien ? "YES" : "NO");
	}

}
