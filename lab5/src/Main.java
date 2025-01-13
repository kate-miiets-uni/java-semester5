public class Main {
    public static void main(String[] args) {
        try {
            // Створення об'єктів електроприладів
            ElectroDevice washingMachine = new WashingMachine("Пральна машина", 2000, 15);
            ElectroDevice refrigerator = new Refrigerator("Холодильник", 1500, 10);
            ElectroDevice microwave = new Microwave("Мікрохвильова піч", 1000, 20);

            // Створення масиву електроприладів
            ElectroDevice[] devices = {washingMachine, refrigerator, microwave};

            // Створення об'єкта Apartment
            Apartment apartment = new Apartment(devices);

            // Увімкнення деяких електроприладів
            apartment.turnOnDevice("Пральна машина");
            apartment.turnOnDevice("Мікрохвильова піч");

            // Підрахунок споживаної потужності
            int totalPower = apartment.calculateTotalPowerConsumption();
            System.out.println("Загальна споживана потужність: " + totalPower + " Вт");

            // Сортування електроприладів за потужністю
            apartment.sortDevicesByPower();
            System.out.println("\nЕлектроприлади відсортовані за потужністю:");
            for (ElectroDevice device : apartment.devices) {
                System.out.println(device.getName() + " - " + device.getPower() + " Вт");
            }

            // Пошук приладу у заданому діапазоні електромагнітного випромінювання
            ElectroDevice foundDevice = apartment.findDeviceByRadiationRange(10, 15);
            System.out.println("\nЗнайдений прилад: " + foundDevice.getName());

        } catch (IllegalArgumentException e) {
            System.err.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Непередбачувана помилка: " + e.getMessage());
        }
    }
}

