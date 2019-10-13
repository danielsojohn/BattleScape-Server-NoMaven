package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.Entity;
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.Tile;
import com.palidino.osrs.model.item.RandomItem;
import com.palidino.osrs.model.npc.Npc;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTable;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTableDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.util.Utils;
import lombok.var;

public class MuttadileCombat extends NpcCombat {
    private static final Tile SPAWN_TILE = new Tile(3314, 5331, 1);
    private static final Tile BABY_SPAWN_TILE = new Tile(3310, 5317, 1);

    private Npc npc;
    private boolean loaded;
    private Npc baby;

    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder();
        var dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BIG_BONES)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.OVERLOAD_PLUS_4)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PRAYER_ENHANCE_PLUS_4)));
        drop.table(dropTable.build());


        var bossCombat1 = NpcCombatDefinition.builder();
        bossCombat1.id(NpcId.MUTTADILE);
        bossCombat1.hitpoints(NpcCombatHitpoints.total(400));
        bossCombat1.stats(NpcCombatStats.builder().attackLevel(250).magicLevel(250).rangedLevel(250).defenceLevel(220)
                .bonus(CombatBonus.MELEE_ATTACK, 88).bonus(CombatBonus.ATTACK_RANGED, 82)
                .bonus(CombatBonus.DEFENCE_MAGIC, 75).build());
        bossCombat1.aggression(NpcCombatAggression.builder().range(16).always(true).build());
        bossCombat1.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        bossCombat1.focus(NpcCombatFocus.builder().disableFacingOpponent(true).disableFollowingOpponent(true).build());
        bossCombat1.deathAnimation(7426);
        bossCombat1.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.maximum(30));
        style.attackSpeed(10).attackRange(16);
        style.projectile(NpcCombatProjectile.id(335));
        bossCombat1.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.maximum(30));
        style.attackSpeed(10).attackRange(16);
        style.projectile(NpcCombatProjectile.id(335));
        style.multiTarget(true);
        bossCombat1.style(style.build());


        var bossCombat2 = NpcCombatDefinition.builder();
        bossCombat2.id(NpcId.MUTTADILE_7563);
        bossCombat2.hitpoints(NpcCombatHitpoints.total(400));
        bossCombat2.stats(NpcCombatStats.builder().attackLevel(150).rangedLevel(150).defenceLevel(138)
                .bonus(CombatBonus.MELEE_ATTACK, 71).bonus(CombatBonus.ATTACK_RANGED, 83)
                .bonus(CombatBonus.DEFENCE_MAGIC, 60).build());
        bossCombat2.aggression(NpcCombatAggression.builder().range(12).always(true).build());
        bossCombat2.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        bossCombat2.focus(NpcCombatFocus.builder().keepWithinDistance(1).build());
        bossCombat2.deathAnimation(7426);
        bossCombat2.drop(drop.build());

        style = NpcCombatStyle.builder();
        style.damage(NpcCombatDamage.maximum(30));
        style.animation(7420).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        bossCombat2.style(style.build());

        style = NpcCombatStyle.builder();
        style.damage(NpcCombatDamage.builder().maximum(60).prayerEffectiveness(0.6).build());
        style.animation(7424).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        style.multiTarget(true);
        bossCombat2.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.builder().maximum(45).prayerEffectiveness(0.5).build());
        style.animation(7421).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        bossCombat2.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.maximum(30));
        style.animation(7422).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        bossCombat2.style(style.build());


        var babyDrop = NpcCombatDrop.builder();
        dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BIG_BONES)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.OVERLOAD_PLUS_4)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PRAYER_ENHANCE_PLUS_4)));
        babyDrop.table(dropTable.build());


        var babyCombat = NpcCombatDefinition.builder();
        babyCombat.id(NpcId.MUTTADILE_7562);
        babyCombat.hitpoints(NpcCombatHitpoints.total(200));
        babyCombat.stats(NpcCombatStats.builder().attackLevel(150).rangedLevel(150).defenceLevel(138)
                .bonus(CombatBonus.MELEE_ATTACK, 71).bonus(CombatBonus.ATTACK_RANGED, 83)
                .bonus(CombatBonus.DEFENCE_MAGIC, 60).build());
        babyCombat.aggression(NpcCombatAggression.builder().range(12).always(true).build());
        babyCombat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        babyCombat.focus(NpcCombatFocus.builder().keepWithinDistance(1).build());
        babyCombat.deathAnimation(7426);
        babyCombat.drop(babyDrop.build());

        style = NpcCombatStyle.builder();
        style.damage(NpcCombatDamage.maximum(12));
        style.animation(7420).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        babyCombat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.builder().maximum(12).ignorePrayer(true).build());
        style.animation(7421).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        babyCombat.style(style.build());


        return Arrays.asList(bossCombat1.build(), bossCombat2.build(), babyCombat.build());
    }

    @Override
    public void spawnHook() {
        npc = getNpc();
    }

    @Override
    public void restoreHook() {
        npc.getWorld().removeNpc(baby);
    }

    @Override
    public void tickStartHook() {
        if (!loaded) {
            loadProfile();
            return;
        }
        if (!npc.getController().isRegionLoaded()) {
            return;
        }
        if (baby != null && npc.getId() == NpcId.MUTTADILE && (!baby.isVisible() || baby.isDead())) {
            npc.getCombat().clear();
            npc.setTransformationId(NpcId.MUTTADILE_7563);
            npc.setAnimation(7423);
            npc.setLock(7);
            npc.getMovement().clear();
            npc.getMovement().addMovement(npc.getX() - 6, npc.getY());
        }
    }

    @Override
    public HitType attackTickHitTypeHook(HitType hitType, Entity opponent) {
        if (hitType == HitType.MAGIC && Utils.randomE(5) != 0) {
            return HitType.RANGED;
        }
        return hitType;
    }

    @Override
    public boolean canBeAttackedHook(Entity opponent, boolean sendMessage, HitType hitType) {
        return npc.getId() != NpcId.MUTTADILE;
    }

    private void loadProfile() {
        if (loaded || npc.getController().getPlayers().isEmpty()) {
            return;
        }
        loaded = true;
        npc.getSpawnTile().setTile((npc.getId() == NpcId.MUTTADILE_7562) ? BABY_SPAWN_TILE : SPAWN_TILE);
        npc.setTile(npc.getSpawnTile());
        var averageHP = 0;
        var playerMultiplier = 1.0;
        var players = npc.getController().getPlayers();
        for (var player : players) {
            averageHP += player.getMaxHitpoints();
            playerMultiplier = Utils.addDoubles(playerMultiplier, 0.5);
        }
        averageHP /= players.size();
        var hitpoints = (int) ((50 + (players.size() * 25) + (averageHP * 2)) * playerMultiplier);
        if (npc.getId() == NpcId.MUTTADILE_7562) {
            npc.setMaxHitpoints(hitpoints / 2);
        } else {
            npc.setMaxHitpoints(hitpoints);
            npc.setTransformationId(NpcId.MUTTADILE);
            baby = new Npc(npc.getController(), NpcId.MUTTADILE_7562, BABY_SPAWN_TILE);
            baby.getController().setMultiCombatFlag(true);
            baby.setMoveDistance(4);
        }
        npc.setHitpoints(npc.getMaxHitpoints());
    }
}
