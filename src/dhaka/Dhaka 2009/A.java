import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;


public class A 
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
	}
	
	static LinkedList <int[]> lista = new LinkedList <int[]> ();
	
	static void meter(int valor, boolean inicio)
	{
		if(inicio)
			lista.addFirst(new int[]{valor, valor});
		else
			lista.addLast(new int[]{valor, valor});
	}
	
	static void sacar(int valor)
	{
		ListIterator<int[]> it = lista.listIterator();
		while(it.hasNext())
		{
			int[] actual = it.next();
			if(valor >= actual[0] && valor <= actual[1])
			{
				if(valor == actual[0])
				{
					actual[0]++;
					if(actual[0] > actual[1])
						it.remove();
					return;
				}
				else if(valor == actual[1])
				{
					actual[1]--;
					if(actual[0] > actual[1])
						it.remove();
					return;
				}
				else
				{
					int actualAnt = actual[1];
					actual[1] = valor - 1;
					int[] siguiente = new int[]{valor + 1, actualAnt};
					if(siguiente[0] <= siguiente[1])
						it.add(siguiente);
					return;
				}
			}
		}
	}
	
	public static void operacionUno()
	{
		int valor = lista.peek()[0];
		System.out.println(valor);
		sacar(valor);
		meter(valor, false);
	}
	
	public static void operacionDos(int valor)
	{
		sacar(valor);
		meter(valor, true);
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int caso = 1;
		while(true)
		{
			int a = sc.nextInt();
			int b = sc.nextInt();
			if(a == 0 && b == 0)
				return;
			lista.clear();
			lista.add(new int[]{1, a});
			System.out.println("Case " + caso++ + ":");
			for(int i = 0; i < b; i++)
			{
				String val = sc.next();
				if(val.charAt(0) == 'N')
					operacionUno();
				else
				{
					int valA = sc.nextInt();
					if(valA < 1 || valA > a)
						continue;
					else
						operacionDos(valA);
				}
			}
		}
	}
}