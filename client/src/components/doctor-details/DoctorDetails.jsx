import { useState } from "react";
import { useParams , Link} from "react-router-dom";
import commentsApi from "../../api/comments-api";
import "./doctor-details.css";
import { useGetOneDoctor } from "../../hooks/useDoctors";
import { useAuthContext } from "../../contexts/AuthContext";
import { format } from "date-fns";

export default function DoctorDetails(){

    const { doctorId} = useParams();
    const [comment, setComment] = useState('');
    const [doctor, setDoctor] = useGetOneDoctor(doctorId);
    const {email, isAuthenticated, role} = useAuthContext();
    const [show, toggleShow] = useState(false);


    
    const commentSubmitHandler = async (e) => {
        e.preventDefault();
        
        const newComment =  await commentsApi.create(doctorId, email, comment);
        
        setDoctor(prevState => ({
            ...prevState,
            comments: [...prevState.comments, newComment]
        }));

        console.log(newComment);
        
        setComment("");
    }
    const commentDeleteHandler = async (commentId) => {
        await commentsApi.del(doctorId, commentId);

        setDoctor(prevState => ({
            ...prevState,
            comments: prevState.comments.filter(comment => comment.id !== commentId)
        }));
    }

    return(
       
        // <!--Details Page-->
        <section id="doctor-details">
            <h1>Doctor Details</h1>
            <div className="info-section">

                <div className="doctor-header">
                    <img className="doctor-img" src={doctor.profilePictureURL} />
                    <h1>{doctor.name}</h1>
                    <p className="type">Specialization: {doctor.specialization}</p>
                    <span className="levels">Phone Number: {doctor.phoneNumber}</span>
                </div>

                <p className="text" >
                    {doctor.description}
                </p>
                

                   {role === "ADMIN" &&(
                <div className="buttons">
                    <Link to={`/doctors/${doctorId}/edit`} className="button">Edit</Link>
                    <Link to={`/doctors/${doctorId}/delete`} className="button second">Delete</Link>
                </div>
                )}

                {/* <!-- Bonus ( for Guests and Users ) --> */}                
                <div className="details-comments">
                    <h2>Comments:</h2> 
                    <button className="styled-button" onClick={() => toggleShow(!show)}>
                        {show ? "Hide" : "Show"}
                    </button>
                    {show &&(
                        <ul className="comments">
                            {Object.keys(doctor.comments || {}).length > 0
                                ? Object.values(doctor.comments).map(comment => (
                                        <li key={comment.id} className="comment">
                                            <p>{comment.author}: {comment.content}</p>
                                            <div >
                                                <span className="data"> {format(comment.timeOnPost, "yyyy MMMM d,  H:MM")}</span>
                                            </div>
                                            <div className="delete-button-div">
                                            {comment.authorEmail === email || role === "ADMIN"
                                                ?<button onClick={() => commentDeleteHandler(comment.id)}>Delete</button>
                                                :<></>
                                            }
                                            </div>
                                        </li>
                                ))
                                : <p className="no-comment">No comments yet.</p>                    
                            }
                
                        </ul>
                    )}
                   
                </div>

             
            </div>

          
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