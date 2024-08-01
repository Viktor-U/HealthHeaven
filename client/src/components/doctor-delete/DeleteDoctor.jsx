import {Navigate, useParams} from 'react-router-dom';
import { useDeleteDoctor } from '../../hooks/useDoctors';

export default function DeleteDoctor() {

    const {doctorId} = useParams();

    useDeleteDoctor(doctorId);
    return  <Navigate to="/doctors"/>
    
}