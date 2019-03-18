package try_with_resources;

public class HtmlTag implements AutoCloseable{
    private String markerName;

    public HtmlTag(String markerName) {
        this.markerName = markerName;
        System.out.println("<" + getMarkerName() + ">");
    }

    public String getMarkerName() {
        return markerName;
    }

    @Override
    public void close() {
        System.out.println("<" + getMarkerName() + "/>");
    }

    public void body(String txt) {
        System.out.println(txt);
    }
}

class TRMain {
    public static void main(String[] args) {

        try (
                HtmlTag h1 = new HtmlTag("h1");
                HtmlTag em = new HtmlTag("em")
        ) {
            em.body("Sample txt of body method.");
        }
    }
}