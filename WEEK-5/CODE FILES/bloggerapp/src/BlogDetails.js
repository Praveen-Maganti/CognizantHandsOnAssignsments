import './App.css'
function BlogDetails(props){
    const blogdet=props.blogdet;

    return(
        <div className="div">
        <h1>Blog Details</h1>
        <ul>
            {blogdet.map((item,index)=>(
                <li key={index}>
                    <h1>{item.name}</h1>
                    <h3>{item.author}</h3>
                    <p>{item.description}</p>
                </li>
            ))}
        </ul>
        </div>
    );
}

export default BlogDetails;