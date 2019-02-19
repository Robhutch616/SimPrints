import java.util.Comparator;

public class HistogramBar implements Comparable<HistogramBar>{
    private final String key;
    private int count = 0;

    public  HistogramBar(String key){
        this.key = key;
    }

    public void increment(){
        count++;
    }

    public int getCount(){
        return count;
    }

    public String key(){
        return key;
    }

    /**
     * Comparable overload for histogramBar
     * @param o histogrambar to compare to
     * @return -1, 0 +1 for less than, equal to or greater than
     */
    @Override
    public int compareTo(HistogramBar o) {
        return key.compareTo(o.key);
    }
}
