package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Schedule {
    final static Scanner scanner = new Scanner(System.in);
    private List<Contact> schedule = new ArrayList<>();

    public Schedule(){
        schedule.clear();
    }

    private void addContact(Contact contact){
        this.schedule.add(contact);
    }

    public void newContact(){
        Contact contato = new Contact();
        System.out.println("Enter the name of the person:");
        contato.setName(scanner.nextLine());
        System.out.println("Enter the surname of the person:");
        contato.setSurname(scanner.nextLine());
        System.out.println("Enter the number:");
        contato.setPhone(scanner.nextLine());
        addContact(contato);
    }

}