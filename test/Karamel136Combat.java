package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatEffect;
import com.palidino.osrs.model.player.Skills;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.npc.combatscript.NCombatScript;
import lombok.var;

public class Karamel136Combat extends NCombatScript {
    @Override
    public List<NpcCombatDefinition> getCombatDefs() {
        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.KARAMEL_136);
        combat.hitpoints(NpcCombatHitpoints.total(250));
        combat.stats(NpcCombatStats.builder().rangedLevel(100).defenceLevel(100).bonus(CombatBonus.ATTACK_STAB, 50).bonus(CombatBonus.ATTACK_SLASH, 50).bonus(CombatBonus.ATTACK_CRUSH, 50).bonus(CombatBonus.ATTACK_RANGED, 134).bonus(CombatBonus.MELEE_DEFENCE, 150).bonus(CombatBonus.DEFENCE_MAGIC, 150).bonus(CombatBonus.DEFENCE_RANGED, 150).build());
        combat.aggression(NpcCombatAggression.PLAYERS);
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.combatScript("KaramelCS").deathAnimation(836).blockAnimation(424);

        var style = NpcCombatStyle.builder();
        style.damage(NpcCombatDamage.maximum(20));
        style.animation(2075).attackSpeed(3);
        style.projectile(NpcCombatProjectile.id(335));
        style.effect(NpcCombatEffect.builder().statDrain(Skills.ATTACK, 1).statDrain(Skills.DEFENCE, 1).statDrain(Skills.STRENGTH, 1).statDrain(Skills.RANGED, 1).statDrain(Skills.MAGIC, 1).build());
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.MAGIC);
        style.damage(NpcCombatDamage.builder().maximum(7).alwaysMaximum(true).build());
        style.animation(1979).attackSpeed(3);
        style.targetGraphic(new Graphic(369));
        style.projectile(NpcCombatProjectile.id(335));
        style.effect(NpcCombatEffect.builder().magicBind(34).statDrain(Skills.ATTACK, 1).statDrain(Skills.DEFENCE, 1).statDrain(Skills.STRENGTH, 1).statDrain(Skills.RANGED, 1).statDrain(Skills.MAGIC, 1).build());
        style.phrase("Semolina-Go!").attackCount(2);
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
