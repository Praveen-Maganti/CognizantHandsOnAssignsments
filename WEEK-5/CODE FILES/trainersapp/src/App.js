import {BrowserRouter, Routes,Route,Link} from "react-router-dom";

import Home from './Home';
import TrainerList from "./trainerslist";
import TrainerDetails from './trainerDetails';
import './App.css';

function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to='/'>Home</Link>

        {"|"}

        <Link to="/trainers">Trainers</Link>
      </nav>

      <Routes>
         <Route path="/" element={<Home />} />

                <Route
                    path="/trainers"
                    element={<TrainerList />}
                />

                <Route
                    path="/trainers/:id"
                    element={<TrainerDetails />}
                />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
