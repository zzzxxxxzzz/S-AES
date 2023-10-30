public class SAES {
    // 定义S-DES算法所需要的置换表

    private static final String RECON1 = "10000000";
    private static final String RECON2 = "00110000";

    private static final int[][] SBOX = {
            { 9, 4, 10, 11 },
            { 13, 1, 8, 5 },
            { 6, 2, 0, 3 },
            { 12, 14, 15, 7 }
    };
    private static final int[][] ISBOX = {
            { 10, 5, 9, 11 },
            { 1, 7, 8, 15 },
            { 6, 0, 2, 3 },
            { 12, 4, 13, 14 }
    };

    // 两字符串的异或操作
    public static String Xor(String bit1, String bit2) {
        if (bit1.length() != bit2.length()) {
            throw new IllegalArgumentException("Input strings must have the same length.");
        }

        StringBuilder A = new StringBuilder();

        for (int i = 0; i < bit1.length(); i++) {
            char char1 = bit1.charAt(i);
            char char2 = bit2.charAt(i);
            if (char1 == char2) {
                A.append('0');
            } else {
                A.append('1');
            }
        }
        return A.toString();
    }

    // 实现密钥加函数
    public static String addRoundKey(String bit1, String bit2) {
        if (bit1.length() != bit2.length()) {
            throw new IllegalArgumentException("Input strings must have the same length.");
        }
        StringBuilder A = new StringBuilder();

        for (int i = 0; i < bit1.length(); i++) {
            char char1 = bit1.charAt(i);
            char char2 = bit2.charAt(i);
            if (char1 == char2) {
                A.append('0');
            } else {
                A.append('1');
            }
        }
        // System.out.print("main:"+A.length());
        return A.toString();

    }

    // 实现半字节代替
    public static String SubNib(String bit1) {

        // System.out.print("zzzzz:"+bit1.length());
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < bit1.length(); i += 4) {
            String patch = bit1.substring(i, i + 4);
            int row = Integer.parseInt(patch.substring(0, 2), 2);
            int col = Integer.parseInt(patch.substring(2, 4), 2);
            int substituteValue = SBOX[row][col];
            String substituteNibble = Integer.toBinaryString(substituteValue);

            while (substituteNibble.length() < 4) {
                substituteNibble = "0" + substituteNibble;
            }

            res.append(substituteNibble);
        }

        return res.toString();
    }

    // 实现逆半字节
    public static String InvSubNib(String bit1) {

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < 16; i += 4) {
            String patch = bit1.substring(i, i + 4);
            int row = Integer.parseInt(patch.substring(0, 2), 2);
            int col = Integer.parseInt(patch.substring(2, 4), 2);
            int substituteValue = ISBOX[row][col];
            String substituteNibble = Integer.toBinaryString(substituteValue);

            while (substituteNibble.length() < 4) {
                substituteNibble = "0" + substituteNibble;
            }

            res.append(substituteNibble);
        }

        return res.toString();
    }

    // 实现行位移
    public static String ShiftRow(String bit1) {

        String row1 = bit1.substring(0, 4);
        String row2 = bit1.substring(4, 8);
        String row3 = bit1.substring(8, 12);
        String row4 = bit1.substring(12, 16);

        // 进行行位移
        String res = row1 + row4 + row3 + row2;

        return res;
    }

    // 实现左移
    public static String RotNib(String bit1) {
        String res = bit1.substring(4, 8) + bit1.substring(0, 4);

        return res;
    }

    // 实现输入两个4bit的字符串，输出一个4位字符串
    public static String GF(String a, String b) {
        // 定义GF(2^4)上的乘法表
        int[][] mulTable = {
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 },
                { 0, 2, 4, 6, 8, 10, 12, 14, 3, 1, 7, 5, 11, 9, 15, 13 },
                { 0, 3, 6, 5, 12, 15, 10, 9, 11, 8, 13, 14, 7, 4, 1, 2 },
                { 0, 4, 8, 12, 3, 7, 11, 15, 6, 2, 14, 10, 5, 1, 13, 9 },
                { 0, 5, 10, 15, 7, 2, 13, 8, 14, 11, 4, 1, 9, 12, 3, 6 },
                { 0, 6, 12, 10, 11, 13, 7, 1, 5, 3, 9, 15, 14, 8, 2, 4 },
                { 0, 7, 14, 9, 15, 8, 1, 6, 13, 10, 3, 4, 2, 5, 12, 11 },
                { 0, 8, 3, 11, 6, 14, 5, 13, 12, 4, 15, 7, 10, 2, 9, 1 },
                { 0, 9, 1, 8, 2, 11, 3, 10, 4, 13, 5, 12, 6, 15, 7, 14 },
                { 0, 10, 7, 13, 14, 4, 9, 3, 15, 5, 8, 2, 1, 11, 6, 12 },
                { 0, 11, 5, 14, 10, 1, 15, 4, 7, 12, 2, 9, 13, 6, 8, 3 },
                { 0, 12, 11, 7, 5, 9, 14, 2, 10, 6, 1, 13, 15, 3, 4, 8 },
                { 0, 13, 9, 4, 1, 12, 8, 5, 2, 15, 11, 6, 3, 14, 10, 7 },
                { 0, 14, 15, 1, 13, 3, 2, 12, 9, 7, 6, 8, 4, 10, 11, 5 },
                { 0, 15, 13, 2, 9, 6, 4, 11, 1, 14, 12, 3, 8, 7, 5, 10 }
        };

        // 执行乘法运算
        int resultInt = mulTable[Integer.parseInt(a, 2)][Integer.parseInt(b, 2)];

        // 将结果转换为4位的二进制字符串
        String resultStr = String.format("%4s", Integer.toBinaryString(resultInt)).replace(' ', '0');

        return resultStr;
    }

    // 实现列混淆
    public static String MixColumns(String bits) {

        String col1 = bits.substring(0, 4);
        String col2 = bits.substring(4, 8);
        String col3 = bits.substring(8, 12);
        String col4 = bits.substring(12, 16);

        String res = Xor(col1, GF("0100", col2)) + Xor(GF("0100", col1), col2) +
                Xor(col3, GF("0100", col4)) + Xor(GF("0100", col3), col4);

        return res;
    }

    // 实现逆列混淆
    public static String InvMixColumns(String bits) {

        String col1 = bits.substring(0, 4);
        String col2 = bits.substring(4, 8);
        String col3 = bits.substring(8, 12);
        String col4 = bits.substring(12, 16);

        String res = Xor(GF("1001", col1), GF("0010", col2)) + Xor(GF("0010", col1), GF("1001", col2)) +
                Xor(GF("1001", col3), GF("0010", col4)) + Xor(GF("0010", col3), GF("1001", col4));
        return res;

    }

    // 实现密钥扩展函数
    public static String[] KeyExpansion(String key) {
        String w0 = key.substring(0, 8);
        // System.out.print("wo"+w0);
        String w1 = key.substring(8, 16);
        // System.out.print("wo"+w1);
        String w2 = Xor(w0, Xor(RECON1, SubNib(RotNib(w1))));
        // System.out.print("wo"+w2);
        String w3 = Xor(w2, w1);
        // System.out.print("wo"+w3);
        String w4 = Xor(w2, Xor(RECON2, SubNib(RotNib(w3))));
        // System.out.print("wo"+w4);
        String w5 = Xor(w4, w3);
        // System.out.print("wo"+w5);

        String[] res = { w0 + w1, w2 + w3, w4 + w5 };

        return res;

    }

    // S-AES加密函数
    public static String encrypt(String plainText, String key) {
        String[] key_expansion = KeyExpansion(key);
        // 密钥加
        String text_cipher = addRoundKey(plainText, key_expansion[0]);

        // 轮函数
        text_cipher = SubNib(text_cipher);
        text_cipher = ShiftRow(text_cipher);
        text_cipher = MixColumns(text_cipher);
        // 密钥加
        text_cipher = addRoundKey(text_cipher, key_expansion[1]);
        // 轮函数
        text_cipher = SubNib(text_cipher);
        text_cipher = ShiftRow(text_cipher);
        // 密钥加
        text_cipher = addRoundKey(text_cipher, key_expansion[2]);

        return text_cipher;
    }

    // S-AES解密函数
    public static String decrypt(String ciphperText, String key) {
        String[] key_expansion = KeyExpansion(key);
        String text_plain = addRoundKey(ciphperText, key_expansion[2]);

        text_plain = ShiftRow(text_plain);
        text_plain = InvSubNib(text_plain);

        text_plain = addRoundKey(text_plain, key_expansion[1]);
        text_plain = InvMixColumns(text_plain);

        text_plain = ShiftRow(text_plain);
        text_plain = InvSubNib(text_plain);

        text_plain = addRoundKey(text_plain, key_expansion[0]);

        return text_plain;
    }

    // CBC加密函数
    public static String CBC_encrypt(String plainText, String key, String IV) {
        // 将ASCII码转换为二进制字符串
        StringBuilder binaryText = new StringBuilder();
        for (char c : plainText.toCharArray()) {
            String binaryChar = Integer.toBinaryString(c);
            while (binaryChar.length() < 8) {
                binaryChar = "0" + binaryChar;
            }
            binaryText.append(binaryChar);
        }

        // 零填充，确保文本长度是16的倍数
        int remainder = binaryText.length() % 16;
        if (remainder != 0) {
            int paddingLength = 16 - remainder;
            for (int i = 0; i < paddingLength; i++) {
                binaryText.append("0");
            }
        }

        // 每16位进行切割，并放入字符串数组
        int blockCount = binaryText.length() / 16;
        String[] binaryBlocks = new String[blockCount];
        String[] cipherBlocks = new String[blockCount];
        for (int i = 0; i < blockCount; i++) {
            binaryBlocks[i] = binaryText.substring(i * 16, (i + 1) * 16);
        }

        // 初始化一个StringBuilder来存储加密后的二进制块
        StringBuilder encryptedText = new StringBuilder();

        String PXorIV = Xor(binaryBlocks[0], IV);
        String PXorC;
        cipherBlocks[0] = SAES.encrypt(PXorIV, key);
        encryptedText.append(cipherBlocks[0]);
        for (int i = 1; i < blockCount; i++) {
            PXorC = Xor(binaryBlocks[i], cipherBlocks[i - 1]);
            cipherBlocks[i] = SAES.encrypt(PXorC, key);
            encryptedText.append(cipherBlocks[i]);
        }

        // 将加密后的二进制块转换为ASCII码形式
        StringBuilder asciiCipherText = new StringBuilder();
        for (int i = 0; i < encryptedText.length(); i += 8) {
            String binaryChar = encryptedText.substring(i, i + 8);
            int asciiValue = Integer.parseInt(binaryChar, 2);
            char asciiChar = (char) asciiValue;
            asciiCipherText.append(asciiChar);
        }

        return asciiCipherText.toString();
    }

    // CBC解密函数
    public static String CBC_decrypt(String cipherText, String key, String IV) {
        // 将ASCII码转换为二进制字符串
        StringBuilder binaryText = new StringBuilder();
        for (char c : cipherText.toCharArray()) {
            String binaryChar = Integer.toBinaryString(c);
            while (binaryChar.length() < 8) {
                binaryChar = "0" + binaryChar;
            }
            binaryText.append(binaryChar);
        }

        // 零填充，确保文本长度是16的倍数
        int remainder = binaryText.length() % 16;
        if (remainder != 0) {
            int paddingLength = 16 - remainder;
            for (int i = 0; i < paddingLength; i++) {
                binaryText.append("0");
            }
        }

        // 每16位进行切割，并放入字符串数组
        int blockCount = binaryText.length() / 16;
        System.out.println("block数为："+blockCount);
        String[] binaryBlocks = new String[blockCount];
        String[] plainBlocks = new String[blockCount];
        for (int i = 0; i < blockCount; i++) {
            binaryBlocks[i] = binaryText.substring(i * 16, (i + 1) * 16);
            System.out.println("第"+i+"个binaryBlock为："+binaryBlocks[i]);
        }

        // 初始化一个StringBuilder来存储加密后的二进制块
        StringBuilder encryptedText = new StringBuilder();

        String D0 = SAES.decrypt(binaryBlocks[0], key);
        plainBlocks[0] = Xor(IV, D0);
        encryptedText.append(plainBlocks[0]);
        String D;
        for (int i = 1; i < blockCount; i++) {
            D = SAES.decrypt(binaryBlocks[i], key);
            plainBlocks[i] = Xor(binaryBlocks[i-1], D);
            encryptedText.append(plainBlocks[i]);
        }

        // 将加密后的二进制块转换为ASCII码形式
        StringBuilder asciiCipherText = new StringBuilder();
        for (int i = 0; i < encryptedText.length(); i += 8) {
            String binaryChar = encryptedText.substring(i, i + 8);
            int asciiValue = Integer.parseInt(binaryChar, 2);
            char asciiChar = (char) asciiValue;
            asciiCipherText.append(asciiChar);
        }

        return asciiCipherText.toString();
    }
}
