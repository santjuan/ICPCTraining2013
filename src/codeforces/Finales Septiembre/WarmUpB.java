
public class WarmUpB {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int cuenta = 0;
		for(long n = 1; n < 100000000; n++)
		{
			long val = -2;
			val += Math.round((Math.sqrt(4.0 + 8 * (n * (n + 1)))));
			if(isPerfectSquare(4 + 8 * (n * (n + 1))) && val % 4 == 0)
			{
				val /= 4;
				int k = (int) Math.round(val);
				if(k < n && k > 0)
				{
					System.out.println(n);
					cuenta++;
				}
			}
		}
		System.out.println(cuenta);
	}
	
	static boolean isPerfectSquare(long val)
	{
		long x = Math.round(Math.sqrt(val));
		return x * x == val;
	}

}
