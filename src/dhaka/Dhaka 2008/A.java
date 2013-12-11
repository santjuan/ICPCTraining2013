import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A 
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

	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		boolean[] esta = new boolean[256];
		boolean[] noEsta = new boolean[256];
		for(int caso = 1; true; caso++)
		{
			Arrays.fill(esta, false);
			Arrays.fill(noEsta, false);
			boolean posible = true;
			int n = sc.nextInt();
			if(n == 0)
				return;
			outer:
			for(int i = 0; i < n; i++)
			{
				String entrada = sc.next();
				entrada = entrada.substring(1, entrada.length() - 1);
				char[] a = entrada.toCharArray();
				entrada = sc.next();
				entrada = entrada.substring(1, entrada.length() - 1);
				char[] b = entrada.toCharArray();
				if(a.length < b.length)
				{
					posible = false;
					for(int j = i + 1; j < n; j++)
					{
						sc.next();
						sc.next();
					}
					break;
				}
				for(int j = 0; j < b.length; j++)
				{
					if(b[j] != a[j])
					{
						posible = false;
						for(int k = i + 1; k < n; k++)
						{
							sc.next();
							sc.next();
						}
						break outer;
					}
					else
					{
						if(noEsta[b[j]])
						{
							posible = false;
							for(int k = i + 1; k < n; k++)
							{
								sc.next();
								sc.next();
							}
							break outer;
						}
						esta[b[j]] = true;
					}
				}
				if(a.length > b.length)
					noEsta[a[b.length]] = true;
			}
			StringBuilder sb = new StringBuilder();
			char ultimo = 0;
			for(char i = 0; i < 256; i++)
				if(esta[i])
				{
					ultimo = i;
					if(noEsta[i])
						posible = false;
				}
			for(char i = '0'; i <= '9'; i++)
				if(!noEsta[i] && i <= ultimo)
					sb.append(i);
			for(char i = 'A'; i <= 'Z'; i++)
				if(!noEsta[i] && i <= ultimo)
					sb.append(i);	
			for(char i = 'a'; i <= 'z'; i++)
				if(!noEsta[i] && i <= ultimo)
					sb.append(i);
			if(sb.length() == 0)
			{
				boolean llego = false;
				for(char i = '0'; i <= '9'; i++)
					if(!noEsta[i] && !llego)
					{
						sb.append(i);
						llego = true;
					}
				for(char i = 'A'; i <= 'Z'; i++)
					if(!noEsta[i] && !llego)
					{
						sb.append(i);	
						llego = true;
					}
				for(char i = 'a'; i <= 'z'; i++)
					if(!noEsta[i] && i <= ultimo)
					{
						sb.append(i);
						llego = true;
					}
			}
			if(sb.length() == 0 || !posible)
				System.out.println("Case " + caso + ": I_AM_UNDONE");
			else
				System.out.println("Case " + caso + ": [" + sb.toString() + "]");
		}
	}
}
