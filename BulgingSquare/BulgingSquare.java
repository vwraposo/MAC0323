/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
            Exercise 1.5.14 - BulgingSquare
//////////////////////////////////////////////////////////////////////////////*/

public class BulgingSquare {

    //Variaveis Globais, as dimensoes da figura
    public static final int N = 15;
    public static final double R = N * 0.42;
    public static final double S = 1.0/6;

    //  Funcao que recebe as cordenadas de um quadrado, uma mascara e um inteiro
    // Pinta quadrados menores nos cantos do quadrado enviado (os cantos sao
    // determinados pela mascara e a cor pelo inteiro)
    public static void miniSquare (int x, int y, int mask, int color) {
        if (color % 2 != 0)
            StdDraw.setPenColor (StdDraw.BLACK);
        else
            StdDraw.setPenColor (StdDraw.WHITE);
        // Top Left
        if ((mask & (1 << 3)) != 0)
            StdDraw.filledSquare (x + S, y + 1 - S, S / 1.4);
        // Bottom Left
        if ((mask & (1 << 2)) != 0)
            StdDraw.filledSquare (x + S, y + S, S / 1.4);
        // Bottom Right
        if ((mask & (1 << 1)) != 0)
            StdDraw.filledSquare (x + 1 - S, y + S, S / 1.4);
        // Top Right
        if ((mask & (1 << 0)) != 0)
            StdDraw.filledSquare (x + 1 - S, y + 1 -S, S / 1.4);
    }

    public static void main (String[] arg) {
        //  Cordenadas de um dado ponto ao mudar a origem do plano para
        // o centro da figura (N/2, N/2)
        double x, y;

        StdDraw.setXscale (0, N);
        StdDraw.setYscale (0, N);

        // Fundo quadriculado
        for (int i = 0; i <= N; i++)
            for (int j = 0; j <= N; j++)
                if ((i + j) % 2 == 0) {
                    StdDraw.setPenColor (StdDraw.BLACK);
                    StdDraw.filledSquare (i+0.5, j+0.5, 0.5);
                }

        // Quadrados menores
        for (int i = 0; i <= N; i++)
            for (int j = 0; j <= N; j++) {
                x = i - N/2;
                y = j - N/2;
                if ( Math.sqrt (Math.pow (x, 2) + Math.pow (y, 2)) <= R) {
                    if (x == 0) {
                        if (y > 0)
                            miniSquare (i, j, (1 << 1) + (1 << 2) , i+j);
                        else if (y < 0)
                            miniSquare (i, j, (1 << 3) + (1 << 0), i+j);
                    }
                    else if (y == 0) {
                        if (x > 0)
                            miniSquare (i, j, (1 << 3) + (1 << 2), i+j);
                        else if (x < 0)
                            miniSquare (i, j, (1 << 1) + (1 << 0), i+j);
                    }
                    // Primeiro e Terceiro Quadrantes
                    else if ((x > 0 && y > 0) || (x < 0 && y < 0))
                        miniSquare (i, j, (1 << 3) + (1 << 1), i+j);
                    // Segundo e Quarto Quadrantes
                    else if ((x > 0 && y < 0) || (x < 0 && y > 0))
                            miniSquare (i, j, (1 << 2) + (1 << 0), i+j);
                }
        }
    }
}
