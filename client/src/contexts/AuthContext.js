import { createContext } from "react";

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