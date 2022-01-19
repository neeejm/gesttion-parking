package entities;

public class Section {
	private int id;
	private String code;

	public Section(int id, String code) {
		this.id = id;
		this.code = code;
	}

	public Section(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}
}
