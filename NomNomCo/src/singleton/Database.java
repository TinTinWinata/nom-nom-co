package singleton;

import java.util.Vector;

import model.Confectionary;

public class Database {

	private static Database db = null;
	private Vector<Confectionary> confectionaries;
	
	private Database() {
		confectionaries = new Vector<Confectionary>();
	}
	
	public Vector<Confectionary> getConfectionary() {
		return confectionaries;
	}
	
	public void pushConfectionary(Confectionary confectionary) {
		confectionaries.add(confectionary);
	}
	
	public static Database getInstance() {
		if(db == null) {
			db = new Database();
		}
		return db;
	}
}
