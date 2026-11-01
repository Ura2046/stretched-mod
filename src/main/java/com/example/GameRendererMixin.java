package net.custom.stretched.mixin;

import net.custom.stretched.StretchedMod;
import net.minecraft.client.render.GameRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "getBasicProjectionMatrix", at = @At("RETURN"), cancellable = true)
    private void injectStretch(double fov, CallbackInfoReturnable<Matrix4f> cir) {
        Matrix4f originalMatrix = cir.getReturnValue();
        Matrix4f stretchedMatrix = new Matrix4f(originalMatrix);
        
        // Применяем коэффициент, заданный ползунком
        stretchedMatrix.scale(StretchedMod.stretchFactor, 1.0f, 1.0f);
        
        cir.setReturnValue(stretchedMatrix);
    }
}
