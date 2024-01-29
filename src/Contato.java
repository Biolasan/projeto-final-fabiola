import java.util.List;
import java.util.ArrayList;


public class Contato {
    private Long id;
    private String nome;
    private List<Telefone> telefones;

    public Contato(Long id, String nome, List<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.telefones = telefones != null ? telefones : new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void adicionarTelefone(Telefone telefone) {
        telefones.add(telefone);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(" | ").append(nome);
        for (Telefone telefone : telefones) {
            stringBuilder.append(" | ").append(telefone);
        }
        return stringBuilder.toString();
    }
}






















