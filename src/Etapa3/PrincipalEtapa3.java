package Etapa3;

public class PrincipalEtapa3 {
    public static void main(String[] args) {
        System.out.println("Teste 1: Contagem de Elementos em uma Lista Estatica");
        StaticList<Integer> lista = new StaticList<>(10);
        lista.insert(1, 0);
        lista.insert(2, 1);
        lista.insert(3, 2);
        lista.insert(1, 3);
        lista.insert(2, 4);

        int contagem = lista.contaElementos(1);
        System.out.println("O elemento 1 aparece " + contagem + " vezes na lista.");
        System.out.println("---------------------------------------------------");

        System.out.println("Teste 2: Verificacao de Parenteses Agrupados");
        StaticStack<Character> pilha = new StaticStack<>(20);
        String expressao = "((A+B)-(C+D))";
        for (int i = 0; i < expressao.length(); i++) {
            pilha.push(expressao.charAt(i));
        }

        Etapa3 etapa3 = new Etapa3();
        boolean resultado = etapa3.checkBrackets(pilha);

        System.out.println("A expressao tem parenteses agrupados corretamente? " + resultado);
        System.out.println("---------------------------------------------------");
    }
}
