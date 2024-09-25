package cadastropoo;

import java.io.IOException;
import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class CadastroPOO {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
            PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
            int opcao = -1;
            
            while (opcao != 0) {
                System.out.println("\nMenu:");
                System.out.println("1 - Incluir");
                System.out.println("2 - Alterar");
                System.out.println("3 - Excluir");
                System.out.println("4 - Exibir pelo ID");
                System.out.println("5 - Exibir todos");
                System.out.println("6 - Salvar dados");
                System.out.println("7 - Recuperar dados");
                System.out.println("0 - Sair");
                System.out.print("Selecione uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Incluir Pessoa Física (1) ou Jurídica (2)? ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        if (tipo == 1) {
                            System.out.print("ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("CPF: ");
                            String cpf = scanner.nextLine();
                            System.out.print("Idade: ");
                            int idade = scanner.nextInt();
                            scanner.nextLine();
                            repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                        } else if (tipo == 2) {
                            System.out.print("ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("CNPJ: ");
                            String cnpj = scanner.nextLine();
                            repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                        }
                    }
                        
                    case 2 -> {
                        System.out.print("Alterar Pessoa Física (1) ou Jurídica (2)? ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        if (tipo == 1) {
                            System.out.print("ID da pessoa a ser alterada: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            PessoaFisica pf = repoFisica.obter(id);
                            if (pf != null) {
                                System.out.print("Novo nome: ");
                                String nome = scanner.nextLine();
                                System.out.print("Novo CPF: ");
                                String cpf = scanner.nextLine();
                                System.out.print("Nova idade: ");
                                int idade = scanner.nextInt();
                                scanner.nextLine();
                                repoFisica.alterar(id, new PessoaFisica(id, nome, cpf, idade));
                            } else {
                                System.out.println("Pessoa física não encontrada.");
                            }
                        } else if (tipo == 2) {
                            System.out.print("ID da pessoa a ser alterada: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            PessoaJuridica pj = repoJuridica.obter(id);
                            if (pj != null) {
                                System.out.print("Novo nome: ");
                                String nome = scanner.nextLine();
                                System.out.print("Novo CNPJ: ");
                                String cnpj = scanner.nextLine();
                                repoJuridica.alterar(id, new PessoaJuridica(id, nome, cnpj));
                            } else {
                                System.out.println("Pessoa jurídica não encontrada.");
                            }
                        }
                    }
                        
                    case 3 -> {
                        System.out.print("Excluir Pessoa Física (1) ou Jurídica (2)? ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        if (tipo == 1) {
                            System.out.print("ID da pessoa a ser excluída: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            repoFisica.excluir(id);
                        } else if (tipo == 2) {
                            System.out.print("ID da pessoa a ser excluída: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            repoJuridica.excluir(id);
                        }
                    }
                        
                    case 4 -> {
                        System.out.print("Exibir Pessoa Física (1) ou Jurídica (2)? ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        if (tipo == 1) {
                            System.out.print("ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            PessoaFisica pf = repoFisica.obter(id);
                            if (pf != null) {
                                pf.exibir();
                            } else {
                                System.out.println("Pessoa física não encontrada.");
                            }
                        } else if (tipo == 2) {
                            System.out.print("ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            PessoaJuridica pj = repoJuridica.obter(id);
                            if (pj != null) {
                                pj.exibir();
                            } else {
                                System.out.println("Pessoa jurídica não encontrada.");
                            }
                        }
                    }
                        
                    case 5 -> {
                        System.out.println("Exibir todos (1 - Pessoas Físicas, 2 - Pessoas Jurídicas, 3 - Todos): ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        if (tipo == 1 || tipo == 3) {
                            System.out.println("Pessoas Físicas:");
                            for (PessoaFisica pf : repoFisica.obterTodos()) {
                                pf.exibir();
                            }
                        }
                        if (tipo == 2 || tipo == 3) {
                            System.out.println("Pessoas Jurídicas:");
                            for (PessoaJuridica pj : repoJuridica.obterTodos()) {
                                pj.exibir();
                            }
                        }
                    }
                        
                    case 6 -> {
                        System.out.print("Salvar dados de Pessoas Físicas (1), Jurídicas (2), ou ambos (3)? ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o prefixo do arquivo: ");
                        String prefixo = scanner.nextLine();
                        try {
                            switch (tipo) {
                                case 1 -> {
                                    repoFisica.persistir(prefixo + ".fisica.bin");
                                    System.out.println("Dados de Pessoas Físicas salvos com sucesso.");
                                }
                                case 2 -> {
                                    repoJuridica.persistir(prefixo + ".juridica.bin");
                                    System.out.println("Dados de Pessoas Jurídicas salvos com sucesso.");
                                }
                                case 3 -> {
                                    repoFisica.persistir(prefixo + ".fisica.bin");
                                    repoJuridica.persistir(prefixo + ".juridica.bin");
                                    System.out.println("Dados de Pessoas Físicas e Jurídicas salvos com sucesso.");
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Erro ao salvar os dados: " + e.getMessage());
                        }
                    }
                        
                    case 7 -> {
                        System.out.print("Recuperar dados de Pessoas Físicas (1), Jurídicas (2), ou ambos (3)? ");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Digite o prefixo do arquivo: ");
                        String prefixo = scanner.nextLine();
                        try {
                            switch (tipo) {
                                case 1 -> {
                                    repoFisica.recuperar(prefixo + ".fisica.bin");
                                    System.out.println("Dados de Pessoas Físicas recuperados com sucesso.");
                                }
                                case 2 -> {
                                    repoJuridica.recuperar(prefixo + ".juridica.bin");
                                    System.out.println("Dados de Pessoas Jurídicas recuperados com sucesso.");
                                }
                                case 3 -> {
                                    repoFisica.recuperar(prefixo + ".fisica.bin");
                                    repoJuridica.recuperar(prefixo + ".juridica.bin");
                                    System.out.println("Dados de Pessoas Físicas e Jurídicas recuperados com sucesso.");
                                }
                            }
                        } catch (IOException | ClassNotFoundException e) {
                            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
                        }
                    }
                        
                    case 0 -> System.out.println("Encerrando o programa.");
                        
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
            
        }
    }
}
