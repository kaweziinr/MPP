/**
 * 
 */
/**
 * @author kawec
 *
 */
// A interface componente declara operações comuns para ambos os
// objetos simples e complexos de uma composição.
interface Graphic {
    void move(int x, int y);
    void draw();
}

// A classe folha representa objetos finais de uma composição.
// Um objeto folha não pode ter quaisquer sub-objetos.
// Geralmente, são os objetos folha que fazem o verdadeiro
// trabalho, enquanto que os objetos composite somente delegam
// para seus sub componentes.
class Dot implements Graphic {
    protected int x, y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
        System.out.println("Move Dot to (" + this.x + ", " + this.y + ")");
    }

    @Override
    public void draw() {
        System.out.println("Draw Dot at (" + x + ", " + y + ")");
    }
}

// Todas as classes componente estendem outros componentes.
class Circle extends Dot {
    private int radius;

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Draw Circle at (" + x + ", " + y + ") with radius " + radius);
    }
}

// A classe composite representa componentes complexos que podem
// ter filhos. Objetos composite geralmente delegam o verdadeiro
// trabalho para seus filhos e então "somam" o resultado.
class CompoundGraphic implements Graphic {
    private final java.util.List<Graphic> children = new java.util.ArrayList<>();

    // Um objeto composite pode adicionar ou remover outros
    // componentes (tanto simples como complexos) para ou de sua
    // lista de filhos.
    public void add(Graphic child) {
        children.add(child);
    }

    public void remove(Graphic child) {
        children.remove(child);
    }

    @Override
    public void move(int x, int y) {
        for (Graphic child : children) {
            child.move(x, y);
        }
    }

    @Override
    public void draw() {
        System.out.println("CompoundGraphic draws its components:");
        for (Graphic child : children) {
            child.draw();
        }
    }
}

// O código cliente trabalha com todos os componentes através de
// suas interfaces base. Dessa forma o código cliente pode
// suportar componentes folha simples e composites complexos.
class ImageEditor {
    private CompoundGraphic all;

    public void load() {
        all = new CompoundGraphic();
        all.add(new Dot(1, 2));
        all.add(new Circle(5, 3, 10));
    }

    // Combina componentes selecionados em um componente
    // composite complexo.
    public void groupSelected(Graphic[] components) {
        CompoundGraphic group = new CompoundGraphic();
        for (Graphic component : components) {
            group.add(component);
            all.remove(component);
        }
        all.add(group);
        all.draw();
    }
}

// Código cliente para testar a composição gráfica.
public class Main {
    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor();
        editor.load();

        // Criando um array de gráficos para agrupar.
        Graphic[] selectedGraphics = {
            new Dot(1, 2),
            new Circle(5, 3, 10)
        };
        
        // Agrupando e desenhando os componentes selecionados.
        editor.groupSelected(selectedGraphics);
    }
}

