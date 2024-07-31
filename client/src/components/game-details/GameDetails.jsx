import { useContext, useState } from "react";
import { useParams } from "react-router-dom";
import commentsApi from "../../api/comments-api";
import "./game-details.css";
import { useGetOneGames } from "../../hooks/useGames";
import { useAuthContext } from "../../contexts/AuthContext";
import { format } from "date-fns";

export default function GameDetails(){

    const { doctorId} = useParams();
    const [comment, setComment] = useState('');
    const [game, setGame] = useGetOneGames(doctorId);
    const {email, isAuthenticated, role} = useAuthContext();
    const [show, toggleShow] = useState(false);



    
    const commentSubmitHandler = async (e) => {
        e.preventDefault();

       const newComment =  await commentsApi.create(doctorId, email, comment);
       
       setGame(prevState => ({
            ...prevState,
            comments: {
                ...prevState.comments,
                [newComment.id]: newComment,
            }
       }));

       setComment("");
    }


    return(
       
        // <!--Details Page-->
        <section id="game-details">
            <h1>Doctor Details</h1>
            <div className="info-section">

                <div className="game-header">
                    <img className="game-img" src={game.profilePictureURL} />
                    <h1>{game.name}</h1>
                    <span className="levels">PhoneNumber: {game.phoneNumber}</span>
                    <p className="type">{game.specialization}</p>
                </div>

                <p className="text">
                    {game.description}
                </p>

                   {/* <!-- Edit/Delete buttons ( Only for creator of this game )  --> */}
                   {role === "ADMIN" &&(
                <div className="buttons">
                    <a href="#" className="button">Edit</a>
                    <a href="#" className="button">Delete</a>
                </div>
                )}

                {/* <!-- Bonus ( for Guests and Users ) --> */}
                <div className="details-comments">
                    <h2>Comments:</h2> 
                    <button className="styled-button" onClick={() => toggleShow(!show)}>
                        {show ? "Hide" : "Show"}
                    </button>
                    {show &&(
                        <ul>
                            {Object.keys(game.comments || {}).length > 0
                                ? Object.values(game.comments).map(comment => (
                                    <li key={comment.id} className="comment">
                                        <p>{comment.author}: {comment.content}</p>
                                        <div>
                                            <span className="data"> {format(comment.timeOnPost, "yyyy MMMM d,  H:MM")}</span>
                                        </div>
                                    </li>
                                ))
                                : <p className="no-comment">No comments.</p>                    
                            }
                
                        </ul>
                    )}
                   
                </div>

             
            </div>

            {/* <!-- Bonus -->
            <!-- Add Comment ( Only for logged-in users, which is not creators of the current game ) --> */}
            {isAuthenticated && (
                <article className="create-comment">
                    <label>Add new comment:</label>
                    <form className="form"  onSubmit={commentSubmitHandler}>
        

                        <textarea 
                            name="comment" 
                            placeholder="Comment......"
                            onChange={(e) => setComment(e.target.value)}
                            value={comment}
                        ></textarea>

                        <input className="btn submit" type="submit" value="Add Comment"/>
                    </form>
                </article>
            )};
        </section>


    );
}