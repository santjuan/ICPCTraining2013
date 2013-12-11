import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeC 
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

	public static void main(String[] args) {
		Scanner sc = new Scanner();
		String[] e = sc.next().split(":");
		String hora = convertir(e[0]);
		String minuto = convertir(e[1]);
		boolean empezo = false;
		String res = "";
		for(int i = 2; i <= 60; i++)
		{
			boolean bien = false;
			try
			{
				int val = parseInt(hora, i);
				if(val >= 0 && val <= 23)
					bien = true;
				else
					bien = false;
			}
			catch(Exception e1)
			{
				bien = false;
			}
			if(bien)
				try
				{
					int val = parseInt(minuto, i);
					if(val >= 0 && val <= 59)
						bien = true;
					else
						bien = false;
				}
				catch(Exception e1)
				{
					bien = false;
				}
			if(bien)
			{
				res += (empezo ? " " : "") + i;
				empezo = true;
			}
		}
		if(res.contains("60"))
			System.out.println("-1");
		else
			System.out.println(res.isEmpty() ? "0" : res);
	}

	private static int parseInt(String hora, int base) 
	{
		int exponente = 1;
		int result = 0;
		for(char c : new StringBuilder(hora).reverse().toString().toCharArray())
		{
			int val;
			if(c >= '0' && c <= '9')
				val = c - '0';
			else
				val = (c - 'A') + 10;
			if(val >= base)
				return 1000;
			result += exponente * val;
			exponente *= base;
		}
		return result;
	}

	private static String convertir(String s) 
	{
		while(s.length() > 1 && s.charAt(0) == '0')
			s = s.substring(1);
		return s;
	}
}
