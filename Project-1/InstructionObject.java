public class InstructionObject {

	private String i;
	private String s;
	private String o;
	private int v;

	public InstructionObject(String line) {
		String[] l = line.split(" ");
		if ((l.length > 4) || (l.length < 3) 
				|| ((l.length == 3) && (!(l[0].equals("read"))))
				|| ((l.length == 4) && (!(l[0].equals("write")))) 
				|| (!SecureSystem.getSubjectManager().containsKey(l[1]))
				|| (!ObjectManager.getObjectManager().containsKey(l[2]))
				|| (l[0].equals("write") && !isInt(l[3])))
			this.i = "BAD";
		else {
			switch (l[0]) {
				case "read":
					this.i = "READ";
					this.s = l[1];
					this.o = l[2];
					ReferenceMonitor.readExecute(getInstructionObject());
					break;
				case "write":
					this.i = "WRITE";
					this.s = l[1];
					this.o = l[2];
					this.v = Integer.parseInt(l[3]);
					ReferenceMonitor.writeExecute(getInstructionObject());
					break;
				default:
					break;
			}
		}
	}

	public InstructionObject getInstructionObject() {
		return this;
	}

	public String getInstruction() {
		return this.i;
	}

	public String getSubject() {
		return this.s;
	}

	public String getObject() {
		return this.o;
	}

	public int getValue() {
		return this.v;
	}

	// if is an int
	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
