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
