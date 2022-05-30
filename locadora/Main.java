package locadora;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Clientes customers = new Clientes();
    private static Locacoes rentals = new Locacoes();

    private static Categorias categories = loadCategories();
    private static Veiculos vehicles = loadVechicles(categories);

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        int option;

        // logLn("\n---------- ÁREA DE VEÍCULOS ----------\n");
        //            TESTAR LISTAGEM COM ASC E DESC

        // logLn("\n---------- ÁREA DE LOCAÇÕES ----------\n");
        //           PRECISA FAZER FILTRO DOS VEÍCULOS
        // logLn("--------------------------------------");
        Cliente david = new Cliente("David Maartins", "1172312663", "55998726363", "083648638");
        customers.inserirCliente(david);
        do {
            printMenu();
            option = input.nextInt();

            switch (option) {
                case 1:
                    insertCustomer();
                    break;

                case 2:
                    deleteCustomer();
                    break;

                case 3:
                    updateCustomer();
                    break;

                case 4:
                    customers.imprime(1);
                    break;

                case 5:
                    insertVehicle();
                    break;

                case 6:
                    deleteVehicle();
                    break;

                case 7:
                    updateVehicle();
                    break;

                case 8:
                    Scanner inputVehicle = new Scanner(System.in);
                    log("QUAL O TIPO DE CLASSIFICAÇAO?\n1 - Crescente\n0 - Decrescente\n\n> ");
                    int typeSort = inputVehicle.nextInt();
                    vehicles.imprime(typeSort);
                    break;

                case 9:
                    insertCategory();
                    break;
                    
                case 10:
                    deleteCategory();
                    break;

                case 11:
                    updateCategory();
                    break;

                case 12:
                    categories.imprime(1);
                    break;

                case 13:
                    insertRental();
                    break;

                case 14:
                    deleteRental();
                    break;

                case 15:
                    rentals.imprime(1);
                    break;

                default:
                    break;
            }
        } while (option != 16);

    }

    public static void deleteCustomer() {
        Scanner input = new Scanner(System.in);

        log("INSIRA O CPF DO CLIENTE: ");
        String cpf = input.nextLine();

        NohCliente nohCliente = customers.busca(customers.getInicio(), cpf);
        Cliente cliente = nohCliente.getCliente();

        boolean exists = rentals.existeClienteVinculado(rentals.getInicio(), cliente.getCNH());

        if(exists) {
            log("\nO CLIENTE " + cliente.getNome() + " ESTÁ ATRELADO A UMA LOCAÇÃO, VOCÊ NÃO PODE REMOVÊ-LO\n");
        } else {
            customers.removerCliente(cpf);
            log("\nCLIENTE " + cpf + " EXCLUÍDO COM SUCESSO\n");
        }
    }

    public static void deleteVehicle() {
        Scanner input = new Scanner(System.in);

        log("INSIRA A PLACA DO VEÍCULO: ");
        String plate = input.nextLine();

        boolean exists = rentals.existeVeiculoLocado(rentals.getInicio(), plate);
        if(exists) {
            log("\nO VEÍCULO " + plate + " ESTÁ ATRELADO A UMA LOCAÇÃO, VOCÊ NÃO PODE REMOVÊ-LA\n");
        } else {
            NohVeiculos nohVeiculos = vehicles.busca(vehicles.getInicio(), plate);
            Veiculo veiculo = nohVeiculos.getVeiculo();
            vehicles.removerVeiculo(veiculo);
            log("\nVEÍCULO " + plate + " EXCLUÍDO COM SUCESSO\n");
        }
    }

    public static void deleteRental() {
        Scanner input = new Scanner(System.in);

        logLn("INSIRA A PLACA DO VEÍCULO PARA DEVOLUÇÃO: ");
        String plate = input.nextLine();
        
        rentals.removerLocacao(plate);
        logLn("\n DEVOLUÇÃO DE VEÍCULO REALIZADA COM SUCESSO\n");
    }

    public static void insertRental() {
        Scanner input = new Scanner(System.in);

        logLn("INSIRA O CNH DO CLIENTE: ");
        String cnh = input.nextLine();
        
        logLn("VEÍCULOS DISPONÍVEIS PARA LOCAÇÃO: ");
        
        NohVeiculos node = vehicles.getInicio();
        
        while (node != null) {
            Veiculo veiculo = node.getVeiculo();
            boolean vehicleAvailable = !rentals.existeVeiculoLocado(rentals.getInicio(), veiculo.getPlaca());
            
            if (vehicleAvailable) {
                logLn(veiculo.toString());
            }
            node = node.getProx();
        }

        logLn("INSIRA A PLACA DO VEÍCULO DISPONÍVEL: ");
        String plate = input.nextLine();
        
        logLn("INSIRA A DATA DE RETIRADA (formato: dd/mm/yyyy): ");
        String dataRetirada = input.nextLine();
        
        logLn("INSIRA A DATA DE DEVOLUÇÃO (formato: dd/mm/yyyy): ");
        String dataDevolucao = input.nextLine();
        
        logLn("INSIRA O VALOR DA LOCAÇÃO: ");
        float valor = input.nextFloat();

        Date dateRetirada = createDateFromStringFormatted(dataRetirada);
        Date dateDevolucao = createDateFromStringFormatted(dataDevolucao);

        Locacao locacao = new Locacao(cnh, plate, dateRetirada, dateDevolucao, valor);
        rentals.inserirLocacao(locacao);

        logLn("\nLOCAÇÃO DO VEÍCULO " + plate + " REALIZADO COM SUCESSO\n");

    }

    public static void deleteCategory() {
        Scanner input = new Scanner(System.in);

        log("INSIRA O IDENTIFICADOR DA CATEGORIA: ");
        int id = input.nextInt();

        boolean exists = vehicles.existeVeiculoPorCategoria(vehicles.getInicio(), id);
        if(exists) {
            log("\nA CATEGORIA " + id + " ESTÁ ATRELADA A UM VEÍCULO, VOCÊ NÃO PODE REMOVÊ-LA\n");
        } else {
            categories.removerCategoria(id);
            log("\nCATEGORIA " + id + " EXCLUÍDA COM SUCESSO\n");
        }
    }

    public static void updateCategory() {
        Scanner input = new Scanner(System.in);

        log("INSIRA O NOME DA CATEGORIA: ");
        String name = input.nextLine();
        log("INSIRA O IDENTIFICADOR DA CATEGORIA: ");
        int id = input.nextInt();

        categories.editar(id, name);
        log("\nCATEGORIA " + name + " EDITADA COM SUCESSO\n");
    }

    public static void updateVehicle() {
        Scanner input = new Scanner(System.in);

        log("INSIRA A PLACA DO VEICULO: ");
        String plate = input.nextLine();
        log("INSIRA O MODELO DO VEICULO: ");
        String model = input.nextLine();
        log("INSIRA O ANO DO VEICULO: ");
        int year = input.nextInt();
        log("INSIRA A POTENCIA DO VEICULO: ");
        int potency = input.nextInt();
        log("INSIRA A QUANTIDADE DE LUGARES DO VEICULO: ");
        int amountPlaces = input.nextInt();
        input.nextLine(); // Somente pra pegar o enter do buffer
        log("INSIRA A MARCA DO VEICULO: ");
        String brand = input.nextLine();

        logLn("CATEGORIAS DISPONÍVEIS ABAIXO");
        categories.imprime(1);

        log("INSIRA O IDENTIFICADOR DA CATEGORIA DO VEICULO: ");
        int id = input.nextInt();

        NohCategoria nohCategory = categories.busca(categories.getInicio(), id);
        Categoria category = nohCategory.getCategoria();

        vehicles.editar(plate, model, brand, year, potency, amountPlaces, category);
        log("\nVEÍCULO " + plate + " EDITADO COM SUCESSO\n");
    }

    public static void updateCustomer() {
        Scanner input = new Scanner(System.in);

        log("INSIRA O NOME DO CLIENTE: ");
        String name = input.nextLine();
        log("INSIRA O CNH DO CLIENTE: ");
        String cnh = input.nextLine();
        log("INSIRA O TELEFONE DO CLIENTE: ");
        String phone = input.nextLine();
        log("INSIRA O CPF DO CLIENTE: ");
        String cpf = input.nextLine();

        customers.editar(cpf, name, cnh, phone);
        log("\nCLIENTE " + name + " EDITADO COM SUCESSO\n");
    }

    public static void insertVehicle() {
        Scanner input = new Scanner(System.in);

        log("INSIRA A PLACA DO VEICULO: ");
        String plate = input.nextLine();
        log("INSIRA O MODELO DO VEICULO: ");
        String model = input.nextLine();
        log("INSIRA O ANO DO VEICULO: ");
        int year = input.nextInt();
        log("INSIRA A POTENCIA DO VEICULO: ");
        int potency = input.nextInt();
        log("INSIRA A QUANTIDADE DE LUGARES DO VEICULO: ");
        int amountPlaces = input.nextInt();
        input.nextLine(); // Somente pra pegar o enter do buffer
        log("INSIRA A MARCA DO VEICULO: ");
        String brand = input.nextLine();

        logLn("CATEGORIAS DISPONÍVEIS ABAIXO");
        categories.imprime(1);

        log("INSIRA O IDENTIFICADOR DA CATEGORIA DO VEICULO: ");
        int id = input.nextInt();

        NohCategoria nohCategory = categories.busca(categories.getInicio(), id);
        Categoria category = nohCategory.getCategoria();

        Veiculo vehicle = new Veiculo(plate, model, brand, year, potency, amountPlaces, category);
        vehicles.inserirVeiculo(vehicle);
        log("\nVEÍCULO " + plate + " ADICIONADO COM SUCESSO\n");
    }

    public static void insertCustomer() {
        Scanner input = new Scanner(System.in);

        log("INSIRA O NOME DO CLIENTE: ");
        String name = input.nextLine();
        log("INSIRA O CNH DO CLIENTE: ");
        String cnh = input.nextLine();
        log("INSIRA O TELEFONE DO CLIENTE: ");
        String phone = input.nextLine();
        log("INSIRA O CPF DO CLIENTE: ");
        String cpf = input.nextLine();

        Cliente customer = new Cliente(name, cnh, phone, cpf);
        customers.inserirCliente(customer);
        log("\nCLIENTE " + name + " ADICIONADO COM SUCESSO\n");
    }

    public static void insertCategory() {
        Scanner input = new Scanner(System.in);

        log("INSIRA O NOME DA CATEGORIA: ");
        String name = input.nextLine();
        log("INSIRA O IDENTIFICADOR DA CATEGORIA: ");
        int id = input.nextInt();

        Categoria category = new Categoria(id, name);
        categories.inserirCategoria(category);
        log("\nCATEGORIA " + name + " ADICIONADA COM SUCESSO\n");
    }

    public static Categorias loadCategories() {
        Categorias categories = new Categorias();
        String path = "Categorias.csv";
        Path pathToFile = Paths.get(path);

        try {
            BufferedReader bufferReader = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8);
            String line = bufferReader.readLine(); // Linha ignorada por ser o cabeçalho do CSV.
            line = bufferReader.readLine(); // Ler a primeira linha

            while (line != null) {
                String[] attributes = line.split(";");
                Categoria category = new Categoria(Integer.parseInt(attributes[0]), attributes[1]);
                categories.inserirCategoria(category);
                line = bufferReader.readLine();
            }
        } catch (Exception e) {
            logLn(e.toString());
            logLn("Um erro ocorreu no momento do carregamento do arquivo !");
        }

        return categories;
    }

    public static Veiculos loadVechicles(Categorias categories) {
        Veiculos vehicles = new Veiculos();
        String path = "Veiculos.csv";
        Path pathToFile = Paths.get(path);

        try {
            BufferedReader bufferReader = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8);
            String line = bufferReader.readLine(); // Linha ignorada por ser o cabeçalho do CSV.
            line = bufferReader.readLine(); // Ler a primeira linha

            while (line != null) {
                String[] attributes = line.split(";");
                String plate = attributes[0];
                String model = attributes[1];
                String brand = attributes[2];
                int year = Integer.parseInt(attributes[3]);
                int potency = Integer.parseInt(attributes[4]);
                int amountPlaces = Integer.parseInt(attributes[5]);
                int categoryId = Integer.parseInt(attributes[6]);

                NohCategoria nohCategory = categories.busca(categories.getInicio(), categoryId);
                Categoria category = nohCategory.getCategoria();

                Veiculo vehicle = new Veiculo(plate, model, brand, year, potency, amountPlaces, category);
                vehicles.inserirVeiculo(vehicle);
                line = bufferReader.readLine();
            }
        } catch (Exception e) {
            logLn(e.toString());
            logLn("Um erro ocorreu no momento do carregamento do arquivo !");
        }

        return vehicles;
    }

    public static void printMenu() {
        logLn("\n---------- ÁREA DE CLIENTES ----------\n");
        logLn("[1] - Incluir Cliente");
        logLn("[2] - Excluir Cliente");
        logLn("[3] - Editar Cliente");
        logLn("[4] - Listar Cliente");

        logLn("\n---------- ÁREA DE VEÍCULOS ----------\n");
        logLn("[5] - Incluir Veiculo");
        logLn("[6] - Excluir Veiculo");
        logLn("[7] - Editar Veiculo");
        logLn("[8] - Listar Veiculo");

        logLn("\n---------- ÁREA DE CATEGORIAS ----------\n");
        logLn("[9] - Incluir Categoria");
        logLn("[10] - Excluir Categoria");
        logLn("[11] - Editar Categoria");
        logLn("[12] - Listar Categoria");

        logLn("\n---------- ÁREA DE LOCAÇÕES ----------\n");
        logLn("[13] - Incluir Locação");
        logLn("[14] - Excluir Locação");
        logLn("[15] - Listar Locação");
        logLn("--------------------------------------");

        logLn("[16] - SAIR\n");
        System.out.print("OPÇÃO: ");
    }

    public static void log(String text) {
        System.out.print(text);
    }

    public static void logLn(String text) {
        System.out.println(text);
    }

    public static Date createDateFromStringFormatted(String textDate) { // Trata a partir da formatação dd/mm/yyyy
        String[] dateSplitted = textDate.split("/");

        LocalDate localDate = LocalDate.of(
            Integer.parseInt(dateSplitted[2]), 
            Integer.parseInt(dateSplitted[1]), 
            Integer.parseInt(dateSplitted[0])
        );

        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date;
    }
}