import {Navigate, useParams} from 'react-router-dom';
import { useDeleteDoctor } from '../../hooks/useDoctors';

export default function DeleteDoctor() {

    const {doctorId} = useParams();

    useDeleteDoctor(doctorId);
    //toda make it async fuction
    return <Navigate to="/doctors"/>
    
}