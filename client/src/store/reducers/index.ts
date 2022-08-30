import {combineReducers} from "redux";
import {accountReducer} from "./accountReducer";
import {customerReducer} from "./customerReducer";

export const rootReducer = combineReducers({
    account: accountReducer,
    customer: customerReducer,
});

export type RootState = ReturnType<typeof rootReducer>