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
        Candidato[] porVotos = OrdenarCandidatos.ordenaCandidatosPorVotos(candidatos);
        Candidato[] porPartido = OrdenarCandidatos.ordenaCandidatosPorPartido(candidatos);

        System.out.println("== Ordenados por nome ==");
        for (Candidato c : porNome) System.out.println(c);

        System.out.println("\n== Ordenados por votos ==");
        for (Candidato c : porVotos) System.out.println(c);

        System.out.println("\n== Ordenados por partido ==");
        for (Candidato c : porPartido) System.out.println(c);

        String nomeBusca = escolherNomeBusca(porNome, nomes, random);
        int idx = OrdenarCandidatos.pesquisaBinariaCandidatos(porNome, nomeBusca);
        if (idx >= 0) {
            System.out.println("\nPesquisa binaria encontrou: " + porNome[idx]);
        } else {
            System.out.println("\nPesquisa binaria nao encontrou: " + nomeBusca);
        }
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
}
