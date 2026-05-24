package Etapa2;

import java.util.Arrays;
import java.util.Comparator;

public class OrdenarCandidatos {

    public static Candidato[] ordenaCandidatosPorNome(Candidato[] candidatos) {
        if (candidatos == null) return new Candidato[0];
        Candidato[] copia = candidatos.clone();
        Arrays.sort(copia, Comparator.comparing(Candidato::getNome));
        return copia;
    }

    public static Candidato[] ordenaCandidatosPorVotos(Candidato[] candidatos) {
        if (candidatos == null) return new Candidato[0];
        Candidato[] copia = candidatos.clone();
        Arrays.sort(copia, (a, b) -> Integer.compare(b.getIntencoesVotos(), a.getIntencoesVotos()));
        return copia;
    }

    public static Candidato[] ordenaCandidatosPorPartido(Candidato[] candidatos) {
        if (candidatos == null) return new Candidato[0];
        Candidato[] copia = candidatos.clone();
        Arrays.sort(copia, Comparator.comparing(Candidato::getPartido));
        return copia;
    }

    public static int pesquisaBinariaCandidatos(Candidato[] candidatos, String nome) {
        if (candidatos == null || nome == null) return -1;
        // assume array is sorted by nome
        int low = 0, high = candidatos.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int cmp = candidatos[mid].getNome().compareTo(nome);
            if (cmp == 0) return mid;
            if (cmp < 0) low = mid + 1; else high = mid - 1;
        }
        return -1;
    }
}
