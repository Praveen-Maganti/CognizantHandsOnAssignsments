import './App.css';

function App() {
    const price=80000;

  return (
    <div className="container">
      <h1>Office Space, at Affordable Range</h1>
      <img src="https://png.pngtree.com/thumb_back/fw800/background/20230531/pngtree-corporate-business-office-workplace-background-image-image_2856638.jpg"
      alt="office"/>
      <div>
        <h1>Name: DBS</h1>
        <h2 style={{color:price<=60000? "red":"green"}}>Rent: Rs. {price}</h2>
        <h1>Address: Chennai</h1>
      </div>
    </div>
  );
}

export default App;
