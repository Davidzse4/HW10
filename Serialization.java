import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * THis program will present the user with a menu with multiple options for interacting with 
 * a file using serialization.
 */
/*
 * The Account class will be used as the object type that will be saved and read from
 * the file 
 */
class Account implements Serializable{
	String name;
	String pNumber;
	String dob;
	String email;
	public Account(String name, String pNumber, String dob, String email) {
		this.name = name;
		this.pNumber = pNumber;
		this.dob = dob;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Account [name=" + name + ", pNumber=" + pNumber + ", dob=" + dob + ", email=" + email + "]";
	}
		
}

public class Serialization {

	public static void main(String[] args) throws IOException {
		ArrayList<Account> list = new ArrayList<>();
		Scanner kb = new Scanner(System.in);
		System.out.println("Please make a selection" + 
				"\n1 = Add information into file" +
				"\n2 = Retrive information from file" +
				"\n3 = Delete information" +
				"\n4 = Update information" +
				"\n5 = Exit");
		int choice = kb.nextInt();
		while (choice != 5) {		// 5=Exit
			if (choice == 1) {		// 1=Add to file 
				kb.nextLine();
				System.out.println("Enter customer name: ");
				String custName = kb.nextLine();
				System.out.println("Enter phone number: ");
				String phoneNum = kb.nextLine();
				System.out.println("Enter DOB (MM-DD-YY): ");
				String dob = kb.nextLine();
				System.out.println("Enter Email: ");
				String email = kb.nextLine();
				Account custAccount = new Account(custName, phoneNum, dob, email);
				list.add(custAccount);
				try {
					writeToFile(list);					
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			else if(choice == 2) {		//2=read from file
				try {
					readFile();
				} catch(ClassNotFoundException | IOException e) {
					System.out.println(e.getMessage());
				}
			}
			else if(choice == 3) {		//Delete from file
				
				try {
					readFile2();
				} catch(ClassNotFoundException | IOException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("Which account would you like to delete [enter number of account]:");
				int num = kb.nextInt();
				list.remove(num - 1);
				try {
					writeToFile(list);					
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			else if(choice == 4) {  	//update file
				try {
					readFile2();
				} catch(ClassNotFoundException | IOException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("Which account would you like to update [enter number of account]:");
				int num = kb.nextInt();
				kb.nextLine();
				System.out.println("Enter customer name: ");
				String custName = kb.nextLine();
				System.out.println("Enter phone number: ");
				String phoneNum = kb.nextLine();
				System.out.println("Enter DOB (MM-DD-YY): ");
				String dob = kb.nextLine();
				System.out.println("Enter Email: ");
				String email = kb.nextLine(); 
				list.get(num - 1).name = custName;
				list.get(num - 1).pNumber = phoneNum;
				list.get(num - 1).dob = dob;
				list.get(num - 1).email = email;
				try {
					writeToFile(list);					
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println("Please make a selection" + 
					"\n1 = Add information into file" +
					"\n2 = Retrive information from file" +
					"\n3 = Delete information" +
					"\n4 = Update information" +
					"\n5 = Exit");
			choice = kb.nextInt();
			
		}
	}
	
	public static void writeToFile(ArrayList<Account> list) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Test.bin"));
		oos.writeObject(list);
	}
	
	public static void readFile() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Test.bin"));
		ArrayList<Account> acc = (ArrayList<Account>) ois.readObject();
		for (int i = 0; i < acc.size(); ++i) {
			Account A = (Account) acc.get(i);
			System.out.println(A + "\n");
		}
		
	}
	public static void readFile2() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Test.bin"));
		ArrayList<Account> acc = (ArrayList<Account>) ois.readObject();
		for (int i = 0; i < acc.size(); ++i) {
			Account A = (Account) acc.get(i);
			System.out.println((i + 1) + " " + A + "\n");
		}
		
	}

}
