import { React, useState } from "react"
import '../style/Post.css';
import { formatDate } from '../Tools.js';
import Comments from "./Comments";

export default function Post(props) {

    const [isCommentVisible, setIsCommentVisible] = useState(false);
    const [post, setPost] = useState([]);
    const { billet, user, connected } = props;


    return (
        <>
            <div className="post">
                <div className="title">
                    <h2>{billet.title}</h2>
                    {user.roleId === 1 && connected && <div className="post-moderation">
                        <img src="/assets/edit.svg" alt="edit" />
                        <img src="/assets/croix.svg" alt="delete" />
                        <img src={billet.isModerated ? "/assets/attentionrouge.svg" : "/assets/attentionvert.svg"} alt="" />
                    </div>}
                    {billet.userId === user.id && connected && <div className="post-moderation">
                        <img src="/assets/edit.svg" alt="edit" />
                        <img src="/assets/croix.svg" alt="delete" />
                    </div>
                    }

                </div>
                <div className="content">
                    <p dangerouslySetInnerHTML={{ __html: billet.content }}></p>
                </div>
                <div className="footer">
                    <p className="author">{billet.author}</p>
                    <p className="date">{formatDate(billet.createdAt)}</p>
                    <img className="eye" src="/assets/eye.svg" alt="eye" onClick={() => setIsCommentVisible(!isCommentVisible)} />
                </div>
            </div>
            {isCommentVisible && <Comments {...props} comments={billet.comments} />}
        </>
    )
}