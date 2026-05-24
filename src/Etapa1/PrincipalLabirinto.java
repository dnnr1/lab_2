package Etapa1;

public class PrincipalLabirinto {
    public static void main(String[] args) {
        Labirinto labirinto = new Labirinto();
        labirinto.criaLabirinto("src/Etapa1/labirinto.txt");

        System.out.println("Labirinto inicial:");
        labirinto.imprimeLabirinto();

        boolean encontrouSaida = labirinto.percorreLabirinto();
        if (encontrouSaida) {
            System.out.println("Solução encontrada");
        } else {
            System.out.println("Solução não encontrada");
        }

        System.out.println("Labirinto final:");
        labirinto.imprimeLabirinto();
    }
}