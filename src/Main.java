import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String ARQUIVO_CONTATOS = "contatos.txt";

    public static void main(String[] args) {
        List<Contato> contatos = carregarContatos();

        int opcao;
        do {
            exibirContatos(contatos);
            opcao = Menu.exibirMenu();

            switch (opcao) {
                case 1:
                    adicionarContato(contatos);
                    break;
                case 2:
                    removerContato(contatos);
                    break;
                case 3:
                    editarContato(contatos);
                    break;
                case 4:
                    salvarContatos(contatos);
                    System.out.println("Saindo da agenda. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);
    }

    private static List<Contato> carregarContatos() {
        List<Contato> contatos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CONTATOS))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                Contato contato = converterLinhaParaContato(linha);
                if (contato != null) {
                    contatos.add(contato);
                }
            }
        } catch (IOException e) {
            // Arquivo ainda não existe, pode ser tratado conforme necessário
        }

        return contatos;
    }

    private static void salvarContatos(List<Contato> contatos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_CONTATOS))) {
            for (Contato contato : contatos) {
                String linha = converterContatoParaLinha(contato);
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Contato converterLinhaParaContato(String linha) {
        // Implementar lógica para converter uma linha do arquivo em um objeto Contato
        // Exemplo: "1 | João"
        String[] partes = linha.split("\\|");
        if (partes.length >= 2) {
            long id = Long.parseLong(partes[0].trim());
            String nome = partes[1].trim();
            List<Telefone> telefones = new ArrayList<>();
            for (int i = 2; i < partes.length; i++) {
                String[] telefoneParts = partes[i].trim().split(" ");
                if (telefoneParts.length == 2) {
                    String ddd = telefoneParts[0].trim();
                    long numero = Long.parseLong(telefoneParts[1].trim());
                    telefones.add(new Telefone(ddd, numero));
                }
            }
            return new Contato(id, nome, telefones);
        }
        return null;
    }

    private static String converterContatoParaLinha(Contato contato) {
        // Implementar lógica para converter um objeto Contato em uma linha do arquivo
        // Exemplo: "1 | João | 123 456789 | 321 987654"
        StringBuilder linha = new StringBuilder();
        linha.append(contato.getId()).append(" | ").append(contato.getNome());
        for (Telefone telefone : contato.getTelefones()) {
            linha.append(" | ").append(telefone);
        }
        return linha.toString();
    }

    private static void adicionarContato(List<Contato> contatos) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o nome do contato: ");
        String nome = scanner.nextLine();
        long novoId = contatos.isEmpty() ? 1 : contatos.get(contatos.size() - 1).getId() + 1;
        Contato novoContato = new Contato(novoId, nome, new ArrayList<>());

        // Adicionar números de telefone
        System.out.print("Deseja adicionar um número de telefone? (S/N): ");
        String resposta = scanner.nextLine();
        while (resposta.equalsIgnoreCase("S")) {
            Telefone novoTelefone = criarTelefone();
            novoContato.adicionarTelefone(novoTelefone);
            System.out.print("Deseja adicionar outro número de telefone? (S/N): ");
            resposta = scanner.nextLine();
        }

        contatos.add(novoContato);
        System.out.println("Contato adicionado com sucesso!");
    }

    private static Telefone criarTelefone() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o DDD do telefone: ");
        String ddd = scanner.next();
        System.out.print("Informe o número do telefone: ");
        long numero = scanner.nextLong();
        return new Telefone(ddd, numero);
    }

    private static void removerContato(List<Contato> contatos) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o ID do contato que deseja remover: ");
        long idRemover = scanner.nextLong();
        scanner.nextLine(); // Consumir a quebra de linha após a leitura do número
        Contato contatoRemover = buscarContatoPorId(contatos, idRemover);

        if (contatoRemover != null) {
            contatos.remove(contatoRemover);
            System.out.println("Contato removido com sucesso!");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    private static void editarContato(List<Contato> contatos) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o ID do contato que deseja editar: ");
        long idEditar = scanner.nextLong();
        scanner.nextLine(); // Consumir a quebra de linha após a leitura do número
        Contato contatoEditar = buscarContatoPorId(contatos, idEditar);

        if (contatoEditar != null) {
            System.out.print("Informe o novo nome do contato: ");
            String novoNome = scanner.nextLine();
            contatoEditar.setNome(novoNome);
            System.out.println("Contato editado com sucesso!");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    private static Contato buscarContatoPorId(List<Contato> contatos, long id) {
        for (Contato contato : contatos) {
            if (contato.getId() == id) {
                return contato;
            }
        }
        return null;
    }

    private static void exibirContatos(List<Contato> contatos) {
        System.out.println(">>>> Lista de Contatos <<<<");
        System.out.println("Id | Nome | Telefones");
        for (Contato contato : contatos) {
            System.out.println(contato);
        }
    }
}













