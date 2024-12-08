public class PopComposition extends MusicComposition{
    public PopComposition(String title, String artist, int duration) {
        super(title, artist, duration);
    }

    @Override
    public String getStyle() {
        return "Pop";
    }
}
