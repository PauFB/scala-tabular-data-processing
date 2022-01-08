package factory;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

import org.json.simple.parser.*;
import visitor.Visitor;

public class JSONData implements DataFrame {
	
	Data data;

	public JSONData(String file) {
        try {
			LinkedList<String> labelList = new LinkedList<>();
			LinkedList<ArrayList<String>> content = new LinkedList<>();

			JSONParser parser = new JSONParser();

			ContainerFactory orderedKeyFactory = new ContainerFactory() {
				public List<String> creatArrayContainer() {
					return new LinkedList<>();
				}

				public Map<String, String> createObjectContainer() {
					return new LinkedHashMap<>();
				}
			};

        	LinkedList<HashMap<String, String>> array = (LinkedList<HashMap<String, String>>) parser.parse(new FileReader(file), orderedKeyFactory);

			HashMap<String, String> jsonObject = array.get(0);
            for (int i = 0; i < jsonObject.keySet().size(); i++) {
				labelList.add((String)jsonObject.keySet().toArray()[i]);
				content.add(new ArrayList<>());
            }

            for (HashMap<String, String> obj : array) {
            	 jsonObject = obj;
            	 for (int j = 0; j < jsonObject.size(); j++) {
					 content.get(j).add(String.valueOf(jsonObject.get(labelList.get(j))));
				 }
            }

			data = new Data(labelList,content);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
	}

	public String at(int id, String label) {
		return data.at(id, label);
	}

	public String iat(int i, int j) {
		return data.iat(i, j);
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
		return data.query(label,func);
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
