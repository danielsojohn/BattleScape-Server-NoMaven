package script.packetdecoder;

import com.palidino.io.Stream;
import com.palidino.osrs.io.PacketDecoder;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.model.player.WidgetManager;
import lombok.var;

public class WindowSizeDecoder extends PacketDecoder {
    public WindowSizeDecoder() {
        super(35);
    }

    @Override
    @SuppressWarnings("unused")
    public void execute(Player player, int index, int size, Stream stream) {
        var viewportType = WidgetManager.ViewportType.get(stream.getUByte());
        var width = stream.getUShort();
        var height = stream.getUShort();
        var currentViewportType = player.getWidgetManager().getViewportType();
        if (viewportType == null) {
            return;
        }
        if (viewportType == currentViewportType) {
            return;
        }
        player.clearIdleTime();
        player.getWidgetManager().setViewportType(viewportType);
        player.getWidgetManager().sendGameViewport();
    }
}
