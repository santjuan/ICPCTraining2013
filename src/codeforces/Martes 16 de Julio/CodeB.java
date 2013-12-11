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
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		char[][] entrada = new char[8][];
		for(int i = 0; i < 8; i++) entrada[i] = sc.next().toCharArray();
		boolean[] rowPainted = new boolean[8];
		Arrays.fill(rowPainted, true);
		boolean[] columnPainted = new boolean[8];
		Arrays.fill(columnPainted, true);
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
			{
				rowPainted[i] &= entrada[i][j] == 'B';
				columnPainted[j] &= entrada[i][j] == 'B';
			}
		int limite = 1 << 8;
		int min = 16;
		outer:
		for(int i = 0; i < limite; i++)
		{
			boolean[][] paint = new boolean[8][8];
			for(int j = 0; j < 8; j++)
				if((i & (1 << j)) != 0)
				{
					if(!rowPainted[j])
						continue outer;
					for(int k = 0; k < 8; k++)
						paint[j][k] = true;
				}
			int cuentaExtra = 0;
			outerRow:
			for(int j = 0; j < 8; j++)
				if(columnPainted[j])
					for(int k = 0; k < 8; k++)
					{
						if((i & (1 << k)) != 0) continue;
						cuentaExtra++;
						for(int l = 0; l < 8; l++)
							paint[l][j] = true;
						continue outerRow;
					}
			for(int j = 0; j < 8; j++)
				for(int k = 0; k < 8; k++)
					if(paint[j][k] != (entrada[j][k] == 'B')) continue outer;
			min = Math.min(min, cuentaExtra + Integer.bitCount(i));
		}
		System.out.println(min);
	}

}
