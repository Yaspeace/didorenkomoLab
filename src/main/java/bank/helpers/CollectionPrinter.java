package bank.helpers;

import java.util.Collection;
import java.util.StringJoiner;

/**
 * Класс для вывода параметризованных коллекций
 */
public class CollectionPrinter {
    /**
     * Вывести содержимое коллекции в строку
     * @param col Коллекция
     * @return Строка
     * @param <T> Базовый тип коллекции
     */
    public static <T> String collectionToString(Collection<T> col) {
        if(col == null || col.size() == 0) return "[]";

        StringJoiner res = new StringJoiner(",\n", "[\n", "\n]");
        for(T item : col) {
            res.add(item.toString());
        }
        return res.toString();
    }
}
