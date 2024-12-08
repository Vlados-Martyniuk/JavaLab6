import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        CustomList<MusicComposition> emptyList = new CustomList<>();
        emptyList.add(new PopComposition("Pop Song", "Artist A", 200));
        System.out.println("Порожній список із доданим елементом:");
        for (MusicComposition composition : emptyList) {
            System.out.println(composition);
        }


        MusicComposition initialComposition = new RockComposition("Rock Song", "Artist B", 300);
        CustomList<MusicComposition> singleElementList = new CustomList<>(initialComposition);
        System.out.println("\nСписок із одним елементом:");
        for (MusicComposition composition : singleElementList) {
            System.out.println(composition);
        }


        List<MusicComposition> standardCollection = Arrays.asList(
                new RapComposition("Rap Song", "Artist C", 250),
                new TechnoComposition("Techno Song", "Artist D", 180)
        );
        CustomList<MusicComposition> fromCollectionList = new CustomList<>(standardCollection);
        System.out.println("\nСписок, створений із стандартної колекції:");
        for (MusicComposition composition : fromCollectionList) {
            System.out.println(composition);
        }

    }
}