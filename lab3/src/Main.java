public class Main {
    public static void main(String[] args) {
        // Створення масиву об'єктів класу Літак
        Plane[] planes = {
                new Plane("Boeing 737", 200, 8000, 20000, 1980),
                new Plane("Airbus A320", 180, 7800, 18000, 1990),
                new Plane("Concorde", 100, 7200, 14000, 1976),
                new Plane("Boeing 747", 400, 15000, 38000, 1969),
                new Plane("Airbus A380", 600, 15500, 45000, 2005)
        };

        // Сортування за числом пасажирів за зростанням
        sortPlanes(planes, "passengerCapacity", true);
        System.out.println("Сортування за числом пасажирів за зростанням: ");
        printPlanes(planes);

        // Сортування за дальністю польоту за спаданням
        sortPlanes(planes, "range", false);
        System.out.println("\nСортування за дальністю польоту за спаданням: ");
        printPlanes(planes);

        // Пошук об'єкта ідентичного заданому
        Plane target = new Plane("Concorde", 100, 7200, 14000, 1976);
        int index = findPlaneIndex(planes, target);
        System.out.println("\nПошук об'єкта, ідентичного заданому: ");
        if (index != -1) {
            System.out.println("Об'єкт знайдено на позиції: " + index);
        } else {
            System.out.println("Об'єкт не знайдено.");
        }
    }

    // Метод для сортування літаків за певною ознакою за зростанням або спаданням
    public static void sortPlanes(Plane[] planes, String field, boolean ascending) {
        // Використовуємо алгоритм бульбашкового сортування для порівняння літаків
        for (int i = 0; i < planes.length-1; i++){
            for (int j = 0; j < planes.length-1-i; j++){
                boolean condition = false;
                switch (field) {
                    // Сортування за числом пасажирі
                    case "passengerCapacity":
                        condition = ascending ?
                                planes[j].getPassengerCapacity() > planes[j+1].getPassengerCapacity() :
                                planes[j].getPassengerCapacity() < planes[j+1].getPassengerCapacity();
                        break;
                    // Сортування за дальністю польоту
                    case "range":
                        condition = ascending ?
                                planes[j].getRange() > planes[j+1].getRange() :
                                planes[j].getRange() < planes[j+1].getRange();
                        break;
                    // Сортування за максимальною злітною вагою
                    case "maxTakeOffWeight":
                        condition = ascending ?
                                planes[j].getMaxTakeoffWeight() > planes[j+1].getMaxTakeoffWeight() :
                                planes[j].getMaxTakeoffWeight() < planes[j+1].getMaxTakeoffWeight();
                        break;
                    // Сортування за роком виробництва
                    case "yearOfManufacture":
                        condition = ascending ?
                                planes[j].getYearOfManufacture() > planes[j+1].getYearOfManufacture() :
                                planes[j].getYearOfManufacture() < planes[j+1].getYearOfManufacture();
                        break;
                    // Сортування за моделлю
                    case "model":
                        condition = ascending ?
                                planes[j].getModel().compareTo(planes[j + 1].getModel()) > 0 :
                                planes[j].getModel().compareTo(planes[j + 1].getModel()) < 0;
                        break;

                }
                // Виконуємо обмін елементів, якщо умова виконується
                if (condition) {
                    Plane temp = planes[j];
                    planes[j] = planes[j+1];
                    planes[j+1] = temp;
                }
            }
        }
    }

    // Метод для виведення масиву літаків
    public static void printPlanes(Plane[] planes){
        for (Plane plane : planes){
            System.out.println(plane);
        }
    }

    // Метод для пошуку індексу літака, ідентичного заданому
    public static int findPlaneIndex(Plane[] planes, Plane target) {
        // Перебір масиву для пошуку об'єкта, який дорівнює заданому
        for (int i = 0; i < planes.length; i++) {
            if (planes[i].equals(target)) {
                return i; // Повертаємо індекс знайденого об'єкта
            }
        }
        return -1; // Повертаємо -1, якщо об'єкт не знайдено
    }

}

// Клас Літак
class Plane{
    private String model; // Модель літака
    private int passengerCapacity; // Кількість пасажирів
    private int range; // Дальність польоту (в кілометрах)
    private int maxTakeoffWeight; // Максимальна злітна вага (в кілограмах)
    private int yearOfManufacture; // Рік виробництва

    // Конструктор класу Літак
    public Plane(String model, int passengerCapacity, int range, int maxTakeoffWeight, int yearOfManufacture){
        this.model = model;
        this.passengerCapacity = passengerCapacity;
        this.range = range;
        this.maxTakeoffWeight = maxTakeoffWeight;
        this.yearOfManufacture = yearOfManufacture;
    }

    // Геттери для полів класу Літак
    public String getModel(){
        return model;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getRange(){
        return range;
    }

    public int getMaxTakeoffWeight() {
        return maxTakeoffWeight;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    // Переозначення методу toString для виведення інформації про літак
    public String toString() {
        return "Літак: модель - "+ model + ", " +
                "кількість пасажирів - "+ passengerCapacity + ", " +
                "дальність польоту - "+ range + ", " +
                "максимальна злітна вага - "+ maxTakeoffWeight + ", " +
                "рік виробництва - "+ yearOfManufacture + ".";
    }

    // Переозначення методу equals для порівняння літаків
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plane plane = (Plane) o;
        return passengerCapacity == plane.passengerCapacity &&
                range == plane.range &&
                maxTakeoffWeight == plane.maxTakeoffWeight &&
                yearOfManufacture == plane.yearOfManufacture &&
                model.equals(plane.model);

    }
}

