import java.util.Arrays;
import java.util.Comparator;

//Клас для представлення квартири з електроприладами.
public class Apartment {
    ElectroDevice[] devices;

    public Apartment(ElectroDevice[] devices) {
        this.devices = devices;
    }

    // Увімкнення заданого електроприладу.
    public void turnOnDevice(String name) {
        for (ElectroDevice device : devices) {
            if (device.getName().equals(name)) {
                device.turnOn();
                return;
            }
        }
        throw new IllegalArgumentException("Електроприлад з назвою " + name + " не знайдено.");
    }

    // Підрахунок споживаної потужності увімкнених приладів.
    public int calculateTotalPowerConsumption() {
        int totalPower = 0;
        for (ElectroDevice device : devices) {
            if (device.isOn()) {
                totalPower += device.getPower();
            }
        }
        return totalPower;
    }

    // Сортування електроприладів за потужністю.
    public void sortDevicesByPower() {
        Arrays.sort(devices, Comparator.comparingInt(ElectroDevice::getPower));
    }

    // Пошук електроприладів у заданому діапазоні електромагнітного випромінювання.
    public ElectroDevice findDeviceByRadiationRange(int minRadiation, int maxRadiation) {
        for (ElectroDevice device : devices) {
            if (device.getElectromagneticRadiation() >= minRadiation && device.getElectromagneticRadiation() <= maxRadiation) {
                return device;
            }
        }
        throw new IllegalArgumentException("Прилад у заданому діапазоні електромагнітного випромінювання не знайдено.");
    }
}
