import './App.css';
import { useState } from 'react';
function App() {
  const [password,setPassword]=useState("");
  const [name,setName]=useState("");
  const [email,setEmail]=useState("");
  const handleSubmit=(event)=>{
    if(password==="" || name==="" || email===""){
      alert("Enter a valid Input");
      return;
    }

    if(name.length<5){
      alert("Name should have atleast 5 characters");
      return;
    }

    if(!email.includes("@")){
        alert("Enter a valid email Id");
      return;
    }

    if(password.length<8){
      alert("Password should have atleast 8 characters");
      return;
    }

    alert("Form Submitted!!");
    event.preventDefault();
  }
  return (
    <div className="form">
      <form onSubmit={handleSubmit}> 
        <h1>Register Here!!!</h1>
         <div className="field">
          <label>Name</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div className="field">
          <label>Email</label>
          <input
            type="text"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="field">
          <label>Password</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>

        <button>Submit</button>
      </form>
    </div>
  );
}

export default App;
