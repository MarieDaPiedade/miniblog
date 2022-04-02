import React from 'react';
import '../style/Comment.css'
import { formatDate } from '../Tools';

export default function Comment({ comment, user, connected }) {



    return <div className='ens-com'>
        <div className="header-com">
            <p className='name'>{comment.author}</p>
            {
                (user.id === comment.userId || user.roleId === 1) && connected && <>
                    <img className='icon1' src="/assets/edit.svg" alt="edit" />
                    <img className='icon2' src="/assets/croix.svg" alt="croix" />
                </>
            }
        </div>
        <div className="com">{comment.content}</div>
        <div className='dt'>{formatDate(comment.createdAt)}</div>
    </div>
}
