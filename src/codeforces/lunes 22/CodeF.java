import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeF 
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
	
	static long gcd(long a, long b)
	{
		if(b == 0)
			return a;
		return gcd(b, a % b);
	}

	static final int limite = 1000000000;
	
	static ArrayList <Long> luckys = new ArrayList <Long> ();
	
	static void generarLuckys(String acum)
	{
		if(acum.length() == 0 || Long.parseLong(acum) <= limite)
		{
			if(acum.length() != 0)
				luckys.add(Long.parseLong(acum));
			generarLuckys(acum + '4');
			generarLuckys(acum + '7');
		}
	}

	static class Materia implements Comparable <Materia>
	{
		long a;
		long b;
		int c;
		int posReal;
		
		public Materia(long a, long b, int c, int posReal) 
		{
			this.a = a;
			this.b = b;
			this.c = c;
			this.posReal = posReal;
		}

		@Override
		public int compareTo(Materia o)
		{
			return c -o.c;
		}
	}

	static Materia[] materias;
	
	static class Respuesta
	{
		int escogido;
		long complexity;
		long given;
		Respuesta siguiente;
		
		public Respuesta(int escogido, long complexity, long given, Respuesta siguiente) 
		{
			this.escogido = escogido;
			this.complexity = complexity;
			this.given = given;
			this.siguiente = siguiente;
		}
	}
	
	static int n;
	static int m;
	static int k;
	static Respuesta imposible = new Respuesta(-1, Long.MIN_VALUE, -1, null);
	static Respuesta[][][][] dp = new Respuesta[52][52][52][102];
	
	static Respuesta dp(int nActual, int mActual, int anterior, int deltaAnterior)
	{
		if(dp[nActual][mActual][anterior + 1][deltaAnterior] != null)
			return dp[nActual][mActual][anterior + 1][deltaAnterior];
		if(nActual == n)
			return dp[nActual][mActual][anterior + 1][deltaAnterior] = new Respuesta(0, 0, -1, null);
		if(mActual == m)
			return dp[nActual][mActual][anterior + 1][deltaAnterior] = imposible;
		long realAnterior = anterior == -1 ? -1L :  materias[anterior].a + deltaAnterior;
		int complexityAnterior = anterior == -1 ? -1 : materias[anterior].c;
		long rangoA = materias[mActual].a;
		long rangoB = materias[mActual].b;
		Respuesta posible = imposible;
		if(materias[mActual].c > complexityAnterior && (realAnterior == -1 || (realAnterior * k >= rangoA && realAnterior * k <= rangoB)))
		{
			long gA = realAnterior == -1 ? materias[mActual].a : realAnterior * k;
			long gB = realAnterior == -1 ? materias[mActual].b : realAnterior * k;
			for(long given = gA; given <= gB; given++)
			{
				Respuesta siguiente = dp(nActual + 1, mActual + 1, mActual, (int) (given - rangoA));
				long posibleComplexity = siguiente.complexity + given;
				if(siguiente != imposible && posibleComplexity > posible.complexity)
					posible = new Respuesta(mActual, posibleComplexity, given, siguiente);
			}
		}
		if(materias[mActual].c > complexityAnterior && (realAnterior == -1 || (realAnterior + k >= rangoA && realAnterior + k <= rangoB)))
		{
			long gA = realAnterior == -1 ? materias[mActual].a : realAnterior + k;
			long gB = realAnterior == -1 ? materias[mActual].b : realAnterior + k;
			for(long given = gA; given <= gB; given++)
			{
				Respuesta siguiente = dp(nActual + 1, mActual + 1, mActual, (int) (given - rangoA));
				long posibleComplexity = siguiente.complexity + given;
				if(siguiente != imposible && posibleComplexity > posible.complexity)
					posible = new Respuesta(mActual, posibleComplexity, given, siguiente);
			}
		}
		Respuesta pasar = dp(nActual, mActual + 1, anterior, deltaAnterior);
		if(pasar != imposible && pasar.complexity > posible.complexity)
			posible = pasar;
		return dp[nActual][mActual][anterior + 1][deltaAnterior] = posible;
	}
	
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		materias = new Materia[m];
		for(int i = 0; i < m; i++)
			materias[i] = new Materia(sc.nextLong(), sc.nextLong(), sc.nextInt(), i);
		Arrays.sort(materias);
		Respuesta r = dp(0, 0, -1, 0);
		if(r == imposible)
			System.out.println("NO");
		else
		{
			System.out.println("YES");
			while(r.siguiente != null)
			{
				System.out.println((materias[r.escogido].posReal + 1) + " " + r.given);
				r = r.siguiente;
			}
		}
	}
}