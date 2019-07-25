package search;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static TreeMap<String, List<Integer>> mapPeoples = new TreeMap<>();
    public static List<String> searchList = new ArrayList<>();
    public static int opMenu = -1;
    public static String texto = "";
    public static String strategy = "ALL";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        /*String lista = scanner.nextLine();
        String busca = scanner.nextLine();

        *//*System.out.println(lista);
        System.out.println(busca);*//*
        String msg = "Not found";
        for (int i=0; i< lista.split(" ").length; i++){
            if (lista.split(" ")[i].equals(busca)) msg = i+1+"";
        }
        System.out.println(msg);*/

        /*System.out.println("Enter the number of people:");
        String input = scanner.nextLine();
        int nPeoples = Integer.parseInt(input);

        String[] peoples = new String[nPeoples];
        System.out.println("Enter all people:");
        for (int i = 0; i < nPeoples; i++){
            input = scanner.nextLine();
            peoples[i] = input;
        }*/

        /*System.out.println("Enter the number of search queries:");
        input = scanner.nextLine();
        int nSearch = Integer.parseInt(input);


        while (nSearch > 0){
            System.out.println();
            System.out.println("Enter data to search people:");
            input = scanner.nextLine();
            searchPeople(peoples, input.trim());
            nSearch--;
        }*/

        String fileName = "C:\\Cursos\\Java\\agenda.txt";
        readFile(fileName);

        String[] peoples = new String[texto.split(";").length];
        peoples = texto.split(";");

        mapearChaves(peoples);

        while (opMenu != 0) {
            printMenu();
            switch (opMenu){
                case 1:
                    findPerson(peoples);
                    break;
                case 2:
                    printLista(peoples);
                    break;
                case 0:
                    System.out.println("Bye!");
                    break;
                default:
                    break;
            }
        }
    }

    public static void findPersonOld(String[] lista){
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        strategy = scanner.nextLine();
        System.out.println(strategy);
        System.out.println("\nEnter a name or email to search all suitable people.");
        String input = scanner.nextLine();
        //System.out.println("input="+input);
        //buscaInversa(input.trim(), lista);
        switch (strategy){
            case "ANY":
                //buscaInversaAny(input.trim(), lista);

                break;
            case "ALL":
                buscaInversaAll(input.trim(), lista);
                break;
            case "NONE":
                buscaInversaNone(input.trim(), lista);
                break;
            default:
                System.out.println("not found");
                break;
        }
        //searchPeople(lista, input.trim());
        System.out.println();
    }

    public static void findPerson(String[] lista){
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        strategy = scanner.nextLine();
        System.out.println(strategy);
        System.out.println("\nEnter a name or email to search all suitable people.");
        String input = scanner.nextLine();
        SelectionContext ctx = new SelectionContext();
        switch (strategy){
            case "ANY":
                ctx.setAlgorithm(new SearchInversaANY());
                break;
            case "ALL":
                ctx.setAlgorithm(new SearchInversaALL());
                break;
            case "NONE":
                ctx.setAlgorithm(new SearchInversaNONE());
                break;
            default:
                System.out.println("not found");
                break;
        }
        ctx.search(lista, input.trim());
        //searchPeople(lista, input.trim());
        System.out.println();
    }

    public static void buscaInversa(String filtro, String[] peoples){
        System.out.println(filtro);
        if (mapPeoples.containsKey(filtro.toUpperCase())){
            System.out.println(mapPeoples.get(filtro.toUpperCase()).size()+" persons found:");
            for (int lin: mapPeoples.get(filtro.toUpperCase())){
                System.out.println(peoples[lin]);
            }
        }else{
            System.out.println("No matching people found.");
        }
    }


    public static void mapearChaves(String[] lista){
        for (int i = 0; i < lista.length; i++){
            String linha = lista[i];
            for (int x=0;x < linha.split("[ ]").length; x++){
                String key = linha.split("[ ]")[x];
                if (!mapPeoples.containsKey(key.toUpperCase())){
                    mapPeoples.put(key.toUpperCase(), Collections.singletonList(i));
                }else{
                    List<Integer> value = new ArrayList<>(mapPeoples.get(key.toUpperCase()));
                    value.add(i);
                    mapPeoples.put(key.toUpperCase(), value);
                }
            }
        }
    }

    public static void readFile(String fileName){
        File file = new File(fileName);
        try (Scanner sc = new Scanner(file)){
            while (sc.hasNextLine()){
                String row = sc.nextLine();
                texto = texto + row + ";";
            }
        } catch (FileNotFoundException e){
            System.out.println("No file found: "+fileName);
        }
    }

    public static void printLista(String[] lista){
        System.out.println("=== List of people ===");
        for (String row: lista){
            System.out.println(row);
        }
        System.out.println();
    }


    /*-- pelo menos 1 item --*/
    public static void buscaInversaAny(String filtro, String[] peoples){
        System.out.println(filtro);
        searchList.clear();
        for (String item : filtro.split(" ")){
            if (mapPeoples.containsKey(item.toUpperCase())){
                for (int lin: mapPeoples.get(item.toUpperCase())){
                    if (searchList.indexOf(peoples[lin]) == -1){
                        searchList.add(peoples[lin]);
                    }
                }
            }
        }
        for (String item : searchList){
            System.out.println(item);
        }
    }

    /*-- todos os itens --*/
    public static void buscaInversaAll(String filtro, String[] peoples){
        System.out.println(filtro);
        searchList.clear();
        for (String info: peoples){
            boolean all = false;
            for (String item : filtro.split(" ")){
                for (String v1 : filtro.split(" ")){
                    all = item.toUpperCase() == v1.toUpperCase();
                    if (all) {
                        break;
                    }
                }
                if (!all) break;
            }
            if (all) searchList.add(info);
        }

        for (String item : searchList){
            System.out.println(item);
        }
    }

    /*-- nenhum item --*/
    public static void buscaInversaNone(String filtro, String[] peoples){

    }

    public static void printMenu(){
        System.out.println("=== Menu ===\n" +
                "1. Find a person\n" +
                "2. Print all people\n" +
                "0. Exit");
        try {
            String op = scanner.nextLine();
            System.out.println(op);
            opMenu = Integer.parseInt(op);
            if (opMenu != 0 && opMenu != 1 && opMenu !=2){
                System.out.println("\nIncorrect option! Try again.\n");
                printMenu();
            }
        }catch(Exception e){
            printMenu();
        }

    }

    public static void searchPeople(String[] lista, String filtro){
        String busca = "";
        for (int y = 0; y < lista.length; y++){
            if (lista[y].toUpperCase().indexOf(filtro.toUpperCase()) >= 0){
                busca += lista[y]+",";
            }
        }
        if (!"".equals(busca)) {
            System.out.println();
            System.out.println("Found people:");
            for (int x = 0; x < busca.split(",").length; x++){
                System.out.println(busca.split(",")[x]);
            }
        }
    }

    public static void stage1(){
        /*String lista = scanner.nextLine();
        String busca = scanner.nextLine();

        System.out.println(lista);
        System.out.println(busca);
        String msg = "Not found";
        for (int i=0; i< lista.split(" ").length; i++){
            if (lista.split(" ")[i].equals(busca)) msg = i+1+"";
        }
        System.out.println(msg);*/
    }

}
