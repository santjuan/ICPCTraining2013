import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class CodeI 
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
	
	private static char[][] mapa;
	
	private static boolean ok(int i, int j)
	{
		return i >= 0 && i < mapa.length && j >= 0 && j < mapa[0].length && mapa[i][j] != '#';
	}
	
	private static boolean check(int iStart, int jStart, int[][] importants)
	{
		if(importants == null) return false;
		for(int[] v : importants)
			if(!ok(iStart + v[0], jStart + v[1])) return false;
		return true;
	}
	
	static boolean ok2(int iStart, int jStart) 
	{
		return iStart <= 1000 && iStart >= -1000 && jStart <= 1000 && jStart >= -1000;
	}
	
	static class Entrada
	{
		int i;
		int j;
		
		Entrada(int ii, int jj)
		{
			i = ii;
			j = jj;
		}

		@Override
		public int hashCode() 
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
			result = prime * result + j;
			return result;
		}

		@Override
		public boolean equals(Object obj) 
		{
			Entrada other = (Entrada) obj;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			return true;
		}
	}
	
	static int[][] getImportant(char[] instructions, int[] lens)
	{
		int iStart = 0;
		int jStart = 0;
		boolean[][] importantes = new boolean[2102][2102];
		for(int i = 0; i < instructions.length; i++)
		{
			int deltaI = 0; 
			int deltaJ = 0;
			if(instructions[i] == 'N')
				deltaI--;
			else if(instructions[i] == 'S')
				deltaI++;
			else if(instructions[i] == 'W')
				deltaJ--;
			else
				deltaJ++;
			int len = lens[i];
			while(ok2(iStart, jStart) && len != 0)
			{
				importantes[iStart + 1010][jStart + 1010] = true;
				iStart += deltaI;
				jStart += deltaJ;
				len--;
			}
			if(!ok2(iStart, jStart)) return null;
			importantes[iStart + 1010][jStart + 1010] = true;
		}
		int cuenta = 0;
		for(int i = 0; i < importantes.length; i++)
			for(int j = 0; j < importantes[0].length; j++)
				if(importantes[i][j])
					cuenta++;
		int[][] importants = new int[cuenta][2];
		int indice = 0;
		for(int i = 0; i < importantes.length; i++)
			for(int j = 0; j < importantes[0].length; j++)
				if(importantes[i][j])
				{
					 importants[indice][0] = i - 1010;
					 importants[indice++][1] = j - 1010;
				}
		return importants;
	}

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		mapa = new char[n][];
		for(int i = 0; i < n; i++) mapa[i] = sc.next().toCharArray();
		int inst = sc.nextInt();
		char[] instructions = new char[inst];
		int[] lens = new int[inst];
		for(int i = 0; i < inst; i++)
		{
			instructions[i] = sc.next().toCharArray()[0];
			lens[i] = sc.nextInt();
		}
		int[][] importants = getImportant(instructions, lens);
		ArrayList <Character> oks = new ArrayList <Character> ();
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++)
				if(mapa[i][j] >= 'A' && mapa[i][j] <= 'Z')
					if(check(i, j, importants))
						oks.add(mapa[i][j]);
		Collections.sort(oks);
		if(oks.isEmpty()) System.out.println("no solution");
		else
		{
			for(char c : oks) System.out.print(c);
			System.out.println();
		}
	}
}