package script.npc.combatv0;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatSpawn;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Kolodion1607Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.KOLODION_1607);
        combat.spawn(NpcCombatSpawn.builder().lock(4).phrase("Foolish mortal; I am unstoppable.").animation(5324).graphic(new Graphic(86, 100)).respawnId(NpcId.KOLODION_1608).deathDelay(8).build());
        combat.hitpoints(NpcCombatHitpoints.total(65));
        combat.stats(NpcCombatStats.builder().magicLevel(60).bonus(CombatBonus.ATTACK_MAGIC, 16).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.combatScript("KolodionCS").deathAnimation(5323).blockAnimation(5320);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(20).splashOnMiss(true).build());
        style.animation(5322).attackSpeed(4);
        style.targetGraphic(new Graphic(76, 100));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(20).splashOnMiss(true).build());
        style.animation(5322).attackSpeed(4);
        style.targetGraphic(new Graphic(77, 100));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(20).splashOnMiss(true).build());
        style.animation(5322).attackSpeed(4);
        style.targetGraphic(new Graphic(78));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
