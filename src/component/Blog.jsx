import {React, useEffect, useState} from "react";
import Post from "./Post";
import Menu from "./Menu";
import '../style/Blog.css';
import Popup from "./Popup";
import authHeader from "../services/auth.headers";
import NewPost from "./NewPost";
import { parseJwt } from "../Tools";

export default function Blog() {

    /* STATES */
    
    let [database, setDatabase] = useState([]);
    let [filteredDatabase, setFilterDatabase] = useState([]);
    let [user, setUser] = useState([]);
    let [connected, setConnected] = useState(false);
    let [isVisiblePopup, setIsVisiblePopup] = useState(false);
    let [isNewPostVisible, setIsNewPostVisible] = useState(false);

    const props = {connected, setConnected, isVisiblePopup, setIsVisiblePopup, user, 
                  setUser, isNewPostVisible, setIsNewPostVisible};

    /* EFFECTS */

    const updateDatabase = () => {
        let token = localStorage.getItem("token")
        let URL = "http://localhost:8080/api/posts/";
        if (token != null) {
            setConnected(true);
            setUser(parseJwt(token));
            URL += "connected";
        }
        fetch(URL, {headers : authHeader()})
            .then(response => response.json())
            .then(data => {
                data.sort((a, b) => b.createdAt.localeCompare(a.createdAt));
                setDatabase(data);
                setFilterDatabase(data);
            })
            .catch(e => {
                setConnected(false);
                localStorage.removeItem("user");
                localStorage.removeItem("token");
            });
    }

    useEffect(updateDatabase, [connected]);

    /* VIEW */

    return (
        <>
            <Menu {...props} />
            {isNewPostVisible && connected && <NewPost user={user} {...{updateDatabase}} />}
            {database.length === 0 ? "loading..." : 
            <div className="posts">{filteredDatabase.map(record => <Post key={record.id} billet={record} {...props} 
            {...{updateDatabase}} />)}</div>}
            {isVisiblePopup && <Popup {...props}/>}
        </>
    )
}

