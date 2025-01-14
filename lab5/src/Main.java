public class Main {
    public static void main(String[] args) {
        // Створення об'єктів електроприладів
        ElectroDevice washingMachine = new WashingMachine("Пральна машина", 2000, 15);
        ElectroDevice refrigerator = new Refrigerator("Холодильник", 1500, 10);
        ElectroDevice microwave = new Microwave("Мікрохвильова піч", 1000, 20);

        // Створення об'єкта ElectroDeviceSet з одним об'єктом
        ElectroDeviceSet<ElectroDevice> singleDeviceSet = new ElectroDeviceSet<>(washingMachine);
        System.out.println("Розмір колекції з одним об'єктом: " + singleDeviceSet.size());

        // Створення об'єкта ElectroDeviceSet з колекцією об'єктів
        ElectroDeviceSet<ElectroDevice> deviceSet = new ElectroDeviceSet<>();
        deviceSet.add(washingMachine);
        deviceSet.add(refrigerator);
        deviceSet.add(microwave);
        System.out.println("Розмір колекції після додавання трьох об'єктів: " + deviceSet.size());

        // Перевірка наявності об'єкта
        System.out.println("Чи містить колекція холодильник? " + deviceSet.contains(refrigerator));

        // Видалення об'єкта
        deviceSet.remove(microwave);
        System.out.println("Розмір колекції після видалення мікрохвильової печі: " + deviceSet.size());

        // Створення іншої колекції для перевірки containsAll
        ElectroDeviceSet<ElectroDevice> anotherSet = new ElectroDeviceSet<>();
        anotherSet.add(washingMachine);
        anotherSet.add(refrigerator);

        // Перевірка, чи містить колекція deviceSet всі елементи з anotherSet
        System.out.println("Чи містить deviceSet всі елементи з anotherSet? " + deviceSet.containsAll(anotherSet));

        // Додавання microwave до anotherSet
        anotherSet.add(microwave);

        // Перевірка, чи містить колекція deviceSet всі елементи з anotherSet
        System.out.println("Чи містить deviceSet всі елементи з anotherSet після додавання мікрохвильової печі? " + deviceSet.containsAll(anotherSet));

        // Видалення всіх елементів з deviceSet, які містяться в anotherSet
        deviceSet.removeAll(anotherSet);
        System.out.println("Розмір колекції після видалення елементів з anotherSet: " + deviceSet.size());

    }
}
