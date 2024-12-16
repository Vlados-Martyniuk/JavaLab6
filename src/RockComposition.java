/**
 * Клас, що представляє рок-композицію.
 */
public class RockComposition  extends MusicComposition{
    public RockComposition(String title, String artist, int duration) {
        super(title, artist, duration);
    }

    @Override
    public String getStyle() {
        return "Rock";
    }
}
