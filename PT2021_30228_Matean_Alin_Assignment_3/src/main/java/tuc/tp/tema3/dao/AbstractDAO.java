package tuc.tp.tema3.dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import tuc.tp.tema3.connection.ConnectionFactory;

/**
 * clasa care realizeaza interogarile pe tabela din baza de date
 * @param <T>
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     * metoda care creeaza o instanta de tipul corespunzator dintr-un ResultSet
     * @param resultSet
     * @return lista de obiecte de tipul client/product/orders
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * creeaza interogare de tip SELECT pentru a extrage o linie din baza de date
     * @param field
     * @return String care contine interogarea
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " = ?");
        return sb.toString();
    }

    /**
     * creeaza interogarea de tip SELECT * dintr-un anumit tabel
     * @return String care contine interogarea
     */
    private String createFindAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     * creeaza interogarea de tip DELETE pentru a sterge o linie din baza de date
     * @return String care contine interogarea
     */
    private String createDeleteQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE  ");
        sb.append("id");
        sb.append(type.getSimpleName());
        sb.append("=?");
        return sb.toString();
    }

    /**
     * creeaza interogarea de tip INSERT pentru a insera un nou obiect de tipul corespunzator
     * @param object
     * @return String care contine interogarea
     * @throws IllegalAccessException
     */
    private String createInsertQuery(T object) throws IllegalAccessException{
        boolean firstField = true;
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" VALUES (");
        for(Field field: object.getClass().getDeclaredFields()){
            field.setAccessible(true);
            if(!firstField){
                sb.append(", ");
            }
            else{
                firstField = false;
            }
            if (field.get(object) instanceof Integer || field.get(object) instanceof Double) {
                sb.append(field.get(object));
            }
            else {
                sb.append("'");
                sb.append(field.get(object));
                sb.append("'");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * creeaza interogarea de tip UPDATE pentru un anumit tabel din baza de date
     * @param object
     * @return String care contine interogarea
     * @throws IllegalAccessException
     */
    private String createUpdateQuery(T object) throws IllegalAccessException{
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(type.getSimpleName()).append(" SET ");
        Field[] field = object.getClass().getDeclaredFields();
        field[0].setAccessible(true);
        for(int i = 1; i < field.length; i++){
            field[i].setAccessible(true);
            if( i >= 2){
                sb.append(", ");
            }
            sb.append(field[i].getName()).append("=");
            if(field[i].get(object) instanceof Integer || field[i].get(object) instanceof Double)
                sb.append(field[i].get(object));
            else
                sb.append("'").append(field[i].get(object)).append("'");
        }
        sb.append(" WHERE ").append("id").append(type.getSimpleName()).append("=").append(field[0].get(object)).toString();
        return sb.toString();
    }

    /**
     *extrage toate datele din tabelul corespunzator
     * @return lista cu fiecare linie din tabel
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFindAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * cauta un anumit obiect din baza de date
     * @param id
     * @return un obiect de tipul corespunzator
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id" + type.getSimpleName());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * insereaza un anumit obiect in tabelul corespunzator
     * @param t
     * @throws IllegalAccessException
     */
    public void insert(T t) throws IllegalAccessException{
        Connection connection = null;
        Statement statement = null;

        String query = createInsertQuery(t);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.execute(query);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * sterge un anumit obiect din tabel
     * @param id
     */
    public void delete(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            statement.execute();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * face update in tabelul corespunzator
     * @param t
     * @return
     * @throws IllegalAccessException
     */
    public T update(T t) throws IllegalAccessException{
        String query = createUpdateQuery(t);
        Connection connection = null;
        Statement statement = null;

        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return t;
    }
}

