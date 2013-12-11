import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class LiveA 
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
	
	static class Solucion
	{
		int aliens;
		int bombas;

		public Solucion(int a, int b)
		{
			aliens = a;
			bombas = b;
		}
	}
	
	static Solucion[] dp;
	
	static Solucion dp(int mascara)
	{
		if(dp[mascara] != null)
			return dp[mascara];
		Solucion mejor = new Solucion(0, 0);
		for(int bomba : bombs)
		{
			if((bomba & mascara) == 0)
				continue;
			int aliensTerminados = Integer.bitCount(bomba & mascara);
			Solucion siguiente = dp(mascara ^ (bomba & mascara));
			int siguienteAliens = aliensTerminados + siguiente.aliens;
			int siguienteBombas = siguiente.bombas + 1;
			if(siguienteAliens > mejor.aliens)
				mejor = new Solucion(siguienteAliens, siguienteBombas);
			else if(siguienteAliens == mejor.aliens && siguienteBombas < mejor.bombas)
				mejor = new Solucion(siguienteAliens, siguienteBombas);
		}
		return dp[mascara] = mejor;
	}

	private static boolean valido(int i, int j) 
	{
		return i >= 0 && i < 10 && j >= 0 && j < 10;
	}
	
	static int nAliens; 
	static int[] bombs;
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int caso = 1; caso <= t; caso++)
		{
			int count = 0;
			int[][] alienNumber = new int[10][10];
			char[][] tablero = new char[10][];
			for(int i = 0; i < 10; i++)
			{
				tablero[i] = sc.next().toCharArray();
				for(int j = 0; j < 10; j++)
					if(tablero[i][j] == 'e')
						alienNumber[i][j] = count++;
			}
			nAliens = count;
			int real = 0;
			ArrayList <Integer> bombsA = new ArrayList <Integer> ();
			for(int i = 0; i < 10; i++)
				for(int j = 0; j < 10; j++)
				{
					boolean tiene = false;
					int mascara = 0;
					for(int k = -1; k <= 1; k++)
						for(int l = -1; l <= 1; l++)
						{
							if(valido(i + k, j + l) && tablero[i + k][j + l] == 'g')
								tiene = true;
							if(valido(i + k, j + l) && tablero[i + k][j + l] == 'e')
								mascara |= 1 << alienNumber[i + k][j + l];
						}
					if(!tiene && mascara != 0)
						bombsA.add(mascara);
				}
			bombs = new int[bombsA.size()];
			for(int i = 0; i < bombsA.size(); i++)
				bombs[i] = bombsA.get(i);
			dp = new Solucion[1 << nAliens];
			Solucion mejor = dp((1 << nAliens) - 1);
			System.out.println(mejor.aliens + " " + mejor.bombas);
		}
	}
}
