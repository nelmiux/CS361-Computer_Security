import java.io.*;
import java.util.*;

public class CovertChannel {

	static long nbits = 0;
	static boolean vb;

	public static void main(String[] args) throws IOException {
		File f;
		if (args[0].equals("v")) {
			System.out.println("Verbose is ON");
			f = new File(args[1]);
			vb = true;
		} else {
			f = new File(args[0]);
			vb = false;
		}

		SecureSystem sys = new SecureSystem(args[0]);
		SecurityLevel low = SecurityLevel.LOW;
		SecurityLevel high = SecurityLevel.HIGH;
		sys.createSubject("LYLE", low);
		sys.createSubject("HAL", high);

		Scanner sc = new Scanner(f);
		String fOutName = f.getName() + ".out";
		String fLogName = "log.txt";
		byte[] nl = System.getProperty("line.separator").getBytes();
		FileOutputStream fOut = new FileOutputStream(fOutName);
		FileOutputStream fLog = new FileOutputStream(fLogName);
		final long st = System.currentTimeMillis();

		while (sc.hasNextLine()) {
			String cl = sc.nextLine();
			byte[] bf = cl.getBytes();
			ByteArrayInputStream in = new ByteArrayInputStream(bf);
			int bytesNum = in.available();

			// While there are still bytes in the file to be parsed
			while (bytesNum > 0) {
				int inBytes = in.read();
				String inBits = Integer.toBinaryString(inBytes);
				int inSize = inBits.length();
				nbits += 8;

				// make all bytes length 8
				if (inSize != 8) {
					while (inSize != 8) {
						String z = "0";
						inBits = z.concat(inBits);
						inSize = inBits.length();
					}
				}
				// if bit is 0 or 1, run different instruction
				for (int i = 0; i < inSize; i++) {
					if (inBits.charAt(i) == '0') {
						String[] instr = { "CREATE HAL OBJ", "CREATE LYLE OBJ",
								"WRITE LYLE OBJ 1", "READ LYLE OBJ",
								"DESTROY LYLE OBJ", "RUN LYLE" };
						// If vb flag is on, write to log
						if (vb) {
							String log = Arrays.toString(instr);
							byte[] logResult = log.getBytes();
							fLog.write(logResult);
							fLog.write(nl);
						}
						SecureSystem.passInstructions(instr);
					} else {
						String[] instr = { "CREATE LYLE OBJ",
								"WRITE LYLE OBJ 1", "READ LYLE OBJ",
								"DESTROY LYLE OBJ", "RUN LYLE" };
						// If vb flag is on, write to log
						if (vb) {
							String log = Arrays.toString(instr);
							byte[] logResult = log.getBytes();
							fLog.write(logResult);
							fLog.write(nl);
						}
						SecureSystem.passInstructions(instr);
					}
				}
				// write to .out file
				bytesNum--;
				String result = ReferenceMonitor.getResultLine();
				byte[] resultArray = result.getBytes();
				fOut.write(resultArray);
				ReferenceMonitor.getRunManager().put("LYLE", "temp");
			}
			fOut.write(nl);
		}
		final long et = System.currentTimeMillis();

		// bandwidth size
		double bytes = f.length();
		long bandwidth = nbits / (et - st);
		System.out.println("Document: " + f.getName());
		System.out.println("Size: " + bytes + " bytes");
		System.out.println("Bandwidth: " + bandwidth + " bits/ms");
		sc.close();
		fOut.close();
		fLog.close();
	}

}
