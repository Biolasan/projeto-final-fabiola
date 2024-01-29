import java.util.Scanner;

public class Menu {
    public static int exibirMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("##################");
        System.out.println("##### AGENDA #####");
        System.out.println(">>>> Menu <<<<");
        System.out.println("1 - Adicionar Contato");
        System.out.println("2 - Remover Contato");
        System.out.println("3 - Editar Contato");
        System.out.println("4 - Sair");

        System.out.print("Escolha uma opção: ");
        int opcao;
        try {
            opcao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            opcao = -1; // Se a entrada não for um número válido
        }

        return opcao;
    }
}






