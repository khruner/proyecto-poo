package com.mycompany.tacotitosv2.Controladores;

import com.mycompany.tacotitosv2.Modelos.Ingrediente;
import com.mycompany.tacotitosv2.Modelos.IngredienteDAO;
import com.mycompany.tacotitosv2.Vistas.ingredientesView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;


public class ControladorIngrediente implements ActionListener{
    Ingrediente ing = new Ingrediente();
    IngredienteDAO dao = new IngredienteDAO();
    ingredientesView vista = new ingredientesView();
    DefaultTableModel modelo = new DefaultTableModel();
    
    public ControladorIngrediente(ingredientesView vista){
        this.vista = vista;
        this.resetear();
        this.vista.btnActualizarTabla.addActionListener(this);
        this.vista.btnCrear.addActionListener(this);
        this.vista.btnBorrar.addActionListener(this);
        this.vista.btnActualizarIng.addActionListener(this);
        this.vista.btnResetearParametros.addActionListener(this);
    }
  
    
    void crear() {
        try {
            String nom = vista.txtNombre.getText();
            String stID = vista.txtId.getText().isEmpty()? "0" : vista.txtId.getText();
            System.out.println(stID);
            int id = Integer.parseInt(stID);
            System.out.println(id);
            int tipoId = vista.cbTipo.getItemAt(vista.cbTipo.getSelectedIndex()).getId();
            double prec = Double.parseDouble(vista.jtxtPrecio.getText());
            System.out.println(nom);
            System.out.println(tipoId);
            System.out.println(prec);
            //System.out.println(id);
            Ingrediente i = new Ingrediente(id, nom, prec, tipoId);
            this.dao.guardar(i);
            JOptionPane.showMessageDialog(vista,"Guardado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(vista,"Error al agregar.");

        }
        
    }
    public void mostrar(JTable tabla) {
        limpiarTabla();
        //centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Ingrediente> lista = dao.listar();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getPrecio();
            objeto[3] = lista.get(i).getTipo_ID();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }
    public void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }
    public void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
    public void resetear() {
        vista.txtId.setText("");
        vista.txtNombre.setText("");
        vista.jtxtPrecio.setText("");
    }
    public void actualizar(){
        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "Primero seleccione editar..");
        } else {
            try {
                int id = Integer.parseInt(vista.txtId.getText());
                String nom = vista.txtNombre.getText();
                Double precio = (Double)vista.jtxtPrecio.getValue();
                ing.setId(id);
                ing.setNombre(nom);
                ing.setPrecio(precio);
                dao.modificar(ing);
                JOptionPane.showMessageDialog(vista,"Actualizado exitosamente");
            } catch (SQLException ex) {
                Logger.getLogger(ControladorIngrediente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        limpiarTabla();
        mostrar(vista.tabla);
    }
    
    public void borrar(){
        int fila = vista.tabla.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "Seleccione una fila");
        } else {
            int id = Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
            dao.borrar(id);
            System.out.println("se borro el id"+id);
            JOptionPane.showMessageDialog(vista,"Eliminado exitosamente");
        }
        limpiarTabla();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.btnCrear){
            crear();
            mostrar(vista.tabla);
            resetear();
        }
        if (ae.getSource() == vista.btnResetearParametros){
            limpiarTabla();
            mostrar(vista.tabla);
            resetear();
        }
        if (ae.getSource() == vista.btnActualizarIng){
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1){
                JOptionPane.showMessageDialog(vista,"Seleccione una fila");
            }
            else {
                int id = Integer.parseInt((String)vista.tabla.getValueAt(fila, 0).toString());
                String nombre = (String)vista.tabla.getValueAt(fila, 1);
                Double precio = (Double)vista.tabla.getValueAt(fila, 2);
                vista.txtId.setText(""+id);
                vista.txtNombre.setText(nombre);
                vista.jtxtPrecio.setValue(precio);
                //actualizar();
            }
        }
        if (ae.getSource() == vista.btnActualizarTabla){
            mostrar(vista.tabla);
        }
        if (ae.getSource() == vista.btnBorrar){
            borrar();
            mostrar(vista.tabla);
            resetear();
        }
        if (ae.getSource() == vista.btnResetearParametros){
            resetear();
        }
    }
}
