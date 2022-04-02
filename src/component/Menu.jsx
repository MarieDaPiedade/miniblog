
import "../style/Menu.css";

export default function Menu({ connected, setConnected, setIsVisiblePopup, isNewPostVisible, setIsNewPostVisible }) {


    const handleLoginLogout = () => {
        if (connected) {
            setConnected(false)
            localStorage.removeItem("user");
            localStorage.removeItem("token");
        } else {
            setIsVisiblePopup(true);
        }
    }


    return (
        <nav className="menu">
            <ul>
                <li><img src="/assets/miniblog-logo.png" alt="logo du site" id="logo" /></li>
                <li><a href="#">Tous les posts</a></li>
                <li><a href="#">Top Posts</a></li>
                {connected && <li><img src="assets/plus.svg" alt="plus" id="plus" onClick={() => setIsNewPostVisible(!isNewPostVisible)} /></li>}
                <li className="relative"><input type="text" name="search" id="search" placeholder="rechercher" />
                    <img src="assets/loop.svg" alt="loop" id="loop" /></li>
                {connected && <li><a href="#"><img src="assets/profil.svg" alt="profil" id="profil" /></a></li>}
                <a href="#" onClick={handleLoginLogout}>{connected ? "Se déconnecter" : "Se connecter"}</a>
            </ul>
        </nav>
    )
}