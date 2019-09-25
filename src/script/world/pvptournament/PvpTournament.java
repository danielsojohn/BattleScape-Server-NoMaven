package script.world.pvptournament;

import java.util.ArrayList;
import java.util.List;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.Controller;
import com.palidino.osrs.model.Tile;
import com.palidino.osrs.model.dialogue.Scroll;
import com.palidino.osrs.model.item.Item;
import com.palidino.osrs.model.map.MapObject;
import com.palidino.osrs.model.player.ClanWars;
import com.palidino.osrs.model.player.PCombat;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.model.player.controller.ClanWarsPC;
import com.palidino.osrs.util.RequestManager;
import com.palidino.osrs.world.WorldEventHooks;
import com.palidino.util.Time;
import com.palidino.util.Utils;
import com.palidino.util.event.Event;
import lombok.Getter;
import lombok.Setter;
import lombok.var;
import script.world.pvptournament.dialogue.AdminDialogue;
import script.world.pvptournament.dialogue.DonateItemDialogue;
import script.world.pvptournament.prize.DefaultPrize;
import script.world.pvptournament.prize.Prize;
import script.world.pvptournament.prize.CustomPrize;
import script.world.pvptournament.state.IdleState;
import script.world.pvptournament.state.LobbyState;
import script.world.pvptournament.state.State;

public class PvpTournament extends Event implements WorldEventHooks {
    public static final boolean DISABLED = false;
    public static final Tile LOBBY_TILE = new Tile(3079, 3479);
    public static final Tile ARENA_TILE_1 = new Tile(2594, 5406, 1), ARENA_TILE_2 = new Tile(2594, 5416, 1);
    public static final String[] TIME = {
        "0:30", "2:30", "4:30", "6:30", "8:30", "10:30", "12:30", "14:30", "16:30", "18:30", "20:30", "22:30"
    };
    public static final int MAX_TIME = (int) Time.minToTick(60), MAX_ROUND_TIME = (int) Time.minToTick(5);
    public static final int LOBBY_JOIN_TIME = 200;
    public static final int MINIMUM_PLAYERS = 4;
    public static final int TIME_BETWEEN_ROUNDS = 25;
    public static final int FIGHT_COUNTDOWN = 17;
    public static final int CUSTOM_COINS_MINIMUM = 5_000_000;

    @Getter
    private List<Mode> recentModes = new ArrayList<>();
    @Getter
    @Setter
    private transient Controller controller;
    @Getter
    @Setter
    private transient Mode mode;
    @Getter
    @Setter
    private transient Prize prize = new DefaultPrize(false);
    @Setter
    private transient int[] rules;
    @Getter
    private transient List<Player> players = new ArrayList<>();
    @Setter
    private transient String viewerList = "";
    @Getter
    @Setter
    private transient State state = new IdleState(this);

    @Override
    public void execute() {
        state.execute();
    }

    @Override
    public boolean widgetOnMapObjectHook(Player player, int widgetId, int childId, int slot, int itemId,
            MapObject mapObject) {
        if (widgetId == WidgetId.INVENTORY) {
            switch (mapObject.getId()) {
            case 29087: // Coffer
                if (!DonatableItems.isDonatable(itemId)) {
                    player.getGameEncoder().sendMessage("The coffer won't take this item.");
                    break;
                }
                new DonateItemDialogue(player, player.getInventory().getItem(slot));
                break;
            }
        }
        return false;
    }

    @Override
    public boolean mapObjectOptionHook(Player player, int index, MapObject mapObject) {
        switch (mapObject.getId()) {
        case 29087: // Coffer
            if (player.getRights() == Player.RIGHTS_ADMIN) {
                new AdminDialogue(player);
            } else {
                viewPrizes(player);
            }
            return true;
        case 26081: // Gate
        case 26082: // Gate
            addPlayer(player);
            return true;
        }
        return false;
    }

    public void addPlayer(Player player) {
        if (!player.getInventory().isEmpty() || !player.getEquipment().isEmpty()) {
            player.getGameEncoder().sendMessage("No items can be taken beyond this point.");
            return;
        }
        if (!(state instanceof LobbyState)) {
            player.getGameEncoder().sendMessage("The lobby is currently closed.");
            return;
        }
        if (players.contains(player)) {
            return;
        }
        for (var aPlayer : players) {
            if (!player.getIP().equals(aPlayer.getIP())) {
                continue;
            }
            player.getGameEncoder().sendMessage("A player with your IP has already joined the tournament.");
            return;
        }

        player.getClanWars().setStatus(ClanWars.Status.TOURNAMENT);
        player.setController(new ClanWarsPC());
        player.restore();
        player.getCombat().setSpecialAttackAmount(PCombat.MAX_SPECIAL_ATTACK);
        player.getSkills().setCombatLevel();
        player.getWidgetManager().removeInteractiveWidgets();
        player.getMovement().teleport(LOBBY_TILE);
        players.add(player);
        if (mode != null) {
            player.getMagic().setSpellbook(mode.getSpellbook());
            player.getLoadout().openSelection();
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void checkPrizes(Player player, boolean isWinner) {
        var isCustom = !(prize instanceof DefaultPrize);
        if (isWinner && !isCustom) {
            player.getClanWars().setCompetitiveTournamentWins(player.getClanWars().getCompetitiveTournamentWins() + 1);
            if (Utils.randomE(240) == 0) {
                player.getBank().add(new Item(ItemId.RAINBOW_PARTYHAT, 1));
                player.getWorld().sendItemDropNews(player, ItemId.RAINBOW_PARTYHAT, "a tournament");
            }
        }
        if (prize == null) {
            return;
        }
        var prizes = prize.getItems(players.size());
        if (prizes == null || prizes.isEmpty()) {
            return;
        }
        var lines = new ArrayList<String>();
        for (var it = prizes.iterator(); it.hasNext();) {
            var prize = it.next();
            it.remove();
            if (!player.isGameModeNormal() && !player.isGameModeHard() && prize.getId() != ItemId.BOND_32318) {
                continue;
            }
            var prizeAmount = prize.getAmount();
            player.getBank().add(new Item(prize.getId(), prizeAmount));
            lines.add(prize.getName() + " x" + Utils.formatNumber(prizeAmount));
            RequestManager.addPlayerLog("clanwarstournament/" + player.getLogFilename(),
                    "[" + player.getId() + "; " + player.getIP() + "] " + player.getUsername() + " won ["
                            + prize.getId() + "] " + prize.getName() + " x" + Utils.formatNumber(prizeAmount)
                            + " in the tournament.");
        }
        Scroll.open(player, "#" + (players.size() + 1) + ": Rewards", lines);
        player.getGameEncoder().sendMessage("Your rewards have been placed in your bank or bond pouch.");
    }

    public void teleportViewing(Player player, String selected) {
        if (player.isLocked() || player.getMovement().getTeleporting() || player.getMovement().getTeleported()) {
            return;
        }
        var options = viewerList.split("\\|\\|");
        for (var i = 0; i < options.length; i++) {
            if (selected.equals(options[i])) {
                player.getClanWars().teleportViewing(i);
                return;
            }
        }
        player.getClanWars().teleportViewing(-1);
    }

    public void viewPrizes(Player player) {
        var lines = new ArrayList<String>();
        for (var i = 0; i < 10; i++) {
            var prizes = prize.getItems(i);
            if (prizes == null || prizes.isEmpty()) {
                continue;
            }
            lines.add("<col=004080>Placing #" + (i + 1) + "</col>");
            for (var item : prizes) {
                lines.add(item.getName() + " x" + Utils.formatNumber(item.getAmount()));
            }
        }
        Scroll.open(player, "Donations", lines);
    }

    public void selectCustomMode(Player player, int index) {
        if (index < 0 || index > Mode.MODES.size()) {
            return;
        }
        if (mode != null) {
            player.getGameEncoder().sendMessage("A mode has already been selected.");
            return;
        }
        mode = Mode.MODES.get(index);
        if (prize instanceof DefaultPrize) {
            prize = new CustomPrize();
        }
    }

    public boolean donateItem(Player player, int itemId, int placing) {
        var isDefault = prize instanceof DefaultPrize;
        var isCustomizable =
                state instanceof IdleState && (prize instanceof DefaultPrize || prize instanceof CustomPrize)
                        || state instanceof LobbyState && prize instanceof CustomPrize;
        if (player.getRights() == 0) {
            player.getGameEncoder().sendMessage("This feature is currently limited to staff.");
            return false;
        }
        if (player.getBank().needsPinInput(false)) {
            return false;
        }
        if (!DonatableItems.isDonatable(itemId)) {
            player.getGameEncoder().sendMessage("You can't donate this item.");
            return false;
        }
        if (!isCustomizable) {
            player.getGameEncoder().sendMessage("You can't donate items right now.");
            return false;
        }
        if (isDefault && placing > 0) {
            player.getGameEncoder().sendMessage("The first donation must be given to first place.");
            return false;
        }
        var item = player.getInventory().getItem(player.getInventory().getSlotById(itemId));
        if (itemId == -1 || item == null) {
            player.getGameEncoder().sendMessage("Unable to find item.");
            return false;
        }
        var itemAmount = item.getAmount();
        if (isDefault && (itemId != ItemId.COINS || itemAmount < CUSTOM_COINS_MINIMUM)) {
            player.getGameEncoder().sendMessage(
                    "The first donation must be at least " + Utils.formatNumber(CUSTOM_COINS_MINIMUM) + " coins.");
            return false;
        }
        if (isDefault) {
            prize = new CustomPrize();
        }
        prize.addItem(placing, new Item(item));
        player.getInventory().deleteItem(item);
        player.getWorld().sendClanWarsTournamentMessage(player.getUsername() + " has donated " + item.getName() + " x"
                + Utils.formatNumber(itemAmount) + " to the tournament.");
        RequestManager.addPlayerLog("clanwarstournament/" + player.getLogFilename(),
                "[" + player.getId() + "; " + player.getIP() + "] " + player.getUsername() + " donated [" + item.getId()
                        + "] " + item.getName() + " x" + Utils.formatNumber(itemAmount) + " to the tournament.");
        return true;
    }

    public void sendWidgetText(Player player) {
        if (player.getWidgetManager().getOverlay() != WidgetId.LMS_LOBBY_OVERLAY) {
            return;
        }
        var warring = player.getClanWars().getWarring();
        player.getGameEncoder().sendWidgetText(WidgetId.LMS_LOBBY_OVERLAY, 6, state.getMessage());
        if (player.inClanWarsBattle()) {
            player.getGameEncoder().sendWidgetText(WidgetId.LMS_LOBBY_OVERLAY, 8,
                    "Remaining: " + Time.ticksToDuration(state.getTime()));
            String opponent = warring != null && warring.isVisible() ? warring.getUsername() : "None";
            player.getGameEncoder().sendWidgetText(WidgetId.LMS_LOBBY_OVERLAY, 10, "Opponent: " + opponent);
        } else {
            player.getGameEncoder().sendWidgetText(WidgetId.LMS_LOBBY_OVERLAY, 8,
                    "Mode: " + (mode != null ? mode.getName() : "None"));
            player.getGameEncoder().sendWidgetText(WidgetId.LMS_LOBBY_OVERLAY, 10,
                    "Wins: " + Utils.formatNumber(player.getClanWars().getCompetitiveTournamentWins()));
        }
    }

    public static String[] getModeNames() {
        var names = new String[Mode.MODES.size()];
        for (var i = 0; i < Mode.MODES.size(); i++) {
            names[i] = Mode.MODES.get(i).getName();
        }
        return names;
    }

    public static int[] getNextTime() {
        if (TIME == null) {
            return null;
        }
        var currentHour = Time.getHour24();
        var currentMinute = Time.getMinute();
        for (var i = 0; i < TIME.length; i++) {
            var time = TIME[i].split(":");
            var hour = Integer.parseInt(time[0]);
            var minute = Integer.parseInt(time[1]);
            if (currentHour > hour || currentHour == hour && currentMinute > minute) {
                continue;
            }
            return new int[] {
                hour, minute
            };
        }
        var time = TIME[0].split(":");
        return new int[] {
            Integer.parseInt(time[0]), Integer.parseInt(time[1])
        };
    }
}
