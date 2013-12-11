import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class JamC1 
{
	static class Posibilidad
	{
		int[] vals;
		HashMap <Long, Long> probabilidades = new HashMap <Long, Long> ();
		
		Posibilidad(int[] vals)
		{
			this.vals = vals.clone();
			HashMap <Long, Long> veces = new HashMap <Long, Long> ();
			int limite = 1 << vals.length;
			for(int i = 0; i < limite; i++)
			{
				long producto = 1;
				int indice = 0;
				int mascara = i;
				while(mascara != 0)
				{
					if((mascara & 1) == 1)
						producto *= vals[indice];
					mascara >>= 1;
					indice++;
				}
				if(!veces.containsKey(producto))
					veces.put(producto, 0L);
				veces.put(producto, veces.get(producto) + 1);
			}
			for(Map.Entry <Long, Long> e : veces.entrySet())
				probabilidades.put(e.getKey(), e.getValue());
		}
		
		long darProbabilidad(long p)
		{
			if(!probabilidades.containsKey(p))
				return 0;
			return probabilidades.get(p);
		}
		
		
		long veces()
		{
			long cnt = 1;
            for (int x = 2; x <= 8; x++) {
                int tt = 0;
                for (int i=0; i < 12; i++) if (vals[i] == x) tt++;
                cnt *= fact(tt);
            }
            return fact(12) / cnt;
		}
	}
	
	static Long[] dpFact = new Long[13];
	
	static long fact(long n) {
        return dpFact[(int) n] != null ? dpFact[(int) n] : (dpFact[(int) n] = n == 0 ? 1 : n * fact(n-1));
    }
	
	static ArrayList <Posibilidad> posibilidades = new ArrayList <Posibilidad> ();
	
	
	static void generar(int actual, int[] vals)
	{
		if(actual == vals.length)
		{
			posibilidades.add(new Posibilidad(vals));
		}
		else
		{
			for(int i = actual == 0 ? 2 : vals[actual - 1]; i <= 8; i++)
			{
				vals[actual] = i;
				generar(actual + 1, vals);
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		PrintStream original = System.out;
		System.setOut(new PrintStream("c-small2.out"));
		generar(0, new int[12]);
		original.println(posibilidades.size());
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int caso = 1; caso <= T; caso++)
		{
			System.out.println("Case #" + caso + ":");
			int R = sc.nextInt();
			sc.nextInt();
			sc.nextInt();
			int K = sc.nextInt();
			for(int i = 0; i < R; i++)
			{
				if(i % 100 == 0)
					original.println("voy en " + i);
				long[] ks = new long[K];
				for(int j = 0; j < K; j++)
					ks[j] = sc.nextLong();
				BigInteger mejor = BigInteger.valueOf(Long.MIN_VALUE);
				Posibilidad mejorP = null;
				for(Posibilidad p : posibilidades)
				{
					BigInteger acum = BigInteger.ONE;
					for(long v : ks)
					{
						long prob = p.darProbabilidad(v);
						acum = acum.multiply(BigInteger.valueOf(prob));
					}
					acum = acum.multiply(BigInteger.valueOf(p.veces()));
					if(acum.compareTo(mejor) > 0)
					{
						mejor = acum;
						mejorP = p;
					}
				}
				for(int v : mejorP.vals)
					System.out.print(v);
				System.out.println();
			}
		}
		sc.close();
		System.out.flush();
		System.out.close();
	}

}
