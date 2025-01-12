public class Main {
    public static void main(String[] args) {
        try {
            // Створення StringBuilder з початковим текстом
            StringBuilder text = new StringBuilder("Сьогодні ввечері над містом " +
                    "розкинулося неймовірне зоряне небо, яке нагадує космічний танець " +
                    "метеоритів.");

            // Розбиття тексту на слова
            StringBuilder[] words = splitTextIntoWords(text);

            // Сортування слів за кількістю входжень символу "е"
            sortWordsByCharCount(words, 'е');

            // Побудова результатного рядка з відсортованими словами
            StringBuilder sortedText = new StringBuilder();
            for (StringBuilder word : words) {
                sortedText.append(word).append(" ");
            }

            // Виведення результату
            System.out.println("Відсортовані слова: " + sortedText.toString().trim());
        } catch (NullPointerException e) {
            System.err.println("Помилка: Вхідний текст не повинен бути нульовим.");
        } catch (IllegalArgumentException e) {
            System.err.println("Помилка: " + e.getMessage());
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println("Помилка: Сталася помилка доступу до символу рядка.");
        } catch (Exception e) {
            // Загальна обробка невідомих помилок
            System.err.println("Непередбачувана помилка: " + e.getMessage());
        }
    }

    // Метод для розбиття тексту на слова з використанням StringBuilder
    public static StringBuilder[] splitTextIntoWords(StringBuilder text) {
        // Розбиття тексту на слова за пробілами
        String[] wordArray = text.toString().split(" ");
        // Створення масиву StringBuilder для кожного слова
        StringBuilder[] words = new StringBuilder[wordArray.length];
        for (int i = 0; i < wordArray.length; i++) {
            words[i] = new StringBuilder().append(wordArray[i]);
        }
        return words;
    }

    // Метод для сортування слів за кількістю входжень певного символу
    public static void sortWordsByCharCount(StringBuilder[] words, char targetChar) {
        // Простий алгоритм сортування методом бульбашки
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words.length - i - 1; j++) {
                // Порівняння кількості входжень символу в кожному слові
                if (countCharOccurrences(words[j], targetChar) > countCharOccurrences(words[j + 1], targetChar)) {
                    // Обмін елементів, якщо кількість входжень більше
                    StringBuilder temp = words[j];
                    words[j] = words[j + 1];
                    words[j + 1] = temp;
                }
            }
        }
    }

    // Метод для підрахунку кількості входжень певного символу в слові
    public static int countCharOccurrences(StringBuilder word, char targetChar) {
        int count = 0;
        // Перебір кожного символу в слові
        for (int i = 0; i < word.length(); i++) {
            // Збільшення лічильника, якщо символ відповідає заданому
            if (word.charAt(i) == targetChar) {
                count++;
            }
        }
        return count;
    }
}

