public class HtmlAnalyzer {

    public static void main(String[] args) {
        if(args.length!=1){
            return;
        }
        String url = args[0];

        try{
            HtmlLoader loader = new HtmlLoader();
            HtmlParser parser = new HtmlParser();

            String hmtl = loader.fetchHtml(url);

            String result = parser.findDeepestText(hmtl);

            System.out.println(result);
        } catch (Exception e) {
            System.out.println("URL connection error");
        }

    }
}
