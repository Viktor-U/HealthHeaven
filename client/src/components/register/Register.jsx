import { Link, useNavigate} from "react-router-dom";

import { useRegister } from "../../hooks/useAuth";
import { useForm } from "../../hooks/useForm";
import { useState } from "react";

const initialValues = {firstName: '', flastName: '', email: '', password: '', 'confirm-password': ''};


export default function Register(){
    const [error, setError] = useState();
    const register = useRegister();
    const navigate = useNavigate();

    const registerHandler = async (values) => {
    if (!values.firstName || values.firstName.trim().length < 2 || values.firstName.trim().length > 20) {
        return setError('First name must be between 2 and 20 characters!');
    }

    if (!values.lastName || values.lastName.trim().length < 2 || values.lastName.trim().length > 20) {
        return setError('Last name must be between 2 and 20 characters!');
    }

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!values.email || !emailRegex.test(values.email)) {
        return setError('A valid email is required!');
    }

    if (!values.password || values.password.length < 8) {
        return setError('Password must be at least 8 characters long!');
    }

    const uppercaseRegex = /[A-Z]/;
    if (!uppercaseRegex.test(values.password)) {
        return setError('Password must contain at least one uppercase letter!');
    }
    const lowercaseRegex = /[a-z]/;
    if (!lowercaseRegex.test(values.password)) {
        return setError('Password must contain at least one lowercase letter!');
    }

    if (values.password !== values['confirm-password']) {
        return setError('Passwords do not match!');
    }
        try {
            await register(values.firstName, values.lastName, values.email, values.password)
            navigate('/');
        } catch (err) {
            setError(err.message);
        }
    };

    const {
        values,
        changeHandler,
        submitHandler,
    } = useForm(initialValues, registerHandler )


    return(
        
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
                        <span>If you already have profile click <Link to="/login">here</Link></span>
                    </p>
                </div>
            </form>
        </section>
    );
}