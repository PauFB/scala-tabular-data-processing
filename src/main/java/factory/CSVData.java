package factory;

import visitor.Visitor;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class CSVData implements DataFrame {

	Data data;

	public CSVData(String fileName) {
		try {
			LinkedList<String> labelList = new LinkedList<>();
			LinkedList<ArrayList<String>> content = new LinkedList<>();

			BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

			String row = fileReader.readLine();
			StringTokenizer st = new StringTokenizer(row, ",");

			// Llegir la capçalera
			while (st.hasMoreTokens()) {
				content.add(new ArrayList<>());		//Per cada nova entrada crear una nova columna
				labelList.add(st.nextToken()); 		// i afegir a LabelList
			}

			// Llegir el contingut
			row = fileReader.readLine();
			while (row != null) {
				int i = 0;
				st = new StringTokenizer(row,",");

				while (st.hasMoreTokens()) {
					content.get(i).add(st.nextToken());		//Per cada nova entrada posar l'element
					i++;
				}				
				row = fileReader.readLine();
			}

			data = new Data(labelList,content);

			fileReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("El fitxer d'entrada no existeix");
		} catch (IOException e) {
			System.out.println("Excepcio d'E/S: " + e);
		}
	}

	public CSVData(Data data) {
		this.data = data;
	}

	public String at(int id, String label) {
		return data.at(id, label);
	}

	public String iat(int i, int j) {
		return data.iat(i,j);
	}

	public int columns() {
		return data.columns();
	}

	public int size() {
		return data.size();
	}

	public ArrayList<String> sort(String label, Comparator<Object> c) {
		return data.sort(label,c);
	}

	public DataFrame query(String label, Predicate<String> func) {
		if (data.query(label,func) != null){
			return new CSVData(data.query(label,func));
		}
		return null;
	}

	public Double max(String label) {
		return data.max(label);
	}

	public Double min(String label) {
		return data.min(label);
	}

	public Double average(String label) {
		return data.average(label);
	}

	public Double sum(String label) {
		return data.sum(label);
	}

	public LinkedList<ArrayList<String>> getContent() {
		return data.getContent();
	}

	public ArrayList<String> getColumn(String label) {
		return data.getColumn(label);
	}

	public void accept(Visitor v, String label) {}

	public Iterator<ArrayList<String>> iterator() {
		return data.iterator();
	}

	@Override
	public String toString() {
		return data.toString();
	}
}
