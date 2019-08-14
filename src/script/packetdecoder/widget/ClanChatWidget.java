package script.packetdecoder.widget;

import com.palidino.osrs.io.ValueEnteredEvent;
import com.palidino.osrs.io.Widget;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.util.RequestManager;
import com.palidino.rs.Clan;

public class ClanChatWidget implements Widget {
    @Override
    public int[] getIds() {
        return new int[] {
            WidgetId.CLAN_CHAT, WidgetId.CLAN_CHAT_OPTIONS
        };
    }

    @Override
    public void execute(Player player, int index, int widgetId, int childId, int slot, int itemId) {
        if (player.isLocked()) {
            return;
        }
        if (widgetId == WidgetId.CLAN_CHAT) {
            switch (childId) {
            case 23:
                if (player.isGameModeGroupIronman()) {
                    player.openDialogue("clanchat", 0);
                } else {
                    player.getMessaging().openClanSettingsInterface();
                }
                break;
            }
        } else if (widgetId == WidgetId.CLAN_CHAT_OPTIONS) {
            int settingValue = 0;
            switch (childId) {
            case 10:
                if (index == 0) {
                    player.getGameEncoder().sendEnterString("Enter chat prefix:", new ValueEnteredEvent.StringEvent() {
                        @Override
                        public void execute(String value) {
                            player.getMessaging().sendClanSetupName(value);
                            RequestManager.getInstance().addClanSetting(player, Clan.NAME, value);
                            if (player.getMessaging().getClanChatDisabled()) {
                                player.getMessaging().setClanChatDisabled(false);
                                RequestManager.getInstance().addClanSetting(player, Clan.DISABLE, 0);
                            }
                        }
                    });
                } else if (index == 1) {
                    boolean disabled = !player.getMessaging().getClanChatDisabled();
                    player.getMessaging().setClanChatDisabled(disabled);
                    String name = player.getMessaging().getMyClanChatName();
                    if (disabled) {
                        name = Clan.DISABLED_NAME;
                    }
                    player.getMessaging().sendClanSetupName(name);
                    RequestManager.getInstance().addClanSetting(player, Clan.DISABLE,
                            player.getMessaging().getClanChatDisabled() ? 1 : 0);
                }
                break;
            case 13:
                if (index == 0) {
                    settingValue = Clan.ANYONE;
                } else if (index == 1) {
                    settingValue = Clan.ANY_FRIENDS;
                } else if (index == 2) {
                    settingValue = Clan.RECRUIT;
                } else if (index == 3) {
                    settingValue = Clan.CORPORAL;
                } else if (index == 4) {
                    settingValue = Clan.SERGEANT;
                } else if (index == 5) {
                    settingValue = Clan.LIEUTENANT;
                } else if (index == 6) {
                    settingValue = Clan.CAPTAIN;
                } else if (index == 7) {
                    settingValue = Clan.GENERAL;
                } else if (index == 8) {
                    settingValue = Clan.ONLY_ME;
                }
                player.getMessaging().sendClanSetupEnterLimit(settingValue);
                RequestManager.getInstance().addClanSetting(player, Clan.ENTER_LIMIT, settingValue);
                break;
            case 16:
                if (index == 0) {
                    settingValue = Clan.ANYONE;
                } else if (index == 1) {
                    settingValue = Clan.ANY_FRIENDS;
                } else if (index == 2) {
                    settingValue = Clan.RECRUIT;
                } else if (index == 3) {
                    settingValue = Clan.CORPORAL;
                } else if (index == 4) {
                    settingValue = Clan.SERGEANT;
                } else if (index == 5) {
                    settingValue = Clan.LIEUTENANT;
                } else if (index == 6) {
                    settingValue = Clan.CAPTAIN;
                } else if (index == 7) {
                    settingValue = Clan.GENERAL;
                } else if (index == 8) {
                    settingValue = Clan.ONLY_ME;
                }
                player.getMessaging().sendClanSetupTalkLimit(settingValue);
                RequestManager.getInstance().addClanSetting(player, Clan.TALK_LIMIT, settingValue);
                break;
            case 19:
                if (index == 3) {
                    settingValue = Clan.CORPORAL;
                } else if (index == 4) {
                    settingValue = Clan.SERGEANT;
                } else if (index == 5) {
                    settingValue = Clan.LIEUTENANT;
                } else if (index == 6) {
                    settingValue = Clan.CAPTAIN;
                } else if (index == 7) {
                    settingValue = Clan.GENERAL;
                } else if (index == 8) {
                    settingValue = Clan.ONLY_ME;
                }
                player.getMessaging().sendClanSetupKickLimit(settingValue);
                RequestManager.getInstance().addClanSetting(player, Clan.KICK_LIMIT, settingValue);
                break;
            }
        }
    }
}
