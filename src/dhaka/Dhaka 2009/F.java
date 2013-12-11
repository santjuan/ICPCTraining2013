import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;


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
	
	static int[] a;
	static int[] b;
	
	static long dp(int indiceIzq, int indiceDer, int izqA, int izqB, int derA, int derB)
	{
		if(indiceIzq == b.length)
		{
			if(indiceDer != -1)
				derB = -1;
			if(derA < 0 || derB > 0)
				return 0;
			else
				return 1;
		}
		if(dp[indiceIzq][indiceDer][izqA + 1][izqB + 1][derA + 1][derB + 1] != -1)
			return dp[indiceIzq][indiceDer][izqA + 1][izqB + 1][derA + 1][derB + 1];
		int currentAIzq = izqA == 0 ? a[indiceIzq] : 0;
		int currentADer = a[indiceDer];
		int currentBIzq = izqB == 0 ? b[indiceIzq] : 9;
		int currentBDer = b[indiceDer];
		boolean pusoAlguno = indiceDer != b.length - 1;
		long cuenta = 0;
		for(int currentNumber = currentAIzq; currentNumber <= currentBIzq; currentNumber++)
		{
			int nextIzqA;
			if(izqA > 0)
				nextIzqA = 1;
			else
				nextIzqA = currentNumber == currentAIzq ? 0 : 1;
			int nextIzqB;
			if(izqB < 0)
				nextIzqB = -1;
			else
				nextIzqB = currentNumber == currentBIzq ? 0 : -1;
			int nextDerA;
			if(derA > 0)
				nextDerA = currentNumber >= currentADer ? 1 : -1;
			else if(derA == 0)
				nextDerA = currentNumber == currentADer ? 0 : currentNumber < currentADer ? -1 : 1;
			else
				nextDerA = currentNumber <= currentADer ? -1 : 1;
			int nextDerB;
			if(derB > 0)
				nextDerB = currentNumber >= currentBDer ? 1 : -1;
			else if(derB == 0)
				nextDerB = currentNumber == currentBDer ? 0 : currentNumber < currentBDer ? -1 : 1;
			else
				nextDerB = currentNumber <= currentBDer ? -1 : 1;
			if(!pusoAlguno && currentNumber == 0)
			{
				nextDerA = derA;
				nextDerB = derB;
			}
			cuenta += dp(indiceIzq + 1, (pusoAlguno || (currentNumber != 0)) ? indiceDer - 1 : indiceDer, nextIzqA, nextIzqB, nextDerA, nextDerB);
		}
		return dp[indiceIzq][indiceDer][izqA + 1][izqB + 1][derA + 1][derB + 1] = cuenta;
	}
	
	static long[][][][][][] dp = new long[21][21][3][3][3][3];
	
	static String count(String aS, String bS)
	{
		char[] bC = bS.toCharArray();
		while(aS.length() != bC.length)
			aS = "0" + aS;
		char[] aC = aS.toCharArray();
		a = new int[aC.length];
		for(int i = 0; i < a.length; i++)
			a[i] = aC[i] - '0';
		b = new int[bC.length];
		for(int i = 0; i < b.length; i++)
			b[i] = bC[i] - '0';
		for(int i = 0; i <= b.length; i++)
			for(int j = 0; j <= b.length; j++)
				for(int k = 0; k < 3; k++)
					for(int l = 0; l < 3; l++)
						for(int m = 0; m < 3; m++)
							for(int n = 0; n < 3; n++)
								dp[i][j][k][l][m][n] = -1;
		long res = dp(0, b.length - 1, 0, 0, 0, 0);	
		BigInteger resB = BigInteger.valueOf(res);
		if(res < 0)
		{
			resB = BigInteger.valueOf(res >>> 1);
			resB = resB.shiftLeft(1);
			if((res & 1) == 1)
				resB = resB.add(BigInteger.ONE);
		}
		return resB.toString();
	}
	
	public static String test(String a, String b)
	{
		int aN = Integer.parseInt(a);
		int bN = Integer.parseInt(b);
		StringBuilder sb = new StringBuilder(20);
		int cuenta = 0;
		for(int i = aN; i <= bN; i++)
		{
			sb.setLength(0);
			sb.append(i);
			sb.reverse();
			String rN = sb.toString();
			while(rN.length() != 1 && rN.charAt(0) == '0')
				rN = rN.substring(1);
			int val = Integer.parseInt(rN);
			if(val >= aN && val <= bN)
				cuenta++;
		}
		return cuenta + "";
	}
	
	public static void main(String[] args)
	{
		Scanner sc = new Scanner();
		int t = sc.nextInt();
		for(int caso = 1; caso <= t; caso++)
			System.out.println("Case " + caso + ": " + count(sc.next(), sc.next()));
	}

}

