import { combineReducers } from 'redux';
import account from "./account";
import builder from "./builder";

const rootReducer = combineReducers({account,builder});

export default rootReducer;
