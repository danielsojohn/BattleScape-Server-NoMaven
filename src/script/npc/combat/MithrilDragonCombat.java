package script.npc.combat;

import java.util.Arrays;
import java.util.List;
import com.palidino.osrs.io.cache.ItemId;
import com.palidino.osrs.io.cache.NpcId;
import com.palidino.osrs.model.CombatBonus;
import com.palidino.osrs.model.Graphic;
import com.palidino.osrs.model.item.RandomItem;
import com.palidino.osrs.model.npc.combat.NpcCombat;
import com.palidino.osrs.model.npc.combat.NpcCombatAggression;
import com.palidino.osrs.model.npc.combat.NpcCombatDefinition;
import com.palidino.osrs.model.npc.combat.NpcCombatDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTable;
import com.palidino.osrs.model.npc.combat.NpcCombatDropTableDrop;
import com.palidino.osrs.model.npc.combat.NpcCombatHitpoints;
import com.palidino.osrs.model.npc.combat.NpcCombatImmunity;
import com.palidino.osrs.model.npc.combat.NpcCombatStats;
import com.palidino.osrs.model.npc.combat.style.NpcCombatDamage;
import com.palidino.osrs.model.npc.combat.style.NpcCombatProjectile;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyle;
import com.palidino.osrs.model.npc.combat.style.NpcCombatStyleType;
import lombok.var;

public class MithrilDragonCombat extends NpcCombat {
        @Override
        public List<NpcCombatDefinition> getCombatDefinitions() {
                var drop = NpcCombatDrop.builder().rareDropTableRate(NpcCombatDropTable.CHANCE_1_IN_256)
                                .clue(NpcCombatDrop.ClueScroll.ELITE, NpcCombatDropTable.CHANCE_1_IN_350);
                var dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_1_IN_32768)
                                .broadcast(true).log(true);
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_FULL_HELM)));
                drop.table(dropTable.build());
                dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_1_IN_10000).broadcast(true)
                                .log(true);
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRACONIC_VISAGE)));
                drop.table(dropTable.build());
                dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_1_IN_64);
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11342)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11343)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11344)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11345)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11346)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11347)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11348)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11349)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11350)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11351)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11352)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11353)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11354)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11355)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11356)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11357)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11358)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11359)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11360)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11361)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11362)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11363)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11364)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11365)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.ANCIENT_PAGE_11366)));
                drop.table(dropTable.build());
                dropTable = NpcCombatDropTable.builder().chance(2.39);
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.CHEWED_BONES)));
                drop.table(dropTable.build());
                dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_UNCOMMON);
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_KNIFE, 8, 18)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_SPEAR)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_FULL_HELM)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SOUL_RUNE, 10)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_ARROW, 8)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNITE_BOLTS, 12, 21)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_JAVELIN_HEADS, 15)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SUPERATTACK_MIX_2)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SUPER_STR_MIX_2)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.SUPER_DEF_MIX_2)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.PRAYER_MIX_2)));
                drop.table(dropTable.build());
                dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_COMMON);
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_JAVELIN, 8)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_DART, 14)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_MACE)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNE_BATTLEAXE)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.BLOOD_RUNE, 27)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.COINS, 600, 876)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNITE_LIMBS)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.RUNITE_BAR, 2)));
                drop.table(dropTable.build());
                dropTable = NpcCombatDropTable.builder().chance(NpcCombatDropTable.CHANCE_ALWAYS);
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.DRAGON_BONES)));
                dropTable.drop(NpcCombatDropTableDrop.items(new RandomItem(ItemId.MITHRIL_BAR, 3)));
                drop.table(dropTable.build());


                var combat = NpcCombatDefinition.builder();
                combat.id(NpcId.MITHRIL_DRAGON_304);
                combat.hitpoints(NpcCombatHitpoints.total(255));
                combat.stats(NpcCombatStats.builder().attackLevel(268).magicLevel(168).rangedLevel(168)
                                .defenceLevel(268).bonus(CombatBonus.DEFENCE_STAB, 50)
                                .bonus(CombatBonus.DEFENCE_SLASH, 100).bonus(CombatBonus.DEFENCE_CRUSH, 70)
                                .bonus(CombatBonus.DEFENCE_MAGIC, 30).bonus(CombatBonus.DEFENCE_RANGED, 90).build());
                combat.aggression(NpcCombatAggression.PLAYERS);
                combat.immunity(NpcCombatImmunity.builder().poison(true).venom(true).build());
                combat.combatScript("mithrildragon").deathAnimation(92).blockAnimation(89);
                combat.drop(drop.build());

                var style = NpcCombatStyle.builder();
                style.type(NpcCombatStyleType.melee(CombatBonus.ATTACK_SLASH));
                style.damage(NpcCombatDamage.maximum(28));
                style.animation(80).attackSpeed(4);
                style.projectile(NpcCombatProjectile.id(335));
                combat.style(style.build());

                style = NpcCombatStyle.builder();
                style.type(NpcCombatStyleType.RANGED);
                style.damage(NpcCombatDamage.maximum(18));
                style.animation(6722).attackSpeed(4);
                style.projectile(NpcCombatProjectile.id(335));
                combat.style(style.build());

                style = NpcCombatStyle.builder();
                style.type(NpcCombatStyleType.MAGIC);
                style.damage(NpcCombatDamage.maximum(18));
                style.animation(6722).attackSpeed(4);
                style.projectile(NpcCombatProjectile.id(335));
                combat.style(style.build());

                style = NpcCombatStyle.builder();
                style.type(NpcCombatStyleType.DRAGONFIRE);
                style.damage(NpcCombatDamage.maximum(60));
                style.animation(81).attackSpeed(6);
                style.castGraphic(new Graphic(1, 100));
                style.projectile(NpcCombatProjectile.id(335));
                combat.style(style.build());


                return Arrays.asList(combat.build());
        }
}
