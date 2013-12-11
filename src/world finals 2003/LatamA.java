import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class LatamA 
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

	static class Line implements Comparable<Line>{
		long m,b;
		public Line(long mm, long bb){
			m = mm;
			b = bb;
		}
		long eval(long x){
			return m * x + b;
		}
		Frac getIntersection(Line o){
			if (o == null)
				return minf;
			return new Frac(o.b - b, m - o.m);
		}
		@Override
		public int compareTo(Line o) {
			return Long.valueOf(o.m).compareTo(m);
		}
		public String toString(){
			return "(" + m +" | " + b +")";
		}
	}
	
	static class Frac implements Comparable<Frac>{
		long n,d;
		public Frac(long nn, long dd){
			n = nn;
			d = dd;
			if (dd < 0){ // Trick for comparator consistency
				d = -dd;
				n = -nn;
			}
			Simplify();
		}
		void Simplify(){
			long g = gcd(Math.abs(n), Math.abs(d));
			n /= g;
			d /= g;
		}
		@Override
		public int compareTo(Frac o) {
			/*
			BigInteger a = new BigInteger(n + "");
			a = a.multiply(new BigInteger(o.d + ""));
			BigInteger b = new BigInteger(o.n + "");
			b = b.multiply(new BigInteger(d + ""));*/
			double a = n / (d + 0.0);
			double b = o.n / (o.d + 0.0);
			return Double.valueOf(a).compareTo(Double.valueOf(b));
		}
		long gcd(long a, long b){
			if (b == 0)
				return a;
			return gcd(b, a % b);
		}
		public String toString(){
			return "(" + n +"/" + d +")";
		}
	}
	
	static final Frac minf = new Frac(-1000000000, 1); // -1 is equivalent to -infinity because of the problem
	
	static class Hull{
		TreeMap<Line, Frac> lines;
		TreeMap<Frac, Line> intervals;
		
		public Hull(){
			lines =new TreeMap<Line, Frac>();
			intervals = new TreeMap<Frac, Line>();
		}
		
		long query(long x){
			Frac tmp = intervals.floorKey(new Frac(x, 1));
			Line maximal = intervals.get(tmp);
			return maximal.eval(x);
		}
		
		boolean checkbad(Line l1, Line l2, Line l3){
			Frac f13 = new Frac(l3.b - l1.b, l1.m - l3.m);
			Frac f12 = new Frac(l1.b - l2.b, l2.m - l1.m);	
			return f13.compareTo(f12) <= 0;
		}
		
		void Add(long m, long b){
			Line l = new Line(m, b);
			Line floor = lines.floorKey(l);
			// is there a line in the hull with same slope?
			if (floor!= null && floor.m == m){
				// this is maximization problem so the higher the y-intercept, the better.
				if (floor.b > b){
					intervals.remove(lines.get(floor));
					lines.remove(floor);
				}
				else
					return;
			}
			Line lower = lines.lowerKey(l);
			Line higher = lines.higherKey(l);
			// is this line important for the hull?
			if (lower != null && higher != null){
				if (checkbad(floor,l , higher))
					return;
			}
			// keep the invariant to the left
			while(true){
				Line l2 = lines.lowerKey(l);
				if (l2 == null)
					break;
				Line l1 = lines.lowerKey(l2);
				if (l1 == null)
					break;
				if (!checkbad(l1,l2,l))
					break;
				intervals.remove(lines.get(l2));
				lines.remove(l2);
			}
			//keep the invariant to the right
			while(true){
				Line l4 = lines.higherKey(l);
				if (l4 == null)
					break;
				Line l5 = lines.higherKey(l4);
				if (l5 == null)
					break;
				if (!checkbad(l,l4,l5))
					break;
				intervals.remove(lines.get(l4));
				lines.remove(l4);
			}
			Line forward = lines.higherKey(l);
			if (forward != null){
				Frac intersection = l.getIntersection(forward);
				intervals.remove(lines.get(forward));
				lines.put(forward, intersection);
				intervals.put(intersection, forward);
			}
			lower = lines.lowerKey(l);
			Frac intersection = l.getIntersection(lower);
			lines.put(l, intersection);
			intervals.put(intersection, l);
		}
	}
	
	static long solve(int N, int K, int[] x, int[] w)
	{
		long[][] dp = new long[N + 1][K + 1];
		long[] ii1 = new long[N + 1];
		long[] ii2 = new long[N + 1];
		long acumW = 0;
		long acumWX = 0;
		for(int n = 1; n <= N; n++)
		{
			acumW += w[n];
			acumWX += x[n] * w[n];
			ii1[n] = acumW;
			ii2[n] = acumWX;
			dp[n][1] = x[n] * acumW - acumWX;
		}
		for(int k = 0; k <= K; k++)
			for(int n = 0; n <= k; n++)
				dp[n][k] = 0;
		for(int k = 2; k <= K; k++)
		{
			Hull hull = new Hull();
			for(int n = 0; n <= N; n++)
			{
				if(n > k)
				{	
					long tmp = hull.query(x[n]);
					long ans = (n == k ? 0 : hull.query(x[n])) + x[n] * ii1[n - 1] - ii2[n - 1];
					dp[n][k] = ans;
				}
				long bi = dp[n][k - 1] + ii2[n];
				long mi = -ii1[n];
				hull.Add(mi, bi);
			}
		}
		return dp[N][K];
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			Integer nTmp = sc.nextInteger();
			if(nTmp == null) return;
			int n = nTmp;
			int k = sc.nextInt();
			int[] xs = new int[n + 1];
			int[] ws = new int[n + 1];
			for(int i = 1; i < xs.length; i++)
			{
				xs[i] = sc.nextInt();
				ws[i] = sc.nextInt();
			}
			System.out.println(solve(n, k, xs, ws));
		}
	}
}

