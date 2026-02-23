import java.util.*;

/** Single responsibility: parse raw key=value string into a flat map. */
public class StudentParser {

    public Map<String, String> parse(String raw) {
        Map<String, String> kv = new LinkedHashMap<>();
        for (String part : raw.split(";")) {
            String[] tokens = part.split("=", 2);
            if (tokens.length == 2) {
                kv.put(tokens[0].trim(), tokens[1].trim());
            }
        }
        return kv;
    }
}
