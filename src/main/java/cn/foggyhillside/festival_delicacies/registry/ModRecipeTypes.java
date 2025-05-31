package cn.foggyhillside.festival_delicacies.registry;

import cn.foggyhillside.festival_delicacies.FestivalDelicacies;
import cn.foggyhillside.festival_delicacies.recipe.StoveRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

//public class ModRecipeTypes {
//    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE, FestivalDelicacies.MOD_ID);
//
//    public static final RegistryObject<RecipeType<StoveRecipe>> STOVE_RECIPE = RECIPE_TYPES.register("stove", () -> registerRecipeType("stove"));
//    public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
//        return new RecipeType<>()
//        {
//            public String toString() {
//                return FestivalDelicacies.MOD_ID + ":" + identifier;
//            }
//        };
//    }
//}

public class ModRecipeTypes {
    // 1. 定义自定义 RecipeType 实例（必须重写 toString() 返回 "modid:identifier"）
    public static final RecipeType<StoveRecipe> STOVE_RECIPE = new RecipeType<StoveRecipe>() {
        @Override
        public String toString() {
            return FestivalDelicacies.MOD_ID + ":stove"; // 配方 JSON 中 type 字段需匹配此值
        }
    };

    // 2. 在 FMLCommonSetupEvent 中，手动注册到 Minecraft 原生 Registry
    public static void register(FMLCommonSetupEvent event) {
        Registry.register(
                Registry.RECIPE_TYPE, // Minecraft 原生的 RecipeType 注册器
                new ResourceLocation(FestivalDelicacies.MOD_ID, "stove"), // 资源定位符（需与 toString() 一致）
                STOVE_RECIPE // 要注册的 RecipeType 实例
        );
    }
}