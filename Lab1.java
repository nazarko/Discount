import java.io.*;
import java.util.*;
class Vigenere{
	private RandomAccessFile in;
	private RandomAccessFile out;
	private String key;
	private Set s1 = new HashSet();
	private List s2  = new ArrayList();
	public void setInputFile(String inputFile){
		try {
			in = new RandomAccessFile(inputFile,"rw");
		} catch(FileNotFoundException e){
			System.out.println("Input file not found");
		}
	}
	public void setOutputFile(String outputFile){
		try {
			out = new RandomAccessFile(outputFile,"rw");
		} catch(FileNotFoundException e){
			System.out.println("Output file not found");
		}
	}
	public void setKey(String s){
		key = s;
	}
	public void encrypt()throws IOException{
		int currentSymbol;
		int currentSymbolOfKey = 0;
		while ((currentSymbol = in.read()) != -1){
			s1.add(currentSymbol);
		}
		Iterator iter = s1.iterator();
		while(iter.hasNext()){
			s2.add(iter.next());
		}
		in.seek(0);
		while ((currentSymbol = in.read()) != -1){
			if(currentSymbolOfKey > key.length() - 1) 
				currentSymbolOfKey = 0;
			out.write((s2.indexOf(currentSymbol) + s2.indexOf(key.charAt(currentSymbolOfKey))) % s2.size());
		}
		out.close();
	}
	public void decrypt() throws IOException{
		int currentSymbol;
		int currentSymbolOfKey = 0;
		StringBuffer key2 = new StringBuffer();
		while ((currentSymbol = in.read()) != -1){
			s1.add(currentSymbol);
		}		
		Iterator iter = s1.iterator();
		while(iter.hasNext()){
			s2.add(iter.next());
		}
		Iterator iter2 = s2.iterator();
		while(iter2.hasNext()){
			System.out.println("1st - "+(iter2.next()));
		}
		in.seek(0);
		for (int i = 0; i < key.length(); i++){
			key2.append((s2.size() - s2.indexOf(key.charAt(i))) % s2.size());
		}
		while ((currentSymbol = in.read()) != -1){
			if(currentSymbolOfKey > key.length() - 1) 
				currentSymbolOfKey = 0;
			out.write((s2.indexOf(currentSymbol) + s2.indexOf(key2.charAt(currentSymbolOfKey))) % s2.size());
		}
		out.close();
		
	}
}
public class Lab1{
	public static void main(String[] args) throws IOException{
		Vigenere test = new Vigenere();
		test.setInputFile("out.txt");
		test.setOutputFile("out2.txt");
		test.setKey("Kacharaba");
		//switch(args[0]){
		//	case "e":
		//	test.encrypt();
	//		break;
	//		case "d":
			test.decrypt();
	//	}
		
	}
}