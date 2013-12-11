import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CodeD 
{
	static int n;
	
	@SuppressWarnings("unchecked")
	static HashMap <Integer, Long> [] dp = new HashMap[1 << 16];
	static long MOD = 1000000007;
	
	static HashMap <Integer, Long> dp(int mascara)
	{
		if(dp[mascara] != null)
			return dp[mascara];
		int indiceActual = Integer.bitCount(mascara);
		if(indiceActual == n)
		{
			HashMap <Integer, Long> ans = new HashMap <Integer, Long> ();
			ans.put(0, 1L);
			return dp[mascara] = ans;
		}
		else
		{
			HashMap <Integer, Long> answers = new HashMap <Integer, Long> ();
			for(int i = 0; i < n; i++)
			{
				if((mascara & (1 << i)) == 0)
				{
					int indiceC = (i + indiceActual) % n;
					int mascaraSig = 1 << indiceC;
					for(Map.Entry<Integer, Long> e : dp(mascara ^ (1 << i)).entrySet())
					{
						int v = e.getKey();
						if((v & mascaraSig) == 0)
						{
							int n = v ^ mascaraSig;
							if(!answers.containsKey(n))
								answers.put(n, 0L);
							answers.put(n, (answers.get(n) + e.getValue()) % MOD);
						}
					}
				}
			}
			return dp[mascara] = answers;
		}
	}

	static int[] ans = {0, 1, 0, 18, 0, 1800, 0, 670320, 0, 734832000, 0, 890786230, 0, 695720788, 0, 150347555, 0};
	
	
	public static void main(String[] args)
	{
//		long[] factorial = new long[17];
//		factorial[0] = 1L;
//		for(int i = 1; i < 17; i++)
//			factorial[i] = (factorial[i - 1] * i) % MOD;
//		for(int i = 0; i <= 16; i++)
//		{
//			if((i & 1) == 0)
//				System.out.print("0, ");
//			else
//			{
//				n = i;
//				dp = new HashMap[1 << n];
//				long ans = 0;
//				for(long v : dp(0).values())
//				{
//					ans += v;
//					ans %= MOD;
//				}
//				ans *= factorial[n];
//				ans %= MOD;
//				System.out.print(ans + ", ");
//			}
//		}
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println(ans[n]);
		sc.close();
	}
}
