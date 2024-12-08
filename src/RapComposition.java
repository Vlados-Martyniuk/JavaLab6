public class RapComposition extends MusicComposition{
    public RapComposition(String title, String artist, int duration) {
        super(title, artist, duration);
    }

    @Override
    public String getStyle() {
        return "Rap";
    }
}
