import {CustomerAction, CustomerActionTypes, CustomerState} from "../../types/customer";

const initialState: CustomerState = {
    customers: [],
    loading: false,
    error: null,
}

export const customerReducer = (state = initialState, action: CustomerAction) => {
    switch (action.type) {
        case CustomerActionTypes.REQUEST:
            return {loading: true, error: null, customers: []}
        case CustomerActionTypes.CREATE_CUSTOMER_SUCCESS:
            return {loading: false, error: null, customers: []}
        case CustomerActionTypes.GET_CUSTOMERS_SUCCESS:
            return {loading: false, error: null, customers: action.payload}
        case CustomerActionTypes.GET_CUSTOMER_SUCCESS:
            return {loading: false, error: null, customers: []}
        case CustomerActionTypes.DELETE_CUSTOMER_SUCCESS:
            return {loading: false, error: null, customers: []}
        case CustomerActionTypes.UPDATE_CUSTOMER_SUCCESS:
            return {loading: false, error: null, customers: []}
        case CustomerActionTypes.REQUEST_ERROR:
            return {loading: false, error: action.payload, customers: []}
        default:
            return state;
    }
}