package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.InfrastructureDepartment;

public class Main {
	
	private final static int EXIT_OPTION = 5;
	private BufferedReader br;
	public InfrastructureDepartment infra;
	
	public Main() throws IOException {
		infra = new InfrastructureDepartment();
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public static void main(String [] args) throws IOException{
		Main ppal = new Main();
		int option = 0;
		String line = "";
		
		System.out.println("------------------------------------------");
		System.out.println("---------WELCOME TO THE BILLBOARD---------");
		System.out.println("------------------------------------------");
		System.out.println("Please, choose the service you want to enter:"
				+ " \n(1) Add new Billboard"
				+ " \n(2) Show city Billboards"
				+ " \n(3) Report on the danger of fences that exceed 162 cm^2"
				+ " \n(5) To exit");
		
		do {
			System.out.print("Option: ");
			line = ppal.br.readLine();
			option = Integer.parseInt(line);
			
			switch(option) {
			case 1:
				//ppal.addNewBillboard();
				break;
			case 2:
				ppal.showBillboard();
				break;
			case 3:
				//ppal.printReport();
				break;
			case 5:
				System.out.println("\n\n\n---------------------"
						+"\n---See you soon :D---"
						+"\n---------------------");
				break;
			}
			
		}while(option != EXIT_OPTION);
	}
	
	
	public void showBillboard() {
		System.out.println(infra.toString());
	}
	
	
}
