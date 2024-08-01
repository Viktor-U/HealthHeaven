import { useEffect, useState } from "react";
import doctorsAPI from "../api/doctors-api";

export default function useGetAllDoctors() {
    const [doctors, setDoctors] = useState([]);

    useEffect(() => {
        (async () =>{
            const result =await doctorsAPI.getAll();
            setDoctors(result);
        })();

      

    }, [])

    return [doctors, setDoctors];
}

export function useGetOneDoctor(doctorId) {
    const [doctors, setDoctors] = useState({});
    
    useEffect(() => {
        (async () => {
            const result = await doctorsAPI.getOne(doctorId);

            setDoctors(result);
        })();
    }, [doctorId]);
    return[
        doctors,
        setDoctors,
    ];
}

export function useCreateDoctor() {
    const doctorCreateHandler = (doctorData) => doctorsAPI.create(doctorData);
      
    return doctorCreateHandler;

}

export function useEditDoctor() {
    const doctorEditHandler = (doctorData) => doctorsAPI.edit(doctorData);
      
    return doctorEditHandler;

}

export function  useDeleteDoctor(doctorId) {
    
    const useDeleteDoc =  doctorsAPI.deleteDoctor(doctorId);
    return useDeleteDoc;
}