import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class geoprog 
{
	static class Scanner
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String nextLine()
		{
			try
			{
				return br.readLine();
			}
			catch(Exception e)
			{
				throw(new RuntimeException(e));
			}
		}
		
		String next()
		{
			while(!st.hasMoreTokens())
			{
				String line = nextLine();
				if(line == null)
					return null;
				st = new StringTokenizer(line);
			}
			return st.nextToken();
		}
		
		int nextInt()
		{
			return Integer.parseInt(next());
		}
		
		Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
		}
		
		long nextLong()
		{
			return Long.parseLong(next());
		}
		
		double nextDouble()
		{
			return Double.parseDouble(next());
		}
		
		int[] nextIntArray(int size)
		{
			int[] result = new int[size];
			for(int i = 0; i < size; i++)
				result[i] = nextInt();
			return result;
		}
	}
	
	static class Par implements Comparable<Par>
	{
		int base;
		int exponente;
		
		public Par(int b, int e) 
		{
			base = b;
			exponente = e;
		}

		Par clonar()
		{
			return new Par(base, exponente);
		}

		@Override
		public int compareTo(Par o)
		{
			if(o.base != base)
				return base - o.base;
			return exponente - o.exponente;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + base;
			result = prime * result + exponente;
			return result;
		}

		@Override
		public boolean equals(Object obj) 
		{
			Par other = (Par) obj;
			if (base != other.base)
				return false;
			if (exponente != other.exponente)
				return false;
			return true;
		}
	}
	
	static class Numero implements Comparable <Numero>
	{
		Par[] potencias;
		Integer hash;
		
		public Numero(ArrayList <Par> p) 
		{
			potencias = p.toArray(new Par[0]);
		}

		Numero potencia(int n)
		{
			if(n == 0)
				return new Numero(new ArrayList <Par> ());
			ArrayList <Par> resultado = new ArrayList <Par> ();
			for(Par p : potencias)
			{
				Par s = p.clonar();
				s.exponente *= n;
				resultado.add(s);
			}
			return new Numero(resultado);
		}
		
		Numero multiplicacion(Numero otro)
		{
			ArrayList <Par> resultado = new ArrayList <Par> ();
			int indiceEste = 0;
			int indiceOtro = 0;
			while(indiceEste < potencias.length || indiceOtro < otro.potencias.length)
			{
				if(indiceEste == potencias.length)
					resultado.add(otro.potencias[indiceOtro++].clonar());
				else if(indiceOtro == otro.potencias.length)
					resultado.add(potencias[indiceEste++].clonar());
				else
				{
					if(potencias[indiceEste].base < otro.potencias[indiceOtro].base)
						resultado.add(potencias[indiceEste++].clonar());
					else if(potencias[indiceEste].base > otro.potencias[indiceOtro].base)
						resultado.add(otro.potencias[indiceOtro++].clonar());
					else
					{
						resultado.add(new Par(potencias[indiceEste].base, potencias[indiceEste].exponente + otro.potencias[indiceOtro].exponente));
						indiceEste++;
						indiceOtro++;
					}
				}
			}
			return new Numero(resultado);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(potencias);
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			Numero other = (Numero) obj;
			if (!Arrays.equals(potencias, other.potencias))
				return false;
			return true;
		}
		

		@Override
		public int compareTo(Numero o)
		{
			if(o.potencias.length != potencias.length)
				return potencias.length - o.potencias.length;
			for(int i = 0; i < potencias.length; i++)
			{
				int cmp = potencias[i].compareTo(o.potencias[i]);
				if(cmp != 0)
					return cmp;
			}
			return 0;
		}
		
		int darExponente(int base)
		{
			for(Par p : potencias)
				if(p.base == base)
					return p.exponente;
			return 0;
		}
	}
	
	static Numero factorizar(int n)
	{
		ArrayList <Par> resultado = new ArrayList <Par> ();
		int limite = (int) Math.min(Math.ceil(Math.sqrt(n)), n - 1);
		for(int i = 2; i <= limite; i++)
		{
			if(n % i == 0)
			{
				Par c = new Par(i, 0);
				while(n % i == 0)
				{
					c.exponente++;
					n /= i;
				}
				resultado.add(c);
			}
		}
		if(n != 1)
			resultado.add(new Par(n, 1));
		Collections.sort(resultado);
		return new Numero(resultado);
	}
	
	static int[] vals(Numero x, int[] bases)
	{
		int[] res = new int[bases.length];
		for(int i = 0; i < bases.length; i++)
			res[i] = x.darExponente(bases[i]);
		return res;
	}
	
	static TreeSet <Integer> darTodos(Numero b, Numero q)
	{
		TreeSet <Integer> bases = new TreeSet <Integer> ();
		for(Par p : b.potencias)
			bases.add(p.base);
		for(Par p : q.potencias)
			bases.add(p.base);
		return bases;
	}
	
	static void sumar(int[] a, int[] b)
	{
		for(int i = 0; i < a.length; i++)
			a[i] += b[i];
	}
	
	static int cmp(int[] a, int[] b)
	{
		for(int i = 0; i < a.length; i++)
			if(a[i] != b[i])
				return a[i] - b[i];
		return 0;
	}
	
	static int n1, n2;
	
	static void generar(int[] b, int[] q, int bO, int qO, HashSet <Vector> hash, int n)
	{
		int[] x1 = b.clone();
		for(int exponente = 0; exponente < n; exponente++)
		{
			Vector actual = new Vector(x1);
			if(exponente == 0)
			{
				if(bO == 1)
					actual = uno;
				else if(bO == 0)
					actual = cero;
				else
					actual = new Vector(b);
			}
			else
			{
				if(bO == 0)
					actual = cero;
				else if(qO == 0)
					actual = cero;
				else if(qO == 1)
				{
					if(bO == 1)
						actual = uno;
					else
						actual = new Vector(b);
				}
			}
			hash.add(actual);
			sumar(x1, q);
		}
	}

	static class Vector
	{
		int[] val;
		Integer hash;
		
		Vector(int[] v)
		{
			val = v.clone();
		}
		
		@Override
		public boolean equals(Object o) 
		{
			return Arrays.equals(((Vector) o).val, val);
		}
		
		@Override
		public int hashCode() 
		{
			if(hash != null)
				return hash;
			return hash = Arrays.hashCode(val);
		}
	}
	
	static Vector cero = new Vector(new int[]{-1});
	static Vector uno = new Vector(new int[0]);

	static int resolver(Numero b1, Numero q1, Numero b2, Numero q2, int b1O, int q1O, int b2O, int q2O)
	{
		TreeSet <Integer> bases = darTodos(b1, q1);
		bases.addAll(darTodos(b2, q2));
		int[] basesR = new int[bases.size()];
		int actual = 0;
		for(int i : bases)
			basesR[actual++] = i;
		int[] b11 = vals(b1, basesR);
		int[] q11 = vals(q1, basesR);
		int[] b21 = vals(b2, basesR);
		int[] q21 = vals(q2, basesR);
		HashSet <Vector> todos = new HashSet <Vector> ();
		generar(b11, q11, b1O, q1O, todos, n1);
		generar(b21, q21, b2O, q2O, todos, n2);
		return todos.size();
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			Integer a = sc.nextInteger();
			if(a == null)
				return;
			int b1 = a;
			int q1 = sc.nextInt();
			n1 = sc.nextInt();
			int b2 = sc.nextInt();
			int q2 = sc.nextInt();
			n2 = sc.nextInt();
			Numero b1N = factorizar(b1);
			Numero q1N = factorizar(q1);
			Numero b2N = factorizar(b2);
			Numero q2N = factorizar(q2);
			System.out.println(resolver(b1N, q1N, b2N, q2N, b1, q1, b2, q2));
		}
	}
}