package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class InfrastructureDepartment {
	
	private String BILDBOARD_FILE_NAME = "data/BillboardDataExported.csv";
	private String separatorCharacter = "\\|";
	private List<Billboard> list;
	
	public InfrastructureDepartment() {
		list=new ArrayList<Billboard>();
	}

	/*
	public ArrayList<Billboard> getBillboards() {
		return list;
	}
	*/
	
	public void setBillboards(ArrayList<Billboard> billboards) {
		this.list = billboards;
		
	}
	
	
	//Methods
	public void addBillboard(double width, double height, boolean inUse, String brand) throws IOException {
		Billboard billboard= new Billboard(width, height, inUse, brand);
		list.add(billboard);
		saveBillboards() ;
	}
	
	public void saveBillboards() throws IOException{
	    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BILDBOARD_FILE_NAME));
	    oos.writeObject(list);
	    oos.close();
	 }
	
	@SuppressWarnings("unchecked")
	public boolean loadBillboards() throws IOException, ClassNotFoundException{
	    File f = new File(BILDBOARD_FILE_NAME);
	    boolean loaded = false;
	    if(f.exists()){
	      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
	      list = (ArrayList<Billboard>)ois.readObject();
	      ois.close();
	      loaded = true;
	    }
	    return loaded;
	  }
	
	
	public void exportDangerousBillboardReport(String fileName) throws FileNotFoundException{
	    PrintWriter pw = new PrintWriter(fileName);
	    pw.println("===========================\n"+
		    		"DANGEROUS BILLBOARD REPORT\n"+
		    		"===========================\n"+
		    		"The dangerous billboard are:");

	    int j=0;
	    for(int i=0;i<list.size();i++){
	      Billboard billboard = list.get(i);
	     
	      if(billboard.calculateArea()>=160000) {
	    	  j++;
	    	  pw.println((j)+". Billboard "+billboard.getBrand()+" with area "+billboard.calculateArea());
	    
	      }
	    }

	    pw.close();
	 }

	public void importData(String fileName) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String line = br.readLine();
		while(line!=null){
			String[] parts = line.split(separatorCharacter);
			boolean inUse=false;

			if(parts[2].equals("true")) {
				inUse=true;
			}

			if(!parts[0].equals("width")) {
				double width =Double.parseDouble(parts[0]);
		    	double height =Double.parseDouble(parts[1]);
				addBillboard(width,height, inUse, parts[3]);
			}

			line = br.readLine();

		}
		br.close();
	}
	
	
	//to String
		public String toString(){

			String answer = "\nNombres: ";
			


			return answer;
		}
	
	

}