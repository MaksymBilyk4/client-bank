import {Dispatch} from "redux";
import {CustomerAction, CustomerActionTypes} from "../../types/customer";
import axios from "axios";

export const getCustomers = () =>
    async (dispatch: Dispatch<CustomerAction>) => {
        try {
            dispatch({type: CustomerActionTypes.REQUEST});
            const response = await axios.get("http://localhost:9000/customers");
            // Timeout для иммитированой загрузки с сервера
            setTimeout(() => {
                dispatch({type: CustomerActionTypes.GET_CUSTOMERS_SUCCESS, payload: response.data});
            }, 500);
        }catch (e) {
            dispatch({
                type: CustomerActionTypes.REQUEST_ERROR,
                payload: "Failed to get customers" + String(e)
            })
        }
    }
