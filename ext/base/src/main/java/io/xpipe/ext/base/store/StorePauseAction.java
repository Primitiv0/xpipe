package io.xpipe.ext.base.store;

import io.xpipe.app.core.AppI18n;
import io.xpipe.app.ext.ActionProvider;
import io.xpipe.app.storage.DataStoreEntryRef;

import javafx.beans.value.ObservableValue;

import lombok.Value;

public class StorePauseAction implements ActionProvider {

    @Override
    public DataStoreCallSite<?> getDataStoreCallSite() {
        return new DataStoreCallSite<PauseableStore>() {

            @Override
            public ActionProvider.Action createAction(DataStoreEntryRef<PauseableStore> store) {
                return new Action(store);
            }

            @Override
            public Class<PauseableStore> getApplicableClass() {
                return PauseableStore.class;
            }

            @Override
            public boolean isMajor(DataStoreEntryRef<PauseableStore> o) {
                return true;
            }

            @Override
            public ObservableValue<String> getName(DataStoreEntryRef<PauseableStore> store) {
                return AppI18n.observable("pause");
            }

            @Override
            public String getIcon(DataStoreEntryRef<PauseableStore> store) {
                return "mdi2p-pause";
            }
        };
    }

    @Value
    static class Action implements ActionProvider.Action {

        DataStoreEntryRef<PauseableStore> entry;

        @Override
        public boolean requiresJavaFXPlatform() {
            return false;
        }

        @Override
        public void execute() throws Exception {
            entry.getStore().pause();
        }
    }
}
