package tuc.tp.tema3.start;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * clasa corespunzatoare pentru extragrea proprietatilor
 */
public class Reflection {
    /**
     * extragrea proprietatilor unui obiect
     * @param object
     * @return ArrayList de obiecte
     */
    public static ArrayList<Object> retrieveProperties(Object object) {
        ArrayList<Object> objects = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true); // set modifier to public
            Object value;
            try {
                value = field.get(object);
                objects.add(value);
                System.out.println(field.getName() + " = " + value);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return objects;
    }

    /**
     * crearea unui tabel corespunzator pentru a fi afisat
     * @param objects
     * @return un JTable din lista de obiecte
     * @throws IllegalAccessException
     */
    public static JTable createTable(List<? extends Object> objects) throws IllegalAccessException{
        ArrayList<Object> rowData = new ArrayList<>();
        ArrayList<String> columns = new ArrayList<>();

        for(Field field: objects.get(0).getClass().getDeclaredFields()){
            field.setAccessible(true);
            columns.add(field.getName());
        }

        String[] columnNames = new String[columns.size()];
        columnNames = columns.toArray(columnNames);
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for(Object obj: objects){
            rowData.clear();
            for(Field field: obj.getClass().getDeclaredFields()){
                field.setAccessible(true);
                rowData.add(field.get(obj));
            }
            Object[] dataObjects = new Object[rowData.size()];
            dataObjects = rowData.toArray(dataObjects);
            tableModel.addRow(dataObjects);
        }
        return new JTable(tableModel);
    }

}
