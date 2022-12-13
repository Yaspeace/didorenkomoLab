package bank.helpers.serialization;

public class FieldReader {
    private int ptr;
    private final String inp;
    public FieldReader(String input) {
        ptr = 0;
        inp = input;
    }

    public void reset() {
        ptr = 0;
    }

    public String readNext() {
        if(ptr > inp.length() - 1) return null;
        String res = "";
        while(ptr < inp.length() && inp.charAt(ptr) != '{') ptr++;
        if(ptr == inp.length()) return null;
        if(inp.charAt(ptr) != '{')
            throw new RuntimeException("Ошибка десериализации, ожидалось '{', получен конец строки");
        res += inp.charAt(ptr);
        ptr++;
        int opened = 1;
        while(ptr < inp.length() && opened > 0) {
            char ch = inp.charAt(ptr);
            if(ch == '{') opened++;
            if(ch == '}') opened--;
            res += ch;
            ptr++;
        }
        return res;
    }
}
