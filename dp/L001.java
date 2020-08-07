import java.util.*;

public class L001 {
    public static void main(String[] args) {
        System.out.println(partition_into_k_subsets(3, 1, new int[6][3]));
    }

    public static void display1D(int[] arr) {
        for (int ele : arr)
            System.out.print(ele + " ");
        System.out.println();
    }

    public static void display2D(int[][] arr) {
        for (int[] ar : arr)
            display1D(ar);
        System.out.println();
    }

    public static int fibonacci(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;
        if (dp[n] != 0)
            return dp[n];
        int res = fibonacci(n - 1, dp) + fibonacci(n - 2, dp);
        return dp[n] = res;
    }

    public static int fibonacciDP(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }

            int res = dp[n - 1] + dp[n - 2];
            dp[n] = res;
        }
        return dp[N];
    }

    public static int fiboBtr(int n) {
        int a = 0;
        int b = 1;
        int sum = 0;
        if (n <= 1)
            return n;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }

        return b;
    }
    // mazepath series

    public static int mazepathHV(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec)
            return dp[sr][sc] = 1;
        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int count = 0;
        if (sr + 1 <= er)
            count += mazepathHV(sr + 1, sc, er, ec, dp);
        if (sc + 1 <= ec)
            count += mazepathHV(sr, sc + 1, er, ec, dp);
        return dp[sr][sc] = count;
    }

    public static int mazepathHV_DP(int sr, int sc, int er, int ec, int[][] dp) {
        for (sr = er; sr >= 0; sr--) {
            for (sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                if (sr + 1 <= er)
                    count += dp[sr + 1][sc];// mazepathHV(sr + 1, sc, er, ec, dp);
                if (sc + 1 <= ec)
                    count += dp[sr][sc + 1];// mazepathHV(sr, sc + 1, er, ec, dp);
                dp[sr][sc] = count;
            }
        }
        return dp[0][0];
    }

    public static int mazepathHVDmulti(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = 1;
        }

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        for (int jump = 1; sr + jump <= er; jump++)
            count += mazepathHVDmulti(sr + jump, sc, er, ec, dp);

        for (int jump = 1; sc + jump <= ec; jump++)
            count += mazepathHVDmulti(sr, sc + jump, er, ec, dp);

        for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
            count += mazepathHVDmulti(sr + jump, sc + jump, er, ec, dp);

        return dp[sr][sc] = count;
    }

    public static int mazepathHVDmulti_DP(int sr, int sc, int er, int ec, int[][] dp) {
        for (sr = er; sr >= 0; sr--) {
            for (sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;
                }

                int count = 0;
                for (int jump = 1; sr + jump <= er; jump++)
                    count += dp[sr + jump][sc];// mazepathHVDmulti_DP(sr + jump, sc, er, ec, dp);

                for (int jump = 1; sc + jump <= ec; jump++)
                    count += dp[sr][sc + jump];// mazepathHVDmulti_DP(sr, sc + jump, er, ec, dp);

                for (int jump = 1; sr + jump <= er && sc + jump <= ec; jump++)
                    count += dp[sr + jump][sc + jump];// mazepathHVDmulti_DP(sr + jump, sc + jump, er, ec, dp);

                dp[sr][sc] = count;
            }
        }
        return dp[0][0];
    }

    public static int boardpath(int sp, int ep, int[] dp) {
        if (sp == ep)
            return dp[sp] = 1;
        if (dp[sp] != 0)
            return dp[sp];
        int count = 0;
        for (int dice = 1; dice + sp <= ep && dice <= 6; dice++)
            count += boardpath(sp + dice, ep, dp);
        return dp[sp] = count;
    }

    public static int boardpath_DP(int sp, int ep, int[] dp) {
        for (sp = ep; sp >= 0; sp--) {
            if (sp == ep) {
                dp[sp] = 1;
            }
            int count = 0;
            for (int dice = 1; dice + sp <= ep && dice <= 6; dice++)
                count += dp[sp + dice];// boardpath(sp + dice, ep, dp);
            dp[sp] = count;
        }
        return dp[0];
    }

    public static int boardpath_best(int sp, int ep) {
        LinkedList<Integer> list = new LinkedList<>();

        for (sp = ep; sp >= 0; sp--) {
            if (sp > ep - 2) {
                list.addFirst(1);
                continue;
            }
            if (list.size() <= 6)
                list.addFirst(2 * list.getFirst());
            else {
                list.addFirst(2 * list.getFirst() - list.getLast());
                list.removeLast();
            }
        }
        return list.getFirst();
    }

    public static int boardPathWithDiceArrayDP(int sp, int ep, int[] dp, int[] diceArray) {

        for (sp = ep; sp >= 0; sp--) {
            if (sp == ep) {
                dp[sp] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 0; sp + diceArray[dice] <= ep && dice < diceArray.length; dice++)
                count += dp[sp + diceArray[dice]]; // boardPath(sp + dice, ep, dp);
            dp[sp] = count;
        }

        return dp[0];
    }

    // =======================leetcode 70=====================

    public int climbStairs(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = 1;
        if (dp[n] != 0)
            return dp[n];
        int count = climbStairs(n - 1, dp) + climbStairs(n - 2, dp);
        return dp[n] = count;
    }

    public static int climbStairs_DP(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {

            if (n <= 1) {
                dp[n] = 1;
                continue;
            }
            int count = dp[n - 1] + dp[n - 2];
            dp[n] = count;
        }
        return dp[N];
    }

    public static int mincostclimbingstairs(int[] cost, int n, int[] dp) {
        if (n <= 1)
            return dp[n] = cost[n];
        if (dp[n] != 0)
            return dp[n];
        int mcost = Math.min(mincostclimbingstairs(cost, n - 1, dp), mincostclimbingstairs(cost, n - 2, dp));
        return dp[n] = mcost + cost[n];
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        int[] cost_ = new int[n + 1];
        for (int i = 0; i < n; i++)
            cost_[i] = cost[i];
        return mincostclimbingstairs(cost_, n, dp);
    }

    public static int friends_pairing_problem(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = 1;
        if (dp[n] != 0)
            return dp[n];
        int single = friends_pairing_problem(n - 1, dp);
        int paired = friends_pairing_problem(n - 2, dp) * (n - 1);
        return dp[n] = (single + paired);
    }

    public static int friends_pairing_problem_DP(int n, int[] dp) {
        int N = n;
        for (n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = 1;
                continue;
            }
            int single = dp[n - 1];// friends_pairing_problem(n - 1, dp);
            int paired = dp[n - 2] * (n - 1);// friends_pairing_problem(n - 2, dp) * (n - 1);
            dp[n] = (single + paired);
        }
        return dp[N];
    }

    public static int min_sum_path(int sr, int sc, int[][] grid, int[][] dp) {
        if (sr == grid.length - 1 && sc == grid[0].length - 1) {
            return dp[sr][sc] = grid[sr][sc];
        }
        if (dp[sr][sc] != 0)
            return dp[sr][sc];
        int min = (int) 1e8;
        if ((sr + 1) <= grid.length - 1)
            min = Math.min(min, min_sum_path(sr + 1, sc, grid, dp));
        if (sc + 1 <= grid[0].length - 1)
            min = Math.min(min, min_sum_path(sr, sc + 1, grid, dp));
        return dp[sr][sc] = (min + grid[sr][sc]);
    }

    public static int min_sum_path_DP(int sr, int sc, int[][] grid, int[][] dp) {
        for (sr = grid.length - 1; sr >= 0; sr--) {
            for (sc = grid[0].length - 1; sc >= 0; sc--) {
                if (sr == grid.length - 1 && sc == grid[0].length - 1) {
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }

                int min = (int) 1e8;
                if ((sr + 1) <= grid.length - 1)
                    min = Math.min(min, dp[sr + 1][sc]);
                if (sc + 1 <= grid[0].length - 1)
                    min = Math.min(min, dp[sr][sc + 1]);
                dp[sr][sc] = (min + grid[sr][sc]);
            }
        }
        return dp[0][0];
    }

    public static int partition_into_k_subsets(int n, int k, int[][] dp) {
        if (n < k)
            return dp[n][k] = 0;
        if (n == k || k == 1)
            return dp[n][k] = 1;
        if (dp[n][k] != 0)
            return dp[n][k];
        int newSet = partition_into_k_subsets(n - 1, k - 1, dp);
        int existingSet = partition_into_k_subsets(n - 1, k, dp) * k;
        return dp[n][k] = (newSet + existingSet);
    }

    public static int partition_into_k_subsets_DP(int n, int k, int[][] dp) {
        int N = n;
        int K = k;
        for (n = 0; n <= N; n++) {
            for (k = 0; k <= K; k++) {
                if (n < k) {
                    dp[n][k] = 0;
                    continue;
                }
                if (n == k || k == 1) {
                    dp[n][k] = 1;
                    continue;
                }

                int newSet = dp[n - 1][k - 1];
                int existingSet = dp[n - 1][k] * k;
                dp[n][k] = (newSet + existingSet);
            }
        }
        return dp[N][K];
    }

    static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 }, { 0, 0 } };

    public static long mobilekeypadproblem(int sr, int sc, int length, long[][][] dp, char[][] keypad) {
        if (length == 1)
            return dp[sr][sc][length] = 1;

        if (dp[sr][sc][length] != 0)
            return dp[sr][sc][length];
        long count = 0;
        for (int i = 0; i < dir.length; i++) {
            int nrow = sr + dir[i][0];
            int ncol = sc + dir[i][1];

            if (nrow >= 0 && nrow <= 3 && ncol >= 0 && ncol <= 2 && keypad[nrow][ncol] != '*'
                    && keypad[nrow][ncol] != '#')
                count += mobilekeypadproblem(nrow, ncol, length - 1, dp, keypad);
        }
        return dp[sr][sc][length] = count;
    }
    // ==========================String questions===================

    // leetcode 72
    public int minDistance(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();
        return editdistance(word1, word2, n, m, new int[n + 1][m + 1]);
    }

    public static int editdistance(String word1, String word2, int n, int m, int[][] dp) {
        if (n == 0 || m == 0)
            return dp[n][m] = (m == 0 ? n : m);
        if (dp[n][m] != 0)
            return dp[n][m];

        if (word1.charAt(n - 1) == word2.charAt(m - 1))
            return dp[n][m] = editdistance(word1, word2, n - 1, m - 1, dp);
        int insert_ = editdistance(word1, word2, n, m - 1, dp) + 1;
        int delete_ = editdistance(word1, word2, n - 1, m, dp) + 1;
        int replace_ = editdistance(word1, word2, n - 1, m - 1, dp) + 1;
        return dp[n][m] = Math.min(insert_, Math.min(delete_, replace_));
    }

    public static int editdistanceDP(String word1, String word2, int n, int m, int[][] dp) {
        int N = n;
        int M = m;
        for (n = 0; n <= N; n++) {
            for (m = 0; m <= M; m++) {
                if (n == 0 || m == 0) {
                    dp[n][m] = (m == 0 ? n : m);
                    continue;
                }

                if (word1.charAt(n - 1) == word2.charAt(m - 1)) {
                    dp[n][m] = dp[n - 1][m - 1];
                    continue;
                }
                int insert_ = dp[n][m - 1] + 1;
                int delete_ = dp[n - 1][m] + 1;
                int replace_ = dp[n - 1][m - 1] + 1;
                dp[n][m] = Math.min(insert_, Math.min(delete_, replace_));
            }
        }
        return dp[N][M];
    }
}
