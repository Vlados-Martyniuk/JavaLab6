import java.util.*;
/**
 * Власна реалізація динамічного списку, яка підтримує операції, визначені інтерфейсом {@link List}.
 * Використовується для зберігання об'єктів довільного типу.
 *
 * @param <T> тип елементів у списку.
 */
public class CustomList<T> implements List<T> {
    private Object[] elements;
    private int size;

    private static final int DEFAULT_CAPACITY = 15;
    private static final double GROWTH_FACTOR = 1.3;

    /**
     * Створює пустий список із початковою ємністю за замовчуванням.
     */
    public CustomList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Створює список із одним елементом.
     *
     * @param element початковий елемент.
     */
    public CustomList(T element) {
        this();
        add(element);
    }

    /**
     * Створює список на основі заданої колекції.
     *
     * @param collection колекція, з якої створюється список.
     */
    public CustomList(Collection<? extends T> collection) {
        this();
        addAll(collection);
    }

    /**
     * Перевіряє, чи список має достатню ємність для зберігання заданої кількості елементів.
     *
     * @param minCapacity мінімальна необхідна ємність.
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = (int) (elements.length * GROWTH_FACTOR) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Перевіряє, чи індекс знаходиться в допустимому діапазоні.
     *
     * @param index індекс для перевірки.
     * @throws IndexOutOfBoundsException якщо індекс виходить за межі списку.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Перевіряє, чи заданий індекс знаходиться в допустимих межах для вставки елементів.
     *
     * @param index індекс для перевірки.
     * @throws IndexOutOfBoundsException якщо індекс виходить за межі списку.
     */
    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Повертає розмір списку.
     *
     * @return кількість елементів у списку.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Перевіряє, чи список порожній.
     *
     * @return {@code true}, якщо список не містить елементів, інакше {@code false}.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Перевіряє, чи список містить заданий елемент.
     *
     * @param o елемент для перевірки.
     * @return {@code true}, якщо список містить елемент, інакше {@code false}.
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Повертає ітератор для проходження списку.
     *
     * @return ітератор для списку.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elements[currentIndex++];
            }
        };
    }

    /**
     * Повертає масив, що містить усі елементи списку.
     *
     * @return масив усіх елементів у списку.
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /**
     * Повертає масив, що містить усі елементи списку, у заданому типі.
     *
     * @param a масив, у який буде скопійовано елементи.
     * @param <E> тип елементів масиву.
     * @return масив, що містить елементи списку.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            return (E[]) Arrays.copyOf(elements, size, a.getClass());
        }
        System.arraycopy(elements, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    /**
     * Додає елемент у список.
     *
     * @param t елемент, який потрібно додати.
     * @return {@code true}, якщо елемент успішно додано.
     */
    @Override
    public boolean add(T t) {
        ensureCapacity(size + 1);
        elements[size++] = t;
        return true;
    }

    /**
     * Видаляє перше входження заданого елемента зі списку.
     *
     * @param o елемент, який потрібно видалити.
     * @return {@code true}, якщо елемент було успішно видалено.
     */
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    /**
     * Перевіряє, чи містить список усі елементи з заданої колекції.
     *
     * @param c колекція елементів для перевірки.
     * @return {@code true}, якщо список містить усі елементи з колекції, {@code false} - якщо хоча б один елемент відсутній.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Додає всі елементи з заданої колекції до цього списку.
     *
     * @param c колекція елементів для додавання.
     * @return {@code true}, якщо список було змінено (якщо було додано хоча б один елемент), {@code false} - якщо колекція пуста або не містить елементів для додавання.
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null || c.isEmpty()) {
            return false;
        }
        ensureCapacity(size + c.size());
        for (T element : c) {
            elements[size++] = element;
        }
        return true;
    }

    /**
     * Додає всі елементи з заданої колекції за індексом у список.
     *
     * @param index індекс, на якому почнеться додавання елементів.
     * @param c колекція елементів для додавання.
     * @return {@code true}, якщо список було змінено, {@code false} - якщо колекція пуста.
     * @throws NullPointerException якщо колекція {@code c} дорівнює {@code null}.
     * @throws IndexOutOfBoundsException якщо індекс знаходиться поза межами списку.
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            throw new NullPointerException("Колекція не може бути null");
        }
        checkIndexForAdd(index);

        Object[] toAdd = c.toArray();
        int numNew = toAdd.length;
        if (numNew == 0) {
            return false;
        }

        ensureCapacity(size + numNew);

        System.arraycopy(elements, index, elements, index + numNew, size - index);
        System.arraycopy(toAdd, 0, elements, index, numNew);
        size += numNew;
        return true;
    }

    /**
     * Видаляє всі елементи з цього списку, які містяться в заданій колекції.
     *
     * @param c колекція елементів для видалення.
     * @return {@code true}, якщо список було змінено, {@code false} - якщо жодного елемента не було видалено.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object element : c) {
            while (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Залишає лише ті елементи, які є в заданій колекції. Інші елементи видаляються.
     *
     * @param c колекція елементів, які повинні залишитись у списку.
     * @return {@code true}, якщо список було змінено, {@code false} - якщо жодного елемента не було видалено.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(elements[i])) {
                remove(i);
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Очищає список, видаляючи всі елементи.
     */
    @Override
    public void clear() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Повертає елемент за заданим індексом.
     *
     * @param index індекс елемента.
     * @return елемент, що знаходиться за заданим індексом.
     * @throws IndexOutOfBoundsException якщо індекс поза межами списку.
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        @SuppressWarnings("unchecked")
        T element = (T) elements[index];
        return element;
    }

    /**
     * Замінює елемент за заданим індексом на новий.
     *
     * @param index індекс елемента для заміни.
     * @param element новий елемент.
     * @return старий елемент, який був замінений.
     * @throws IndexOutOfBoundsException якщо індекс поза межами списку.
     */
    @Override
    public T set(int index, T element) {
        checkIndex(index);
        @SuppressWarnings("unchecked")
        T oldElement = (T) elements[index];
        elements[index] = element;
        return oldElement;
    }

    /**
     * Додає елемент за заданим індексом.
     *
     * @param index індекс для додавання елемента.
     * @param element елемент, який потрібно додати.
     * @throws IndexOutOfBoundsException якщо індекс поза межами допустимих значень.
     */
    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Видаляє елемент за заданим індексом.
     *
     * @param index індекс елемента для видалення.
     * @return елемент, який був видалений.
     * @throws IndexOutOfBoundsException якщо індекс поза межами списку.
     */
    @Override
    public T remove(int index) {
        checkIndex(index);
        @SuppressWarnings("unchecked")
        T removedElement = (T) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
        return removedElement;
    }

    /**
     * Повертає індекс першого входження заданого елемента в список.
     *
     * @param o елемент для пошуку.
     * @return індекс першого входження елемента, або {@code -1}, якщо елемент не знайдено.
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Повертає індекс останнього входження заданого елемента в список.
     *
     * @param o елемент для пошуку.
     * @return індекс останнього входження елемента, або {@code -1}, якщо елемент не знайдено.
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Повертає ітератор, що дозволяє пройтись по елементах списку від початку.
     *
     * @return ітератор для списку.
     */
    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    /**
     * Повертає ітератор, що дозволяє пройтись по елементах списку, починаючи з заданого індексу.
     *
     * @param index індекс, з якого почнеться перебір елементів.
     * @return ітератор для списку, починаючи з заданого індексу.
     * @throws IndexOutOfBoundsException якщо індекс поза межами списку.
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        checkIndexForAdd(index);
        return new ListIterator<T>() {
            private int currentIndex = index;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) elements[currentIndex++];
            }

            @Override
            public boolean hasPrevious() {
                return currentIndex > 0;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                return (T) elements[--currentIndex];
            }

            @Override
            public int nextIndex() {
                return currentIndex;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                CustomList.this.remove(--currentIndex);
            }

            @Override
            public void set(T t) {
                CustomList.this.set(currentIndex - 1, t);
            }

            @Override
            public void add(T t) {
                CustomList.this.add(currentIndex++, t);
            }
        };
    }

    /**
     * Повертає підсписок, що містить елементи між заданими індексами (від {@code fromIndex} до {@code toIndex}).
     *
     * @param fromIndex індекс початку підсписку (включно).
     * @param toIndex індекс кінця підсписку (не включно).
     * @return підсписок елементів.
     * @throws IndexOutOfBoundsException якщо індекси виходять за межі списку.
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        checkIndex(fromIndex);
        checkIndex(toIndex - 1);
        CustomList<T> subList = new CustomList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(get(i));
        }
        return subList;
    }
}
