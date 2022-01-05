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

			// Llegir la capcalera
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

	public String at(int id, String label) {
		return this.data.at(id, label);
	}

	public String iat(int i, int j) {
		return this.data.iat(i,j);
	}

	public int columns() {
		return this.data.columns();
	}

	public int size() {
		return this.data.size();
	}

	public ArrayList<String> sort(String label, Comparator<Object> c) {
		return this.data.sort(label,c);
	}

	public Data query(String label, Predicate<String> func) {
		return this.data.query(label, func);
	}

	public Double max(String label) {
		return this.data.max(label);
	}

	public Double min(String label) {
		return this.data.min(label);
	}

	public Double average(String label) {
		return this.data.average(label);
	}

	public Double sum(String label) {
		return this.data.sum(label);
	}

	public LinkedList<ArrayList<String>> getContent() {
		return this.data.getContent();
	}

	public LinkedList<String> getLabelList() {
		return this.data.getLabelList();
	}

	public ArrayList<String> getColumn(String label) {
		return this.data.getColumn(label);
	}

	public void accept(Visitor v) {}

	public Iterator<ArrayList<String>> iterator() {
		return this.data.iterator();
	}

	public String toString() {
		return this.data.toString();
	}

}
