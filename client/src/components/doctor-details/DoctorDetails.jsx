import { useState } from "react";
import { useParams, Link } from "react-router-dom";
import commentsApi from "../../api/comments-api";
import "./doctor-details.css";
import { useGetOneDoctor } from "../../hooks/useDoctors";
import { useAuthContext } from "../../contexts/AuthContext";
import { format } from "date-fns";
import { useTranslation } from "react-i18next";

export default function DoctorDetails() {
    const { doctorId } = useParams();
    const [comment, setComment] = useState('');
    const [doctor, setDoctor] = useGetOneDoctor(doctorId);
    const { email, isAuthenticated, role } = useAuthContext();
    const [show, toggleShow] = useState(false);
    const { t } = useTranslation();

    const commentSubmitHandler = async (e) => {
        e.preventDefault();

        const newComment = await commentsApi.createDoctorComment(doctorId, email, comment);
       
        setDoctor(prevState => ({
            ...prevState,
            comments: [...prevState.comments, newComment]
        }));
        setComment("");
    }

    const commentDeleteHandler = async (commentId) => {
        await commentsApi.deleteDoctorComment(doctorId, commentId);
        setDoctor(prevState => ({
            ...prevState,
            comments: prevState.comments.filter(comment => comment.id !== commentId)
        }));
    }

    return (
        <section id="doctor-details">
            <h1>{t('doctor_details')}</h1>
            <div className="info-section">
                <div className="doctor-header">
                    <img className="doctor-img" src={doctor.profilePictureURL} alt={doctor.name} />
                    <h1>{doctor.name}</h1>
                    <p className="type">{t('specialization')}: {doctor.specialization}</p>
                    <span className="levels">{t('phone_number')}: {doctor.phoneNumber}</span>
                </div>
                <p className="text">{doctor.description}</p>
                {role === "ADMIN" && (
                    <div className="buttons">
                        <Link to={`/doctors/${doctorId}/edit`} className="button">{t('edit')}</Link>
                        <Link to={`/doctors/${doctorId}/delete`} className="button second">{t('delete')}</Link>
                    </div>
                )}
                <div className="details-comments">
                    <h2>{t('comments')}</h2>
                    <button className="styled-button" onClick={() => toggleShow(!show)}>
                        {show ? t('hide') : t('show')}
                    </button>
                    {show && (
                        <ul className="comments">
                            {Object.keys(doctor.comments || {}).length > 0
                                ? Object.values(doctor.comments).map(comment => (
                                    <li key={comment.id} className="comment">
                                        <p>{comment.author}: {comment.content}</p>
                                        <div>
                                            <span className="data"> {format(new Date(comment.timeOnPost), "yyyy MMMM do, H:mma")}</span>
                                        </div>
                                        <div className="delete-button-div">
                                            {comment.authorEmail === email || role === "ADMIN"
                                                ? <button onClick={() => commentDeleteHandler(comment.id)}>{t('delete')}</button>
                                                : null
                                            }
                                        </div>
                                    </li>
                                ))
                                : <p className="no-comment">{t('no_comments')}</p>
                            }
                        </ul>
                    )}
                </div>
            </div>
            {isAuthenticated && (
                <article className="create-comment">
                    <label>{t('add_new_comment')}:</label>
                    <form className="form" onSubmit={commentSubmitHandler}>
                        <textarea
                            name="comment"
                            placeholder={t('comment_placeholder')}
                            onChange={(e) => setComment(e.target.value)}
                            value={comment}
                        ></textarea>
                        <input className="btn submit" type="submit" value={t('add_comment')} />
                    </form>
                </article>
            )}
        </section>
    );
}
