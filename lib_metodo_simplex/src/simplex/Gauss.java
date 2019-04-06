package simplex;

import java.util.ArrayList;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Gauss {

    void sumarRenglones(ArrayList<Double[]> sumando, int renglonSumando, int renglonSuma, int numVariables) {
        Double h[] = null;
        for (int i = 0; i < numVariables; i++) {
            h[renglonSuma] = sumando.get(renglonSuma)[i] + sumando.get(renglonSumando)[i];
        }
        sumando.set(renglonSuma, h);
    }

    static int[] hallarPivote(ArrayList<Double[]> matriz, int numVariables, int numEcuaciones) {
        ArrayList<Double> numeros = new ArrayList();
        // Encontrar la columna
        for (int i = 1; i <= numVariables; i++) {
            if (matriz.get(0)[i] < 0) {
                numeros.add(matriz.get(0)[i]);
            }
        }
        int col = menorPosicion(numeros);
        numeros.clear();
        
        // Encontrar la fila
        for (int i = 1; i <= numEcuaciones; i++) {
            double cociente = matriz.get(i)[(numVariables*2)+1] /  matriz.get(i)[col];
            numeros.add(cociente);
        }
        int fil = menorPosicion(numeros);
        
        // Resultado
        int res[] = null;
        res[0] = fil;
        res[1] = col;
        return res;
    }
    
/**
 * Multiplica una final pivote por un número especifico
 * @param matriz
 * @param posPivote
 * @param multiplicador 
 */
    void productoPivote(ArrayList<Double[]> matriz, int[] posPivote, double multiplicador) {
        for (double producto : matriz.get(posPivote[0])) {
            producto = producto * multiplicador;
            
        }
    }

    /**
     * Retorna el producto de la fila especificada
     * @param matriz
     * @param posicion
     * @param multiplicador
     * @return 
     */
    static double[] productoFila(ArrayList<Double[]> matriz, int[] posicion, double multiplicador) {
        double[] productos = null;
        for (int i = 0; i < matriz.size(); i++) {
            productos [i] = multiplicador * matriz.get(posicion[0])[i];
        }
        return productos;
    }
    
  /*  static double menor(ArrayList<Double> numeros) {
        double menor = numeros.get(0);
        for (Double i : numeros) {
            if (menor > i) {
                menor = i;
            }
        }
        return menor;
    }
   */ 
    
    /**
     * Regresa la columna del numero menor
     * @param numeros
     * @return 
     */
    static int menorPosicion(ArrayList<Double> numeros) {
        double menor = numeros.get(0);
        int pos = 0;
        for (int i = 0 ; i < numeros.size(); i++) {
            if (menor > numeros.get(i)) {
                menor = numeros.get(i);
                pos = i;
            }
        }
        return pos++;
    }
}
