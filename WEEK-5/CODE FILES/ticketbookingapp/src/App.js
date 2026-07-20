import { useState } from "react";

function App() {
  const [login, setLogin] = useState(false);

  return (
    <div style={{ margin: "100px" }}>
      {login ? (
        <>
          <h1>Welcome back</h1>
          <button onClick={() => setLogin(false)}>Logout</button>
        </>
      ) : (
        <>
          <h1>Please sign up.</h1>
          <button onClick={() => setLogin(true)}>Login</button>
        </>
      )}
    </div>
  );
}

export default App;