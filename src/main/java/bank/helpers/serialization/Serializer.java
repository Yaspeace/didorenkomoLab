package bank.helpers.serialization;

import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Serializer {
    public static String serialize(Object obj) throws Exception {
        String res = "{\n";
        Field[] fields = obj.getClass().getFields();
        int i = 0;
        while(i < fields.length && fields[i].get(obj) instanceof Collection<?>) ++i;
        Object value = fields[i].get(obj);

        res += "\t{" + fields[i].getName() + ":" + (value == null ? "null" : value.toString()) + "}";

        for(int j = i + 1; j < fields.length; j++) {
            value = fields[j].get(obj);
            if(value instanceof Collection<?>) continue;
            res += ",\n\t{" + fields[j].getName() + ":" + (value == null ? "null" : value.toString()) + "}";
        }
        res += "\n}";
        return res;
    }

    public static <T> String serializeMany(Collection<T> objects) throws Exception {
        String res = "";
        for(Object o : objects) {
            res += serialize(o) + ",\n";
        }
        return res;
    }

    private static Tuple deserializeField(String field) throws Exception {
        Tuple res = new Tuple();
        if(field.charAt(0) != '{' || field.charAt(field.length() - 1) != '}')
            throw new RuntimeException("Попытка десериализации некорректной строки поля");
        int i = 1;
        while(i < field.length() && field.charAt(i) != ':') {
            res.key += field.charAt(i);
            i++;
        }
        if(i == field.length()) throw new RuntimeException("Ожидалось ':', встречен конец строки");
        i++;
        while(i < field.length() - 1) {
            res.value += field.charAt(i);
            i++;
        }
        return res;
    }

    public static HashMap<String, String> deserialize(String serializedObject) throws Exception {
        HashMap<String, String> res = new HashMap<>();
        int i = 0;
        while(i < serializedObject.length() && serializedObject.charAt(i) != '{') i++;
        if(i == serializedObject.length())
            throw new Exception("Ошибка сериализации: ожидался объект");

        int check = 1;
        i++;
        int startPos = i;
        while(i < serializedObject.length() && check > 0) {
            if(serializedObject.charAt(i) == '{') check++;
            if(serializedObject.charAt(i) == '}') check--;
            i++;
        }
        if (check > 0) throw new Exception("Ошибка сериализации: имеются не закрытые '{'");
        if(check < 0) throw new Exception("Ошибка сериализации: имеются излишние закрывающие '}'");

        String trimmed = serializedObject.substring(startPos);
        FieldReader fr = new FieldReader(trimmed);
        String field = fr.readNext();
        while(field != null) {
            Tuple parsedField = deserializeField(field);
            res.put(parsedField.key, parsedField.value);
            field = fr.readNext();
        }
        return res;
    }

    public static Collection<HashMap<String, String>> deserializeMany(String serializedObjects) throws Exception {
        LinkedList<HashMap<String, String>> res = new LinkedList<>();
        FieldReader fr = new FieldReader(serializedObjects);
        String nextObject = fr.readNext();
        while(nextObject != null) {
            res.add(Serializer.deserialize(nextObject));
            nextObject = fr.readNext();
        }
        return res;
    }
}
