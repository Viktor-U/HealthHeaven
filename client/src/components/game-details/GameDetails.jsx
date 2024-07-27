import { useEffect, useState } from "react";
import gamesAPI from "../../api/games-api";
import { useParams } from "react-router-dom";
import commentsApi from "../../api/comments-api";

export default function GameDetails(){

    const [game, setGame] = useState({});
    const [username, setUsername] = useState('');
    const [comment, setComment] = useState('');
    const { gameId} = useParams();

    useEffect(() => {
        (async () => {
            const result = await gamesAPI.getOne(gameId);

            setGame(result);
        })();
    }, []);

    
    const commentSubmitHandler = async (e) => {
        e.preventDefault();

       const newComment =  await commentsApi.create(gameId, username, comment);
       //todo
       setGame(prevState => ({
            ...prevState,
            comments: {
                ...prevState.comments,
                [newComment.id]: newComment,
            }
       }));

       setUsername("");
       setComment("");
    }


    return(
       
        // <!--Details Page-->
        <section id="game-details">
            <h1>Game Details</h1>
            <div className="info-section">

                <div className="game-header">
                    <img className="game-img" src={game.profilePictureURL} />
                    <h1>{game.name}</h1>
                    <span className="levels">MaxLevel: {game.phoneNumber}</span>
                    <p className="type">{game.specialization}</p>
                </div>

                <p className="text">
                    {game.description}
                </p>

                {/* <!-- Bonus ( for Guests and Users ) --> */}
                <div className="details-comments">
                    <h2>Comments:</h2>

                    <ul>
                        {Object.keys(game.comments || {}).length > 0
                            ? Object.values(game.comments).map(comment => (
                                <li key={comment.id} className="comment">
                                    <p>{comment.author}: {comment.content}</p>
                                </li>
                            ))
                            : <p className="no-comment">No comments.</p>                    
                        }
              
                    </ul>

                   
                </div>

                {/* <!-- Edit/Delete buttons ( Only for creator of this game )  --> */}
                <div className="buttons">
                    <a href="#" className="button">Edit</a>
                    <a href="#" className="button">Delete</a>
                </div>
            </div>

            {/* <!-- Bonus -->
            <!-- Add Comment ( Only for logged-in users, which is not creators of the current game ) --> */}
            <article className="create-comment">
                <label>Add new comment:</label>
                <form className="form"  onSubmit={commentSubmitHandler}>
                    <input
                        type="text" 
                        placeholder="Pesho" 
                        name="username"
                        onChange={(e) => setUsername(e.target.value)}
                        value={username}
                    />

                    <textarea 
                        name="comment" 
                        placeholder="Comment......"
                        onChange={(e) => setComment(e.target.value)}
                        value={comment}
                    ></textarea>

                    <input className="btn submit" type="submit" value="Add Comment"/>
                </form>
            </article>

        </section>


    );
}