import java.util.ArrayList;
import java.util.List;

public class FormaDePagamento {
    private double limite;
    protected double saldo;
    private List<Compra> listaDeCompras;

    public FormaDePagamento(double limite) {
        this.limite = limite;
        this.saldo = limite;
        this.listaDeCompras = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Compra> getListaDeCompras() {
        return listaDeCompras;
    }

    public double getLimite() {
        return limite;
    }

    public boolean verificaLimite(double limite) {
        if (this.limite < 1) {
            System.out.print("""
                    O limite NÃO pode estar abaixo de 1, tente novamente!
                    """);
            return false;
        }

        return true;
    }

    public boolean realizaCompra(Compra compra) {
        if (this.saldo > compra.valor) {
            this.saldo -= compra.valor;
            listaDeCompras.add(compra);
            return true;
        }
        return false;
    }

    public double somaValor() {
        return this.limite - this.saldo;
    }
}
