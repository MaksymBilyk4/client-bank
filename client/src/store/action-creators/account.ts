import {AccountAction, AccountActionTypes} from "../../types/account";
import {Dispatch} from "redux";
import axios from "axios";

export const getAccounts = () =>
    async (dispatch: Dispatch<AccountAction>) => {
        try {
            dispatch({type: AccountActionTypes.ACCOUNT_REQUEST});
            const response = await axios.get("http://localhost:9000/accounts");
            // Timeout для иммитированой загрузки с сервера
            setTimeout(() => {
                dispatch({type: AccountActionTypes.GET_ACCOUNTS_SUCCESS, payload: response.data});
            }, 500)
        } catch (e) {
            dispatch({
                type: AccountActionTypes.REQUEST_ERROR,
                payload: "Failed to get accounts" + String(e)
            })
        }
    }