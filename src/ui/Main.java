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
		
		
		do {
			System.out.println("Please, choose the service you want to enter:"
					+ " \n(1) Add new Billboard"
					+ " \n(2) Show city Billboards"
					+ " \n(3) Report on the danger of Billboards that exceed 160 cm^2"
					+ " \n(5) To exit");
			System.out.print("Option: ");
			line = ppal.br.readLine();
			option = Integer.parseInt(line);
			
			switch(option) {
			case 1:
				ppal.addNewBillboard();
				break;
			case 2:
				ppal.showBillboard();
				break;
			case 3:
				ppal.printReport();
				break;
			case 5:
				System.out.println("\n\n\n---------------------"
						+"\n---See you soon :D---"
						+"\n---------------------");
				break;
			}
			
		}while(option != EXIT_OPTION);
	}
	
	public void addNewBillboard() throws IOException {
		System.out.println("\n===============================\n    Billboard Formulary    \n===============================\n");
		System.out.println("Please fill the following fields about the new Billboard");
		System.out.print("Weight: ");
		String w = br.readLine();
		System.out.print("Height: ");
		String h = br.readLine();
		System.out.print("Is in Use?: ");
		String use = br.readLine();
		System.out.print("What is the brand of the Billboard: ");
		String brand = br.readLine();
		
		double width = Double.parseDouble(w);
		double height = Double.parseDouble(h);
		boolean inUse = Boolean.parseBoolean(use);
		
		if(infra.addBillboard(width, height, inUse, brand)) {
			System.out.println("The billboard has been added successfully");
		} else {
			System.out.println("An error occurred while registering the new Billboard");
		}
		
	}
	
	
	
	public void showBillboard() {
		System.out.println(infra.toString());
	}
	
	public void printReport() throws IOException {
		System.out.println(infra.exportDangerousBillboardReport());
	}
	
	
}
