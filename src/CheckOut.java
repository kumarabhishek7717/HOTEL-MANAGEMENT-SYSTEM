import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class CheckOut extends JFrame {

    CheckOut(){
        JPanel panel = new JPanel();
        panel.setBounds(5,5,790,390);
        panel.setBackground(new Color(3,5,48));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Check-Out");
        label.setBounds(100,20,100,30);
        label.setFont(new Font("Tahoma",Font.PLAIN,20));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JLabel userId = new JLabel("Customer Id");
        userId.setBounds(30,80,100,30);
        userId.setFont(new Font("Tahoma",Font.BOLD,14));
        userId.setForeground(Color.WHITE);
        panel.add(userId);

        Choice Customer = new Choice();
        Customer.setBounds(200,80,150,25);
        panel.add(Customer);

        JLabel roomNum = new JLabel("Room Number");
        roomNum.setBounds(30,130,150,30);
        roomNum.setFont(new Font("Tahoma",Font.BOLD,14));
        roomNum.setForeground(Color.WHITE);
        panel.add(roomNum);

        JLabel labelRoomnumber = new JLabel();
        labelRoomnumber.setBounds(200,130,150,30);
        labelRoomnumber.setFont(new Font("Tahoma",Font.BOLD,14));
        labelRoomnumber.setForeground(Color.WHITE);
        panel.add(labelRoomnumber);

        JLabel checkintime = new JLabel("Check-In-Time");
        checkintime.setBounds(30,180,150,30);
        checkintime.setFont(new Font("Tahoma",Font.BOLD,14));
        checkintime.setForeground(Color.WHITE);
        panel.add(checkintime);

        JLabel labelcheckintime = new JLabel();
       labelcheckintime .setBounds(200,180,200,30);
        labelcheckintime.setFont(new Font("Tahoma",Font.BOLD,14));
        labelcheckintime.setForeground(Color.WHITE);
        panel.add(labelcheckintime);

        JLabel checkOuttime = new JLabel("Check-Out Time");
        checkOuttime.setBounds(30,230,150,30);
        checkOuttime.setFont(new Font("Tahoma",Font.BOLD,14));
        checkOuttime.setForeground(Color.WHITE);
        panel.add(checkOuttime);


        Date date = new Date();



        JLabel labelcheckOuttime = new JLabel(""+date);
        labelcheckOuttime.setBounds(200,230,200,30);
        labelcheckOuttime.setFont(new Font("Tahoma",Font.BOLD,14));
        labelcheckOuttime.setForeground(Color.WHITE);
        panel.add(labelcheckOuttime);

        try{
            con c = new con();
            ResultSet resultSet= c.statement.executeQuery("select * from customers");
            while (resultSet.next()){
                Customer.add(resultSet.getString("number"));
            }

        }catch (Exception E){
            E.printStackTrace();
        }

          JButton checkOut = new JButton("Check-Out");
        checkOut.setBounds(30,300,120,30);
        checkOut.setForeground(Color.WHITE);
        checkOut.setBackground(Color.BLACK);
        panel.add(checkOut);
        checkOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    con cv = new con();
                  cv.statement.executeUpdate("delete from customers where number = '"+Customer.getSelectedItem()+"'");
                    cv.statement.executeUpdate("update room set availability = 'Available' where roomnumber = '"+labelRoomnumber.getText()+"'");
                    JOptionPane.showMessageDialog(null,"Done");
                    setVisible(false);
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });


        JButton check = new JButton("Check");
        check.setBounds(300,300,120,30);
        check.setForeground(Color.WHITE);
        check.setBackground(Color.BLACK);
        panel.add(check);
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                con c = new con();
               try {

                   ResultSet resultSet = c.statement.executeQuery("select * from customers where number = '"+Customer.getSelectedItem()+"'");
                   while (resultSet.next()){
                       labelRoomnumber.setText(resultSet.getString("room"));
                       labelcheckintime.setText(resultSet.getString("checkintime"));

                   }

               }catch (Exception E){
                   E.printStackTrace();
               }
            }
        });


        JButton back = new JButton("Back");
        back.setBounds(170,300,120,30);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);
            }
        });



           setUndecorated(true);
        setLayout(null);
        setSize(800,400);
         setLocation(500,210);
         setVisible(true);
    }
    public static void main(String[] args) {
        new CheckOut();
    }
}
