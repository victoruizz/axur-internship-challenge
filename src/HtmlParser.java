import java.util.Stack;

public class HtmlParser {

    private static final String MALFORMED_HTML_MSG = "malformed HTML";

    public String findDeepestText(String html){
        String[] lines = html.split("\n");

        Stack<String> tags = new Stack<>();

        int currentDepth = 0;
        int maxDepth = -1;
        String deepestText = null;

        for(String line : lines){
            String trimmed = line.trim();

            if(trimmed.isEmpty()){
                continue;
            }

            if(isClosingTag(trimmed)){
                if(tags.isEmpty()){
                    return MALFORMED_HTML_MSG;
                }

                String tagName = extractTagName(trimmed);
                String lastOpened = tags.pop();

                if(!tagName.equals(lastOpened)){
                    return MALFORMED_HTML_MSG;
                }

                currentDepth--;
            } else if (isOpeningTag(trimmed)) {
                String tagName = extractTagName(trimmed);
                tags.push(tagName);
                currentDepth++;

            }else {
                if(currentDepth > maxDepth){
                    maxDepth = currentDepth;
                    deepestText = trimmed;
                }
            }
        }

        if(!tags.isEmpty()){
            return MALFORMED_HTML_MSG;
        }

        return deepestText != null ? deepestText : "";
    }

    private boolean isOpeningTag(String line){
        return line.startsWith("<")
                && line.endsWith(">")
                && !line.startsWith("</");
    }

    private boolean isClosingTag(String line){
        return line.startsWith("</") && line.endsWith(">");
    }

    private String extractTagName(String line){
        return line.replaceAll("[</>]", "").trim();
    }
}
