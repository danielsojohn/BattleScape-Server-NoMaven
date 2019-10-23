package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.Tile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.player.Player;
import lombok.var;

public class ElvargCombat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.ELVARG_83_6349);
        combat.hitpoints(NpcCombatHitpoints.total(80));
        combat.stats(NpcCombatStats.builder().attackLevel(70).magicLevel(70).defenceLevel(70)
                .bonus(CombatBonus.DEFENCE_STAB, 20).bonus(CombatBonus.DEFENCE_SLASH, 40)
                .bonus(CombatBonus.DEFENCE_CRUSH, 40).bonus(CombatBonus.DEFENCE_MAGIC, 30)
                .bonus(CombatBonus.DEFENCE_RANGED, 20).build());
        combat.aggression(NpcCombatAggression.builder().range(4).build());
        combat.deathAnimation(92).blockAnimation(89);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MELEE_SLASH);
        style.damage(NpcCombatDamage.maximum(8));
        style.animation(80).attackSpeed(4);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.DRAGONFIRE);
        style.damage(NpcCombatDamage.maximum(50));
        style.animation(81).attackSpeed(4);
        style.castGraphic(new Graphic(1, 100));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }

    @Override
    public void deathDropItemsHook(Player player, int additionalPlayerLoopCount, Tile dropTile) {
        player.getCombat().setDragonSlayer(true);
        player.getMovement().teleport(3109, 3514);
        player.getGameEncoder().sendMessage("<col=ff0000>You have completed Dragon Slayer!");
        player.getInventory().addOrDropItem(ItemId.COINS, 25_000);
    }
}
