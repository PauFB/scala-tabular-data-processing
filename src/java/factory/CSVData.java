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

			//Read header
			while (st.hasMoreTokens()) {
				content.add(new ArrayList<>());		//For every new entrance create a new column
				labelList.add(st.nextToken()); 		//and add the new label to labelList
			}

			//Read content
			row = fileReader.readLine();
			while (row != null) {
				int i = 0;
				st = new StringTokenizer(row,",");

				while (st.hasMoreTokens()) {
					content.get(i).add(st.nextToken());		//For every new entrance add the element to content
					i++;
				}				
				row = fileReader.readLine();
			}

			data = new Data(labelList,content);

			fileReader.close();

		} catch (FileNotFoundException e) {
			System.out.println("File doesn't exist");
		} catch (IOException e) {
			System.out.println("I/O Exception: " + e);
		}
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

	public ArrayList<String> sort(String label, Comparator<String> c) {
		return data.sort(label,c);
	}

	public Data query(String label, Predicate<String> func) {
		return data.query(label, func);
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

	public LinkedList<String> getLabelList() {
		return data.getLabelList();
	}

	public ArrayList<String> getColumn(String label) {
		return data.getColumn(label);
	}

	public void accept(Visitor v) {}

	public Iterator<ArrayList<String>> iterator() {
		return data.iterator();
	}

	@Override
	public String toString() {
		return data.toString();
	}

}
