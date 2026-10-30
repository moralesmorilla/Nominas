package empresa.modelo;

public class Empleado {

    private final String nombre;
    private final String dni;
    private final String sexo;
    private final int categoria;
    private final int anyos;

    public Empleado(String nombre, String dni, String sexo, int categoria, int anyos) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
        this.categoria = categoria;
        this.anyos = anyos;
    }

    public String getNombre() { return nombre; }

    public String getDni() { return dni; }

    public String getSexo() { return sexo; }

    public int getCategoria() { return categoria; }

    public int getAnyos() { return anyos; }
}