/**
 *
 * @author Shaw Jie Yao
 */

import javax.swing.JFrame;  
import javax.swing.*;
import java.util.*;

class Contact{
    String name;
    String contactNumber;
    String age;
    // Declare instance variables

    Contact(String NAME, String ConNum, String AGE) {
        name = NAME;
        contactNumber = ConNum;
        age = AGE;
    }
    // Constructor (accepts two parameters: name and contact number of the person)
    
    @Override
    public String toString() {
        return "\n Name: " + name + ",  Contact No: " + contactNumber + ",  Age of Contact: " + age;
    }
    // public String method toString()

    public String getName() {
        return name;
    }
    // public String method getName()

    public String getNumber() {
        return contactNumber;
    }
    // public String method getNumber()
    
    public String getAge() {
        return age;
    }

    public boolean equalsName(String name) {
        return getName().toLowerCase().equals(name.toLowerCase());
    }
    // public boolean method equalsName()
}

public class PhoneBookGUI {
    JFrame frame; 
    JLabel label;
    ArrayList contacts;

    PhoneBookGUI() {
        this.frame = new JFrame("PhoneBook Application!");
        contacts = new ArrayList();
    }
    
    public static void main(String args[]) {
        PhoneBookGUI press;
        String select;
        JFrame frame;
        
        press = new PhoneBookGUI();
        frame = new JFrame("PhoneBook Application!");

        do {
            select = JOptionPane.showInputDialog (frame, """
                                                  Welcome to the PhoneBook (GUI) Application [Java Ver 1.0]
                                                  What would you like to do? Press: 
                                                  (P) Print all Contact Information from the PhoneBook
                                                  (A) Add a New Contact to the Phonebook
                                                  (E) Edit Contact Information
                                                  (D) Delete a Contact from the Phonebook
                                                  (S) Search Contact Information by Name
                                                  (Q) Quit the PhoneBook Application""", "PhoneBookGUI", JOptionPane.PLAIN_MESSAGE);
   
            switch(select) {
                case "P" -> press.printAllContacts();
                case "p" -> press.printAllContacts();
                
                case "A" -> press.addContact();
                case "a" -> press.addContact();

                case "E" -> press.editContact();
                case "e" -> press.editContact();

                case "D" -> press.deleteContact();
                case "d" -> press.deleteContact();

                case "S" -> press.searchContact();
                case "s" -> press.searchContact();

                case "Q" -> {
                    return;
                }
                case "q" -> {
                    return;
                }
                
                default -> JOptionPane.showMessageDialog(null, "You pressed an invalid key, (P, A, E, D, S, Q) only!");
            }
        }
        while(!select.equals("Q") && !select.equals("q"));
    }

    public void addContact(){
        String NAME, ConNum, age;
        Contact pb;
        boolean successful;
        
        NAME = JOptionPane.showInputDialog(frame, "Enter Contact Name: ", "addContact", JOptionPane.PLAIN_MESSAGE);
        ConNum = JOptionPane.showInputDialog(frame, "Enter Contact Number: ", "addContact", JOptionPane.PLAIN_MESSAGE);
        age = JOptionPane.showInputDialog(frame, "Enter Age of Contact: ", "addContact", JOptionPane.PLAIN_MESSAGE);
        pb = new Contact(NAME, ConNum, age);
        successful = contacts.add(pb);
            
        if(successful){
            JOptionPane.showMessageDialog(frame, "Contact is successfully added.", "addContact", JOptionPane.INFORMATION_MESSAGE);
        } 
            
        else{
            JOptionPane.showMessageDialog(frame, "Phonebook is full!", "addContact", JOptionPane.WARNING_MESSAGE);
        } 
    }

    public void editContact() {
        int c, check = 0;
        String name;
        
        name = JOptionPane.showInputDialog(frame, "Enter Contact Name to be [Updated]: ", "editContact", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(frame, "Contact is now being searched for in the Database... ", "editContact", JOptionPane.INFORMATION_MESSAGE);
        
        for(c = 0; c < contacts.size(); c++){
            Contact pb = (Contact)contacts.get(c);
            
            if(name.equals(pb.name)){
                check = 1;
                pb.name = JOptionPane.showInputDialog(frame, "Enter the New Contact Name: ", "editContact", JOptionPane.PLAIN_MESSAGE);
                pb.contactNumber = JOptionPane.showInputDialog(frame, "Enter the New Contact Number: ", "editContact", JOptionPane.PLAIN_MESSAGE);
                pb.age = JOptionPane.showInputDialog(frame, "Enter the New Age of Contact: ", "editContact", JOptionPane.PLAIN_MESSAGE);
                contacts.set(c, pb); 
          
                JOptionPane.showMessageDialog(frame, "The Contact has been successfully updated!", "editContact", JOptionPane.INFORMATION_MESSAGE);
            }
        } 
        
        if(check == 0){
            JOptionPane.showMessageDialog(frame, "Sorry, the Contact does not exist in the Database.", "editContact", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void deleteContact() {
        int c, select, check = 0;
        String name;
        
        name = JOptionPane.showInputDialog(frame, "Enter Contact Name:", "deleteContact", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(frame,"Contact is now being searched for in the Database... ", "deleteContact", JOptionPane.INFORMATION_MESSAGE);
        
        OUTER:
        for(c = 0; c < contacts.size(); c++) {
            Contact pb = (Contact)contacts.get(c);
            
            if(pb.name != null && name.equals(pb.name)) {
                check = 1;
                select = JOptionPane.showConfirmDialog(frame,"Are you sure you want to proceed in deleting this contact? [Note: Records may not be restored.]", "deleteContact", JOptionPane.YES_NO_OPTION);
                
                switch(select) {
                    case JOptionPane.NO_OPTION -> {
                        JOptionPane.showMessageDialog(frame, "Aaah! Why did you change your mind?", "deleteContact", JOptionPane.QUESTION_MESSAGE);
                        break OUTER;
                    }
                    case JOptionPane.YES_OPTION -> {
                        contacts.remove(c);
                        JOptionPane.showMessageDialog(frame, "Contact Info was successfully deleted from the PhoneBook.", "deleteContact", JOptionPane.INFORMATION_MESSAGE);
                        break OUTER;
                    }
                    default -> JOptionPane.showMessageDialog(frame, "It seems you have made an invalid choice, please try again.", "deleteContact", JOptionPane.INFORMATION_MESSAGE);
                }
            }    
        }
        
        if(check == 0){
            JOptionPane.showMessageDialog(frame, "Sorry, the Contact does not exist in the Database.", "deleteContact", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void printAllContacts() {
        int pac, p, count = 0;
        String list, select;
        
        list = "";
        
        for(pac = 0; pac < contacts.size();) {
            for(p = 0; count < contacts.size() && p < 10;) {
                if(contacts.listIterator() != null) {
                    list = list + contacts.toString();
                    p++;
                    count++;
                }
                pac++;
            }
            
            if(contacts.size() <= count) {
                break;
            }
        }
        
        if(count == 0){
            JOptionPane.showMessageDialog(frame, "You haven't saved any contacts yet.", "printAllContacts", JOptionPane.WARNING_MESSAGE);
        }
        
        else{
            JOptionPane.showMessageDialog(frame, contacts.toString() + "\n", "printAllContacts", JOptionPane.PLAIN_MESSAGE);
        }
 
        do{
            select = JOptionPane.showInputDialog(frame, "Press (G) to go back to the Main Menu...", "printAllContacts", JOptionPane.PLAIN_MESSAGE);
        } 
        while (!select.equals("G") && !select.equals("g"));
    }

    public void searchContact() {
        int c, contactname = 0;
        String name;
        
        name = JOptionPane.showInputDialog(frame, "Enter Contact Name: ", "searchContact", JOptionPane.PLAIN_MESSAGE);
        JOptionPane.showMessageDialog(frame, "Contact is now being searched for in the Database... ", "searchContact", JOptionPane.INFORMATION_MESSAGE);
        
        for(c = 0; c < contacts.size(); c++){
            Contact pb = (Contact)contacts.get(c);
            
            if(name.equals(pb.name)){
                contactname = 1;
                JOptionPane.showMessageDialog(frame, pb.toString(), "searchContact", JOptionPane.PLAIN_MESSAGE);
            }
        }
            
        if(contactname == 0){
            JOptionPane.showMessageDialog(frame, "Sorry, the Contact does not exist in the Database.", "searchContact", JOptionPane.WARNING_MESSAGE);
        }
    }
}