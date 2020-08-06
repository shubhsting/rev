import java.util.*;

public class L002 {
    public static void main(String[] args) {

    }

    public static ArrayList<String> mazepath_1(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns = new ArrayList<>();
        if (sr + 1 <= er) {
            ArrayList<String> list = mazepath_1(sr + 1, sc, er, ec);
            for (String ele : list)
                myAns.add("H" + ele);
        }
        if (sc + 1 <= ec) {
            ArrayList<String> list = mazepath_1(sr, sc + 1, er, ec);
            for (String ele : list)
                myAns.add("H" + ele);
        }
        return myAns;
    }

    public static ArrayList<String> mazepathHVD_1(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns = new ArrayList<>();
        if (sr + 1 <= er) {
            ArrayList<String> list = mazepathHVD_1(sr + 1, sc, er, ec);
            for (String ele : list)
                myAns.add("H" + ele);
        }
        if (sc + 1 <= ec) {
            ArrayList<String> list = mazepathHVD_1(sr, sc + 1, er, ec);
            for (String ele : list)
                myAns.add("H" + ele);
        }

        if (sc + 1 <= ec && sr + 1 <= er) {
            ArrayList<String> list = mazepathHVD_1(sr + 1, sc + 1, er, ec);
            for (String ele : list)
                myAns.add("D" + ele);
        }
        return myAns;
    }

    public static ArrayList<String> mazepathmultiHVD_1(int sr, int sc, int er, int ec) {
        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        ArrayList<String> myAns = new ArrayList<>();
        for (int jump = 1; jump + sr <= er; jump++) {
            ArrayList<String> list = mazepathmultiHVD_1(sr + 1, sc, er, ec);
            for (String ele : list)
                myAns.add("H" + ele);
        }
        for (int jump = 1; jump + sc <= ec; jump++) {
            ArrayList<String> list = mazepathmultiHVD_1(sr, sc + 1, er, ec);
            for (String ele : list)
                myAns.add("H" + ele);
        }

        for (int jump = 1; jump + sr <= er && jump + sc <= ec; jump++) {
            ArrayList<String> list = mazepathmultiHVD_1(sr + 1, sc + 1, er, ec);
            for (String ele : list)
                myAns.add("D" + ele);
        }
        return myAns;
    }

    public static int mazepath_HV_02(int sr, int sc, int er, int ec, String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        if (sc + 1 <= ec)
            count += mazepath_HV_02(sr, sc + 1, er, ec, ans + "H");
        if (sr + 1 <= er)
            count += mazepath_HV_02(sr + 1, sc, er, ec, ans + "V");

        return count;
    }

    public static int mazepath_HVD_02(int sr, int sc, int er, int ec, String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        if (sc + 1 <= ec)
            count += mazepath_HVD_02(sr + 0, sc + 1, er, ec, ans + "H");
        if (sc + 1 <= ec && sr + 1 <= er)
            count += mazepath_HVD_02(sr + 1, sc + 1, er, ec, ans + "D");
        if (sr + 1 <= er)
            count += mazepath_HVD_02(sr + 1, sc + 0, er, ec, ans + "V");

        return count;
    }

    public static int mazepath_multiHVD_02(int sr, int sc, int er, int ec, String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }

        int count = 0;
        for (int jump = 1; sc + jump <= ec; jump++)
            count += mazepath_multiHVD_02(sr + jump * 0, sc + jump * 1, er, ec, ans + "H" + jump);

        for (int jump = 1; sc + jump <= ec && sr + jump <= er; jump++)
            count += mazepath_multiHVD_02(sr + jump * 1, sc + jump * 1, er, ec, ans + "D" + jump);

        for (int jump = 1; sr + jump <= er; jump++)
            count += mazepath_multiHVD_02(sr + jump, sc, er, ec, ans + "V" + jump);

        return count;
    }

    static int[][] dir = { { 0, 1 }, { -1, 1 }, { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
    static String[] dirN = { "R", "E", "U", "N", "L", "W", "D", "S" };

    public static boolean isValid(int r, int c, int[][] board) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] == 2)
            return false;
        return true;
    }

    public static int floodfill(int sc, int sr, int ec, int er, int rad, int[][] board, String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return 1;
        }
        board[sr][sc] = 2;
        int count = 0;
        for (int i = 0; i < dir.length; i++) {
            for (int jump = 1; jump <= rad; jump++) {
                int x = sr + jump * dir[i][0];
                int y = sc + jump * dir[i][1];
                if (isValid(x, y, board))
                    count += floodfill(x, y, ec, er, rad, board, ans + dirN[i]);
            }
        }
        board[sr][sc] = 1;
        return count;
    }

    public List<List<Integer>> subsets(int[] nums) {
        return subsets_(nums, 0);
    }

    public List<List<Integer>> subsets_(int[] nums, int idx) {
        if (idx == nums.length) {
            List<List<Integer>> base = new ArrayList<>();
            base.add(new ArrayList<>());
            return base;
        }
        List<List<Integer>> myAns = new ArrayList<>();
        List<List<Integer>> subprob = subsets_(nums, idx + 1);
        for (List<Integer> lst : subprob) {
            myAns.add(lst);
            ArrayList<Integer> np = new ArrayList<>(lst);
            np.add(nums[idx]);
            myAns.add(np);
        }
        return myAns;
    }

    public static int combinationQueen1D(boolean[] boxes, int qpsf, int tnq, int idx, String ans) {
        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < boxes.length; i++)
            count += combinationQueen1D(boxes, qpsf + 1, tnq, idx, ans + "{" + i + "Q" + qpsf + "}");
        return count;
    }

    public static int permutationQueen1D(boolean[] boxes, int qpsf, int tnq, int idx, String ans) {
        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < boxes.length; i++) {
            if (!boxes[i]) {
                boxes[i] = true;
                count += permutationQueen1D(boxes, qpsf + 1, tnq, idx, ans + "{" + i + "Q" + qpsf + "}");
                boxes[i] = false;
            }
        }
        return count;
    }

    public static int permutationQueen1D_sub(boolean[] boxes, int qpsf, int tnq, int idx, String ans) {
        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;

        if (!boxes[idx]) {
            boxes[idx] = true;
            count += permutationQueen1D_sub(boxes, qpsf + 1, tnq, 0, ans + "{" + idx + "Q" + qpsf + "}");
            boxes[idx] = false;
        }
        count += permutationQueen1D_sub(boxes, qpsf, tnq, idx + 1, ans);
        return count;
    }

    public static int combinationQueen2D(int[][] boxes, int qpsf, int tnq, int idx, String ans) {
        if (qpsf == tnq) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        for (int i = 0; i < boxes.length * boxes[0].length; i++) {
            int r = i / boxes[0].length;
            int c = i % boxes[0].length;
            count += combinationQueen2D(boxes, qpsf + 1, tnq, i + 1, ans + "(" + r + ", " + c + ") ");
        }
        return count;
    }

}
