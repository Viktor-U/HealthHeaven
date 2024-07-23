import { BrowserRouter, Routes, Route } from "react-router-dom";
import Register from "./Components/Register";
import UsersList from "./Components/UserList";
import Layout from "./Components/Layout";

function App() {

  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route path="/register" element={<Register />} />
            <Route path="/user-list" element={<UsersList />} />
          </Route>
        </Routes>
      </BrowserRouter>
  )
}

export default App;

