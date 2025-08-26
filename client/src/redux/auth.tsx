import { createSlice } from "@reduxjs/toolkit";
import { RootState } from "./store";
import { jwtDecode } from "jwt-decode";
import { AccessJwtPayload } from "../types/AccessJwtPayload";

type SliceState = {
    token: string,
    isAuthenticated: boolean,
}

const initialState: SliceState = {
    token: "",
    isAuthenticated: false,
}

export const authSlice = createSlice({
	name: "auth",
	initialState,
	reducers: {
        login: (state, action) => { //PayloadAction<SliceState>
            state.token = action.payload;
            state.isAuthenticated = true;
		},
		logout: (state) => {
            state.token = "";
            state.isAuthenticated = false;
        }

	}
});


export const authThunks = {
    ...authSlice.actions
};

export const authSelectors = {
    selectToken: (state: RootState) => state.auth.token,
    selectTokenPayload: (state: RootState) => state.auth.isAuthenticated ? jwtDecode<AccessJwtPayload>(state.auth.token) : null,
    selectIsAuthenticated: (state: RootState) => state.auth.isAuthenticated
};

export default authSlice.reducer;