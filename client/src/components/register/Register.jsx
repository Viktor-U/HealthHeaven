import { Link, useNavigate } from "react-router-dom";
import { useRegister } from "../../hooks/useAuth";
import { useForm } from "../../hooks/useForm";
import { useState } from "react";
import { useTranslation } from "react-i18next";

const initialValues = { firstName: '', lastName: '', email: '', password: '', 'confirm-password': '' };

export default function Register() {
    const [error, setError] = useState();
    const register = useRegister();
    const navigate = useNavigate();
    const { t } = useTranslation();

    const registerHandler = async (values) => {
        if (!values.firstName || values.firstName.trim().length < 2 || values.firstName.trim().length > 20) {
            return setError(t('first_name_error'));
        }

        if (!values.lastName || values.lastName.trim().length < 2 || values.lastName.trim().length > 20) {
            return setError(t('last_name_error'));
        }

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!values.email || !emailRegex.test(values.email)) {
            return setError(t('valid_email_error'));
        }

        if (!values.password || values.password.length < 8) {
            return setError(t('password_length_error'));
        }

        const uppercaseRegex = /[A-Z]/;
        if (!uppercaseRegex.test(values.password)) {
            return setError(t('password_uppercase_error'));
        }
        const lowercaseRegex = /[a-z]/;
        if (!lowercaseRegex.test(values.password)) {
            return setError(t('password_lowercase_error'));
        }

        if (values.password !== values['confirm-password']) {
            return setError(t('password_match_error'));
        }

        try {
            await register(values.firstName, values.lastName, values.email, values.password);
            navigate('/');
        } catch (err) {
            setError(err.message);
        }
    };

    const {
        values,
        changeHandler,
        submitHandler,
    } = useForm(initialValues, registerHandler);

    return (
        <section id="register-page" className="content auth">
            <form id="register" onSubmit={submitHandler}>
                <div className="container">
                    <div className="brand-logo"></div>
                    <h1>{t('register')}</h1>
                    {error && (
                        <p>
                            <span className="error-message">{error}</span>
                        </p>
                    )}

                    <label htmlFor="firstName">{t('first_name')}:</label>
                    <input
                        type="text"
                        id="firstName"
                        name="firstName"
                        value={values.firstName}
                        onChange={changeHandler}
                        placeholder={t('first_name')}
                    />

                    <label htmlFor="lastName">{t('last_name')}:</label>
                    <input
                        type="text"
                        id="lastName"
                        name="lastName"
                        value={values.lastName}
                        onChange={changeHandler}
                        placeholder={t('last_name')}
                    />

                    <label htmlFor="email">{t('email')}:</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        value={values.email}
                        onChange={changeHandler}
                        placeholder="maria@email.com"
                    />

                    <label htmlFor="pass">{t('password')}:</label>
                    <input
                        type="password"
                        name="password"
                        id="register-password"
                        value={values.password}
                        onChange={changeHandler}
                    />

                    <label htmlFor="con-pass">{t('confirm_password')}:</label>
                    <input
                        type="password"
                        name="confirm-password"
                        id="confirm-password"
                        value={values['confirm-password']}
                        onChange={changeHandler}
                    />

                    <input className="btn submit" type="submit" value={t('register')} />

                    <p className="field">
                        <span>{t('already_have_profile')} <Link to="/login">{t('here')}</Link></span>
                    </p>
                </div>
            </form>
        </section>
    );
}
