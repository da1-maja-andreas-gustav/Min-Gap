// Version: 20200917
// Handin done by:
//   <202004107> <A. Malthe Henriksen>
//   <202007490> <Gustav Burchardt>
//   <201905796> <Maja Vonge Cornils>
// Contributions:
//   <A. Malthe Henriksen> <Solved and implemented exercise>

import java.util.ArrayList;
import java.util.Collections;

public class Augment {
    public int minGap;
    // If you need any additional fields, you can add them here

    /**
     * The minimum and maximum value for keys in this subtree.
     */
    private int min = -1, max = -1;

    public static Augment combine(Augment left, Augment right, int key) {
        Augment res = new Augment();
        // Implement your method here to combine the augmented data from
        // the left and right child with the node's key

        res.setMin(getMin(left.getMin(),right.getMin(),key));
        res.setMax(getMax(left.getMax(),right.getMax(),key));

        /*
        This is a list of all gaps that will be compared to each other. The smallest will be chosen as the minGap for
        Augment object 'res'.
        Each index in the ArrayList will be checked for validity before being added.
            i.e if no mingap for left or right is found, then it won't be added.
         */
        ArrayList<Integer> gaps = new ArrayList<Integer>(){{
            if (right.getMinGap() != 0) add(right.getMinGap()); // Right.minGap
            if (left.getMinGap() != 0) add(left.getMinGap()); // left.minGap
            if (right.getMin() != -1 && left.getMax() != -1 ) add(Math.abs(right.getMin() - left.getMax())); // Compare right.min to left.max
            if (right.getMin() != -1) add(Math.abs(right.getMin() - key)); // Compare right.min to key
            if (left.getMax() != -1) add(Math.abs(key - left.getMax())); // Compare left.max to key.
        }};

        // Sort gaps from smallest to largest
        Collections.sort(gaps);

        // If list of gaps has 1 or more indeces, pick the first one(the smallest). Otherwise leave mingap at 0
        res.setMinGap(gaps.size() > 0 ? gaps.get(0) : 0);

        return res;
    }

    public static Augment leaf() {
        Augment res = new Augment();
        // Implement your method here to return the augmented data of a leaf
        return res;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public int getMinGap() {
        return minGap;
    }

    public void setMinGap(int minGap) {
        this.minGap = minGap;
    }

    /**
     * Compare an array of integers, and return the smallest.
     * @param k Array of integers
     * @return The smallest integer
     */
    private static int getMin(Integer... k) {
        int result = 1000000001;
        for (int i : k) {
            if (i != -1) {
                result = Math.min(result,i);
            }
        }
        return result;
    }

    /**
     * Compare an array of integers, and return the largest.
     * @param k Array of integers
     * @return The largest integer
     */
    private static int getMax(Integer... k) {
        int result = 0;
        for (int i : k) {
            if (i != -1) {
                result = Math.max(result,i);
            }
        }
        return result;
    }
}