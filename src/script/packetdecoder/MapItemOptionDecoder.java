package script.packetdecoder;

import com.palidino.io.Stream;
import com.palidino.osrs.Main;
import com.palidino.osrs.io.PacketDecoder;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.model.Tile;
import com.palidino.osrs.model.dialogue.Dialogue;
import com.palidino.osrs.model.item.ItemDef;
import com.palidino.osrs.model.map.route.Route;
import com.palidino.osrs.model.player.PCombat;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.util.RequestManager;
import com.palidino.util.Logger;
import lombok.var;

public class MapItemOptionDecoder extends PacketDecoder {
    public MapItemOptionDecoder() {
        super(-1, -1, 6);
    }

    @Override
    public void execute(Player player, int index, int size, Stream stream) {
        var id = -1;
        var x = -1;
        var y = -1;
        var moveType = 0;
        if (index == 2) {
            y = stream.getUShortLE();
            moveType = stream.getU128Byte();
            x = stream.getUShortLE();
            id = stream.getUShortLE();
        }
        var message = "[MapItemOption(" + index + ")] id=" + id + "/" + ItemId.valueOf(id) + "; x=" + x + "; y=" + y
                + "; moveType=" + moveType;
        if (Main.isLocal()) {
            Logger.println(message);
        }
        if (player.getOptions().getPrintPackets()) {
            player.getGameEncoder().sendMessage(message);
        }
        RequestManager.addUserPacketLog(player, message);
        if (player.isLocked()) {
            return;
        }
        if (player.getMovement().isViewing()) {
            return;
        }
        player.clearIdleTime();
        player.getMovement().fullRoute(x, y, moveType);
        player.putAttribute("packet_decoder_index", index);
        player.putAttribute("packet_decoder_item_id", id);
        player.putAttribute("packet_decoder_item_x", x);
        player.putAttribute("packet_decoder_item_y", y);
        if (complete(player)) {
            stop(player);
            return;
        }
        player.putAttribute("packet_decoder", this);
    }

    @Override
    public boolean complete(Player player) {
        var index = player.getAttributeInt("packet_decoder_index");
        var id = player.getAttributeInt("packet_decoder_item_id");
        var x = player.getAttributeInt("packet_decoder_item_x");
        var y = player.getAttributeInt("packet_decoder_item_y");
        var reach = 0;
        var t = new Tile(x, y, player.getHeight());
        if (!player.getMovement().isRouting() && player.withinDistance(t, 1) && Route.canReachTile(player, t, true)) {
            reach = 1;
        }
        if (player.isLocked()) {
            return false;
        }
        if (!player.withinDistance(t, reach)) {
            return false;
        }
        if (reach == 1 && !player.withinDistance(t, 0)) {
            player.setAnimation(827);
        }
        player.getMovement().clear();
        if (index == 2) {
            if (id == ItemId.BLOODIER_KEY && player.getSkills().getCombatLevel() > 110) {
                player.getGameEncoder().sendMessage("You need a combat level of atleast 110 to pick this up.");
                return true;
            }
            var isStackableAndCarrying = ItemDef.getStackable(id) && player.getInventory().hasItem(id);
            var result = player.getWorld().pickupMapItem(player, id, x, y);
            if (result.partialSuccess() && (id == ItemId.BLOODY_KEY || id == ItemId.BLOODIER_KEY)
                    && (player.getController().inWilderness() || player.getController().inPvPWorld())) {
                player.getCombat().setPKSkullDelay(PCombat.SKULL_DELAY);
                player.getMovement().setEnergy(0);
                player.getGameEncoder().sendMessage("<col=ff0000>Carrying the key prevents you from teleporting.");
                if (id == ItemId.BLOODIER_KEY) {
                    player.getWorld().sendMessage("<col=ff0000>A " + ItemDef.getName(id) + " has been picked up by "
                            + player.getUsername() + " at level " + player.getWildernessLevel() + " wilderness!");
                }
            } else if (result.partialSuccess() && result.getSlot() != -1 && !isStackableAndCarrying
                    && player.getInventory().hasItem(ItemId.LOOTING_BAG_22586)) {
                player.putAttribute("looting_bag_item_slot", result.getSlot());
                Dialogue.executeScript(player, "lootingbag", 0, 4);
                player.removeAttribute("looting_bag_item_slot");
            }
        }
        return true;
    }

    @Override
    public void stop(Player player) {
        player.removeAttribute("packet_decoder_index");
        player.removeAttribute("packet_decoder_item_id");
        player.removeAttribute("packet_decoder_item_x");
        player.removeAttribute("packet_decoder_item_y");
    }
}
