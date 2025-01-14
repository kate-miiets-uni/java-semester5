import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class ElectroDeviceSet<E extends ElectroDevice> implements Set<E> {

    private Node<E> head;
    private int size;

    // Внутрішній клас Node для реалізації однозв'язкового списку
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    // Порожній конструктор
    public ElectroDeviceSet() {
        head = null;
        size = 0;
    }

    // Конструктор, який приймає один об'єкт
    public ElectroDeviceSet(E element) {
        this();
        add(element);
    }

    // Конструктор, який приймає колекцію об'єктів
    public ElectroDeviceSet(Collection<? extends E> collection) {
        this();
        addAll(collection);
    }

    // Повертає кількість елементів у колекції
    @Override
    public int size() {
        return size;
    }

    // Перевіряє, чи є колекція порожньою
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Перевіряє, чи міститься певний об'єкт у колекції
    @Override
    public boolean contains(Object o) {
        Node<E> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Повертає ітератор для перебору елементів у колекції
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    // Перетворює колекцію на масив об'єктів
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        Node<E> current = head;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    // Перетворює колекцію на масив заданого типу
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int index = 0;
        Node<E> current = head;
        Object[] result = a;
        while (current != null) {
            result[index++] = current.data;
            current = current.next;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    // Додає новий елемент до колекції
    @Override
    public boolean add(E e) {
        if (contains(e)) {
            return false;
        }
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    // Видаляє заданий елемент з колекції
    @Override
    public boolean remove(Object o) {
        if (head == null) {
            return false;
        }

        if (head.data.equals(o)) {
            head = head.next;
            size--;
            return true;
        }

        Node<E> current = head;
        while (current.next != null && !current.next.data.equals(o)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            size--;
            return true;
        }

        return false;
    }

    // Перевіряє, чи містить колекція всі елементи заданої колекції
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    // Додає всі елементи з заданої колекції до цієї колекції
    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    // Зберігає лише ті елементи в цій колекції, які містяться в заданій колекції
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (!c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    // Видаляє з цієї колекції всі елементи, які містяться в заданій колекції
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object e : c) {
            if (remove(e)) {
                modified = true;
            }
        }
        return modified;
    }

    // Очищає колекцію
    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}

