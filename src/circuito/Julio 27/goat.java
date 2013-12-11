import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class goat 
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
	}
	
	static class Goat
	{
		double x;
		double y;
		Double fixed;
		
		public Goat(double xx, double yy)
		{
			x = xx;
			y = yy;
		}

		double distancia(Goat otra)
		{
			return Math.sqrt((x - otra.x) * (x - otra.x) + (y - otra.y) * (y - otra.y));
		}
		
		double maximo(Goat otra)
		{
			double dist = distancia(otra);
			if(otra.fixed != null)
			{
				dist -= otra.fixed;
				return dist;
			}
			else
				return dist / 2;
		}
	}
	
	static double EPS = 1e-9;
	
	static class Simplex 
	{
		int m, n;
		int[]  B, N;
		double[][] D;

		Simplex(double[][] A, double[] b, double[] c) 
		{
			m = b.length;
			n = c.length;
			N = new int[n+1];
			B = new int[m]; 
			D = new double[m+2][n+2];  
			for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) 
				D[i][j] = A[i][j];
			for (int i = 0; i < m; i++) { B[i] = n+i; D[i][n] = -1; 
			D[i][n+1] = b[i]; }
			for (int j = 0; j < n; j++) { N[j] = j; D[m][j] = -c[j]; }
			N[n] = -1; D[m+1][n] = 1;
		}

		void Pivot(int r, int s) 
		{
			for (int i = 0; i < m+2; i++) if (i != r)
				for (int j = 0; j < n+2; j++) if (j != s)
					D[i][j] -= D[r][j] * D[i][s] / D[r][s];
			for (int j = 0; j < n+2; j++) if (j != s) D[r][j] /= D[r][s];
			for (int i = 0; i < m+2; i++) if (i != r) D[i][s] /= -D[r][s];
			D[r][s] = 1.0 / D[r][s];
			int tmp = B[r];
			B[r] = N[s];
			N[s] = tmp;
		}

		boolean simplex(int phase) 
		{
			int x = phase == 1 ? m+1 : m;
			while (true) {
				int s = -1;
				for (int j = 0; j <= n; j++) {
					if (phase == 2 && N[j] == -1) continue;
					if (s == -1 || D[x][j] < D[x][s] || D[x][j] == D[x][s] && N[j] < N[s]) 
						s = j;
				}
				if (D[x][s] >= -EPS) return true;
				int r = -1;
				for (int i = 0; i < m; i++) {
					if (D[i][s] <= 0) continue;
					if (r == -1 || D[i][n+1] / D[i][s] < D[r][n+1] / D[r][s] ||
							D[i][n+1] / D[i][s] == D[r][n+1] / D[r][s] && B[i] < B[r]) r = i;
				}
				if (r == -1) return false;
				Pivot(r, s);
			}
		}

		double Solve(double[] x) 
		{
			int r = 0;
			for (int i = 1; i < m; i++) if (D[i][n+1] < D[r][n+1]) r = i;
			if (D[r][n+1] <= -EPS) {
				Pivot(r, n);
				if (!simplex(1) || D[m+1][n+1] < -EPS)
					return Double.NEGATIVE_INFINITY;
				for (int i = 0; i < m; i++) if (B[i] == -1) {
					int s = -1;
					for (int j = 0; j <= n; j++) 
						if (s == -1 || D[i][j] < D[i][s] || D[i][j] == D[i][s] 
								&& N[j] < N[s]) s = j;
					Pivot(i, s);
				}
			}
			if (!simplex(2)) return Double.POSITIVE_INFINITY;
			for (int i = 0; i < m; i++) if (B[i] < n) x[B[i]] = D[i][n+1];
			return D[m][n+1];
		}
	}

	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0) return;
			Goat[] goats = new Goat[n];
			for(int i = 0; i < n; i++)
				goats[i] = new Goat(sc.nextDouble(), sc.nextDouble());
			ArrayList <double[]> aT = new ArrayList <double[]> ();
			ArrayList <Double> bT = new ArrayList <Double> ();
			for(int i = 0; i < n; i++)
				for(int j = i + 1; j < n; j++)
				{
					double[] ans = new double[n];
					ans[i] = 1;
					ans[j] = 1;
					aT.add(ans);
					bT.add(goats[i].distancia(goats[j]));
				}
			double[] c = new double[n];
			for(int i = 0; i < n; i++) c[i] = 1;
			double[][] A = new double[aT.size()][n];
			for(int i = 0; i < aT.size(); i++)
				A[i] = aT.get(i);
			double[] b = new double[bT.size()];
			for(int i = 0; i < bT.size(); i++)
				b[i] = bT.get(i);
			double[] x = new double[n];
			Simplex simplex = new Simplex(A, b, c);
			String cad = String.format("%.2f", simplex.Solve(x));
			System.out.println(cad);
		}
	}
}
