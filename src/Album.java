import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
/**
 * Клас представляє музичний альбом, що містить список музичних композицій.
 * Дозволяє додавати композиції, отримувати загальну тривалість,
 * сортувати композиції за стилем та знаходити композиції в заданому діапазоні тривалості.
 */
public class Album {
    private CustomList<MusicComposition> compositions;

    /**
     * Створює новий пустий альбом.
     */
    public Album() {
        this.compositions = new CustomList<>();
    }

    /**
     * Додає музичну композицію до альбому.
     *
     * @param composition композиція для додавання.
     * @throws IllegalArgumentException якщо передана композиція є null.
     */
    public void addComposition(MusicComposition composition) {
        if (composition == null) {
            throw new IllegalArgumentException("Композиція не може бути пустою");
        }
        compositions.add(composition);
    }

    /**
     * Повертає загальну тривалість усіх композицій в альбомі.
     *
     * @return загальна тривалість у секундах.
     */
    public int getTotalDuration() {
        return compositions.stream().mapToInt(MusicComposition::getDuration).sum();
    }

    /**
     * Сортує композиції в альбомі за стилем у лексикографічному порядку.
     */
    public void sortByStyle() {
        compositions.sort(Comparator.comparing(MusicComposition::getStyle));;
    }

    /**
     * Знаходить композиції в заданому діапазоні тривалості.
     *
     * @param minDuration мінімальна тривалість у секундах.
     * @param maxDuration максимальна тривалість у секундах.
     * @return список композицій у вказаному діапазоні тривалості.
     * @throws IllegalArgumentException якщо діапазон некоректний (наприклад, мінімум більший за максимум).
     */
    public CustomList<MusicComposition> findByDurationRange(int minDuration, int maxDuration) {
        if (minDuration < 0 || maxDuration < 0 || minDuration > maxDuration) {
            throw new IllegalArgumentException("Некоректний діапазон композицфї");
        }
        CustomList<MusicComposition> result = new CustomList<>();
        for (MusicComposition composition : compositions) {
            if (composition.getDuration() >= minDuration && composition.getDuration() <= maxDuration) {
                result.add(composition);
            }
        }
        return result;
    }

    /**
     * Повертає рядкове представлення альбому.
     *
     * @return рядок із інформацією про всі композиції в альбомі.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Альбом містить:\n");
        for (MusicComposition composition : compositions) {
            builder.append(composition.toString()).append("\n");
        }
        return builder.toString();
    }
}
