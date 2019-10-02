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
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatFocus;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import lombok.var;

public class Muttadile7562Combat extends NpcCombat {
    @Override
    public List<NpcCombatDefinition> getCombatDefinitions() {
        var drop = NpcCombatDrop.builder();
        var dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BIG_BONES)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.OVERLOAD_PLUS_4)));
        dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PRAYER_ENHANCE_PLUS_4)));
        drop.table(dropTable.build());


        var combat = NpcCombatDefinition.builder();
        combat.id(NpcId.MUTTADILE_7562);
        combat.hitpoints(NpcCombatHitpoints.total(200));
        combat.stats(NpcCombatStats.builder().attackLevel(150).rangedLevel(150).defenceLevel(138)
                .bonus(CombatBonus.MELEE_ATTACK, 71).bonus(CombatBonus.ATTACK_RANGED, 83)
                .bonus(CombatBonus.DEFENCE_MAGIC, 60).build());
        combat.aggression(NpcCombatAggression.builder().range(12).always(true).build());
        combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
        combat.focus(NpcCombatFocus.builder().keepWithinDistance(1).build());
        combat.combatScript("muttadile").deathAnimation(7426);
        combat.drop(drop.build());

        var style = NpcCombatStyle.builder();
        style.damage(NpcCombatDamage.maximum(12));
        style.animation(7420).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());

        style = NpcCombatStyle.builder();
        style.type(NpcCombatStyleType.RANGED);
        style.damage(NpcCombatDamage.builder().maximum(12).ignorePrayer(true).build());
        style.animation(7421).attackSpeed(5);
        style.projectile(NpcCombatProjectile.id(335));
        combat.style(style.build());


        return Arrays.asList(combat.build());
    }
}
