import java.util.Scanner;
/**
 * Esta clase permite hacer el cálculo del servicio del parqueadero,
 * para esto el usuario debe ingresar la hora de entrada, la hora de
 * salida y el tipo de vehículo. Adicionalmente, permite llevar el
 * valor recaudado en el día.
 * 
 * @author Lida Heliana Arenas Mogollón
 * @version 11-08-2017
 */
public class Parqueadero{
    //Valores de la tarífa por minuto del carro y de la moto
    private final long[] TARIFAS = {48, 67};
    private final int TIPO_MOTO = 0;
    private final int TIPO_CARRO = 1;
    
    //Variables del sistema
    private Scanner teclado;
    private long recaudo;
    
    //Método principal
    public static void main(String[] args){
        Parqueadero p = new Parqueadero();
    }
    
    //Constructor de la clase
    public Parqueadero(){
        //Variables para el método
        int opcion = 1;
        int horaE = 0;//Hora de entrada
        int minE = 0;//Minutos entrada
        int horaS = 0;//Hora de salida
        int minS = 0;//Minutos de salida y total de minutos
        int tipo;//Tipo de vehículo
        long valor;//Valor del servicio
        String tmp;//Variable temporal
        
        //inicialiación de las variables del sistema
        teclado = new Scanner(System.in);
        recaudo = 0;
        
        while(opcion != 2){
            //imprimir menú
            System.out.println("+--------------------------------------+");
            System.out.println("|             MENU PRINCIPAL           |");
            System.out.println("+--------------------------------------+");
            System.out.println("| 1) Calcular valor del servicio.      |");
            System.out.println("| 2) Salir del programa.               |");
            System.out.println("+--------------------------------------+");
            System.out.println(":::::");
            System.out.printf( "  Recaudado: $%d\n",recaudo);
            System.out.println(":::::");
            System.out.println("\nDigíte su opción por favor: ");
            opcion = teclado.nextInt();
            if(opcion == 1){
                do{
                    System.out.println("\nDigíte la hora de entrada. HH:mm");
                    System.out.println("Ejemplo: 13:30");
                    tmp = teclado.next()+":";
                    Scanner leerHora = new Scanner(tmp).useDelimiter(":");
                    horaE = leerHora.nextInt();
                    minE = leerHora.nextInt();
                    leerHora.close();
                }while(!horaValida(horaE,minE));
                do{
                    System.out.println("\nDigíte la hora de salida. HH:mm");
                    System.out.println("Ejemplo: 16:45");
                    tmp = teclado.next()+":";
                    Scanner leerHora = new Scanner(tmp).useDelimiter(":");
                    horaS = leerHora.nextInt();
                    minS = leerHora.nextInt();
                    leerHora.close();
                }while(!horaValida(horaS,minS) || horaS<horaE);
                
                tipo=3;
                while(tipo!=TIPO_MOTO && tipo!=TIPO_CARRO){
                    System.out.println("+----------------------+");
                    System.out.println("|   Tipo de Vehículo   |");
                    System.out.println("+----------------------+");
                    System.out.println("| 1) Moto              |");
                    System.out.println("| 2) Carro             |");
                    System.out.println("+----------------------+");
                    System.out.println("\nDigíte su opción por favor: ");
                    tipo = teclado.nextInt()-1;
                }
                minS = calcularMinutos(horaE, minE, horaS, minS);
                valor = minS * TARIFAS[tipo];
                if(tipo == TIPO_MOTO){
                    System.out.println("\nServicio de parqueo de moto:");
                    System.out.printf("Tarifa por minuto $%d\n",TARIFAS[0]);
                }else{
                    System.out.println("\nServicio de parqueo de carro:");
                    System.out.printf("Tarifa por minuto $%d:\n",TARIFAS[1]);
                }
                System.out.printf("%d minutos = $%d\n\n",minS,valor);
                recaudo += valor;
            }
        }
    }
    
    public boolean horaValida(int hora, int min){
        return (hora>=0&&hora<24)&&(min>=0&&min<60);
    }
    
    public int calcularMinutos(int hIni, int mIni, int hFin, int mFin){
        return  (mFin - mIni) + (hFin - hIni) * 60;
    }
}
   