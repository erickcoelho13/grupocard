import React from 'react';
import './App.css';
import 'h8k-components';
import logo from './img/icon.png';

import Slides from './components/Slides';
const title = "JoKenPo App";

function App({slides}) {
    return (
        <div>
            <div className="header">
                <img src={logo} alt="Logo" />JoKenPo APP
            </div>
            <div className="App">
                <Slides slides={slides} />
            </div>
        </div>
    );
}

export default App;
