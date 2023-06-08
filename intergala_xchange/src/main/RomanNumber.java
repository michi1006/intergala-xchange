package main;

public enum RomanNumber {
	
	M(1000, true), CM (900, false), D(500, false), CD(400, false),
	C(100, true), XC(90, false), L(50, false), XL(40, false),
	X(10, true), IX(9, false), V(5, false), IV(4, false),
	I(1, true);
	
	private int value;
	private boolean repeatable;
	
	private RomanNumber(int value, boolean repeatable) {
		this.value = value;
		this.repeatable = repeatable;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isRepeatable() {
		return repeatable;
	}
}
