// Узагальнений клас для представлення електроприладу.
public abstract class ElectroDevice {
    private String name; // Назва приладу
    private int power; // Потужність у ватах
    private boolean isOn; // Увімкнений чи ні
    private int electromagneticRadiation; // Електромагнітне випромінювання у мкТл

    public ElectroDevice(String name, int power, int electromagneticRadiation) {
        this.name = name;
        this.power = power;
        this.electromagneticRadiation = electromagneticRadiation;
        this.isOn = false; // За замовчуванням прилад вимкнений
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public boolean isOn() {
        return isOn;
    }

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public int getElectromagneticRadiation() {
        return electromagneticRadiation;
    }
}
