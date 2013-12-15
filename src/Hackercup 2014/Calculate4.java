import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.StringTokenizer;

public class Calculate4
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

        <T> T[] nextObjectArray(Class <T> clazz, int size)
        {
            @SuppressWarnings("unchecked")
            T[] result = (T[]) java.lang.reflect.Array.newInstance(clazz, size);
            for(int c = 0; c < 3; c++)
            {
                Constructor <T> constructor;
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

        public Collection <Integer> wrap(int[] as)
        {
            ArrayList <Integer> ans = new ArrayList <Integer>();
            for(int i : as)
                ans.add(i);
            return ans;
        }

        public int[] unwrap(Collection <Integer> collection)
        {
            int[] vals = new int[collection.size()];
            int index = 0;
            for(int i : collection)
                vals[index++] = i;
            return vals;
        }
    }

    public static class Bag
    {
        final int[] values;
        final int originalSize;
        final Random random = new Random();
        int size;

        public Bag(int size, int a, int b)
        {
            originalSize = size;
            this.size = size;
            values = new int[size];
            int currentCard = 1;
            for(int i = 0; i < size; i++)
            {
                while(currentCard == a || currentCard == b)
                    currentCard++;
                values[i] = currentCard++;
            }
        }

        public int nextRandom()
        {
            int index = random.nextInt(size);
            int returnValue = values[index];
            values[index] = values[--size];
            values[size] = returnValue;
            return returnValue;
        }

        public void clear()
        {
            size = originalSize;
        }
    }

    public static boolean simulatePlayer(Bag bag, int a, int b)
    {
        int playerA1 = bag.nextRandom();
        int playerA2 = bag.nextRandom();
        if(a + b < playerA1 + playerA2)
            return false;
        else if((a + b == playerA1 + playerA2) && Math.max(playerA1, playerA2) > Math.max(a, b))
            return false;
        else
            return true;
    }

    public static boolean simulate(Bag bag, int a, int b)
    {
        bag.clear();
        return simulatePlayer(bag, a, b) && simulatePlayer(bag, a, b) && simulatePlayer(bag, a, b);
    }

    public static double calculateMontecarlo(int n, int a, int b, long nSimulations)
    {
        Bag bag = new Bag(n - 2, a, b);
        long wins = 0;
        for(int i = 0; i < nSimulations; i++)
            if(simulate(bag, a, b))
                wins++;
        return BigDecimal.valueOf(wins).divide(BigDecimal.valueOf(nSimulations), 100, BigDecimal.ROUND_DOWN)
            .doubleValue();
    }

    static long startTime;
    static boolean hurry;

    public static boolean play(int n, int a, int b)
    {
        double firstAttempt = calculateMontecarlo(n, a, b, Math.max(100, hurry ? 0 : 10 * n));
        return firstAttempt >= (0.25d - 1e-6);
    }

    public static BigInteger factorial(int n)
    {
        if(n == 0)
            return BigInteger.ONE;
        return BigInteger.valueOf(n).multiply(factorial(n - 1));
    }

    public static long combinatorial(int n, int m)
    {
        return factorial(n).divide(factorial(n - m).multiply(factorial(m))).longValue();
    }

    public static boolean[][] fill(int n)
    {
        boolean[][] answer = new boolean[n + 1][n + 1];
        for(int i = 1; i <= n; i++)
        {
            for(int j = i + 1; j <= n; j++)
            {
                boolean result = play(n, i, j);
                answer[i][j] = answer[j][i] = result;
            }
            System.out.println(n + " " + i);
            hurry = (System.currentTimeMillis() - startTime) >= 15L * 60L * 60L * 1000L;
        }
        return answer;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        hurry = false;
        startTime = System.currentTimeMillis();
        boolean[][][] answers = new boolean[101][][];
        for(int i = 8; i <= 100; i++)
            answers[i] = fill(i);
        Scanner sc = new Scanner();
        while(true)
        {
            String linea = sc.nextLine();
            if(linea.equals("ya"))
                break;
        }
        // Up to here was precalculated before downloading input
        System.setIn(new FileInputStream(new File("Dropbox/inputA.txt")));
        System.setOut(new PrintStream(new File("Dropbox/inputA.answer")));
        sc = new Scanner();
        int t = sc.nextInt();
        for(int c = 1; c <= t; c++)
        {
            int n = sc.nextInt();
            int h = sc.nextInt();
            String answer = "";
            for(int i = 0; i < h; i++)
            {
                int a = sc.nextInt();
                int b = sc.nextInt();
                answer += answers[n][a][b] ? "B" : "F";
            }
            System.out.println("Case #" + c + ": " + answer);
        }
        System.out.flush();
    }
}