import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;


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

	static class Entrada
	{
		public Entrada(String a, int puntos)
		{
			nombre = a;
			val = puntos;
		}
		String nombre;
		int val;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		TreeMap <Integer, String> vals = new TreeMap <Integer, String> ();
		TreeMap <String, Integer> points = new TreeMap <String, Integer> ();
		ArrayList <Entrada> entradas = new ArrayList <Entrada> ();
		for(int i = 0; i < n; i++)
		{
			String a = sc.next();
			int val = sc.nextInt();
			if(points.get(a) == null)
				points.put(a, val);
			else
				points.put(a, points.get(a) + val);
			int puntos = points.get(a);
			if(vals.get(puntos) == null)
				vals.put(puntos, a);
			entradas.add(new Entrada(a, puntos));
		}
		ArrayList <String> bests = new ArrayList <String> ();
		int bestCount = Integer.MIN_VALUE;
		for(Map.Entry <String, Integer> e : points.entrySet())
		{
			if(e.getValue().intValue() == bestCount)
				bests.add(e.getKey());
			else if(e.getValue().intValue() > bestCount)
			{
				bestCount = e.getValue().intValue();
				bests.clear();
				bests.add(e.getKey());
			}		
		}
		if(bests.size() == 1)
			System.out.println(bests.get(0));
		else
		{
			for(Entrada e : entradas)
				if(bests.contains(e.nombre) && e.val >= bestCount)
				{
					System.out.println(e.nombre);
					return;
				}
		}
	}

}
