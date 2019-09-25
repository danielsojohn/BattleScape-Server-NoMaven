package script.world.pvptournament.prize;

import com.palidino.util.Utils;

public class OsrsPrize implements Prize {
    private int value;

    public OsrsPrize(int value) {
        this.value = value;
    }

    @Override
    public String getMessage() {
        return "Prizes include " + Utils.abbreviateNumber(value) + " OSRS coins.";
    }
}
