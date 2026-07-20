import {Home} from './components/Home';
import {About} from './components/About';
import {Contact} from './components/Contact';
import './App.css';

function App() {
  return (
    <div className="container">
      <Home/>
      <About/>
      <Contact/>
    </div>
  );
}

export default App;
