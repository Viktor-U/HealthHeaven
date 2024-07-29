import { useNavigate} from "react-router-dom";

import { useRegister } from "../../hooks/useAuth";
import { useForm } from "../../hooks/useForm";
import { useState } from "react";

const initialValues = {firstName: '', flastName: '', email: '', password: '', 'confirm-password': ''};


export default function Register(){
    const [error, setError] = useState();
    const register = useRegister();
    const navigate = useNavigate();

    const registerHandler = async (values) => {
        if (values.password !== values['confirm-password']) {
            return setError('Password missmatch!');
        }
        try {
            await register(values.firstName, values.lastName, values.email, values.password)
            navigate('/');
        } catch (err) {
            setError(err.message);
            console.error(err.message);
        }
    };

    const {
        values,
        changeHandler,
        submitHandler,
    } = useForm(initialValues, registerHandler )


    return(
        
        // <!-- Register Page ( Only for Guest users ) -->
        <section id="register-page" className="content auth">
            <form id="register" onSubmit={submitHandler}>
                <div className="container">
                    <div className="brand-logo"></div>
                    <h1>Register</h1>
                    {error && (
                        <p>
                            <span className="error-message ">{error}</span>
                        </p>
                    )}

                    <label htmlFor="firstName">First Name:</label>
                    <input 
                        type="text" 
                        id="firstName" 
                        name="firstName" 
                        value={values.firstName}
                        onChange={changeHandler}
                        placeholder="Maria"
                    />

                    <label htmlFor="lastName">Last Name:</label>
                    <input 
                        type="text" 
                        id="lastName" 
                        name="lastName" 
                        value={values.lastName}
                        onChange={changeHandler}
                        placeholder="Ivanova"
                    />

                    <label htmlFor="email">Email:</label>
                    <input 
                        type="email" 
                        id="email" 
                        name="email" 
                        value={values.email}
                        onChange={changeHandler}
                        placeholder="maria@email.com"
                    />

                    <label htmlFor="pass">Password:</label>
                    <input 
                        type="password" 
                        name="password" 
                        id="register-password"
                        value={values.password}
                        onChange={changeHandler}
                    />

                    <label htmlFor="con-pass">Confirm Password:</label>
                    <input 
                        type="password" 
                        name="confirm-password" 
                        id="confirm-password"
                        value={values['confirm-password']}
                        onChange={changeHandler}
                    />

                    <input className="btn submit" type="submit" value="Register"/>

                    <p className="field">
                        <span>If you already have profile click <a href="#">here</a></span>
                    </p>
                </div>
            </form>
        </section>
    );
}