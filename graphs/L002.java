import java.lang.reflect.Array;
import java.util.*;

public class L002 {
    public static void main(String[] args) {
        constructGraph();
    }

    public static int N = 8;
    public static ArrayList<Integer>[] graph;

    public static void display() {
        for (int i = 0; i < graph.length; i++) {
            System.out.print(i + "-> "); // u

            for (Integer e : graph[i]) {
                System.out.print(e + ", "); // v
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void constructGraph() {
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        graph[7].add(5);
        graph[7].add(6);
        graph[5].add(4);
        graph[5].add(2);
        graph[6].add(4);
        graph[6].add(3);
        graph[2].add(1);
        graph[3].add(1);
        graph[1].add(0);

        display();
    }

    // ===================== topic=======================
    public static void topologicalsort_(int src, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;
        for (Integer e : graph[src]) {
            if (!vis[e])
                topologicalsort_(e, vis, ans);
        }
        ans.add(src);
    }

    public static void topologicalsort() {
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++)
            if (!vis[i])
                topologicalsort_(i, vis, ans);

        for (int i = ans.size() - 1; i >= 0; i--)
            System.out.println(ans.get(i));
    }

    public static void khansAlgo() {
        int[] indegree = new int[N];
        // firstly update all indegreees ie incoming edges in the array
        for (int i = 0; i < N; i++) {
            for (Integer e : graph[i])
                indegree[e]++;
        }

        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < N; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        ArrayList<Integer> ans = new ArrayList<>();
        while (que.size() != 0) {
            int siz = que.size();
            while (siz-- > 0) {
                int rvtx = que.removeFirst();
                ans.add(rvtx);
                for (int e : graph[rvtx])
                    if (--indegree[e] == 0)
                        que.addLast(e);
            }

        }

        if (ans.size() != N)
            System.out.println("Cycle");
        else
            System.out.println(ans);

    }

    // the below code faith is to detect cycle
    public static boolean topologicalsortcycle_(int src, int[] vis, ArrayList<Integer> ans) {
        if (vis[src] == 1)
            return true;
        if (vis[src] == 2)
            return false;
        boolean res = false;
        vis[src] = 1;
        for (Integer e : graph[src])
            res = res || topologicalsortcycle_(e, vis, ans);
        // agar ek baar bhi cycle mil gyi true return ho jayega
        vis[src] = 2;
        ans.add(src);
        return res;
    }

    public static void topologicalsortcycle() {
        int[] vis = new int[N];
        boolean res = false;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            res = res || topologicalsortcycle_(i, vis, ans);
        }
        if (!res)
            for (int i = ans.size() - 1; i >= 0; i--)
                System.out.println(ans.get(i));
        else
            System.out.println("Cycle");
    }

    public static void SCC() {
        boolean[] vis = new boolean[N];
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < N; i++)
            if (!vis[i])
                topologicalsort_(i, vis, ans);
        ArrayList<Integer>[] ngraph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            for (Integer ele : graph[i])
                ngraph[ele].add(i);
        }
        vis = new boolean[N];
        for (int i = ans.size() - 1; i >= 0; i--)
            if (!vis[i]) {
                ArrayList<Integer> ans_ = new ArrayList<>();
                System.out.println(dfs_SCC(ans.get(i), ngraph, vis, ans_));
                System.out.println(ans_);
            }
    }

    public static int dfs_SCC(int src, ArrayList<Integer>[] ngraph, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;
        int count = 0;
        for (Integer ele : ngraph[src])
            if (!vis[ele])
                count += dfs_SCC(ele, ngraph, vis, ans);
        ans.add(src);
        return count + 1;
    }
}