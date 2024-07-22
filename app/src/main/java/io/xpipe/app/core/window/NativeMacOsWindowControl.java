package io.xpipe.app.core.window;

import com.sun.jna.NativeLong;
import io.xpipe.app.core.AppProperties;
import io.xpipe.app.util.NativeBridge;
import io.xpipe.core.util.ModuleHelper;
import javafx.stage.Window;
import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.Method;

@Getter
public class NativeMacOsWindowControl {

    private final long nsWindow;

    @SneakyThrows
    public NativeMacOsWindowControl(Window stage) {
        Method tkStageGetter = Window.class.getDeclaredMethod("getPeer");
        tkStageGetter.setAccessible(true);
        Object tkStage = tkStageGetter.invoke(stage);
        Method getPlatformWindow = tkStage.getClass().getDeclaredMethod("getPlatformWindow");
        getPlatformWindow.setAccessible(true);
        Object platformWindow = getPlatformWindow.invoke(tkStage);
        Method getNativeHandle = platformWindow.getClass().getMethod("getNativeHandle");
        getNativeHandle.setAccessible(true);
        Object nativeHandle = getNativeHandle.invoke(platformWindow);
        this.nsWindow = (long) nativeHandle;
    }

    public boolean setAppearance(boolean seamlessFrame, boolean darkMode) {
        if (!ModuleHelper.isImage() || !AppProperties.get().isFullVersion()) {
            return false;
        }

        var lib = NativeBridge.getMacOsLibrary();
        if (lib.isEmpty()) {
            return false;
        }

        lib.get().setAppearance(new NativeLong(nsWindow), seamlessFrame, darkMode);
        return true;
    }
}
