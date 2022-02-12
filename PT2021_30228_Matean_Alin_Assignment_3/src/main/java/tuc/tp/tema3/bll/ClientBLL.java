package tuc.tp.tema3.bll;

import tuc.tp.tema3.bll.validators.ClientNameAddressValidator;
import tuc.tp.tema3.bll.validators.Validator;
import tuc.tp.tema3.dao.ClientDAO;
import tuc.tp.tema3.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**Business logic class
 * incapsuleaza operatiile care se fac asupra clientilor
 *
 */
public class ClientBLL {
    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientBLL(){
        validators = new ArrayList<Validator<Client>>();
        validators.add(new ClientNameAddressValidator());

        clientDAO = new ClientDAO();
    }

    /**
     * apeleaza metoda care insereaza un client
     * @param client
     * @throws IllegalAccessException
     */
    public void insertClient(Client client) throws IllegalAccessException{
        for (Validator<Client> validator: validators){
            validator.validate(client);
        }
        clientDAO.insert(client);
    }

    /**
     * apeleaza metoda care returneaza o lista cu fiecare linie din tabel
     * @return tot tabelul din baza de date
     */
    public List<Client> findAll(){
        return clientDAO.findAll();
    }

    /**
     * apeleaza metoda care sterge un client din baza de date
     * @param idClient
     */
    public void deleteClient(int idClient){
        if(idClient == 0){
            throw new NoSuchElementException("This client does not exists!");
        }
        clientDAO.delete(idClient);
    }

    /**
     * apeleaza metoda care face update in baza de date unui client
     * @param client
     * @throws IllegalAccessException
     */
    public void updateClient(Client client) throws IllegalAccessException{

        clientDAO.update(client);
    }

    /**
     * apeleaza metoda care extrage numele clientului
     * @param id
     * @return numele clientului cu id-ul trimis ca parametru
     */
    public String getName(int id){
        return clientDAO.findName(id);
    }
}
