package tuc.tp.tema3.bll.validators;

import tuc.tp.tema3.model.Client;

/**
 * clasa care implementeaza interfata Validator
 */
public class ClientNameAddressValidator implements Validator<Client>{
    private static final int MAX = 45;

    /**
     * verifica lngimea numelui unui client
     * @param client
     */
    @Override
    public void validate(Client client) {
        if(client.getName().length() > MAX || client.getAddress().length() > 45){
            throw new IllegalArgumentException("Client name length or client address length is not respected!");
        }
    }
}
