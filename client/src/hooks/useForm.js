import { useState } from "react";

export function useForm(initialValues, submitCallback){
    const [values, setValues] = useState(initialValues);

    //todo :add sup checbox
    const changeHandler =  (e) => {
        setValues(state => ({
            ...state,
            [e.target.name]: e.target.value
       }))
    };

    const submitHandler =(e) => {
        e.preventDefault();

        submitCallback(values);
    }

    return{

    };
}