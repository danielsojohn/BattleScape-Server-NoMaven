package script.packetdecoder;

import com.palidino.io.Stream;
import com.palidino.osrs.io.PacketDecoder;
import com.palidino.osrs.model.player.Player;

public class CloseWidgetDecoder extends PacketDecoder {
    public CloseWidgetDecoder() {
        super(20);
    }

    @Override
    public void execute(Player player, int index, int size, Stream stream) {
        player.clearIdleTime();
        player.getWidgetManager().removeInteractiveWidgets();
    }
}
