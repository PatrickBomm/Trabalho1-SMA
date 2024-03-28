package Menu;

import java.util.Scanner;

public class Menu {

	// Menu option descriptions with improved aesthetics
	private static final String[] MENU_OPTIONS = {
			"1 - Simulação de Fila Simples\n    Parâmetros: Chegadas 1-2, Atendimento 3-6, Números Aleatórios: 7 ([0.3276, 0.8851, ...]), Tempo Inicial: 2",
			"2 - Modelo de Fila Padrão G/G/1/5\n    Parâmetros: Chegadas 2-4, Atendimento 3-5, Números Aleatórios: 100,000, Tempo Inicial: 3, Execução Média: 5",
			"3 - Modelo de Fila Aprimorado G/G/2/5\n    Parâmetros: Chegadas 2-4, Atendimento 3-5, Números Aleatórios: 100,000, Tempo Inicial: 3, Execução Média: 5",
			"4 - Cenário Complexo: Duas Filas em Tandem",
			"5 - Sair"
	};

	/**
	 * Print the menu options with aesthetic enhancements.
	 */
	private static void printMenu() {
		System.out.println("\n##################### MENU DE SIMULAÇÃO DE FILAS #####################\n");
		for (String option : MENU_OPTIONS) {
			System.out.println(option);
		}
		System.out.println("\n######################################################################");
		System.out.print("\nSelecione uma opção: ");
	}

	/**
	 * Execute the action based on the selected menu option.
	 * 
	 * @param option Selected menu option
	 */
	private static void executeOption(int option) {
		switch (option) {
			case 1:
				System.out.println("\n## Executando Simulação de Fila Simples ##");
				Select.ExampleClass();
				break;
			case 2:
				System.out.println("\n## Executando Modelo de Fila Padrão ##");
				Select.PartOne();
				break;
			case 3:
				System.out.println("\n## Executando Modelo de Fila Aprimorado ##");
				Select.PartTwo();
				break;
			case 4:
				System.out.println("\n## Executando Cenário Complexo: Duas Filas em Tandem ##");
				Select.PartThree();
				break;
			case 5:
				System.out.println("Saindo...");
				System.exit(0);
			default:
				System.out.println("Opção inválida. Por favor, selecione um número entre 1 e " + MENU_OPTIONS.length);
		}
	}

	/**
	 * Display the main menu in an infinite loop until the user chooses to exit.
	 */
	public static void menu() {
		try (Scanner scanner = new Scanner(System.in)) {
			int selectedOption;
			while (true) { // Infinite loop
				printMenu();
				while (!scanner.hasNextInt()) {
					System.out.println("Por favor, insira um número válido.");
					scanner.next(); // Consume the non-integer input
				}
				selectedOption = scanner.nextInt();
				executeOption(selectedOption);
			}
		} catch (Exception ex) {
			System.out.println("Ocorreu um erro: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
