import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
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
	}
	
	
	static char noExiste = '.';
	
	static class Cubo
	{
		char color;
		
		
		boolean pintar(char c)
		{
			if(color == noExiste)
				return color == noExiste;
			else if(color == c)
				return color == noExiste;
			else if(color == 0)
			{
				 color = c;
				 return color == noExiste;
			}
			else if(color != c)
			{
				color = noExiste;
				return color == noExiste;
			}
			return color == noExiste;
		}
	}
	
	static Cubo[][][] todos;
	static int n;
	
	static boolean esVisible(int x, int y, int z, int[] delta)
	{
		if(x < 0 || x >= n || y < 0 || y >= n || z < 0 || z >= n)
			return true;
		if(todos[x][y][z].color != noExiste)
			return false;
		return esVisible(x + delta[0], y + delta[1], z + delta[2], delta);
	}
	
	static int[][] ejeCaras = new int[][]{{1, 2}, {1, -3}, {1, -2}, {1, 3}, {-3, 2}, {3, 2}};
	static int[][] deltas = new int[][]{{0, 0, -1}, {0, -1, 0}, {0, 0, 1}, {0, 1, 0}, {-1, 0, 0}, {1, 0, 0}};
	static char[][][] caras;
	
	static char darColor(int cara, int x, int y, int z)
	{
		int[] ejes = ejeCaras[cara];
		int xP = Math.abs(ejes[0]) - 1;
		int yP = Math.abs(ejes[1]) - 1;
		int[] x3 = new int[]{x, y, z};
		int xV = x3[xP];
		int yV = x3[yP];
		if(ejes[0] < 0)
			xV = (n - 1) - xV;
		if(ejes[1] < 0)
			yV = (n - 1) - yV;
		return caras[cara][xV][yV];
	}
	
	static int simular()
	{
		while(true)
		{
			boolean cambio = false;
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
					for(int k = 0; k < n; k++)
					{
						if(todos[i][j][k].color == noExiste)
							continue;
						for(int cara = 0; cara < 6; cara++)
						{
							if(esVisible(i + deltas[cara][0], j + deltas[cara][1], k + deltas[cara][2], deltas[cara]))
								if(todos[i][j][k].pintar(darColor(cara, i, j, k)))
									break;
						}
						if(todos[i][j][k].color == noExiste)
							cambio = true;
					}
			if(!cambio)
				break;
		}
		int cuenta = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++)
				for(int k = 0; k < n; k++)
					if(todos[i][j][k].color != noExiste)
						cuenta++;
		return cuenta;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			n = sc.nextInt();
			if(n == 0)
				return;
			StringTokenizer[] stS = new StringTokenizer[n];
			for(int i = 0; i < n; i++)
				stS[i] = new StringTokenizer(sc.nextLine());
			caras = new char[6][n][];
			for(int i = 0; i < 6; i++)
				for(int j = 0; j < n; j++)
					caras[i][j] = stS[j].nextToken().toCharArray();
			todos = new Cubo[n][n][n];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < n; j++)
					for(int k = 0; k < n; k++)
						todos[i][j][k] = new Cubo();
			System.out.println("Maximum weight: " + simular() + " gram(s)");
		}
	}
}