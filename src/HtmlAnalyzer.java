import java.io.IOException;

public class HtmlAnalyzer {

    public static void main(String[] args) {
        if(args.length!=1){
            return;
        }
        String url = args[0];

        try{
            HtmlLoader loader = new HtmlLoader();
            HtmlParser parser = new HtmlParser();

            String html = loader.fetchHtml(url);

            String result = parser.findDeepestText(html);

            System.out.println(result);
        } catch (IOException e) {
            System.out.println("URL connection error");
        }
    }
}

