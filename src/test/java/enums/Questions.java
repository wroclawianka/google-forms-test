package enums;

/**
 * Created by dorot on 29/03/2017.
 */
public enum Questions {

    CHECK_THAT("Check that say so"),
    SELECT_DATE("Select date at least five days from now"),
    MANDATORY("Check that this question is mandatory"),
    FAVOURITE_MOVIES("Create list of your favorite movies and series"),
    FAVOURITE_COLOR("Select favorite color"),
    DONE("It is done");


    public final String name;

    Questions(String value) {
        name = value;
    }

    public String getName() {
        return name;
    }
}
