import java.util.HashMap;

public class ObjectManager {

	static HashMap <String, SecurityLevel> objectManager = new HashMap <String, SecurityLevel>();
	static HashMap <String, Integer> value = new HashMap <String, Integer>();
	static HashMap <String, Integer> readManager = new HashMap <String, Integer>();

	public static HashMap <String, SecurityLevel> getObjectManager() {
		return objectManager;
	}

	public static HashMap <String, Integer> getObjectValue() {
		return value;
	}

	public static HashMap <String, Integer> getReadManager() {
		return readManager;
	}

	// Creates a new object by placing it in the object manager
	static void createNewObject(String name, SecurityLevel sl) {
		objectManager.put(name, sl);
		value.put(name, 0);
	}

	static void writeExecute(InstructionObject i) {
		int v = i.getValue();
		String o = i.getObject();
		value.put(o, v);
	}

	static void readExecute(InstructionObject i) {
		String s = i.getSubject();
		String o = i.getObject();
		readManager.put(s, value.get(o));
	}

	// If the command was a illegal read return 0
	static void notReadExecute(InstructionObject i) {
		String s = i.getSubject();
		readManager.put(s, 0);
	}
}
