import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUI {

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
    

    

   

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getDone() {
        return done;
    }

    public JButton getDelete() {
        return delete;
    }

    public void setDelete(JButton delete) {
        this.delete = delete;
    }

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

    public JFrame newWindow(String type,String title){
        JLabel label_name = new JLabel("Item name");
        JLabel label_price = new JLabel("Price");
        JLabel label_cat = new JLabel("Category");
        JLabel label_date = new JLabel("Date");

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
        new_window.add(label_name);
        new_window.add(tf_name);

        new_window.add(label_price);
        new_window.add(tf_price);

        new_window.add(label_cat);
        new_window.add(tf_cat);

        new_window.add(label_date);
        new_window.add(tf_date);
        if (type.equals("add")){
            new_window.add(done);
        }else{
            new_window.add(updateButton);
        }

        new_window.pack();
        new_window.setVisible(true);
        return new_window;

    }
    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JScrollPane getPanel() {
        return table_panel;
    }

    public void setPanel(JScrollPane table_panel) {
        this.table_panel = table_panel;
    }


    public JButton getAdd() {
        return add;
    }

    public void setAdd(JButton add) {
        this.add = add;
    }

    public JButton getUpdate() {
        return update;
    }

    public void setUpdate(JButton update) {
        this.update = update;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JTable getTable() {
        return table;
    }

    public JTextField getTf_name() {
        return tf_name;
    }

    public JTextField getTf_price() {
        return tf_price;
    }
   
    public JTextField getTf_cat() {
        return tf_cat;
    }

    public JTextField getTf_date() {
        return tf_date;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JMenuItem getWrite_csv() {
        return write_csv;
    }
    
    public JMenuItem getRead_csv() {
        return read_csv;
    }

    public JMenuBar getMenu() {
        return menu;
    }

    public JMenuItem getSummary() {
        return summary;
    }

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