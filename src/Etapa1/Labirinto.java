package Etapa1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Labirinto {
    private static final char PAREDE = 'X';
    private static final char CAMINHO_ABERTO = ' ';
    private static final char SAIDA = 'D';
    private static final char CAMINHO_SOLUCAO = '#';

    private char[][] labirinto;

    public void criaLabirinto(String filename) {
        List<char[]> linhas = new ArrayList<>();

        try (BufferedReader leitor = abreArquivo(filename)) {
            String linha;
            int maiorTamanho = 0;

            while ((linha = leitor.readLine()) != null) {
                linhas.add(linha.toCharArray());
                if (linha.length() > maiorTamanho) {
                    maiorTamanho = linha.length();
                }
            }

            if (linhas.isEmpty()) {
                throw new IllegalArgumentException("O arquivo do labirinto está vazio.");
            }

            labirinto = new char[linhas.size()][maiorTamanho];
            for (int i = 0; i < linhas.size(); i++) {
                for (int j = 0; j < maiorTamanho; j++) {
                    labirinto[i][j] = j < linhas.get(i).length ? linhas.get(i)[j] : PAREDE;
                }
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Não foi possível ler o labirinto.", e);
        }
    }

    public boolean percorreLabirinto() {
        if (labirinto == null || labirinto.length == 0 || labirinto[0].length == 0) {
            return false;
        }
        return resolverLabirinto(0, 0);
    }

    public void imprimeLabirinto() {
        if (labirinto == null) {
            System.out.println("Labirinto não carregado.");
            return;
        }

        for (char[] linha : labirinto) {
            System.out.println(new String(linha));
        }
    }

    private boolean resolverLabirinto(int x, int y) {
        if (x < 0 || y < 0 || x >= labirinto.length || y >= labirinto[x].length) {
            return false;
        }

        char posicaoAtual = labirinto[x][y];
        if (posicaoAtual == PAREDE || posicaoAtual == CAMINHO_SOLUCAO) {
            return false;
        }

        if (posicaoAtual == SAIDA) {
            return true;
        }

        labirinto[x][y] = CAMINHO_SOLUCAO;

        if (resolverLabirinto(x, y + 1)
                || resolverLabirinto(x + 1, y)
                || resolverLabirinto(x, y - 1)
                || resolverLabirinto(x - 1, y)) {
            return true;
        }

        labirinto[x][y] = CAMINHO_ABERTO;
        return false;
    }

    private BufferedReader abreArquivo(String filename) throws IOException {
        try {
            return new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException excecaoPrincipal) {
            String caminhoAlternativo = filename.replace("src/", "");
            if (!caminhoAlternativo.equals(filename)) {
                try {
                    return new BufferedReader(new FileReader(caminhoAlternativo));
                } catch (FileNotFoundException excecaoAlternativa) {
                    throw excecaoPrincipal;
                }
            }
            throw excecaoPrincipal;
        }
    }
}