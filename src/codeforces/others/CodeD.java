import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.StringTokenizer;

public class CodeD
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

    static int[][] normal;
    static int[][] integralImage;
    static int totalCount;

    static void generateIntegeralImage()
    {
        for(int i = 0; i < normal.length; i++)
        {
            int count = 0;
            for(int j = 0; j < normal[0].length; j++)
            {
                count += normal[i][j];
                integralImage[i][j] = ((i == 0) ? 0 : integralImage[i - 1][j]) + count;
            }
        }
    }

    static int count(int startI, int startJ, int size)
    {
        int endI = startI + size;
        int endJ = startJ + size;
        int topLeft = (startI == 0 || startJ == 0) ? 0 : integralImage[startI - 1][startJ - 1];
        int top = (startI == 0) ? 0 : integralImage[startI - 1][endJ];
        int bottom = (startJ == 0) ? 0 : integralImage[endI][startJ - 1];
        return integralImage[endI][endJ] - top - bottom + topLeft;
    }

    static int countInside(int startI, int startJ, int size)
    {
        if(size == 0 || size == 1)
            return 0;
        startI++;
        startJ++;
        size -= 2;
        return count(startI, startJ, size);
    }

    static int compare(int startI, int startJ, int size)
    {
        int all = count(startI, startJ, size);
        int inside = countInside(startI, startJ, size);
        int outside = totalCount - all;
        if(outside != 0)
            return -1;
        else if(inside != 0)
            return 1;
        return 0;
    }

    static int binarySearch(int startI, int startJ, int sizeI, int sizeJ)
    {
        if(sizeI > sizeJ)
            return -1;
        if(sizeI + 1 == sizeJ)
            return compare(startI, startJ, sizeI) == 0 ? sizeI : (compare(startI, startJ, sizeJ) == 0 ? sizeJ : -1);
        if(sizeI == sizeJ)
            return compare(startI, startJ, sizeI) == 0 ? sizeI : -1;
        int mid = (sizeI + sizeJ) >>> 1;
        int cmpMid = compare(startI, startJ, mid);
        if(cmpMid == 0)
            return binarySearch(startI, startJ, sizeI, mid);
        else if(cmpMid == -1)
            return binarySearch(startI, startJ, mid + 1, sizeJ);
        else
            return binarySearch(startI, startJ, sizeI, mid - 1);
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner();
        int n = sc.nextInt();
        int m = sc.nextInt();
        normal = new int[n][m];
        integralImage = new int[n][m];
        for(int i = 0; i < n; i++)
        {
            char[] val = sc.next().toCharArray();
            for(int j = 0; j < m; j++)
            {
                normal[i][j] = val[j] == 'w' ? 1 : 0;
                totalCount += normal[i][j];
            }
        }
        generateIntegeralImage();
        int bestI = -1;
        int bestJ = -1;
        int bestSize = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
            {
                int maxSize = Math.min(n - i, m - j) - 1;
                if(compare(i, j, maxSize) == -1)
                    continue;
                int possible = binarySearch(i, j, 0, maxSize);
                if(possible != -1)
                    if(possible < bestSize)
                    {
                        bestI = i;
                        bestJ = j;
                        bestSize = possible;
                    }
            }
        if(bestSize == Integer.MAX_VALUE)
        {
            System.out.println(-1);
            return;
        }
        char[][] ans = new char[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
            {
                if(normal[i][j] == 1)
                    ans[i][j] = 'w';
                else
                    ans[i][j] = '.';
            }
        for(int i = bestI; i <= bestI + bestSize; i++)
        {
            int posI = i;
            int posJ = bestJ;
            if(ans[posI][posJ] != 'w')
                ans[posI][posJ] = '+';
        }
        for(int i = bestI; i <= bestI + bestSize; i++)
        {
            int posI = i;
            int posJ = bestJ + bestSize;
            if(ans[posI][posJ] != 'w')
                ans[posI][posJ] = '+';
        }
        for(int j = bestJ; j <= bestJ + bestSize; j++)
        {
            int posI = bestI;
            int posJ = j;
            if(ans[posI][posJ] != 'w')
                ans[posI][posJ] = '+';
        }
        for(int j = bestJ; j <= bestJ + bestSize; j++)
        {
            int posI = bestI + bestSize;
            int posJ = j;
            if(ans[posI][posJ] != 'w')
                ans[posI][posJ] = '+';
        }
        for(char[] c : ans)
            System.out.println(new String(c));
    }
}