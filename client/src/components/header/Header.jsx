import {Link} from "react-router-dom";
import { useAuthContext } from "../../contexts/AuthContext";
import { getAccessToken } from "../../utils/authUtils";

export default function Header(){
    const {isAuthenticated} = useAuthContext();


    return(
        <header>
            <h1><Link className="home" to="/">HealthHeavan</Link></h1>
            <nav>
                <Link to="/doctors">All Doctors</Link>
                {isAuthenticated
                    ?(
                        <div id="user">
                            <Link to="/doctors/create">Create Doctor</Link>
                            <Link to="/logout">Logout</Link>
                        </div>
                    )
                    :(
                        <div id="guest">
                            <Link to="/login">Login</Link>
                            <Link to="/register">Register</Link>           
                         </div>
                    )
                
                }
             
                
            </nav>
        </header>
    );
}