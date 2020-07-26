import java.util.*;

public class L003 {
    public static void main(String[] args) {

    }

    // agar parent same hai to dono ko ek hi set mein merge kr do nhi to alag alag
    // set bna do
    static int[] par;
    static int[] setSize;

    public static int findPar(int vtx) {
        if (par[vtx] == vtx)
            return vtx;
        return par[vtx] = findPar(vtx);
    }

    public static void mergeSet(int l1, int l2) {
        if (setSize[l1] > setSize[l2]) {
            par[l2] = l1;
            setSize[l1] += setSize[l2];
        } else {
            par[l1] = l2;
            setSize[l2] += setSize[l1];
        }
    }
}