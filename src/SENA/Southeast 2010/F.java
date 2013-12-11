import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class F 
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
	
	@SuppressWarnings("unchecked")
	static TreeSet <Long> [] palindromes = new TreeSet[10];
	
	public static void generar()
	{
		palindromes[1] = new TreeSet <Long> ();
		for(int i = 0; i < 10; i++)
			palindromes[1].add((long) i);
		StringBuilder sb = new StringBuilder();
		StringBuilder sb1 = new StringBuilder();	
		for(int nDigitos = 2; nDigitos < 10; nDigitos++)
		{
			palindromes[nDigitos] = new TreeSet <Long> ();
			int mitad = nDigitos >> 1;
			for(int i = 0; true; i++)
			{
				String esteS = i + "";
				if(esteS.length() > mitad)
					break;
				sb.setLength(0);
				for(int j = 0; j < mitad - esteS.length(); j++)
					sb.append('0');
				sb.append(esteS);
				sb1.setLength(0);
				sb1.append(sb);
				sb1.reverse();
				if((nDigitos & 1) == 1)
				{
					for(int j = 0; j < 10; j++)
					{
						String val = sb1.toString() + ((char) ('0' + j)) + sb.toString();
						while(val.length() != 1 && val.charAt(0) == '0')
							val = val.substring(1);
						palindromes[nDigitos].add(Long.parseLong(val));
					}
				}
				else
				{
					String val = sb1.toString() + sb.toString();
					while(val.length() != 1 && val.charAt(0) == '0')
						val = val.substring(1);
					palindromes[nDigitos].add(Long.parseLong(val));
				}
			}
		}
	}
	
	static long procesar(String numero)
	{
		int nDigitos = numero.length();
		while(numero.length() != 1 && numero.charAt(0) == '0')
			numero = numero.substring(1);
		long numeroLong = Long.parseLong(numero);
		Long siguiente = palindromes[nDigitos].ceiling(numeroLong);
		if(siguiente == null)
			siguiente = 0L;
		if(siguiente < numeroLong)
		{
			long pow = (long) Math.pow(10, nDigitos);
			return pow - numeroLong;
		}
		return siguiente - numeroLong;
	}
	
	public static void main(String[] args)
	{
		generar();
		Scanner sc = new Scanner();
		while(true)
		{
			String val = sc.next();
			if(val.equals("0"))
				return;
			System.out.println(procesar(val));
		}
	}

}
