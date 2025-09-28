import java.util.LinkedHashMap;
import java.util.Map;

public class Roles {
    private static String printTextPerRole(String[] roles, String[] textLines) {
        Map<String, StringBuilder> roleMap = new LinkedHashMap<>();

        for (String role : roles) {
            roleMap.put(role, new StringBuilder());
        }

        for (int i = 0; i < textLines.length; i++) {

            String line = textLines[i];
            int colonIndex = line.indexOf(':');

            if (colonIndex == -1) {
                continue;
            }

            String role = line.substring(0, colonIndex);
            String text = line.substring(colonIndex + 1);
            StringBuilder roleBuilder = roleMap.get(role);

            if (roleBuilder.length() > 0) {
                roleBuilder.append("\n");
            }

            roleBuilder.append(i + 1).append(")").append(text);
        }

        StringBuilder result = new StringBuilder();
        boolean firstRole = true;

        for (String role : roles) {
            if (!firstRole) {
                result.append("\n\n");
            }

            firstRole = false;

            result.append(role).append(":\n");
            StringBuilder roleBuilder = roleMap.get(role);
            result.append(roleBuilder);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String[] roles = {
                "Городничий",
                "Аммос Федорович",
                "Артемий Филиппович",
                "Лука Лукич"
        };

        String[] textLines = {
                "Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
                "Аммос Федорович: Как ревизор?",
                "Артемий Филиппович: Как ревизор?",
                "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
                "Аммос Федорович: Вот те на!",
                "Артемий Филиппович: Вот не было заботы, так подай!",
                "Лука Лукич: Господи боже! еще и с секретным предписаньем!"
        };

        String result = printTextPerRole(roles, textLines);
        System.out.println(result);
    }
}