import java.io.*;
import java.util.*;
import java.lang.*;

class AES{
    static final int numberRounds = 14;
    static final int numberWords = 8;
    static byte[][] normal = new byte[16][16], inverse = new byte[16][16];
    static byte[][] normalMDS = new byte[][]
                                        {
                                            {2, 3, 1, 1}, 
                                            {1, 2, 3, 1}, 
                                            {1, 1, 2, 3}, 
                                            {3, 1, 1, 2}
                                        };
    static byte[][] inverseMDS = new byte[][]
                                        {
                                            {14, 11, 13,  9}, 
                                            { 9, 14, 11, 13}, 
                                            {13,  9, 14, 11}, 
                                            {11, 13,  9, 14}
                                        };
                                        
    //MDS Tables
    static{
        int[] normalArray = new int []
        {
           0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76,
           0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0,
           0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15,
           0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75,
           0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84,
           0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF,
           0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8,
           0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2,
           0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73,
           0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB,
           0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79,
           0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08,
           0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A,
           0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E,
           0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF,
           0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16
        };
        int[] inverseArray = new int []
        {
           0x52, 0x09, 0x6A, 0xD5, 0x30, 0x36, 0xA5, 0x38, 0xBF, 0x40, 0xA3, 0x9E, 0x81, 0xF3, 0xD7, 0xFB,
           0x7C, 0xE3, 0x39, 0x82, 0x9B, 0x2F, 0xFF, 0x87, 0x34, 0x8E, 0x43, 0x44, 0xC4, 0xDE, 0xE9, 0xCB,
           0x54, 0x7B, 0x94, 0x32, 0xA6, 0xC2, 0x23, 0x3D, 0xEE, 0x4C, 0x95, 0x0B, 0x42, 0xFA, 0xC3, 0x4E,
           0x08, 0x2E, 0xA1, 0x66, 0x28, 0xD9, 0x24, 0xB2, 0x76, 0x5B, 0xA2, 0x49, 0x6D, 0x8B, 0xD1, 0x25,
           0x72, 0xF8, 0xF6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xD4, 0xA4, 0x5C, 0xCC, 0x5D, 0x65, 0xB6, 0x92,
           0x6C, 0x70, 0x48, 0x50, 0xFD, 0xED, 0xB9, 0xDA, 0x5E, 0x15, 0x46, 0x57, 0xA7, 0x8D, 0x9D, 0x84,
           0x90, 0xD8, 0xAB, 0x00, 0x8C, 0xBC, 0xD3, 0x0A, 0xF7, 0xE4, 0x58, 0x05, 0xB8, 0xB3, 0x45, 0x06,
           0xD0, 0x2C, 0x1E, 0x8F, 0xCA, 0x3F, 0x0F, 0x02, 0xC1, 0xAF, 0xBD, 0x03, 0x01, 0x13, 0x8A, 0x6B,
           0x3A, 0x91, 0x11, 0x41, 0x4F, 0x67, 0xDC, 0xEA, 0x97, 0xF2, 0xCF, 0xCE, 0xF0, 0xB4, 0xE6, 0x73,
           0x96, 0xAC, 0x74, 0x22, 0xE7, 0xAD, 0x35, 0x85, 0xE2, 0xF9, 0x37, 0xE8, 0x1C, 0x75, 0xDF, 0x6E,
           0x47, 0xF1, 0x1A, 0x71, 0x1D, 0x29, 0xC5, 0x89, 0x6F, 0xB7, 0x62, 0x0E, 0xAA, 0x18, 0xBE, 0x1B,
           0xFC, 0x56, 0x3E, 0x4B, 0xC6, 0xD2, 0x79, 0x20, 0x9A, 0xDB, 0xC0, 0xFE, 0x78, 0xCD, 0x5A, 0xF4,
           0x1F, 0xDD, 0xA8, 0x33, 0x88, 0x07, 0xC7, 0x31, 0xB1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xEC, 0x5F,
           0x60, 0x51, 0x7F, 0xA9, 0x19, 0xB5, 0x4A, 0x0D, 0x2D, 0xE5, 0x7A, 0x9F, 0x93, 0xC9, 0x9C, 0xEF,
           0xA0, 0xE0, 0x3B, 0x4D, 0xAE, 0x2A, 0xF5, 0xB0, 0xC8, 0xEB, 0xBB, 0x3C, 0x83, 0x53, 0x99, 0x61,
           0x17, 0x2B, 0x04, 0x7E, 0xBA, 0x77, 0xD6, 0x26, 0xE1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0C, 0x7D
        };
        
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                normal[i][j] = (byte)normalArray[i*16 + j];
                inverse[i][j] = (byte)inverseArray[i*16 + j];
            }
        }
    }
    
    byte[][] encrypt(byte[][] opt, byte[][][] rk){

        // initial addRoundkey
        opt = addRoundkey(opt, rk[0]);
        System.out.printf("After addRoundKey (0):\n");
        printSteps(opt);
        
        for (int i = 1; i < numberRounds + 1; ++i) 
        {
            // subBytes
            opt = subBytes(opt);
            System.out.println("After subBytes:");
            printSteps(opt);
            
            // shiftRows
            opt = shiftRows(opt);
            System.out.println("After shiftRows:");
            printSteps(opt);
            
            // mixColumns
            if(i != numberRounds)
            {
                opt = mixColumns(opt);
                System.out.println("After mixColumns:");
                printSteps(opt); 
            }

            opt = addRoundkey(opt, rk[i]);
            System.out.printf("After addRoundKey (%d):\n", i);
            printSteps(opt);
        }
        System.out.println("The ciphertext:");
        printPlainCipher(opt);
        return opt;
    }
    
    byte[][] decrypt(byte[][] opt, byte[][][] rk){

        // initial addRoundkey
        opt = addRoundkey(opt, rk[numberRounds]);
        System.out.println("\nAfter addRoundKey (14):");
        printSteps(opt);
        
        // 14 cycles for 256-bit key
        for (int i = numberRounds-1; i >= 0; --i) 
        {
            // invShiftRows
            opt = invShiftRows(opt);
            System.out.println("After invShiftRows:");
            printSteps(opt);
            
            // invSubBytes
            opt = invSubBytes(opt);
            System.out.println("After invSubBytes:");
            printSteps(opt);
            
            // addRoundkey
            opt = addRoundkey(opt, rk[i]);
            System.out.printf("After addRoundKey (%d):\n", i);
            printSteps(opt);
            
            // invMixColumns
            if(i != 0)
            {
                opt = invMixColumns(opt);
                System.out.println("After invMixColumns:");
                printSteps(opt); 
            }
        }
        System.out.println("The decryption of the ciphertext:");
        printPlainCipher(opt);
        System.out.println("The decryption of the ciphertext:");
        printSteps(opt);
        System.out.println();       
        return opt;
    }
    
    byte[][] addRoundkey(byte[][] M, byte[][] rk){
        int m = M.length;
        int n = M[0].length;
        for (int j = 0; j < n; ++j) for (int i = 0; i < m; ++i) M[i][j] ^= rk[i][j];
        return M;
    }
    
    byte[][] subBytes(byte[][] M){ return subBytesHelper(M, normal); }
    
    byte[][] shiftRows(byte[][] M){ return shiftRowsHelper(M, 0); }
    
    byte[][] mixColumns(byte[][] M){ return mixColumnsHelper(M, normalMDS); }
    
    byte[][] invSubBytes(byte[][] M){ return subBytesHelper(M, inverse); }
    
    byte[][] invShiftRows(byte[][] M){ return shiftRowsHelper(M, 1); }
    
    byte[][] invMixColumns(byte[][] M){ return mixColumnsHelper(M, inverseMDS); }
    
    byte[][] shiftRowsHelper(byte[][] M, int x) {
        final int m = M.length;
        final int n = M[0].length;
        byte[][] N = new byte[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int k = (j + i + x * (4 - 2 * i)) % 4;
                N[i][j] = M[i][k];
            }
        }
        return M = N;
    }
    
    byte[][] subBytesHelper(byte[][] M, byte[][] t) {
        final int m = M.length;
        final int n = M[0].length;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                M[i][j] = keyExpansionHelper4(M[i][j], t);
            }
        }
        return M;
    }
    
    byte[][] mixColumnsHelper(byte[][] M, byte[][] t) {
        final int n = M[0].length;      
        for (int j = 0; j < n; ++j) {
            M = mixCol(M, j, t);
        }
        return M;
    }

    byte[][] mixCol(byte[][] M, int k, byte[][] t){
        final int m = M.length;
        byte[] w = new byte[4];      
        for (int i = 0; i < m; ++i) for (int j = 0; j < 4; ++j) w[i] ^= mul(M[j][k], t[i][j]);
        return M = toCol(M, w, k);
    }
    
    void toOut(byte[][] M, PrintWriter printW) throws Exception {
        int m = M.length;
        int n = M[0].length;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                String str = Integer.toHexString(M[i][j]).toUpperCase();
                if(str.length() < 2) str = "0" + str;
                if(str.length() > 2) str = str.substring(str.length()-2);
                printW.print(str);
            }
        }
        printW.println();
    }

    void printSteps(byte[][] M) {
        final int m = M.length;
        final int n = M[0].length;
        for (int i = 0; i < n; ++i) for (int j = 0; j < m; ++j) System.out.printf("%02X", M[j][i]);
        System.out.println();
    }

    void printPlainCipher(byte[][] M) {
        final int m = M.length;
        final int n = M[0].length;
        for (int k = 0; k < n / 4; ++k) {
            for (int i = 0; i < m; ++i) {
                if ((k!= 0) && (k != n / 4 - 1)) System.out.printf("  ");
                for (int j = 4 * k; j < 4*k + 4; ++j) System.out.printf("%02X ", M[i][j]);
            if (k == n / 4 - 1) System.out.println();
            }
        }
        System.out.println();
    }

    void printCipherMatrix(byte[][] M) {
        final int m = M.length;
        final int n = M[0].length;
        for (int k = 0; k < n / 4 - 1; ++k) {
            for (int i = 0; i < m; ++i) {
                if ((k!= 0) && (k != n / 8 - 1)) System.out.printf("  ");
                for (int j = 8 * k; j < 4 * k + 8; ++j) System.out.printf("%02X ", M[i][j / 4]);
            if (k == n / 8 - 1) System.out.println();
            }
        }
        System.out.println();
    }

    void printExpandedKey(byte[][] M) {
        final int m = M.length;
        final int n = M[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((j != 0) && (j % 4 == 0)) System.out.printf("  ");
                System.out.printf("%02X", M[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    void inData(byte[][] M, String l) throws Exception {
        final int m = M.length;
        final int n = M[0].length;
        while (l.length() < 2 * m * n) l += "0";
        l = l.substring(0, 2*m*n).toLowerCase();
        for(char c : l.toCharArray()){
            if(c < '0' || c > 'f' || c > '9' && c < 'a') {
                System.out.printf("This '%s' is not in Hexadecimal form\n", c);
                System.exit(1);
            }
        }
        int k = 0;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i < m; ++i) {
                String hexText = l.substring(k, k + 2);
                k += 2;
                M[i][j] = (byte)Integer.parseInt(hexText, 16);
            }
        }
    }
    
    byte[][] keyExpansion(byte[][] key) {
        byte[][] extKey = new byte[4][4 * (numberRounds + 1)];        
        for(int i = 0; i < 4; ++i) for(int j = 0; j < numberWords; ++j) extKey[i][j] = key[i][j];
        for(int j = numberWords; j < 4 * (numberRounds + 1); ++j) {
            byte[] w = columnWord(extKey, j - 1); 
            if(j % numberWords == 0) {
                byte[] rw = new byte[]{r((byte)(j / numberWords)), 0, 0, 0};
                w = keyExpansionHelper1(w);
                w = keyExpansionHelper2(w);
                w = keyExpansionHelper3(w, rw);
            } else if (numberWords > 6 && j % numberWords == 4) w = keyExpansionHelper2(w);
            byte[] wk = columnWord(extKey, j - numberWords);
            wk = keyExpansionHelper3(wk, w);
            extKey = toCol(extKey, wk, j);
        }                 
        return extKey;
    }
    
    byte[] keyExpansionHelper1(byte[] w) {
        assert w.length == 4;
        byte t = w[0];
        for (int i = 0; i < 4-1; ++i) w[i] = w[i+1];
        w[3] = t;
        return w;
    }
    
    byte[] keyExpansionHelper2(byte[] w) {
        assert w.length == 4;
        for (int i = 0; i < 4; ++i) w[i] = keyExpansionHelper4(w[i], normal);
        return w;
    }
    
    byte keyExpansionHelper4(byte B, byte[][] t) {
        int i = B >>> 4 & 0x0F;
        int j = B & 0x0F;            
        B = t[i][j];
        return B;
    }
    
    byte[] keyExpansionHelper3(byte[] w, byte[] w2) {
        assert w.length == 4;
        for (int i = 0; i < 4; ++i) w[i] = (byte)(w[i] ^ w2[i]);
        return w;
    }
    
    byte[] columnWord(byte[][] M, final int j) {
        byte[] w = new byte[4];
        for(int i = 0; i < 4; ++i) w[i] = M[i][j];
        return w;
    }
    
    byte[][] toCol(byte[][] M, byte[] w, final int j){
        for(int i = 0; i < 4; ++i) M[i][j] = w[i];
        return M;
    }
    
    byte r(byte i) {
        byte c = 1;
        if(i == 0) return 0; 
        while(i != 1) {
            c = mul(c,(byte)2);
            --i;
        }
        return c;
    }
    
    byte mul(byte x, byte y) {
        byte z = 0;
        byte hight;
        for(byte c = 0; c < 8; c++) {
            if((y & 1) != 0) z ^= x;
            hight = (byte)(x & 0x80);
            x <<= 1;
            if(hight != 0x00) x ^= 0x1b;
            y >>= 1;
        }
        return z;
    }
    
    byte[][][] divRK(byte[][] extKey) {
        byte[][][] rk = new byte[numberRounds + 1][4][4]; 
        for (int r = 0; r < numberRounds + 1; ++r) for (int j = 0; j < 4; ++j) for (int i = 0; i < 4; ++i) rk[r][i][j] = extKey[i][4 * r + j];      
        return rk;
    }

public static void main(String[] args) throws Exception{
        if (args.length != 3) {
            System.out.println("\nIncorrect Syntax, Try: \"java AES < ed > keyFile inputFile\"\n");
            System.exit(1);
        } 
        String ed = args[0].toLowerCase();
        File keyFile = new File(args[1]);
        String inputFile = args[2];
        String outFile = null;
        byte[][] opt = new byte[4][4];
        byte[][][] rk;
        AES myClass = new AES();
              
        if (ed.equals("e")) outFile = inputFile + ".enc";
        else if (ed.equals("d"))outFile = inputFile + ".dec";
        else{
            System.out.println("\nIncorrect Syntax, Try: \"java AES < ed > keyFile inputFile\"\n");
            System.exit(1);
        }
        
        // Input
        Scanner sc = new Scanner(keyFile);
        String l = sc.next();
        byte[][] key = new byte[4][numberWords];        // 256 bit key
        
        // input Key
        myClass.inData(key, l);
        
        // key expansion
        byte[][] extKey = myClass.keyExpansion(key);
        rk = myClass.divRK(extKey);
        
        sc = new Scanner(new File(inputFile));
        PrintWriter printW = new PrintWriter(outFile);
        while(sc.hasNext()){
            l = sc.next();     
            try { myClass.inData(opt, l); }
            catch(Exception e) { continue; }
            
            if (ed.equals("e")) {
                System.out.println("\nThe Plaintext is: ");
                myClass.printPlainCipher(opt);
                System.out.println("The CipherKey is: ");
                myClass.printCipherMatrix(key);
                System.out.println("The expanded key is: ");
                myClass.printExpandedKey(myClass.keyExpansion(key));
                opt = myClass.encrypt(opt, rk);
            } else opt = myClass.decrypt(opt, rk);
            
            // Output
            myClass.toOut(opt, printW);
        }
        printW.close();
    }
}