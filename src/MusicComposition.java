public abstract class MusicComposition {
    private String title;
    private String artist;
    private int duration;

    public MusicComposition(String title, String artist, int duration) {
        if (title == null || artist == null || duration <= 0) {
            throw new IllegalArgumentException("Некоректний ввід даних для композиції");
        }
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public abstract String getStyle();

    @Override
    public String toString() {
        return String.format("%s by %s [%d seconds] (Style: %s)", title, artist, duration, getStyle());
    }
}
