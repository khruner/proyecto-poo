package com.mycompany.tacotitosv2;

import com.mycompany.tacotitosv2.Controladores.ControladorIngrediente;
import com.mycompany.tacotitosv2.Modelos.IngredienteDAO;
import com.mycompany.tacotitosv2.Vistas.ingredientesView;
import com.mycompany.tacotitosv2.Vistas.menuView;
import com.mycompany.tacotitosv2.Vistas.tipoIngredientesView;
import java.sql.SQLException;

public class TacotitosV2 {

    public static void main(String[] args) throws SQLException {
        //tipoIngrediente t = new tipoIngrediente("Salsa", 5);
        //Ingrediente e = new Ingrediente("nombre",145, "Salsa", 5);
        try {
                    IngredienteDAO ing = IngredienteDAO.getInstance();
        } catch (Exception e) {
        }
        ingredientesView v=new ingredientesView();
        ControladorIngrediente con=new ControladorIngrediente(v);
        v.setVisible(true);
        v.setLocationRelativeTo(null);
    }
}
