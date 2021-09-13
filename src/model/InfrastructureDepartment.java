package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class InfrastructureDepartment {
	
	private final String BILDBOARD_FILE_NAME = "data/BillboardDataExported.csv";
	public final String FILE_REPORT_PATH = "data/report.txt";
	private final String FILE_SAVE_PATH = "data/SecretCode.danger";
	
	private String separatorCharacter = "\\|";
	
	//BufferReader
	private BufferedReader br;
	
	private List<Billboard> list;
	
	
	//Constructor
	public InfrastructureDepartment() throws IOException {
		list=new ArrayList<Billboard>();
		importData();
	}

	
	public List<Billboard> getBillboards() {
		return list;
	}
	
	
	public void setBillboards(ArrayList<Billboard> billboards) {
		this.list = billboards;
		
	}
	
	
	//Methods
	public boolean addBillboard(double w, double h, boolean inUse, String brand) throws IOException {
		
		Billboard newBillboard = new Billboard(w, h, inUse, brand);
		FileWriter fw = new FileWriter(BILDBOARD_FILE_NAME, true);
		fw.write(w + "|" + h + "|" + inUse + "|" + brand + "\n");
		
		fw.close();
		
		if(list.add(newBillboard)) {
			saveBillboards();
			return true;
		} else {
			return false;
		}
		
		
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
	
	public String exportDangerousBillboardReport() throws IOException {
		String report = "===========================\n"+
	    		"DANGEROUS BILLBOARD REPORT\n"+
	    		"===========================\n"+
	    		"The dangerous billboard are:\n"; 
		int j=1;
		
		for (int i = 0; i < list.size(); i++) {
			
			if(list.get(i).calculateArea()>=160) {
				report += j + ". Billboard "+list.get(i).getBrand()+" with area "+list.get(i).calculateArea() + " cm^2\n";
				j++;
			}
		}
		FileWriter fw = new FileWriter(FILE_REPORT_PATH, false);
		fw.write(report);

		fw.close();
		report+="TOTAL: "+ (j-1) + " Billboards\n";
		return report;
		
	}
	
	
	public void importData() throws IOException {
		br = new BufferedReader(new FileReader(BILDBOARD_FILE_NAME));

		String line = br.readLine();
		line = br.readLine();
		while(line != null) {
			String parts[] = line.split(separatorCharacter);

			double width = Double.parseDouble(parts[0]);
			double height = Double.parseDouble(parts[1]);
			boolean inUse = Boolean.parseBoolean(parts[2]);

			list.add(new Billboard(width, height, inUse, parts[3]));
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
		answer+="TOTAL: "+ list.size() + " Billboards";
		return answer;
	}
	
	

}