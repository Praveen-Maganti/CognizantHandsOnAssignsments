import { useState } from "react";
import "./App.css";

function App() {
  const [name, setName] = useState("");
  const [complaint, setComplaint] = useState("");

  const handleForm = (event) => {
    event.preventDefault();

    const transactionId = Math.floor(Math.random() * 100);

    const msg =
      "Thanks " +
      name +
      "\nYour Complaint was Submitted Successfully\nTransaction ID: " +
      transactionId;

    alert(msg);
  };

  return (
    <div className="form">
    <form onSubmit={handleForm}>
      <h1>Register your complaints here!!!</h1>

       <div className="field">
          <label>Name</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
      </div>

      <div className="field">
        <label>Complaint</label>
        <textarea
          value={complaint}
          onChange={(e) => setComplaint(e.target.value)}
        ></textarea>
      </div>

      <button type="submit">Submit</button>
    </form>
    </div>
  );
}

export default App;