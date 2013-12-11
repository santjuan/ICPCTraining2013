import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	
	static class Complex
	{
		double i;
		double j;
		
		Complex(double ii, double jj)
		{
			i = ii;
			j = jj;
		}
		
		Complex suma(Complex otro)
		{
			return new Complex(i + otro.i, j + otro.j);
		}
		
		Complex resta(Complex otro)
		{
			return new Complex(i - otro.i, j - otro.j);
		}
		
		Complex multiplicacion(Complex otro)
		{
			return new Complex(i * otro.i - j * otro.j, i * otro.j + j * otro.i);
		}
	}
	
	static Complex exponenciar(double exp)
	{
		return new Complex(Math.cos(exp), Math.sin
				(exp));
	}
	
	static final double two_pi = Math.PI * 2;

	static void FFT(Complex[] in, Complex[] out, int inOffset, int outOffset, int step, int size, int dir)
	{
	  if(size < 1) return;
	  if(size == 1)
	  {
	    out[outOffset] = in[inOffset];
	    return;
	  }
	  FFT(in, out, inOffset, outOffset, step * 2, size / 2, dir);
	  FFT(in, out, inOffset + step, outOffset + size / 2, step * 2, size / 2, dir);
	  Complex w = exponenciar(dir * two_pi / size);
	  Complex e1 = new Complex(1, 0);
	  Complex e2 = exponenciar((dir * two_pi * (size / 2)) / size);
	  for(int i = 0 ; i < size / 2 ; i++)
	  {
	    Complex even = out[i + outOffset];
	    Complex odd = out[i + size / 2 + outOffset];
	    out[i + outOffset] = even.suma(e1.multiplicacion(odd));
	    out[i + size / 2 + outOffset] = even.suma(e2.multiplicacion(odd));
	    e1 = e1.multiplicacion(w);
	    e2 = e2.multiplicacion(w);
	  }
	}
	
	static int convolucion(boolean[] filas, boolean[] columnas, int[] diagonales)
	{
		int m = filas.length + columnas.length;
		int tam = 1;
		while(tam < m)
			tam <<= 1;
		Complex[] a = new Complex[tam];
		Complex[] b = new Complex[tam];
		Complex[] c = new Complex[tam];
		Complex[] A = new Complex[tam];
		Complex[] B = new Complex[tam];
		Complex[] C = new Complex[tam];
		for(int i = 0; i < filas.length; i++)
			a[i] = new Complex(filas[i] ? 1 : 0, 0);
		for(int i = filas.length; i < tam; i++)
			a[i] = new Complex(0, 0);
		for(int i = 0; i < columnas.length; i++)
			b[i] = new Complex(columnas[columnas.length - i - 1] ? 1 : 0, 0);
		for(int i = columnas.length; i < tam; i++)
			b[i] = new Complex(0, 0);
		FFT(a, A, 0, 0, 1, tam, 1);
		FFT(b, B, 0, 0, 1, tam, 1);
		for(int i = 0; i < tam; i++)
			C[i] = A[i].multiplicacion(B[i]);
		FFT(C, c, 0, 0, 1, tam, -1);
		int respuesta = 0;
		int limite = Math.min(diagonales.length, tam);
		for(int i = 0; i < limite; i++)
			if(diagonales[i] == 1)
				respuesta += Math.round(c[i].i / tam);
		return respuesta;
	}
	
	
	static void generarIntegralImage(int[] vals)
	{
		for(int i = 1; i < vals.length; i++)
			vals[i] += vals[i - 1];
	}
	
	static int darSuma(int[] vals, int a, int b)
	{
		if(a == 0)
			return vals[b];
		return vals[b] - vals[a - 1];
	}
	
	static int contar(int rows, int cols, boolean[] r, boolean[] c, int[] diagonales, boolean x, boolean y, boolean z)
	{
		int cuenta = 0;
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++)
			{
				if(!x || (x && r[i]))
					if(!y || (y && c[j]))
						if(!z || (z && diagonales[i + (cols - j - 1)] == 1))
							cuenta++;
			}
		return cuenta;
	}
	
	static int contarReal(int rows, int cols, boolean[] r, boolean[] c, int[] diagonales)
	{
		int cuenta = 0;
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++)
			{
				if(!(r[i] || c[j] || (diagonales[i + (cols - j - 1)] == 1)))
					cuenta++;
			}
		return cuenta;
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		System.setIn(new FileInputStream("prueba.txt"));
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int caso = 1; caso <= t; caso++)
		{
			if(caso == 9)
				System.out.print("");
			int rows = sc.nextInt();
			int cols = sc.nextInt();					
			int total = (rows * cols);
			boolean[] filas = new boolean[rows];
			boolean[] columnas = new boolean[cols];
			int[] diagonales = new int[rows + cols];
			int n = sc.nextInt();
			for(int i = 0; i < n; i++)
			{
				int r = sc.nextInt() - 1;
				int c = sc.nextInt() - 1;
				filas[r] = true;
				columnas[c] = true;
				diagonales[r + (cols - c - 1)] = 1;
			}
			int colsFree = 0;
			for(boolean b : columnas)
				if(!b)
					colsFree++;
			int rowsFree = 0;
			for(boolean b : filas)
				if(!b)
					rowsFree++;
			long colsA = (rows - rowsFree) * cols;
			if(caso >= 0)
				colsA -= contar(rows, cols, filas, columnas, diagonales, true, false, false);
			if(colsA != 0)
				System.out.println("paila");
			long rowsA = (cols - colsFree) * rows;
			if(caso >= 0)
				rowsA -= contar(rows, cols, filas, columnas, diagonales, false, true, false);
			if(rowsA != 0)
				System.out.println("paila");
			long acum = (cols - colsFree) * rows + (rows - rowsFree) * cols;
			long diagA = 0;
			for(int i = 0; i < cols; i++)
			{
				if(diagonales[i] == 1)
				{
					acum += Math.min(i + 1, rows);
					diagA += Math.min(i + 1, rows);
				}
			}
			for(int i = cols; i < diagonales.length; i++)
			{
				if(diagonales[i] == 1)
				{
					acum += Math.min(diagonales.length - (i + 1), cols);
					diagA += Math.min(diagonales.length - (i + 1), cols);
				}
			}
			if(caso >= 0)
				diagA -= contar(rows, cols, filas, columnas, diagonales, false, false, true);			
			if(diagA != 0)
				System.out.println("paila");
			int[] diagonalesNormales = diagonales.clone();
			generarIntegralImage(diagonales);
			long rowColsA = 0;
			for(int i = 0; i < rows; i++)
				if(filas[i])
				{
					acum -= (cols - colsFree);
					rowColsA += (cols - colsFree);
				}
			if(caso >= 0)
				rowColsA -= contar(rows, cols, filas, columnas, diagonalesNormales, true, true, false);
			if(rowColsA != 0)
				System.out.println("paila");
			for(int i = 0; i < rows; i++)
				if(filas[i])
				{
					acum -= darSuma(diagonales, i, i + cols - 1);
					rowColsA += darSuma(diagonales, i, i + cols - 1);
				}
			if(caso >= 0)
				rowColsA -= contar(rows, cols, filas, columnas, diagonalesNormales, true, false, true);
			if(rowColsA != 0)
				System.out.println("paila");
			for(int i = 0; i < cols; i++)
				if(columnas[i])
				{
					int iReal = cols - i - 1;
					acum -= darSuma(diagonales, iReal, iReal + rows - 1);
					rowColsA += darSuma(diagonales, iReal, iReal + rows - 1);
				}
			if(caso >= 0)
				rowColsA -= contar(rows, cols, filas, columnas, diagonalesNormales, false, true, true);
			if(rowColsA != 0)
				System.out.println("paila");
			acum += convolucion(filas, columnas, diagonalesNormales);
			rowColsA += convolucion(filas, columnas, diagonalesNormales);
			if(caso >= 0)
				rowColsA -= contar(rows, cols, filas, columnas, diagonalesNormales, true, true, true);
			if(rowColsA != 0)
				System.out.println("paila");
			System.out.println("Case " + caso + ": " + ((rows * cols) - acum) + " " + contarReal(rows, cols, filas, columnas, diagonalesNormales));
		}
	}

}
