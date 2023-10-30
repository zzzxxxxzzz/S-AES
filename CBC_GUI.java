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
