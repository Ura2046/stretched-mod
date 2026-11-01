package net.custom.stretched;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.custom.stretched.gui.StretchScreen;
import org.lwjgl.glfw.GLFW;

public class StretchedMod implements ModInitializer {
    public static final String MOD_ID = "stretchedmod";
    
    // Текущий коэффициент растяжения
    public static float stretchFactor = 1.0f;
    
    // Создаем привязку клавиши B
    private static KeyBinding openGuiKey;

    @Override
    public void onInitialize() {
        // Регистрируем клавишу B в категории "Stretched Mod"
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.stretchedmod.open", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_B, 
                "category.stretchedmod"
        ));

        // Каждый игровой тик проверяем, нажата ли клавиша B
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player != null && openGuiKey.wasPressed()) {
                // Открываем наш кастомный экран с ползунком
                client.setScreen(new StretchScreen());
            }
        });
    }
}
