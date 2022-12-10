import java.awt.BorderLayout;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//constructor 
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

    //init all controller logic addActionListener for JButton or JMenuItem
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


    // Logic for addRow init a window for user to input data
    private void addRow(){
        view.newWindow("add", "add a row");
    }

    // add a row after user click done 
    private void done(){
        //get user input
        String name = view.getTf_name().getText();
        String price = view.getTf_price().getText();
        String cat = view.getTf_cat().getText();
        String date = view.getTf_date().getText();

        //modify model and change view
        model.addRow(new Object[] {name,Double.valueOf(price),cat,date});
        view.getTable().setModel(new DefaultTableModel(model.getRows(),model.getCol()));
    }
    
    // update a row after user click update
    private void update(){

        //get user input
        String name = view.getTf_name().getText();
        String price = view.getTf_price().getText();
        String cat = view.getTf_cat().getText();
        String date = view.getTf_date().getText();

        //modify model and change view
        model.updateRow(selected_row,new Object[] {name,Double.valueOf(price),cat,date});
        view.getTable().setModel(new DefaultTableModel(model.getRows(),model.getCol()));
    }

    // delete a selected row
    private void deleteRow(){
        //get selected row index
        int index = view.getTable().getSelectedRow();
        System.out.println("selected row"+index);// test if we get the right index

        //modify model and change view
        model.removeRow(index);
        view.getTable().setModel(new DefaultTableModel(model.getRows(),model.getCol()));
    }

    //init a window for user input
    private void updateRow(){
        this.selected_row = view.getTable().getSelectedRow();
        view.newWindow("update", "update a row");
    }

    // read the data from test.csv
    private void read() {
        //modify model and change view
        model.read(model.read_csv());
        view.getTable().setModel(new DefaultTableModel(model.getRows(),model.getCol()));
    }

    // write the data to output.csv
    private void write(){
        System.out.println("success");
        model.write_csv();
    }

    // init a window for data summary
    private void summary(){
        JFrame frame = view.summaryWindow();
        LinkedList<Double> list = model.summarize();
        DefaultTableModel tm = new DefaultTableModel(
            new Object[][] {{Double.valueOf(1000),// assume the Budget is 1000
                            Double.valueOf(1000-list.get(4)), // Remaining
                            Double.valueOf(list.get(0)), //Food
                            Double.valueOf(list.get(1)), //Electronics
                            Double.valueOf(list.get(2)), //Transportation
                            Double.valueOf(list.get(3)), //Other
                            }},
            new Object[] {"Budget","Remaining","Food","Electronics","Transportation","Other"}//column name
        );
        JTable t = new JTable(tm);
        frame.add(new JScrollPane(t),BorderLayout.CENTER);
        frame.pack();
    }


}
