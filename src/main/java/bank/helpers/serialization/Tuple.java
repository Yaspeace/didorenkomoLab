package bank.helpers.serialization;

public class Tuple {
        public String key;
        public String value;
        public Tuple() {
            key = "";
            value = "";
        }

        public Tuple(String key, String value) {
            this.key = key;
            this.value = value;
        }
}
