import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Robert 
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
		
		public Integer nextInteger()
		{
			String s = next();
			if(s == null)
				return null;
			return Integer.parseInt(s);
		}
		
		public int[][] nextIntMatrix(int n, int m)
		{
			int[][] ans = new int[n][];
			for(int i = 0; i < n; i++)
				ans[i] = nextIntArray(m);
			return ans;
		}
		
		public char[][] nextGrid(int r) 
		{
			char[][] grid = new char[r][];
			for(int i = 0; i < r; i++)
				grid[i] = next().toCharArray();
			return grid;
		}
		
		public static <T> T fill(T arreglo, int val)
		{
			if(arreglo instanceof Object[])
			{
				Object[] a = (Object[]) arreglo;
				for(Object x : a)
					fill(x, val);
			}
			else if(arreglo instanceof int[])
				Arrays.fill((int[]) arreglo, val);
			else if(arreglo instanceof double[])
				Arrays.fill((double[]) arreglo, val);
			else if(arreglo instanceof long[])
				Arrays.fill((long[]) arreglo, val);
			return arreglo;
		}
		
		<T> T[] nextObjectArray(Class <T> clazz, int size)
		{
			@SuppressWarnings("unchecked")
			T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
			for(int c = 0; c < 3; c++)
			{
				Constructor <T> constructor;
				try 
				{
					if(c == 0)
						constructor = clazz.getDeclaredConstructor(Scanner.class, Integer.TYPE);
					else if(c == 1)
						constructor = clazz.getDeclaredConstructor(Scanner.class);
					else
						constructor = clazz.getDeclaredConstructor();
				} 
				catch(Exception e)
				{
					continue;
				}
				try
				{
					for(int i = 0; i < result.length; i++)
					{
						if(c == 0)
							result[i] = constructor.newInstance(this, i);
						else if(c == 1)
							result[i] = constructor.newInstance(this);
						else
							result[i] = constructor.newInstance();	
					}
				}
				catch(Exception e)
				{
					throw new RuntimeException(e);
				}
				return result;
			}
			throw new RuntimeException("Constructor not found");
		}
		
		public void printLine(int... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(long... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(double... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.print(vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.print(" ".concat(String.valueOf(vals[i])));
				System.out.println();
			}
		}
		
		public void printLine(int prec, double... vals)
		{
			if(vals.length == 0)
				System.out.println();
			else
			{
				System.out.printf("%." + prec + "f", vals[0]);
				for(int i = 1; i < vals.length; i++)
					System.out.printf(" %." + prec + "f", vals[i]);
				System.out.println();
			}
		}
		
		public Collection <Integer> wrap(int[] as)
		{
			ArrayList <Integer> ans = new ArrayList <Integer> ();
			for(int i : as)
				ans.add(i);
			return ans;
		}
		
		public int[] unwrap(Collection <Integer> collection)
		{
			int[] vals = new int[collection.size()];
			int index = 0;
			for(int i : collection) vals[index++] = i;
			return vals;
		}
	}


	static class point{
		double x,y;
		public point(double xx,double yy){
			x=xx;
			y=yy;
		}
		point sub(point a){
			return new point(x - a.x,y - a.y);
		}
		point add(point a){
			return new point(x + a.x,y + a.y);
		}
		double cross(point a){
			return x*a.y - a.x*y;
		}
		double norm(){
			return Math.sqrt(x*x + y*y);
		}
		public point rotate(double angle){
			//counterclockwise
			double xx=this.x*Math.cos(angle) - this.y*Math.sin(angle);
			double yy=this.x*Math.sin(angle) + this.y*Math.cos(angle);
			return new point(xx,yy);
		}
		public double dot(point o){
			return this.x*o.x + this.y*o.y;
		}
		public double angle(point o){
			return Math.acos(this.dot(o)/ (this.norm()*o.norm()) );
		}
		public point multbyscalar(double u) {
			return new point(u*x,u*y);
		}
	}
	
	static double eps=1e-7;
	static point first_point;
	
	static class cmp1 implements Comparator<point>{
		@Override
		public int compare(point f, point s) {
			if (Math.abs(f.x - s.x)<eps)
				return Double.compare(f.y,s.y);
			return Double.compare(f.x, s.x);
		}
	}
	
	static class cmp2 implements Comparator<point>{
		@Override
		public int compare(point f, point s) {
			double tmp=ccw(first_point,f,s);
			if (Math.abs(tmp)<eps){
				if (f.sub(first_point).norm() < s.sub(first_point).norm())
					return -1;
				else
					return 1;
			}
			return (tmp>eps)?1:-1;
		}
	}
	
	static ArrayList<point> convexhull(point[] in,int n){
		ArrayList<point> hull=new ArrayList<point>(n);
		Arrays.sort(in,0,n,new cmp1());
		first_point=in[0];
		Arrays.sort(in,1,n,new cmp2());
		if (n<=3){
			for(int i=0;i<n;i++)
				hull.add(in[i]);
			return hull;
		}
		hull.add(first_point);
		hull.add(in[1]);
		int top=1;
		int i=2;
		while(i<n){
			if (top>0 && ccw(hull.get(top-1),hull.get(top),in[i])>-1*eps){
				hull.remove(top);
				top=top-1;
			}
			else{
				top=top+1;
				hull.add(in[i]);
				i++;
			}
		}
		return hull;
	}
	
	static double ccw(point a,point b,point c)
	{
		return a.sub(b).cross(c.sub(b));
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int c = sc.nextInt();
		point[] points = new point[c];
		for(int i = 0; i < c; i++)
			points[i] = new point(sc.nextInt(), sc.nextInt());
		ArrayList <point> convex = convexhull(points, c);
		double best = 0;
		for(point p : convex)
			for(point p1 : convex)
				best = Math.max(best, p.sub(p1).norm());
		System.out.println(best);
	}
}