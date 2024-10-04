/**
 * 
 */
/**
 * @author kawec
 *
 */

// A "abstração" define a interface para a parte "controle" das
// duas hierarquias de classe. Ela mantém uma referência a um
// objeto da hierarquia de "implementação" e delega todo o
// trabalho real para esse objeto.
abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public void togglePower() {
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    public void volumeDown() {
        device.setVolume(device.getVolume() - 10);
    }

    public void volumeUp() {
        device.setVolume(device.getVolume() + 10);
    }

    public void channelDown() {
        device.setChannel(device.getChannel() - 1);
    }

    public void channelUp() {
        device.setChannel(device.getChannel() + 1);
    }
}

// Você pode estender classes a partir dessa hierarquia de
// abstração independentemente das classes de dispositivo.
class AdvancedRemoteControl extends RemoteControl {

    public AdvancedRemoteControl(Device device) {
        super(device);
    }

    public void mute() {
        device.setVolume(0);
    }
}

// Criando uma classe concreta que herda de RemoteControl
class BasicRemoteControl extends RemoteControl {

    public BasicRemoteControl(Device device) {
        super(device);
    }

    // Você pode adicionar funcionalidades extras aqui, se necessário
}

// A interface "implementação" declara métodos comuns a todas as
// classes concretas de implementação. Ela não precisa coincidir
// com a interface de abstração. Tipicamente, a interface de
// implementação fornece apenas operações primitivas, enquanto
// a abstração define operações de alto nível baseadas
// naquelas primitivas.
interface Device {
    boolean isEnabled();
    void enable();
    void disable();
    int getVolume();
    void setVolume(int percent);
    int getChannel();
    void setChannel(int channel);
}

// Todos os dispositivos seguem a mesma interface.
class Tv implements Device {
    private boolean on = false;
    private int volume = 50;
    private int channel = 1;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
        System.out.println("TV ligada");
    }

    @Override
    public void disable() {
        on = false;
        System.out.println("TV desligada");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        this.volume = percent;
        System.out.println("Volume da TV ajustado para: " + volume);
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("Canal da TV ajustado para: " + channel);
    }
}

class Radio implements Device {
    private boolean on = false;
    private int volume = 30;
    private int channel = 101;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enable() {
        on = true;
        System.out.println("Rádio ligado");
    }

    @Override
    public void disable() {
        on = false;
        System.out.println("Rádio desligado");
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        this.volume = percent;
        System.out.println("Volume do rádio ajustado para: " + volume);
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel = channel;
        System.out.println("Canal do rádio ajustado para: " + channel);
    }
}

// Código cliente
public class Main {
    public static void main(String[] args) {
        Tv tv = new Tv();
        BasicRemoteControl remote = new BasicRemoteControl(tv);  // Agora instanciamos BasicRemoteControl
        remote.togglePower();  // Liga a TV
        remote.volumeUp();     // Aumenta o volume da TV
        remote.channelUp();    // Muda o canal da TV

        Radio radio = new Radio();
        AdvancedRemoteControl advancedRemote = new AdvancedRemoteControl(radio);
        advancedRemote.togglePower();  // Liga o rádio
        advancedRemote.mute();         // Muta o rádio
    }
}
