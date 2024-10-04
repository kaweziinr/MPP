/**
 * 
 */
/**
 * @author kawec
 *
 */
// Digamos que você tenha duas classes com interfaces
// compatíveis: RoundHole (Buraco Redondo) e RoundPeg (Pino
// Redondo).
class RoundHole {
	private double radius;

	public RoundHole(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius; // Retorna o raio do buraco.
	}

	public boolean fits(RoundPeg peg) {
		return this.getRadius() >= peg.getRadius();
	}
}

class RoundPeg {
	private double radius;

	public RoundPeg(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius; // Retorna o raio do pino.
	}
}

// Mas tem uma classe incompatível: SquarePeg (Pino Quadrado).
class SquarePeg {
	private double width;

	public SquarePeg(double width) {
		this.width = width;
	}

	public double getWidth() {
		return width; // Retorna a largura do pino quadrado.
	}
}

// Uma classe adaptadora permite que você encaixe pinos
// quadrados em buracos redondos. Ela estende a classe RoundPeg
// para permitir que objetos do adaptador ajam como pinos
// redondos.
class SquarePegAdapter extends RoundPeg {
	private SquarePeg peg;

	public SquarePegAdapter(SquarePeg peg) {
		super(0); // Chamando o construtor da superclasse com 0.
		this.peg = peg;
	}

	@Override
	public double getRadius() {
		// O adaptador finge que é um pino redondo com um raio
		// que encaixaria o pino quadrado que o adaptador está
		// envolvendo.
		return peg.getWidth() * Math.sqrt(2) / 2;
	}
}

// Em algum lugar no código cliente.
public class Main {
	public static void main(String[] args) {
		RoundHole hole = new RoundHole(5);
		RoundPeg rpeg = new RoundPeg(5);
		System.out.println(hole.fits(rpeg)); // true

		SquarePeg smallSqPeg = new SquarePeg(5);
		SquarePeg largeSqPeg = new SquarePeg(10);

		// Isso não vai compilar (tipos incompatíveis).
		// hole.fits(smallSqPeg); // Comentado para evitar erro de compilação

		SquarePegAdapter smallSqPegAdapter = new SquarePegAdapter(smallSqPeg);
		SquarePegAdapter largeSqPegAdapter = new SquarePegAdapter(largeSqPeg);

		System.out.println(hole.fits(smallSqPegAdapter)); // true
		System.out.println(hole.fits(largeSqPegAdapter)); // false
	}
}
