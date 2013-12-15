import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

public class FacebookD
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

        public Integer nextInteger()
        {
            String s = next();
            if(s == null)
                return null;
            return Integer.parseInt(s);
        }

        public int[][] nextIntMatrix(int n, int m)
        {
            int[][] ans = new int[n][];
            for(int i = 0; i < n; i++)
                ans[i] = nextIntArray(m);
            return ans;
        }

        public char[][] nextGrid(int r)
        {
            char[][] grid = new char[r][];
            for(int i = 0; i < r; i++)
                grid[i] = next().toCharArray();
            return grid;
        }

        public static <T> T fill(T arreglo, int val)
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

        <T> T[] nextObjectArray(Class<T> clazz, int size)
        {
            @SuppressWarnings("unchecked")
            T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
            for(int c = 0; c < 3; c++)
            {
                Constructor<T> constructor;
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

        public void printLine(int... vals)
        {
            if(vals.length == 0)
                System.out.println();
            else
            {
                System.out.print(vals[0]);
                for(int i = 1; i < vals.length; i++)
                    System.out.print(" ".concat(String.valueOf(vals[i])));
                System.out.println();
            }
        }

        public void printLine(long... vals)
        {
            if(vals.length == 0)
                System.out.println();
            else
            {
                System.out.print(vals[0]);
                for(int i = 1; i < vals.length; i++)
                    System.out.print(" ".concat(String.valueOf(vals[i])));
                System.out.println();
            }
        }

        public void printLine(double... vals)
        {
            if(vals.length == 0)
                System.out.println();
            else
            {
                System.out.print(vals[0]);
                for(int i = 1; i < vals.length; i++)
                    System.out.print(" ".concat(String.valueOf(vals[i])));
                System.out.println();
            }
        }

        public void printLine(int prec, double... vals)
        {
            if(vals.length == 0)
                System.out.println();
            else
            {
                System.out.printf("%." + prec + "f", vals[0]);
                for(int i = 1; i < vals.length; i++)
                    System.out.printf(" %." + prec + "f", vals[i]);
                System.out.println();
            }
        }

        public Collection<Integer> wrap(int[] as)
        {
            ArrayList<Integer> ans = new ArrayList<Integer>();
            for(int i : as)
                ans.add(i);
            return ans;
        }

        public int[] unwrap(Collection<Integer> collection)
        {
            int[] vals = new int[collection.size()];
            int index = 0;
            for(int i : collection)
                vals[index++] = i;
            return vals;
        }
    }

    static final boolean[] isFreePrime = new boolean[150];
    static final int[] freePrimes = new int[] { 0, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149 };
    static int[] primeMasks = new int[150];

    static
    {
        for(int i : freePrimes)
            if(i != 0)
                isFreePrime[i] = true;
        for(int i = 2; i < 150; i++)
        {
            int index = 0;
            int mask = 0;
            for(int p : new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73 })
            {
                if((i % p) == 0)
                    mask |= 1 << index;
                index++;
            }
            primeMasks[i] = mask;
        }
    }

    static int[] values;

    static int getHash(int mask, int current, int lastPrime)
    {
        return mask | (lastPrime << 21) | (current << 26);
    }

    static final HashMap<Integer, Integer> dp = new HashMap<Integer, Integer>();
    static int bestKnown;

    static int[][] dp2;

    static int dp2(int from, int moreOrEqual)
    {
        if(dp2[from][moreOrEqual] != -1)
            return dp2[from][moreOrEqual];
        int total = 0;
        for(int i = from; i < values.length; i++)
        {
            if(values[i] <= moreOrEqual)
                total += moreOrEqual - values[i];
        }
        return dp2[from][moreOrEqual] = total;
    }

    static int dp(int mask, int current, int lastPrime, int partial)
    {
        if(partial >= bestKnown)
            return 1000000;
        if(current == values.length)
        {
            bestKnown = Math.min(bestKnown, partial);
            return 0;
        }
        int hash = getHash(mask, current, lastPrime);
        if(dp.containsKey(hash))
        {
            int next = dp.get(hash);
            bestKnown = Math.min(bestKnown, partial + next);
            return next;
        }
        int best = Integer.MAX_VALUE;
        for(int i = values[current]; i < 150; i++)
        {
            if(partial + dp2(current, i) >= bestKnown)
                break;
            if(!isFreePrime[i] && ((primeMasks[i] & mask) == 0))
            {
                int diff = i - values[current];
                best = Math.min(best, diff + dp(mask | primeMasks[i], current + 1, lastPrime, partial + diff));
            }
        }
        if(lastPrime + 1 < freePrimes.length && values[current] <= freePrimes[lastPrime + 1])
        {
            int diff = freePrimes[lastPrime + 1] - values[current];
            best = Math.min(best, diff + dp(mask, current + 1, lastPrime + 1, partial + diff));
        }
        dp.put(hash, best);
        return best;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner();
        int t = sc.nextInt();
        for(int c = 1; c <= t; c++)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();
            values = sc.nextIntArray(n);
            int total = 0;
            boolean zero = false;
            boolean moreThanK = false;
            for(int i : values)
                if(i > k)
                    moreThanK = true;
            for(int i = 0; i < values.length; i++)
            {
                while((values[i] % k) != 0)
                {
                    total++;
                    values[i]++;
                }
                values[i] /= k;
                if(values[i] == 0)
                {
                    if(moreThanK)
                    {
                        values[i]++;
                        total += k;
                    }
                    else
                    {
                        if(!zero)
                            zero = true;
                        else
                        {
                            values[i]++;
                            total += k;
                        }
                    }
                }
            }
            Arrays.sort(values);
            dp.clear();
            dp2 = Scanner.fill(new int[150][200], -1);
            bestKnown = Integer.MAX_VALUE;
            total += dp(0, 0, 0, 0) * k;
            System.out.println("Case #" + c + ": " + total);
        }
    }
}