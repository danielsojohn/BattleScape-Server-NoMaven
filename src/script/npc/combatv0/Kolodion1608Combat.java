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

public class Kolodion1608Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.KOLODION_1608);
        combat.spawn(NpcCombatSpawn.builder().lock(4).phrase("Now you feel it... The dark energy.").animation(715).graphic(new Graphic(86, 100)).respawnId(NpcId.KOLODION_112).deathDelay(8).build());
        combat.hitpoints(NpcCombatHitpoints.total(78));
        combat.stats(NpcCombatStats.builder().magicLevel(60).bonus(CombatBonus.ATTACK_MAGIC, 16).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.combatScript("KolodionCS").deathAnimation(714).blockAnimation(399);

        var style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(20).splashOnMiss(true).build());
        style.animation(812).attackSpeed(4);
        style.targetGraphic(new Graphic(76, 100));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(20).splashOnMiss(true).build());
        style.animation(812).attackSpeed(4);
        style.targetGraphic(new Graphic(77, 100));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(20).splashOnMiss(true).build());
        style.animation(812).attackSpeed(4);
        style.targetGraphic(new Graphic(78));
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
