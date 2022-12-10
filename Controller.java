import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Controller {
    private expenseDao model;
    private GUI view;
    private int selected_row;

    public Controller(expenseDao m, GUI view){
        this.model = m;
        this.view = view;
    }

    // public void initView(){
        

    // }

    public void initController(){
        view.getAdd().addActionListener(e -> addRow());
        view.getUpdate().addActionListener(e -> updateRow());
        view.getDelete().addActionListener(e -> deleteRow());
        view.getDone().addActionListener(e -> done());
        view.getUpdateButton().addActionListener(e -> update());
        view.getRead_csv().addActionListener(e -> read());
        view.getWrite_csv().addActionListener(e -> write());
        view.getSummary().addActionListener(e -> summary());
    }


    private void addRow(){
        view.newWindow("add", "add a row");
    }

    private void done(){
        String name = view.getTf_name().getText();
        String price = view.getTf_price().getText();
        String cat = view.getTf_cat().getText();
        String date = view.getTf_date().getText();

        model.addRow(new Object[] {name,Double.valueOf(price),cat,date});
        view.getTable().setModel(new DefaultTableModel(model.getRows(),model.getCol()));
    }
    
    private void update(){
        String name = view.getTf_name().getText();
        String price = view.getTf_price().getText();
        String cat = view.getTf_cat().getText();
        String date = view.getTf_date().getText();

        model.updateRow(selected_row,new Object[] {name,Double.valueOf(price),cat,date});
        view.getTable().setModel(new DefaultTableModel(model.getRows(),model.getCol()));
    }

    private void deleteRow(){
        int index = view.getTable().getSelectedRow();
        System.out.println("selected row"+index);
        model.removeRow(index);
        view.getTable().setModel(new DefaultTableModel(model.getRows(),model.getCol()));
    }

    private void updateRow(){
        this.selected_row = view.getTable().getSelectedRow();
        view.newWindow("update", "update a row");

    }

    private void read() {
        model.read(model.read_csv());
        view.getTable().setModel(new DefaultTableModel(model.getRows(),model.getCol()));
    }

    private void write(){
        System.out.println("1");
        model.write_csv();
    }

    private void summary(){
        JFrame frame = view.summaryWindow();
        LinkedList<Double> list = model.summarize();
        DefaultTableModel tm = new DefaultTableModel(
            new Object[][] {{Double.valueOf(1000),
                            Double.valueOf(1000-list.get(4)),
                            Double.valueOf(list.get(0)),
                            Double.valueOf(list.get(1)),
                            Double.valueOf(list.get(2)),
                            Double.valueOf(list.get(3)),
                            }},
            new Object[] {"Budget","Reminding","Food","Electronics","Transportation","Other"}
        );
        JTable t = new JTable(tm);
        frame.add(new JScrollPane(t),BorderLayout.CENTER);
        frame.pack();
    }


}
