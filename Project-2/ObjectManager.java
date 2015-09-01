import java.util.HashMap;

public class ObjectManager {

	static HashMap<String, SecurityLevel> objectManager = new HashMap<String, SecurityLevel>();
	static HashMap<String, Integer> value = new HashMap<String, Integer>();
	static HashMap<String, Integer> readManager = new HashMap<String, Integer>();

	public static HashMap<String, SecurityLevel> getObjectManager() { return objectManager; }

	public static HashMap<String, Integer> getValueManager() { return value; }

	public static HashMap<String, Integer> getReadManager() { return readManager; }

	static void createNewObject(String name, SecurityLevel sl) {
		objectManager.put(name, sl);
		value.put(name, 0);
	}

	static void writeExecute(InstructionObject i) {
		int val = i.getValue();
		String o = i.getObject();
		value.put(o, val);
	}
	
	static void destroyExecute(InstructionObject i) {
		String o = i.getObject();
		objectManager.remove(o);
	}

	static void readExecute(InstructionObject i) {
		String s = i.getSubject();
		String o = i.getObject();
		readManager.put(s, value.get(o));

	}

	static void badReadExecute(InstructionObject i) {
		String s = i.getSubject();
		readManager.put(s, 0);

	}
}
