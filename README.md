# S-AES小组作业
## 一 、作业任务

### 第一关：基本测试

**GUI用户交互界面首页**：用户选择加密或解密操作。

![1-1](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/1-1.png)

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

参考其他同学小组中的测试内容，可以验证我们的加密以及解密过程可以得到与其他小组同样的结果。
（参考小组的github链接：[https://github.com/dori0512/S-AES-by-qt](https://github.com/dori0512/S-AES-by-qt);[https://github.com/Mercurius14/S-AES-HW](https://github.com/Mercurius14/S-AES-HW))

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



### 第三关：扩展功能

我们对该项目进行了拓展，实现了以ASCII码为明文输入和密文输出的功能。下面是加密演示：

明文：zxzxzxzx

密钥：0000000000000000

 密文：rÂrÂrÂrÂ

![[3-1]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/3-1.png)

下面是相应的解密演示：

![[3-2]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/3-2.png)

解密成功，得到相应的明文，第三关通过。

### 第四关：暴力破解

#### 4-1 双重加密

我们实现了将S-AES算法通过双重加密进行扩展，分组长度仍然为16bits，密钥长度扩展到32bits。
```java
//双重加密核心代码：
public static String double_Encrypt(String plain_text,String key1,String key2){ 
 
String text1_cipher = SAES.encrypt(plain_text,key1);  
String text2_cipher = SAES.decrypt(text1_cipher,key2);  
  
return text2_cipher;  
}  
  
public static String double_Decrypt(String cipher_text,String key1,String key2){  

String text1_decrypt= SAES.encrypt(cipher_text,key2);  
String text2_decrypt = SAES.decrypt(text1_decrypt,key1);  
  
return text2_decrypt;  
}
```
下面是加解密演示：

对明文进行加密：

明文：1010000011110101

密钥1：1111111111111111

密钥2：0000000000000000

生成密文：1001011001110011

![[4-1-1]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/4-1-1.png)

对密文进行解密：

![[4-1-2]](https://github.com/zzzxxxxzzz/S-AES/blob/main/image/4-1-2.png)


解密结果与明文完全一致。



#### 4-2 中间相遇攻击
#### 4-3 三重加密



### 第五关：封闭测试
