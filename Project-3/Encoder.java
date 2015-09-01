import java.io.*;
import java.lang.Math;
import java.lang.System;
import java.lang.StringBuilder;
import java.util.*;
import java.text.*;

class Encoder {
    
    private Map<Character, String> symbolEnc;
    public Encoder() {
        symbolEnc = new HashMap<Character, String>();
    }

    private static void enc(StringBuilder text, Map<Character, String> chEnc, String f) throws IOException {
        StringBuilder eText = new StringBuilder();
        for(int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            if(chEnc.containsKey(symbol)) eText.append(chEnc.get(symbol));
       }
       BufferedWriter out = new BufferedWriter(new FileWriter(new File(f)));
       out.append(eText);
       out.flush();
       out.close();
    }

    private static void dec(String in, String out, Map<String, Character> chDec) throws FileNotFoundException, IOException {
        StringBuilder nText = new StringBuilder(), dText = new StringBuilder();
        BufferedReader eFile = new BufferedReader(new FileReader(in));
        while(eFile.ready()) dText.append(eFile.readLine());
            StringBuilder t = new StringBuilder();
            for(int i = 0; i < dText.length(); i++) {
                t.append(dText.charAt(i));
                if(chDec.containsKey(t.toString())) {
                    nText.append(chDec.get(t.toString()));
                    t = new StringBuilder();
                }
            }
        BufferedWriter dFile = new BufferedWriter(new FileWriter(new File(out)));
        dFile.append(nText);
        dFile.flush(); 
        dFile.close(); 
    }

    private static int[] twoSymFreq(Map<String, Integer> m) {
        int[] freq = new int[677];
        int i = 0;
        for(String s: m.keySet()) {
            freq[i] = m.get(s);
            i++;
        }
        return freq;
     }

    private static void twoEnc(StringBuilder text, Map<String, String> symEnc, String f) throws IOException {
        StringBuilder eText = new StringBuilder();
        for(int i = 0; i < text.length()-1; i=i+2) {
            StringBuilder sym = new StringBuilder();
            sym.append(text.charAt(i));
            sym.append(text.charAt(i+1));
            if(symEnc.containsKey(sym.toString())) eText.append(symEnc.get(sym.toString()));
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(new File(f)));
        out.append(eText);
        out.flush();
        out.close();
    }

    private static void twoDec(String in, String out, Map<String, String> symDec) throws FileNotFoundException, IOException {
        StringBuilder nText = new StringBuilder();
        StringBuilder dText = new StringBuilder();
        BufferedReader eFile = new BufferedReader(new FileReader(in));
        while(eFile.ready()) dText.append(eFile.readLine());
        StringBuilder t = new StringBuilder();
        for(int i = 0; i < dText.length(); i++) {
            t.append(dText.charAt(i));
            if(symDec.containsKey(t.toString())) {
                nText.append(symDec.get(t.toString()));
                t = new StringBuilder();
            }
        }
        BufferedWriter dFile = new BufferedWriter(new FileWriter(new File(out)));
        dFile.append(nText);
        dFile.flush(); 
        dFile.close(); 
    }

    private static StringBuilder characters(int[] f, int totalSum, int toInt) throws IOException {
        Random ram = new Random();
        StringBuilder testText = new StringBuilder();
        int symbol = 65, i = 0, newSymbol = 0;
        char[] chArray = new char[totalSum];
        int k = 0;
        int freq = f[k];

        while(i < totalSum) {
            if (freq == 0) {
                symbol++;
                k++;
                freq = f[k];
                chArray[i] = (char)symbol;
            } else chArray[i] = (char)symbol;
            freq--;
            i++;
        }
        for(int j = 0; j < toInt; j++) {
            newSymbol = ram.nextInt(totalSum);
            testText.append(chArray[newSymbol]);
        }
        BufferedWriter out = new BufferedWriter(new FileWriter(new File("testText.txt")));
        out.append(testText);
        out.flush();
        out.close();
        return testText;
    }

    public static void main (String[] args) throws FileNotFoundException, IOException {
        if(args.length < 2) System.exit(1);
        String freqFile = args[0];
        int toInt = Integer.parseInt(args[1]);
        BufferedReader in;
        in = new BufferedReader(new FileReader(freqFile));

        // Entropy
        double h = 0.0;
        int[] freq = new int[27];
        double[] ch = new double[27];
        int totalSum = 0;
        String l;
        for(int i = 0; in.ready(); i++) {
            freq[i] = Integer.parseInt(in.readLine());
            totalSum += freq[i];
        }
        for(int i = 0; freq[i] != 0; i++) {
            ch[i] = ((double) freq[i] / totalSum);
            h += ch[i] * (Math.log(ch[i])/Math.log(2));
        }
        DecimalFormat df = new DecimalFormat("#.##");
        h *= -1;
        System.out.println("\nh = " + df.format(h) + "\n");

        // binary encoding with Huffman
        System.out.println("1-character encoding");
        HuffmanTree tree = HuffmanCode.buildTree(freq);
        System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
        HuffmanCode.printCodes(tree, new StringBuffer());

        final String eFile = "testText.enc1", dFile = "testText.dec1";
        StringBuilder testText = characters(freq, totalSum, toInt);
        Map<Character, String> chEnc = new HashMap<Character, String>();
        Map<String, Character> chDec = new HashMap<String, Character>();
        HuffmanCode.getCode(tree, new StringBuffer(), chEnc, chDec);
        enc(testText, chEnc, eFile);
        dec(eFile, dFile, chDec);

        double bits = (new FileInputStream(new File(eFile))).getChannel().size();
        double avgBits = (bits/toInt), perDiff = Math.abs((avgBits / h - 1) * 100);
        System.out.println("\nAverage bits per symbol = " + df.format(avgBits));
        System.out.println("Percentage difference =  " + df.format(perDiff) + "%");

        final String twoSymEncF = "testText.enc2", twoSymDecF = "testText.dec2";

        // mapping from two-symbols to frequencies
        Map<String, Integer> symToFreq  = new TreeMap<String, Integer>();

        for(int i = 0; freq[i] != 0 && i < 27; i++) {
            for(int j=0; freq[j] != 0 && j < 27; j++) {
                char one = (char)(65+i);
                char two = (char)(65+j);
                StringBuilder twoSym = new StringBuilder();
                twoSym.append(one);
                twoSym.append(two);
                symToFreq.put(twoSym.toString(), freq[i]*freq[j]);
            }
        }
        int[] twoSymFreq = twoSymFreq(symToFreq);
        HuffmanTree twoSymTree = HuffmanCode.buildTree(twoSymFreq);
        ArrayList<String> all = new ArrayList<String>();
        HuffmanCode.getTwoLetterCode(twoSymTree, new StringBuffer(),  all);
        Map<String, String> symDec = new HashMap<String, String>();
        Map<String, String> symEnc  = new HashMap<String, String>();

        int i = 0;
        for(String s: symToFreq.keySet()) {
            symDec.put(all.get(i), s);
            symEnc.put(s, all.get(i));
            i++;
        }

        System.out.println("\n2-character encoding");
        System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
        for(String symbols: symToFreq.keySet()) System.out.println(symbols + "\t" + symToFreq.get(symbols) + "\t" + symEnc.get(symbols));

        twoEnc(testText, symEnc, twoSymEncF);
        twoDec(twoSymEncF, twoSymDecF, symDec);
        double twoBits = (new FileInputStream(new File(twoSymEncF))).getChannel().size();
        double twoAvgBits = (twoBits / toInt), twoPerDiff = Math.abs((twoAvgBits / h - 1) * 100), twoOnePerDiff = Math.abs((twoAvgBits / avgBits - 1) * 100);
        System.out.println("\nAverage bits per symbol = " + df.format(twoAvgBits));
        System.out.println("Percentage difference = " + df.format(twoPerDiff) + "%");
        System.out.println("Percentage difference with 1-character encoding = " + df.format(twoOnePerDiff) + "%\n");
    }
}
