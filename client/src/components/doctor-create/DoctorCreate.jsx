import { useNavigate } from "react-router-dom";
import { useForm } from "../../hooks/useForm";
import { useCreateDoctor } from "../../hooks/useDoctors";
import { useTranslation } from "react-i18next";

const initialValues = {
    name: '',
    specialization: '',
    phoneNumber: '',
    profilePictureURL: '',
    description: '',
};

export default function DoctorCreate(){
    const navigate = useNavigate();
    const { t } = useTranslation();

    const createDoctor = useCreateDoctor();

    const createHandler = async (values) => {
        try {
            const { id: doctorId } = await createDoctor(values);
            navigate(`/doctors/${doctorId}/details`);
        } catch (err) {
            alert(t('not_authorized'));
        }
    };

    const {
        values,
        changeHandler,
        submitHandler,
    } = useForm(initialValues, createHandler);

    return (
        <section id="create-page" className="auth">
            <form id="create" onSubmit={submitHandler} >
                <div className="container">
                    <h1 className="title-creat-edit">{t('create_doctor')}</h1>
                    <label className="label-create-edit" htmlFor="doc-name">{t('doctor_name')}:</label>
                    <input 
                        type="text" 
                        id="name" 
                        name="name" 
                        value={values.name}
                        minLength="2"
                        maxLength="20"
                        required
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
                        type="tel" 
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
                    <input className="btn submit" type="submit" value={t('create')} />
                </div>
            </form>
        </section>
    );
}
