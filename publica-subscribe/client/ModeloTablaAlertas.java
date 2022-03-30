/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author pablo
 */
public class ModeloTablaAlertas extends AbstractTableModel {
    
    //var
    private ArrayList<String []> valor;
    
    //constructor
    public ModeloTablaAlertas(){
        this.valor = new ArrayList<String []>();
    }
    
    //fucntions
    public int getColumnCount(){ return 3; }
    
    public int getRowCount() { return this.valor.size(); }
    
    @Override
    public String getColumnName(int col){
        switch(col){
            case 0: return "EMPRESA";
            case 1: return "OPERACION";
            case 2: return "VALOR";
            default: return "";
        }
    }
    
    public Object getValueAt(int row, int col){
        return this.valor.get(row)[col];
    }
    
    public void setData(String msg){
        
        if(msg.equals("")){
            this.valor.clear();
            fireTableDataChanged();
            return;
        }
        
        String [] str = msg.split("\\n");
        this.valor.clear();
        for(String s : str){
            this.valor.add(s.split("\\|"));
            System.out.println(s);
        }
        fireTableDataChanged();
    }
    
}
