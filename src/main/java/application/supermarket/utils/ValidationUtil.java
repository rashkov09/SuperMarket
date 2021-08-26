package application.supermarket.utils;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {
    <T> boolean isValid(T entity);

    <T> String getViolations(T entity);


}
