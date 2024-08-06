import { Link, useNavigate} from "react-router-dom";

import { useLogin } from "../../hooks/useAuth";
import { useForm } from "../../hooks/useForm";
import { useState } from "react";

const initialValues = {email: '', password: ''};

export default function Login(){
    const [error, setError] = useState();
    const login = useLogin();
    const navigate = useNavigate();

    const loginHandler = async ({ email, password }) => {

        try {
            await login(email, password)
            navigate('/');
        } catch (err) {
            setError('Invalid email or password');
        }
    };
    const { 
        values,
        changeHandler, 
        submitHandler 
    } = useForm (initialValues, loginHandler);



    return(
        <section id="login-page" className="auth">
            <form id="login" onSubmit={submitHandler}>
                <div className="container">
                    <div className="brand-logo"></div>
                    <h1>Login</h1>
                    {error && (
                        <p>
                            <span className="error-message ">{error}</span>
                        </p>
                    )}
                    <label htmlFor="email">Email:</label>
                    <input 
                        type="email" 
                        id="email" 
                        name="email"
                        value={values.email}
                        onChange={changeHandler} 
                        placeholder="mariaXD@gmail.com"
                    />

                    <label htmlFor="login-pass">Password:</label>
                    <input 
                        type="password" 
                        id="login-password" 
                        name="password"
                        value={values.password}
                        onChange={changeHandler}
                    />
                    <input type="submit" className="btn submit" value="Login"/>
                    <p className="field">
                        <span>If you don't have profile click <Link to="/register">here</Link></span>
                    </p>
                </div>
            </form>
        </section>
    );
}