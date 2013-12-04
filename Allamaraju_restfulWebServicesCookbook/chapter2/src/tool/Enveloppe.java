package tool;

import java.util.List;

public class Enveloppe<T> {
	private List<T> list;
	public Enveloppe(List<T> list){
		this.list = list;
	}
	public List<T> getList(){
		return list;
	}
}
