package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTable;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTableDrop;
import com.palidino.osrs.model.item.RandomItem;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.model.npc.combat.NpcCombatSpawn;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.HitpointsBar;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatKillCount;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.HitType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatEffect;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Sotetseg995_16017Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder().underKiller(true).rareDropTableRate(NpcCombatDropTable.CHANCE_1_IN_256);
        var dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BLOODY_KEY)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.SOTETSEG_995_16017);
        combat.spawn(NpcCombatSpawn.builder().respawnDelay(36000).build());
        combat.hitpoints(NpcCombatHitpoints.builder().total(4000).bar(HitpointsBar.GREEN_RED_120).build());
        combat.stats(NpcCombatStats.builder().attackLevel(250).magicLevel(250).rangedLevel(250).defenceLevel(200)
                .bonus(CombatBonus.MELEE_DEFENCE, 70).bonus(CombatBonus.DEFENCE_MAGIC, 30)
                .bonus(CombatBonus.DEFENCE_RANGED, 150).build());
        combat.aggression(NpcCombatAggression.builder().range(8).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.killCount(NpcCombatKillCount.builder().sendMessage(true).build());
        combat.combatScript("SotetsegCS").deathAnimation(8140);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.builder().maximum(50).delayedPrayerProtectable(true).build());
        style.animation(8138).attackSpeed(5);
        style.projectile(
                NpcCombatProjectile.builder().id(1607).speedType(HitType.MAGIC).speedMinimumDistance(14).build());
        style.effect(NpcCombatEffect.builder().damageProtectionPrayerBlock(8).build());
        style.multiTarget(true);
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(50).delayedPrayerProtectable(true).build());
        style.animation(8138).attackSpeed(5);
        style.projectile(
                NpcCombatProjectile.builder().id(1606).speedType(HitType.MAGIC).speedMinimumDistance(14).build());
        style.effect(NpcCombatEffect.builder().damageProtectionPrayerBlock(8).build());
        style.multiTarget(true);
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
