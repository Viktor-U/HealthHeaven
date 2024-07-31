import { createContext , useContext, useState} from "react";

export const AuthContext = createContext({
    userId: '',
    firstName: '',
    lastName: '',
    email: '',
    role: '',
    accessToken: '',
    isAuthenticated: false,
    changeAuthState: (authState = {}) => null,
});

export function AuthContextProvider(props) {

    const [authState, setAuthState] = useState({});

    const changeAuthState = (state) => {
        localStorage.setItem('accessToken', state.accessToken);
    
        setAuthState(state);
      };

    const contextData = {
        userId: authState.id,
        firstName: authState.firstName,
        lastName: authState.lastName,
        email: authState.email,
        role: authState.role,
        accessToken: authState.accessToken,
        isAuthenticated: !!authState.email,
        changeAuthState,
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