import {Navigate} from 'react-router-dom';
import { useAuthContext } from '../../contexts/AuthContext';

export default function Logout() {

    const { logout } = useAuthContext();

    logout();

    return <Navigate to="/"/>
    
}