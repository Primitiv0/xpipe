package io.xpipe.ext.base.script;

import io.xpipe.app.core.AppResources;
import io.xpipe.app.storage.DataStoreEntryRef;
import io.xpipe.core.process.ShellDialects;

import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

@Getter
public enum PredefinedScriptStore {
    CLINK_SETUP("Clink Setup", () -> SimpleScriptStore.builder()
            .group(PredefinedScriptGroup.CLINK.getEntry())
            .minimumDialect(ShellDialects.CMD)
            .commands(file("clink.bat"))
            .build()),
    CLINK_INJECT("Clink Inject", () -> SimpleScriptStore.builder()
            .group(PredefinedScriptGroup.CLINK.getEntry())
            .minimumDialect(ShellDialects.CMD)
            .script(CLINK_SETUP.getEntry())
            .commands("""
                            clink inject --quiet
                            """)
            .build()),
    STARSHIP_BASH("Starship Bash", () -> SimpleScriptStore.builder()
            .group(PredefinedScriptGroup.STARSHIP.getEntry())
            .minimumDialect(ShellDialects.BASH)
            .commands(file("starship_bash.sh"))
            .build()),
    STARSHIP_ZSH("Starship Zsh", () -> SimpleScriptStore.builder()
            .group(PredefinedScriptGroup.STARSHIP.getEntry())
            .minimumDialect(ShellDialects.ZSH)
            .commands(file("starship_zsh.sh"))
            .build()),
    STARSHIP_FISH("Starship Fish", () -> SimpleScriptStore.builder()
            .group(PredefinedScriptGroup.STARSHIP.getEntry())
            .minimumDialect(ShellDialects.FISH)
            .commands(file("starship_fish.fish"))
            .build()),
    STARSHIP_CMD("Starship Cmd", () -> SimpleScriptStore.builder()
            .group(PredefinedScriptGroup.STARSHIP.getEntry())
            .minimumDialect(ShellDialects.CMD)
            .script(CLINK_SETUP.getEntry())
            .commands(file(("starship_cmd.bat")))
            .build()),
    STARSHIP_POWERSHELL("Starship Powershell", () -> SimpleScriptStore.builder()
            .group(PredefinedScriptGroup.STARSHIP.getEntry())
            .minimumDialect(ShellDialects.POWERSHELL)
            .commands(file("starship_powershell.ps1"))
            .build());

    private final String name;
    private final Supplier<ScriptStore> scriptStore;
    private final UUID uuid;

    @Setter
    private DataStoreEntryRef<ScriptStore> entry;

    PredefinedScriptStore(String name, Supplier<ScriptStore> scriptStore) {
        this.name = name;
        this.scriptStore = scriptStore;
        this.uuid = UUID.nameUUIDFromBytes(name.getBytes(StandardCharsets.UTF_8));
    }

    public static String file(String name) {
        AtomicReference<String> string = new AtomicReference<>();
        AppResources.with("io.xpipe.ext.base", "scripts/" + name, var1 -> {
            string.set(Files.readString(var1));
        });
        return string.get();
    }
}
