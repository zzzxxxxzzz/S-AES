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
