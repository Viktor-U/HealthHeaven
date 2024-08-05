import {Link} from "react-router-dom";
import { useAuthContext } from "../../contexts/AuthContext";

export default function Header(){
    const {isAuthenticated, role, email} = useAuthContext();


    return(
        <header>
            <h1><Link className="home" to="/">HealthHeavan {email}</Link></h1>
            <nav>
                <Link to="/doctors">All Doctors</Link>
                <Link to="/shop">Shop</Link>
                <Link to="/articles">Articles</Link>
                

                {role==="ADMIN"
                    ?(<Link to="/doctors/create">Create Doctor</Link>)
                    :(<></>)
                }
             
                {isAuthenticated
                    ?(
                        <div id="user">
                            <Link to="/articles/create">Create Articles</Link>
                            <Link to="/cart">My Cart</Link>
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