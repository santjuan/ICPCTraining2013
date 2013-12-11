import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class CodeF 
{
	
	static class Scanner {
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
	
	static long[] gcd(long p, long q)
	{
		if (q == 0)
			return new long[] { p, 1, 0 };
		long[] vals = gcd(q, p % q);
		long d = vals[0];
		long a = vals[2];
		long b = vals[1] - (p / q) * vals[2];
		return new long[] { d, a, b };
	}

	static long inverse(long k, long n) 
	{
		long[] vals = gcd(k, n);
		long d = vals[0];
		long a = vals[1];
		if (d > 1) { System.out.println("Inverse does not exist."); return 0; }
		if (a > 0) return a;
		return n + a;
	}
	   
	
	static long[] factorial;
	static long[] inversos;
	static long modulo = 1000000007;
	
	static void calcularFactorial()
	{
		factorial = new long[100010];
		inversos = new long[100010];
		factorial[0] = 1;
		for(int i = 1; i < 100010; i++)
		{
			long siguiente = (factorial[i - 1] % modulo) * (((long) i) % modulo);
			siguiente %= modulo;
			factorial[i] = siguiente;
		}
		for(int i = 0; i < 100010; i++)
			inversos[i] = inverse(factorial[i], modulo) % modulo;
	}
	
	static long calcularCombinatoria(int n, int k)
	{
		long ans = factorial[n];
		ans %= modulo;
		ans *= inversos[k] % modulo;
		ans %= modulo;
		ans *= inversos[n - k] % modulo;
		ans %= modulo;
		return ans;
	}
	
	static boolean esLucky(int numero)
	{
		String s = numero + "";
		for(char c : s.toCharArray())
		{
			if(c != '4' && c != '7')
				return false;
		}
		return true;
	}
	
	static long bigmod(long b, long p, long m){
		 long mask = 1;
		 long pow2 = b % m;
		 long r = 1;
		 while (mask != 0){
		   if ((p & mask) != 0) r = (r * pow2) % m;
		   pow2 = (pow2 * pow2) % m;
		   mask <<= 1;
		 }
		 return r % m;
	}

	static int[] cuantos;
	static int[] valores;
	static long[][] dp;
	
	static long dp(int k, int indice)
	{
		if(k < 0)
			return 0L;
		if(dp[k][indice] != -1)
			return dp[k][indice];
		if(indice == cuantos.length && k == 0)
			return dp[k][indice] = 1L;
		else if(indice == cuantos.length)
			return dp[k][indice] = 0L;
		int limite = Math.min(k, cuantos[indice]);
		long acum = 0;
		long potencia = 1;
		for(int escogidos = 0; escogidos <= limite; escogidos++)
		{
			long res = calcularCombinatoria(cuantos[indice], escogidos) % modulo;
			res *= potencia % modulo;
			potencia *= valores[indice];
			potencia %= modulo;
			res %= modulo;
			res *= dp(k - escogidos, indice + 1) % modulo;
			res %= modulo;
			acum += res;
			acum %= modulo;
		}
		return dp[k][indice] = acum % modulo;
	}
	
	static void agregar(int num, HashMap <Integer, Integer> mapa)
	{
		if(!mapa.containsKey(num))
			mapa.put(num, 0);
		mapa.put(num, mapa.get(num) + 1);
	}
	
	public static void main(String[] args)
	{
		calcularFactorial();
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int k = sc.nextInt();
		int normales = 0;
		HashMap <Integer, Integer> luckysH = new HashMap <Integer, Integer> ();
		for(int i = 0; i < n; i++)
		{
			int num = sc.nextInt();
			if(esLucky(num))
				agregar(num, luckysH);
			else
				normales++;
		}
		HashMap <Integer, Integer> mapaH = new HashMap <Integer, Integer> ();
		for(Map.Entry<Integer, Integer> vals : luckysH.entrySet())
			agregar(vals.getValue(), mapaH);
		cuantos = new int[mapaH.size()];
		valores = new int[mapaH.size()];
		int indice = 0;
		for(Map.Entry<Integer, Integer> vals : mapaH.entrySet())
		{
			cuantos[indice] = vals.getValue();
			valores[indice++] = vals.getKey();
		}
		int luckys = luckysH.size();
		dp = new long[luckys + 2][cuantos.length + 1];
		for(long[] l : dp)
			Arrays.fill(l, -1);
		long result = 0;
		for(int i = 0; i <= k; i++)
		{
			if(i <= normales && (k - i) <= luckys)
			{
				long tmp = calcularCombinatoria(normales, i) % modulo;
				long tmp2 = dp((k - i), 0) % modulo;
				result += (tmp * tmp2) % modulo;
				result %= modulo;
			}
		}
		System.out.println(result);
	}
}
