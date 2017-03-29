package enums;

/**
 * Created by dorot on 29/03/2017.
 */
public enum Buttons {

    NEXT("Další"),
    BACK("Zpět"),
    SUBMIT("Odeslat");

    public final String name;

    Buttons(String value) {
        name = value;
    }

    public String getName() {
        return name;
    }
}
