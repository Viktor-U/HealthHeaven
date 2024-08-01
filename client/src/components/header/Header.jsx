import {Link} from "react-router-dom";
import { useAuthContext } from "../../contexts/AuthContext";
import { getAccessToken } from "../../utils/authUtils";

export default function Header(){
    const {isAuthenticated, role} = useAuthContext();


    return(
        <header>
            <h1><Link className="home" to="/">HealthHeavan</Link></h1>
            <nav>
                <Link to="/doctors">All Doctors</Link>
                <Link to="/shop">Shop</Link>

                {role==="ADMIN"
                    ?(<Link to="/doctors/create">Create Doctor</Link>)
                    :(<></>)
                }
             
                {isAuthenticated
                    ?(
                        <div id="user">
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