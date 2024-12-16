/**
 * Клас, що представляє техно-композицію.
 */
public class TechnoComposition extends MusicComposition{
    public TechnoComposition(String title, String artist, int duration) {
        super(title, artist, duration);
    }

    @Override
    public String getStyle() {
        return "Techno";
    }
}
