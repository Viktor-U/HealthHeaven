import React, { useEffect, useState } from 'react';
import { useParams, Navigate } from 'react-router-dom';
import { useDeleteDoctor } from '../../hooks/useDoctors';

export default function DeleteDoctor() {
    const { doctorId } = useParams();
    const [deleteDoctor, setDeleteDoctor] = useState(false);

    useEffect(() => {
        const deleteDoctorAsync = async () => {
            await useDeleteDoctor(doctorId);
            setDeleteDoctor(true);
        };

        deleteDoctorAsync();
    }, [doctorId]);

    
    return <Navigate to="/doctors" />;
    

}