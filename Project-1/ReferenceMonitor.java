public class ReferenceMonitor {

	public ReferenceMonitor() {
		// initially 0
		ObjectManager.getReadManager().put("Hal", 0);
		ObjectManager.getReadManager().put("Lyle", 0);
	}

	// Create an object
	void createNewObject(String name, SecurityLevel sl) {
		ObjectManager.createNewObject(name, sl);
	}

	// if the write instruction is allowed
	static void writeExecute(InstructionObject i) {
		String s = i.getSubject();
		String o = i.getObject();

		SecurityLevel ss = SecureSystem.getSubjectManager().get(s);
		int ssl = ss.getDominates();
		SecurityLevel os = ObjectManager.getObjectManager().get(o);
		int osl = os.getDominates();
		if (osl >= ssl) ObjectManager.writeExecute(i);
	}

	// if read is allowed
	static void readExecute(InstructionObject i) {
		String s = i.getSubject();
		String o = i.getObject();
		
		SecurityLevel ss = SecureSystem.getSubjectManager().get(s);
		int ssl = ss.getDominates();
		SecurityLevel os = ObjectManager.getObjectManager().get(o);
		int osl = os.getDominates();
		if (osl <= ssl) ObjectManager.readExecute(i);
		else ObjectManager.notReadExecute(i);
	}
}
