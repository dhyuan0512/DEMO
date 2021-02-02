package com.example.demo.test.Jdk;

/**
 * @Author Great
 * @Date 2020/12/31 10:32
 * @Version 1.0
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class Login extends JFrame {
    public void changeContentPane(JPanel contentPanel, JPanel showPanel) {
//	    this.setContentPane(contentPane);
//	    this.revalidate();
        contentPanel.removeAll();
        contentPanel.add(showPanel);
        contentPanel.validate();
        contentPanel.repaint();
    }
    public Login() {

        setTitle("登录系统"); // 窗口标题
        setBounds(100, 100, 500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 关闭程序终止

        Container c = getContentPane();
        c.setLayout(new GridLayout(3, 1, 0, 0)); // 窗格布局

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));

//		添加边框标题
//		p1.setBorder(BorderFactory.createTitledBorder("面板1"));
//		p2.setBorder(BorderFactory.createTitledBorder("面板2"));
//		p3.setBorder(BorderFactory.createTitledBorder("面板3"));

        JLabel user = new JLabel("用户名 : ");
        user.setPreferredSize(new Dimension(80, 30));
        user.setFont(new Font(null, 1, 15));
        p1.add(user);

        JLabel pass = new JLabel("密　码 : ");
        p2.add(pass);
        pass.setPreferredSize(new Dimension(80, 30));

        JTextField t1 = new JTextField();
        p1.add(t1);
        t1.setPreferredSize(new Dimension(150, 30));

        JPasswordField t2=new JPasswordField();
        t2.setPreferredSize(new Dimension(150, 30));
        t2.setEchoChar('☺');
        p2.add(t2);

//		JScrollPane scroll = new JScrollPane();	//滚动条
//		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//水平滚动一直显示
//		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	//垂直滚动一直显示
//		scroll.setViewportView(p1);
//		c.add(scroll);

        JLabel ts1 = new JLabel();
        ts1.setPreferredSize(new Dimension(100, 30));
        ts1.setForeground(Color.red);
        p1.add(ts1);// 提示
        JLabel ts2 = new JLabel();
        ts2.setPreferredSize(new Dimension(100, 30));
        ts2.setForeground(Color.red);
        p2.add(ts2);// 提示

        JButton dbtn = new JButton("登录");
        dbtn.setPreferredSize(new Dimension(100, 40));
        p3.add(dbtn);

        JButton quit = new JButton("退出");
        quit.setPreferredSize(new Dimension(100, 40));
        p3.add(quit);
        p3.setBackground(Color.yellow);// 设置背景颜色

        c.add(p1);
        c.add(p2);
        c.add(p3);
        setVisible(true);

        quit.addMouseListener(new MouseAdapter() { // 匿名内部类，添加一个鼠标监听，鼠标适配器
            public void mouseClicked(MouseEvent e) {// 单击Clicked
                System.exit(0);
            }
        });
        dbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if (t1.getText().equals("root")&&t2.getText().equals("123456")) {
                    System.out.println("登录成功");
//                    new window();
                    setVisible(false);
                }else if (t1.getText().trim().equals("")) {
                    ts1.setText("用户名不能为空");
                }else if (t2.getText().trim().equals("")) {
                    ts2.setText("密码不能为空");
                }else {
                    ts1.setText("用户或密码错误");
//					System.out.println(t2.getText());
                }
            }
        });
        t1.addMouseListener(new MouseAdapter() { // 匿名内部类，添加一个鼠标监听，鼠标适配器
            public void mouseClicked(MouseEvent e) {// 单击Clicked
                if (t1.hasFocus()) {
                    ts1.setText(null);
                }
            }
        });
        t2.addMouseListener(new MouseAdapter() { // 匿名内部类，添加一个鼠标监听，鼠标适配器
            public void mouseClicked(MouseEvent e) {// 单击Clicked
                if (t2.hasFocus()) {
                    ts2.setText(null);
                }
            }
        });
        this.addMouseListener(new MouseAdapter() { // 匿名内部类，添加一个鼠标监听，鼠标适配器
            public void mouseClicked(MouseEvent e) {// 单击Clicked
                requestFocus();
            }
        });

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 27) { // 按下ESC退出程序
                    System.exit(0);
                }
            }
        });

//			public void keyTyped(KeyEvent e) {
//				char c = e.getKeyChar();
//				System.out.println("你按下了 " + c + "键");
//			}


//		addWindowFocusListener(new WindowFocusListener() {
//			@Override
//			public void windowGainedFocus(WindowEvent e) {
//				// TODO Auto-generated method stub
//				System.out.println("窗口获得了焦点！");
//			}
//
//			@Override
//			public void windowLostFocus(WindowEvent e) {
//				// TODO Auto-generated method stub
//				System.out.println("窗口失去了焦点！");
//			}
//		});
    }
}

public class Sales_system {
    public static void main(String[] args) {
        Login win = new Login();
    }
}
