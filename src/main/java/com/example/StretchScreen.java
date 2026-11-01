package net.custom.stretched.gui;

import net.custom.stretched.StretchedMod;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class StretchScreen extends Screen {

    public StretchScreen() {
        super(Text.literal("Настройка растяжения экрана"));
    }

    @Override
    protected void init() {
        // Минимальное и максимальное значение ползунка (БЕЗ ЖЕСТКИХ ЛИМИТОВ)
        final float min = 0.1f;
        final float max = 5.0f; // Поменяй на 10.0f или выше, если нужно!

        // Переводим текущее значение в проценты от 0.0 до 1.0 для корректного отображения ползунка
        double initialProgress = (StretchedMod.stretchFactor - min) / (max - min);

        // Создаем и добавляем ползунок по центру экрана
        this.addDrawableChild(new SliderWidget(this.width / 2 - 100, this.height / 2 - 10, 200, 20, 
            Text.literal("Растяг: " + String.format("%.3f", StretchedMod.stretchFactor)), initialProgress) {
            
            @Override
            protected void updateMessage() {
                // Обновляем текст на ползунке в реальном времени
                this.setMessage(Text.literal("Растяг: " + String.format("%.3f", StretchedMod.stretchFactor)));
            }

            @Override
            protected void applyValue() {
                // Пересчитываем положение ползунка в реальный коэффициент и сохраняем его без ограничений игры
                StretchedMod.stretchFactor = min + (float) this.value * (max - min);
            }
        });
    }

    @Override
    public boolean shouldPause() {
        return false; // Игра не будет вставать на паузу в одиночном режиме при открытии меню
    }
}
