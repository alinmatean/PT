package tuc.tp.tema3.bll.validators;

/**
 * interfata pentru validatoare
 * @param <T>
 */
public interface Validator<T> {
    public void validate(T t);
}
