import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "./store";

type SliceState = {
    isAlive: boolean,
    lastAlive: number,
}

const initialState: SliceState = {
    isAlive: false,
    lastAlive: 0
}

export const heartbeatSlice = createSlice({
	name: "heartbeat",
	initialState,
	reducers: {
    setHeartbeat: (state, action: PayloadAction<boolean>) => {
        state.isAlive = action.payload;
        if (state.isAlive) {
            state.lastAlive = Date.now();
        }
    }
	}
});

export const heartbeatThunks = {
    ...heartbeatSlice.actions
};

export const heartbeatSelectors = {
    selectIsAlive: (state: RootState) => state.heartbeat.isAlive,
    selectLastAlive: (state: RootState) => state.heartbeat.lastAlive
};

export default heartbeatSlice.reducer;
