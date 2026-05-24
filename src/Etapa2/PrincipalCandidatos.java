package Etapa2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PrincipalCandidatos {
    private static final List<String> NOMES_PADRAO = Arrays.asList(
            "Amanda", "Alexandre", "Beatriz", "Gabriela", "Giovana",
            "Jorge", "Lucas", "Mariana", "Paulo", "Rafael"
    );
    private static final List<String> PARTIDOS_PADRAO = Arrays.asList(
            "PL", "PMDB", "PSB", "PSDB", "PSD", "PT"
    );
    private static final String SEPARATOR = "===========================================================================";
    private static final int COL_NOME = 20;
    private static final int COL_PARTIDO = 15;
    private static final int COL_PREFIXO = 19;

    public static void main(String[] args) {
        List<String> nomes = carregarLista("Etapa2/nomes.txt", "nomes.txt", NOMES_PADRAO);
        List<String> partidos = carregarLista("Etapa2/partidos.txt", "partidos.txt", PARTIDOS_PADRAO);

        ThreadLocalRandom random = ThreadLocalRandom.current();
        int n = random.nextInt(1, 101);

        Candidato[] candidatos = new Candidato[n];
        for (int i = 0; i < n; i++) {
            String nome = nomes.get(random.nextInt(nomes.size()));
            String partido = partidos.get(random.nextInt(partidos.size()));
            int votos = random.nextInt(1, 1001);
            candidatos[i] = new Candidato(nome, partido, votos);
        }

        Candidato[] porNome = OrdenarCandidatos.ordenaCandidatosPorNome(candidatos);
        OrdenarCandidatos.ordenaCandidatosPorVotos(candidatos);
        OrdenarCandidatos.ordenaCandidatosPorPartido(candidatos);

        String nomeBusca = escolherNomeBusca(porNome, nomes, random);
        int idx = OrdenarCandidatos.pesquisaBinariaCandidatos(porNome, nomeBusca);
        imprimirRelatorio(porNome, idx, nomeBusca);
    }

    private static List<String> carregarLista(String caminhoPreferencial, String caminhoAlternativo, List<String> fallback) {
        List<String> linhas = lerArquivo(caminhoPreferencial);
        if (linhas.isEmpty()) {
            linhas = lerArquivo(caminhoAlternativo);
        }
        if (linhas.isEmpty()) {
            return new ArrayList<>(fallback);
        }
        return linhas;
    }

    private static List<String> lerArquivo(String caminho) {
        List<String> linhas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    linhas.add(linha);
                }
            }
        } catch (IOException e) {
            return new ArrayList<>();
        }
        return linhas;
    }

    private static String escolherNomeBusca(Candidato[] porNome, List<String> nomes, ThreadLocalRandom random) {
        if (porNome.length > 0 && random.nextBoolean()) {
            return porNome[random.nextInt(porNome.length)].getNome();
        }
        return nomes.get(random.nextInt(nomes.size()));
    }

    private static void imprimirRelatorio(Candidato[] porNome, int idxBusca, String nomeBusca) {
        System.out.println(SEPARATOR);
        System.out.println("         RELATÓRIO DE VOTAÇÃO");
        System.out.println(SEPARATOR);
        System.out.printf("%-" + COL_NOME + "s %-" + COL_PARTIDO + "s %s%n",
            "Nome", "Partido", "Intenções de Votos");
        System.out.println(SEPARATOR);
        System.out.println("Candidatos ordenados por nome:");
        for (Candidato c : porNome) {
            System.out.println(formatarLinha(c));
        }
        System.out.println(SEPARATOR);
        if (porNome.length > 0) {
            System.out.println(formatarResumo("Primeiro candidato:", porNome[0]));
            System.out.println(formatarResumo("Último candidato:", porNome[porNome.length - 1]));
        }
        if (idxBusca >= 0 && idxBusca < porNome.length) {
            System.out.printf("[OK] Candidato encontrado na posição %2d: %s%n",
                    idxBusca, formatarLinha(porNome[idxBusca]));
        } else {
            System.out.println("[NOK] Candidato não encontrado: " + nomeBusca);
        }
    }

    private static String formatarLinha(Candidato candidato) {
        return String.format("%-" + COL_NOME + "s %-" + COL_PARTIDO + "s %d",
            candidato.getNome(), candidato.getPartido(), candidato.getIntencoesVotos());
    }

    private static String formatarResumo(String prefixo, Candidato candidato) {
        return String.format("%-" + COL_PREFIXO + "s %-" + COL_NOME + "s %-" + COL_PARTIDO + "s %d",
            prefixo, candidato.getNome(), candidato.getPartido(), candidato.getIntencoesVotos());
    }
}
