import './App.css'
function BookDetails(props){
    const bookdet=props.bookdet;

    return(
        <div className="div">
        <h1>Book Details</h1>
        <ul>
            {bookdet.map((item,index)=>(
                <li key={index}>
                    <h3>{item.name}</h3>
                    <p>{item.price}</p>
                </li>
            ))}
        </ul>
        </div>
    );
}

export default BookDetails;