package christmas.EventPlannerIO;
import static christmas.EventPlannerIO.InputViewErrorMessages.*;

import java.util.IllegalFormatWidthException;

public class InputViewValidator {

    public InputViewValidator() {
    }
    private final int ONE = 1;
    private final int THIRTYONE = 31;

    public int validateDate(String date){
        if (date.matches("^[\\D]*$")) {
            throw new IllegalStateException(OUTPUT_ERROR.getErrorMessage()
                    + OUTPUT_ERROR_SPACE.getErrorMessage()
                    + OUTPUT_ERROR_OUTOFRANGE.getErrorMessage());

        }
        int dateCopy = Integer.parseInt(date);
        if ( ONE < dateCopy && dateCopy < THIRTYONE ) {
            throw new IllegalArgumentException(OUTPUT_ERROR.getErrorMessage()
                    + OUTPUT_ERROR_SPACE.getErrorMessage()
                    + OUTPUT_ERROR_OUTOFRANGE.getErrorMessage());
        }
        return dateCopy;
    }

}
