package gui;

import app.AppCore;
import lombok.Data;
import observer.Notification;
import observer.Subscriber;
import observer.enums.NotificationCode;
import resource.implementation.InformationResource;
import javax.swing.JButton;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Data
public class MainFrame extends JFrame implements Subscriber {

    private static MainFrame instance = null;

    private AppCore appCore;
    private JTable jTable;
    private JScrollPane jsp;
    private JPanel bottomStatus;
    private JTextArea area;
    private JButton button=new JButton("Enter");


    private MainFrame() {

    }

    public static MainFrame getInstance(){
        if (instance==null){
            instance=new MainFrame();
            instance.initialise();
        }
        return instance;
    }


    private void initialise() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jTable = new JTable();
        jTable.setPreferredScrollableViewportSize(new Dimension(700, 400));
        jTable.setFillsViewportHeight(true);
        this.add(new JScrollPane(jTable));

        area=new JTextArea();
     //   button=new JButton("Enter");
        area.setColumns(50);
        area.setRows(5);
        this.add(area,BorderLayout.BEFORE_FIRST_LINE);
        this.add(button,BorderLayout.AFTER_LAST_LINE);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);


        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String s=area.getText();
                if (appCore.getValidator().validate(s))
                    appCore.getCompailer().compaile(s);
            }
        });
    }



    public void setAppCore(AppCore appCore) {
        this.appCore = appCore;
        this.appCore.addSubscriber(this);
        this.jTable.setModel(appCore.getTableModel());
    }


    @Override
    public void update(Notification notification) {

        if (notification.getCode() == NotificationCode.RESOURCE_LOADED){
            System.out.println((InformationResource)notification.getData());
        }

        else{
            jTable.setModel((TableModel) notification.getData());
        }

    }
}
