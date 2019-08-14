package script.packetdecoder;

import com.palidino.io.Stream;
import com.palidino.osrs.io.PacketDecoder;
import com.palidino.osrs.model.player.Appearance;
import com.palidino.osrs.model.player.Appearance.Style;
import com.palidino.osrs.model.player.IdentityKit;
import com.palidino.osrs.model.player.Player;
import lombok.var;

public class AppearanceSelectDecoder extends PacketDecoder {
    public AppearanceSelectDecoder() {
        super(12);
    }

    @Override
    public void execute(Player player, int index, int size, Stream stream) {
        var gender = stream.getUByte();
        if (gender != Appearance.MALE && gender != Appearance.FEMALE) {
            return;
        }
        var styles = new int[player.getAppearance().getStyles().length];
        for (var i = 0; i < styles.length; i++) {
            var id = stream.getUByte();
            if (gender == Appearance.FEMALE && Style.get(i) == Style.BEARD) {
                styles[i] = -1;
                continue;
            }
            if (!IdentityKit.isValidStyle(id, i, gender)) {
                return;
            }
            styles[i] = id;
        }
        var colors = new int[player.getAppearance().getColors().length];
        for (var i = 0; i < colors.length; i++) {
            var id = stream.getUByte();
            colors[i] = id < IdentityKit.COLORS[i].length ? id : 0;
        }
        player.clearIdleTime();
        player.getAppearance().setGender(gender);
        player.getAppearance().setStyles(styles);
        player.getAppearance().setColors(colors);
        player.getAppearance().setUpdate(true);
        player.getWidgetManager().removeInteractiveOverlay();
    }
}
