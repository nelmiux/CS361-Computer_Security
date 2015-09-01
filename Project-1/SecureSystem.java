import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class SecureSystem {
	
	static HashMap <String, SecurityLevel> subjectManager = new HashMap <String, SecurityLevel>();;
	static InstructionObject iobj;
	ReferenceMonitor rm = new ReferenceMonitor();
	
	public static void main(String[] args) throws FileNotFoundException {

		if(args.length == 0) args[0] = "";
		
		// Create the SecureSystem
		SecureSystem sys = new SecureSystem(args[0]);
		
		// LOW and HIGH are constants defined in the SecurityLevel 
        // class, such that HIGH dominates LOW.
		SecurityLevel low  = SecurityLevel.LOW;
		SecurityLevel high = SecurityLevel.HIGH;

		// We add two subjects, one high and one low.
		sys.createSubject("Lyle", low);
		sys.createSubject("Hal", high);

		// We add two objects, one high and one low.
		sys.getReferenceMonitor().createNewObject("LObj", low);
		sys.getReferenceMonitor().createNewObject("HObj", high);
		
		// Input file
		File f = new File(args[0]);
		// Read successive instructions from the input file and execute them
		Scanner s = new Scanner(f);
		while (s.hasNextLine()) {
			String line = s.nextLine();
			iobj = new InstructionObject(line);
			printState();
		}
		s.close();
	}

	// SecureSystem constructor
	public SecureSystem(String fname) throws FileNotFoundException {
		File file = new File(fname);
		System.out.println("Reading from file: " + file);
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

	static void printState() {
		System.out.println();
		
		switch (iobj.getInstruction()) {
			case "BAD":
				System.out.println("Bad Instruction");
				break;
			case "READ":
				System.out.println(iobj.getSubject().toLowerCase() + " reads " + iobj.getObject().toLowerCase());
				break;
			case "WRITE":
				System.out.println(iobj.getSubject().toLowerCase() + " writes value " 
						+ iobj.getValue() + " to " + iobj.getObject().toLowerCase());
				break;
			default:
				break;
		}
		
		System.out.println("The current state is:");
		System.out.println("   LObj has value: " + ObjectManager.getObjectValue().get("LObj"));
		System.out.println("   HObj has value: " + ObjectManager.getObjectValue().get("HObj"));
		System.out.println("   Lyle has recently read: " + ObjectManager.getReadManager().get("Lyle"));
		System.out.println("   Hal has recently read: " + ObjectManager.getReadManager().get("Hal"));
	}
}
