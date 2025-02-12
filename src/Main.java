import java.sql.SQLOutput;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var escolha = 1;

        var input = new Scanner(System.in);

        System.out.print("Digite o limite: ");
        var inputLimite = input.nextDouble();

        var cartao = new FormaDePagamento(inputLimite);
        var foiAutorizado = cartao.verificaLimite(inputLimite);

        if (!foiAutorizado) {
            System.exit(0);
        }

        while (escolha != 0) {
            System.out.print("Escreva o nome da compra: ");
            var inputNome = input.next();

            System.out.print("Escreva o valor da compra: ");
            var inputValor = input.nextDouble();

            System.out.println();

            var compra = new Compra(inputNome, inputValor);
            boolean compraFoiFinalizada = cartao.realizaCompra(compra);

            if (compraFoiFinalizada) {
                System.out.println("COMPRA REALIZADA! :)");
                System.out.println();
                System.out.print("Digite (0) para SAIR, (1) para CONTINUAR ou (2) para EXIBIR CARRINHO: ");
                escolha = input.nextInt();
            } else {
                System.out.println("Você inseriu um valor de um produto ACIMA do seu saldo disponível, tente novamente!");
                System.exit(0);
            }

            if (escolha == 2) {
                cartao.getListaDeCompras().sort(Comparator.comparing(Compra::getValor));

                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                cartao.getListaDeCompras().forEach(System.out::println);
                System.out.println();
                System.out.println("> VALOR TOTAL DO CARRINHO: R$" + cartao.somaValor());
                System.out.println("> SALDO DISPONÍVEL: R$" + cartao.getSaldo());
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");

                System.out.print("Digite (0) para SAIR, (1) para CONTINUAR ou (2) para EXIBIR CARRINHO: ");
                escolha = input.nextInt();
            }
        }
        System.out.println();
        System.out.println("PROGRAMA FINALIZADO! :D ");
    }
}