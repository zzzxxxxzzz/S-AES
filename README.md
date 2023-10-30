# S-AES小组作业
## 一 、作业任务

### 第一关：基本测试

**GUI用户交互界面首页**：用户选择加密或解密操作。

![[1-1]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/1-1.png)

**加密界面**：用户输入需要加密的明文（需要在二进制模式和ASCII模式中进行选择）以及相关的密钥，得到加密后的密文

**二进制（16-bit）模式**：
如图，当输入明文为1111111111111111，密钥为0000000000000000时，得到加密后的密文为0010100100110000。

![[1-2]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/1-2.png)

如果输入的内容不规范（非二进制16bit明文，非二进制16bit密钥），会进行相应的错误提示。


![[1-3]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/1-3.png)


![[1-4]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/1-4.png)


**解密界面**：用户输入需要解密的密文（同样需要在二进制模式和ASCII模式中进行选择）以及相关的密钥，得到解密后的明文。

**二进制（16-bit）模式：**

如图，当输入密文为0010100100110000，密钥为0000000000000000时，得到解密后的明文与上述加密前的明文一致，为1111111111111111

![[1-5]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/1-5.png)

同样，如果输入的内容不规范（非二进制16bit密文，非二进制16bit密钥），也会进行相应的错误提示。


![[1-6]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/1-6.png)



![[1-7]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/1-7.png)

### 第二关：交叉测试

参考其他小组中的测试内容，可以验证我们的加密以及解密过程可以得到与其他小组同样的结果。
（参考小组的github链接：[https://github.com/dori0512/S-AES-by-qt](https://github.com/dori0512/S-AES-by-qt)；[https://github.com/Mercurius14/S-AES-HW](https://github.com/Mercurius14/S-AES-HW)；[https://github.com/Xialanshan/S_AES](https://github.com/Xialanshan/S_AES)）

**二进制（16-bit）模式下加密**：

明文：1111111111111111

密钥：1111111111111111

密文：0101001101000011

![[2-1]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/2-1.png)


**二进制（16-bit）模式下解密**：

密文：0101001101000011

密钥：1111111111111111

明文：1111111111111111

![[2-2]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/2-2.png)


**ASCII模式下加密**：

明文：asdasx

密钥：1111111111111111

密文：4Sß&P

![[2-3]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/2-3.png)


**ASCII模式下解密:**

密文: 4Sß&P

密钥: 1111111111111111

明文: asdasx

![[2-4]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/2-4.png)

**双重加密模式下加密:**

明文：0000111100001111

密钥：00101101010101011010011100111011

生成密文：1110010001011110

![\[2-5\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/2-5.png)

**双重加密模式下解密:**

密文：1110010001011110

密钥：00101101010101011010011100111011

解密结果：0000111100001111

![\[2-6\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/2-6.png)

**三重加密模式下加密:**

明文：0110111101101011

密钥：00000000000000001010011100111011

生成密文：0000111110110100


![\[2-7\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/2-7.png)


**三重加密模式下解密:**

密文：0000111110110100

密钥：00000000000000001010011100111011

解密结果：0110111101101011

![\[2-8\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/2-8.png)


### 第三关：扩展功能

我们对该项目进行了拓展，实现了以ASCII码为明文输入和密文输出的功能。下面是加密演示：

明文：zxzxzxzx

密钥：0000000000000000

 密文：rÂrÂrÂrÂ

![[3-1]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/3-1.png)

下面是相应的解密演示：

![[3-2]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/3-2.png)

解密成功，得到相应的明文，第三关通过。

### 第四关：多重加密

#### 4-1 双重加密

我们实现了将S-AES算法通过双重加密进行扩展，分组长度仍然为16bits，密钥长度扩展到32bits。

下面是加解密演示：

对明文进行加密：

明文：1001010101110001

密钥：11110000101010010001101010101001

生成密文：1111000011001011


![[4-1-1]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/4-1-1.png)

对密文进行解密：

![[4-1-2]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/4-1-2.png)


解密结果与明文完全一致。



#### 4-2 中间相遇攻击

利用使用相同密钥的明密文对，采用中间相遇攻击的方法找到正确的密钥Key（K1+K2）。
利用密钥'0010110101010101'+'1010011100111011'获取如下明密文对。

|明文|密文|
|--|--|--|
|0000111100001111  | 1110010001011110 |
|1111111100000000 |0010010001010010|
|1100110011001101 |1010101100100010|

进行中间相遇攻击：

![\[4-2\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/4-2.png)

最终得到Key=K1+K2（00101101010101011010011100111011）。


#### 4-3 三重加密

将S-AES算法通过三重加密进行扩展，按照32bits密钥Key(K1+K2)的模式进行三重加密解密。

下面是加密演示：

明文：1010111100000001

密钥：00000000000000001111111111111111

生成密文：0110001100110010

![\[4-3-1\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/4-3-1.png)

下面进行解密演示：

![\[4-3-2\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/4-3-2.png)

得到的解密结果和明文一致。


### 第五关：工作模式

基于S-AES算法，使用密码分组链(CBC)模式对较长的明文消息进行加密。

注意初始向量(16 bits) 的生成，并需要加解密双方共享。

在CBC模式下进行加密，对密文分组进行替换或修改，然后进行解密，对比篡改密文前后的解密结果如下：

明文：111101

密钥：11100100001011110

IV向量：0000111100001111

生成密文：¹Ø«

![\[5-1\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/5-1.png)


密文未进行篡改的解密结果如下：

![\[5-2\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/5-2.png)

对密文进行篡改的解密结果如下：


![\[5-3\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/5-3.png)


两次生成的明文不一致，说明密文被篡改后无法得到正确的明文。






## 二、用户手册
### 1、S-AES算法介绍

* S-AES（Simplified Advanced Encryption Standard）算法是一种轻量级的对称加密算法，是对高级加密标准（AES）的简化版本。

* S-AES算法在加密过程中应用了一系列的基本运算，包括代替（Substitution）、行位移（Shift Row）、列混淆（Mix Columns）和密钥加（Key Addition）等步骤。这些步骤使得S-AES能够对输入的数据进行加密处理，从而生成安全的密文。

### 2、使用步骤
#### （1） 启动S-AES密码系统以进入主界面

![\[u-1\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/u-1.png)

#### （2） 选择加密、解密或者CBC加/解密操作

加密界面：

![\[u-2\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/u-2.png)


解密界面：

![\[u-3\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/u-3.png)

CBC加/解密界面：

![\[u-4\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/u-4.png)


#### （3）选择使用（二进制（16-bit）模式/ASCII模式）、（单次加密模式/双重加密模式/三重加密模式）进行加解密。

![\[u-5\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/u-5.png)

#### （4）输入需要加密（解密）的明文（密文）及其密钥（密钥输入时默认隐藏，如需要显示可点击右侧的“显示密钥”），如果格式错误会有相应的提示

![\[u-6\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/u-6.png)


#### （5）得到加密（解密）之后的密文（明文）结果

![\[u-7\]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/u-7.png)



#  
##  三、开发者手册

### 1、系统结构 
 S-AES（Simplified Advanced Encryption Standard）算法是一种轻量级的对称加密算法，是对高级加密标准（AES）的简化版本。

 S-AES算法在加密过程中应用了一系列的基本运算，包括代替（Substitution）、行位移（Shift Row）、列混淆（Mix Columns）和密钥加（Key Addition）等步骤。

根据S-AES算法的执行步骤，我们的S-ES系统结构如下：

#### 1.GUI用户界面（提供用户进行加解密交互操作的界面）

#### 2.基础加密（解密）操作：

（1）密钥扩展：根据用户提供的16bits大小的密钥生成三组16bits的子密钥

```java
public static String[] KeyExpansion(String key) {  

String w0 = key.substring(0, 8);  
String w1 = key.substring(8, 16);   
String w2 = Xor(w0, Xor(RECON1, SubNib(RotNib(w1))));  
String w3 = Xor(w2, w1);  
String w4 = Xor(w2, Xor(RECON2, SubNib(RotNib(w3))));  
String w5 = Xor(w4, w3);  
 
String[] res = { w0 + w1, w2 + w3, w4 + w5 };  
  
return res;  
}
```

（2）数据加密：依照S-AES算法的加密基本流程进行加密操作：
轮密钥加→第一轮加密（包含四个函数的完整轮：半字节代替→行位移→列混淆→轮密钥加）
→第二轮加密（包含三个函数：半字节代替→行位移→轮密钥加）→完成加密
```java
public static String encrypt(String plainText, String key) {  
//密钥扩展
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
```

（3）数据解密：依照S-AES算法的解密基本流程进行解密操作：
轮密钥加→第一轮解密（包含四个函数的完整轮：逆行位移→逆半字节代替
→轮密钥加→逆列混淆）→第二轮加密（包含三个函数：逆行位移→逆半字节代替→轮密钥加）
→完成解密

```java
// S-AES解密函数  
public static String decrypt(String ciphperText, String key) {
//密钥扩展  
String[] key_expansion = KeyExpansion(key);  
//轮密钥加
String text_plain = addRoundKey(ciphperText, key_expansion[2]);  
//第一轮解密
text_plain = ShiftRow(text_plain);  
text_plain = InvSubNib(text_plain);  
text_plain = addRoundKey(text_plain, key_expansion[1]);  
text_plain = InvMixColumns(text_plain);  
//第二轮解密
text_plain = ShiftRow(text_plain);  
text_plain = InvSubNib(text_plain);  
//轮密钥加
text_plain = addRoundKey(text_plain, key_expansion[0]);  
  
return text_plain;  
}
```


### 2、关键代码组件
#### 1、SAES类（用于进行基础16bits明密文加解密功能的类）

（1） 需要用到的置换盒
``` java
// 定义S-AES算法所需要的置换表
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
```
（2）密钥扩展函数
```java
public static String[] KeyExpansion(String key) {  

String w0 = key.substring(0, 8);  
String w1 = key.substring(8, 16);   
String w2 = Xor(w0, Xor(RECON1, SubNib(RotNib(w1))));  
String w3 = Xor(w2, w1);  
String w4 = Xor(w2, Xor(RECON2, SubNib(RotNib(w3))));  
String w5 = Xor(w4, w3);  
 
String[] res = { w0 + w1, w2 + w3, w4 + w5 };  
  
return res;  
}
```
（3）异或函数
```java
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
```
（4）轮密钥加函数
```java
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
```

（5）半字节替代函数
```java
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
```
（6）逆半字节替代函数
```java
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

```
（7）行位移函数
```java
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
```

（8）列混淆函数
```java
public static String MixColumns(String bits) {  
  
String col1 = bits.substring(0, 4);  
String col2 = bits.substring(4, 8);  
String col3 = bits.substring(8, 12);  
String col4 = bits.substring(12, 16);  
  
String res = Xor(col1, GF("0100", col2)) + Xor(GF("0100", col1), col2) +  
Xor(col3, GF("0100", col4)) + Xor(GF("0100", col3), col4);  
  
return res;  
}
```

（9）逆列混淆函数
```java
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
```

（10）CBC模式加密函数：
```java
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
```
（11）CBC模式解密函数：
```java
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
```

####  2、用户界面设计

（1）主界面
```java
public class UI extends JFrame{  
Encrypt_GUI encrypt_GUI;  
Decrypt_GUI decrypt_GUI;  
CBC_GUI cbc_GUI;  
JLabel welcomeLabel;  
JButton decryptButton,encryptButton,cbcButton;  
  
UI(){  
JFrame frame = new JFrame();  
frame.setTitle("S-AES加密系统");  
frame.setSize(800, 600);  
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
frame.setLocationRelativeTo(null);  
frame.setResizable(false);  
frame.setLayout(null);  
  
//设置基础进入界面  
welcomeLabel = new JLabel("欢迎使用S-AES加密系统！");  
welcomeLabel.setFont(new Font("宋体", Font.TYPE1_FONT, 30));  
welcomeLabel.setBounds(220, 120, 400, 130);  
encryptButton = new JButton("加密明文");  
encryptButton.setFont(new Font("黑体", Font.PLAIN, 18));  
encryptButton.setBounds(180, 315, 130, 50);  
encryptButton.addActionListener(new ActionListener() {  
public void actionPerformed(ActionEvent e){  
frame.setVisible(false);  
encrypt_GUI = new Encrypt_GUI();  
}  
});  
  
decryptButton = new JButton("解密密文");  
decryptButton.setBounds(330, 315, 130, 50);  
decryptButton.setFont(new Font("黑体", Font.PLAIN, 18));  
decryptButton.addActionListener(new ActionListener() {  
public void actionPerformed(ActionEvent e){  
frame.setVisible(false);  
decrypt_GUI = new Decrypt_GUI();  
}  
});  
  
cbcButton = new JButton("CBC加/解密");  
cbcButton.setBounds(480, 315, 130, 50);  
cbcButton.setFont(new Font("黑体", Font.PLAIN, 18));  
cbcButton.addActionListener(new ActionListener() {  
public void actionPerformed(ActionEvent e){  
frame.setVisible(false);  
cbc_GUI = new CBC_GUI();  
}  
});  
  
frame.add(welcomeLabel);  
frame.add(encryptButton);  
frame.add(decryptButton);  
frame.add(cbcButton);  
frame.setVisible(true);  
  
}  
}
```

（2）加密界面
```java
import java.awt.Dimension;  
import java.awt.Font;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.ItemEvent;  
import java.awt.event.ItemListener;  
import java.util.Random;  
import javax.swing.*;  
  
public class Encrypt_GUI {  
JTextField dataField;  
JTextArea resulTextArea;  
JPasswordField keyJPasswordField;  
JLabel dataJLabel, keyJLabel, resultJLabel, welcomeLabel;  
JCheckBox checkBox;  
JButton button1, button2;  
JOptionPane warning;  
String dataString, cipherText;  
  
String keyString;  
int[] key;  
Box box_H_01, box_H_02, box_H_03, box_V;  
  
Encrypt_GUI() {  
init();  
}  
  
void init() {  
JFrame frame = new JFrame("Encrypt_GUI");  
  
// 初始化UI布置组件  
box_H_01 = Box.createHorizontalBox();  
box_H_02 = Box.createHorizontalBox();  
box_H_03 = Box.createHorizontalBox();  
box_V = Box.createVerticalBox();  
  
// 设置窗体对象的属性值  
frame.setTitle("加密界面");  
frame.setSize(900, 650);  
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
frame.setLocationRelativeTo(null);  
frame.setResizable(false);  
frame.setLayout(null);  
  
// 设置欢迎标签  
welcomeLabel = new JLabel("欢迎使用S-AES加密系统！");  
welcomeLabel.setFont(new Font("宋体", Font.TYPE1_FONT, 30));  
welcomeLabel.setBounds(265, 40, 400, 80);  
  
// 明文输入模式选择按钮  
JRadioButton textModeRadioButton = new JRadioButton("二进制(16-bit)模式");  
JRadioButton asciiModeRadioButton = new JRadioButton("ASCII模式");  
JRadioButton key1 = new JRadioButton("单次加密模式");  
JRadioButton key2 = new JRadioButton("双重加密模式");  
JRadioButton key3 = new JRadioButton("三重加密模式");  
ButtonGroup modeButtonGroup = new ButtonGroup();  
ButtonGroup keyButtonGroup = new ButtonGroup();  
modeButtonGroup.add(textModeRadioButton);  
modeButtonGroup.add(asciiModeRadioButton);  
textModeRadioButton.setSelected(true);  
textModeRadioButton.setFont(new Font("黑体", Font.PLAIN, 18));  
asciiModeRadioButton.setFont(new Font("黑体", Font.PLAIN, 18));  
textModeRadioButton.setBounds(250, 105, 210, 30);  
asciiModeRadioButton.setBounds(500, 105, 115, 30);  
  
keyButtonGroup.add(key1);  
keyButtonGroup.add(key2);  
keyButtonGroup.add(key3);  
key1.setSelected(true);  
key1.setFont(new Font("黑体", Font.PLAIN, 18));  
key2.setFont(new Font("黑体", Font.PLAIN, 18));  
key3.setFont(new Font("黑体", Font.PLAIN, 18));  
key1.setBounds(240, 135, 145, 30);  
key2.setBounds(385, 135, 145, 30);  
key3.setBounds(530, 135, 145, 30);  
  
// 明文输入  
dataJLabel = new JLabel("明文：");  
dataJLabel.setFont(new Font("黑体", Font.PLAIN, 18));  
dataField = new JTextField();  
dataField.setPreferredSize(new Dimension(150, 25));  
dataField.setFont(new Font("微软雅黑", Font.PLAIN, 18));  
  
// 密钥输入  
keyJLabel = new JLabel("密钥：");  
keyJLabel.setFont(new Font("黑体", Font.PLAIN, 18));  
keyJPasswordField = new JPasswordField();  
keyJPasswordField.setEchoChar('*');  
keyJPasswordField.setPreferredSize(new Dimension(150, 25));  
keyJPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 18));  
  
// 密钥可视按钮  
checkBox = new JCheckBox("显示密钥");  
checkBox.setSize(new Dimension(70, 35));  
checkBox.setFont(new Font("黑体", Font.TYPE1_FONT, 21));  
checkBox.addItemListener(new ItemListener() {  
public void itemStateChanged(ItemEvent e) {  
if (e.getStateChange() == ItemEvent.SELECTED) {  
keyJPasswordField.setEchoChar((char) 0);  
} else {  
keyJPasswordField.setEchoChar('*');  
}  
}  
});  
checkBox.setBounds(650, 255, 130, 70);  
  
// 密文显示  
resultJLabel = new JLabel("密文：");  
resultJLabel.setFont(new Font("黑体", Font.PLAIN, 18));  
resulTextArea = new JTextArea();  
resulTextArea.setPreferredSize(new Dimension(180, 80));  
resulTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));  
resulTextArea.setEditable(false);  
  
// 加密按钮  
button1 = new JButton();  
button1.setText("加密");  
button1.setFont(new Font("黑体", Font.PLAIN, 19));  
button1.setSize(new Dimension(100, 50));  
  
// 加密按钮  
button1.addActionListener(new ActionListener() {  
public void actionPerformed(ActionEvent e) {  
if (textModeRadioButton.isSelected()) {  
// 文本模式  
String dataInput = dataField.getText().trim();  
String keyInput = new String(keyJPasswordField.getPassword());  
if (!isValidBinary(dataInput)) {  
warning.showMessageDialog(frame, "请输入16位的二进制明文！", "警告", JOptionPane.WARNING_MESSAGE);  
} else if (!isValidBinaryKey16(keyInput) && key1.isSelected()) {  
warning.showMessageDialog(frame, "请输入16位的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);  
} else if (!isValidBinaryKey32(keyInput) && (key2.isSelected() || key3.isSelected())) {  
warning.showMessageDialog(frame, "请输入32位的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);  
} else {  
if (key1.isSelected()) {  
// 获取明文  
dataString = dataInput;  
  
// 获取密钥  
keyString = keyInput;  
  
cipherText = SAES.encrypt(dataString, keyString);  
resulTextArea.setText(cipherText);  
} else if (key2.isSelected()) {  
// 获取明文  
dataString = dataInput;  
  
// 获取密钥  
keyString = keyInput;  
int splitIndex = keyString.length() / 2;  
String key1String = keyString.substring(0, splitIndex);  
String key2String = keyString.substring(splitIndex);  
// 双重加密  
cipherText = SAES.encrypt(SAES.encrypt(dataString, key1String), key2String);  
resulTextArea.setText(cipherText);  
} else if (key3.isSelected()) {  
// 获取明文  
dataString = dataInput;  
  
// 获取密钥  
keyString = keyInput;  
int splitIndex = keyString.length() / 2;  
String key1String = keyString.substring(0, splitIndex);  
String key2String = keyString.substring(splitIndex);  
String cipherText1;  
String cipherText2;  
// 三重加密  
cipherText = SAES.encrypt(dataString, key1String);  
cipherText1 = SAES.decrypt(cipherText, key2String);  
cipherText2 = SAES.encrypt(cipherText1, key1String);  
resulTextArea.setText(cipherText2);  
}  
  
}  
} else if (asciiModeRadioButton.isSelected()) {  
// ASCII模式  
String keyInput = new String(keyJPasswordField.getPassword());  
if (dataField.getText().trim().equals("")) {  
warning.showMessageDialog(frame, "明文不能为空！", "警告", JOptionPane.WARNING_MESSAGE);  
} else if (!isValidBinaryKey16(keyInput) && key1.isSelected()) {  
warning.showMessageDialog(frame, "请输入16位的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);  
} else if (!isValidBinaryKey32(keyInput) && (key2.isSelected() || key3.isSelected())) {  
warning.showMessageDialog(frame, "请输入32位的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);  
} else {  
// 获取明文  
String asciiText = dataField.getText();  
// 获取密钥  
keyString = new String(keyJPasswordField.getPassword());  
// 将ASCII码转换为二进制字符串  
StringBuilder binaryText = new StringBuilder();  
for (char c : asciiText.toCharArray()) {  
String binaryChar = Integer.toBinaryString(c);  
while (binaryChar.length() < 8) {  
binaryChar = "0" + binaryChar;  
}  
binaryText.append(binaryChar);  
}  
  
// 每16位进行切割，并放入字符串数组  
int blockCount = binaryText.length() / 16;  
String[] binaryBlocks = new String[blockCount];  
for (int i = 0; i < blockCount; i++) {  
binaryBlocks[i] = binaryText.substring(i * 16, (i + 1) * 16);  
}  
// 初始化一个StringBuilder来存储加密后的二进制块  
StringBuilder encryptedText = new StringBuilder();  
  
// 对每个16位的二进制块进行SDES加密  
if (key1.isSelected()) {  
for (String block : binaryBlocks) {  
cipherText = SAES.encrypt(block, keyString); // 假设SAES.encrypt接受16位的二进制输入  
encryptedText.append(cipherText);  
}  
}  
else if (key2.isSelected()){  
int splitIndex = keyString.length() / 2;  
String key1String = keyString.substring(0, splitIndex);  
String key2String = keyString.substring(splitIndex);  
for (String block : binaryBlocks) {  
cipherText = SAES.encrypt(SAES.encrypt(block, key1String), key2String);  
encryptedText.append(cipherText);  
}  
}  
else if (key3.isSelected()){  
int splitIndex = keyString.length() / 2;  
String key1String = keyString.substring(0, splitIndex);  
String key2String = keyString.substring(splitIndex);  
for (String block : binaryBlocks) {  
cipherText = SAES.encrypt(SAES.decrypt(SAES.encrypt(block, key1String), key2String), key1String);  
encryptedText.append(cipherText);  
}  
}  
  
// 将加密后的二进制块转换为ASCII码形式  
StringBuilder asciiCipherText = new StringBuilder();  
for (int i = 0; i < encryptedText.length(); i += 8) {  
String binaryChar = encryptedText.substring(i, i + 8);  
int asciiValue = Integer.parseInt(binaryChar, 2);  
char asciiChar = (char) asciiValue;  
asciiCipherText.append(asciiChar);  
}  
  
resulTextArea.setText(asciiCipherText.toString());  
}  
}  
}  
 //判断输入是否为二进制
private boolean isValidBinary(String input) {  
  
return input.matches("[01]{16}");  
}  
  
private boolean isValidBinaryKey16(String input) {  
  
return input.matches("[01]{16}");  
}  
  
private boolean isValidBinaryKey32(String input) {  
  
return input.matches("[01]{32}");  
}  
});  
  
button1.setBounds(265, 500, 160, 40);  
  
// 返回按钮  
button2 = new JButton("返回");  
button2.setFont(new Font("黑体", Font.PLAIN, 19));  
button2.setSize(new Dimension(100, 50));  
button2.addActionListener(new ActionListener() {  
public void actionPerformed(ActionEvent e) {  
UI uI = new UI();  
frame.dispose();  
}  
});  
button2.setBounds(465, 500, 160, 40);  
  
// 布置各个组件  
box_H_01.add(dataJLabel);  
box_H_01.add(Box.createHorizontalStrut(5));  
box_H_01.add(dataField);  
  
box_H_02.add(keyJLabel);  
box_H_02.add(Box.createHorizontalStrut(5));  
box_H_02.add(keyJPasswordField);  
  
box_H_03.add(resultJLabel);  
box_H_03.add(Box.createHorizontalStrut(5));  
box_H_03.add(resulTextArea);  
  
box_V.add(box_H_01);  
box_V.add(Box.createVerticalStrut(40));  
box_V.add(box_H_02);  
box_V.add(Box.createVerticalStrut(30));  
box_V.add(box_H_03);  
box_V.setBounds(200, 170, 450, 300);  
  
frame.add(welcomeLabel);  
frame.add(box_V);  
frame.add(checkBox);  
frame.add(button1);  
frame.add(button2);  
frame.add(textModeRadioButton);  
frame.add(asciiModeRadioButton);  
frame.add(key1);  
frame.add(key2);  
frame.add(key3);  
frame.setVisible(true);  
}  
}
```


（3）解密界面
```java
import javax.swing.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.ItemEvent;  
import java.awt.event.ItemListener;  
import java.awt.Dimension;  
import java.awt.Font;  
  
public class Decrypt_GUI {  
JTextField dataField;  
JTextArea resulTextArea;  
JPasswordField keyJPasswordField;  
JLabel dataJLabel, keyJLabel, resultJLabel, welcomeLabel;  
JCheckBox checkBox;  
JButton button1, button2;  
JOptionPane warning;  
String dataString, plainText;  
String keyString;  
int[] key;  
Box box_H_01, box_H_02, box_H_03, box_V;  
SAES sDes;  
  
Decrypt_GUI() {  
init();  
}  
  
void init() {  
JFrame frame = new JFrame("Encrypt_GUI");  
  
// 初始化UI布置组件  
box_H_01 = Box.createHorizontalBox();  
box_H_02 = Box.createHorizontalBox();  
box_H_03 = Box.createHorizontalBox();  
box_V = Box.createVerticalBox();  
  
// 设置窗体对象的属性值  
frame.setTitle("解密界面");  
frame.setSize(900, 650);  
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
frame.setLocationRelativeTo(null);  
frame.setResizable(false);  
frame.setLayout(null);  
  
// 设置欢迎标签  
welcomeLabel = new JLabel("欢迎使用S-AES解密系统！");  
welcomeLabel.setFont(new Font("黑体", Font.PLAIN, 30));  
welcomeLabel.setBounds(265, 40, 400, 80);  
  
// 明文输入模式选择按钮  
JRadioButton textModeRadioButton = new JRadioButton("二进制(16-bit)模式");  
JRadioButton asciiModeRadioButton = new JRadioButton("ASCII模式");  
JRadioButton key1 = new JRadioButton("单次加密模式");  
JRadioButton key2 = new JRadioButton("双重加密模式");  
JRadioButton key3 = new JRadioButton("三重加密模式");  
ButtonGroup modeButtonGroup = new ButtonGroup();  
ButtonGroup keyButtonGroup = new ButtonGroup();  
modeButtonGroup.add(textModeRadioButton);  
modeButtonGroup.add(asciiModeRadioButton);  
textModeRadioButton.setSelected(true);  
textModeRadioButton.setFont(new Font("黑体", Font.PLAIN, 18));  
asciiModeRadioButton.setFont(new Font("黑体", Font.PLAIN, 18));  
textModeRadioButton.setBounds(250, 105, 210, 30);  
asciiModeRadioButton.setBounds(500, 105, 120, 30);  
  
keyButtonGroup.add(key1);  
keyButtonGroup.add(key2);  
keyButtonGroup.add(key3);  
key1.setSelected(true);  
key1.setFont(new Font("黑体", Font.PLAIN, 18));  
key2.setFont(new Font("黑体", Font.PLAIN, 18));  
key3.setFont(new Font("黑体", Font.PLAIN, 18));  
key1.setBounds(240, 135, 145, 30);  
key2.setBounds(385, 135, 145, 30);  
key3.setBounds(530, 135, 145, 30);  
  
// 密文输入  
dataJLabel = new JLabel("密文：");  
dataJLabel.setFont(new Font("黑体", Font.PLAIN, 18));  
dataField = new JTextField();  
dataField.setPreferredSize(new Dimension(150, 25));  
dataField.setFont(new Font("微软雅黑", Font.PLAIN, 18));  
  
// 密钥输入  
keyJLabel = new JLabel("密钥：");  
keyJLabel.setFont(new Font("黑体", Font.PLAIN, 18));  
keyJPasswordField = new JPasswordField();  
keyJPasswordField.setEchoChar('*');  
keyJPasswordField.setPreferredSize(new Dimension(150, 25));  
keyJPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 18));  
  
// 密钥可视按钮  
checkBox = new JCheckBox("显示密钥");  
checkBox.setSize(new Dimension(70, 35));  
checkBox.setFont(new Font("黑体", Font.TYPE1_FONT, 21));  
checkBox.addItemListener(new ItemListener() {  
public void itemStateChanged(ItemEvent e) {  
if (e.getStateChange() == ItemEvent.SELECTED) {  
keyJPasswordField.setEchoChar((char) 0);  
} else {  
keyJPasswordField.setEchoChar('*');  
}  
}  
});  
checkBox.setBounds(650, 255, 130, 70);  
  
// 明文显示  
resultJLabel = new JLabel("明文：");  
resultJLabel.setFont(new Font("黑体", Font.PLAIN, 18));  
resulTextArea = new JTextArea();  
resulTextArea.setPreferredSize(new Dimension(180, 80));  
resulTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));  
resulTextArea.setEditable(false);  
  
// 解密按钮  
button1 = new JButton();  
button1.setText("解密");  
button1.setFont(new Font("黑体", Font.PLAIN, 19));  
button1.setSize(new Dimension(100, 50));  
button1.addActionListener(new ActionListener() {  
public void actionPerformed(ActionEvent e) {  
if (textModeRadioButton.isSelected()) {  
// 文本模式  
String dataInput = dataField.getText().trim();  
String keyInput = new String(keyJPasswordField.getPassword());  
if (!isValidBinary(dataInput)) {  
warning.showMessageDialog(frame, "请输入16位的二进制密文！", "警告", JOptionPane.WARNING_MESSAGE);  
} else if (!isValidBinaryKey16(keyInput) && key1.isSelected()) {  
warning.showMessageDialog(frame, "请输入16位的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);  
} else if (!isValidBinaryKey32(keyInput) && (key2.isSelected() || key3.isSelected())) {  
warning.showMessageDialog(frame, "请输入32位的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);  
} else {  
if (key1.isSelected()) {  
// 获取密文  
dataString = dataField.getText();  
// 获取密钥  
keyString = new String(keyJPasswordField.getPassword());  
  
plainText = SAES.decrypt(dataString, keyString);  
resulTextArea.setText(plainText);  
} else if (key2.isSelected()) {  
// 获取密文  
dataString = dataField.getText();  
// 获取密钥  
keyString = new String(keyJPasswordField.getPassword());  
int splitIndex = keyString.length() / 2;  
String key1String = keyString.substring(0, splitIndex);  
String key2String = keyString.substring(splitIndex);  
// 双重解密  
plainText = SAES.decrypt(SAES.decrypt(dataString, key2String), key1String);  
resulTextArea.setText(plainText);  
} else if (key3.isSelected()) {  
// 获取密文  
dataString = dataField.getText();  
// 获取密钥  
keyString = new String(keyJPasswordField.getPassword());  
int splitIndex = keyString.length() / 2;  
String key1String = keyString.substring(0, splitIndex);  
String key2String = keyString.substring(splitIndex);  
String plainText1;  
String plainText2;  
// 三重解密  
plainText = SAES.decrypt(dataString, key1String);  
plainText1 = SAES.encrypt(plainText, key2String);  
plainText2 = SAES.decrypt(plainText1, key1String);  
resulTextArea.setText(plainText2);  
}  
}  
} else if (asciiModeRadioButton.isSelected()) {  
// ASCII模式  
String keyInput = new String(keyJPasswordField.getPassword());  
if (dataField.getText().trim().equals("")) {  
warning.showMessageDialog(frame, "密文不能为空！", "警告", JOptionPane.WARNING_MESSAGE);  
} else if (!isValidBinaryKey16(keyInput) && key1.isSelected()) {  
warning.showMessageDialog(frame, "请输入16位的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);  
} else if (!isValidBinaryKey32(keyInput) && (key2.isSelected() || key3.isSelected())) {  
warning.showMessageDialog(frame, "请输入32位的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);  
} else {  
// 获取密文  
String cipherText = dataField.getText();  
// 获取密钥  
keyString = new String(keyJPasswordField.getPassword());  
  
// 将密文转换为二进制字符串  
StringBuilder binaryText = new StringBuilder();  
for (char c : cipherText.toCharArray()) {  
String binaryChar = Integer.toBinaryString(c);  
while (binaryChar.length() < 8) {  
binaryChar = "0" + binaryChar;  
}  
binaryText.append(binaryChar);  
}  
  
// 每8位进行切割，并放入字符串数组  
int blockCount = binaryText.length() / 16;  
String[] binaryBlocks = new String[blockCount];  
for (int i = 0; i < blockCount; i++) {  
binaryBlocks[i] = binaryText.substring(i * 16, (i + 1) * 16);  
}  
  
// 初始化一个StringBuilder来存储解密后的二进制块  
StringBuilder decryptedText = new StringBuilder();  
  
// 对每个8位的二进制块进行SDES解密  
if (key1.isSelected()) {  
for (String block : binaryBlocks) {  
String plaintextBlock = SAES.decrypt(block, keyString); // 使用SDES解密  
decryptedText.append(plaintextBlock);  
}  
}  
else if(key2.isSelected()){  
int splitIndex = keyString.length() / 2;  
String key1String = keyString.substring(0, splitIndex);  
String key2String = keyString.substring(splitIndex);  
for (String block : binaryBlocks) {  
String plaintextBlock = SAES.decrypt(SAES.decrypt(block, key2String), key1String); // 使用SDES解密  
decryptedText.append(plaintextBlock);  
}  
}  
else if(key3.isSelected()){  
int splitIndex = keyString.length() / 2;  
String key1String = keyString.substring(0, splitIndex);  
String key2String = keyString.substring(splitIndex);  
for (String block : binaryBlocks) {  
String plaintextBlock = SAES.decrypt(SAES.encrypt(SAES.decrypt(block, key1String), key2String), key1String); // 使用SDES解密  
decryptedText.append(plaintextBlock);  
}  
}  
// 将解密后的二进制块转换为ASCII码形式  
StringBuilder asciiPlaintext = new StringBuilder();  
for (int i = 0; i < decryptedText.length(); i += 8) {  
String binaryChar = decryptedText.substring(i, i + 8);  
int asciiValue = Integer.parseInt(binaryChar, 2);  
char asciiChar = (char) asciiValue;  
asciiPlaintext.append(asciiChar);  
}  
  
resulTextArea.setText(asciiPlaintext.toString());  
  
}  
}  
}  
  
// 定义一个辅助方法来验证二进制输入是否有效  
private boolean isValidBinary(String input) {  
// 使用正则表达式验证是否是16位二进制输入  
return input.matches("[01]{16}");  
};  
  
// 定义一个辅助方法来验证二进制密钥是否有效  
private boolean isValidBinaryKey16(String input) {  
// 使用正则表达式验证是否是16位二进制输入  
return input.matches("[01]{16}");  
}  
  
private boolean isValidBinaryKey32(String input) {  
  
return input.matches("[01]{32}");  
}  
});  
button1.setBounds(265, 500, 160, 40);  
  
// 返回按钮  
button2 = new JButton("返回");  
button2.setFont(new Font("黑体", Font.PLAIN, 19));  
button2.setSize(new Dimension(100, 50));  
button2.addActionListener(new ActionListener() {  
public void actionPerformed(ActionEvent e) {  
UI uI = new UI();  
frame.dispose();  
}  
});  
button2.setBounds(465, 500, 160, 40);  
  
// 布置各个组件  
box_H_01.add(dataJLabel);  
box_H_01.add(Box.createHorizontalStrut(5));  
box_H_01.add(dataField);  
  
box_H_02.add(keyJLabel);  
box_H_02.add(Box.createHorizontalStrut(5));  
box_H_02.add(keyJPasswordField);  
  
box_H_03.add(resultJLabel);  
box_H_03.add(Box.createHorizontalStrut(5));  
box_H_03.add(resulTextArea);  
  
box_V.add(box_H_01);  
box_V.add(Box.createVerticalStrut(40));  
box_V.add(box_H_02);  
box_V.add(Box.createVerticalStrut(30));  
box_V.add(box_H_03);  
box_V.setBounds(200, 170, 450, 300);  
  
frame.add(welcomeLabel);  
frame.add(box_V);  
frame.add(checkBox);  
frame.add(button1);  
frame.add(button2);  
frame.add(textModeRadioButton);  
frame.add(asciiModeRadioButton);  
frame.add(key1);  
frame.add(key2);  
frame.add(key3);  
frame.setVisible(true);  
}  
}
```
#### 3、 CBC模式加解密界面
```java
import java.awt.Dimension;  
import java.awt.Font;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.ItemEvent;  
import java.awt.event.ItemListener;  
import java.util.Random;  
  
import javax.swing.*;  
  
public class CBC_GUI {  
JTextField dataField, IVField;  
JTextArea resulTextArea;  
JPasswordField keyJPasswordField;  
JLabel dataJLabel, keyJLabel, resultJLabel, welcomeLabel, IVLabel;  
JCheckBox checkBox;  
JButton button1, button2;  
JOptionPane warning;  
JRadioButton encryptButton;  
JRadioButton decryptButton;  
String dataString, cipherText, IVString, resultString;  
  
String keyString;  
int[] key;  
Box box_H_01, box_H_02, box_H_03, box_H_04, box_V;  
  
CBC_GUI() {  
init();  
}  
  
void init() {  
JFrame frame = new JFrame("Encrypt_GUI");  
  
// 初始化UI布置组件  
box_H_01 = Box.createHorizontalBox();  
box_H_02 = Box.createHorizontalBox();  
box_H_03 = Box.createHorizontalBox();  
box_H_04 = Box.createHorizontalBox();  
box_V = Box.createVerticalBox();  
  
// 设置窗体对象的属性值  
frame.setTitle("CBC加/解密界面");  
frame.setSize(900, 650);  
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
frame.setLocationRelativeTo(null);  
frame.setResizable(false);  
frame.setLayout(null);  
  
// 设置欢迎标签  
welcomeLabel = new JLabel("欢迎使用S-AES加密系统！");  
welcomeLabel.setFont(new Font("宋体", Font.TYPE1_FONT, 30));  
welcomeLabel.setBounds(265, 40, 400, 80);  
  
// 加/解密模式选择  
encryptButton = new JRadioButton("加密模式");  
decryptButton = new JRadioButton("解密模式");  
ButtonGroup modeButtonGroup = new ButtonGroup();  
modeButtonGroup.add(encryptButton);  
modeButtonGroup.add(decryptButton);  
encryptButton.setSelected(true);  
encryptButton.setFont(new Font("黑体", Font.PLAIN, 18));  
decryptButton.setFont(new Font("黑体", Font.PLAIN, 18));  
encryptButton.setBounds(250, 105, 180, 30);  
decryptButton.setBounds(500, 105, 115, 30);  
  
// 信息输入  
dataJLabel = new JLabel("明密文:");  
dataJLabel.setFont(new Font("黑体", Font.PLAIN, 18));  
dataField = new JTextField();  
dataField.setPreferredSize(new Dimension(150, 25));  
dataField.setFont(new Font("微软雅黑", Font.PLAIN, 18));  
  
// 密钥输入  
keyJLabel = new JLabel("密 钥：");  
keyJLabel.setFont(new Font("黑体", Font.PLAIN, 18));  
keyJPasswordField = new JPasswordField();  
keyJPasswordField.setEchoChar('*');  
keyJPasswordField.setPreferredSize(new Dimension(150, 25));  
keyJPasswordField.setFont(new Font("微软雅黑", Font.PLAIN, 18));  
  
// IV输入  
IVLabel = new JLabel("IV向量:");  
IVLabel.setFont(new Font("黑体", Font.PLAIN, 18));  
IVField = new JTextField();  
IVField.setPreferredSize(new Dimension(150, 25));  
IVField.setFont(new Font("微软雅黑", Font.PLAIN, 18));  
  
// 密钥可视按钮  
checkBox = new JCheckBox("显示密钥");  
checkBox.setSize(new Dimension(70, 35));  
checkBox.setFont(new Font("黑体", Font.TYPE1_FONT, 21));  
checkBox.addItemListener(new ItemListener() {  
public void itemStateChanged(ItemEvent e) {  
if (e.getStateChange() == ItemEvent.SELECTED) {  
keyJPasswordField.setEchoChar((char) 0);  
} else {  
keyJPasswordField.setEchoChar('*');  
}  
}  
});  
checkBox.setBounds(650, 225, 130, 70);  
  
// 结果显示  
resultJLabel = new JLabel("结果：");  
resultJLabel.setFont(new Font("黑体", Font.PLAIN, 18));  
resulTextArea = new JTextArea();  
resulTextArea.setPreferredSize(new Dimension(180, 80));  
resulTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 18));  
resulTextArea.setEditable(false);  
  
// 加/解密按钮  
button1 = new JButton();  
button1.setText("加/解密");  
button1.setFont(new Font("黑体", Font.PLAIN, 19));  
button1.setSize(new Dimension(100, 50));  
  
// 加/解密按钮事件  
button1.addActionListener(new ActionListener() {  
public void actionPerformed(ActionEvent e) {  
if (encryptButton.isSelected()) {  
// 获取明文  
String dataInput = dataField.getText().trim();  
String keyInput = new String(keyJPasswordField.getPassword());  
IVString = IVField.getText().trim();  
if (dataField.getText().trim().equals("")) {  
warning.showMessageDialog(frame, "明文不能为空！", "警告", JOptionPane.WARNING_MESSAGE);  
} else if (!isValidBinaryKey16(keyInput)) {  
warning.showMessageDialog(frame, "请输入16位的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);  
} else if (!isValidBinaryKey16(IVString)) {  
warning.showMessageDialog(frame, "请输入16位的二进制IV向量！", "警告", JOptionPane.WARNING_MESSAGE);  
} else {  
// 获取密钥  
keyString = new String(keyJPasswordField.getPassword());  
resultString = SAES.CBC_encrypt(dataInput, keyString, IVString);  
resulTextArea.setText(resultString);  
}  
} else if (decryptButton.isSelected()) {  
// 获取密文  
String dataInput = dataField.getText().trim();  
String keyInput = new String(keyJPasswordField.getPassword());  
IVString = IVField.getText().trim();  
if (dataField.getText().trim().equals("")) {  
warning.showMessageDialog(frame, "明文不能为空！", "警告", JOptionPane.WARNING_MESSAGE);  
} else if (!isValidBinaryKey16(keyInput)) {  
warning.showMessageDialog(frame, "请输入16位的二进制密钥！", "警告", JOptionPane.WARNING_MESSAGE);  
} else if (!isValidBinaryKey16(IVString)) {  
warning.showMessageDialog(frame, "请输入16位的二进制IV向量！", "警告", JOptionPane.WARNING_MESSAGE);  
}else {  
// 获取密钥  
keyString = new String(keyJPasswordField.getPassword());  
IVString = IVField.getText();  
resultString = SAES.CBC_decrypt(dataInput, keyString, IVString);  
resulTextArea.setText(resultString);  
}  
}  
}  
  
private boolean isValidBinaryKey16(String input) {  
  
return input.matches("[01]{16}");  
}  
});  
  
button1.setBounds(265, 500, 160, 40);  
  
// 返回按钮  
button2 = new JButton("返回");  
button2.setFont(new Font("黑体", Font.PLAIN, 19));  
button2.setSize(new Dimension(100, 50));  
button2.addActionListener(new ActionListener() {  
public void actionPerformed(ActionEvent e) {  
UI uI = new UI();  
frame.dispose();  
}  
});  
button2.setBounds(465, 500, 160, 40);  
  
// 布置各个组件  
box_H_01.add(dataJLabel);  
box_H_01.add(Box.createHorizontalStrut(5));  
box_H_01.add(dataField);  
  
box_H_02.add(keyJLabel);  
box_H_02.add(Box.createHorizontalStrut(5));  
box_H_02.add(keyJPasswordField);  
  
box_H_04.add(IVLabel);  
box_H_04.add(Box.createHorizontalStrut(5));  
box_H_04.add(IVField);  
  
box_H_03.add(resultJLabel);  
box_H_03.add(Box.createHorizontalStrut(5));  
box_H_03.add(resulTextArea);  
  
box_V.add(box_H_01);  
box_V.add(Box.createVerticalStrut(25));  
box_V.add(box_H_02);  
box_V.add(Box.createVerticalStrut(30));  
box_V.add(box_H_04);  
box_V.add(Box.createVerticalStrut(30));  
box_V.add(box_H_03);  
box_V.setBounds(200, 170, 450, 300);  
  
frame.add(welcomeLabel);  
frame.add(box_V);  
frame.add(checkBox);  
frame.add(button1);  
frame.add(button2);  
frame.add(encryptButton);  
frame.add(decryptButton);  
frame.setVisible(true);  
}  
}

```
#### 4、 中间相遇攻击
```java
(crack.java)

import java.util.ArrayList;  
import java.util.Scanner;  
  
public class crack {  
public static void main(String[] args) {  
System.out.println("已知明密文对的数量");  
Scanner scan = new Scanner(System.in);  
int number = scan.nextInt();// 记录明密文对数量  
String[] plainTextArr = new String[number];// 记录明文  
String[] cipherTextArr = new String[number];// 记录密文  
ArrayList<ArrayList<String>> keyArrays = new ArrayList<>(); // 记录可能的密钥  
  
int inputcount = 0;  
while (inputcount < number) {  
System.out.print("请输入第" + (inputcount + 1) + "条明文");  
plainTextArr[inputcount] = scan.next();  
System.out.print("请输入第" + (inputcount + 1) + "条密文");  
cipherTextArr[inputcount] = scan.next();  
inputcount++;  
}  
  
// 获取当前时间的毫秒数，作为起始时间  
long startTime = System.currentTimeMillis();  
int num = (int) Math.pow(2, 16); // 16bit的密钥有2^16种可能  
for (int i = 0; i < number; i++) {  
int keynum = 0;// 记录密钥数量  
String[] midText = new String[num]; // 可能的Mid-text  
ArrayList<String> keyArray = new ArrayList<>();  
for (int j = 0; j < num; j++) {  
String binary = Integer.toBinaryString(j);  
int length = binary.length();  
// 补位至16bit  
while (length < 16) {  
binary = "0" + binary;  
length++;  
}  
midText[j] = SAES.encrypt(plainTextArr[i], binary); // 得到所有可能的Mid-text结果  
  
}  
  
for (int j = 0; j < num; j++) {  
String binary = Integer.toBinaryString(j);  
int length = binary.length();  
// 补位至16bit  
while (length < 16) {  
binary = "0" + binary;  
length++;  
}  
String test = SAES.decrypt(cipherTextArr[i], binary);  
  
for (int k = 0; k < num; k++) {  
if (test.equals(midText[k])) { // 破解出一个密钥  
String binary_k = Integer.toBinaryString(k);  
int length_k = binary_k.length();  
// 补位至16bit  
while (length_k < 16) {  
binary_k = "0" + binary_k;  
length_k++;  
}  
keyArray.add(binary_k + binary);  
}  
}  
}  
keyArrays.add(keyArray);  
}  
  
// 获取当前时间的毫秒数，作为结束时间  
long endTime = System.currentTimeMillis();  
// 计算代码块的运行时间（毫秒）  
long executionTime = endTime - startTime;  
// 查找重复的密钥  
ArrayList<String> sameKeyArray = new ArrayList<>();  
  
// 遍历每一个ArrayList  
// 如果keyArrays不为空且包含至少一个ArrayList  
if (!keyArrays.isEmpty() && keyArrays.get(0) != null && !keyArrays.get(0).isEmpty()) {  
// 选择第一个ArrayList作为参考  
ArrayList<String> referenceList = keyArrays.get(0);  
  
// 遍历参考ArrayList的每个字符串  
for (String str : referenceList) {  
boolean existsInAllLists = true;  
  
// 遍历其余的ArrayList  
for (int i = 1; i < keyArrays.size(); i++) {  
ArrayList<String> currentList = keyArrays.get(i);  
if (!currentList.contains(str)) {  
existsInAllLists = false;  
break; // 如果不在某一个ArrayList中，跳出内循环  
}  
}  
  
// 如果字符串存在于所有ArrayList中，添加到sameKeyArray  
if (existsInAllLists) {  
sameKeyArray.add(str);  
}  
}  
}  
  
System.out.println("可能的密钥如下：");  
if (sameKeyArray.size() == 0) {  
System.out.println("没有找到共同的密钥");  
} else {  
for (String key : sameKeyArray) {  
System.out.println("密钥：" + key);  
}  
}  
System.out.println("起始时间为：");  
System.out.println(startTime);  
System.out.println("终止时间为：");  
System.out.println(endTime);  
System.out.println("运行时间为：");  
System.out.println(executionTime + " 毫秒");  
  
scan.close();  
}  
}
```

