import React, { useState } from 'react';
import AuthService from "../services/auth.service";

export default function Connexion({ setConnected, setIsVisiblePopup, loginMessage, setLoginMessage, loginSuccess, setLoginSuccess}) {

    let [ident, setIdent] = useState('');
    let [password, setPassword] = useState('');
   

    let handleSubmit = (e) => {
        e.preventDefault();
        setLoginMessage(null);
            AuthService.login(ident, password).then(
                () => {
                    setLoginMessage(null);
                    setConnected(true);
                    setIsVisiblePopup(false);
                },
                _error => {
                    if(ident === "") {
                        setLoginMessage("Merci de saisir votre identifiant");
                    } else if(password === "") {
                        setLoginMessage("Merci de saisir votre mot de passe");
                    } else {
                        setLoginMessage("Identifiant ou mot de passe incorrect");
                    }
                }
            );
    }

    return <div>
        {loginMessage && <p className="error">{loginMessage}</p>}
        <form className="focus" onSubmit={handleSubmit}>
            <label htmlFor='ident'>Identifiant
                <input type='text' id='ident' name='ident' value={ident} 
                       onChange={(e) => setIdent(e.target.value)} />
            </label>
            <label htmlFor='mdp'>Mot de passe
                <input type='password' id='mdp' name='mdp' value={password} 
                       onChange={(e) => setPassword(e.target.value)} />
            </label>
            <button type='submit' className='btn-submit'>VALIDER</button>
        </form>
        {loginMessage && <p className="success">{loginMessage}</p>}
    </div>;
}
