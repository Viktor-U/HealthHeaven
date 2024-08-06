import { useNavigate, useParams } from "react-router-dom";
import { useForm } from "../../hooks/useForm";
import { useEditDoctor, useGetOneDoctor } from "../../hooks/useDoctors";
import { useTranslation } from "react-i18next";




const initialValues={
    id: '',
    name: '',
    specialization: '',
    phoneNumber: '',
    profilePictureURL: '',
    description: '',
};


export default function DoctorEdit() {
    const navigate = useNavigate();
    const { doctorId} = useParams();
    const { t } = useTranslation();

    
    const editDoctor = useEditDoctor();
    
    const [doctor] = useGetOneDoctor(doctorId);
    
    initialValues.id = doctorId;
    initialValues.name = doctor.name;
    initialValues.specialization = doctor.specialization;
    initialValues.phoneNumber = doctor.phoneNumber;
    initialValues.profilePictureURL = doctor.profilePictureURL;
    initialValues.description = doctor.description;


 
    const editHandler = async (values) => {
        try{
            const {id: doctorId} = await editDoctor(values);
            navigate(`/doctors/${doctorId}/details`);
        }catch(err) {
            alert("You are not authorised to do this!");
        }
    };

 

    const{
        values,
        changeHandler,
        submitHandler,
    } = useForm(initialValues, editHandler);


    return (
        <section id="edit-page" className="auth">
            <form id="edit" onSubmit={submitHandler}>
                <div className="container">

                    <h1 className="title-creat-edit">{t('edit_doctor')}</h1>
                    <label className="label-create-edit" htmlFor="doc-name">{t('doctor_name')}:</label>
                    <input 
                        type="text" 
                        id="name" 
                        name="name" 
                        minLength="2"
                        maxLength="20"
                        required
                        value={values.name}
                        onChange={changeHandler}
                        placeholder={t('enter_doctor_name')}
                    />

                    <label className="label-create-edit" htmlFor="specialization">{t('specialization')}:</label>
                    <input 
                        type="text" 
                        id="specialization" 
                        name="specialization" 
                        minLength="2"
                        maxLength="20"
                        required
                        value={values.specialization}
                        onChange={changeHandler}
                        placeholder={t('enter_specialization')}
                    />

                    <label className="label-create-edit" htmlFor="phoneNumbers">{t('phone_number')}:</label>
                    <input 
                        type="text" 
                        id="phoneNumber" 
                        name="phoneNumber" 
                        minLength="10"
                        maxLength="10"
                        required 
                        value={values.phoneNumber}
                        onChange={changeHandler}
                        placeholder={t('enter_phone_number')}
                    />

                    <label className="label-create-edit" htmlFor="doctor-img">{t('profile_picture_url')}:</label>
                    <input 
                        type="text" 
                        id="profilePictureURL" 
                        name="profilePictureURL"
                        required 
                        value={values.profilePictureURL}
                        onChange={changeHandler}
                        placeholder={t('upload_photo')}

                    />

                    <label className="label-create-edit" htmlFor="description">{t('description')}:</label>
                    <textarea 
                        name="description" 
                        id="description"
                        required 
                        minLength="10"
                        value={values.description}
                        onChange={changeHandler}    
                    >
                    </textarea>
                    <input className="btn submit" type="submit" value={t('edit')}/>

                </div>
            </form>
        </section>
    );
}