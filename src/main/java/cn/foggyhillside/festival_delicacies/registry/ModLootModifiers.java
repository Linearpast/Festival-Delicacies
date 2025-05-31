package cn.foggyhillside.festival_delicacies.registry;

import cn.foggyhillside.festival_delicacies.FestivalDelicacies;
import cn.foggyhillside.festival_delicacies.events.loot.*;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, FestivalDelicacies.MOD_ID);

    public static final RegistryObject<BambooLeafAdditionModifier.Serializer> BambooLeafSerializers = LOOT_MODIFIERS.register("bamboo_leaf", BambooLeafAdditionModifier.Serializer::new);
    public static final RegistryObject<LootTableAdditionModifier.Serializer> LootTableSerializers = LOOT_MODIFIERS.register("loot_table", LootTableAdditionModifier.Serializer::new);

    public static void register(IEventBus bus) {
        LOOT_MODIFIERS.register(bus);
    }
}
