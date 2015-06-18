package pigiadibooks.model;

public class BookSearchModel implements DataModel {
	private String key;
	private int count;
	
	
	public BookSearchModel() {
		super();
		this.key = "";
		this.count = 0;
	}
	public BookSearchModel(String key, int count) {
		super();
		this.key = key;
		this.count = count;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "BookSearchModel [key=" + key + ", count=" + count + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookSearchModel other = (BookSearchModel) obj;
		if (count != other.count)
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
	
	
}
