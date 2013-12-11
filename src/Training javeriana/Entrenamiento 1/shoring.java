import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class shoring 
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
		
		static <T> T fill(T arreglo, int val)
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
	}
	
	static class Polygon implements Comparable <Polygon>
	{
		ArrayList <Line2D> lines;
		Point2D anterior = null;
		
		public Polygon(int minSize)
		{
			lines = new ArrayList <Line2D> (minSize); 
		}
		
		public void add(Point2D nuevo)
		{
			if(anterior != null)
			{
				lines.add(new Line2D.Double(anterior, nuevo));
			}
			anterior = nuevo;
		}
		
		public void close()
		{
			lines.add(new Line2D.Double(anterior, lines.get(0).getP1()));
		}
		
		public static Point2D subtract(Point2D a, Point2D b)
		{
			return new Point2D.Double(a.getX() - b.getX(), a.getY() - b.getY());
		}
		
		static double abs(Point2D a)
		{
			return Math.sqrt(a.getX() * a.getX() + a.getY() * a.getY());
		}
		
		static double cross(Point2D a, Point2D b) 
		{
			return (a.getX() * b.getY()) - (a.getY() * b.getX());
		}
		
		static boolean is_point_online(Point2D a, Point2D b, Point2D c)
		{
			return abs(subtract(a, c)) + abs(subtract(b, c)) <= abs(subtract(a, b));
		}

		// Ya esta implementado en ((Line2D) lineA).intersectsLine(lineB)
		public static boolean lines_intersect(Line2D a, Line2D b)
		{
			return cross(subtract(a.getP2(), a.getP1()), subtract(b.getP1(), a.getP1())) *
				   cross(subtract(a.getP2(), a.getP1()), subtract(b.getP2(), a.getP1())) < 0 && 
				   cross(subtract(b.getP2(), b.getP1()), subtract(a.getP1(), b.getP1())) *
				   cross(subtract(b.getP2(), b.getP1()), subtract(a.getP2(), b.getP1())) < 0;
		}
		
		// Funciona para cualquier poligono excepto si es self-intersecting
		public double area() 
		{
			double area = 0;
			for (Line2D line : lines) 
			{
				area += line.getP1().getX() * line.getP2().getY();
				area -= line.getP2().getX() * line.getP1().getY();
			}
			area /= 2.0;
			return Math.abs(area);
		}
		
		public String areaEspecial()
		{
			return String.format("%.3f", area());
		}
		
		public double perimeter()
		{
			double total = 0;
			for(Line2D line : lines)
				total += line.getP2().distance(line.getP1());
			return total;
		}

		// Funciona para cualquier poligono excepto si es self-intersecting
		public double areaUnsigned() 
		{
			double area = 0;
			for (Line2D line : lines) 
			{
				area += line.getP1().getX() * line.getP2().getY();
				area -= line.getP2().getX() * line.getP1().getY();
			}
			area /= 2.0;
			return area;
		}

		// Funciona para cualquier poligono excepto si es self-intersecting
		public Point2D centerOfMass() 
		{
			double cx = 0, cy = 0;
			double area = areaUnsigned();
			double factor = 0;
			for (Line2D line : lines) 
			{
				factor = line.getP1().getX() * line.getP2().getY() - line.getP2().getX() * line.getP1().getY();
				cx += (line.getP1().getX() + line.getP2().getX()) * factor;
				cy += (line.getP1().getY() + line.getP2().getY()) * factor;
			}
			area *= 6.0d;
			factor = 1 / area;
			cx *= factor;
			cy *= factor;
			return new Point2D.Double(cx, cy);
		}
		
		public boolean intersects(Polygon other)
		{
			for(Line2D lineA : lines)
			{
				if(other.contains(lineA.getP1()))
					return true;
				for(Line2D lineB : other.lines)
				{
					if(lineA.intersectsLine(lineB))
						return true;
				}
			}
			for(Line2D line : other.lines)
			{
				if(contains(line.getP1()))
					return true;
			}
			return false;
		}
		
		public boolean contains(Point2D p)
		{
			  int cnt = 0;
			  for(Line2D line : lines)
			  {
			    Point2D curr = subtract(line.getP1(), p);
			    Point2D next = subtract(line.getP2(), p);
			    if(curr.getY() > next.getY())
			    {
			    	Point2D temp = curr;
			    	curr = next;
			    	next = temp;
			    }
			    if (curr.getY() < 0 && 0 <= next.getY() && cross(next, curr) >= 0)
			    {
			    	cnt++;
			    }
			    if (is_point_online(line.getP1(), line.getP2(), p))
			    		return true;
			  }
			  return  cnt % 2 == 1;
		}

		@Override
		public int compareTo(Polygon o)
		{
			if(areaEspecial().equals(o.areaEspecial()))
				return Double.valueOf(o.perimeter()).compareTo(perimeter());
			return Double.valueOf(o.area()).compareTo(area());
		}
	}
	
	static double EPS = 1e-9;
	
	static boolean point_in_box(double x, double y,
			  double x0, double y0,
			  double x1, double y1){
			  return 
			    Math.min(x0, x1) <= x && x <= Math.max(x0, x1) &&
			    Math.min(y0, y1) <= y && y <= Math.max(y0, y1);
			}
	

	static Point2D.Double segment_segment_intersection(double x0, double y0,
			double x1, double y1,
			double x2, double y2,
			double x3, double y3){
			  double t0 = (y3-y2)*(x0-x2)-(x3-x2)*(y0-y2);
			  double t1 = (x1-x0)*(y2-y0)-(y1-y0)*(x2-x0);
			  double det = (y1-y0)*(x3-x2)-(y3-y2)*(x1-x0);
			  if (Math.abs(det) < EPS){
			    //parallel
			    if (Math.abs(t0) < EPS || Math.abs(t1) < EPS){
			      //they lie on same line, but they may or may not intersect.
			      return null;
			    }else{
			      //just parallel, no intersection
			      return null;
			    }
			  }else{
			    t0 /= det;
			    t1 /= det;
			    /*
			    0 <= t0 <= 1 iff the intersection point lies in segment 1.
			    0 <= t1 <= 1 iff the intersection point lies in segment 2.
			    */
			      double x = x0 + t0*(x1-x0);
			      double y = y0 + t0*(y1-y0);
			      //intersection is point (x, y)
			      return new Point2D.Double(x, y);
			  }
			}

	
	static Polygon generar(Point2D p1, Point2D p2, Point2D p3)
	{
		Polygon p = new Polygon(3);
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.close();
		return p;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		while(true)
		{
			double[] vals = sc.nextDoubleArray(8);
			if(Arrays.equals(vals, new double[8])) return;
			Point2D.Double p1 = new Point2D.Double(vals[0], vals[1]);
			Point2D.Double p2 = new Point2D.Double(vals[2], vals[3]);
			Point2D.Double p3 = new Point2D.Double(vals[4], vals[5]);
			Point2D.Double p4 = new Point2D.Double(vals[6], vals[7]);
			Point2D.Double mitad = segment_segment_intersection(p1.x, p1.y, p3.x, p3.y, p2.x, p2.y, p4.x, p4.y);
			ArrayList <Polygon> poligonos = new ArrayList <Polygon> ();
			poligonos.add(generar(p1, mitad, p2));
			poligonos.add(generar(p1, mitad, p4));
			poligonos.add(generar(p3, mitad, p4));
			poligonos.add(generar(p2, mitad, p3));
			Collections.sort(poligonos);
			boolean empezo = false;
			for(Polygon p : poligonos)
			{
				if(empezo)
					System.out.print(" ");
				empezo = true;
				System.out.print(p.areaEspecial());
				System.out.print(" ");
				System.out.print(String.format("%.3f", p.perimeter()));
			}
			System.out.println();	
		}
	}
}