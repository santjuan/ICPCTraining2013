import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class CodeB 
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

	public static int tipo(char[] numero)
	{
		boolean todosIguales = true;
		for(int i = 1; i < numero.length; i++)
			if(numero[i - 1] != numero[i])
			{
				todosIguales = false;
				break;
			}
		if(todosIguales)
			return 0;
		boolean bajan = true;
		for(int i = 1; i < numero.length; i++)
			if(numero[i - 1] <= numero[i])
			{
				bajan = false;
				break;
			}
		if(bajan)
			return 1;
		return 2;
	}

	static char[] convertir(String s)
	{
		s = s.replace("-", "");
		return s.toCharArray();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int mejorTaxis = -1;
		int mejorPizza = -1;
		int mejorGirls = -1;
		ArrayList <String> mejorT = new ArrayList <String> ();
		ArrayList <String> mejorP = new ArrayList <String> ();
		ArrayList <String> mejorG = new ArrayList <String> ();
		for(int i = 0; i < n; i++)
		{
			int cuantos = sc.nextInt();
			String nombre = sc.next();
			int numeroTaxis = 0;
			int numeroPizza = 0;
			int numeroGirls = 0;
			for(int j = 0; j < cuantos; j++)
			{
				char[] val = convertir(sc.next());
				int tipo = tipo(val);
				if(tipo == 0)
					numeroTaxis++;
				else if(tipo == 1)
					numeroPizza++;
				else
					numeroGirls++;
			}
			if(numeroTaxis == mejorTaxis)
				mejorT.add(nombre);
			else if(numeroTaxis > mejorTaxis)
			{
				mejorTaxis = numeroTaxis;
				mejorT.clear();
				mejorT.add(nombre);
			}
			if(numeroPizza == mejorPizza)
				mejorP.add(nombre);
			else if(numeroPizza > mejorPizza)
			{
				mejorPizza = numeroPizza;
				mejorP.clear();
				mejorP.add(nombre);
			}
			if(numeroGirls == mejorGirls)
				mejorG.add(nombre);
			else if(numeroGirls > mejorGirls)
			{
				mejorGirls = numeroGirls;
				mejorG.clear();
				mejorG.add(nombre);
			}
		}
		System.out.println("If you want to call a taxi, you should call: " + mejorT.toString().replace("[", "").replace("]", "") + ".");
		System.out.println("If you want to order a pizza, you should call: " + mejorP.toString().replace("[", "").replace("]", "") + ".");
		System.out.println("If you want to go to a cafe with a wonderful girl, you should call: " + mejorG.toString().replace("[", "").replace("]", "") + ".");

	}
}
