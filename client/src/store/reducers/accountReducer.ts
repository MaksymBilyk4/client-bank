import {AccountAction, AccountActionTypes, AccountState} from "../../types/account";

const initialState: AccountState = {
    accounts: [],
    loading: false,
    error: null
}

export const accountReducer = (state = initialState, action: AccountAction) => {
    switch (action.type) {
        case AccountActionTypes.REQUEST:
            return {loading: true, error: null, accounts: []}
        case AccountActionTypes.CREATE_ACCOUNT_SUCCESS:
            return {loading: false, error: null, accounts: []}
        case AccountActionTypes.GET_ACCOUNTS_SUCCESS:
            return {loading: false, error: null, accounts: action.payload}
        case AccountActionTypes.DELETE_ACCOUNT_SUCCESS:
            return {loading: false, error: null, accounts: []}
        case AccountActionTypes.TRANSFER_MONEY_SUCCESS:
            return {loading: false, error: null, accounts: []}
        case AccountActionTypes.WITHDRAW_MONEY_SUCCESS:
            return {loading: false, error: null, accounts: []}
        case AccountActionTypes.UP_MONEY_SUCCESS:
            return {loading: false, error: null, accounts: []}
        case AccountActionTypes.REQUEST_ERROR:
            return {loading: false, error: action.payload, accounts: []}
        default:
            return state;
    }
}