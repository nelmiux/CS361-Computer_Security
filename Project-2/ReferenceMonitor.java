import java.util.HashMap;

public class ReferenceMonitor {

	static HashMap<String, String> runManager = new HashMap<String, String>();
	static String line;

	public ReferenceMonitor() {
		ObjectManager.getReadManager().put("HAL", 0);
		ObjectManager.getReadManager().put("LYLE", 0);
		runManager.put("HAL", "temp");
		runManager.put("LYLE", "temp");
	}

	static void createNewObject(String name, SecurityLevel sl) {
		ObjectManager.createNewObject(name, sl);
	}

	public static HashMap<String, String> getRunManager() {
		return runManager;
	}

	// return a byte in string way
	public static String getResultLine() {
		return line;
	}

	// see if the write instruction is safe
	static void writeExecute(InstructionObject i) {
		String s = i.getSubject();
		String o = i.getObject();

		SecurityLevel ss = SecureSystem.getSubjectManager().get(s);
		int ssl = ss.getDominates();
		SecurityLevel os = ObjectManager.getObjectManager().get(o);
		int osl = os.getDominates();
		if (ssl <= osl) ObjectManager.writeExecute(i);
	}

	static void readExecute(InstructionObject i) {
		String s = i.getSubject();
		String o = i.getObject();

		SecurityLevel ss = SecureSystem.getSubjectManager().get(s);
		int ssl = ss.getDominates();
		SecurityLevel os = ObjectManager.getObjectManager().get(o);
		int osl = os.getDominates();
		if (ssl >= osl) ObjectManager.readExecute(i);
		else ObjectManager.badReadExecute(i);
	}

	static void createExecute(InstructionObject i) {
		String s = i.getSubject();
		SecurityLevel ss = SecureSystem.getSubjectManager().get(s);
		createNewObject(i.getObject(), ss);
	}

	// run call
	static void runExecute(InstructionObject i) {
		int temp = ObjectManager.getReadManager().get(i.getSubject());
		String cBit = runManager.get(i.getSubject());
		// first bit
		if (cBit.equals("temp")) {
			if (temp != 0) {
				cBit = "1";
				runManager.put(i.getSubject(), cBit);
			} else {
				cBit = "0";
				runManager.put(i.getSubject(), cBit);
			}
		}
		// less than 8 bits
		else if (cBit.length() < 8) {
			if (temp != 0) {
				cBit = cBit.concat("1");
				runManager.put(i.getSubject(), cBit);
			} else {
				cBit = cBit.concat("0");
				runManager.put(i.getSubject(), cBit);
			}
		}
		// 8 bits, make the string
		if (cBit.length() == 8) {
			line = cBit;
			int ch = Integer.parseInt(line, 2);
			line = new Character((char) ch).toString();
		}

	}

	// destroy call
	static void destroyExecute(InstructionObject i) {
		String s = i.getSubject();
		String o = i.getObject();
		SecurityLevel ss = SecureSystem.getSubjectManager().get(s);
		int ssl = ss.getDominates();
		SecurityLevel os = ObjectManager.getObjectManager().get(o);
		int osl = os.getDominates();
		if (ssl <= osl) ObjectManager.destroyExecute(i);
		else System.out.println("This destroy call is invalid and did not occur!");
	}

}
