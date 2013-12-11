import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class LiveD
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
		
		public void sortLongArray(long[] todos, int tamTodos) 
		{
			Long[] vals = new Long[tamTodos];
			for(int i = 0; i < tamTodos; i++)
				vals[i] = todos[i];
			Arrays.sort(vals);
			for(int i = 0; i < tamTodos; i++)
				todos[i] = vals[i];
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
	
	static class QuickMap
	{
		static final long[] todos = new long[120011];
		static final int[] cuentasTmp = new int[120011];
		static int tamTodos;
		int[] cuentas;
		int primeroExiste;
		
		QuickMap()
		{
			tamTodos = 0;
		}
		
		QuickMap(boolean noBorrar)
		{
		}

		public void agregar(long val) 
		{
			todos[tamTodos++] = val;
		}

		public void cerrar(Scanner sc) 
		{
			sc.sortLongArray(todos, tamTodos);
			int tamTodosActual = 1;
			int tamCuentas = 0;
			cuentasTmp[0] = 1;
			for(int i = 1; i < tamTodos; i++)
			{
				if(todos[i] != todos[i - 1])
				{
					todos[tamTodosActual++] = todos[i];
					tamCuentas++;
					cuentasTmp[tamCuentas] = 1;
				}
				else
					cuentasTmp[tamCuentas]++;
			}
			tamCuentas++;
			tamTodos = tamTodosActual;
			cuentas = new int[tamCuentas];
			for(int i = 0; i < cuentas.length; i++)
				cuentas[i] = cuentasTmp[i];
			primeroExiste = 0;
		}
		
		void arreglarPrimero()
		{
			while(primeroExiste < cuentas.length && cuentas[primeroExiste] <= 0)
				primeroExiste++;
		}
		
		long sacarPrimero()
		{
			arreglarPrimero();
			cuentas[primeroExiste]--;
			return todos[primeroExiste];
		}

		public boolean contiene(long l)
		{
			int indice = Arrays.binarySearch(todos, 0, tamTodos, l);
			return indice >= 0 && cuentas[indice] > 0;
		}

		public QuickMap clonar()
		{
			QuickMap clon = new QuickMap(true);
			clon.cuentas = cuentas.clone();
			clon.primeroExiste = primeroExiste;
			return clon;
		}

		public boolean sacar(long l) 
		{
			int posicion = Arrays.binarySearch(todos, 0, tamTodos, l);
			if(posicion < 0)
				return false;
			if(cuentas[posicion] > 0)
			{
				cuentas[posicion]--;
				return true;
			}
			else
				return false;
		}

		public boolean estaVacio() {
			arreglarPrimero();
			return primeroExiste == cuentas.length;
		}
	}
	
	static long mulMod(long a, long b, long n) {
		if(a == 0 || b <= (1L << 62) / a) return (a * b) % n;
		long result = 0;	
		if(a < b) { long tmp = a; a = b; b = tmp; }
		for(; b != 0; b >>= 1) {
			if((b & 1) != 0) {
				result += a; if(result >= n) result -= n;
			}
			a <<= 1; if(a >= n) a -= n;
		}
		return result;
	}

	static long gcd(long u, long v) {
		while(u != 0){v %= u; long tmp = u; u = v; v = tmp;} return v;
	}
	
	static long f(long x, long c, long n) {
		long result = mulMod(x, x, n);
		result += c; if(result >= n) result -= n;
		return result;
	}
	
	static long pollardsRho(long n) {
		for (int count = 0, c = 1;;) {
			long x = 2, y = 2, pot = 1, lam = 1, d;
			do {
				++count;
				if(pot == lam) {x = y; pot <<= 1; lam = 0;}
	      y = f(y,c,n);
	      lam++;
	      d = gcd(x >= y ? x - y : y - x, n);
			} while (d == 1);
	    if (d != n) return d; else c++;
		}
	}
	
	static long powMod(long a, long exp, long n) {
		long result = 1;
		for(; exp != 0; exp >>= 1) {
			if((exp & 1) != 0) {
				result = mulMod(result, a, n);
			}
			a = mulMod(a, a, n);
		}
		return result;
	}
	
	static boolean primeTestMillerRabin(long n, long a) {
		long d = n-1, x;
		int s = 0, r;
		while ((d & 1) == 0 && d != 0){ s++; d >>= 1;}
		while (a >= n) a >>= 1;
		x = powMod(a, d, n);
		if (x != 1 && x != n-1) {
			r = 1;
			while (r <= s-1 && x != n-1) {
				x = mulMod(x, x, n);
				if (x == 1) return false; else r++;
			}
			if (x != n-1) return false;
		}
		return true;
	}
	
	static boolean isPrime(long n) {
		if (n <= 1) return false;
		if (n <= 3) return true;
	  if ((n & 1) == 0) return false;
	  return primeTestMillerRabin(n, 2) && primeTestMillerRabin(n, 3) 
	  && primeTestMillerRabin(n, 5) && primeTestMillerRabin(n, 7) 
	  && (n < 3215031751L || (primeTestMillerRabin(n, 11) 
	  && (n < 2152302898747L || (primeTestMillerRabin(n, 13) 
	  && (n < 3474749660383L || (primeTestMillerRabin(n, 17) 
	  && (n < 341550071728321L || (primeTestMillerRabin(n, 23)))))))));
	}
	
	private static void generarDivisores(ArrayList <Long> factores, ArrayList <Integer> numeroVeces, int i, ArrayList <Long> respuesta, long acum)
	{
		if(i == factores.size())
		{
			respuesta.add(acum);
			return;
		}
		for(int j = 0; j <= numeroVeces.get(i); j++)
		{
			generarDivisores(factores, numeroVeces, i + 1, respuesta, acum);
			acum *= factores.get(i);
		}
	}
	
	static void factorizar(long n, HashMap <Long, Integer> todos)
	{
		if(n == 1)
			return;
		if(isPrime(n))
		{
			if(todos.containsKey(n))
				todos.put(n, todos.get(n) + 1);
			else
				todos.put(n, 1);
		}
		else
		{
			long fact = pollardsRho(n);
			factorizar(fact, todos);
			factorizar(n / fact, todos);
		}
	}
	
	public static ArrayList <Long> generarDivisores(long n)
	{
		ArrayList <Long> factores = new ArrayList <Long> ();
		ArrayList <Integer> numeroVeces = new ArrayList <Integer> ();
		HashMap <Long, Integer> todos = new HashMap <Long, Integer> ();
		factorizar(n, todos);
		for(Map.Entry <Long, Integer> e : todos.entrySet())
		{
			factores.add(e.getKey());
			numeroVeces.add(e.getValue());
		}
		ArrayList <Long> respuesta = new ArrayList <Long> ();
		generarDivisores(factores, numeroVeces, 0, respuesta, 1);
		Collections.sort(respuesta);
		return respuesta;
	}
	
	static QuickMap todos;

	static ArrayList <Long> solve(long x1, long a, long b)
	{
		ArrayList <Long> answer = new ArrayList <Long> ();
		long x2 = a / x1;
		long x3 = b / x1;
		if(x2 == 1 || x3 == 1)
			return null;
		answer.add(x1);
		answer.add(x2);
		answer.add(x3);
		if(!todos.contiene(x2 * x3))
			return null;
		QuickMap mapa = todos.clonar();
		if(!mapa.sacar(x2 * x3))
			return null;
		while(!mapa.estaVacio())
		{
			long e = mapa.sacarPrimero();
			if(e % x1 != 0)
				return null;
			long xi = e / x1;
			if(xi == 1)
				return null;
			int indice = 0;
			for(long x : answer)
			{
				if(indice == 0)
				{
					indice++;
					continue;
				}
				if(!mapa.sacar(x * xi))
					return null;
			}
			answer.add(xi);
		}
		Collections.sort(answer);
		return answer;
	}
	
	static ArrayList <Long> solve()
	{
		long a = todos.sacarPrimero();
		long b = todos.sacarPrimero();
		long gcd = gcd(a, b);
		outer:
		for(long d : generarDivisores(gcd))
		{
			if(d == 1)
				continue;
			ArrayList <Long> possible = solve(d, a, b);
			if(possible != null)
			{
				TreeSet <Long> todos = new TreeSet <Long> ();
				for(long l : possible)
					if(todos.contains(l) || l > 1000000000)
						continue outer;
					else
						todos.add(l);
				return possible;
			}
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int caso = 1; caso <= t; caso++)
		{
			int n = sc.nextInt();
			int limite = (n * (n - 1)) / 2;
			todos = new QuickMap();
			for(int i = 0; i < limite; i++)
				todos.agregar(sc.nextLong());
			todos.cerrar(sc);
			StringBuilder sb = new StringBuilder();
			sb.append("Case " + caso + ":");
			for(long l : solve())
				sb.append(" " + l);
			System.out.println(sb.toString());
		}
	}
}