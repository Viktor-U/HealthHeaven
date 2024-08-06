import { Link, useNavigate } from "react-router-dom";
import { useLogin } from "../../hooks/useAuth";
import { useForm } from "../../hooks/useForm";
import { useState } from "react";
import { useTranslation } from "react-i18next";

const initialValues = { email: '', password: '' };

export default function Login() {
    const [error, setError] = useState();
    const login = useLogin();
    const navigate = useNavigate();
    const { t } = useTranslation();

    const loginHandler = async ({ email, password }) => {
        try {
            await login(email, password)
            navigate('/');
        } catch (err) {
            setError(t('invalid_email_or_password'));
        }
    };

    const { 
        values,
        changeHandler, 
        submitHandler 
    } = useForm(initialValues, loginHandler);

    return (
        <section id="login-page" className="auth">
            <form id="login" onSubmit={submitHandler}>
                <div className="container">
                    <div className="brand-logo"></div>
                    <h1>{t('login')}</h1>
                    {error && (
                        <p>
                            <span className="error-message">{error}</span>
                        </p>
                    )}
                    <label htmlFor="email">{t('email')}:</label>
                    <input 
                        type="email" 
                        id="email" 
                        name="email"
                        value={values.email}
                        onChange={changeHandler} 
                        placeholder="mariaXD@gmail.com"
                    />

                    <label htmlFor="login-pass">{t('password')}:</label>
                    <input 
                        type="password" 
                        id="login-password" 
                        name="password"
                        value={values.password}
                        onChange={changeHandler}
                    />
                    <input type="submit" className="btn submit" value={t('login')} />
                    <p className="field">
                        <span>{t('no_profile')} <Link to="/register">{t('here')}</Link></span>
                    </p>
                </div>
            </form>
        </section>
    );
}
