import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./auth";
import storage from 'redux-persist/lib/storage';
import { persistReducer, persistStore } from "redux-persist";
import heartbeatReducer from "./heartbeat";

const persistedAuthReducer = persistReducer({
    key: 'auth',
    storage,
}, authReducer)

export type RootState = ReturnType<typeof store.getState>

export type AppDispatch = typeof store.dispatch

export const store = configureStore({
    reducer: { 
        auth: persistedAuthReducer,
        heartbeat: heartbeatReducer
    },
    //middleware: [thunk]
})

export const persistor = persistStore(store)