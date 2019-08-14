package script.packetdecoder;

import com.palidino.io.Stream;
import com.palidino.osrs.Main;
import com.palidino.osrs.io.PacketDecoder;
import com.palidino.osrs.io.ValueEnteredEvent;
import com.palidino.osrs.model.player.Player;
import com.palidino.util.Logger;
import lombok.var;

public class ValueEnteredDecoder extends PacketDecoder {
    public ValueEnteredDecoder() {
        super(17, 8, 42);
    }

    @Override
    public void execute(Player player, int index, int size, Stream stream) {
        if (index == 0) {
            var value = stream.getInt();
            var message = "[ValueEnteredInt] value=" + value;
            if (Main.isLocal()) {
                Logger.println(message);
            }
            if (player.getOptions().getPrintPackets()) {
                player.getGameEncoder().sendMessage(message);
            }
            var event = (ValueEnteredEvent.IntegerEvent) player.removeAttribute("entered_integer_event");
            if (event == null) {
                return;
            }
            if (player.isLocked()) {
                event.close();
                return;
            }
            player.clearIdleTime();
            event.execute(value);
            event.close();
        } else if (index == 1) {
            var value = stream.getString();
            var message = "[ValueEnteredString] value=" + value;
            if (Main.isLocal()) {
                Logger.println(message);
            }
            if (player.getOptions().getPrintPackets()) {
                player.getGameEncoder().sendMessage(message);
            }
            var event = (ValueEnteredEvent.StringEvent) player.removeAttribute("entered_string_event");
            if (event == null) {
                return;
            }
            if (player.isLocked()) {
                event.close();
                return;
            }
            event.execute(value);
            event.close();
        } else if (index == 2) {
            var id = stream.getUShort();
            if (id == 65535) {
                id = -1;
            }
            var message = "[ItemEntered] id=" + id;
            if (Main.isLocal()) {
                Logger.println(message);
            }
            if (player.getOptions().getPrintPackets()) {
                player.getGameEncoder().sendMessage(message);
            }
            var event = (ValueEnteredEvent.IntegerEvent) player.removeAttribute("entered_integer_event");
            if (event == null) {
                return;
            }
            if (player.isLocked()) {
                event.close();
                return;
            }
            event.execute(id);
            event.close();
        }
    }
}
