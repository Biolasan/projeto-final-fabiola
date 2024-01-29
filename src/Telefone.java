public class Telefone {
    private Long id;
    private String ddd;
    private Long numero;

    public Telefone(String ddd, Long numero) {
        this.ddd = ddd;
        this.numero = numero;
    }

    public String getDdd() {
        return ddd;
    }

    public Long getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return ddd + " " + numero;
    }
}


















