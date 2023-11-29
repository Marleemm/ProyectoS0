package proyecto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Fifo {

    Queue q = new LinkedList<>();
    ;
    int tamano = 0;
    int cantidadFallos = 0;
    ArrayList cola = new ArrayList<>();

    //codigo para creacion de la tabla
    int cantidadCadena;
    int cantidadMarcos;
    String[] cadenaReferencia, cadenaAux;
    String[][] matriz;
    String[] fallos;
    int auxiliar = 0;
    String tab = "";

    public Fifo() {
    }

    public void setCadena(String[] cadenaReferencia) {
        this.cadenaReferencia = cadenaReferencia;
        this.cadenaAux = cadenaReferencia;
    }

    public void setTamanoCadena(int cantidadCadena) {
        this.cantidadCadena = cantidadCadena;
    }

    public void setCantidadMarcos(int cantidadMarcos) {
        this.cantidadMarcos = cantidadMarcos;
    }
   public String getCola() {
        return colaString;
    }

    public String[][] getMatriz() {

        return matriz;
    }

    public String[] getFallos() {

        return fallos;
    }

    public String getMatrizString() {

        return tab;
    }

    public void iniciarxfallos() {
        for (int i = 0; i < cantidadCadena; i++) {
            fallos[i] = "0";
        }
    }

    private void iniciarMatriz() {
        for (int i = 0; i < cantidadMarcos; i++) {
            for (int j = 0; j < cantidadCadena; j++) {
                matriz[i][j] = " ";
            }
        }
    }

    public void siguiente() {
        auxiliar++;
        if (auxiliar == cantidadMarcos) {//si llega al final de los frames regresa al primer frame
            auxiliar = 0;
        }
    }

    public boolean buscar(int paginaAcutal) {
        boolean bandera = false;
        for (int j = 0; j < cantidadMarcos; j++) {
            //recorre todos los frames de una pagina determinada
            if (cadenaReferencia[paginaAcutal].equals(matriz[j][paginaAcutal])) {
                //si el valor de la pagina actual existe en algun frame la bandera se le asigna verdadero
                bandera = true;
            }
        }
        return bandera;
    }

    public void modificar(boolean encontrado, int paginaActual) {
        if (!encontrado) {
            for (int aux = paginaActual; aux < cantidadCadena; aux++) {
                matriz[auxiliar][aux] = cadenaReferencia[paginaActual];
                fallos[paginaActual] = "1";
            }
            siguiente();
        }
    }

    public void algoritmoFifo() {
        matriz = new String[cantidadMarcos][cantidadCadena];
        fallos = new String[cantidadCadena];
        iniciarxfallos();
        for (int i = 0; i < cantidadCadena; i++) {
            modificar(buscar(i), i);
        }
        mostrarTabla();

        colaActulizacion();
        calculos();
        mostrarCola();

    }

    public void mostrarTabla() {
        System.out.print("Referencia|");
        tab += "Referencia|";
        for (int i = 0; i < cadenaAux.length; i++) {
            System.out.print(""+cadenaAux[i]);
            tab += " " + cadenaAux[i];
        }
        System.out.print("\n-------------------------\n");
        tab += "\n-------------------------\n";
        for (int i = 0; i < cantidadMarcos; i++) {
            tab += "Marco "+i+"   |";
            System.out.print("Marco "+i+"   |");
            for (int j = 0; j < cantidadCadena; j++) {
               if(matriz[i][j]==null){
                   System.out.print(" ");
                    tab += "  ";

               }else{ 
                   System.out.print(" "+matriz[i][j]);
                    tab +=" "+matriz[i][j];}
                
            }
            tab += "\n";
            System.out.println();
        }
        System.out.print("Fallos    |");
        tab += "Fallos    |";
        for (int i = 0; i < cantidadCadena; i++) {

            System.out.print(""+fallos[i]);
            tab += " " + fallos[i];
        }

    }

    public void colaActulizacion() {

        tamano = cadenaReferencia.length;
        for (int i = 0; i < cadenaReferencia.length; i++) {

            if (q.size() < cantidadMarcos) {
                if (!q.contains(cadenaReferencia[i])) {
                    q.add(cadenaReferencia[i]);

                    cantidadFallos++;

                }
            } else {
                if (!q.contains(cadenaReferencia[i])) {

                    q.poll();
                    q.add(cadenaReferencia[i]);

                    cantidadFallos++;

                }
            }
            ArrayList<Integer> copiaMarcosLista = new ArrayList<>(q);
            cola.add(copiaMarcosLista);

        }

    }

    public String calculos() {
        float razon = (float) cantidadFallos / tamano;
        float rendimiento = (float) (1 - (razon));
        System.out.println("\nNumero de Fallos=" + cantidadFallos);
        System.out.println("Razon de fallos=" + razon);
        System.out.println("Rendimiento=" + rendimiento * 100 + "%");
        return  "\nNumero de Fallos=" + cantidadFallos + "\nRazon de fallos=" + razon + "\nRendimiento=" + rendimiento * 100 + "%";
       /* razon = 0;
        rendimiento = 0;
        cantidadFallos = 0;*/
    }

 
    
    String colaString = "";

    public void mostrarCola() {
        System.out.println("\n-------------Colas de estado del algoritmo FIFO:-----------------------------\n");
        //colaString +="\n-------------Colas de estado del algoritmo FIFO:-----------------------------\n";
        for (int i = 0; i < cola.size(); i++) {
            System.out.println((i + 1) + ": " + cola.get(i));
            colaString += cola.get(i) + "\n";
        }
    }

 
    public void limpiarDatos() {
        for (int i = 0; i < cadenaAux.length; i++) {
            cadenaAux[i] = "";
        }
        iniciarMatriz();
        iniciarxfallos();
        cola.clear();
        colaString = "";
        tab = "";
        q.clear();

    }

}
