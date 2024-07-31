import { createContext , useContext} from "react";
import usePersistedState from "../hooks/usePersistedState";

export const AuthContext = createContext({
    userId: '',
    firstName: '',
    lastName: '',
    email: '',
    role: '',
    accessToken: '',
    isAuthenticated: false,
    changeAuthState: (authState = {}) => null,
    logout: () => null,
});

export function AuthContextProvider(props) {

    const [authState, setAuthState] = usePersistedState('auth', {});

    const changeAuthState = (state) => {
        localStorage.setItem('accessToken', state.token);
    
        setAuthState(state);
    };

    const logout = () => {
        setAuthState(null);
    };



    const contextData = {
        userId: authState?.id,
        firstName: authState?.firstName,
        lastName: authState?.lastName,
        email: authState?.email,
        role: authState?.role,
        accessToken: authState?.token,
        isAuthenticated: !!authState?.email,
        changeAuthState,
        logout,
    }
    

    return(
        <AuthContext.Provider value={contextData}>
            {props.children}

        </AuthContext.Provider>

    );
}

export function useAuthContext() {

    return useContext(AuthContext);

}