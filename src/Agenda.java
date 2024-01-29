import java.util.List;

public class Agenda {
    private List<Contato> contatos;

    public Agenda(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void adicionarContato(Contato contato) {
        // Implementar lógica para adicionar contato, considerando RN1 e RN2
    }

    public void removerContato(long id) {
        // Implementar lógica para remover contato pelo ID
    }

    public void editarContato(long id, Contato novoContato) {
        // Implementar lógica para editar contato pelo ID
    }
}


