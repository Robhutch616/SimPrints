import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Wrapper for holding histogram data
 *
 * StringUtils requires :
 */
public class Histogram {
    private HashMap<String,HistogramBar> map;
    private boolean countSpecials;

    public Histogram(boolean countSpecialCharacters) {
        map = new HashMap<>();
        countSpecials = countSpecialCharacters;
    }

    private boolean containsSpecialCharacter(String s) {
        return (s == null) ? false : s.matches("[^A-Za-z0-9 ]");
    }

    public void increment(Character character){
        increment(character.toString());
    }

    public void increment(String key){
        if(StringUtils.isBlank(key) || (!countSpecials && containsSpecialCharacter(key))){
            return;
        }
        HistogramBar bar = map.get(key);
        if(bar == null){
            bar = new HistogramBar(key);
            bar.increment();
            map.put(key, bar);
        }else{
            bar.increment();
        }
    }

    public String print(){
        List<HistogramBar> list = new ArrayList<HistogramBar>(map.values());
        Collections.sort(list);
        StringBuilder builder = new StringBuilder();
        builder.append("Histogram of letters in Document:\n");
        for(HistogramBar bar : list){
            builder.append(bar.key());
            builder.append(": ");
            for(int i=0;i<bar.getCount();i++){
                builder.append("+");
            }
            builder.append("\n");
        }
        return builder.toString();
    }


}
