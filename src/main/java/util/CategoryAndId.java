package util;

public class CategoryAndId {
	private Integer categoryId;
	private String category;
	
	public CategoryAndId(Integer categoryId, String category) {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
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
		CategoryAndId other = (CategoryAndId) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return category +" "+ categoryId;
	}
}
