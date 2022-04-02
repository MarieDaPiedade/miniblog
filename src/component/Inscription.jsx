import { React, useState } from 'react';
import authService from '../services/auth.service';
import {isEmail} from 'validator';

export default function Inscription({ setIsVisiblePopup, setIsLoginVisible, setLoginMessage }) {

    let [username, setUsername] = useState('');
    let [password, setPassword] = useState('');
    let [email, setEmail] = useState('');
    let [confirmPassword, setConfirmPassword] = useState('');
    let [error, setError] = useState(null);

    const validatePassword = () => {
        let isValid = true;
        if (password !== '' && confirmPassword !== ''){
          if (password !== confirmPassword) {
            isValid = false;
            setError('Erreur sur le mot de passe : merci de saisir deux mots de passe identiques.');
          }
        }
        return isValid
      }

    let handleSubmit = (e) => {
        e.preventDefault();
        // TODO vérification password
        setError('');

        if(validatePassword) {

            authService.register(username, email, password).then(response => {
                
                if (response.data > 0) { // ok
                    // TODO message de succes ?
                    console.log(response.data);
                    console.log(username);
                    setError(null);
                    setLoginMessage("Vous êtes bien enregistré. Vous pouvez maintenant vous connecter.")
                    setIsLoginVisible(true);
                } else { // error
                    // TODO personnaliser les erreurs
                    console.log(response);
                    console.log(username);
                    console.log(email);
                    console.log(password);
                    console.log(confirmPassword);
                    setError("Erreur dans le formulaire");
                }
            })
        }
        setUsername('');
        setEmail('')
        setPassword('')
        setConfirmPassword('')
    }

    return <div>
        <form className="focus" onSubmit={handleSubmit}>
            <label htmlFor='ident'>Identifiant</label>
            <input type='text' id='ident' name='ident' value={username} onChange={(e) => setUsername(e.target.value)} />

            <label htmlFor='email'>Email</label>
            <input type='email' id='email' name='email' value={email} onChange={(e) => setEmail(e.target.value)}/>

            <label htmlFor='mdp'>Mot de passe</label>
            <input type='password' id='mdp' name='mdp' value={password} onChange={(e) => setPassword(e.target.value)} />

            <label htmlFor='confirm-mdp'>Confirmation mot de passe</label>
            <input type='password' id='confirm-mdp' name='confirm-mdp' value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />

            <button type='submit' className='btn-submit'>VALIDER</button>
        </form>
        {error && <p>{error}</p>}
    </div>;
}



// import { React, useState } from 'react';
// import authService from '../services/auth.service';
// import {validateEmail} from '../Tools';

// export default function Inscription({ setIsVisiblePopup, setIsLoginVisible, setLoginSuccess }) {

//     let [username, setUsername] = useState('');
//     let [password, setPassword] = useState('');
//     let [email, setEmail] = useState('');
//     let [confirmPassword, setConfirmPassword] = useState('');
//     let [error, setErrorMessage] = useState('');

//     // let handleSubmit = (e) => {
//     //     e.preventDefault();
//     //     // TODO vérification password
    
//     //     AuthService.register(username, email, password).then(response => {
//     //         if (response.data > 0) { // ok
//     //             // TODO message de succes ?
//     //             console.log(response.data);
//     //             setErrorMessage(false);
//     //             setIsVisiblePopup(true);
//     //             setIsLoginVisible(true);
//     //             setLoginSuccess("Vous êtes bien enregistré. Vous pouvez maintenant vous connecter.")
//     //         } else { // error
//     //             // // TODO personnaliser les erreurs
//     //             // setErrorMessage("Erreur dans le formulaire");
//     //             console.log(response.data);
//     //         }
//     //     });
//     // }
//     let handleSubmit = (e) => {
//         e.preventDefault();
//          setErrorMessage(null);
//             authService.register(username, email, password).then(
//                 response => {
//                     if (response.data > 0) { 
//                         console.log(response.data);
//                                         setErrorMessage(null);
//                                         setLoginSuccess("Vous êtes bien enregistré. Vous pouvez maintenant vous connecter.")
//                                         setIsLoginVisible(true);
//                                     } 
//                                 },
//                                 error => {
//                     if((username === "") && (email === "") && (password === "") && (confirmPassword === "")) {
//                         setErrorMessage("Merci de remplir tous les champs.");
//                     }
//                     else if((username.length < 3) || (username.length > 20)) {
//                         setErrorMessage("Votre identifiant doit comporter entre 3 et 20 caractères.");
//                     } 
//                     else if(!isEmail(email)) {
//                         setErrorMessage("Merci de saisir une adresse mail valide.");
//                     } 
//                     else if(password.length < 8) {
//                         setErrorMessage("Votre mot de passe doit contenir minimum 8 caractères.");
//                     }
//                     else if(confirmPassword === "" || password !== confirmPassword) {
//                         setErrorMessage("Erreur sur le mot de passe : merci de saisir deux mots de passe identiques.");
//                     } 
//                 }
//                 );
//     }
//     // /**
//     //  * Validation des données saisies par l'utilisateur et envoi du formulaire d'inscription
//     //  * @param {Event} e 
//     //  */
//     // let handleSubmit = (e) => {
//     //     e.preventDefault();
//     //     setError(null);

//     //     if((username === "") && (email === "") && (password === "") && (confirmPassword === "")) {
//     //         setError("Merci de remplir tous les champs.");
//     //     }
//     //     else if((username.length < 3) || (username.length > 20)) {
//     //         setError("Votre identifiant doit comporter entre 3 et 20 caractères.");
//     //     } 
//     //     else if(!validateEmail(email)) {
//     //         setError("Merci de saisir une adresse mail valide.");
//     //     } 
//     //     else if(password.length < 8) {
//     //         setError("Votre mot de passe doit contenir minimum 8 caractères.");
//     //     }
//     //     else if(confirmPassword === "" || password !== confirmPassword) {
//     //         setError("Erreur sur le mot de passe : merci de saisir deux mots de passe identiques.");
//     //     } else {
//     //         AuthService.register(username, email, password).then(response => {
    
//     //             if (response.data > 0) { 
//     //                 setError(null);
//     //                 setLoginSuccess("Vous êtes bien enregistré. Vous pouvez maintenant vous connecter.")
//     //                 setIsLoginVisible(true);
//     //             } 
//     //         });
//     //      }
//     // }

//     return <div>
//         {error && <p className="error">{error}</p>}
//         <form className="focus" onSubmit={handleSubmit}>
//             <label htmlFor='ident'>Identifiant</label>
//             <input type='text' id='ident' name='ident' value={username} onChange={(e) => setUsername(e.target.value)} />

//             <label htmlFor='email'>Email</label>
//             <input type='email' id='email' name='email' value={email} onChange={(e) => setEmail(e.target.value)} />

//             <label htmlFor='mdp'>Mot de passe</label>
//             <input type='password' id='mdp' name='mdp' value={password} onChange={(e) => setPassword(e.target.value)} />

//             <label htmlFor='confirm-mdp'>Confirmation mot de passe</label>
//             <input type='password' id='confirm-mdp' name='confirm-mdp' value={confirmPassword} onChange={(e) => setConfirmPassword(e.target.value)} />

//             <button type='submit' className='btn-submit'>VALIDER</button>
//         </form>
//     </div>;
// }
