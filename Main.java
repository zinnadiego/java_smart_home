import java.util.*;
import java.util.InputMismatchException;

// Clase base Electrodoméstico
class Electrodomestico {
    protected String nombre;
    protected boolean estado;
    
    public Electrodomestico(String nombre) {
        this.nombre = nombre;
        this.estado = false;
    }
    
    public void encender() {
        estado = true;
        System.out.println(nombre + " encendido");
    }
    
    public void apagar() {
        estado = false;
        System.out.println(nombre + " apagado");
    }
    
    public void mostrarEstado() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Estado: " + (estado ? "Encendido" : "Apagado"));
    }
    
    public boolean getEstado() {
        return estado;
    }
    
    public String getNombre() {
        return nombre;
    }
}

// Subclase Televisor
class Televisor extends Electrodomestico {
    private int volumen;
    private int canal;
    
    public Televisor(String nombre) {
        super(nombre);
        this.volumen = 50;
        this.canal = 1;
    }
    
    public void cambiarCanal(int nuevoCanal) {
        if(estado) {
            canal = nuevoCanal;
            System.out.println("Canal cambiado a: " + canal);
        } else {
            System.out.println("¡Enciende el televisor primero!");
        }
    }
    
    public void ajustarVolumen(int nuevoVolumen) {
        if(estado) {
            volumen = nuevoVolumen;
            System.out.println("Volumen ajustado a: " + volumen);
        } else {
            System.out.println("¡Enciende el televisor primero!");
        }
    }
    
    @Override
    public void mostrarEstado() {
        super.mostrarEstado();
        System.out.println("Canal actual: " + canal);
        System.out.println("Volumen actual: " + volumen);
    }
}

// Subclase Nevera
class Nevera extends Electrodomestico {
    private int temperatura;
    
    public Nevera(String nombre) {
        super(nombre);
        this.temperatura = 4;
    }
    
    public void ajustarTemperatura(int nuevaTemperatura) {
        if(estado) {
            temperatura = nuevaTemperatura;
            System.out.println("Temperatura ajustada a: " + temperatura + "°C");
        } else {
            System.out.println("¡Enciende la nevera primero!");
        }
    }
    
    @Override
    public void mostrarEstado() {
        super.mostrarEstado();
        System.out.println("Temperatura actual: " + temperatura + "°C");
    }
}

// Subclase Lavadora
class Lavadora extends Electrodomestico {
    private String[] programasLavado;
    private String programaActual;
    
    public Lavadora(String nombre, String[] programas) {
        super(nombre);
        this.programasLavado = programas;
        this.programaActual = programas.length > 0 ? programas[0] : "Ninguno";
    }
    
    public void seleccionarPrograma(String programa) {
        if(estado) {
            if(Arrays.asList(programasLavado).contains(programa)) {
                programaActual = programa;
                System.out.println("Programa seleccionado: " + programa);
            } else {
                System.out.println("Programa no disponible");
            }
        } else {
            System.out.println("¡Enciende la lavadora primero!");
        }
    }
    
    @Override
    public void mostrarEstado() {
        super.mostrarEstado();
        System.out.println("Programa actual: " + programaActual);
    }
}

// Clase Casa
class Casa {
    private List<Electrodomestico> electrodomesticos;
    
    public Casa() {
        electrodomesticos = new ArrayList<>();
    }
    
    public void añadirElectrodomestico(Electrodomestico e) {
        electrodomesticos.add(e);
    }
    
    public void eliminarElectrodomestico(int indice) {
        if(indice >= 0 && indice < electrodomesticos.size()) {
            electrodomesticos.remove(indice);
        }
    }
    
    public void encenderElectrodomestico(int indice) {
        Electrodomestico e = electrodomesticos.get(indice);
        e.encender();
    }
    
    public void apagarElectrodomestico(int indice) {
        Electrodomestico e = electrodomesticos.get(indice);
        e.apagar();
    }
    
    public void mostrarEstados() {
        for(Electrodomestico e : electrodomesticos) {
            e.mostrarEstado();
            System.out.println("-------------------");
        }
    }
    
    public void encenderTodos() {
        for(Electrodomestico e : electrodomesticos) {
            e.encender();
        }
    }
    
    public void apagarTodos() {
        for(Electrodomestico e : electrodomesticos) {
            e.apagar();
        }
    }
    
    public List<Electrodomestico> getElectrodomesticos() {
        return electrodomesticos;
    }
}

// Interfaz de usuario
public class Main {
    private static Casa casa = new Casa();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean salir = false;
        
        while(!salir) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            
            switch(opcion) {
                case 1:
                    añadirElectrodomestico();
                    break;
                case 2:
                    eliminarElectrodomestico();
                    break;
                case 3:
                    operarElectrodomestico(false);
                    break;
                case 4:
                    operarElectrodomestico(true);
                    break;
                case 5:
                    casa.mostrarEstados();
                    break;
                case 6:
                    casa.encenderTodos();
                    break;
                case 7:
                    casa.apagarTodos();
                    break;
                case 8:
                    operarFuncionalidadEspecifica();
                    break;
                case 9:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
    
    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Añadir electrodoméstico");
        System.out.println("2. Eliminar electrodoméstico");
        System.out.println("3. Encender electrodoméstico");
        System.out.println("4. Apagar electrodoméstico");
        System.out.println("5. Mostrar estados");
        System.out.println("6. Encender todos");
        System.out.println("7. Apagar todos");
        System.out.println("8. Operar funcionalidad específica");
        System.out.println("9. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private static int leerOpcion() {
        while(true) {
            try {
                return scanner.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("¡Entrada inválida! Introduce un número.");
                scanner.nextLine();
            }
        }
    }
    
    private static void añadirElectrodomestico() {
        scanner.nextLine();
        System.out.print("\nTipo de electrodoméstico (TV, Nevera, Lavadora): ");
        String tipo = scanner.nextLine();
        System.out.print("Nombre del electrodoméstico: ");
        String nombre = scanner.nextLine();
        
        switch(tipo.toLowerCase()) {
            case "tv":
                casa.añadirElectrodomestico(new Televisor(nombre));
                break;
            case "nevera":
                casa.añadirElectrodomestico(new Nevera(nombre));
                break;
            case "lavadora":
                System.out.print("Programas de lavado (separados por coma): ");
                String[] programas = scanner.nextLine().split(",");
                casa.añadirElectrodomestico(new Lavadora(nombre, programas));
                break;
            default:
                System.out.println("Tipo no válido");
        }
    }
    
    private static void eliminarElectrodomestico() {
        List<Electrodomestico> lista = casa.getElectrodomesticos();
        System.out.println("\nElectrodomésticos disponibles:");
        for(int i = 0; i < lista.size(); i++) {
            System.out.println((i+1) + ". " + lista.get(i).getNombre());
        }
        System.out.print("Seleccione el índice a eliminar: ");
        int indice = leerOpcion() - 1;
        if(indice >= 0 && indice < lista.size()) {
            casa.eliminarElectrodomestico(indice);
            System.out.println("Electrodoméstico eliminado");
        } else {
            System.out.println("Índice inválido");
        }
    }
    
    private static void operarElectrodomestico(boolean apagar) {
        List<Electrodomestico> lista = casa.getElectrodomesticos();
        System.out.println("\nElectrodomésticos disponibles:");
        for(int i = 0; i < lista.size(); i++) {
            System.out.println((i+1) + ". " + lista.get(i).getNombre());
        }
        System.out.print("Seleccione el índice: ");
        int indice = leerOpcion() - 1;
        
        if(indice >= 0 && indice < lista.size()) {
            if(apagar) {
                casa.apagarElectrodomestico(indice);
            } else {
                casa.encenderElectrodomestico(indice);
            }
        } else {
            System.out.println("Índice inválido");
        }
    }
    
    private static void operarFuncionalidadEspecifica() {
        List<Electrodomestico> lista = casa.getElectrodomesticos();
        System.out.println("\nElectrodomésticos disponibles:");
        for(int i = 0; i < lista.size(); i++) {
            System.out.println((i+1) + ". " + lista.get(i).getNombre());
        }
        System.out.print("Seleccione un electrodoméstico: ");
        int indice = leerOpcion() - 1;
        
        if(indice < 0 || indice >= lista.size()) {
            System.out.println("Índice inválido");
            return;
        }
        
        Electrodomestico e = lista.get(indice);
        
        if(e instanceof Televisor) {
            manejarTelevisor((Televisor) e);
        } else if(e instanceof Nevera) {
            manejarNevera((Nevera) e);
        } else if(e instanceof Lavadora) {
            manejarLavadora((Lavadora) e);
        } else {
            System.out.println("Electrodoméstico no reconocido");
        }
    }
    
    private static void manejarTelevisor(Televisor tv) {
        System.out.println("\nOperaciones del televisor:");
        System.out.println("1. Cambiar canal");
        System.out.println("2. Ajustar volumen");
        System.out.print("Seleccione una opción: ");
        
        switch(leerOpcion()) {
            case 1:
                System.out.print("Nuevo canal: ");
                tv.cambiarCanal(scanner.nextInt());
                break;
            case 2:
                System.out.print("Nuevo volumen: ");
                tv.ajustarVolumen(scanner.nextInt());
                break;
            default:
                System.out.println("Opción inválida");
        }
    }
    
    private static void manejarNevera(Nevera nevera) {
        System.out.print("\nNueva temperatura: ");
        nevera.ajustarTemperatura(scanner.nextInt());
    }
    
    private static void manejarLavadora(Lavadora lavadora) {
        scanner.nextLine();
        System.out.print("\nPrograma a seleccionar: ");
        lavadora.seleccionarPrograma(scanner.nextLine());
    }
}
