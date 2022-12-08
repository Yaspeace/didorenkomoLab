package helpers;

import java.util.Collection;
import java.util.StringJoiner;

public class CollectionPrinter <T> {
    public static <T> String collectionToString(Collection<T> col) {
        if(col == null || col.size() == 0) return "[]";

        StringJoiner res = new StringJoiner(",\n", "[\n", "\n]");
        for(T item : col) {
            res.add(item.toString());
        }
        return res.toString();
    }
}
