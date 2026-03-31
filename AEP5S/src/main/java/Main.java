import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SolicitacaoService service = new SolicitacaoService();
        service.criarTabela();

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n========== SISTEMA DE SOLICITAÇÕES ==========");
            System.out.println("1. Criar nova solicitação");
            System.out.println("2. Listar todas as solicitações");
            System.out.println("3. Buscar solicitação por protocolo");
            System.out.println("4. Atualizar status de solicitação");
            System.out.println("5. Registrar comentário em solicitação");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    System.out.print("E-mail: ");
                    String email = scanner.nextLine();

                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();

                    System.out.print("Descrição da solicitação: ");
                    String descricao = scanner.nextLine();

                    Solicitacao solicitacao = new Solicitacao(
                            nome, cpf, email, endereco,
                            0, descricao, StatusSolicitacao.ABERTA
                    );

                    service.criar(solicitacao);
                    System.out.print("\nPressione Enter para continuar...");
                    scanner.nextLine();
                }

                case 2 -> {
                    List<Solicitacao> lista = service.listar();

                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma solicitação encontrada.");
                    } else {
                        System.out.println("\n--- Solicitações cadastradas ---");
                        for (Solicitacao s : lista) {
                            System.out.printf("ID: %d | Nome: %s | Status: %s | Descrição: %s%n",
                                    s.getId(), s.getNome(), s.getStatus(), s.getDescricao());
                        }
                    }
                    System.out.print("\nPressione Enter para continuar...");
                    scanner.nextLine();
                }

                case 3 -> {
                    System.out.print("Informe o número do protocolo (ID): ");
                    long id = scanner.nextLong();
                    scanner.nextLine();

                    Solicitacao s = service.buscarPorProtocolo(id);

                    if (s != null) {
                        System.out.println("\n--- Solicitação encontrada ---");
                        System.out.printf("ID: %d%n", s.getId());
                        System.out.printf("Nome: %s%n", s.getNome());
                        System.out.printf("CPF: %s%n", s.getCpf());
                        System.out.printf("E-mail: %s%n", s.getEmail());
                        System.out.printf("Endereço: %s%n", s.getEndereco());
                        System.out.printf("Descrição: %s%n", s.getDescricao());
                        System.out.printf("Status: %s%n", s.getStatus());
                    } else {
                        System.out.println("Solicitação não encontrada.");
                    }
                    System.out.print("\nPressione Enter para continuar...");
                    scanner.nextLine();
                }

                case 4 -> {
                    System.out.print("Informe o número do protocolo (ID): ");
                    long id = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Selecione o novo status:");
                    System.out.println("1. ABERTA");
                    System.out.println("2. EM_ANALISE");
                    System.out.println("3. FINALIZADA");
                    System.out.print("Opção: ");
                    int statusOpcao = scanner.nextInt();
                    scanner.nextLine();

                    StatusSolicitacao novoStatus = switch (statusOpcao) {
                        case 1 -> StatusSolicitacao.ABERTA;
                        case 2 -> StatusSolicitacao.EM_ANALISE;
                        case 3 -> StatusSolicitacao.FINALIZADA;
                        default -> {
                            System.out.println("Opção inválida. Status não alterado.");
                            yield null;
                        }
                    };

                    if (novoStatus != null) {
                        service.atualizarStatus(id, novoStatus);
                    }
                    System.out.print("\nPressione Enter para continuar...");
                    scanner.nextLine();
                }

                case 5 -> {
                    System.out.print("Informe o número do protocolo (ID): ");
                    long id = scanner.nextLong();
                    scanner.nextLine();

                    System.out.print("Digite o comentário: ");
                    String comentario = scanner.nextLine();

                    service.registrarComentario(id, comentario);
                    System.out.print("\nPressione Enter para continuar...");
                    scanner.nextLine();
                }

                case 0 -> System.out.println("Encerrando o sistema. Até logo!");

                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }
}