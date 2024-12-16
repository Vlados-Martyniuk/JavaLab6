/**
 * Абстрактний клас, що представляє музичну композицію.
 */
public abstract class MusicComposition {
    private String title;
    private String artist;
    private int duration;

    /**
     * Конструктор для створення музичної композиції.
     *
     * @param title назва композиції
     * @param artist виконавець композиції
     * @param duration тривалість композиції у секундах
     * @throws IllegalArgumentException якщо вхідні дані некоректні
     */
    public MusicComposition(String title, String artist, int duration) {
        if (title == null || artist == null || duration <= 0) {
            throw new IllegalArgumentException("Некоректний ввід даних для композиції");
        }
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    /**
     * Повертає назву композиції.
     *
     * @return назва композиції
     */
    public String getTitle() {
        return title;
    }

    /**
     * Повертає ім'я виконавця композиції.
     *
     * @return ім'я виконавця
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Повертає тривалість композиції.
     *
     * @return тривалість у секундах
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Повертає музичний стиль композиції.
     *
     * @return стиль композиції
     */
    public abstract String getStyle();

    /**
     * Повертає текстове представлення композиції.
     *
     * @return текстове представлення
     */
    @Override
    public String toString() {
        return String.format("%s by %s [%d seconds] (Style: %s)", title, artist, duration, getStyle());
    }
}
