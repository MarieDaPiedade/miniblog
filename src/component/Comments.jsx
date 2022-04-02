import React from 'react';
import '../style/Comments.css';
import Comment from './Comment';
import axios from 'axios';
import authHeader from '../services/auth.headers';

export default function Comments(props) {

    const { comments, connected, user, billet, updateDatabase } = props;
    const [content, setContent] = React.useState("");

    const handleSubmit = e => {
        e.preventDefault();
        axios.post("http://localhost:8080/api/comments/save", 
                    {content, userId : user.id, postId : billet.id }, 
                    { headers: authHeader() })
            .then(response => {
                setContent("");
                updateDatabase();
            })
    }

    return <div>
        {connected &&
            <div className="addComment">
                <form onSubmit={handleSubmit}>
                    <input type="text" className='arrondi-gris' placeholder='Ajouter un commentaire...' 
                           value={content} onChange={e => setContent(e.target.value)} />
                    <button type="submit" className='publier'>Publier</button>
                </form>
            </div>}
        {comments.length > 0 ? comments.filter(comment => !comment.isModerated)
                 .map(comment => <Comment key={comment.id} comment={comment} {...props} />) : 
                 <p className="ens-com">Aucun commentaire pour le moment</p>}
    </div>;
}


