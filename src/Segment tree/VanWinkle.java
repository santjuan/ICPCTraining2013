import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;


public class VanWinkle
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
	}

	static class Nodo
	{
		int a, b;
		Nodo i;
		Nodo d;
		long suma = 0;
		long inicioAcum = Long.MIN_VALUE;
		int diffAcum = Integer.MIN_VALUE;
		boolean clearAcum = false;
		long sumaCalculada = Long.MIN_VALUE;
		
		public Nodo(int aa, int bb)
		{
			a = aa;
			b = bb;
			if(a == b)
				return;
			i = new Nodo(a, (a + b) / 2);
			d = new Nodo((a + b) / 2 + 1, b);
		}
		
		void pushAcum(int i, int j, long val, int diff, boolean clear)
		{
			if(clear)
			{
				suma = 0;
				inicioAcum = Long.MIN_VALUE;
				diffAcum = Integer.MIN_VALUE;
				clearAcum = false;
			}
			if(diffAcum == Integer.MIN_VALUE)
			{
				inicioAcum = val + (a - i) * diff;
				diffAcum = (int) diff;
				clearAcum = clear;
			}
			else
			{
				inicioAcum += val + (a - i) * diff;
				diffAcum += diff;
				clearAcum |= clear;
			}
			sumaCalculada = Long.MIN_VALUE;
		}
		
		long darSumaReal()
		{
			if(sumaCalculada != Long.MIN_VALUE)
				return sumaCalculada;
			if(diffAcum == Integer.MIN_VALUE)
				return sumaCalculada = suma;
			if(diffAcum == 0 && clearAcum)
				return sumaCalculada = inicioAcum * (b - a + 1);
			else if(diffAcum == 0)
				return sumaCalculada = suma + inicioAcum * (b - a + 1);
			long s = suma + inicioAcum * (b - a + 1);
			s += ((((long) (b - a + 1)) * ((long) (b - a))) >>> 1) * diffAcum;
			return sumaCalculada = s;
		}
		
		void propagar()
		{
			if(diffAcum == Integer.MIN_VALUE)
				return;
			if(i != null)
			{
				i.pushAcum(a, b, inicioAcum, diffAcum, clearAcum);
				d.pushAcum(a, b, inicioAcum, diffAcum, clearAcum);
			}
			suma = darSumaReal();
			diffAcum = Integer.MIN_VALUE;
			inicioAcum = Long.MIN_VALUE;
			clearAcum = false;
			sumaCalculada = Long.MIN_VALUE;
		}
		
		void update(int i, int j, long value, int diff, boolean clear)
		{
			if (i > b || j < a)
				return;
			if(diffAcum != Integer.MIN_VALUE)
				propagar();
			if (a >= i && b <= j)
			{
				pushAcum(i, j, value, diff, clear);
				return;
			}
			this.i.update(i, j, value, diff, clear);
			d.update(i, j, value, diff, clear);
			suma = this.i.darSumaReal() + d.darSumaReal();
			sumaCalculada = Long.MIN_VALUE;
		}
		
		long query(int i, int j)
		{
			if (i > b || j < a)
				return 0;
			if(diffAcum != Integer.MIN_VALUE)
				propagar();
			if (a >= i && b <= j)
				return suma;
			return this.i.query(i, j) + d.query(i, j);
		}
	}
	
//	static long[] data = new long[250001];
//	
//	static void A( int st, int nd ) {
//	    for( int i = st; i <= nd; i++ ) data[i] = data[i] + (i - st + 1);
//	}
//	
//	static void B( int st, int nd ) {
//	    for( int i = st; i <= nd; i++ ) data[i] = data[i] + (nd - i + 1);
//	}
//	
//	static void C( int st, int nd, int x ) {
//	    for( int i = st; i <= nd; i++ ) data[i] = x;
//	}
//	
//	static long S( int st, int nd ) {
//	    long res = 0;
//	    for( int i = st; i <= nd; i++ ) res += data[i];
//	    return res;
//	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
//		System.setIn(new FileInputStream("prueba.in"));
		Nodo raiz = new Nodo(0, 250001);
		Scanner sc = new Scanner();
//		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("salida.out")), false));
	    System.setOut(new PrintStream(new BufferedOutputStream(System.out), false));
		int t = sc.nextInt();
//		long tiempo = System.currentTimeMillis();
		for(int i = 0; i < t; i++)
		{
			char tipo = sc.next().charAt(0);
			if(tipo == 'A')
			{
				int st = sc.nextInt();
				int nd = sc.nextInt();
//				A(st, nd);
				raiz.update(st, nd, 1, 1, false);
			}
			else if(tipo == 'B')
			{
				int st = sc.nextInt();
				int nd = sc.nextInt();
//				B(st, nd);
				raiz.update(st, nd, nd - st + 1, -1, false);
			}
			else if(tipo == 'C')
			{
				int st = sc.nextInt();
				int nd = sc.nextInt();
				int x = sc.nextInt();
//				C(st, nd, x);
				raiz.update(st, nd, x, 0, true);
			}
			else
			{
				int st = sc.nextInt();
				int nd = sc.nextInt();
//				if(raiz.query(st, nd) != S(st, nd))
//				{
//					System.out.println(raiz.query(st, nd) + " " + S(st, nd));
//					throw(new RuntimeException("Fallo"));
//				}
				System.out.println(raiz.query(st, nd));
			}
		}
//		System.out.println(System.currentTimeMillis() - tiempo);
		System.out.flush();
	}
}