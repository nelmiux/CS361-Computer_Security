public class InstructionObject {

	private String i;
	private String s;
	private String o;
	private int val;

	public InstructionObject(String instr) {
		String[] l = instr.split(" ");
		l[0] = l[0].toLowerCase();
		if (l[0].equals("run")) {
			this.i = "RUN";
			this.s = l[1];
			ReferenceMonitor.runExecute(getInstructionObject());
		} else if ((l.length != 4 && l[0].equals("write"))
				|| (l.length != 3 && l[0].equals("read"))
				|| (l.length != 3 && l[0].equals("create"))
				|| (l.length != 3 && l[0].equals("destroy"))
				|| (l.length != 2 && l[0].equals("run"))) {
			this.i = "BAD";
			// if the passed subject is in the subject manager
		} else if (!SecureSystem.getSubjectManager().containsKey(l[1])) {
			this.i = "BAD";
			// if the passed object is in the object manager
		} else if (!ObjectManager.getObjectManager().containsKey(l[2])
				&& l[0].equals("create") == false) {
			this.i = "BAD";
		} else if (l[0].equals("destroy")
				&& !ObjectManager.getObjectManager().containsKey(l[2])) {
			this.i = "BAD";
		} else if (l[0].equals("create")
				&& ObjectManager.getObjectManager().containsKey(l[2])) {
			this.i = "BAD";
	    // Otherwise, is safe
		} else {
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
					this.val = Integer.parseInt(l[3]);
					ReferenceMonitor.writeExecute(getInstructionObject());
					break;
                case "create":
					this.i = "CREATE";
					this.s = l[1];
					this.o = l[2];
					ReferenceMonitor.createExecute(getInstructionObject());
					break;
                case "destroy":
					this.i = "DESTROY";
					this.s = l[1];
					this.o = l[2];
					ReferenceMonitor.destroyExecute(getInstructionObject());
					break;
				default:
					break;
			}
        }
	}

	public InstructionObject getInstructionObject() { return this; }

	public String getInstruction() { return this.i; }

	public String getSubject() { return this.s; }

	public String getObject() { return this.o; }

	public int getValue() { return this.val; }

	public static boolean isInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
