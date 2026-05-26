package codsoft;
import javax.swing.*;
import java.awt.event.*;

public class ATMSystem extends JFrame implements ActionListener {

    JLabel t1, t2;

    JTextField f1;

    JTextArea a1;

    JScrollPane s1;

    JButton bt1, bt2, bt3, bt4;

    UserBank ub;

    public ATMSystem() {

        ub = new UserBank();

        setTitle("ATM SYSTEM");

        setSize(550, 500);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        t1 = new JLabel("Enter Amount");

        t1.setBounds(80, 40, 150, 30);

        add(t1);

        f1 = new JTextField();

        f1.setBounds(220, 40, 150, 30);

        add(f1);

        bt1 = new JButton("DEPOSIT");

        bt1.setBounds(30, 110, 120, 40);

        bt1.addActionListener(this);

        add(bt1);

        bt2 = new JButton("WITHDRAW");

        bt2.setBounds(160, 110, 120, 40);

        bt2.addActionListener(this);

        add(bt2);

        bt3 = new JButton("CHECK BALANCE");

        bt3.setBounds(290, 110, 160, 40);

        bt3.addActionListener(this);

        add(bt3);

        bt4 = new JButton("EXIT");

        bt4.setBounds(180, 180, 120, 40);

        bt4.addActionListener(this);

        add(bt4);

        t2 = new JLabel("Current Balance : Rs. "
                + ub.getBalance());

        t2.setBounds(140, 250, 250, 30);

        add(t2);

        a1 = new JTextArea();

        a1.setEditable(false);

        s1 = new JScrollPane(a1);

        s1.setBounds(70, 300, 380, 120);

        add(s1);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bt1)
        {

            try
            {

                if (f1.getText().equals(""))
                {

                    JOptionPane.showMessageDialog(this,
                            "Please enter amount");

                    return;
                }

                double money = Double.parseDouble(f1.getText());

                if (money <= 0)
                {

                    JOptionPane.showMessageDialog(this,
                            "Amount should be greater than 0");

                    f1.setText("");

                    return;
                }

                if (money > 100000)
                {

                    JOptionPane.showMessageDialog(this,
                            "Amount limit exceeded");

                    f1.setText("");

                    return;
                }

                ub.addMoney(money);

                t2.setText("Current Balance : Rs. "
                        + ub.getBalance());

                a1.setText("Amount Deposited : Rs. "
                        + money +
                        "\nUpdated Balance : Rs. "
                        + ub.getBalance());

                f1.setText("");
            }

            catch(NumberFormatException ex)
            {

                JOptionPane.showMessageDialog(this,
                        "Please enter numbers only");

                f1.setText("");
            }

            catch(NullPointerException ex)
            {

                JOptionPane.showMessageDialog(this,
                        "Null value found");
            }

            catch(Exception ex)
            {

                JOptionPane.showMessageDialog(this,
                        "Invalid Input");
            }
        }

        if (e.getSource() == bt2)
        {

            try
            {

                if (f1.getText().equals(""))
                {

                    JOptionPane.showMessageDialog(this,
                            "Please enter amount");

                    return;
                }

                double money = Double.parseDouble(f1.getText());

                if (money <= 0)
                {

                    JOptionPane.showMessageDialog(this,
                            "Amount should be greater than 0");

                    f1.setText("");

                    return;
                }

                if (money > 100000)
                {

                    JOptionPane.showMessageDialog(this,
                            "Amount limit exceeded");

                    f1.setText("");

                    return;
                }

                boolean x = ub.takeMoney(money);

                if (!x)
                {

                    JOptionPane.showMessageDialog(this,
                            "Insufficient Balance");

                    f1.setText("");

                    return;
                }

                t2.setText("Current Balance : Rs. "
                        + ub.getBalance());

                a1.setText("Amount Withdrawn : Rs. "
                        + money +
                        "\nUpdated Balance : Rs. "
                        + ub.getBalance());

                f1.setText("");
            }

            catch(NumberFormatException ex)
            {

                JOptionPane.showMessageDialog(this,
                        "Please enter numbers only");

                f1.setText("");
            }

            catch(NullPointerException ex)
            {

                JOptionPane.showMessageDialog(this,
                        "Null value found");
            }

            catch(Exception ex)
            {

                JOptionPane.showMessageDialog(this,
                        "Invalid Input");
            }
        }

        if (e.getSource() == bt3)
        {

            a1.setText("Available Balance : Rs. "
                    + ub.getBalance());
        }

        if (e.getSource() == bt4)
        {

            int y = JOptionPane.showConfirmDialog(this,
                    "Do you want to exit ?");

            if (y == JOptionPane.YES_OPTION)
            {

                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {

        new ATMSystem();
    }
}