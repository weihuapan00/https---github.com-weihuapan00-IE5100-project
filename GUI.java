import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUI {

    //init all Component here
    private JFrame frame;
    private JPanel tool_panel;
    private JScrollPane  table_panel;
    private JTable table;

    private JButton done = new JButton("Done");
    private JButton updateButton = new JButton("Update");
    private DefaultTableModel tableModel;

    private JButton add;
    private JButton update;
    private JButton delete;
    private JMenuItem write_csv;
    private JMenuItem read_csv;
    private JMenuBar menu;



    private JTextField tf_name = new JTextField();
    private JTextField tf_price = new JTextField();
    private JTextField tf_cat = new JTextField();
    private JTextField tf_date = new JTextField();
    private JMenuItem summary;
    


    
    //constructor
    public GUI(){
        frame = new JFrame("Expense Tracker");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800,800));
        tool_panel = new JPanel();
        tool_panel.setPreferredSize(new Dimension(800,50));
        add = new JButton("Add");
        delete = new JButton("Delete");
        update = new JButton("Update");
        tool_panel.add(add);
        tool_panel.add(update);
        tool_panel.add(delete);

        expenseDao m = new expenseDao("test.csv");
        tableModel = new DefaultTableModel(m.getRows(),m.getCol());
        table = new JTable(tableModel);
        table_panel = new JScrollPane(table,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        

        frame.add(tool_panel,BorderLayout.SOUTH);
        frame.add(table_panel,BorderLayout.CENTER);
        menu = createJMenuBar();
        frame.setJMenuBar(menu);
        frame.pack();
        frame.setVisible(true);
    }

    // method to create JMenuBar and add JMenuItem to it
    public JMenuBar createJMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        write_csv = new JMenuItem("write to csv file");
        read_csv = new JMenuItem("read csv file");
        summary = new JMenuItem("summary");
        menuBar.add(write_csv);
        menuBar.add(read_csv);
        menuBar.add(summary);
        return menuBar;
    }

    // new window method
    public JFrame newWindow(String type,String title){
        JLabel label_name = new JLabel("Item name");
        JLabel label_price = new JLabel("Price");
        JLabel label_cat = new JLabel("Category");
        JLabel label_date = new JLabel("Date");

        //set the size of the component
        done.setPreferredSize(new Dimension(200,50));
        updateButton.setPreferredSize(new Dimension(200,50));
        tf_name.setPreferredSize(new Dimension(200,50));
        tf_price.setPreferredSize(new Dimension(200,50));
        tf_cat.setPreferredSize(new Dimension(200,50));
        tf_date.setPreferredSize(new Dimension(200,50));
        label_name.setPreferredSize(new Dimension(200,50));
        label_price.setPreferredSize(new Dimension(200,50));
        label_cat.setPreferredSize(new Dimension(200,50));
        label_date.setPreferredSize(new Dimension(200,50));

        JFrame new_window = new JFrame(title);
        new_window.setLayout(new FlowLayout());
        new_window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        new_window.setPreferredSize(new Dimension(400,600));

        //add components
        new_window.add(label_name);
        new_window.add(tf_name);
        new_window.add(label_price);
        new_window.add(tf_price);
        new_window.add(label_cat);
        new_window.add(tf_cat);
        new_window.add(label_date);
        new_window.add(tf_date);

        //add different button to the end ie. add for addRow update for updateRow
        if (type.equals("add")){
            new_window.add(done);
        }else{
            new_window.add(updateButton);
        }

        new_window.pack();
        new_window.setVisible(true);
        return new_window;

    }

    //getter for main frame
    public JFrame getFrame() {
        return frame;
    }

    //setter for frame
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    //getter for JScrollPane
    public JScrollPane getPanel() {
        return table_panel;
    }


    //setter for JScrollPane
    public void setPanel(JScrollPane table_panel) {
        this.table_panel = table_panel;
    }


    //getter for add button
    public JButton getAdd() {
        return add;
    }

    //setter for add button
    public void setAdd(JButton add) {
        this.add = add;
    }

    //getter for update btn
    public JButton getUpdate() {
        return update;
    }

    //setter for update btn
    public void setUpdate(JButton update) {
        this.update = update;
    }

    //setter for table 
    public void setTable(JTable table) {
        this.table = table;
    }

    //getter for table
    public JTable getTable() {
        return table;
    }

    //getter for tf_name
    public JTextField getTf_name() {
        return tf_name;
    }

    //getter for tf_price
    public JTextField getTf_price() {
        return tf_price;
    }

    //getter for tf_cat
    public JTextField getTf_cat() {
        return tf_cat;
    }

    //getter for tf_date
    public JTextField getTf_date() {
        return tf_date;
    }

    //getter for update btn
    public JButton getUpdateButton() {
        return updateButton;
    }

    //getter for JMenuItem write_csv
    public JMenuItem getWrite_csv() {
        return write_csv;
    }
    
    //getter for JMenuItem read_csv
    public JMenuItem getRead_csv() {
        return read_csv;
    }


    //getter for JMenuItem summary
    public JMenuItem getSummary() {
        return summary;
    }

    //getter for tableModel
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    //getter for done btn
    public JButton getDone() {
        return done;
    }

    // getter for delete btn
    public JButton getDelete() {
        return delete;
    }

    //init a summary window
    public JFrame summaryWindow(){
        JFrame new_window = new JFrame("summary");
        new_window.setLayout(new BorderLayout());
        new_window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        new_window.setPreferredSize(new Dimension(600,600));
        new_window.setVisible(true);
        new_window.pack();
        return new_window;
    }



   

}