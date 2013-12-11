
public class Test 
{
	static long mulMod(long a, long b, long n) {
	    if(a == 0 || b <= (1L << 62) / a) return (a * b) % n;
	    long result = 0;    
	    if(a < b) { long tmp = a; a = b; b = tmp; }
	    for(; b != 0; b >>= 1) {
	        if((b & 1) != 0) {
	            result += a; if(result >= n) result -= n;
	        }
	        a <<= 1; if(a >= n) a -= n;
	    }
	    return result;
	}

	static long gcd(long u, long v) {
	    while(u != 0){v %= u; long tmp = u; u = v; v = tmp;} return v;
	}

	static long f(long x, long c, long n) {
	    long result = mulMod(x, x, n);
	    result += c; if(result >= n) result -= n;
	    return result;
	}

	static long pollardsRho(long n) {
	    for (int count = 0, c = 1;;) {
	        long x = 2, y = 2, pot = 1, lam = 1, d;
	        do {
	            ++count;
	            if(pot == lam) {x = y; pot <<= 1; lam = 0;}
	      y = f(y,c,n);
	      lam++;
	      d = gcd(x >= y ? x - y : y - x, n);
	        } while (d == 1);
	    if (d != n) return d; else c++;
	    }
	}

	static long powMod(long a, long exp, long n) {
	    long result = 1;
	    for(; exp != 0; exp >>= 1) {
	        if((exp & 1) != 0) {
	            result = mulMod(result, a, n);
	        }
	        a = mulMod(a, a, n);
	    }
	    return result;
	}

	static boolean primeTestMillerRabin(long n, long a) {
	    long d = n-1, x;
	    int s = 0, r;
	    while ((d & 1) == 0 && d != 0){ s++; d >>= 1;}
	    while (a >= n) a >>= 1;
	    x = powMod(a, d, n);
	    if (x != 1 && x != n-1) {
	        r = 1;
	        while (r <= s-1 && x != n-1) {
	            x = mulMod(x, x, n);
	            if (x == 1) return false; else r++;
	        }
	        if (x != n-1) return false;
	    }
	    return true;
	}

	static boolean isPrime(long n) {
	    if (n <= 1) return false;
	    if (n <= 3) return true;
	  if ((n & 1) == 0) return false;
	  return primeTestMillerRabin(n, 2) && primeTestMillerRabin(n, 3) 
	  && primeTestMillerRabin(n, 5) && primeTestMillerRabin(n, 7) 
	  && (n < 3215031751L || (primeTestMillerRabin(n, 11) 
	  && (n < 2152302898747L || (primeTestMillerRabin(n, 13) 
	  && (n < 3474749660383L || (primeTestMillerRabin(n, 17) 
	  && (n < 341550071728321L || (primeTestMillerRabin(n, 23)))
	  && (n < 3825123056546413051L || (primeTestMillerRabin(n, 29) &&
		  primeTestMillerRabin(n, 31) && primeTestMillerRabin(n, 37)))))))));
	}
	
	public static void main(String[] args)
	{
		System.out.println(isPrime(3825123056546413051L));
	}

}
