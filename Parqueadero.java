import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parqueadero {

    List<Puesto> puestos;
    double costoHora;
    double ingresosTotales;

    public Parqueadero() {
        puestos = new ArrayList<>();
        for (int i = 1; i <= 40; i++) {
            puestos.add(new Puesto(i));
        }
        this.costoHora = 2.75;
    }

    public void ingresoAuto(String nombre, String apellido, String cedula, String referenteAu, String placa, int horaEntrada) {

        Puesto lugarLibre = null;
        // ciclo for-each sirve para trabajar con los elementos de manera directa y no topa al indice 
        for (Puesto puesto : puestos) { 
            if (puesto.autoIngre == null) {
                lugarLibre = puesto;
                break;
            }
        }

        if (lugarLibre != null) {
            Auto autoNuevo = new Auto(nombre, apellido, cedula, referenteAu, placa, horaEntrada);
            lugarLibre.autoIngre = autoNuevo;
            System.out.println("Ingresado. Su auto está en el lugar " + lugarLibre.nLugar);

        } else {
            System.out.println("Estacionamiento lleno!!");
        }
    }

    public void saleAuto(String placa, int horaSalida) {

        Puesto lugarOcupado = null;

        for (Puesto puesto : puestos) {

            if (puesto.autoIngre != null && puesto.autoIngre.placa.equals(placa)) {
                lugarOcupado = puesto;
                break;
            }
        }

        if (lugarOcupado != null) {
            int tiempoParqueo = horaSalida - lugarOcupado.autoIngre.horaEntrada;
            double costo = tiempoParqueo * costoHora;
            System.out.println("El auto con su placa " + placa + " salió del lugar " + lugarOcupado.nLugar);
            System.out.println("Tiempo en el parqueadero: " + tiempoParqueo + " horas.");
            System.out.println("Total a pagar: $" + costo);
            ingresosTotales += costo;
            lugarOcupado.autoIngre = null;
        } else {
            System.out.println("No se encontró el auto con placa " + placa + " en el parqueadero.");
        }
    }

    public void lugaresLibres() {
        int lugaresDisponibles = 0;

        for (Puesto puesto : puestos) {
            if (puesto.autoIngre == null) {
                lugaresDisponibles++;
            }
        }

        System.out.println("Lugares libres: " + lugaresDisponibles);
    }

    public void lugarOcupado() {
        System.out.println("Datos de clientes y su auto:");

        for (Puesto puesto : puestos) {
            if (puesto.autoIngre != null) {
                Auto auto = puesto.autoIngre;
                System.out.println("Puesto: " + puesto.nLugar);
                System.out.println("Nombre: " + auto.nombre + " " + auto.apellido);
                System.out.println("Cédula: " + auto.cedula);
                System.out.println("Referente: " + auto.referenteAu);
                System.out.println("Placa: " + auto.placa);
                System.out.println("Hora de entrada: " + auto.horaEntrada);
                System.out.println("-----------------------------");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parqueadero parqueadero = new Parqueadero();
        System.out.println("*******Bienvenido al parqueadero*******");

        while (true) {
            System.out.println("Escoja una opción:");
            System.out.println("1. Mirar lugares libres");
            System.out.println("2. Ingresar datos");
            System.out.println("3. Consulta de pago");
            System.out.println("4. Ver datos de los lugares ocupados");
            System.out.println("5. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    parqueadero.lugaresLibres();
                    break;
                case 2:
                    System.out.println("Ingrese su nombre: ");
                    String nombre = scanner.next();
                    System.out.println("Ingrese su apellido: ");
                    String apellido = scanner.next();
                    System.out.println("Ingrese su cédula: ");
                    String cedula = scanner.next();
                    System.out.println("Ingrese el referente de su auto: ");
                    String referenteAu = scanner.next();
                    System.out.println("Ingrese la placa del auto: ");
                    String placa = scanner.next();
                    System.out.println("Ingrese la hora de entrada: ");
                    int horaEntrada = scanner.nextInt();
                    parqueadero.ingresoAuto(nombre, apellido, cedula, referenteAu, placa, horaEntrada);
                    break;
                case 3:
                    System.out.println("Ingrese la placa: ");
                    String placaSalida = scanner.next();
                    System.out.println("Ingrese la hora de salida: ");
                    int horaSalida = scanner.nextInt();
                    parqueadero.saleAuto(placaSalida, horaSalida);
                    break;
                case 4:
                    parqueadero.lugarOcupado();
                    break;
                case 5:
                    System.out.println("Ingreso total del parqueadero: $" + parqueadero.ingresosTotales);
                    scanner.close();
                    System.out.println("Gracias por utilizar el parqueadero");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ingrese un número de 1 al 5.");
            }
        }
    }

    class Auto {
        String placa;
        int horaEntrada;
        String nombre;
        String apellido;
        String cedula;
        String referenteAu;

        public Auto(String nombre, String apellido, String cedula, String referenteAu, String placa, int horaEntrada) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.cedula = cedula;
            this.referenteAu = referenteAu;
            this.placa = placa;
            this.horaEntrada = horaEntrada;
        }
    }

    class Puesto {
        int nLugar;
        Auto autoIngre;

        public Puesto(int nLugar) {
            this.nLugar = nLugar;
        }
    }
}
