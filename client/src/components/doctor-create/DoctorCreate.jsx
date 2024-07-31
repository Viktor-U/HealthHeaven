import { useNavigate } from "react-router-dom";
import { useForm } from "../../hooks/useForm";
import { useCreateDoctor } from "../../hooks/useDoctors";


const initialValues = {
    name: '',
    specialization: '',
    phoneNumber: '',
    imageUrl: '',
    description: '',
};

export default function DoctorCreate(){
    const navigate = useNavigate();

    const createGame = useCreateDoctor();

    const createHandler = async (values) => {
        try{
            const {id: doctorId} = await createGame(values);
            navigate(`/doctors/${doctorId}/details`);
        }catch(err) {
            // TODO: set error state and display error
            console.log(err.message);
        }
    };

    const{
        values,
        changeHandler,
        submitHandler,
    } = useForm(initialValues, createHandler);

    return(
        // <!-- Create Page ( Only for logged-in users ) -->
        <section id="create-page" className="auth">
            <form id="create" onSubmit={submitHandler} >
                <div className="container">

                    <h1>Create Game</h1>
                    <label htmlFor="doc-name">Doctor Name:</label>
                    <input 
                        type="text" 
                        id="name" 
                        name="name" 
                        value={values.name}
                        onChange={changeHandler}
                        placeholder="Enter doctor name..."
                    />

                    <label htmlFor="specialization">Specialization:</label>
                    <input 
                        type="text" 
                        id="specialization" 
                        name="specialization" 
                        value={values.specialization}
                        onChange={changeHandler}
                        placeholder="Enter doctor specialization..."
                    />

                    <label htmlFor="phoneNumbers">Phone Number:</label>
                    <input 
                        type="text" 
                        id="phoneNumber" 
                        name="phoneNumber" 
                        value={values.phoneNumber}
                        onChange={changeHandler}
                        placeholder="0893664222"
                    />

                    <label htmlFor="game-img">Profile PictureURL:</label>
                    <input 
                        type="text" 
                        id="imageUrl" 
                        name="imageUrl"
                        value={values.imageUrl}
                        onChange={changeHandler}
                        placeholder="Upload a photo..."

                    />

                    <label htmlFor="description">Description:</label>
                    <textarea 
                        name="description" 
                        id="description"
                        value={values.description}
                        onChange={changeHandler}    
                    >
                    </textarea>
                    <input className="btn submit" type="submit" value="Create Game"/>
                </div>
            </form>
        </section>
    );
}