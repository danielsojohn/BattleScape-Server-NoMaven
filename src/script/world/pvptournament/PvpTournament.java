/*
 * Converting tournaments over to the public repo, atm this code is ignored
 */

package script.world.pvptournament;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.palidino.osrs.Main;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.io.cache.WidgetId;
import com.palidino.osrs.model.Controller;
import com.palidino.osrs.model.Movement;
import com.palidino.osrs.model.Tile;
import com.palidino.osrs.model.dialogue.Scroll;
import com.palidino.osrs.model.item.Item;
import com.palidino.osrs.model.item.ItemDef;
import com.palidino.osrs.model.player.ClanWars;
import com.palidino.osrs.model.player.ItemCharges;
import com.palidino.osrs.model.player.Loadout;
import com.palidino.osrs.model.player.PCombat;
import com.palidino.osrs.model.player.Player;
import com.palidino.osrs.util.RequestManager;
import com.palidino.osrs.world.World;
import com.palidino.setting.SqlUserRank;
import com.palidino.util.Time;
import com.palidino.util.Utils;

public class PvpTournament {
    public static final Tile LOBBY_TILE = new Tile(3079, 3479);
    private static final boolean DISABLED = false;
    private static final String[] TIME = {
        "0:30", "2:30", "4:30", "6:30", "8:30", "10:30", "12:30", "14:30", "16:30", "18:30", "20:30", "22:30"
    };
    private static final int MAX_TIME = (int) Time.minToTick(60), MAX_ROUND_TIME = (int) Time.minToTick(5);
    private static final int JOIN_COUNT_DOWN = 200, CUSTOM_JOIN_COUNT_DOWN = 300, LOBBY_COUNT_DOWN = 25;
    private static final int ROUND_DELAY = 25;
    private static final int COMBAT_START_DELAY = 17;
    private static final int MINIMUM_PLAYERS = 2;
    private static final int CUSTOM_MODE_SELECT_DELAY = 50;
    private static final int CUSTOM_COINS_MINIMUM = 5_000_000;
    private static final List<Item> PRIZES = Utils.toList(new Item(ItemId.COINS, 16_000_000),
            new Item(ItemId.COINS, 8_000_000), new Item(ItemId.COINS, 4_000_000), new Item(ItemId.COINS, 2_000_000));
    public static final int[] DONATABLE_ITEMS = {
        ItemId.COINS, ItemId.BOND_32318, 4708, 4710, 4712, 4714, 4716, ItemId.DHAROKS_GREATAXE, 4720, 4722, 4724, 4726,
        4728, 4730, 4732, 4734, ItemId.KARILS_LEATHERTOP, 4738, 4745, 4747, ItemId.TORAGS_PLATEBODY, 4751, 4753, 4755,
        4757, ItemId.VERACS_PLATESKIRT, 19532, 19535, 19538, 19541, 19544, 19547, 19550, 19553, 6575, 6577, 6581, 11130,
        6583, 6585, 11128, 11133, ItemId.ARMADYL_CROSSBOW, 11791, ItemId.ARMADYL_GODSWORD, 11804, 11806, 11808, 11824,
        11826, ItemId.ARMADYL_CHESTPLATE, 11830, 11832, 11834, 11836, 11838, 12804, 12004, 11283, 12817, 12821, 12825,
        12831, 4151, 20408, 12002, 13235, 13237, 13239, ItemId.LIGHT_BALLISTA, 19481, 6889, 6914, 6916, 6918, 6920,
        6922, 6924, 11926, 11924, 12922, 12924, 12927, 12929, 12932, 20716, 13576, 21006, 13256, 13263, 13265, 13267,
        13269, 13271, 8901, 8921, 6731, 6733, 6735, 6737, 6739, 6571, 6573, 19493, 19496, 4207, 4212, 4224, 11920,
        11905, 11908, 12601, 12603, 12605, 3140, 12000, 13231, 13229, 13227, 13233, 10330, 10332, 10334, 10336, 10338,
        10340, 10342, 10344, 10346, 10348, 10350, 10352, 12422, 12424, 12426, 12437, 20011, 20014, 21034, 21079, 21009,
        21028, 20849, 21000, 21012, 21015, 21018, 21021, 21024, ItemId.DRAGON_CLAWS, 21003, 21043, 20997, 22477, 22326,
        ItemId.JUSTICIAR_CHESTGUARD, ItemId.JUSTICIAR_LEGGUARDS, ItemId.GHRAZI_RAPIER, 22481, 22486, 22290, 22294
    };

    private static PvpTournament instance = new PvpTournament();

    private transient Controller controller;
    private transient Mode mode;
    private transient int teamSize = 1;
    private transient boolean bondPrize;
    private transient int osCoinsPrize;
    private transient List<Player> players = new ArrayList<>();
    private transient Map<Player, Item[]> playersEquipment = new HashMap<>();
    private transient Map<Player, Item[]> playersInventory = new HashMap<>();
    private transient Status status = Status.OFF;
    private transient int[] rules;
    private transient int countDown;
    private transient boolean announcedStartingSoon;
    private transient int time;
    private transient int round, roundTime, roundDelay;
    private transient boolean isCustom;
    private transient String viewerList = "";
    private transient int firstWinner = -1;
    private List<List<Item>> donatedItems =
            Utils.toList(new ArrayList<Item>(), new ArrayList<Item>(), new ArrayList<Item>(), new ArrayList<Item>());
    private List<Mode> recentModes = new ArrayList<>();

    public void tick() {
        countDown();
        checkStart();
        checkEnd();
    }

    public void countDown() {
        if (countDown == 0) {
            return;
        }
        if (isLobbyJoinable()
                && (!isCustom && countDown == JOIN_COUNT_DOWN || isCustom && countDown == CUSTOM_JOIN_COUNT_DOWN)) {
            int minutes = (int) Time.tickToMin(countDown);
            String minutesString = minutes + " minutes.";
            if (minutes <= 1) {
                minutesString = minutes + " minute.";
            }
            String message = "The tournament lobby is open" + (isCustom ? " for a custom match" : "") + " as "
                    + mode.getName() + "! It will begin in " + minutesString;
            String prizes = "";
            if (isCustom) {
                if (osCoinsPrize > 0) {
                    prizes += (prizes.length() > 0 ? "/" : "") + Utils.abbreviateNumber(osCoinsPrize) + " OSGP";
                }
                boolean hasDonatedCoins = false;
                for (List<Item> prizeList : donatedItems) {
                    if (prizeList.isEmpty() || prizeList.get(0).getId() != ItemId.COINS) {
                        break;
                    }
                    prizes += (prizes.length() > 0 ? "/" : "") + Utils.abbreviateNumber(prizeList.get(0).getAmount());
                    hasDonatedCoins = true;
                }
                if (hasDonatedCoins) {
                    message += " Coins";
                }
            } else if (bondPrize) {
                message += " The reward is 50 bonds";
            }
            if (prizes.length() > 0) {
                message += " rewards include " + prizes + ".";
            }
            Main.getWorld().sendBroadcast(message);
        }
        countDown--;
        for (Player p : players) {
            checkBrews(p);
        }
        if (countDown == 1 && isLobbyJoinable()) {
            status = Status.LOBBY_LOCKED;
            countDown = LOBBY_COUNT_DOWN;
            boolean hasEnoughPlayers = players.size() >= MINIMUM_PLAYERS;
            if (hasEnoughPlayers) {
                for (Player p : players) {
                    p.getClanWars().setCountDown(countDown);
                }
            } else {
                while (!players.isEmpty()) {
                    Player p = players.get(0);
                    p.getGameEncoder()
                            .sendMessage("<col=ff0000>At least " + MINIMUM_PLAYERS + " players are needed to start.");
                    p.getController().stop();
                }
            }
            if (!hasEnoughPlayers) {
                status = Status.OFF;
                mode = null;
                return;
            }
        } else if (countDown == 1 && isLobbyLocked()) {
            status = Status.BATTLE;
            if (players.size() < MINIMUM_PLAYERS) {
                while (!players.isEmpty()) {
                    Player p = players.get(0);
                    p.getGameEncoder()
                            .sendMessage("<col=ff0000>At least " + MINIMUM_PLAYERS + " players are needed to start.");
                    p.getController().stop();
                }
                status = Status.OFF;
                mode = null;
            } else {
                controller = Controller
                        .getDefaultController(ClanWars.Arena.get(rules[ClanWars.Rule.ARENA.ordinal()]).arenaBottom);
                if (teamSize > 1) {
                    controller.instance();
                    round = 0;
                } else {
                    round = 1;
                }
                for (Player p : players) {
                    p.getController().setInstance(controller);
                    p.getGameEncoder().sendPlayerOption("Attack", 2, false);
                    p.getWidgetManager().removeInteractiveWidgets();
                    p.getClanWars().setStatus(ClanWars.Status.BATTLE);
                    p.getWidgetManager().removeOverlay();
                    if (teamSize > 1) {
                        p.getClanWars().teleport();
                    }
                    p.getClanWars().setCountDown(countDown);
                    checkBrews(p);
                }
            }
        }
    }

    public void checkStart() {
        if (DISABLED || status != Status.OFF && status != Status.CUSTOM_MODE_SELECT) {
            return;
        }
        int[] nextTime = getNextTime();
        if (nextTime == null) {
            return;
        }
        boolean isCustomStart = isCustomModeSelect() && (countDown == 0 || mode != null);
        if (!donatedItems.get(0).isEmpty()) {
            isCustom = true;
        }
        if (!isCustom && Main.getWorld().getId() != World.SPECIAL_FEATURES_WORLD_ID) {
            return;
        }
        int currentHour = Time.getHour24();
        int currentMinute = Time.getMinute();
        int dayMinute = (int) Time.hourToMin(currentHour) + currentMinute;
        int remainingMinutes = Time.getRemainingMinutes(dayMinute, nextTime[0] * 60 + nextTime[1]);
        if (!isCustom && !announcedStartingSoon) {
            if (remainingMinutes == 4) {
                announcedStartingSoon = true;
                Main.getWorld().sendNotice("The tournament lobby will open in 4 minutes!");
            }
        }
        if (remainingMinutes != 0 && !isCustomStart && !isCustom) {
            return;
        }
        if (isCustom && !isCustomStart && isCustomModeSelect()) {
            return;
        }
        announcedStartingSoon = false;
        countDown = isCustom ? CUSTOM_JOIN_COUNT_DOWN : JOIN_COUNT_DOWN;
        if (!isCustomModeSelect() && isCustom) {
            countDown--;
        }
        status = Status.LOBBY_JOINABLE;
        time = MAX_TIME;
        roundDelay = 1;
        bondPrize = currentHour == 16;
        selectMode();
        players.clear();
        firstWinner = -1;
        if (!isCustom) {
            osCoinsPrize = 0;
        }
    }

    public void checkEnd() {
        if (status != Status.BATTLE || countDown > 0) {
            return;
        }
        time--;
        if (teamSize == 1) {
            roundTime--;
            roundDelay--;
            boolean matchesRemain = false;
            for (int i = 0; i < players.size(); i++) {
                Player p = players.get(i);
                if (roundDelay > 0) {
                    checkBrews(p);
                }
                if (p.getClanWars().getWarring() != null && !players.contains(p.getClanWars().getWarring())) {
                    p.getClanWars().setWarring(null);
                }
                if (roundTime == 0 && p.getClanWars().getWarring() != null) {
                    Player loser = p.getCombat().getDamageInflicted() < p.getClanWars().getWarring().getCombat()
                            .getDamageInflicted() ? p : p.getClanWars().getWarring();
                    Player winner = loser == p ? p.getClanWars().getWarring() : p;
                    loser.getController().stop();
                    loser.getGameEncoder().sendMessage(
                            "<col=ff0000>You have run out of time and dealt less damage than your opponent! You have been disqualified.");
                    winner.getGameEncoder().sendMessage(
                            "<col=ff0000>You have run out of time and dealt more damage than your opponent! Your opponent has been disqualified.");
                    winner.getClanWars().setWarring(null);
                    if (p == loser) {
                        i--;
                    }
                }
                if (p.getClanWars().getWarring() != null) {
                    matchesRemain = true;
                } else if (!p.isLocked() && p.inClanWarsBattle()) {
                    p.getEquipment().setItems(playersEquipment.get(p));
                    p.getInventory().setItems(playersInventory.get(p));
                    p.getEquipment().weaponUpdate(true);
                    p.restore();
                    p.getCombat().setSpecialAttackAmount(PCombat.MAX_SPECIAL_ATTACK);
                    p.getMovement().teleport(LOBBY_TILE);
                    p.getGameEncoder().sendHideWidget(WidgetId.LMS_LOBBY_OVERLAY, 0, false);
                    if (firstWinner == -1) {
                        firstWinner = p.getId();
                    }
                }
            }
            if (roundDelay < 0 && (roundTime == 0 || !matchesRemain) && players.size() > 1) {
                roundDelay = ROUND_DELAY;
                round++;
                viewerList = "";
                for (Player p : players) {
                    if (roundDelay < 100) {
                        p.getGameEncoder().sendMessage(
                                "<col=ff0000>The next round will begin in " + Time.tickToSec(roundDelay) + " seconds.");
                    } else if (roundDelay < 200) {
                        p.getGameEncoder().sendMessage("<col=ff0000>The next round will begin in 1 minute.");
                    } else {
                        p.getGameEncoder().sendMessage(
                                "<col=ff0000>The next round will begin in " + Time.tickToMin(roundDelay) + " minutes.");
                    }
                    checkBrews(p);
                    if (p.getMovement().isViewing()) {
                        p.getMovement().stopViewing();
                        p.getWidgetManager().removeInteractiveWidgets();
                    }
                }
            }
            if (roundDelay == 0) {
                for (int i = 0; i < players.size(); i++) {
                    Player p = players.get(i);
                    if (p.getAppearance().getNpcId() == Movement.VIEWING_NPC_ID) {
                        p.getController().stop();
                        i--;
                    } else if (!p.isVisible()) {
                        players.remove(i--);
                    }
                }
                roundTime = MAX_ROUND_TIME;
                Collections.shuffle(players);
                int height = 0;
                viewerList = "";
                if (firstWinner != -1) {
                    Player previousFirstWinner = Main.getWorld().getPlayerById(firstWinner);
                    if (previousFirstWinner != null && players.contains(previousFirstWinner)) {
                        players.remove(previousFirstWinner);
                        players.add(previousFirstWinner);
                    }
                }
                for (int i = 0; i < players.size(); i += 2) {
                    Player p1 = players.get(i);
                    if (i == 0) {
                        p1.getController().removeMapItems(10324);
                    }
                    if (i == players.size() - 1) {
                        p1.getGameEncoder().sendMessage("<col=ff0000>You don't have an opponent for this round.");
                        break;
                    }
                    Player p2 = players.get(i + 1);
                    p1.getClanWars().setWarring(p2);
                    p2.getClanWars().setWarring(p1);
                    p1.getGameEncoder().sendMessage("<col=ff0000>Your opponent is " + p2.getUsername() + "!");
                    p2.getGameEncoder().sendMessage("<col=ff0000>Your opponent is " + p1.getUsername() + "!");
                    p1.setCombatImmunity(COMBAT_START_DELAY);
                    p2.setCombatImmunity(COMBAT_START_DELAY);
                    p1.getClanWars().setTournamentFightDelay(COMBAT_START_DELAY);
                    p2.getClanWars().setTournamentFightDelay(COMBAT_START_DELAY);
                    p1.getCombat().setDamageInflicted(0);
                    p2.getCombat().setDamageInflicted(0);
                    p1.getWidgetManager().removeInteractiveWidgets();
                    p2.getWidgetManager().removeInteractiveWidgets();
                    p1.getCharges().setRingOfRecoil(ItemCharges.RING_OF_RECOIL);
                    p2.getCharges().setRingOfRecoil(ItemCharges.RING_OF_RECOIL);
                    p1.restore();
                    p2.restore();
                    p1.getCombat().setSpecialAttackAmount(PCombat.MAX_SPECIAL_ATTACK);
                    p2.getCombat().setSpecialAttackAmount(PCombat.MAX_SPECIAL_ATTACK);
                    checkBrews(p1);
                    checkBrews(p2);
                    playersEquipment.put(p1, p1.getEquipment().copy());
                    playersInventory.put(p1, p1.getInventory().copy());
                    playersEquipment.put(p2, p2.getEquipment().copy());
                    playersInventory.put(p2, p2.getInventory().copy());
                    p1.getMovement().teleport(2594, 5406, 1 + height);
                    p2.getMovement().teleport(2594, 5416, 1 + height);
                    height += 4;
                    p1.getGameEncoder().sendHideWidget(WidgetId.LMS_LOBBY_OVERLAY, 0, true);
                    p2.getGameEncoder().sendHideWidget(WidgetId.LMS_LOBBY_OVERLAY, 0, true);
                    p1.getGameEncoder().sendPlayerOption("Attack", 2, false);
                    p2.getGameEncoder().sendPlayerOption("Attack", 2, false);
                    if (i < 22) {
                        viewerList += p1.getUsername() + " vs " + p2.getUsername() + "||";
                    }
                }
                if (viewerList.length() > 2) {
                    viewerList = viewerList.substring(0, viewerList.length() - 2);
                }
                firstWinner = -1;
            }
        }
        if (time == 0 || players.size() <= 1) {
            status = Status.OFF;
            mode = null;
            controller = null;
            if (players.size() == 1) {
                Player p = players.get(0);
                p.getInventory().clear();
                p.getEquipment().clear();
                p.getController().stop();
                checkPrizes(p, true);
                p.getWorld().sendPvpNews(p.getMessaging().getIconImage() + p.getUsername() + " won the tournament!");
            }
            while (!players.isEmpty()) {
                players.get(0).getController().stop();
            }
            isCustom = false;
            viewerList = "";
        }
    }

    public void resetMode() {
        mode = null;
        selectMode();
    }

    public void selectMode() {
        if (mode == null) {
            int attempts = 0;
            while (attempts++ < 16 && (mode == null || !bondPrize && isRecentMode(mode))) {
                mode = Utils.listRandom(Mode.MODES);
            }
            addRecentMode(mode);
        }
        teamSize = 1;
        rules = new int[ClanWars.RULE_COUNT];
        System.arraycopy(mode.getRules(), 0, rules, 0, ClanWars.RULE_COUNT);
        rules[ClanWars.Rule.ARENA.ordinal()] =
                Utils.randomI(1) == 0 ? ClanWars.RuleOption.LUMBRIDGE.index : ClanWars.RuleOption.FALADOR.index;
    }

    public String getNextGameTime() {
        if (DISABLED) {
            return "Unavailable";
        }
        if (countDown > 0 && (isLobbyJoinable() || isLobbyLocked())) {
            String timeRemaining;
            int minutes = (int) Time.tickToMin(countDown);
            if (minutes > 0) {
                timeRemaining = minutes + (minutes == 1 ? " minute" : " minutes");
            } else {
                timeRemaining = Time.tickToSec(countDown) + " seconds";
            }
            return (isLobbyJoinable() ? "Lobby: " : "Count Down: ") + timeRemaining;
        } else if (status != Status.OFF && !isCustomModeSelect()) {
            if (roundDelay > 0) {
                return "Next Round: " + Time.tickToSec(roundDelay) + " seconds";
            } else if (round > 0) {
                return "Round: " + round;
            }
            return "Status: Running";
        } else if (TIME.length == 0) {
            return "Status: None";
        }
        if (Main.getWorld().getId() != World.SPECIAL_FEATURES_WORLD_ID) {
            return "Unavailable";
        }
        int currentHour = Time.getHour24();
        int currentMinute = Time.getMinute();
        int dayMinute = (int) Time.hourToMin(currentHour) + currentMinute;
        int[] nextTime = getNextTime();
        int remainingMinutes = Time.getRemainingMinutes(dayMinute, nextTime[0] * 60 + nextTime[1]);
        return "" + Time.ticksToLongDuration(Time.minToTick(remainingMinutes));
    }

    public void checkBrews(Player p) {
        int[] brewIds = new int[] {
            ItemId.SARADOMIN_BREW_1, ItemId.SARADOMIN_BREW_2, ItemId.SARADOMIN_BREW_3, ItemId.SARADOMIN_BREW_4
        };
        for (int brewDeleteId : brewIds) {
            if (!p.getInventory().hasItem(brewDeleteId)) {
                continue;
            }
            int brewCount = 0;
            for (int brewId : brewIds) {
                brewCount += p.getInventory().getCount(brewId);
            }
            if (brewCount <= getBrewCap()) {
                break;
            }
            p.getInventory().deleteItem(brewDeleteId, brewCount - getBrewCap());
        }
    }

    public void addPlayer(Player p) {
        players.add(p);
        if (mode != null) {
            p.getMagic().setSpellbook(mode.getSpellbook());
            p.getLoadout().openSelection();
        }
    }

    public void removePlayer(Player p) {
        players.remove(p);
    }

    public boolean hasIPMatch(String ip) {
        for (Player p : players) {
            if (p.getIP().equals(ip)) {
                return true;
            }
        }
        return false;
    }

    public void addDonatedItem(int placing, Item item) {
        if (placing < 0 || placing >= donatedItems.size()) {
            return;
        }
        List<Item> items = donatedItems.get(placing);
        items.add(item);
    }

    public List<Item> getPrizes() {
        int index = players.size();
        if (isCustom) {
            return index < donatedItems.size() ? donatedItems.get(index) : null;
        } else {
            return index < PRIZES.size() ? Utils.toList(PRIZES.get(index)) : null;
        }
    }

    public void checkPrizes(Player p, boolean isWinner) {
        List<Item> prizes = getPrizes();
        if (isWinner && !isCustom) {
            p.getClanWars().setCompetitiveTournamentWins(p.getClanWars().getCompetitiveTournamentWins() + 1);
            if (Utils.randomE(240) == 0) {
                p.getBank().add(new Item(ItemId.RAINBOW_PARTYHAT, 1));
                p.getWorld().sendItemDropNews(p, ItemId.RAINBOW_PARTYHAT, "a tournament");
            }
            if (bondPrize) {
                p.getBonds().setPouch(p.getBonds().getPouch() + 50);
            }
        }
        if (prizes != null && !prizes.isEmpty()) {
            List<String> lines = new ArrayList<>();
            for (Iterator<Item> it = prizes.iterator(); it.hasNext();) {
                Item prize = it.next();
                if (!isCustom || p.isGameModeNormal() || p.isGameModeHard() || prize.getId() == ItemId.BOND_32318) {
                    it.remove();
                    int prizeAmount = prize.getAmount();
                    if (prize.getId() != ItemId.BOND_32318 && !p.isGameModeNormal() && !p.isGameModeHard()) {
                        continue;
                    }
                    p.getBank().add(new Item(prize.getId(), prizeAmount));
                    lines.add(prize.getName() + " x" + Utils.formatNumber(prizeAmount));
                    RequestManager.addPlayerLog("clanwarstournament/" + p.getLogFilename(),
                            "[" + p.getId() + "; " + p.getIP() + "] " + p.getUsername() + " won [" + prize.getId()
                                    + "] " + prize.getName() + " x" + Utils.formatNumber(prizeAmount)
                                    + " in the tournament.");
                }
            }
            Scroll.open(p, "#" + (players.size() + 1) + ": Rewards", lines);
            p.getGameEncoder().sendMessage("Your rewards have been placed in your bank or bond pouch.");
        }
    }

    public boolean isRecentMode(Mode mode) {
        return recentModes.contains(mode);
    }

    public void addRecentMode(Mode mode) {
        if (!recentModes.contains(mode)) {
            recentModes.add(mode);
        }
        if (recentModes.size() > 2) {
            recentModes.remove(0);
        }
    }

    public String getModeName() {
        return mode != null ? mode.getName() : "None";
    }

    public int[] getSkills() {
        return null; // return mode != null ? mode.skills : null;
    }

    public int getBrewCap() {
        return mode != null ? mode.getBrewCap() : 0;
    }

    public int[] getRunePouch() {
        return null; // return mode != null ? mode.runePouch : null;
    }

    public List<Loadout.Entry> getLoadouts() {
        return mode != null ? mode.getLoadouts() : null;
    }

    public int[] getRules() {
        return rules;
    }

    public boolean isOff() {
        return status == Status.OFF;
    }

    public boolean isLobbyJoinable() {
        return status == Status.LOBBY_JOINABLE;
    }

    public boolean isLobbyLocked() {
        return status == Status.LOBBY_LOCKED;
    }

    public boolean isCustomModeSelect() {
        return status == Status.CUSTOM_MODE_SELECT;
    }

    public boolean isBattle() {
        return status == Status.BATTLE;
    }

    public int getCountDown() {
        return countDown;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public int getTime() {
        return time;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public String getViewerList() {
        return viewerList;
    }

    public int getRoundDelay() {
        return roundDelay;
    }

    public static boolean donateItem(Player p, int itemId, int placing) {
        Item item = p.getInventory().getItem(p.getInventory().getSlotById(itemId));
        if (itemId == -1 || item == null) {
            p.getGameEncoder().sendMessage("Unable to find " + ItemDef.getName(itemId) + ".");
            return false;
        }
        int itemAmount = item.getAmount();
        if (!canDonateItem(itemId)) {
            p.getGameEncoder().sendMessage("You can't donate this item.");
            return false;
        } else if (p.getBank().needsPinInput(false)) {
            return false;
        } else if (instance.isBattle()) {
            p.getGameEncoder().sendMessage("Items can't be added to the coffer while a tournament is running.");
            return false;
        } else if (!instance.isOff() && !instance.isCustom) {
            p.getGameEncoder().sendMessage("You can't donate items to a normal tournament.");
            return false;
        } else if (!instance.isCustom && placing > 0) {
            p.getGameEncoder().sendMessage("The first donation must be given to first place.");
            return false;
        } else if (!instance.isCustom && (itemId != ItemId.COINS || itemAmount < CUSTOM_COINS_MINIMUM)) {
            p.getGameEncoder().sendMessage(
                    "The first donation must be at least " + Utils.formatNumber(CUSTOM_COINS_MINIMUM) + " coins.");
            return false;
        } else if (p.getRights() == 0 && !p.isUsergroup(SqlUserRank.ADVERTISEMENT_MANAGER)) {
            p.getGameEncoder().sendMessage("This feature is currently limited to staff.");
            return false;
        }
        instance.addDonatedItem(placing, new Item(itemId, itemAmount));
        p.getInventory().deleteItem(item);
        p.getWorld().sendClanWarsTournamentMessage(p.getUsername() + " has donated " + item.getName() + " x"
                + Utils.formatNumber(itemAmount) + " to the tournament.");
        RequestManager.addPlayerLog("clanwarstournament/" + p.getLogFilename(),
                "[" + p.getId() + "; " + p.getIP() + "] " + p.getUsername() + " donated [" + item.getId() + "] "
                        + item.getName() + " x" + Utils.formatNumber(itemAmount) + " to the tournament.");
        return startCustom(p, 0);
    }

    public static boolean startCustom(Player p, int coinAmount) {
        if (!instance.isOff()) {
            return false;
        }
        instance.isCustom = true;
        boolean allowModeSelection = instance.isOff() && instance.countDown == 0;
        if (allowModeSelection) {
            instance.status = Status.CUSTOM_MODE_SELECT;
            instance.countDown = CUSTOM_MODE_SELECT_DELAY;
            if (coinAmount > 0) {
                instance.addDonatedItem(0, new Item(ItemId.COINS, coinAmount));
                instance.addDonatedItem(1, new Item(ItemId.COINS, coinAmount / 2));
                instance.addDonatedItem(2, new Item(ItemId.COINS, coinAmount / 4));
                instance.addDonatedItem(3, new Item(ItemId.COINS, coinAmount / 8));
            }
            instance.osCoinsPrize = 0;
        }
        return allowModeSelection;
    }

    public static void setOSCoinsPrize(int osCoinsPrize) {
        instance.osCoinsPrize = osCoinsPrize;
    }

    public static void donatedSelectMode(Player p, int index) {
        if (index < 0 || index > Mode.MODES.size()) {
            return;
        } else if (!instance.isCustomModeSelect()) {
            p.getGameEncoder().sendMessage("A mode has already been selected.");
            return;
        }
        instance.mode = Mode.MODES.get(index);
    }

    public static void viewDonatedItems(Player p) {
        List<String> lines = new ArrayList<>();
        if (!instance.isCustomModeSelect() && !instance.isCustom) {
            for (int i = 0; i < PRIZES.size(); i++) {
                lines.add("<col=004080>Placing #" + (i + 1) + "</col>");
                Item item = PRIZES.get(i);
                lines.add(item.getName() + " x" + Utils.formatNumber(item.getAmount()));
            }
        } else {
            for (int i = 0; i < instance.donatedItems.size(); i++) {
                lines.add("<col=004080>Placing #" + (i + 1) + "</col>");
                List<Item> items = instance.donatedItems.get(i);
                for (Item item : items) {
                    lines.add(item.getName() + " x" + Utils.formatNumber(item.getAmount()));
                }
            }
        }
        Scroll.open(p, "Donations", lines);
    }

    public static boolean canDonateItem(int itemId) {
        for (int donatableId : DONATABLE_ITEMS) {
            if (itemId == donatableId || itemId != -1 && ItemDef.getUnnotedId(itemId) == donatableId) {
                return true;
            }
        }
        return false;
    }

    public static String[] getModeNames() {
        String[] names = new String[Mode.MODES.size()];
        for (int i = 0; i < Mode.MODES.size(); i++) {
            names[i] = Mode.MODES.get(i).getName();
        }
        return names;
    }

    public static void teleportViewing(Player p, String selected) {
        if (p.isLocked() || p.getMovement().getTeleporting() || p.getMovement().getTeleported()) {
            return;
        }
        String[] options = instance.viewerList.split("\\|\\|");
        for (int i = 0; i < options.length; i++) {
            if (selected.equals(options[i])) {
                p.getClanWars().teleportViewing(i);
                return;
            }
        }
        p.getClanWars().teleportViewing(-1);
    }

    public static PvpTournament getInstance() {
        return instance;
    }

    public static int[] getNextTime() {
        if (TIME == null) {
            return null;
        }
        int currentHour = Time.getHour24();
        int currentMinute = Time.getMinute();
        for (int i = 0; i < TIME.length; i++) {
            String[] time = TIME[i].split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            if (currentHour > hour || currentHour == hour && currentMinute > minute) {
                continue;
            }
            return new int[] {
                hour, minute
            };
        }
        String[] time = TIME[0].split(":");
        return new int[] {
            Integer.parseInt(time[0]), Integer.parseInt(time[1])
        };
    }
}
