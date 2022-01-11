package edu.uncc.utility;

public class Literal {

	private String literalName;

	public Literal() {
		super();
	}

	public Literal(String literalName) {
		super();
		this.literalName = literalName;
	}

	public String getLiteralName() {
		return literalName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((literalName == null) ? 0 : literalName.hashCode());
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
		Literal other = (Literal) obj;
		if (literalName == null) {
			if (other.literalName != null)
				return false;
		} else if (!literalName.equals(other.literalName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Literal [literalName=" + literalName + "]";
	}
	
}