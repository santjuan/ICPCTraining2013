import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class E
{
	static class Scanner
	{
		static final boolean debug = false;
		static final String name = "medical";
		BufferedReader br;
		StringTokenizer st = new StringTokenizer("");
		PrintStream out;		
		
		Scanner()
		{
			if(debug)
			{
				br = new BufferedReader(new InputStreamReader(System.in));
				out = System.out;
			}
			else
			{
				try 
				{
					br = new BufferedReader(new FileReader(name + ".in"));
					out = new PrintStream(new BufferedOutputStream(new FileOutputStream(name + ".out")), false);
				}
				catch (FileNotFoundException e) 
				{
					throw(new RuntimeException());
				}
			}
		}
		
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
		
		public void println(String s)
		{
			out.println(s);
		}
		
		public void print(String s)
		{
			out.print(s);
		}
	}
	
	static class Paciente
	{
		int nPaciente;
		LinkedList <Integer> faltantes = new LinkedList <Integer> ();
		
		Paciente(int n)
		{
			nPaciente = n;
		}
	}
	
	static class Entrada implements Comparable <Entrada>
	{
		int tiempo;
		int cola;
		int id;
		Paciente paciente;
		
		Entrada(int tiempo, int cola, int id, Paciente paciente) 
		{
			this.tiempo = tiempo;
			this.cola = cola;
			this.id = id;
			this.paciente = paciente;
		}

		@Override
		public int compareTo(Entrada o)
		{
			if(tiempo != o.tiempo)
				return tiempo - o.tiempo;
			if(cola != o.cola)
				return cola - o.cola;
			return id - o.id;
		}
	}
	
	public static int simular(PriorityQueue <Entrada> pq, int m)
	{
		int maxTime = Integer.MIN_VALUE;
		int currentTime = -1;
		@SuppressWarnings("unchecked")
		LinkedList <Paciente> [] colas = new LinkedList[m];
		for(int i = 0; i < m; i++)
			colas[i] = new LinkedList <Paciente> ();
		int colasNoVacias = 0;
		while(!pq.isEmpty() || colasNoVacias != 0)
		{
			if(colasNoVacias == 0)
				currentTime = pq.peek().tiempo;
			else
				currentTime++;
			while(!pq.isEmpty() && pq.peek().tiempo == currentTime)
			{
				Entrada actual = pq.poll();
				LinkedList <Paciente> cola = colas[actual.cola];
				cola.addFirst(actual.paciente);
			}
			colasNoVacias = 0;
			for(int i = 0; i < m; i++)
			{
				if(colas[i].isEmpty())
					continue;
				Paciente actual = colas[i].pollLast();
				if(!colas[i].isEmpty())
					colasNoVacias++;
				actual.faltantes.pollFirst();
				if(actual.faltantes.isEmpty())
					maxTime = currentTime + 1;
				else
					pq.add(new Entrada(currentTime + 1, actual.faltantes.peekFirst(), actual.nPaciente, actual));
			}
		}
		return maxTime;
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int n = sc.nextInt();
		int m = sc.nextInt();
		PriorityQueue <Entrada> pq = new PriorityQueue <Entrada> ();
		for(int i = 0; i < n; i++)
		{
			Paciente actual = new Paciente(i);
			int tiempo = sc.nextInt();
			int k = sc.nextInt();
			for(int j = 0; j < k; j++)
				actual.faltantes.add(sc.nextInt() - 1);
			pq.add(new Entrada(tiempo, actual.faltantes.peek(), i, actual));
		}
		sc.println(simular(pq, m) + "");
		sc.out.flush();
	}
}