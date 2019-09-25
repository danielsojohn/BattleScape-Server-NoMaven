package script.world.pvptournament.prize;

import java.util.ArrayList;
import java.util.List;
import com.palidino.osrs.model.item.Item;
import com.palidino.util.Utils;
import lombok.var;

public class CustomPrize implements Prize {
    private List<List<Item>> items =
            Utils.toList(new ArrayList<Item>(), new ArrayList<Item>(), new ArrayList<Item>(), new ArrayList<Item>());

    @Override
    public List<Item> getItems(int position) {
        if (position >= 0 && position < items.size()) {
            return items.get(position);
        }
        return null;
    }

    @Override
    public String getMessage() {
        var value = 0L;
        for (var itemGroup : items) {
            for (var item : itemGroup) {
                value += item.getDef().getConfiguredExchangePrice();
            }
        }
        return value > 0 ? "Prizes include " + Utils.abbreviateNumber(value) + "." : null;
    }

    @Override
    public boolean addItem(int position, Item item) {
        if (position < 0 || position >= items.size()) {
            return false;
        }
        items.get(position).add(item);
        return true;
    }
}
