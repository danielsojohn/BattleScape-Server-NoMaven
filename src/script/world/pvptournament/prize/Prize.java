package script.world.pvptournament.prize;

import java.util.List;
import com.palidino.osrs.model.item.Item;

public interface Prize {
    default List<Item> getItems(int position) {
        return null;
    }

    default String getMessage() {
        return null;
    }

    default boolean addItem(int position, Item item) {
        return false;
    }
}
