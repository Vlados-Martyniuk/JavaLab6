# Лабораторна робота №6
**Тема:** Робота з колекціями в мові програмування Java

**Мета:** Здобуття навичок у створенні власних та використанні стандартних колекцій в мові програмування Java.


---

## Завдання
**Розрахунок параметрів:**
- (C2) = 0: List
- (C3) = 0: Масив із початковою кількістю елементів 15 та збільшенням кількості елементів на 30%

---
## Основні функції
1. **Додавання композицій до альбому.**
2. **Обчислення загальної тривалості композицій в альбомі.**
3. **Сортування композицій за стилем.**
4. **Пошук композицій за діапазоном тривалості.**
5. **Виведення інформації про всі композиції в альбомі.**

---
## Структура проекту

### Клас `MusicComposition`
- Абстрактний клас, що представляє музичну композицію.
- **Атрибути:**
    - `title` — назва композиції.
    - `artist` — виконавець композиції.
    - `duration` — тривалість композиції в секундах.
- **Методи:**
    - `getStyle()` — абстрактний метод, який повертає стиль композиції.
    - `toString()` — надає текстове представлення композиції.

### Похідні класи
- **`PopComposition`** — клас для поп-композицій, що успадковує `MusicComposition`.
- **`RapComposition`** — клас для реп-композицій, що успадковує `MusicComposition`.
- **`RockComposition`** — клас для рок-композицій, що успадковує `MusicComposition`.
- **`TechnoComposition`** — клас для техно-композицій, що успадковує `MusicComposition`.

### Клас `Album`
- Клас для роботи з музичним альбомом, який містить список композицій.
- **Методи:**
    - `addComposition(MusicComposition composition)` — додає композицію до альбому.
    - `getTotalDuration()` — повертає загальну тривалість композицій у альбомі.
    - `sortByStyle()` — сортує композиції в альбомі за стилем.
    - `findByDurationRange(int minDuration, int maxDuration)` — знаходить композиції в альбомі, що потрапляють в заданий діапазон тривалості.
    - `toString()` — повертає текстове представлення альбому.

### Клас `CustomList`
- Клас для створення та обробки списку композицій.
- **Методи:**
    - `add(MusicComposition composition)` — додає композицію до списку.
    - `remove(MusicComposition composition)` — видаляє композицію зі списку.
    - `sortByTitle()` — сортує композиції за назвою.
    - `filterByArtist(String artist)` — фільтрує композиції за виконавцем.
    - `toString()` — повертає текстове представлення списку композицій.

### Клас `Main`
- Головний клас для демонстрації роботи програми.
- **Основна логіка:**
    1. Створення альбому.
    2. Додавання композицій до альбому та кастомного списку.
    3. Виведення інформації про альбом та список композицій.
    4. Обчислення загальної тривалості альбому.
    5. Сортування композицій за стилем та іншими критеріями.
    6. Пошук композицій за діапазоном тривалості та іншими параметрами.
  
## Як запустити програму
1. Завантажте або скопіюйте проєкт.
2. Відкрийте його в будь-якому середовищі розробки Java (наприклад, IntelliJ IDEA, Eclipse, NetBeans).
3. Переконайтеся, що встановлена версія JDK 8 або новіша.
4. Запустіть клас `Main`.
