package script.world.pvptournament.prize;

import java.util.List;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.model.item.Item;
import com.palidino.util.Utils;

public class DefaultPrize implements Prize {
    private boolean rewardBonds;

    public DefaultPrize(boolean rewardBonds) {
        this.rewardBonds = rewardBonds;
    }

    @Override
    public List<Item> getItems(int position) {
        List<Item> items = null;
        switch (position) {
        case 0:
            items = Utils.toList(new Item(ItemId.COINS, 8_000_000));
            break;
        case 1:
            items = Utils.toList(new Item(ItemId.COINS, 4_000_000));
            break;
        case 2:
            items = Utils.toList(new Item(ItemId.COINS, 2_000_000));
            break;
        case 3:
            items = Utils.toList(new Item(ItemId.COINS, 1_000_000));
            break;
        }
        if (items != null && rewardBonds) {
            items.add(new Item(ItemId.BOND_32318, 50 / (position + 1)));
        }
        return items;
    }

    @Override
    public String getMessage() {
        return "Prizes include 15M" + (rewardBonds ? " and 100 bonds" : "") + ".";
    }
}