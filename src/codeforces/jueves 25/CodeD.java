import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
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
	
	static int[][] diffs = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
	
	static int n;
	static int m;
	
	static ArrayList <Integer> generarAristasCero(int i, int j, int dir)
	{
		ArrayList <Integer> respuesta = new ArrayList <Integer> ();
		int iSig = i + diffs[dir][0];
		int jSig = j + diffs[dir][1];
		if(iSig >= 0 && iSig < n && jSig >= 0 && jSig < m)
			respuesta.add(transformar(iSig, jSig, dir));
		return respuesta;
	}
	
	static BitSet especiales;
	
	static ArrayList <Integer> generarAristasUno(int i, int j, int dir)
	{
		ArrayList <Integer> respuesta = new ArrayList <Integer> ();
		if(especiales.get(transformar(i, j, dir)))
			for(int k = 0; k < 4; k++)
				if(k != dir)
					respuesta.add(transformar(i, j, k));
		return respuesta;
	}
	
	static int transformar(int i, int j, int dir)
	{
		return i | (j << 10) | (dir << 20); 
	}
	
	static int[] inversa(int val)
	{
		int i = val & ((1 << 10) - 1);
		val >>= 10;
		int j = val & ((1 << 10) - 1);
		val >>= 10;
		int dir = val;
		return new int[]{i, j, dir};
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		m = sc.nextInt();
		char[][] tableroR = new char[n][];
		for(int i = 0; i < n; i++)
			tableroR[i] = sc.next().toCharArray();
		especiales = new BitSet();
		int cuantos = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
				for(int dir = 0; dir < 4; dir++)
					if(tableroR[i][j] == '#')
					{
						especiales.set(transformar(i, j, dir));
						cuantos++;
					}
		boolean esDos = false;
		for(int j = 0; j < m; j++)
			if(tableroR[0][j] == '#' && tableroR[n - 1][j] == '#')
				esDos = true;
		if(esDos)
		{
			System.out.println("2");
			return;
		}
		int[] deque = new int[1024 * 1024 * 4];
		BitSet visitados = new BitSet();
		visitados.set(transformar(0, 0, 3));
		int[] mejor = new int[1024 * 1024 * 4];
		Arrays.fill(mejor, -1);
		mejor[transformar(0, 0, 3)] = 0;
		int cabeza = 0;
		int cola = 0;
		deque[cola++] = transformar(0, 0, 3);
		while(cabeza < cola)
		{
			int actual = deque[cabeza++];
			int[] este = inversa(actual);
			for(int v : generarAristasCero(este[0], este[1], este[2]))
			{
				if(!visitados.get(v))
				{
					visitados.set(v);
					mejor[v] = mejor[actual];
					deque[--cabeza] = v;
				}
			}
			for(int v : generarAristasUno(este[0], este[1], este[2]))
			{
				if(!visitados.get(v))
				{
					visitados.set(v);
					mejor[v] = mejor[actual] + 1;
					deque[cola++] = v;
				}
			}
		}
		System.out.println(mejor[transformar(n - 1, m - 1, 3)]);
	}
}