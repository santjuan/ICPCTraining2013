import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public class CodeD 
{
	static class Scanner 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");

		public String nextLine() {
			try {
				return br.readLine();
			} catch (Exception e) {
				throw (new RuntimeException());
			}
		}

		public String next() {
			while (!st.hasMoreTokens()) {
				String l = nextLine();
				if (l == null)
					return null;
				st = new StringTokenizer(l);
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public int[] nextIntArray(int n) {
			int[] res = new int[n];
			for (int i = 0; i < res.length; i++)
				res[i] = nextInt();
			return res;
		}

		public long[] nextLongArray(int n) {
			long[] res = new long[n];
			for (int i = 0; i < res.length; i++)
				res[i] = nextLong();
			return res;
		}

		public double[] nextDoubleArray(int n) {
			double[] res = new double[n];
			for (int i = 0; i < res.length; i++)
				res[i] = nextLong();
			return res;
		}

		public void sortIntArray(int[] array) {
			Integer[] vals = new Integer[array.length];
			for (int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for (int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}

		public void sortLongArray(long[] array) {
			Long[] vals = new Long[array.length];
			for (int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for (int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}

		public void sortDoubleArray(double[] array) {
			Double[] vals = new Double[array.length];
			for (int i = 0; i < array.length; i++)
				vals[i] = array[i];
			Arrays.sort(vals);
			for (int i = 0; i < array.length; i++)
				array[i] = vals[i];
		}
	}

	
	static boolean recorrerTodos(int indice, int[] exponentes, int[] actuales)
	{
		if(indice == exponentes.length)
		{
			boolean okNumero = false;
			boolean okUno = false;
			for(int i = 0; i < exponentes.length; i++)
			{
				if(actuales[i] != exponentes[i])
					okNumero = true;
				if(actuales[i] != 0)
					okUno = true;
			}
			if(okNumero && okUno)
			{
				if(!dp(actuales))
				{
					if(Arrays.equals(exponentesOriginales, exponentes))
						primeraJugada = convertirNumero(actuales);
					return true;
				}
				else
					return false;
			}
			else
				return false;
		}
		else
		{
			for(int i = 0; i <= exponentes[indice]; i++)
			{
				actuales[indice] = i;
				if(recorrerTodos(indice + 1, exponentes, actuales))
					return true;
			}
			return false;
		}
	}
	
	private static long convertirNumero(int[] actuales) 
	{
		long tmp = 1;
		for(int i = 0; i < actuales.length; i++)
		{
			for(int j = 0; j < actuales[i]; j++)
				tmp *= factoresOriginales.get(i);
		}
		return tmp;
	}

	static class Entrada
	{
		int[] exponentes;

		public Entrada(int[] exponentes)
		{
			this.exponentes = exponentes;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(exponentes);
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Entrada other = (Entrada) obj;
			if (!Arrays.equals(exponentes, other.exponentes))
				return false;
			return true;
		}
	}
	static HashMap <Entrada, Boolean> dp = new HashMap <Entrada, Boolean> ();
	
	
	static long primeraJugada = 0;
	static int[] exponentesOriginales;
	static ArrayList <Long> factoresOriginales;
	
	static boolean dp(int[] exponentes)
	{
		exponentes = exponentes.clone();
		Entrada e = new Entrada(exponentes);
		if(dp.containsKey(e))
			return dp.get(e);
		if(esPrimo(exponentes))
		{
			if(Arrays.equals(exponentesOriginales, exponentes))
				primeraJugada = 0;
			dp.put(e, true);
			return true;
		}
		if(recorrerTodos(0, exponentes, new int[exponentes.length]))
		{
			dp.put(e, true);
			return true;
		}
		else
		{
			dp.put(e, false);
			return false;
		}
	}
	
	private static boolean esPrimo(int[] exponentes) 
	{
		int numeroUnos = 0;
		for(int i : exponentes)
			if(i == 1)
				numeroUnos++;
			else if(i > 1)
				return false;
		return numeroUnos == 1;
	}

	static ArrayList <Integer> generarDivisores(long n) 
	{
		ArrayList<Long> factores = new ArrayList<Long>();
		ArrayList<Integer> numeroVeces = new ArrayList<Integer>();
		int limite = (int) Math.ceil(Math.sqrt(n)) + 1;
		limite = (int) Math.min(n, limite);
		for (int i = 2; i <= limite; i++) {
			if (n % i == 0) {
				int cuenta = 0;
				while (n % i == 0) {
					n /= i;
					cuenta++;
				}
				factores.add((long) i);
				numeroVeces.add(cuenta);
			}
		}
		if (n != 1) {
			if (factores.contains(n))
				numeroVeces.set(factores.indexOf(n),
						numeroVeces.get(factores.indexOf(n)) + 1);
			else {
				factores.add(n);
				numeroVeces.add(1);
			}
		}
		factoresOriginales = factores;
		return numeroVeces;
	}
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner();
		long n = sc.nextLong();
		ArrayList <Integer> facts = generarDivisores(n);
		int[] factores = new int[facts.size()];
		for(int i = 0; i < factores.length; i++)
			factores[i] = facts.get(i);
		exponentesOriginales = factores.clone();
		boolean respuesta = dp(factores);
		if(respuesta || n == 1)
		{
			System.out.println("1");
			System.out.println(primeraJugada);
		}
		else
			System.out.println("2");
	}
}