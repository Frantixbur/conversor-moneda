import com.clasedelconversor.Conversor;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        String moneda1 = "";
        String moneda2 = "";
        double monto = 0;

        while (opcion != 7) {
            System.out.println("Sea bienvenido/a al Conversor de Moneda =)");
            System.out.println("----------  Menú  ----------");
            System.out.println("1. Dólar --> Peso Argentino");
            System.out.println("2. Peso Argentino --> Dólar");
            System.out.println("3. Dólar --> Real Brasileño");
            System.out.println("4. Real Brasileño --> Dólar");
            System.out.println("5. Dólar --> Euro");
            System.out.println("6. Euro --> Dólar");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción (1-7): ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        moneda1 = "USD";
                        moneda2 = "ARS";
                        break;
                    case 2:
                        moneda1 = "ARS";
                        moneda2 = "USD";
                        break;
                    case 3:
                        moneda1 = "USD";
                        moneda2 = "BRL";
                        break;
                    case 4:
                        moneda1 = "BRL";
                        moneda2 = "USD";
                        break;
                    case 5:
                        moneda1 = "USD";
                        moneda2 = "EUR";
                        break;
                    case 6:
                        moneda1 = "EUR";
                        moneda2 = "USD";
                        break;
                    case 7:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, selecciona un número del 1 al 7.");
                        break;
                }

                if (opcion >= 1 && opcion <= 6) {
                    System.out.print("Ingrese un monto de dinero: ");
                    if (scanner.hasNextDouble()) {
                        monto = scanner.nextDouble();
                        String direccion = "https://v6.exchangerate-api.com/v6/2107647882babc9576a4ea4c/pair/" + moneda1 + "/" + moneda2 + "/" + monto;
                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(direccion))
                                .build();
                        HttpResponse<String> response = client
                                .send(request, HttpResponse.BodyHandlers.ofString());

                        String json = response.body();
                        //System.out.println(json);

                        Gson gson = new Gson();
                        Conversor miConversor = gson.fromJson(json, Conversor.class);

                        System.out.println("");
                        System.out.println("*****************************************************");
                        System.out.println("");
                        System.out.println("Moneda de Origen: " + miConversor.monedaOrigen);
                        System.out.println("Moneda Objetivo: " + miConversor.monedaObjetivo);
                        System.out.println("El monto ingresado es de: $" + monto + " " + miConversor.monedaOrigen);
                        System.out.println("El valor de la moneda individual es de: $" + miConversor.valorMoneda + " " + miConversor.monedaObjetivo);
                        System.out.println("El resultado de la conversión según el monto es de: $" + miConversor.resultadoDeConversion + " " + miConversor.monedaObjetivo);
                        System.out.println("");
                        System.out.println("*****************************************************");
                        System.out.println("");
                    } else {
                        System.out.println("Monto no válido. Por favor, ingrese un número válido.");
                        scanner.next(); // Limpiar la entrada no válida
                    }
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese un número del 1 al 7.");
                scanner.next(); // Limpiar la entrada no válida
            }
            System.out.println(); // Salto de línea para una mejor legibilidad
        }
        scanner.close();
    }
}