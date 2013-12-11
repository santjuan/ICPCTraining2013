import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeD 
{
	static class Scanner
	{
		StringTokenizer st = new StringTokenizer("");
		BufferedReader br;
		
		Scanner(InputStream in)
		{
			br = new BufferedReader(new InputStreamReader(in));
		}
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
	
	static class Fractal
	{
		boolean[][] valores;
		Fractal[][] hijos;
		
		Fractal(int k, boolean unos)
		{
			if(k == 1)
				valores = unos ? llenoInicial : valoresIniciales;
			else
			{
				hijos = new Fractal[valoresIniciales.length][valoresIniciales.length];
				for(int i = 0; i < valoresIniciales.length; i++)
					for(int j = 0; j < valoresIniciales.length; j++)
						hijos[i][j] = new Fractal(k - 1, unos ? true : valoresIniciales[i][j]);
			}
		}
		
		void imprimirFila(int fila, StringBuilder sb, int nK)
		{
			if(valores == null)
			{
				for(int i = 0; i < valoresIniciales.length; i++)
					hijos[fila / nK][i].imprimirFila(fila % nK, sb, nK / n);
			}
			else
			{
				for(int i = 0; i < valoresIniciales.length; i++)
					if(valores[fila][i])
						sb.append('*');
					else
						sb.append('.');
			}
		}
	}
	
	static boolean[][] valoresIniciales;
	static boolean[][] llenoInicial;
	static char[][] tableroInicial;
	static int n;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		InputStream in = new FileInputStream("input.txt");
		PrintStream out = new PrintStream("output.txt");
		Scanner sc = new Scanner(in);
		n = sc.nextInt();
		int k = sc.nextInt();
		valoresIniciales = new boolean[n][n];
		llenoInicial = new boolean[n][n];
		for(int i = 0; i < n; i++)
		{
			char[] v = sc.next().toCharArray();
			for(int j = 0; j < n; j++)
			{
				valoresIniciales[i][j] = v[j] == '*';
				llenoInicial[i][j] = true;
			}
		}
		Fractal f = new Fractal(k, false);
		int nK = (int) Math.pow(n, k);
		for(int i = 0; i < nK; i++)
		{
			StringBuilder sb = new StringBuilder();
			f.imprimirFila(i, sb, nK / n);
			out.println(sb.toString());
		}
		out.flush();
		out.close();
	}

}
