import java.util.*;

public class L001 {
    public static void main(String[] args) {
        System.out.println(nokiaKeyPad_02("10", ""));
    }

    public static int fact(int num) {
        return num <= 1 ? 1 : fact(num - 1) * num;
    }

    public static int power(int num, int pow) {
        return pow == 0 ? 1 : power(num, pow - 1) * num;
    }

    public static int power_btr(int num, int pow) {
        if (pow == 0)
            return 1;
        int smallAns = power_btr(num, pow / 2);
        smallAns *= smallAns;
        return pow % 2 == 0 ? smallAns : smallAns * num;
    }

    public static void display(int[] arr, int idx) {
        if (idx == arr.length)
            return;
        System.out.println(arr[idx]);
        display(arr, idx + 1);
    }

    public static boolean find(int[] arr, int num, int idx) {
        if (idx == arr.length)
            return false;
        if (arr[idx] == num)
            return true;
        boolean res = false;
        res = res || find(arr, num, idx + 1);
        return res;
    }

    public static int maximum(int[] arr, int idx) {
        if (idx == arr.length - 1)
            return arr[idx];
        int maxi = maximum(arr, idx + 1);
        return Math.max(maxi, arr[idx]);
    }

    public static int minimum(int[] arr, int idx) {
        if (idx == arr.length - 1)
            return arr[idx];
        int maxi = minimum(arr, idx + 1);
        return Math.min(maxi, arr[idx]);
    }

    public static ArrayList<String> subseqret(String ques) {
        if (ques.length() == 0)
            return new ArrayList<>();
        String nques = ques.substring(1);
        char ch = ques.charAt(0);
        ArrayList<String> retur = new ArrayList<>();
        ArrayList<String> smallAns = subseqret(nques);
        for (String str : smallAns) {
            retur.add(str);
            retur.add(str + ch);
        }
        return retur;
    }

    public static int subseqvoid(String ques, String ans) {
        if (ques.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        return subseqvoid(ques.substring(1), ans + ques.charAt(0)) + subseqvoid(ques.substring(1), ans);
    }

    public static ArrayList<String> perwith_dupli(String ques) {
        if (ques.length() == 1) {
            ArrayList<String> base = new ArrayList<>();
            base.add(ques);
            return base;
        }
        String nques = ques.substring(1);
        char ch = ques.charAt(0);
        ArrayList<String> retur = new ArrayList<>();
        ArrayList<String> smallAns = perwith_dupli(nques);
        for (String str : smallAns) {
            for (int i = 0; i <= str.length(); i++)
                retur.add(str.substring(0, i) + ch + str.substring(i));
        }
        return retur;
    }

    public static int perwithdupli_02(String ques, String ans) {
        if (ques.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;

        for (int i = 0; i < ques.length(); i++) {
            char ch = ques.charAt(i);
            count += perwithdupli_02(ques.substring(0, i) + ques.substring(i), ans + ch);
        }
        return count;
    }

    public static int permuwithoutdupli(String ques, String ans) {
        if (ques.length() == 0) {
            System.out.println(ans);
            return 1;
        }
        int count = 0;
        boolean[] vis = new boolean[26];
        for (int i = 0; i < ques.length(); i++) {
            char ch = ques.charAt(i);
            if (!vis[ch - 'a']) {
                vis[ch - 'a'] = true;
                count += perwithdupli_02(ques.substring(0, i) + ques.substring(i), ans + ch);
            }
        }
        return count;
    }

    static String[] words = { ":;/", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz", "&*%", "#@$", };

    // single index
    public static ArrayList<String> nokiaKeyPad_01(String ques) {
        if (ques.length() == 0) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }
        int idx = ques.charAt(0) - '0';
        String word = words[idx];
        ArrayList<String> smallAns = nokiaKeyPad_01(ques.substring(1));
        ArrayList<String> ans = new ArrayList<>();
        for (String wds : smallAns) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                ans.add(ch + wds);
            }
        }
        return ans;
    }

    public static int nokiaKeyPad_02(String ques, String ans) {
        if (ques.length() == 0) {
            System.out.print(ans + " ");
            return 1;
        }
        int idx = ques.charAt(0) - '0';
        String word = words[idx];
        int count = 0;
        for (int i = 0; i < word.length(); i++)
            count += nokiaKeyPad_02(ques.substring(1), ans + word.charAt(i));
        if (ques.length() > 1) {
            int idx1 = ques.charAt(1) - '0';
            int fidx = idx * 10 + idx1;
            if (fidx >= 10 && fidx <= 11) {
                String word_ = words[fidx];

                for (int i = 0; i < word_.length(); i++)
                    count += nokiaKeyPad_02(ques.substring(2), ans + word_.charAt(i));
            }
        }
        return count;
    }
    
}