package database;

public class CategoryAndCode {
	private Integer categoryId;
	private String category;
	
	public CategoryAndCode(Integer categoryId, String category) {
		this.categoryId = categoryId;
		this.category = category;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public String getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return category;
	}
}
