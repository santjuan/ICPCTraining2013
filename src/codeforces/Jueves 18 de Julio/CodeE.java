import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeE 
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
				res[i] = nextDouble();
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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		int x = sc.nextInt();
		x *= x;
		char[][] teclado = new char[n][];
		for(int i = 0; i < n; i++) teclado[i] = sc.next().toCharArray();
		boolean[] posible = new boolean[26];
		boolean[] existe = new boolean[26];
		boolean hayShift = false;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
			{
				if(teclado[i][j] == 'S')
				{
					hayShift = true;
					continue;
				}
				int letra = teclado[i][j] - 'a';
				existe[letra] = true;
 				if(posible[letra]) continue;
 				outer:
				for(int k = 0; k < n; k++)
					for(int l = 0; l < m; l++)
					{
						if(teclado[k][l] != 'S') continue;
						int dist = (i - k) * (i - k) + (j - l) * (j - l);
						if(dist > x) continue;
						posible[letra] = true;
						break outer;
					}
			}
		int respuesta = 0;
		sc.next();
		for(char c : sc.next().toCharArray())
		{
			boolean mayuscula = c >= 'A' && c <= 'Z';
			int letra = mayuscula ? c - 'A' : c - 'a';
			if(!existe[letra] || (mayuscula && !hayShift))
			{
				respuesta = -1;
				break;
			}
			if(mayuscula && !posible[letra])
				respuesta++;
		}
		System.out.println(respuesta);
	}

}
