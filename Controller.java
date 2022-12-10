import javax.swing.JTable;

public class Controller {
    private expenseDao model;
    private GUI view;

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
    }

    private void addRow(){
        // view.newWindow("add", "add a row");
        model.insertRow(3,new Object[]{"Apple",Double.valueOf(10),"food","12-9-2022"});
        model.fireTableRowsInserted(0, 0);
    }

    private void done(){
        String name = view.getTf_name().getText();
        String price = view.getTf_price().getText();
        String cat = view.getTf_cat().getText();
        String date = view.getTf_date().getText();

        model.addRow(new Object[] {name,Double.valueOf(price),cat,date});
        model.fireTableDataChanged();
    }

    private void deleteRow(){
        int index = view.getTable().getSelectedRow();
        System.out.println("selected row"+index);
        model.removeRow(index);
        model.fireTableRowsDeleted(index, index);
        
    }

    private void updateRow(){
        view.getTable().getModel().setValueAt("model", 0, 0);

    }

    private void visualize(){

    }


}
