package simplex;

import java.util.ArrayList;

/**
 *
 * @author <a href="https://github.com/JQuinteroC">JQuinteroC</a>
 */
public class Gauss {

    public void gauss(ArrayList<Double[]> matriz, int numVariables, int numEcuacione) {
        int[] posPivote;

        
            posPivote = hallarPivote(matriz, numVariables, numEcuacione);
            double multiplicador = Math.pow(matriz.get(posPivote[0])[posPivote[1]] , -1);
            productoPivote(matriz, posPivote,multiplicador );
            for (int j = 0; j < numEcuacione + 1; j++) {
                if (matriz.get(j)[posPivote[1]] != 0 && posPivote[0] != j) {
                    double factor = matriz.get(j)[posPivote[1]] *-1;
                    double[] producto = productoFila(matriz, posPivote, factor);
                    sumarRenglonesG(matriz, j, producto);
                }
            }

    }

    void sumarRenglonesG(ArrayList<Double[]> sumando, int renglonSuma, double[] sumandoPivote) {
        Double h[] = new Double[sumando.get(0).length];
        for (int i = 0; i < sumando.get(0).length; i++) {
            h[i] = sumando.get(renglonSuma)[i] + sumandoPivote[i];
        }
        sumando.set(renglonSuma, h);
    }

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
        int col = menorPosicion(numeros)+1;
        numeros.clear();

        // Encontrar la fila
        for (int i = 1; i <= numEcuaciones; i++) {
            double cociente = matriz.get(i)[(numVariables * 2) + 1] / matriz.get(i)[col];
            numeros.add(cociente);
        }
        int fil = menorPosicion(numeros)+1;

        // Resultado
        int res[] = new int[2];
        res[0] = fil;
        res[1] = col;
        return res;
    }

    /**
     * Multiplica una fila pivote por un número especifico
     *
     * @param matriz
     * @param posPivote
     * @param multiplicador
     */
    void productoPivote(ArrayList<Double[]> matriz, int[] posPivote, double multiplicador) {
        Double[] producto = new Double[matriz.get(posPivote[0]).length];
        for (int i = 0; i < matriz.get(posPivote[0]).length; i++) {
            producto[i] = multiplicador * matriz.get(posPivote[0])[i];
        }
        matriz.set(posPivote[0], producto);
    }

    /**
     * Retorna el producto de la fila especificada
     *
     * @param matriz
     * @param posicion
     * @param multiplicador
     * @return
     */
    static double[] productoFila(ArrayList<Double[]> matriz, int[] posicion, double multiplicador) {
        double[] productos = new double [matriz.get(posicion[0]).length];
        for (int i = 0; i < matriz.get(posicion[0]).length; i++) {
            productos[i] = multiplicador * matriz.get(posicion[0])[i];
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
     *
     * @param numeros
     * @return
     */
    static int menorPosicion(ArrayList<Double> numeros) {
        double menor = numeros.get(0);
        int pos = 0;
        for (int i = 0; i < numeros.size(); i++) {
            if (menor > numeros.get(i)) {
                menor = numeros.get(i);
                pos = i;
            }
        }
        return pos++;
    }
}
