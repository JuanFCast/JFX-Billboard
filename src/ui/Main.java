package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.InfrastructureDepartment;

public class Main {
	
	private final int EXIT_OPTION = 5;
	
	private BufferedReader br;
	
	private InfrastructureDepartment deparment;
	
	public Main() throws IOException {
		deparment = new InfrastructureDepartment();
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String [] args) throws IOException {
		
	}
	
	
}
