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
	
	private final String BILDBOARD_FILE_NAME = "data/BillboardDataExported.csv";
	private final String FILE_REPORT_PATH = "data/report.txt";
	private final String FILE_SAVE_PATH = "data/SecretCode.danger";
	
	private String separatorCharacter = "\\|";
	
	private List<Billboard> list;
	
	
	//Constructor
	public InfrastructureDepartment() throws IOException {
		list=new ArrayList<Billboard>();
		importData(BILDBOARD_FILE_NAME);
		//exportDangerousBillboardReport(FILE_REPORT_PATH);
	}

	
	public List<Billboard> getBillboards() {
		return list;
	}
	
	
	public void setBillboards(ArrayList<Billboard> billboards) {
		this.list = billboards;
		
	}
	
	
	//Methods
	public void addBillboard(double width, double height, boolean inUse, String brand) throws IOException {
		Billboard billboard= new Billboard(width, height, inUse, brand);
		list.add(billboard);
		saveBillboards() ;
	}
	
	
	private void saveBillboards() throws IOException{
	    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SAVE_PATH));
	    oos.writeObject(list);
	    oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public boolean loadBillboards() throws IOException, ClassNotFoundException{
	    File f = new File(FILE_SAVE_PATH);
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
	public String toString() {
		String answer= "";
		answer+="w		h		inUse		Brand\n";
		for (int i = 0; i<list.size(); i++) {
			answer+=(list.get(i).getWidth()+"		"+list.get(i).getHeight()+"		"+list.get(i).isInUse()+"		"+list.get(i).getBrand()+"		\n");;
			
		}
		answer+="TOTAL: "+ list.size() + " vallas";
		return answer;
	}
	
	

}