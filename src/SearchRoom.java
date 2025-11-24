import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.concurrent.Callable;

public class SearchRoom extends JFrame implements ActionListener {
    JCheckBox checkBox;
    Choice choice;
    JTable table;
    JButton add,back;

    SearchRoom(){

        JPanel panel = new JPanel();
        panel.setBackground(new Color(3,45,48));
        panel.setBounds(5,5,690,490);
        panel.setLayout(null);
        add(panel);

        JLabel searchRoom = new JLabel("Search For Room");
        searchRoom.setBounds(250,11,186,31);
        searchRoom.setForeground(Color.WHITE);
        searchRoom.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(searchRoom);


        JLabel Rbt = new JLabel("Room Bed Type:");
        Rbt.setBounds(50,73,120,20);
        Rbt.setForeground(Color.WHITE);
        Rbt.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(Rbt);

        JLabel Rn = new JLabel("Room Number");
        Rn.setBounds(23,162,150,20);
        Rn.setForeground(Color.WHITE);
        Rn.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(Rn);

        JLabel avilable = new JLabel("Availabilty");
        avilable.setBounds(175,162,150,20);
        avilable.setForeground(Color.WHITE);
        avilable.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(avilable);



        JLabel price = new JLabel("Price");
        price.setBounds(458,162,150,20);
        price.setForeground(Color.WHITE);
        price.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(price);


        JLabel Bt = new JLabel("Bed Type");
        Bt.setBounds(580,162,150,20);
        Bt.setForeground(Color.WHITE);
        Bt.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(Bt);


        JLabel CS = new JLabel("Cleaning Status");
        CS.setBounds(306,162,150,20);
        CS.setForeground(Color.WHITE);
        CS.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(CS);


        checkBox = new JCheckBox("Only Display Available");
        checkBox.setBounds(400,69,205,23);
        checkBox.setForeground(Color.WHITE);
        checkBox.setBackground(new Color(3,45,48));
        panel.add(checkBox);

        choice = new Choice();
        choice.add("Single Bed");
        choice.add("Double Bed");
        choice.setBounds(170,70,120,20);
        panel.add(choice);

        table = new JTable();
        table.setBounds(0,187,700,150);
        table.setBackground(new Color(3,45,48));
        table.setForeground(Color.WHITE);
        panel.add(table);

        try{
            con c = new con();
            String q = "select * from room ";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

        }catch (Exception e){
            e.printStackTrace();
        }


        add = new JButton("Search");
        add.setBounds(200,400,120,30);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        panel.add(add);

        back = new JButton("Back");
        back.setBounds(380,400,120,30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        panel.add(back);

        setUndecorated(true);
        setLocation(500,200);
        setLayout(null);
        setSize(700,500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==add){
            String Q = "select * from Room where bed_type = '"+choice.getSelectedItem()+"'";
            String Q1 =  "select * from Room where availability = 'Available' And bed_type = '"+choice.getSelectedItem()+"'";
            try{
                con c = new con();
                ResultSet resultSet = c.statement.executeQuery(Q);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));

                if (checkBox.isSelected()){
                    ResultSet resultSet1 = c.statement.executeQuery(Q1);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet1));

                }

            }catch (Exception E){
                E.printStackTrace();
            }
        } else {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}
