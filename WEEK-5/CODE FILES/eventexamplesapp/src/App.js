import './App.css';
import { useState } from 'react';
import CurrencyConverter from "./CurrencyConverter";

function App() {
  const [count,setCount]=useState(0);

  return(
    <div className="container">
      <h2>Count: {count}</h2>
      <div className="div">
          <button onClick={()=> setCount(count+1)}>Increment</button>
          <button onClick={()=> setCount(count-1)}>Decrement</button>
          <button onClick={()=> alert("welcome")}>Say welcome</button>
          <button onClick={()=> alert("I was clicked")}>Click on me</button>
      </div>

      <h1>Currency Converter!!!</h1>

      <CurrencyConverter />
    </div>
  );
}

export default App;
