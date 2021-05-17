import java.io.*; 
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	private FileWriter fw;
	SimpleDateFormat date;
	SimpleDateFormat lineDate;
	public Logger() {
		date = new SimpleDateFormat("ddMMyy-hhmmss");
		lineDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		createFileWriter();
	}
	
	public void createFileWriter() {
		try {
	         File file = new File("logs\\" + date.format(new Date()) + "-log.txt");
	         file.getParentFile().mkdirs();
	         fw = new FileWriter(file);
		 }catch(IOException ex){
	    	 System.out.println(ex + "Directorio no encontrado");
	     }
	}
	
	public void writeFile(String line) {
		try {
			fw.write(lineDate.format(new Date()) + " " + line + "\n");
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeWirter() {
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}