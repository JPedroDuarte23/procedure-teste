package sptech.school.exerciciodynamicjpql.util;

import java.util.List;
import java.util.Map;

public class JsonFormatter {

    public static String format(Map<String, Object> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        map.forEach((key, value) -> {
            json.append("\"").append(key).append("\":");
            if (value instanceof String) {
                json.append("\"").append(value).append("\"");
            } else {
                json.append(value);
            }
            json.append(",");
        });
        json.deleteCharAt(json.length() - 1);
        json.append("}");
        return json.toString();
    }

    public static String format(List<Map<String, Object>> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        list.forEach(map -> json.append(format(map)).append(","));
        json.deleteCharAt(json.length() - 1);
        json.append("]");
        return json.toString();
    }
}
