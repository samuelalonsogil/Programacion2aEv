package HiloEjemplos;

public class TestHiloAYB {
public static void main(String[] args) {
	Contador c= new Contador(10);
	HiloA ha= new HiloA("Hiloa",c );
	HiloB hb= new HiloB("Hilob",c );
	//Se crean los dos hilos y se ponen en marcha, aumentando y disminuyendo el contador a intervalos iguales
	ha.start();
	hb.start();

}
}
