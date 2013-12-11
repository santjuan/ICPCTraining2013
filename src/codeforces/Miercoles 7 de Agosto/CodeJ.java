import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeJ 
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
		
		static <T> T fill(T arreglo, int val)
		{
			if(arreglo instanceof Object[])
			{
				Object[] a = (Object[]) arreglo;
				for(Object x : a)
					fill(x, val);
			}
			else if(arreglo instanceof int[])
				Arrays.fill((int[]) arreglo, val);
			else if(arreglo instanceof double[])
				Arrays.fill((double[]) arreglo, val);
			else if(arreglo instanceof long[])
				Arrays.fill((long[]) arreglo, val);
			return arreglo;
		}
	}

	static class Punto
	{
		int x;
		int y;
		
		Punto(int xx, int yy)
		{
			x = xx;
			y = yy;
		}
		
		int distSq(Punto otro)
		{
			return (x - otro.x) * (x - otro.x) + (y - otro.y) * (y - otro.y);
		}
	}
	
	static boolean intersect(Punto a, Punto b, Punto ref)
	{
		if(a.x != ref.x && a.y != ref.y)
			return false;
		if(b.x != ref.x && a.y != ref.y)
			return false;
		return ref.distSq(a) == ref.distSq(b);
	}
	
	static boolean[] filasBloqueadas = new boolean[1000];
	static boolean[] columnasBloqueadas = new boolean[1000];
	static int n;
	
	static boolean intentar(int filaA, int filaB, int columnaA, int columnaB, int orden)
	{
		int ordenFilaA = orden & 3;
		int ordenFilaB = (orden >> 2) & 3;
		int ordenColumnaA = (orden >> 4) & 3;
		int ordenColumnaB = (orden >> 6) & 3;
		if(filasBloqueadas[filaA] && ordenFilaA != 0) return false;
		if(filasBloqueadas[filaB] && ordenFilaB != 0) return false;
		if(columnasBloqueadas[columnaA] && ordenColumnaA != 0) return false;
		if(columnasBloqueadas[columnaB] && ordenColumnaB != 0) return false;
		ArrayList <Punto> todosPuntos = new ArrayList <Punto> ();
		if(ordenFilaA == 1)
			todosPuntos.add(new Punto(filaA, 0));
		else if(ordenFilaA == 2)
			todosPuntos.add(new Punto(filaA, n - 1));
		if(ordenFilaB == 1)
			todosPuntos.add(new Punto(filaB, 0));
		else if(ordenFilaB == 2)
			todosPuntos.add(new Punto(filaB, n - 1));
		if(ordenColumnaA == 1)
			todosPuntos.add(new Punto(0, columnaA));
		else if(ordenColumnaA == 2)
			todosPuntos.add(new Punto(n - 1, columnaA));
		if(ordenColumnaB == 1)
			todosPuntos.add(new Punto(0, columnaB));
		else if(ordenColumnaB == 2)
			todosPuntos.add(new Punto(n - 1, columnaB));
		ArrayList <Punto> todosPuntosClave = new ArrayList <Punto> ();
		todosPuntosClave.add(new Punto(filaA, columnaA));
		todosPuntosClave.add(new Punto(filaA, columnaB));
		todosPuntosClave.add(new Punto(filaB, columnaA));
		todosPuntosClave.add(new Punto(filaB, columnaB));
		for(Punto p : todosPuntos)
			for(Punto p1 : todosPuntos)
			{
				if(p == p1) continue;
				for(Punto ref : todosPuntosClave)
					if(intersect(p, p1, ref)) return false;
			}
		return true;
	}
	
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		int m = sc.nextInt();
		for(int i = 0; i < m; i++)
		{
			filasBloqueadas[sc.nextInt() - 1] = true;
			columnasBloqueadas[sc.nextInt() - 1] = true;
		}
		int total = 0;
		for(int i = 1; i < n - 1; i++)
		{
			int filaA = i;
			int filaB = n - i - 1;
			int columnaA = i;
			int columnaB = n - i - 1;
			if(filaA > filaB) break;
			int mejor = 0;
			for(int ordenA = 0; ordenA < 3; ordenA++)
				for(int ordenB = 0; ordenB < 3; ordenB++)
					for(int ordenC = 0; ordenC < 3; ordenC++)
						for(int ordenD = 0; ordenD < 3; ordenD++)
						{
							int orden = ordenA | (ordenB << 2) | (ordenC << 4) | (ordenD << 6);
							int cuenta = 0;
							if(ordenA != 0) cuenta++;
							if(ordenB != 0) cuenta++;
							if(ordenC != 0) cuenta++;
							if(ordenD != 0) cuenta++;
							if(intentar(filaA, filaB, columnaA, columnaB, orden))
								mejor = Math.max(mejor, cuenta);
						}
			total += mejor;
		}
		System.out.println(total);
	}
}