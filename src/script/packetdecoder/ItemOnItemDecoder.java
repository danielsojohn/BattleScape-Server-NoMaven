package script.packetdecoder;

import com.palidino.io.Stream;
import com.palidino.osrs.Main;
import com.palidino.osrs.io.PacketDecoder;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.model.player.skill.SkillContainer;
import com.palidino.osrs.util.RequestManager;
import com.palidino.util.Logger;
import lombok.var;
import script.packetdecoder.misc.ItemOnItemAction;

public class ItemOnItemDecoder extends PacketDecoder {
    public ItemOnItemDecoder() {
        super(63);
    }

    @Override
    public void execute(Player player, int index, int size, Stream stream) {
        var useWidgetHash = stream.getIntV2();
        var onWidgetHash = stream.getIntV3();
        var useSlot = stream.getUShortLE128();
        var useItemId = stream.getUShortLE();
        var onSlot = stream.getUShortLE();
        var onItemId = stream.getUShortLE128();
        var useWidgetId = useWidgetHash >> 16;
        var useChildId = useWidgetHash & 65535;
        var onWidgetId = onWidgetHash >> 16;
        var onChildId = onWidgetHash & 65535;
        if (useSlot == 65535) {
            useSlot = -1;
        }
        if (onSlot == 65535) {
            onSlot = -1;
        }
        var message = "[ItemOnItem] useWidgetId=" + useWidgetId + "/" + WidgetId.valueOf(useWidgetId) + "; useChildId="
                + useChildId + "; onWidgetId=" + onWidgetId + "/" + WidgetId.valueOf(onWidgetId) + "; onChildId="
                + onChildId + "; useSlot=" + useSlot + "; onSlot=" + onSlot + "; useItemId=" + useItemId + "/"
                + ItemId.valueOf(useItemId) + "; onItemId=" + onItemId + "/" + ItemId.valueOf(onItemId);
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
        if (!player.getWidgetManager().hasWidget(useWidgetId)) {
            return;
        }
        if (!player.getWidgetManager().hasWidget(onWidgetId)) {
            return;
        }
        player.clearIdleTime();
        if (useWidgetId == WidgetId.INVENTORY && onWidgetId == WidgetId.INVENTORY
                && (player.getInventory().getId(useSlot) != useItemId
                        || player.getInventory().getId(onSlot) != onItemId)) {
            return;
        }
        if (player.getController().itemOnItemHook(useWidgetId, useChildId, onWidgetId, onChildId, useSlot, onSlot,
                useItemId, onItemId)) {
            return;
        }
        for (var plugin : player.getPlugins()) {
            if (plugin.widgetOnWidgetHook(useWidgetId, useChildId, onWidgetId, onChildId, useSlot, useItemId, onSlot,
                    onItemId)) {
                return;
            }
        }
        if (SkillContainer.widgetOnWidgetHooks(player, useWidgetId, useChildId, onWidgetId, onChildId, useSlot,
                useItemId, onSlot, onItemId)) {
            return;
        }
        ItemOnItemAction.doAction(player, useWidgetId, useChildId, onWidgetId, onChildId, useSlot, onSlot, useItemId,
                onItemId);
    }
}
