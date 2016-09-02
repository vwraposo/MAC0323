/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 3/03/2016
             Exercise 1.6.15 - Hubs and Authorities

Conclusao:
        Conclui que os Hubs tem um Page Rank em geral maior que as Authorities.
        Os hubs recebem muitos links, principalmente para um N grande<
    portanto serao mais visitados, aumentando seu page rank. Enquanto isso as
    Authorities dependem dos 10% de chance de ir para um pagina aleatoria
    para serem acessados. Logo seu page rank nao e tao alto.
        Exemplo: N = 200; M = 50; H = 3; A = 3; number of moves = 10,000
                Hubs:
                     0.05840
                     0.06510
                     0.07050
                Authorities:
                     0.00330
                     0.00170
                     0.00210
//////////////////////////////////////////////////////////////////////////////*/

public class HubsandAuthorities {

    // Funcao recebe uma funcao e devolve-a com todos os seus valores zerados
    public static boolean[] zera (boolean[] v) {
        int N = v.length;
        for (int i = 0; i < N; i++)
            v[i] = false;

        return v;
    }

    /* Funcao que recebe uma pagina x, que pode ser um hub ou uma authority
    // (definido pela variavel hub), a quantidade de paginas iniciais e, se for,
    // um hub aponta links de 10% das paginas iniciais a esse hub; se for uma
    // authority aponta links dela para 10% das paginais iniciais */
    public static void generate (int x, boolean hub, int N) {
        double links = Math.ceil (0.10 * N);
        boolean[] flag = new boolean[N];
        flag = zera (flag);
        while (links > 0) {
            int n = StdRandom.uniform (N);
            if (!flag[n]) {
                flag[n] = true;
                if (hub)
                    StdOut.println (n + " " + x);
                else
                    StdOut.println (x + " " + n);
            }
            links--;
        }
    }

    public static void main (String[] args) {
        // Quantidade de paginas iniciais
        int N = Integer.parseInt (args[0]);
        // Quandtidade de Arestas
        int M = Integer.parseInt (args[1]);
        // Quantidade de Hubs
        int H = Integer.parseInt (args[2]);
        // Quandtidade de Authorities
        int A = Integer.parseInt (args[3]);
        // Numero total de paginas = paginas iniciais + Hubs + Authorities
        int P = N + H + A;
        StdOut.println (P);

        // Gerando os links
        for (int i = 0; i < M; i++) {
            StdOut.print (StdRandom.uniform (N) + " ");
            StdOut.println (StdRandom.uniform (N));
        }
        // Criando Hubs
        for (int i = N; i < N + H; i++)
            generate (i, true, N);

        // Criando Authorities
        for (int i = N + H; i < P; i++)
            generate (i, false, N);

    }
}
