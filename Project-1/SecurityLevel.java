public class SecurityLevel {

	static SecurityLevel HIGH = new SecurityLevel(1);
	static SecurityLevel LOW = new SecurityLevel(0);
	int dominates;

	public SecurityLevel(int level) {
		dominates = level;
	}

	int getDominates() {
		return dominates;
	}
}
