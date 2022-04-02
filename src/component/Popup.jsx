import { React, useState } from 'react';
import '../style/Popup.css';
import Connexion from './Connexion';
import Inscription from './Inscription';

export default function Popup({ setIsVisiblePopup, setConnected, setUser }) {

    const [isLoginVisible, setIsLoginVisible] = useState(true);
    const [loginMessage, setLoginMessage] = useState(null);
    const [loginSuccess, setLoginSuccess] = useState(null);

    const props = { setIsLoginVisible, setConnected, setUser, setIsVisiblePopup, loginMessage, setLoginMessage, loginSuccess, setLoginSuccess };


    /**
     * Permet de dévisualiser la pop-up dès qu'un évènement se produit 
     * (ici dès que l'on clique sur l'écran, en dehors de la pop-in)
     * @param {Event} e 
     */
    let handleBackgroundClic = (event) => {
        setIsVisiblePopup(false);
    }


    return <div className='background' onClick={handleBackgroundClic}>
        <div className="popup" onClick={(event) => event.stopPropagation()}>
            <div className='popup-header'>
                <button className={`btn ${isLoginVisible && 'active'}`} onClick={() => setIsLoginVisible(true)}>Se connecter</button>
                <button className={`btn ${!isLoginVisible && 'active'}`} onClick={() => setIsLoginVisible(false)}>S'inscrire</button>
            </div>
            {isLoginVisible ? <Connexion {...props} /> : <Inscription {...props} />}
        </div>
    </div>;
}


