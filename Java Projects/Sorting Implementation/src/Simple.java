public class Simple implements Comparable<Simple> {
    public String alpha = null; // If private, it gives an access error in main
    public Integer numeric = 0; // If private, it gives an access error in main

    public Simple() {
    }

    public Simple(String a, Integer n) {
        alpha = a;
        numeric = n;
    }

    public String alpha() { return alpha; }
    public void alpha(String a) { alpha = a; }
    public Integer numeric() { return numeric; }
    public void numeric(Integer n) { numeric = n; }

    @Override
    public String toString() {
        return "{"+alpha+","+numeric+"}";
    }

    @Override
    public int compareTo(Simple other) {
        return numeric.compareTo(other.numeric);
    }

    @Override
    public boolean equals(Object object) {
        if (this==object) return true;
        if (!(object instanceof Simple)) return false;

        Simple other = (Simple) object;
        return (alpha.equals(other.alpha) && numeric==other.numeric);

    }
}