import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class SecureSystem {
	
	static HashMap <String, SecurityLevel> subjectManager = new HashMap <String, SecurityLevel>();;
	static InstructionObject iobj;
	ReferenceMonitor rm = new ReferenceMonitor();

    static void passInstructions(String[] instructions) {
		for (int i = 0; i < instructions.length; i++) {
			iobj = new InstructionObject(instructions[i]);
		}
    }

	// SecureSystem constructor
	public SecureSystem(String fname) throws FileNotFoundException {
	}

	// Create subject
	void createSubject(String name, SecurityLevel sl) {
		subjectManager.put(name, sl);
	}

	// To get subject manager
	public static HashMap <String, SecurityLevel> getSubjectManager() {
		return subjectManager;
	}

	// To get reference monitor
	public ReferenceMonitor getReferenceMonitor() {
		return rm;
	}
}
