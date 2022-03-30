package client;

import java.util.ArrayList;
import javax.swing.table.*;

public class ModeloTablaDatos extends AbstractTableModel {
    
    //var
    private ArrayList<String []> valor;
    
    //constructor
    public ModeloTablaDatos(){
        this.valor = new ArrayList<String []>();
    }
    
    //fucntions
    public int getColumnCount(){ return 2; }
    
    public int getRowCount() { return this.valor.size(); }
    
    @Override
    public String getColumnName(int col){
        if(col == 0)
            return "EMPRESA";
        else
            return "VALOR";
    }
    
    public Object getValueAt(int row, int col){
        return this.valor.get(row)[col];
    }
    
    public void setData(String msg){
        String [] str = msg.split("\\n");
        this.valor.clear();
        for(String s : str)
            this.valor.add(s.split("\\|"));
        fireTableDataChanged();
    }
    
    public Boolean containsTable(String empresa){
        Boolean check = false;
        for(String[] s : this.valor){
            if(empresa.equals(s[0]))
                check = true;
        }
        return check;
    }
    
}
