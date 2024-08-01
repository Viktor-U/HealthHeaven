import { useNavigate, useParams } from "react-router-dom";
import { useForm } from "../../hooks/useForm";
import { useEditDoctor, useGetOneDoctor } from "../../hooks/useDoctors";




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
            // TODO: set error state and display error
            console.log(err.message);
            console.log(doctorId);
        }
    };

 

    const{
        values,
        changeHandler,
        submitHandler,
    } = useForm(initialValues, editHandler);


    return (
        // <!-- Edit Page ( Only for the creator )-->
        <section id="edit-page" className="auth">
            <form id="edit" onSubmit={submitHandler}>
                <div className="container">

                    <h1>Edit Doctor</h1>
                    <label className="label-create-edit" htmlFor="doc-name">Doctor Name:</label>
                    <input 
                        type="text" 
                        id="name" 
                        name="name" 
                        value={values.name}
                        onChange={changeHandler}
                        placeholder="Enter doctor name..."
                    />

                    <label className="label-create-edit" htmlFor="specialization">Specialization:</label>
                    <input 
                        type="text" 
                        id="specialization" 
                        name="specialization" 
                        value={values.specialization}
                        onChange={changeHandler}
                        placeholder="Enter doctor specialization..."
                    />

                    <label className="label-create-edit" htmlFor="phoneNumbers">Phone Number:</label>
                    <input 
                        type="text" 
                        id="phoneNumber" 
                        name="phoneNumber" 
                        value={values.phoneNumber}
                        onChange={changeHandler}
                        placeholder="0893664222"
                    />

                    <label className="label-create-edit" htmlFor="doctor-img">Profile PictureURL:</label>
                    <input 
                        type="text" 
                        id="profilePictureURL" 
                        name="profilePictureURL"
                        value={values.profilePictureURL}
                        onChange={changeHandler}
                        placeholder="Upload a photo..."

                    />

                    <label className="label-create-edit" htmlFor="description">Description:</label>
                    <textarea 
                        name="description" 
                        id="description"
                        value={values.description}
                        onChange={changeHandler}    
                    >
                    </textarea>
                    <input className="btn submit" type="submit" value="Edit Doctor"/>

                </div>
            </form>
        </section>
    );
}