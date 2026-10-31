interface Switchable {
    void on();
    void off();
    boolean isOn();
    default void toggle() { if (isOn()) off(); else on(); }
}
class Fan implements Switchable {
    private boolean on;
    public void on() { on = true; System.out.println("Fan ON"); }
    public void off() { on = false; System.out.println("Fan OFF"); }
    public boolean isOn() { return on; }
}
class Light implements Switchable {
    private boolean on;
    public void on() { on = true; System.out.println("Light ON"); }
    public void off() { on = false; System.out.println("Light OFF"); }
    public boolean isOn() { return on; }
}
interface Permission { boolean maySwitchOn(Switchable device, int hour); }

public class RemoteControl {
    public static void main(String[] args) {
        Switchable[] devices = {new Fan(), new Light()};
        for (Switchable device : devices) device.toggle();
        Permission anonymousPermission = new Permission() {
            @Override public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };
        Permission lambdaPermission = (device, hour) -> hour >= 6 && hour <= 22;
        System.out.println("Anonymous: " + anonymousPermission.maySwitchOn(devices[0], 10));
        System.out.println("Lambda: " + lambdaPermission.maySwitchOn(devices[1], 23));
        devices[0].toggle();
    }
}
