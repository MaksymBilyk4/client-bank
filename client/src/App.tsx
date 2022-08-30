import React, {useEffect} from 'react';
import {BrowserRouter} from "react-router-dom";
import NavBar from "./components/Navbar";
import AppRouter from "./components/AppRouter";
import {useActions} from "./hooks/useActions";

const App = () => {

    const {getCustomers, getAccounts} = useActions();

    useEffect(() => {
        getCustomers();
        getAccounts();
    }, []);
    return (
        <>
            <BrowserRouter>
                <NavBar/>
                <AppRouter/>
            </BrowserRouter>
        </>
    );
};

export default App;
