package empresa.modelo;

public class Nomina {

    private final String dni;
    private final int sueldo;

    public Nomina(String dni, int sueldo) {
        this.dni = dni;
        this.sueldo = sueldo;
    }

    public String getDni() {
        return dni;
    }

    public int getSueldo() {
        return sueldo;
    }
}
