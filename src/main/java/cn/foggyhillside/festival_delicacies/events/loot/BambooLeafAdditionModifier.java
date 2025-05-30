package cn.foggyhillside.festival_delicacies.events.loot;


import com.google.common.base.Suppliers;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Supplier;

public class BambooLeafAdditionModifier extends LootModifier {
    private final Item item; // 要添加的物品

    public BambooLeafAdditionModifier(LootItemCondition[] conditionsIn, Item item) {
        super(conditionsIn);
        this.item = item;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if (context.getRandom().nextFloat() > 0.6) {
            generatedLoot.add(new ItemStack(item, 1)); // 60%概率添加物品
        }
        return generatedLoot;
    }

    // 1.18.2 序列化器（替代 Codec）
    public static class Serializer extends GlobalLootModifierSerializer<BambooLeafAdditionModifier> {
        @Override
        public BambooLeafAdditionModifier read(ResourceLocation location, JsonObject json, LootItemCondition[] conditions) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(json.get("item").getAsString()));
            return new BambooLeafAdditionModifier(conditions, item);
        }

        @Override
        public JsonObject write(BambooLeafAdditionModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("item", ForgeRegistries.ITEMS.getKey(instance.item).toString());
            return json;
        }
    }
}
