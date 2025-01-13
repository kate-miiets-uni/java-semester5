public class Main {
    public static void main(String[] args) {
        try {
            // Створення StringBuilder з початковим текстом
            StringBuilder text = new StringBuilder("Сьогодні ввечері над містом " +
                    "розкинулося неймовірне зоряне небо, яке нагадує космічний танець " +
                    "метеоритів.");

            // Замінити послідовність табуляцій та пробілів одним пробілом
            int i = 0;
            while (i < text.length()) {
                if (Character.isWhitespace(text.charAt(i))) {
                    int j = i;
                    while (j < text.length() && Character.isWhitespace(text.charAt(j))) {
                        j++;
                    }
                    text.replace(i, j, " ");
                }
                i++;
            }

            // Створення об'єкта тексту
            Text textObj = new Text(text);

            // Сортування слів за кількістю входжень символу "е"
            for (Sentence sentence : textObj.getSentences()) {
                sentence.sortWordsByCharCount('е');
            }

            // Виведення результату
            System.out.println("Відсортовані слова у реченнях за кількістю входжень символу 'е':");
            System.out.println(textObj);
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
}


// Клас для літери
class Letter {
    // Поле для зберігання літери
    private char character;

    // Конструктор класу
    public Letter(char character) {
        this.character = character;
    }

    // Метод для отримання літери
    public char getCharacter() {
        return character;
    }

    // Метод для перетворення об'єкта Letter у рядок
    @Override
    public String toString() {
        return Character.toString(character);
    }
}

// Клас для слова
class Word {
    // Масив літер, що складають слово
    private Letter[] letters;

    // Конструктор класу
    public Word(StringBuilder word) {
        // Ініціалізуємо масив літер розміром, який відповідає довжині введеного слова
        letters = new Letter[word.length()];
        // Заповнюємо масив літер, створюючи об'єкти класу Letter для кожної літери в слові
        for (int i = 0; i < word.length(); i++) {
            letters[i] = new Letter(word.charAt(i));
        }
    }

    // Підраховуємо кількість входження певного символу в кожне слово
    public int countCharOccurrences(char targetChar) {
        // Лічильник для підрахунку входжень символу
        int count = 0;
        // Перебираємо кожну літеру в слові
        for (Letter letter : letters) {
            // Збільшуємо лічильник, якщо літера відповідає заданому символу
            if (letter.getCharacter() == targetChar) {
                count++;
            }
        }
        // Повертаємо кількість входжень символу
        return count;
    }

    // Метод для перетворення об'єкта Word у рядок
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Додаємо кожну літеру у вигляді рядка до StringBuilder
        for (Letter letter : letters) {
            sb.append(letter);
        }
        // Повертаємо слово у вигляді рядка
        return sb.toString();
    }
}

// Клас для розділового знаку
class Punctuation {
    // Поле для зберігання розділового знаку
    private char mark;

    // Конструктор класу
    public Punctuation(char mark) {
        this.mark = mark;
    }

    // Метод для перетворення об'єкта Punctuation у рядок
    @Override
    public String toString() {
        // Повертаємо розділовий знак у вигляді рядка
        return Character.toString(mark);
    }
}

// Клас для речення
class Sentence {
    // Масив для зберігання слів у реченні
    private Word[] words;
    // Поле для зберігання розділових знаків
    private Punctuation punctuation;

    // Конструктор, що приймає речення у вигляді StringBuilder
    public Sentence(StringBuilder sentence) {
        // Розбиття речення на слова та розділовий знак
        String[] parts = sentence.toString().split("\\s+");
        // Створення об'єкта Punctuation для розділового знаку, який є останнім символом у частині
        this.punctuation = new Punctuation(parts[parts.length - 1].charAt(0));
        // Створення масиву Word без врахування розділового знаку
        this.words = new Word[parts.length - 1];
        for (int i = 0; i < parts.length - 1; i++) {
            // Видалення зайвих пробілів навколо кожного слова і створення нового об'єкта Word
            words[i] = new Word(new StringBuilder(parts[i].trim()));
        }
    }

    // Метод для отримання масиву слів
    public Word[] getWords() {
        return words;
    }

    // Сортування слів у реченні за кількістю входжень певного символу
    public void sortWordsByCharCount(char targetChar) {
        // Простий алгоритм сортування методом бульбашки
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = 0; j < words.length - i - 1; j++) {
                if (words[j].countCharOccurrences(targetChar) > words[j + 1].countCharOccurrences(targetChar)) {
                    Word temp = words[j];
                    words[j] = words[j + 1];
                    words[j + 1] = temp;
                }
            }
        }
    }

    // Метод для перетворення об'єкта Sentence у рядок
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Додаємо кожне слово у речення
        for (Word word : words) {
            sb.append(word).append(" ");
        }
        sb.append(punctuation);
        // Повертаємо речення у вигляді рядка
        return sb.toString().trim();
    }
}

// Клас для тексту
class Text {
    private Sentence[] sentences;

    // Конструктор класу
    public Text(StringBuilder text) {
        // Розбиття тексту на речення
        String[] sentenceArray = text.toString().split("(?<=[.!?])\\s+");
        // Ініціалізація масиву речень
        this.sentences = new Sentence[sentenceArray.length];
        // Заповнення масиву речень об'єктами класу Sentence
        for (int i = 0; i < sentenceArray.length; i++) {
            sentences[i] = new Sentence(new StringBuilder(sentenceArray[i].trim()));
        }
    }

    // Метод для отримання масиву речень
    public Sentence[] getSentences() {
        return sentences;
    }

    // Метод для перетворення об'єкта Text у рядок
    @Override
    public String toString() {
        // Додавання кожного речення до StringBuilder
        StringBuilder sb = new StringBuilder();
        for (Sentence sentence : sentences) {
            sb.append(sentence).append(" ");
        }
        // Повернення тексту у вигляді рядка без зайвих пробілів
        return sb.toString().trim();
    }
}