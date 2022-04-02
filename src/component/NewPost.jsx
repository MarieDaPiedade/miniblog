import '../style/NewPost.css';
import React, { useRef, useState } from 'react';
import axios from 'axios';
import authHeader from '../services/auth.headers';
import { Editor } from '@tinymce/tinymce-react';

export default function NewPost({user, updateDatabase}) {

    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    
    let editorRef = useRef(null);

    /**
     * Récupération du contenu de l'éditeur de texte Tinymce
     */
    const log = () => {
      if (editorRef.current) {
         setContent(editorRef.current.getContent());
      }
    };

    /**
     * Soumission du formulaire pour la création d'un post
     * @param {Event} e 
     */
    const handleSubmit = e => {
        e.preventDefault();
        axios.post("http://localhost:8080/api/posts/save", {title, content, userId : user.id}, { headers: authHeader() })
            .then(response => {
                setTitle("");
                setContent("");
                updateDatabase();
            });
    }

    return (
        <div id="new-post">
            <h1 className="title-post">Créé ton post</h1>
             <form onSubmit={handleSubmit}>
                <label htmlFor="title">
                    Titre du post : 
                    <input type="text" name="title" id="title" className="arrondi-gris" value={title} onChange={e => setTitle(e.target.value)} />
                </label>
           
                <Editor
                    textareaName='content'
                    onInit={(evt, editor) => editorRef.current = editor}
                    initialValue=""
                    init={{
                    height: 500,
                    menubar: false,
                    plugins: [
                        'advlist autolink lists link image charmap print preview anchor',
                        'searchreplace visualblocks code fullscreen',
                        'insertdatetime media table paste code help wordcount'
                        ],
                        toolbar: 'undo redo | formatselect | ' +
                        'bold italic backcolor | alignleft aligncenter ' +
                        'alignright alignjustify | bullist numlist outdent indent | ' +
                        'removeformat | help',
                        content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }'
                    }}
                />
        <input className="submit-post"onClick={log} type="submit" value="VALIDER" />
        </form>
            <hr />
        </div>
    );
}

