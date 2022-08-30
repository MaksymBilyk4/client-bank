import * as AccountActionCreator from "./account";
import * as CustomerActionCreator from "./customer";

export default {
    ...AccountActionCreator,
    ...CustomerActionCreator,
}