import { useNavigate } from "react-router-dom";
import { useForm } from "../../hooks/useForm";
import { useCreateDoctor } from "../../hooks/useDoctors";


const initialValues = {
    name: '',
    specialization: '',
    phoneNumber: '',
    profilePictureURL: '',
    description: '',
};

export default function DoctorCreate(){
    const navigate = useNavigate();

    const createDoctor = useCreateDoctor();

    const createHandler = async (values) => {
        try{
            const {id: doctorId} = await createDoctor(values);
            navigate(`/doctors/${doctorId}/details`);
        }catch(err) {
            alert("You are not authorised to do this!");
        }
    };

    const{
        values,
        changeHandler,
        submitHandler,
    } = useForm(initialValues, createHandler);

    return(
        <section id="create-page" className="auth">
            <form id="create" onSubmit={submitHandler} >
                <div className="container">

                    <h1 className="title-creat-edit">Create Doctor</h1>
                    <label className="label-create-edit" htmlFor="doc-name">Doctor Name:</label>
                    <input 
                        type="text" 
                        id="name" 
                        name="name" 
                        value={values.name}
                        minLength="2"
                        maxLength="20"
                        required
                        onChange={changeHandler}
                        placeholder="Enter doctor name..."
                    />

                    <label className="label-create-edit" htmlFor="specialization">Specialization:</label>
                    <input 
                        type="text" 
                        id="specialization" 
                        name="specialization" 
                        minLength="2"
                        maxLength="20"
                        required
                        value={values.specialization}
                        onChange={changeHandler}
                        placeholder="Enter doctor specialization..."
                    />

                    <label className="label-create-edit" htmlFor="phoneNumbers">Phone Number:</label>
                    <input 
                        type="tel" 
                        id="phoneNumber" 
                        name="phoneNumber"
                        minLength="10"
                        maxLength="10"
                        required 
                        value={values.phoneNumber}
                        onChange={changeHandler}
                        placeholder="0893664222"
                    />

                    <label className="label-create-edit" htmlFor="doctor-img">Profile PictureURL:</label>
                    <input 
                        type="text" 
                        id="profilePictureURL" 
                        name="profilePictureURL"
                        required 
                        value={values.profilePictureURL}
                        onChange={changeHandler}
                        placeholder="Upload a photo..."

                    />

                    <label className="label-create-edit" htmlFor="description">Description:</label>
                    <textarea 
                        name="description" 
                        id="description"
                        required 
                        minLength="10"
                        value={values.description}
                        onChange={changeHandler}    
                    >
                    </textarea>
                    <input className="btn submit" type="submit" value="Create Doctor"/>
                </div>
            </form>
        </section>
    );
}