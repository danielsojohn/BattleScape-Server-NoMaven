package script.packetdecoder.widget;

import com.palidino.osrs.io.ValueEnteredEvent;
import com.palidino.osrs.io.Widget;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.item.Item;
import com.palidino.osrs.model.player.Bank;
import com.palidino.osrs.model.player.Magic;
import com.palidino.osrs.model.player.Player;
import lombok.var;

public class BankWidget implements Widget {
    @Override
    public int[] getIds() {
        return new int[] {
            WidgetId.BANK, WidgetId.BANK_INVENTORY, WidgetId.DEPOSIT_BOX, WidgetId.BANK_PIN_SETTINGS
        };
    }

    @Override
    public void execute(Player player, int index, int widgetId, int childId, int slot, int itemId) {
        if (player.isLocked()) {
            return;
        }
        if (widgetId == WidgetId.BANK) {
            switch (childId) {
            case 11:
                if (index == 0) {
                    player.getBank().setViewingTab(slot - 10);
                    player.getGameEncoder().sendScript(101, 11);
                } else if (index == 5) {
                    player.getBank().collapseTab(slot - 10, 0);
                }
                break;
            case 13:
                var realSlot = player.getBank().getRealSlot(slot);
                if (index == 0) {
                    if (player.getBank().getDefaultQuantity() == Bank.DefaultQuantity.X) {
                        player.getBank().withdraw(itemId, realSlot, player.getBank().getLastEnteredX());
                    } else {
                        player.getBank().withdraw(itemId, realSlot, player.getBank().getDefaultQuantity().quantity);
                    }
                } else if (index == 1) {
                    player.getBank().withdraw(itemId, realSlot, 1);
                } else if (index == 2) {
                    player.getBank().withdraw(itemId, realSlot, 5);
                } else if (index == 3) {
                    player.getBank().withdraw(itemId, realSlot, 10);
                } else if (index == 4) {
                    player.getBank().withdraw(itemId, realSlot, player.getBank().getLastEnteredX());
                } else if (index == 5) {
                    player.getGameEncoder().sendEnterAmount(new ValueEnteredEvent.IntegerEvent() {
                        @Override
                        public void execute(int value) {
                            player.getBank().setLastEnteredX(value);
                            player.getBank().withdraw(itemId, realSlot, value);
                        }
                    });
                } else if (index == 6) {
                    player.getBank().withdraw(itemId, realSlot, Item.MAX_AMOUNT);
                } else if (index == 7) {
                    var item = player.getBank().getItem(realSlot);
                    if (item != null) {
                        player.getBank().withdraw(itemId, realSlot, item.getAmount() - 1);
                    }
                } else if (index == 8) {
                    player.getBank().withdraw(itemId, realSlot, -1);
                }
                break;
            case 17:
                player.getBank().setInsertMode(false);
                break;
            case 19:
                player.getBank().setInsertMode(true);
                break;
            case 22:
                player.getBank().setTakeAsNote(false);
                break;
            case 24:
                player.getBank().setTakeAsNote(true);
                break;
            case 28:
                player.getBank().setDefaultQuantity(Bank.DefaultQuantity.ONE);
                break;
            case 30:
                player.getBank().setDefaultQuantity(Bank.DefaultQuantity.FIVE);
                break;
            case 32:
                player.getBank().setDefaultQuantity(Bank.DefaultQuantity.TEN);
                break;
            case 34:
                player.getBank().setDefaultQuantity(Bank.DefaultQuantity.X);
                break;
            case 36:
                player.getBank().setDefaultQuantity(Bank.DefaultQuantity.ALL);
                break;
            case 38:
                player.getBank().setAlwaysSetPlaceholders(!player.getBank().getAlwaysSetPlaceholders());
                break;
            case 42:
                player.getBank().storeInventory();
                break;
            case 44:
                player.getBank().storeEquipment();
                break;
            case 47:
                player.getBank().incinerateItem(itemId, slot - 1);
                break;
            case 50:
                if (slot == 0) {
                    player.getBank().setTabDisplay(Bank.TAB_DISPLAY_FIRST_ITEM);
                } else if (slot == 1) {
                    player.getBank().setTabDisplay(Bank.TAB_DISPLAY_DIGIT);
                } else if (slot == 2) {
                    player.getBank().setTabDisplay(Bank.TAB_DISPLAY_ROMAN_NUMERAL);
                } else if (slot == 3) {
                    player.getBank().setTabDisplay(Bank.TAB_DISPLAY_HIDDEN);
                }
                break;
            case 51:
                player.getBank().setIncinerator(!player.getBank().getIncinerator());
                break;
            case 52:
                player.getBank().setHideWornItems(!player.getBank().getHideWornItems());
                break;
            }
        } else if (widgetId == WidgetId.BANK_INVENTORY) {
            switch (childId) {
            case 3:
                if (index == 1) {
                    if (player.getBank().getDefaultQuantity() == Bank.DefaultQuantity.X) {
                        player.getBank().deposit(itemId, slot, player.getBank().getLastEnteredX());
                    } else {
                        player.getBank().deposit(itemId, slot, player.getBank().getDefaultQuantity().quantity);
                    }
                } else if (index == 2) {
                    player.getBank().deposit(itemId, slot, 1);
                } else if (index == 3) {
                    player.getBank().deposit(itemId, slot, 5);
                } else if (index == 4) {
                    player.getBank().deposit(itemId, slot, 10);
                } else if (index == 5) {
                    player.getBank().deposit(itemId, slot, player.getBank().getLastEnteredX());
                } else if (index == 6) {
                    player.getGameEncoder().sendEnterAmount(new ValueEnteredEvent.IntegerEvent() {
                        @Override
                        public void execute(int value) {
                            player.getBank().setLastEnteredX(value);
                            player.getBank().deposit(itemId, slot, value);
                        }
                    });
                } else if (index == 7) {
                    player.getBank().deposit(itemId, slot, Item.MAX_AMOUNT);
                } else if (index == 8) {
                    if (itemId == player.getInventory().getId(slot) && itemId == ItemId.RUNE_POUCH) {
                        player.getMagic().removeRunesFromPouch(0, Magic.MAX_RUNE_POUCH_AMOUNT);
                        player.getMagic().removeRunesFromPouch(1, Magic.MAX_RUNE_POUCH_AMOUNT);
                        player.getMagic().removeRunesFromPouch(2, Magic.MAX_RUNE_POUCH_AMOUNT);
                    }
                }
                break;
            }
        } else if (widgetId == WidgetId.DEPOSIT_BOX) {
            switch (childId) {
            case 2:
                if (index == 1) {
                    player.getBank().deposit(itemId, slot, 1);
                } else if (index == 2) {
                    player.getBank().deposit(itemId, slot, 5);
                } else if (index == 3) {
                    player.getBank().deposit(itemId, slot, 10);
                } else if (index == 4) {
                    player.getBank().deposit(itemId, slot, Item.MAX_AMOUNT);
                } else if (index == 5) {
                    player.getGameEncoder().sendEnterAmount(new ValueEnteredEvent.IntegerEvent() {
                        @Override
                        public void execute(int value) {
                            player.getBank().deposit(itemId, slot, value);
                        }
                    });
                }
                break;
            case 4:
                player.getBank().storeInventory();
                break;
            case 6:
                player.getBank().storeEquipment();
                break;
            case 8:
                player.getBank().storeLootingBag();
                break;
            }
        } else if (widgetId == WidgetId.BANK_PIN_SETTINGS) {
            switch (childId) {
            case 19:
            case 22:
                player.getBank().openPinSettingsConfirm(false);
                break;
            case 23:
            case 26:
                player.getBank().openPinSettingsConfirm(true);
                break;
            case 33:
                if (player.getAttributeBool("remove_bank_pin")) {
                    player.getBank().deletePin();
                } else {
                    player.getBank().openPinEnter(true);
                }
                break;
            case 36:
                player.removeAttribute("remove_bank_pin");
                break;
            }
        }
    }
}
