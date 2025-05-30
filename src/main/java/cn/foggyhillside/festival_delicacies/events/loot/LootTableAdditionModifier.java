package cn.foggyhillside.festival_delicacies.events.loot;


import com.google.common.base.Suppliers;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class LootTableAdditionModifier extends LootModifier {
    private final ResourceLocation lootTable;

    protected LootTableAdditionModifier(LootItemCondition[] conditionsIn, ResourceLocation lootTable) {
        super(conditionsIn);
        this.lootTable = lootTable;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> list, LootContext lootContext) {
        LootTable extraTable = lootContext.getLootTable(this.lootTable);
        extraTable.getRandomItemsRaw(lootContext, list::add);
        return list;
    }

    public static class Serializer extends GlobalLootModifierSerializer<LootTableAdditionModifier> {
        @Override
        public LootTableAdditionModifier read(ResourceLocation location, JsonObject json, LootItemCondition[] conditions) {
            ResourceLocation lootTable = new ResourceLocation(json.get("lootTable").getAsString());
            return new LootTableAdditionModifier(conditions, lootTable);
        }

        @Override
        public JsonObject write(LootTableAdditionModifier instance) {
            JsonObject json = new JsonObject();
            json.addProperty("lootTable", instance.lootTable.toString());
            return json;
        }
    }
}
